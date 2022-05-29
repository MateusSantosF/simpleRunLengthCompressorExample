
package model;

import interfaces.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Utils;


/**
 *
 * @author mateus
 */
public class Compressor implements IFile, IMatriz, IColor{
    
    private int matrizSize;
    private int numberColors;
    private String pathOutputFile;
    private String absolutePathFile;
    private String[][] image;
      
    private Compressor(){
        
    }
    
    public static IFile Configure(){
        return new Compressor();
    }
    
    public IMatriz PathFileOutput(String path){
        pathOutputFile = path;
        return this;
    }
    
    public  IColor SetMatrizDimension(int dimension){
        matrizSize = dimension;
        return this;
    }
    
    public IColor SetNumberOfDiffColors(int numberColors){   
        if(numberColors <= 5 ){
            if(numberColors <= 0){
               this.numberColors = 1;
            }else{
                this.numberColors = numberColors;
            }
        }else{
            this.numberColors = 5;
        }
        return this;
    }
    
    public Compressor Build(){
        CreateImage();
        showImage();
        compress();
        return this;
    }

    public int getMatrizSize() {
        return matrizSize;
    }

    public void setMatrizSize(int matrizSize) {
        this.matrizSize = matrizSize;
    }

    public int getNumberColors() {
        return numberColors;
    }

    public void setNumberColors(int numberColors) {
        this.numberColors = numberColors;
    }

    public String getPathOutputFile() {
        return pathOutputFile;
    }

    public void setPathOutputFile(String pathOutputFile) {
        this.pathOutputFile = pathOutputFile;
    }
    
    private void CreateImage(){
        
        String[][] image = new String[matrizSize][matrizSize];
        Utils util = new Utils(numberColors, matrizSize);
        int tiledColor = util.randomTiledColor();
        String color = util.createRandomColor();
        
        for (int i = 0; i < matrizSize; i++) {
            for (int j = 0; j < matrizSize; j++) {
               if(tiledColor > 0){
                   image[i][j] = color;
                   tiledColor--;
               }else{
                   color = util.createRandomColor();
                   tiledColor = util.randomTiledColor();
                   image[i][j] = color;
               }
            }
        }
        
        this.image = image;
    }
    
    public void showImage(){
        for (int i = 0; i < matrizSize; i++) {
            for (int j = 0; j < matrizSize; j++) {
                System.out.print(image[i][j] + " ");
            }
            System.out.println("");
        }  
    }
    
    public void compress(){
        
        StringBuilder stringBuilder = new StringBuilder();
        
        for (int i = 0; i < matrizSize; i++) {
            for (int j = 0; j < matrizSize; j++) {
                stringBuilder.append(image[i][j]);
            }
        }
        
        int previousCount = 1;
        char previousChar = '%';
        boolean first = true;
        String mappedImage = stringBuilder.toString();
        StringBuilder compressedImage = new StringBuilder();
        
        for(char c : mappedImage.toCharArray()){
            if(first){
                previousChar = c;
                first = false;
            }else{
                if(c == previousChar){
                    previousCount++;
                }else{
                    compressedImage.append( String.valueOf(previousCount) + previousChar + " ");
                    previousChar = c;
                    previousCount = 1;
                }
            }
        }
        compressedImage.append( String.valueOf(previousCount) + previousChar + " ");

        writeFile(compressedImage.toString());
    }
    
    private void writeFile(String line){
        
        int count = 1;
        File file = new File(pathOutputFile + "/compressedImage.txt");
        
        while(file.exists()){
            file = new File(pathOutputFile + "/compressedImage.txt" + count);
            count++;
        }
        this.absolutePathFile = file.getAbsolutePath();
      
           boolean sucess = false;
            try {
                sucess = file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Compressor.class.getName()).log(Level.SEVERE, null, ex);
            }
           if(sucess){
               try {
                   try (FileWriter writer = new FileWriter(file)) {
                       for(char c: line.toCharArray()){
                           writer.append(c);
                       }
                   }
                   
               } catch (IOException ex) {
                   Logger.getLogger(Compressor.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
        System.out.println("File compress with sucess!");
    }

    public String getAbsolutePathFile() {
        return absolutePathFile;
    }
    
    
    
    
}
