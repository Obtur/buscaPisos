package com.ancabi;

import com.ancabi.buscadores.Fotocasa;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    public static void main(String[] args) {

        System.out.println("****************************");
        System.out.println("Busca piso");
        System.out.println("****************************");

        URL url= null;
        try {
            url = new URL(args[0]);

            if(url.getHost().contains("fotocasa")){
                Fotocasa f = new Fotocasa(url);

                f.search();
            }

            while(true){}

        } catch (MalformedURLException e) {
            System.out.println("ERROR: URL no válida");
        }




    }
}
