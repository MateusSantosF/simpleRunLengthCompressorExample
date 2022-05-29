/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import interfaces.ICompressor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mateus
 */
public class Decompressor implements ICompressor {

    private Compressor compressor;
    private String decompressedMatriz;
    private String absolutePathDecompressedImage;

    private Decompressor() {

    }

    public static ICompressor Configure(Compressor compressor) {
        Decompressor c = new Decompressor();
        c.compressor = compressor;
        return c;
    }

    @Override
    public Decompressor Build() {
        decompress();
        return this;
    }

    public void decompress() {

        File file = new File(compressor.getAbsolutePathFile());
        StringBuilder descompressed = new StringBuilder();

        if (file.exists()) {

            try ( FileReader reader = new FileReader(file)) {
                BufferedReader buffer = new BufferedReader(reader);

                String linha = "";
                while ((linha = buffer.readLine()) != null) {

                    String[] colors = linha.split(" ");

                    for (String s : colors) {
                        String number = "";
                        String color = "";
                        for (char c : s.toCharArray()) {

                            if (Character.isDigit(c)) {
                                number += c;
                            } else {
                                color = c + "";
                            }
                        }

                        for (int i = 0; i < Integer.parseInt(number); i++) {
                            descompressed.append(color);
                        }
                    }
                }
            } catch (Exception e) {

            }
        }
        this.decompressedMatriz = descompressed.toString();
        recreateImage();
    }

    private void recreateImage() {

        int count = 1;
        File file = new File(compressor.getPathOutputFile() + "/decompressedImage.txt");

        while (file.exists()) {
            file = new File(compressor.getPathOutputFile() + "/decompressedImage.txt" + count);
            count++;
        }
        this.absolutePathDecompressedImage = file.getAbsolutePath();

        boolean sucess = false;
        try {
            sucess = file.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Compressor.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (sucess) {
            try {
                try ( FileWriter writer = new FileWriter(file)) {
                    BufferedWriter buffer = new BufferedWriter(writer);
                    for (int i = 0; i < decompressedMatriz.length(); i++) {

                        if (i % compressor.getMatrizSize() == 0 && i != 0) {
                            buffer.newLine();
                        }
                        buffer.write(decompressedMatriz.charAt(i) + " ");
                    }
                    buffer.close();
                }

            } catch (IOException ex) {
                Logger.getLogger(Compressor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("File decompress with sucess! Check File");
    }

    public void showImage() {

        File file = new File(absolutePathDecompressedImage);

        if (file.exists()) {

            try ( FileReader reader = new FileReader(file)) {
                BufferedReader buffer = new BufferedReader(reader);
                
                String line = "";
                while( (line = buffer.readLine()) != null){
                    System.out.println(line);
                }
              
            } catch (Exception e) {

            }
        }

    }
}
