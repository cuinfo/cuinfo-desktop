/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.desktopapp.fxmls.defaultview;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import org.cuinfo.desktopapp.Configs;
import org.cuinfo.desktopapp.Main;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class DefaultViewController implements Initializable {

    @FXML
    FlowPane layout;
   
    ImageIconButtonController appCenter;
    ImageIconButtonController infoBox;
    ImageIconButtonController video;
    ImageIconButtonController net;
    ImageIconButtonController webLink;
    ImageIconButtonController listenMusic;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FXMLLoader loader = Configs.createFXMLLoader(Configs.FXML_NAME_IMAGE_ICON_BUTTON);

            layout.getChildren().add((Pane) loader.load());
            appCenter = loader.getController();
            appCenter.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Main.getSenceController().goToPage(Configs.FXML_NAME_DEFAULT_VIEW);
                }
            });

            loader = Configs.createFXMLLoader(Configs.FXML_NAME_IMAGE_ICON_BUTTON);
            layout.getChildren().add((Pane) loader.load());
            infoBox = loader.getController();
            infoBox.getIconImage().setImage(Configs.getDefaultViewImages().get(Configs.IMAGE_BACKGROUND_DEFAULT_VIEW_INFO_BOX));
            infoBox.getText().setText("信息框");
            infoBox.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Main.getSenceController().goToPage(Configs.FXML_NAME_DEFAULT_VIEW);
                }
            });
            
            loader = Configs.createFXMLLoader(Configs.FXML_NAME_IMAGE_ICON_BUTTON);
            layout.getChildren().add((Pane) loader.load());
            video = loader.getController();
            video.getIconImage().setImage(Configs.getDefaultViewImages().get(Configs.IMAGE_BACKGROUND_DEFAULT_VIDEO));
            video.getText().setText("视屏广告");
            video.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent event) {
                   Main.getSenceController().goToPage(Configs.FXML_NAME_ANVANCED_VIEWER);
                }
            });

            loader = Configs.createFXMLLoader(Configs.FXML_NAME_IMAGE_ICON_BUTTON);
            layout.getChildren().add((Pane) loader.load());
            listenMusic = loader.getController();
            listenMusic.getIconImage().setImage(Configs.getDefaultViewImages().get(Configs.IMAGE_BACKGROUND_DEFAULT_VIEW_LISTEN_MUSIC));
            listenMusic.getText().setText("听音乐");
            listenMusic.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Main.getSenceController().goToPage(Configs.FXML_NAME_DEFAULT_VIEW);
                }
            });

            loader = Configs.createFXMLLoader(Configs.FXML_NAME_IMAGE_ICON_BUTTON);
            layout.getChildren().add((Pane) loader.load());
            net = loader.getController();
            net.getIconImage().setImage(Configs.getDefaultViewImages().get(Configs.IMAGE_BACKGROUND_DEFAULT_VIEW_NET));
            net.getText().setText("网络");
            net.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Main.getSenceController().goToPage(Configs.FXML_NAME_DEFAULT_VIEW);
                }
            });
            

            loader = Configs.createFXMLLoader(Configs.FXML_NAME_IMAGE_ICON_BUTTON);
            layout.getChildren().add((Pane) loader.load());
            webLink = loader.getController();
            webLink.getIconImage().setImage(Configs.getDefaultViewImages().get(Configs.IMAGE_BACKGROUND_DEFAULT_WEB_LINK));
            webLink.getText().setText("百度");
            webLink.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Main.getSenceController().goToPage(Configs.FXML_NAME_IMAGE_WEB_LINK);
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(DefaultViewController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
//    public void pageClick(MouseEvent event) {
//        Main.getSenceController().goToPage(Configs.FXML_NAME_ANVANCED_VIEWER);
//    }
}
