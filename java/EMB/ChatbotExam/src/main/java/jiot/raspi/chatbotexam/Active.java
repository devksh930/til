package jiot.raspi.chatbotexam;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Active {
    public static void reqexe(String url) {
        HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpGet httpget = new HttpGet(url);
//            System.out.println("executing request " + httpget.getURI());
            HttpResponse entity = httpclient.execute(httpget);
//            System.out.println("----------------------------------------");
            httpclient.getConnectionManager().shutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
