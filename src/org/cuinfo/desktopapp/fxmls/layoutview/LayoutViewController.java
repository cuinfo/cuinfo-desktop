/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.desktopapp.fxmls.layoutview;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import org.cuinfo.desktopapp.Configs;
import org.cuinfo.desktopapp.model.AnimationType;

/**
 * FXML Controller class 布局控制器
 *
 * @author xiaobo
 */
public class LayoutViewController implements Initializable {

    @FXML
    StackPane me;
    /**
     * 页面背景
     */
    @FXML
    ImageView backgroundImage;
    /**
     * 页面容器
     */
    @FXML
    AnchorPane pageContainer;
    /**
     * *
     * 页面背景控制器
     */
    private BooleanProperty backgroundImageSwitchProperty = new SimpleBooleanProperty(true);
    private Timeline backgroundImageTimeline;
    private final int backgroundImageSwitchSecond = 10;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //根据界面大小自动变换
        ChangeListener sizeChangeListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                backgroundImage.setFitWidth(LayoutViewController.this.me.widthProperty().doubleValue());
                backgroundImage.setFitHeight(LayoutViewController.this.me.heightProperty().doubleValue());
            }
        };
        me.widthProperty().addListener(sizeChangeListener);
        me.heightProperty().addListener(sizeChangeListener);


        //设置按时改变后台背景
        backgroundImageTimeline = new Timeline();
        backgroundImageTimeline.setCycleCount(Timeline.INDEFINITE);
        backgroundImageTimeline.getKeyFrames().add(new KeyFrame(new Duration(backgroundImageSwitchSecond * 1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {

                if (!backgroundImageSwitchProperty.getValue()) {
                    return;
                }

                changeBGImage();
            }
        }));
        backgroundImageTimeline.play();


    }

    /**
     * 切换页面,随即切换动画
     *
     * @param page 页面
     *
     */
    public void changePage(Parent page) {
        Random random = new Random();
        int select=random.nextInt();
       if(select%3==0){
            changePage(page, ANIMATION_ZOOM_TYPES.get(random.nextInt(ANIMATION_ZOOM_TYPES.size())));
       }else if(select%3==1){
            changePage(page, ANIMATION_RORATE_TYPES.get(random.nextInt(ANIMATION_RORATE_TYPES.size())));
       }else if(select%3==2){
       changePage(page, ANIMATION_TRANSLATE_TYPES.get(random.nextInt(ANIMATION_TRANSLATE_TYPES.size())));
       }
    }

    /**
     * 切换页面
     *
     * @param page 页面
     * @param aniType 动画类型
     */
    public void changePage(Parent page, AnimationType aniType) {
      
        if(ANIMATION_RORATE_TYPES.contains(aniType)){
         pageRorateAinmation(pageContainer, page, aniType);
        }else  if(ANIMATION_ZOOM_TYPES.contains(aniType)){
         pageZoomAinmation(pageContainer, page, aniType);
        }else   if(ANIMATION_TRANSLATE_TYPES.contains(aniType)){
            pageTranslateAinmation(pageContainer, page, aniType);
        }
       
    }
    /*
     * 随即切换背景图片
     */

    private void changeBGImage() {
        Random random = new Random();
        backgroundImage.setImage(Configs.getBackgroundImages().get(random.nextInt(Configs.getBackgroundImages().size())));

    }
    /**
     * ****************************页面动画配置*******************************************
     */
    public final static ObservableList<AnimationType> ANIMATION_RORATE_TYPES = FXCollections.observableArrayList(
            new AnimationType("rorateYAxisCounterclockwise")//Y正轴逆时针
            , new AnimationType("rorateYAxisCounterclock")//Y正轴顺时针
            , new AnimationType("rorateXAxisCounterclock")//X正轴顺时针
            , new AnimationType("rorateXAxisCounterclockwise")//X正轴逆时针
          
            );
    public final static int ANIMATION_TYPE_RORATE_Y_AXIS_CC_WISE = 0;//Y正轴逆时针
    public final static int ANIMATION_TYPE_RORATE_Y_AXIS_CC = 1;//Y正轴顺时针
    public final static int ANIMATION_TYPE_RORATE_X_AXIS_CC = 2;//X正轴顺时针
    public final static int ANIMATION_TYPE_RORATE_X_AXIS_CC_WISE = 3;//X正轴逆时针
    
    public final static ObservableList<AnimationType> ANIMATION_ZOOM_TYPES = FXCollections.observableArrayList(
             new AnimationType("zoomLeftTop")//支点位于左上
            , new AnimationType("zoomLeftBottom")//支点位于左下
            , new AnimationType("zoomRightTop")//支点位于右上
            , new AnimationType("zoomRightBottom")//支点位于右下
            , new AnimationType("zoomCenter")//支点位于中心
            );
    public final static int ANIMATION_TYPE_ZOOM_LEFT_TOP = 0;//支点位于左上
    public final static int ANIMATION_TYPE_ZOOM_LEFT_BOTTOM = 1;//支点位于左下
    public final static int ANIMATION_TYPE_ZOOM_RIGHT_TOP = 2;//支点位于右上
    public final static int ANIMATION_TYPE_ZOOM_RIGHT_BOTTOM = 3;//支点位于右下
    public final static int ANIMATION_TYPE_ZOOM_CENTER = 4;//支点位于中心

     public final static ObservableList<AnimationType> ANIMATION_TRANSLATE_TYPES = FXCollections.observableArrayList(
             new AnimationType("translateTopLeftToBottomRight")//从左上到右下
            , new AnimationType("translateTopRightToBottomLeft")//从右上到左下
            , new AnimationType("translateBottomRightToTopLeft")//从右下到左上
            , new AnimationType("translateBottomLeftToTopRight")//从左下到右上
            , new AnimationType("translateTopToBottom")//从上到下
              , new AnimationType("translateBottomToTop")//从下到上
            , new AnimationType("translateLeftToRight")//从左到右
            , new AnimationType("translateRightToLeft")//从右到左
            );
    public final static int ANIMATION_TYPE_TRANSLATE_TOP_LEFT_TO_BOTTOM_RIGHT = 0;//左上到右下
    public final static int ANIMATION_TYPE_TRANSLATE_TOP_RIGHT_TO_BOTTOM_LEFT = 1;//从右上到左下
    public final static int ANIMATION_TYPE_TRANSLATE_BOTTOM_RIGHT_TO_TOP_LEFT = 2;//从右下到左上
    public final static int ANIMATION_TYPE_TRANSLATE_BOTTOM_LEFT_TO_TOP_RIGHT = 3;///从左下到右上
    public final static int ANIMATION_TYPE_TRANSLATE_TOP_TO_BOTTOM= 4;//从上到下
    public final static int ANIMATION_TYPE_TRANSLATE_BOTTOM_TO_TOP = 5;//从下到上
    public final static int ANIMATION_TYPE_TRANSLATE_LEFT_TO_RIGHT = 6;//从左到右
    public final static int ANIMATION_TYPE_TRANSLATE_RIGHT_TO_LEFT = 7;//从右到左
    
    
     private void pageTranslateAinmation(final AnchorPane parentPane, Parent page, final AnimationType aniType) {
       if (parentPane == null || page == null) {
            return;
        }
        final Group group = new Group(page);
        double width = parentPane.getWidth();
        double height = parentPane.getHeight();

        final double width2 = page.prefWidth(width);
        final double height2 = page.prefHeight(height);
        //首先适应大小
        final Scale s = new Scale(width / width2, height / height2);

        group.getTransforms().add(s);

        ChangeListener sizeChangeListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                s.setX(parentPane.widthProperty().getValue() / width2);
                s.setY(parentPane.heightProperty().getValue() / height2);
            }
        };

        parentPane.widthProperty().addListener(sizeChangeListener);
        parentPane.heightProperty().addListener(sizeChangeListener);



        Node oldPage = null;
        if (!parentPane.getChildren().isEmpty()) {
            oldPage = parentPane.getChildren().get(0);
        }
        
        final int circleCount = 20;//20*offsetX=width2;20*offsetY=height2;
        double offsetX=width2/circleCount;
        double offsetY=height2/circleCount;
        
        double initPointX=0;
        double initPointY=0;
        
        if (aniType.equals(ANIMATION_TRANSLATE_TYPES.get(ANIMATION_TYPE_TRANSLATE_TOP_LEFT_TO_BOTTOM_RIGHT))) {
            initPointX=-width2;
            initPointY=-height2;
            
        } else if (aniType.equals(ANIMATION_TRANSLATE_TYPES.get(ANIMATION_TYPE_TRANSLATE_TOP_RIGHT_TO_BOTTOM_LEFT))) {
            initPointX=width2;
            initPointY=-height2;
            offsetX=-offsetX;
        }  else if (aniType.equals(ANIMATION_TRANSLATE_TYPES.get(ANIMATION_TYPE_TRANSLATE_BOTTOM_RIGHT_TO_TOP_LEFT))) {
            initPointX=width2;
            initPointY=height2;
             offsetX=-offsetX;
             offsetY=-offsetY;
        }  else if (aniType.equals(ANIMATION_TRANSLATE_TYPES.get(ANIMATION_TYPE_TRANSLATE_BOTTOM_LEFT_TO_TOP_RIGHT))) {
            initPointX=-width2;
            initPointY=height2;
             offsetY=-offsetY;
        } else if (aniType.equals(ANIMATION_TRANSLATE_TYPES.get(ANIMATION_TYPE_TRANSLATE_TOP_TO_BOTTOM))) {
            initPointX=0;
            initPointY=-height2;
            offsetX=0;
         
            
        } else if (aniType.equals(ANIMATION_TRANSLATE_TYPES.get(ANIMATION_TYPE_TRANSLATE_BOTTOM_TO_TOP))) {
            initPointX=0;
            initPointY=height2;
            offsetX=0;
            offsetY=-offsetY;
             
        } else if (aniType.equals(ANIMATION_TRANSLATE_TYPES.get(ANIMATION_TYPE_TRANSLATE_LEFT_TO_RIGHT))) {
            initPointX=-width2;
            initPointY=0;
             offsetY=0;
             
        } else if (aniType.equals(ANIMATION_TRANSLATE_TYPES.get(ANIMATION_TYPE_TRANSLATE_RIGHT_TO_LEFT))) {
            initPointX=width2;
            initPointY=0;
             offsetY=0;
             offsetX=-offsetX;;
        } 
        
        
        
        
        
        
        final double offsetXTemp=offsetX;
        final double offsetYTemp=offsetY;
        
        final Translate translateOld=new Translate();
        final Translate translateNew=new Translate();
        translateNew.setX(initPointX);
         translateNew.setY(initPointY);
        
        Timeline timeline1 = new Timeline(new KeyFrame(new Duration(30), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               translateOld.setX(translateOld.getX()+offsetXTemp);
               translateOld.setY(translateOld.getY()+offsetYTemp);
            }
        }));
        timeline1.setCycleCount(circleCount);
        timeline1.play();
        Timeline timeline2 = new Timeline(new KeyFrame(new Duration(30), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               translateNew.setX(translateNew.getX()+offsetXTemp);
               translateNew.setY(translateNew.getY()+offsetYTemp);
            }
        }));
        timeline2.setDelay(new Duration(30));
        timeline2.setCycleCount(circleCount);
        timeline2.play();
        
        
          if (oldPage != null) {
               final Node oldPageTemp = oldPage;
             
            oldPage.getTransforms().add(translateOld);
            group.getTransforms().add(translateNew);
            parentPane.getChildren().add(group);
            timeline2.setOnFinished(new EventHandler<ActionEvent>() {

                  @Override
                  public void handle(ActionEvent event) {
                      
                     parentPane.getChildren().remove(oldPageTemp);
                  }
              });
           

        } else {
            //初始化的时候，无动画
            parentPane.getChildren().add(group);
        }
        
        
     }
    
    /**
     * 比例动画，支持5种：支点位于左上、支点位于左下、支点位于右上、支点位于右下、支点位于中心
     *
     * @param parentPane 容器
     * @param page 页面
     * @param aniType 动画类型
     */
    private void pageZoomAinmation(final AnchorPane parentPane, Parent page, final AnimationType aniType) {
        if (parentPane == null || page == null) {
            return;
        }
        final Group group = new Group(page);
        double width = parentPane.getWidth();
        double height = parentPane.getHeight();

        final double width2 = page.prefWidth(width);
        final double height2 = page.prefHeight(height);
        //首先适应大小
        final Scale s = new Scale(width / width2, height / height2);

        group.getTransforms().add(s);

        ChangeListener sizeChangeListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                s.setX(parentPane.widthProperty().getValue() / width2);
                s.setY(parentPane.heightProperty().getValue() / height2);
            }
        };

        parentPane.widthProperty().addListener(sizeChangeListener);
        parentPane.heightProperty().addListener(sizeChangeListener);



        Node oldPage = null;
        if (!parentPane.getChildren().isEmpty()) {
            oldPage = parentPane.getChildren().get(0);
        }


        final double scaleOffset = 0.05;
        final int circleCount = 20;//20*scaleOffset=1;
        double sPivotX = 0;
        double sPivotY = 0;
        double sPivotZ = 0;
        if (aniType.equals(ANIMATION_ZOOM_TYPES.get(ANIMATION_TYPE_ZOOM_CENTER))) {
            sPivotX = width2 / 2;
            sPivotY = height2 / 2;
        } else if (aniType.equals(ANIMATION_ZOOM_TYPES.get(ANIMATION_TYPE_ZOOM_LEFT_TOP))) {
            sPivotX = 0;
            sPivotY = 0;
        } else if (aniType.equals(ANIMATION_ZOOM_TYPES.get(ANIMATION_TYPE_ZOOM_LEFT_BOTTOM))) {
            sPivotX = 0;
            sPivotY = height2;
        } else if (aniType.equals(ANIMATION_ZOOM_TYPES.get(ANIMATION_TYPE_ZOOM_RIGHT_TOP))) {
            sPivotX = width2;
            sPivotY = 0;
        } else if (aniType.equals(ANIMATION_ZOOM_TYPES.get(ANIMATION_TYPE_ZOOM_LEFT_TOP))) {
            sPivotX = width2;
            sPivotY = height2;
        }


        final Scale zoom = new Scale();
        zoom.setPivotX(sPivotX);
        zoom.setPivotY(sPivotY);
        zoom.setPivotZ(sPivotZ);
        //第一段动画

        Timeline timeline1 = new Timeline(new KeyFrame(new Duration(30), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                zoom.setX(zoom.getX() - scaleOffset);
                zoom.setY(zoom.getY() - scaleOffset);
            }
        }));
        timeline1.setCycleCount(circleCount);
        timeline1.play();




        if (oldPage != null) {
            oldPage.getTransforms().add(zoom);
            timeline1.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    parentPane.getChildren().clear();


                    group.getTransforms().add(zoom);
                    parentPane.getChildren().add(group);

                    Timeline timeline2 = new Timeline(new KeyFrame(new Duration(30), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            zoom.setX(zoom.getX() + scaleOffset);
                            zoom.setY(zoom.getY() + scaleOffset);
                            //System.out.println("2:"+rotate2.getAngle());
                        }
                    }));
                    timeline2.setCycleCount(circleCount);
                    timeline2.play();
                }
            });

        } else {
            //初始化的时候，无动画
            parentPane.getChildren().add(group);
        }
    }

    /**
     * 页面切换旋转动画，支持四种动画：Y正轴逆时针、Y正轴顺时针、X正轴顺时针、X正轴逆时针
     */
    private void pageRorateAinmation(final AnchorPane parentPane, Parent page, final AnimationType aniType) {
        if (parentPane == null || page == null) {
            return;
        }
        final Group group = new Group(page);
        double width = parentPane.getWidth();
        double height = parentPane.getHeight();

        final double width2 = page.prefWidth(width);
        final double height2 = page.prefHeight(height);
        //首先适应大小
        final Scale s = new Scale(width / width2, height / height2);

        group.getTransforms().add(s);

        ChangeListener sizeChangeListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                s.setX(parentPane.widthProperty().getValue() / width2);
                s.setY(parentPane.heightProperty().getValue() / height2);
            }
        };

        parentPane.widthProperty().addListener(sizeChangeListener);
        parentPane.heightProperty().addListener(sizeChangeListener);



        Node oldPage = null;
        if (!parentPane.getChildren().isEmpty()) {
            oldPage = parentPane.getChildren().get(0);
        }

        final Rotate rotate2 = new Rotate();


        final double angleOffset = 5;
        final int circleCount = 18;//18*5=90;
        double rAngleOffset = 0;
        double rPivotX = 0;
        double rPivotY = 0;
        double rPivotZ = 0;
        Point3D rAxis = null;

        if (aniType.equals(ANIMATION_RORATE_TYPES.get(ANIMATION_TYPE_RORATE_Y_AXIS_CC_WISE))) {
            rAngleOffset = -angleOffset;
            rPivotX = width2 / 2;
            rPivotY = 0;
            rPivotZ = 0;
            rAxis = Rotate.Y_AXIS;
        } else if (aniType.equals(ANIMATION_RORATE_TYPES.get(ANIMATION_TYPE_RORATE_Y_AXIS_CC))) {
            rAngleOffset = angleOffset;
            rPivotX = width2 / 2;
            rPivotY = 0;
            rPivotZ = 0;
            rAxis = Rotate.Y_AXIS;
        } else if (aniType.equals(ANIMATION_RORATE_TYPES.get(ANIMATION_TYPE_RORATE_X_AXIS_CC))) {
            rAngleOffset = -angleOffset;
            rPivotX = 0;
            rPivotY = height2 / 2;
            rPivotZ = 0;
            rAxis = Rotate.X_AXIS;
        } else if (aniType.equals(ANIMATION_RORATE_TYPES.get(ANIMATION_TYPE_RORATE_X_AXIS_CC_WISE))) {
            rAngleOffset = angleOffset;
            rPivotX = 0;
            rPivotY = height2 / 2;
            rPivotZ = 0;
            rAxis = Rotate.X_AXIS;
        }




        rotate2.setPivotX(rPivotX);
        rotate2.setPivotY(rPivotY);
        rotate2.setPivotZ(rPivotZ);
        rotate2.setAxis(rAxis);

        //第一段动画
        final double rAngleOffsetTemp = rAngleOffset;
        Timeline timeline2 = new Timeline(new KeyFrame(new Duration(30), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                rotate2.setAngle(rotate2.getAngle() + rAngleOffsetTemp);
                // System.out.println("1:"+rotate2.getAngle());
            }
        }));
        timeline2.setCycleCount(circleCount);
        timeline2.play();



        //第二段动画
        if (oldPage != null) {
            oldPage.getTransforms().add(rotate2);
            timeline2.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    parentPane.getChildren().clear();
                    rotate2.setAngle(-rotate2.getAngle());

                    group.getTransforms().add(rotate2);
                    parentPane.getChildren().add(group);

                    Timeline timeline = new Timeline(new KeyFrame(new Duration(30), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            rotate2.setAngle(rotate2.getAngle() + rAngleOffsetTemp);
                            //System.out.println("2:"+rotate2.getAngle());
                        }
                    }));
                    timeline.setCycleCount(circleCount);
                    timeline.play();
                }
            });


        } else {
            //初始化的时候，无动画
            parentPane.getChildren().add(group);
        }
    }
}
