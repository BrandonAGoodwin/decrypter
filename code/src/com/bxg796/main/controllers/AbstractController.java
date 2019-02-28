package com.bxg796.main.controllers;

import com.bxg796.main.models.*;

public abstract class AbstractController{

    protected DecrypterModel model;

    public AbstractController(DecrypterModel model){
        this.model = model;

    }
}
