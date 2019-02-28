package com.bxg796.main.views;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;

import com.bxg796.main.controllers.*;

public abstract class AbstractView{

    DecrypterController controller;

    public AbstractView(DecrypterController controller){
        this.controller = controller;
    }

    // Returns a file path using the JFileChooser
    public String getFilePath(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setDialogTitle("Open File");
        // Only allow txt files
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files (.txt)", "txt", "text"));

        if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        else{
            // If no file is found return null
            return null;
        }
    }

}
