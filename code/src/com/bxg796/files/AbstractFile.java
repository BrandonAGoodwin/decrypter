package com.bxg796.files;

public abstract class AbstractFile{

    private static boolean DEFAULT_FILE_READ_VALUE = false;

    private String path;

    private boolean fileRead;

    public AbstractFile(String path){
        this.path = path;
        fileRead = DEFAULT_FILE_READ_VALUE;
    }

    // Returns the file path
    public String getPath(){
        return path;
    }

    // Sets the file to read only works one way as a file cannot be "unread"
    public void setFileRead(){ fileRead = true; }
    // returns whether or not the file has been read
    public boolean checkFileRead(){ return fileRead; }

    // Reads the file in whatever way the implementing class specifies
    protected abstract void read();

}
