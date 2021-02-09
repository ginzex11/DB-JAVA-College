package AvivDoron_AlexGinzburg;

import AvivDoron_AlexGinzburg.controller.AcademyController;
import AvivDoron_AlexGinzburg.model.Academy;
import AvivDoron_AlexGinzburg.model.Model;
import AvivDoron_AlexGinzburg.view.AbstractAcademyView;
import AvivDoron_AlexGinzburg.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	
    public static void main(String[] args) {
        	launch(args);   	

    	
        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
        Model model = new Model(),loadModel = new Model();;
        
        if (loadModel.loadFile())
        {
            AbstractAcademyView theView = new View(primaryStage);
            AcademyController controller1 = new AcademyController(loadModel, theView);
        }
        else
        {
            AbstractAcademyView theView = new View(primaryStage);
            AcademyController controller1 = new AcademyController(model, theView);
        }

        

    }

}