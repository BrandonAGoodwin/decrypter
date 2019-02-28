package com.bxg796.files;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashMap;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class CipherFile extends AbstractFile{

    // Stores the count of each character
    private HashMap<String, Integer> charCount;

    private HashMap<String, Double> charFrequencies;

    public CipherFile(String path){
        super(path);

        charCount = new HashMap<String, Integer>();

        charFrequencies = new HashMap<String, Double>();

        read();
    }

    public HashMap<String, Double> getFrequencies(){
        // return a copy of the cipher frequencies hash map
        HashMap <String, Double> giveFreq = new HashMap<String, Double>(charFrequencies);
        return giveFreq;
    }

    // Maybe make read protected
    // Also check all instances for overiding
    @Override
    protected void read(){

        try(
            BufferedReader bufferedReader = new BufferedReader(new FileReader(getPath()));
        ){
            String cipherLineUnformatted;
            String cipherLine;

            int totalCharacters = 0;
            int lineLength = 0;

            Matcher matcher;
            String currentChar;
            // Check we are only looking at letters (in upper case)
            String onlyUpperCaseLettersFormat = "([A-Z])";
            Pattern pattern = Pattern.compile(onlyUpperCaseLettersFormat);

            // Read in each line of the cipher
            while((cipherLineUnformatted = bufferedReader.readLine()) != null){

                // Set it all to upper case as all cipher characters are upper case and all
                // decrypted characters are lower case
                cipherLine = cipherLineUnformatted.toUpperCase();

                lineLength = cipherLine.length();

                // Get the total number of characters by adding the length of each line
                totalCharacters += lineLength;

                for(int i = 0; i < lineLength; i++){
                    // Get each individual char in a line and add it to the hash map or increment the count value
                    // for the appropriate character in the hash map
                    currentChar = cipherLine.substring(i, i + 1);
                    matcher = pattern.matcher(currentChar);
                    if(matcher.matches()){
                        charCount.put(currentChar, charCount.getOrDefault(currentChar, 0) + 1);

                    }
                }
            }

            int currentValue;
            double charFrequency;
            // Calculate the frequency for each character in the cipher
            for(String currentKey : charCount.keySet()){
                currentValue = charCount.get(currentKey);

                // Calculate the characters frequency as a percentage
                charFrequency = ((double) currentValue / (double) totalCharacters) * 100.0;

                // Add the character and frequency to the hashmap
                charFrequencies.put(currentKey, charFrequency);

            }

            // Set to true only if it successfully reads the whole file
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
