/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.desktopapp;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Administrator
 */
public class Test extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
     
        
       AnchorPane p=(AnchorPane) FXMLLoader.load(getClass().getResource("/org/desktopapp/fxmls/advancedviewer/AdvancedViwer.fxml"));
        
       
       
       
         double fontSize = 450;
        final Rotate rectRotate1 = new Rotate();

        rectRotate1.setPivotX(0);
        rectRotate1.setPivotY(fontSize/2);
        rectRotate1.setPivotZ(0);

        rectRotate1.setAxis(Rotate.X_AXIS);
        Timeline timeline = new Timeline(new KeyFrame(new Duration(500), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                rectRotate1.setAngle(rectRotate1.getAngle() + 10);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
         //rectRotate1.setAngle(90);
        timeline.play();

//        Text text = new Text("世界，您好!站住，請不要動。");
//
//        text.setFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[]{
//                new Stop(0, Color.AQUA)
//                , new Stop(0.25, Color.CADETBLUE)
//                , new Stop(0.75, Color.BURLYWOOD)
//                , new Stop(1, Color.BROWN)
//                }));
//        text.setX(0);
//        text.setY(fontSize);
//        text.setFont(new Font(fontSize));
//
//        text.getTransforms().add(rectRotate1);
        
        
        
     

        Group root = new Group();
        root.getChildren().addAll(p);
   root.getTransforms().add(rectRotate1);
   

   AnchorPane anchorPane=new AnchorPane();
   
   anchorPane.getChildren().add(root);
   
        StackPane pane = new StackPane();
        pane.getChildren().add(anchorPane);
        Scene scene = new Scene(pane, 300, 250);
        PerspectiveCamera camera = new PerspectiveCamera();

        scene.setCamera(camera);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
