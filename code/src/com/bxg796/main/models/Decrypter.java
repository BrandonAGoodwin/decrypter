package com.bxg796.main.models;

import java.util.HashMap;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.bxg796.files.*;

public class Decrypter{

    // Types of decryption
    public enum DecryptionType{
        TYPE_1, TYPE_2
    }

    // Stores the type of decryption to be applied
    private DecryptionType type;

    private String outputFileName;

    private CipherFile cipherFile;

    private FrequenciesFile frequenciesFile;

    // Stores the known frequencies
    private HashMap<String, Double> frequencies;

    // Stores the frequencies of each character in the cipher file
    private HashMap<String, Double> cipherFrequencies;

    // Stores the equivalent cipher characters and actual characters
    private HashMap<String, String> characterEquivalence;

    public Decrypter(FrequenciesFile freqFile, CipherFile ciphFile, String outputFileName, DecryptionType type){

        this.type = type;

        this.outputFileName = outputFileName;

        frequenciesFile = freqFile;

        cipherFile = ciphFile;

        frequencies = freqFile.getFrequencies();

        cipherFrequencies = ciphFile.getFrequencies();

        characterEquivalence = new HashMap<String, String>();

        decrypt();

    }

    private void decrypt(){

        switch(type){
            case TYPE_1:{

                // Counter for converting each different character (Will usually be 26 as there are 26 letters in the alphabet)
                int counter = 0;
                int counterMax;
                String greatestF;
                String greatestC;
                // Store the current greatest frequency for the cipher frequencies hash map and the known frequencies hash map
                double greatestFValue;
                double greatestCValue;
                // The current value we are looking at
                double currentValueF;
                double currentValueC;

                // The counterMax is equal to the size of the smallest set of frequency values (a file doesn't contain every character)
                if(frequencies.size() < cipherFrequencies.size()){
                    counterMax = frequencies.size();
                }
                else{
                    counterMax = cipherFrequencies.size();
                }

                while(counter < counterMax){

                    // Get the character with the greatest frequency from the known frequencies hashmap
                    greatestF = "";
                    for(String currentKey : frequencies.keySet()){
                        greatestFValue = frequencies.getOrDefault(greatestF, 0.0);
                        currentValueF = frequencies.get(currentKey);

                        if(currentValueF > greatestFValue){
                            greatestF = currentKey;
                        }

                    }

                    // Get the character with the greatest frequency from the cipher frequencies hashmap
                    greatestC = "";
                    for(String currentKey : cipherFrequencies.keySet()){
                        greatestCValue = cipherFrequencies.getOrDefault(greatestC, 0.0);
                        currentValueC = cipherFrequencies.get(currentKey);

                        if(currentValueC > greatestCValue){
                            greatestC = currentKey;
                        }

                    }

                    // Once the characters with the greatest frequency from each hash map are found set the character from each
                    // hash map to be equivalent
                    characterEquivalence.put(greatestF, greatestC);

                    // And remove each character from their respective hash maps
                    frequencies.remove(greatestF);
                    cipherFrequencies.remove(greatestC);

                    counter++;
                }

                // Write the output file
                writeFile();

                break;
            }
            case TYPE_2:{

                // The current value we are looking at
                double currentValueF;
                double currentValueC;

                // The smallest percentage difference found and the key that corresponds to that smallest difference
                double smallestDifference;
                String smallestDifferenceKey;
                // The current difference we are looking at
                double difference;

                for(String currentKeyF : frequencies.keySet()){

                    currentValueF = frequencies.get(currentKeyF);

                    // Reset the values
                    smallestDifferenceKey = "";
                    // Set to 100 as all the differences are percentages and no difference can be greater than 100
                    smallestDifference = 100.0;

                    for(String currentKeyC : cipherFrequencies.keySet()){
                        currentValueC = cipherFrequencies.get(currentKeyC);

                        // Calculate the difference as a absolute value
                        difference = Math.abs(currentValueF - currentValueC);

                        if(difference < smallestDifference){
                            smallestDifference = difference;
                            smallestDifferenceKey = currentKeyC;
                        }

                    }
                    // Add the equivalent frequency character and cipher character to the characterEquivalence HashMap
                    characterEquivalence.put(currentKeyF, smallestDifferenceKey);

                    cipherFrequencies.remove(smallestDifferenceKey);
                }

                // Write the output file
                writeFile();

                break;
            }
            default:{

                break;
            }
        }
    }

    // Using the characterEquivalence hashmap, write the decrypted file "output.txt"
    private void writeFile(){

        // Read in the cipher file and write out the output file
        try(
            BufferedReader bufferedReader = new BufferedReader(new FileReader(cipherFile.getPath()));
            PrintWriter printWriter = new PrintWriter(new FileWriter(outputFileName));
        ){
            String cipherLine;
            String encryptedChar;
            while((cipherLine = bufferedReader.readLine()) != null){
                cipherLine = cipherLine.toUpperCase();
                for(String correctChar : characterEquivalence.keySet()){

                    // Get the equivalent encryptedChar for each different character in the cipher file
                    encryptedChar = characterEquivalence.get(correctChar);
                    cipherLine = cipherLine.replaceAll(encryptedChar, correctChar);
                }

                // Print converted line to the output file
                printWriter.printf("%s" + "%n", cipherLine);

            }

        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
