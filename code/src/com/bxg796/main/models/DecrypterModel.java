package com.bxg796.main.models;

import com.bxg796.files.*;

public class DecrypterModel extends AbstractModel{

    // Default type of decryption
    private static final Decrypter.DecryptionType DEFAULT_TYPE = Decrypter.DecryptionType.TYPE_1;

    private static final String OUTPUT_FILE_NAME = "output.txt";

    // Files
    private FrequenciesFile frequenciesFile;
    private CipherFile cipherFile;
    private DecryptedFile decryptedFile;

    private Decrypter decrypter;

    // Stores the current decryption type
    private Decrypter.DecryptionType type;

    public DecrypterModel(){
        super();

        /*
        frequenciesFile = new FrequenciesFile("frequencies.txt");
        cipherFile = new CipherFile("cipher3.txt");
        */

        //SUPER IMPORTANT MUST CHANGE
        frequenciesFile = null;
        cipherFile = null;

        decryptedFile = null;

        decrypter = null;

        type = DEFAULT_TYPE;
    }

    // Load the frequencies file using its path
    public void loadFrequenciesFile(String path){
        frequenciesFile = new FrequenciesFile(path);
    }

    // Load the cipher file using its path
    public void loadCipherFile(String path){
        cipherFile = new CipherFile(path);
    }

    // Set the current decryption type to the specifed type
    public void setDecryptionType(Decrypter.DecryptionType type){
        this.type = type;
    }

    public void decrypt(){
        // Create a decrypter that will decrypt using the two loaded files and the specified decryption type
        decrypter = new Decrypter(frequenciesFile, cipherFile, OUTPUT_FILE_NAME, type);

        // Create a decrypted file for the new decrypted file
        decryptedFile = new DecryptedFile(OUTPUT_FILE_NAME);
    }

    // Check frequencies file and cipher file have been loaded and read
    public boolean checkFilesLoaded(){
        if(checkFrequenciesFileLoaded() && checkCipherFileLoaded()){
            return true;
        }
        else{
            return false;
        }
    }

    // Check that the frequencies file has been loaded and read
    public boolean checkFrequenciesFileLoaded(){
        if(frequenciesFile != null && frequenciesFile.checkFileRead()){
            return true;
        }
        else{
            return false;
        }
    }
    // check that the cipher file has been loaded and read
    public boolean checkCipherFileLoaded(){
        if(cipherFile != null && cipherFile.checkFileRead()){
            return true;
        }
        else{
            return false;
        }
    }

}
