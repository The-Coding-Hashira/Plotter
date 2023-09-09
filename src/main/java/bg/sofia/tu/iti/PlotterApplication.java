package bg.sofia.tu.iti;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PlotterApplication extends Application{
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Plotter");
        primaryStage.setScene(new Scene(FXMLUtil.load("main")));
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
