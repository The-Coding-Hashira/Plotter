package bg.sofia.tu.iti;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class FXMLUtil{
    private static final String FXML_RESOURCE = "src/main/resources/view/%s.fxml";

    public static Parent load(String resource){
        try{
            return FXMLLoader.load(toFXMLResourceURL(resource));
        }
        catch(IOException e){
            throw new RuntimeException("Could not load resource: " + resource, e);
        }
    }

    private static URL toFXMLResourceURL(String resource){
        String path = String.format(FXML_RESOURCE, resource);
        try{
            return Paths.get(path)
                        .toUri()
                        .toURL();
        }
        catch(IOException e){
            throw new RuntimeException("Invalid resource URL: " + path, e);
        }
    }

    public static Parent load(String resource, Callback<Class<?>, Object> controllerFactory){
        return load(new FXMLLoader(toFXMLResourceURL(resource), null, null, controllerFactory));
    }

    private static Parent load(FXMLLoader loader){
        try{
            return loader.load();
        }
        catch(IOException e){
            throw new RuntimeException("Could not load resource: " + loader.getLocation(), e);
        }
    }
}
