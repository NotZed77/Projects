package resources.P5Hangman;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CustomTools {
    public static JLabel loadImage(String resource){
        try{
            InputStream inputStream = CustomTools.class.getResourceAsStream(resource);
            assert inputStream != null;
            BufferedImage image = ImageIO.read(inputStream);
            return new JLabel(new ImageIcon(image));
        }catch(Exception e){
            System.out.println("Error: "+ e);
        }
        return null;
    }

    public static void updateImage(JLabel imageContainer, String resource){
        BufferedImage image;
        try{
            InputStream inputStream = CustomTools.class.getResourceAsStream(resource);
            assert inputStream != null;
            image = ImageIO.read(inputStream);
                imageContainer.setIcon(new ImageIcon(image));
            }catch(IOException e){
                System.out.println("Error: " + e);
            }
    }

    public static Font createFont(String resource){
        // Get font file path
        String filePath = CustomTools.class.getClassLoader().getResource(resource).getPath();

        // Check for empty spaces in path (bug)
        if(filePath.contains("%20")){
            filePath = filePath.replaceAll("%20"," ");
        }

        // Create font
        try{
            File customFontFile = new File(filePath);
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, customFontFile);
            return customFont;
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
        return null;
    }

    public static String hideWords(String word){
        String hiddenWord = "";
        for (int i =0; i< word.length(); i++){
            if(!(word.charAt(i) == ' ')){
                hiddenWord += "*";
            }else{
                hiddenWord += " ";
            }
        }
        return hiddenWord;
    }
}


