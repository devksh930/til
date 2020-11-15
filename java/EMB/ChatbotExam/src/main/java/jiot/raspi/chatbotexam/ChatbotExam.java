/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.chatbotexam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.sun.tools.javac.file.SymbolArchive;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class ChatbotExam {
    public static final String openApiURL = "http://aiopen.etri.re.kr:8000/Dialog";
    public static final String accessKey = "d4a6918f-a60a-426b-8e1f-144933435e9f";    // 발급받은 API Key
    public static final String domain = "IOTTEST";              // 도메인 명
    public static final String access_method = "external_data";     // 도메인 방식

    private String uuidDialog = null;
    private Gson gson = null;
    private boolean bOpened = false;

    public ChatbotExam() {
        gson = new GsonBuilder().disableHtmlEscaping().create();
        bOpened = false;
    }

    public boolean isOpened() {
        return bOpened;
    }

    public String openDialog() {
//        Map<String, Object> request = new HashMap<>();
//        Map<String, String> argument = new HashMap<>();
//  s
//        ////////////////////////// OPEN DIALOG //////////////////////////
//        argument.put("name", domain);
//        argument.put("access_method", access_method);
//        argument.put("method", "open_dialog");
//
//        request.put("access_key", accessKey);
//        request.put("argument", argument);

//        ResponseContent response = sendRequestSync(request);
        JSONObject request = new JSONObject();
        JSONObject argument = new JSONObject();
        argument.put("name", domain);
        argument.put("method", "open_dialog");
        argument.put("access_key", accessKey);
        argument.put("access_method", access_method);

        request.put("access_key", accessKey);
        request.put("argument", argument);

        ResponseContent response = sendRequestSync(request);
        ////////////////////////// GET UUID /////////////////////////
        String sysText = null;
        Map respMap = new HashMap();
        respMap = gson.fromJson(response.body, Map.class);
        Double result = (Double) respMap.get("result");
        if (result == 0) {
            uuidDialog = ((Map) respMap.get("return_object")).get("uuid").toString();
            System.out.println(uuidDialog);
            sysText = ((Map) ((Map) respMap.get("return_object")).get("result")).get("system_text").toString();
            bOpened = true;
        } else if (result == -1) {
            displayMsg(respMap.get("reason").toString());
        }
        return sysText;

    }


    public String sendDialog(String userText) {
        JSONObject request = new JSONObject();
        JSONObject argument = new JSONObject();
        JSONObject pdata = new JSONObject();
        JSONObject sdata = new JSONObject();
        JSONObject cdsdata = new JSONObject();
        JSONObject led1 = new JSONObject();
        JSONObject led2 = new JSONObject();
        JSONObject led3 = new JSONObject();
        JSONObject fan = new JSONObject();
        JSONArray data_array = new JSONArray();

        pdata.clear();
        sdata.clear();
        cdsdata.clear();

        argument.put("method", "dialog");
        argument.put("uuid", uuidDialog);
        argument.put("text", userText);
//        System.out.println(argument);
        request.put("access_key", accessKey);
        request.put("argument", argument);

        ResponseContent response = sendRequestSync(request);
//        System.out.println(response.body.toString());

        Map<String, Object> result = new HashMap<>();
        result = (Map) (((Map) (gson.fromJson(response.body, Map.class).get("return_object"))).get("result"));
//        System.out.println("result /n"+result);

        String state = result.get("state").toString();
        if (state.equals("error")) {
            displayMsg(result.get("message").toString());
        } else if (state.equals("end")) {
            bOpened = false;

        } else if (state.equals("data_request")) {
            SensorDTO sensorDTO = new SensorDTO();

            //        System.out.println(argument);
            request.put("access_key", accessKey);
            request.put("argument", argument);


            pdata.put("Dev.type", "습도");
            sensorDTO.setHmd(test.getsensor("http://192.168.2.14:8080/gethmd"));
            pdata.put("Dev.info", sensorDTO.getHmd());
            data_array.add(pdata);


            sdata.put("Dev.type", "온도");
            sensorDTO.setTemp(test.getsensor("http://192.168.2.14:8080/gettmp"));
            sdata.put("Dev.info", sensorDTO.getTemp());
            data_array.add(sdata);

            cdsdata.put("Dev.type", "조도");
            sensorDTO.setCdr(test.getsensor("http://192.168.2.14:8080/getcds"));
            cdsdata.put("Dev.info", sensorDTO.getCdr());
            data_array.add(cdsdata);

            led1.put("led1", "");
            data_array.add(led1);

            led2.put("led2", "");
            data_array.add(led2);

            led3.put("led3", "");
            data_array.add(led3);

            fan.put("fan", "");
            data_array.add(fan);


//            argument.put("data", String.valueOf(list));

            argument.put("data", data_array);
            request.put("access_key", accessKey);
            request.put("argument", argument);
//            System.out.println(request);
            response = sendRequestSync(request);
            result = (Map) (((Map) (gson.fromJson(response.body, Map.class).get("return_object"))).get("result"));
//            System.out.println("request" + request);
        }
//        System.out.println(result.get("system_text").toString().trim());
        return result.get("system_text").toString();
    }

    public void closeDialog() {
        JSONObject request = new JSONObject();
        JSONObject argument = new JSONObject();

        ////////////////////////// CLOSE DIALOG ////////////////////////// 
        argument.put("method", "dialog");
        argument.put("uuid", uuidDialog);

        request.put("access_key", accessKey);
        request.put("argument", argument);

        ResponseContent response = sendRequestSync(request);

        String result = response.body;
        System.out.println("[state] " + result);
    }

    private ResponseContent sendRequestSync(JSONObject request) {
        ResponseContent response = new ResponseContent();

        URL url;
        try {
            url = new URL(openApiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(gson.toJson(request).getBytes("UTF-8"));
                wr.flush();
            }

            response.code = con.getResponseCode();
            InputStream is = con.getInputStream();
            byte[] buffer = new byte[is.available()];
            int byteRead = is.read(buffer);

            response.body = new String(buffer);
//            System.out.println("responseBody():" + response.body);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ChatbotExam.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChatbotExam.class.getName()).log(Level.SEVERE, null, ex);
        }
        request = null;
        return response;
    }

    class ResponseContent {
        public Integer code = null;
        public String body = null;
    }

    public static void displayMsg(String msg) {
        if (msg != null) {
            System.out.println("[DEBUG] " + msg);
        }
    }

    public static void displaySystemDialog(String msg) {
        if (msg != null) {
            if (msg.trim().equals("led1을 수행했습니다.")) {
                Active.reqexe("http://192.168.2.14:8080/ledon/1");
            }
            if (msg.trim().equals("led2을 수행했습니다.")) {
                Active.reqexe("http://192.168.2.14:8080/ledon/2");
            }
            if (msg.trim().equals("led3을 수행했습니다.")) {
                Active.reqexe("http://192.168.2.14:8080/ledon/3");
            }
            if (msg.trim().equals("fan을 수행했습니다.")) {
                Active.reqexe("http://192.168.2.14:8080/fanon");
            }
            if (msg.trim().equals("led1off를 수행했습니다.")) {
                Active.reqexe("http://192.168.2.14:8080/ledoff/1");
            }
            if (msg.trim().equals("led2off를 수행했습니다.")) {
                Active.reqexe("http://192.168.2.14:8080/ledoff/2");
            }
            if (msg.trim().equals("led3off를 수행했습니다.")) {
                Active.reqexe("http://192.168.2.14:8080/ledoff/3");
            }
            if (msg.trim().equals("fanoff를 수행했습니다.")) {
                Active.reqexe("http://192.168.2.14:8080/fanoff");
            }

            System.out.println("\n[System] " + msg);
            System.out.print("[User] ");
        }
    }

    public static void main(String[] args) {
        System.out.println("Starting pizza order chatbot...\r\n");
        System.out.println("Input 'bye' to close this dialog.\r\n");

//        SensorDTO sensorDTO =new SensorDTO();
//        sensorDTO.setTemp(test.getsensor("http://localhost:8080/gettmp"));
//        sensorDTO.setHmd(test.getsensor("http://localhost:8080/gethmd"));
//        System.out.println(sensorDTO.getHmd());
//        System.out.println(sensorDTO.getTemp());

        ChatbotExam chatbot = new ChatbotExam();
        String sysText = chatbot.openDialog();

        displaySystemDialog(sysText);

        Scanner scanner = new Scanner(System.in);
        for (String line = scanner.nextLine(); !line.trim().equals("bye"); line = scanner.nextLine()) {
            if (line.length() != 0) {
                sysText = chatbot.sendDialog(line);
//                System.out.println("systext");
//                System.out.println(sysText);


                displaySystemDialog(sysText);

            }
            if (!chatbot.isOpened()) break;
        }

        if (chatbot.isOpened()) {
            displaySystemDialog("close this dialog...");
            chatbot.closeDialog();
        }
    }

}
