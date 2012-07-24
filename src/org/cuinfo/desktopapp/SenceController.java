/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.desktopapp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.cuinfo.desktopapp.fxmls.layoutview.LayoutViewController;

/**
 * 场景控制器（控制）
 *
 * @author xiaobo
 */
public class SenceController {

    public final Stage stage;
   
    LayoutViewController layoutViewController;
    /**
     * 会话期，用于记录场景状态
     */
    public Map<String, Object> sessionContext = new HashMap<>();

    public SenceController(Stage stage) {
        this.stage = stage;
        initStage();
    }

    private void initStage() {
        
       
        //设置主框架
        FXMLLoader loader = Configs.createFXMLLoader(Configs.FXML_NAME_LAYOUT_VIEW);
        StackPane pane=null;
        try {
            pane = (StackPane) loader.load();
            layoutViewController=loader.getController();
        } catch (Exception ex) {
            Logger.getLogger(SenceController.class.getName()).log(Level.SEVERE, null, ex);
           pane=new StackPane();
           pane.getChildren().add(new Label("无法加载主框架("+Configs.getFXMLNames().get(Configs.FXML_NAME_LAYOUT_VIEW).getName() +")！"));
           
        }
        
    
        
        //加载场景
        Scene scene = new Scene(pane);
         PerspectiveCamera camera = new PerspectiveCamera();
        scene.setCamera(camera);
        
        
        this.stage.setScene(scene);
        
        this.stage.onCloseRequestProperty().addListener(new ChangeListener<EventHandler<WindowEvent>>(){

            @Override
            public void changed(ObservableValue<? extends EventHandler<WindowEvent>> observable, EventHandler<WindowEvent> oldValue, EventHandler<WindowEvent> newValue) {
                exit();
            }
        });
        
        
    }
     public void goToPage(int index){
           FXMLLoader loader = Configs.createFXMLLoader(index);
        try {
            
           layoutViewController.changePage((Pane)loader.load());
            
           
        } catch (IOException ex) {
            Logger.getLogger(SenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public void goToDefault(){
     goToPage(Configs.FXML_NAME_DEFAULT_VIEW);
      
   }
   public void exit(){
        Platform.exit();
   }
    
}
