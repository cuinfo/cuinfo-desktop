/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.desktopapp;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import org.cuinfo.desktopapp.exception.SystemException;
import org.cuinfo.desktopapp.model.FXMLConfig;

/**
 *配置管理
 * 
 * @author xiaobo
 */
public class Configs {
    
    
    
    /*=======================start fxml refrences==================================================*/
    /*
     * FXML的基本路径
     */
    private   static String URI_FXML_BASE_DIR="/org/cuinfo/desktopapp/fxmls/";
    private   static String URI_FXML_RESOURCE_BASE_DIR="org.cuinfo.desktopapp.fxmls.";
     /*
      * FXML 模板名；用于加载管理模板
      * 注意FXMLConfig不能重复，也就是FXMLConfig的URI_FXML_BASE_DIR+name属性不能重复
      */
    private final static ObservableList<FXMLConfig> FXML_CONFIGS=FXCollections.observableArrayList(
           new  FXMLConfig("layoutview/LayoutView.fxml","layoutview.LayoutView")
           , new  FXMLConfig("defaultview/DefaultView.fxml")
            , new  FXMLConfig("advancedviewer/AdvancedViwer.fxml")
             , new  FXMLConfig("defaultview/ImageIconButton.fxml")
               , new  FXMLConfig("weblink/WebLink.fxml")
            
            );
     public final static int FXML_NAME_LAYOUT_VIEW=0;//layoutView布局视图
     public final static int FXML_NAME_DEFAULT_VIEW=1;//默认页面
     public final static int FXML_NAME_ANVANCED_VIEWER=2;//先进的查看器
     public final static int FXML_NAME_IMAGE_ICON_BUTTON=3;//ICON按钮
      public final static int FXML_NAME_IMAGE_WEB_LINK=4;//WebLink
     
     public static ObservableList<FXMLConfig> getFXMLNames(){
         return FXML_CONFIGS;
     }
     /*======================end fxml refrences===================================================*/
     
     /**
     * 
     * 背景图片
     */
    public static final String IMAGE_BG_DIR = "/org/cuinfo/desktopapp/fxmls/layoutview/images/";
    private static ObservableList<Image> backgroundImages = FXCollections.<Image>observableArrayList();
    private static final String[] IMAGES_BACKGROUND_NAMES = new String[]{
        "earth.jpg", "sky_straw.jpg", "cloud4.jpg", "cloud20.jpg"
    };
    public static final int IMAGE_BACKGROUND_LAYOUT_VIEW_EARTH = 0;
    public static final int IMAGE_BACKGROUND_LAYOUT_VIEW_SKY_STRAW = 1;
    public static final int IMAGE_BACKGROUND_LAYOUT_VIEW_CLOUND_4 = 2;
    public static final int IMAGE_BACKGROUND_LAYOUT_VIEW_CLOUND_20 = 3;
  

    public static ObservableList<Image> getBackgroundImages() {
        return backgroundImages;
    }
     
      /**
     * 
     * defaultView Ico
     */
    private static final String IMAGE_DEFAULT_VIEW_DIR = "/org/cuinfo/desktopapp/fxmls/defaultview/images/";
    private static ObservableList<Image> defaultViewImages = FXCollections.<Image>observableArrayList();
    private static final String[] IMAGES_DEFAULT_VIEW_NAMES = new String[]{
        "appCenter2_64.png", "listenMusic_64.png", "Infobox64.png", "net_64.png","shipin_64.png","Weblink2_64.png"
    };
    public static final int IMAGE_BACKGROUND_DEFAULT_VIEW_APP_CENTER = 0;
    public static final int IMAGE_BACKGROUND_DEFAULT_VIEW_LISTEN_MUSIC = 1;
    public static final int IMAGE_BACKGROUND_DEFAULT_VIEW_INFO_BOX = 2;
    public static final int IMAGE_BACKGROUND_DEFAULT_VIEW_NET = 3;
   public static final int IMAGE_BACKGROUND_DEFAULT_VIDEO = 4;
    public static final int IMAGE_BACKGROUND_DEFAULT_WEB_LINK = 5;

    public static ObservableList<Image> getDefaultViewImages() {
        return defaultViewImages;
    }
     
    
    public static FXMLLoader createFXMLLoader(int configIndex){
        
        FXMLConfig config=FXML_CONFIGS.get(configIndex);
        return createFXMLLoader(config);
        
    }
    public static FXMLLoader createFXMLLoader(FXMLConfig config){
        if(config==null){
            return null;
        }
         String key=URI_FXML_BASE_DIR+config.getName();
        String resourceName=URI_FXML_RESOURCE_BASE_DIR+config.getResourceName();
        FXMLLoader fxmlLoader=null;
            try{
                if(config.getResourceName()!=null&&!config.getResourceName().isEmpty()){
                fxmlLoader = new FXMLLoader(Configs.class.getResource(key), ResourceBundle.getBundle(resourceName));
                }else{
                  fxmlLoader = new FXMLLoader(Configs.class.getResource(key));
                }
            }catch(MissingResourceException e){
               
            }
        
         return  fxmlLoader;   
    }
    
    
   
    
    
    
    
    
    
    
    
    
    
    /**
     * 加载FXML页面
     * @throws SystemException ,启动界面会处理这个异常。
     */
    public static void loadFXMLPage() throws SystemException{
        //加载FXML资源
      
       
        
        
    }
    /**
     * 加载系统图片
     * @throws SystemException ,启动界面会处理这个异常。
     */
    public static void loadImage() throws SystemException{
        //加载系统图片
     
        //1、加载背景图片
         for (String imageName : IMAGES_BACKGROUND_NAMES) {
             try{
            Image image = new Image(Configs.class.getResourceAsStream(IMAGE_BG_DIR + imageName));
            if (image.isError()) {
                Logger.getLogger(Configs.class.getName()).log(Level.SEVERE, "Background Image {0} not found", imageName);
            }
            backgroundImages.add(image);
             }catch(Exception e){
                 throw createSystemException("加载背景图片("+IMAGE_BG_DIR + imageName+")失败！");
             }
        }
         
          //2、加载defaultView图片
         for (String imageName : IMAGES_DEFAULT_VIEW_NAMES) {
             try{
            Image image = new Image(Configs.class.getResourceAsStream(IMAGE_DEFAULT_VIEW_DIR + imageName));
            if (image.isError()) {
                Logger.getLogger(Configs.class.getName()).log(Level.SEVERE, "defaultView Image {0} not found", imageName);
            }
            defaultViewImages.add(image);
             }catch(Exception e){
                 throw createSystemException("加载defaultView图片("+IMAGE_DEFAULT_VIEW_DIR + imageName+")失败！");
             }
        }
        
    }
    /**
     *加载国际化资源
     * @throws SystemException ,启动界面会处理这个异常。
     */
    public static void loadI18N() throws Exception{
        //加载国际化资源
        // Thread.sleep(1000);
    }
    /**
     * 加载远程调用
     * @throws SystemException ,启动界面会处理这个异常。
     */
    public static void connectRemoteService() throws Exception{
        //加载远程调用
       //  Thread.sleep(1000);
     
    }

    private static SystemException createSystemException(String string) {
     SystemException s=new SystemException();
     s.setMessage(string);
     return s;
        
    }
}
