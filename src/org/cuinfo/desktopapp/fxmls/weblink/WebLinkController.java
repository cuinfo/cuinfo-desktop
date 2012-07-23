/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.desktopapp.fxmls.weblink;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Polygon;
import javafx.scene.web.WebView;
import org.cuinfo.desktopapp.Configs;
import org.cuinfo.desktopapp.Main;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class WebLinkController implements Initializable {

    @FXML
    WebView webView;
    @FXML
    Polygon polygon;
    @FXML
    ImageView back;
    @FXML
    ImageView forward;
    @FXML
    ImageView refresh;
    @FXML
    ImageView close;
    Stop[] imageBackgroundHoverColor = new Stop[]{
        //new Stop(0, Color.web("#306fc4")), new Stop(1, Color.web("#acbdd0"))
        new Stop(0, Color.web("#4977A3").brighter()),
        new Stop(0.5, Color.web("#B0C6DA").brighter()),
        new Stop(1, Color.web("#9CB6CF").brighter())
    };

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        webView.getEngine().load("http://www.baidu.com/");
        polygon.getPoints().clear();
        polygon.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, imageBackgroundHoverColor));

        polygon.getPoints().addAll(new Double[]{
                    50.0, 0.0, 10.0, 50.0, 790.0, 50.0, 750.0, 0.0
                });

    }

    public void forward(MouseEvent e) {
        if (e.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            webView.getEngine().getHistory().go(1);
        
            forward.setScaleX(1);
            forward.setScaleY(1);
        } else if (e.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
            forward.setCursor(Cursor.HAND);
            forward.setScaleX(1.1);
            forward.setScaleY(1.1);
        } else if (e.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
            forward.setCursor(Cursor.DEFAULT);
            forward.setScaleX(1);
            forward.setScaleY(1);
        }

    }

    public void back(MouseEvent e) {
        if (e.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
           
            webView.getEngine().getHistory().go(-1);
            back.setScaleX(1);
            back.setScaleY(1);
        } else if (e.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
            back.setCursor(Cursor.HAND);
            back.setScaleX(1.1);
            back.setScaleY(1.1);
        } else if (e.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
            back.setCursor(Cursor.DEFAULT);
            back.setScaleX(1);
            back.setScaleY(1);
        }
    }

    public void refresh(MouseEvent e) {
        if (e.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            webView.getEngine().reload();

            refresh.setScaleX(1);
            refresh.setScaleY(1);
        } else if (e.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
            refresh.setCursor(Cursor.HAND);
            refresh.setScaleX(1.1);
            refresh.setScaleY(1.1);
        } else if (e.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
            refresh.setCursor(Cursor.DEFAULT);
            refresh.setScaleX(1);
            refresh.setScaleY(1);
        }
    }

    public void close(MouseEvent e) {
        if (e.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
        Main.getSenceController().goToPage(Configs.FXML_NAME_DEFAULT_VIEW);
         }else if (e.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
            close.setCursor(Cursor.HAND);
            close.setScaleX(1.1);
            close.setScaleY(1.1);
        } else if (e.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
            close.setCursor(Cursor.DEFAULT);
            close.setScaleX(1);
            close.setScaleY(1);
        }
    }
}
