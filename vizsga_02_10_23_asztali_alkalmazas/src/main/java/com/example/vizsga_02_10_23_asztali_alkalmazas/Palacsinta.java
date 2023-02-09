package com.example.vizsga_02_10_23_asztali_alkalmazas;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Palacsinta {
    String nev;
    int ar;
    Tipus tipus;
    int minosites;
    String foOsszetevo;

    public Palacsinta(String nev, int ar, String tipusNev, int minosites, String foOsszetevo) {
        this.nev = nev;
        this.ar = ar;
        this.tipus = new Tipus(tipusNev);
        this.minosites = minosites;
        this.foOsszetevo = foOsszetevo;
    }

    // 2. static file read method
    public static ArrayList<Palacsinta> loadPalacsintakFromCsv(String filePath) {

        ArrayList<Palacsinta> palacsintak = new ArrayList<>();

        try ( FileReader fr = new FileReader(filePath) ) {

            BufferedReader br = new BufferedReader(fr);
            ArrayList<HashMap<String, String>> palacsintaObjects = new ArrayList<>();

            String[] headerNames = br.readLine().split(";");

            // System.out.println(String.join(";", headerNames));

            String newLine = "";
            while( (newLine = br.readLine()) != null ) {
                HashMap<String, String> dataObject = new HashMap<>();
                String[] dataValues = newLine.split(";");

                for (int i = 0; i < headerNames.length; i++) {
                    dataObject.put(headerNames[i], dataValues[i]);
                }
                palacsintaObjects.add(dataObject);
            }

            for (HashMap<String, String> dataSet : palacsintaObjects) {
                palacsintak.add(new Palacsinta(
                        dataSet.get("NEV"),
                        Integer.parseInt(dataSet.get("AR")),
                        dataSet.get("TIPUS"),
                        Integer.parseInt(dataSet.get("MINOSITES")),
                        dataSet.get("FO_OSSZETEVO")
                ));
            }

        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return  palacsintak;
    }

    // 5 - ar novelo metodus
    public int increasePrice(int percentageIncrease) {
        return (int) Math.round(this.ar * ((100 + percentageIncrease)/100.0));
    }

    @Override
    public String toString() {
        return "Palacsinta{" +
                "nev='" + nev + '\'' +
                ", ar=" + ar +
                ", tipus=" + tipus +
                ", minosites=" + minosites +
                ", foOsszetevo='" + foOsszetevo + '\'' +
                '}';
    }
}
