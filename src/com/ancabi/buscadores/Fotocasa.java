package com.ancabi.buscadores;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

/**
 * Created by ancabi on 10/9/16.
 */
public class Fotocasa {

    private URL url;


    public Fotocasa(URL url){
        this.url=url;
    }

    public void search(){

        Document d = null;
        try {
            d = Jsoup.connect(url.toString()).get();

            Element pisos = d.getElementById("ctl00_content1_gridphotos_listAds");

            Elements k=pisos.getElementsByTag("li");

            for (Element l : k) {
                System.out.println(l.text());
            }



        } catch (IOException e) {
            e.printStackTrace();
        }



    }


}
