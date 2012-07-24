/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.desktopapp.fxmls.advancedviewer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import org.cuinfo.desktopapp.Configs;
import org.cuinfo.desktopapp.Main;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class AdvancedViwerController implements Initializable {

    @FXML
    AnchorPane me;
    @FXML
    ImageView close;
    @FXML
    StackPane mediaGroup;
    MediaView mediaView;
    MediaPlayer mediaPlayer;
    final String resourceBasePath = "resources/oracle/";
    final String protocol = "file:///";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        String u =  UrlUtil.getClassPath(AdvancedViwerController.class) + "/" + resourceBasePath + "oracle.flv";
//        System.out.println(u);
//        u = u.replace("\\", "/");
//        u = u.replace(" ", "%20");
//        getClass().getResource("/resources/oracle/oracle.flv");
//        
//        u = protocol +u;
//        
//        
//        System.out.println(u);
//         System.out.println(u);
        // Media media=new Media(protocol + UrlUtil.getClassPath(AdvancedViwerController.class) + "/" + resourceBasePath + "oracle.flv");
        Media media = new Media(getClass().getResource("/resources/oracle/oracle.flv").toString());

         mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
      mediaPlayer.setOnEndOfMedia(new Runnable() {

            @Override
            public void run() {
              mediaPlayer.stop();
            Main.getSenceController().goToPage(Configs.FXML_NAME_DEFAULT_VIEW);
            }
        });
        
        mediaView = new MediaView();
        mediaView.setMediaPlayer(mediaPlayer);


        mediaView.setPreserveRatio(false);
        mediaView.setFitWidth(800);
        mediaView.setFitHeight(450);
        
        mediaGroup.getChildren().add(mediaView);
        
        
    }

    public void close(MouseEvent e) {
        if (e.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            mediaPlayer.stop();
            Main.getSenceController().goToPage(Configs.FXML_NAME_DEFAULT_VIEW);
        } else if (e.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
            close.setCursor(Cursor.HAND);
            close.setScaleX(1.1);
            close.setScaleY(1.1);
        } else if (e.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
            close.setCursor(Cursor.DEFAULT);
            close.setScaleX(1);
            close.setScaleY(1);
        }
    }
//    public void pageClick(MouseEvent event) {
//        Main.getSenceController().goToPage(Configs.FXML_NAME_DEFAULT_VIEW);
//    }
}
