package com.bxg796.files;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashMap;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class FrequenciesFile extends AbstractFile{

    HashMap <String, Double> frequencies;

    public FrequenciesFile(String path){
        super(path);

        frequencies = new HashMap<String, Double>();

        read();
    }

    public HashMap<String, Double> getFrequencies(){
        if(checkFileRead()){
            // Return a copy of the frequencies hashmap
            HashMap <String, Double> giveFreq = new HashMap<String, Double>(frequencies);
            return giveFreq;
        }
        else{
            return null;
        }
    }

    @Override
    protected void read(){
        // Read in the frequencies and add them to a hash mao
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(getPath()));) {

            String frequencyString;

            String frequencyFileFormat = "([a-z]\\s\\d+\\.\\d+)";
            Pattern pattern = Pattern.compile(frequencyFileFormat);
            Matcher matcher;

            String[] frequency;

            // Read in each line of the frequencies file
            while ((frequencyString = bufferedReader.readLine()) != null) {
                // Match the frequency format to a individual frequency
                matcher = pattern.matcher(frequencyString);
                if(matcher.find()){

                    // Split the string into the character and its frequency
                    frequency = matcher.group(1).split(" ");

                    // Add the string and its corresponding frequency to the frequencies hash map
                    frequencies.put(frequency[0], Double.parseDouble(frequency[1]));
                    
                }
            }

            // Only set to true if it successfully reads the whole file
            setFileRead();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
