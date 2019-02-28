package com.bxg796.main;

import com.bxg796.main.models.DecrypterModel;
import com.bxg796.main.controllers.DecrypterController;
import com.bxg796.main.views.DecrypterView;

public class Main{

    public static void main(String[] args){

        // Model Object
        // Processes information based on what the controller tells it to do
        DecrypterModel model = new DecrypterModel();

        // Controller Object
        // Controller takes information about user input from the view and uses it to tell the model to do something
        DecrypterController controller = new DecrypterController(model);

        // View Object
        // Interface and takes in user input
        DecrypterView view = new DecrypterView(controller);

        // Ultimate Efficiency
        // DecrypterView view = new DecrypterView(new DecrypterController(new DecrypterModel()));

    }

}
