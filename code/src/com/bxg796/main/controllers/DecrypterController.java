package com.bxg796.main.controllers;

import com.bxg796.main.models.*;

public class DecrypterController extends AbstractController{

    public DecrypterController(DecrypterModel model){
        super(model);
    }

    public boolean checkFilesLoaded(){
        return model.checkFilesLoaded();
    }

    // These methods return a boolean type to let the view know if the files loaded in properly by returning
    // true if they did load in properly, false otherwise
    public boolean loadFrequencies(String path){
        model.loadFrequenciesFile(path);
        if(model.checkFrequenciesFileLoaded()){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean loadCipher(String path){
        model.loadCipherFile(path);
        if(model.checkCipherFileLoaded()){
            return true;
        }
        else{
            return false;
        }
    }

    public void setDecryptionType1(){
        model.setDecryptionType(Decrypter.DecryptionType.TYPE_1);
    }

    public void setDecryptionType2(){
        model.setDecryptionType(Decrypter.DecryptionType.TYPE_2);
    }

    // Allows decryption if both files are loaded in by returning true or false depending on whether or not decryption is allowed
    public boolean decrypt(){
        // Check whether or not the decryption can occur and if so then initate the decryption
        if(model.checkFilesLoaded() == true){
            model.decrypt();
        }

        return model.checkFilesLoaded();
    }


}
