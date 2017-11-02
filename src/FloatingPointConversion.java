import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Create the GUI for Floating Point Conversion.
 * 
 * @author Hoang, Minh
 * @version 31/10/2017
 */
public class FloatingPointConversion extends Application {

    /*
     * Starts the GUI.
     * 
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        final int appWidth = 600;
        final int appHeight = 200;
        Scene scene = new Scene(new FloatingPointPane(), appWidth, appHeight);
        primaryStage.setTitle("Floating Point Conversion");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Drives the program.
     * 
     * @param args launch.
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        launch(args);

    }

}
