package com.bxg796.main.views;

import com.bxg796.main.controllers.*;

public class DecrypterView extends AbstractView{

    private DecrypterFrame frame;

    public DecrypterView(DecrypterController controller){
        super(controller);

        frame = new DecrypterFrame(this);

    }

    // Methods that are called by the DecrypterFrame to tell the controller what is being done

    public void loadFrequencies(){
        if(controller.loadFrequencies(getFilePath()) == true){
            frame.addMessage("Frequency File Loaded");
        }
        else{
            frame.addMessage("Error occured when loading the frequencies file");
        }

    }

    public void loadCipher(){
        if(controller.loadCipher(getFilePath()) == true){
            frame.addMessage("Cipher File Loaded");
        }
        else{
            frame.addMessage("Error occured when loading the cipher file");
        }
    }

    public void setDecryptionType1(){
        controller.setDecryptionType1();
        frame.addMessage("Decryption Type: Type 1");
    }

    public void setDecryptionType2(){
        controller.setDecryptionType2();
        frame.addMessage("Decryption Type: Type 2");
    }

    public void decrypt(){
        if(controller.decrypt() == true){
            frame.addMessage("Cipher Decrypted");
        }
        else{
            frame.addMessage("Decryption can not be performed as cipher files and frequencies files have not both been loaded");
        }
    }

}
