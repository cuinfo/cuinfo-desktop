/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.desktopapp.fxmls.defaultview;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class ImageIconButtonController implements Initializable, EventHandler<MouseEvent> {

    @FXML
    StackPane me;
    @FXML
    Rectangle background;
    @FXML
    Label text;
    @FXML
    ImageView iconImage;
    
    
   private  EventHandler<ActionEvent> actionEventHandler;
    Stop[] imageBackgroundBaseColor = new Stop[]{ //new Stop(0, Color.web("#306fc4")), new Stop(1, Color.web("#acbdd0"))
    //                   new Stop(0,Color.web("#4977A3")),
    //    new Stop(0.5, Color.web("#B0C6DA")),
    //    new Stop(1,Color.web("#9CB6CF"))
    };
    Stop[] imageBackgroundHoverColor = new Stop[]{
        //new Stop(0, Color.web("#306fc4")), new Stop(1, Color.web("#acbdd0"))
        new Stop(0, Color.web("#4977A3").brighter()),
        new Stop(0.5, Color.web("#B0C6DA").brighter()),
        new Stop(1, Color.web("#9CB6CF").brighter())
    };
    Stop[] imageBackgroundPressColor = new Stop[]{
        //new Stop(0, Color.web("#306fc4")), new Stop(1, Color.web("#acbdd0"))
        new Stop(0, Color.web("#4977A3").darker()),
        new Stop(0.5, Color.web("#B0C6DA").darker()),
        new Stop(1, Color.web("#9CB6CF").darker())
    };

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        background.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, imageBackgroundBaseColor));

        double arc = 15;
        Rectangle clip = new Rectangle(background.getWidth() - 5, background.getHeight() - 5);
        clip.setArcHeight(arc);
        clip.setArcWidth(arc);

        me.setClip(clip);

    


    }

   
    
    

    public StackPane getMe() {
        return me;
    }

    public Rectangle getBackground() {
        return background;
    }

    public Label getText() {
        return text;
    }

    public ImageView getIconImage() {
        return iconImage;
    }

    public EventHandler<ActionEvent> getOnAction() {
        return actionEventHandler;
    }

    public void setOnAction(EventHandler<ActionEvent> actionEventHandler) {
        this.actionEventHandler = actionEventHandler;
    }

    
    
    
    @Override
    public void handle(MouseEvent event) {
        if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
          //  System.out.println("世界你好");
          
        } else if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
            background.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, imageBackgroundHoverColor));
        } else if (event.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
            background.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, imageBackgroundBaseColor));
        } else if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
            
            
            background.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, imageBackgroundPressColor));
              if(this.actionEventHandler!=null)
            this.actionEventHandler.handle(new ActionEvent());
        } else if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
            background.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, imageBackgroundBaseColor));
        }
    }
}
