package com.ancabi.buscadores;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by ancabi on 10/9/16.
 */
public class Fotocasa {

    private URL url;
    private String name;
    private Set<String> pisosSaved=new HashSet<>();


    public Fotocasa(URL url){

        this.url=url;
        this.name=url.getPath().replace("/","-");

    }

    public void search(){

        try {

            boolean firstTime = createFile();

            if(!firstTime){
                loadPisos();
            }

            Document d = Jsoup.connect(url.toString()).get();

            Element gridPisos = d.getElementById("ctl00_content1_gridphotos_listAds");

            Elements pisos=gridPisos.getElementsByTag("li");

            for (Element piso : pisos) {
                if(saveIfNotExist(piso.attr("data-adid"))){
                    break;
                }
            }



        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private void loadPisos() throws IOException {

        InputStream fis = new FileInputStream(name);
        InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
        BufferedReader br = new BufferedReader(isr);

        String line;

        while((line=br.readLine()) != null){

            pisosSaved.add(line);

        }

    }

    private boolean saveIfNotExist(String id) throws IOException {

        if(pisosSaved.contains(id)){
            return false;

        }else{
            addPiso(id);
            return true;
        }

    }

    private void addPiso(String id) throws IOException {

        File fout = new File(name);
        FileOutputStream fos = new FileOutputStream(fout);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        bw.newLine();
        bw.write(id);

        fos.close();
    }

    private boolean createFile() throws IOException {

        File f = new File(name+".txt");

        if(f.exists()){

            return false;

        }else{

            f.createNewFile();

            return true;
        }

    }


}
