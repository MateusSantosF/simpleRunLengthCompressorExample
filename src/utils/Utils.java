/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Random;

/**
 *
 * @author mateus
 */
public class Utils {
    
    private int numberColors;
    private int previousColor;
    private int matrizSize;
    
    public Utils(int numberColors,int matrizSize){
        this.numberColors = numberColors;
        this.matrizSize = matrizSize;
    }
    
    public String createRandomColor(){
        
        Random random = new Random();
        
        int color = random.nextInt(numberColors) + 1;
        
        while(color == previousColor){
            color = random.nextInt(numberColors) + 1;
        }
        
        switch (color) {
            case 1:
                return Color.B.toString();
            case 2:
                return Color.G.toString();
            case 3:
                return Color.P.toString();
            case 4:
                return Color.R.toString();
            case 5:
                return Color.Y.toString();
        }
        
        return Color.B.toString();
    }
    
    public int randomTiledColor(){
        
        return new Random().nextInt(matrizSize)/2;
    }
    
}
