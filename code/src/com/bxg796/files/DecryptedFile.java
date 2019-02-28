package com.bxg796.files;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DecryptedFile extends AbstractFile{

    private String text;

    public DecryptedFile(String path){
        super(path);

        text = null;
    }

    // If the text hasn't already been read in, read in the text then return it
    public String getText(){
        if(text != null){
            return text;
        }
        else{
            read();
            return text;
        }
    }

    @Override
    protected void read(){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(getPath()));){
            String readText = "";
            String textLine;
            // Read in each line from the file
            while((textLine = bufferedReader.readLine()) != null){
                readText += textLine + "\n";
            }

            text = readText;

            bufferedReader.close();

        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
