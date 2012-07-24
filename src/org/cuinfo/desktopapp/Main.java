/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.desktopapp;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.cuinfo.desktopapp.preloader.PreloaderMessageNotification;

/**
 * 主程序入口
 *
 * @author xiaobo
 */
public class Main extends Application {

    BooleanProperty ready = new SimpleBooleanProperty(false);
    
   static SenceController controller;
    static Stage stage;

    public static SenceController getSenceController(){
        return controller;
    }
    
    @Override
    public void start(final Stage primaryStage) {
        stage=primaryStage;
        longStart();
        
        //监听初始化完成
        ready.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(
                    ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                if (Boolean.TRUE.equals(t1)) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                           controller=new SenceController(stage);
                           stage.setTitle("桌面应用");
                           stage.show();
                           
                           stage.setFullScreen(true);
                           Platform.runLater(new Runnable() {

                                @Override
                                public void run() {
                                    controller.goToDefault();
                                }
                           });
                           
                         
                        }
                    });
                }
            }
        });



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

    private void longStart() {
        Task task = new Task<Void>() {
            @Override
            protected Void call(){

                try {
                    notifyPreloader(new PreloaderMessageNotification("开始初始化..."));
                    notifyPreloader(new Preloader.ProgressNotification(0f));
                    notifyPreloader(new PreloaderMessageNotification("连接远程调用.."));
                    Configs.connectRemoteService();
                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    //  notifyPreloader(new PreloaderMessageNotification("加载远程失败.."));
                    notifyPreloader(new Preloader.ErrorNotification(null, ex.getMessage(), ex));
                    return null;
                }

                try {
                    notifyPreloader(new PreloaderMessageNotification("连接远程成功..."));
                    notifyPreloader(new Preloader.ProgressNotification(0.25f));
                    notifyPreloader(new PreloaderMessageNotification("加载FXML页面.."));
                    Configs.loadFXMLPage();
                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    notifyPreloader(new Preloader.ErrorNotification(null,ex.getMessage(), ex));
                    return null;
                }

                try {
                    notifyPreloader(new PreloaderMessageNotification("加载FXML页面成功..."));
                    notifyPreloader(new Preloader.ProgressNotification(0.5f));
                    notifyPreloader(new PreloaderMessageNotification("加载国际化资源.."));
                    Configs.loadI18N();
                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    notifyPreloader(new Preloader.ErrorNotification(null, ex.getMessage(), ex));
                    return null;
                }

                try {
                    notifyPreloader(new PreloaderMessageNotification("加载国际化资源成功..."));
                    notifyPreloader(new Preloader.ProgressNotification(0.75f));
                    notifyPreloader(new PreloaderMessageNotification("加载系统图片.."));
                    Configs.loadImage();
                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    notifyPreloader(new Preloader.ErrorNotification(null, ex.getMessage(), ex));
                    return null;
                }

                notifyPreloader(new Preloader.ProgressNotification(0.99f));
                notifyPreloader(new PreloaderMessageNotification("准备启动程序...."));


                

              
                
//                notifyPreloader(new PreloaderMessageNotification("初始化框架...."));
//                try {
//                   
//                } catch (IOException ex) {
//                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//                    notifyPreloader(new Preloader.ErrorNotification(null, ex.getMessage(), ex));
//                    return null;
//                }
                
                notifyPreloader(new Preloader.ProgressNotification(1));
                ready.setValue(Boolean.TRUE);

                notifyPreloader(new Preloader.StateChangeNotification(
                        Preloader.StateChangeNotification.Type.BEFORE_START));

                return null;
            }
        };

        new Thread(task).start();
    }
}
