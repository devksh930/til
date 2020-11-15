package jiot.raspi.chatbotexam;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class test {
    public static String getsensor(String url){
        String data = null;
        try {
            Document doc = Jsoup.connect(url).get();
            Element el = doc.body();
            data = Jsoup.parse(el.toString()).text();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
