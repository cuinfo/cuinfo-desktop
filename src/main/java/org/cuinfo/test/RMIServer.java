/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.test;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 *
 * @author Administrator
 */
public class RMIServer {

    
    static String CONTROLLER_CLOSE="close server";
    static String CONTROLLER_START="start server";
    static  ApplicationContext context=null;
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
           
        
        
        JFrame frame=new JFrame("Spring RMI Test!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        
        final JButton controller=new JButton(CONTROLLER_CLOSE);
        controller.setActionCommand(CONTROLLER_CLOSE);
        
        
        ActionListener controllerListener=new ActionListener() {

            public void actionPerformed(ActionEvent e) {
               if(e.getActionCommand().endsWith(CONTROLLER_CLOSE)){
                   stopServer();
                   controller.setActionCommand(CONTROLLER_START);
                   controller.setText(CONTROLLER_START);
               }else{
                   startServer();
                controller.setActionCommand(CONTROLLER_CLOSE);
                   controller.setText(CONTROLLER_CLOSE);
               }
            }

           
        };
        controller.addActionListener(controllerListener);
        startServer();
        
        frame.setLayout(new FlowLayout());
        frame.add(controller);
        frame.setVisible(true);
    }

    
    
    
    private static void startServer() {
       if(context==null){
       context = new ClassPathXmlApplicationContext(new String[]{
                    "appliaction-context.xml", "dao-context.xml", "service-context.xml", "rmi-context.xml"
       });
       }else{
       ((ClassPathXmlApplicationContext)context).start();
       }
       
     
    }
    private static void stopServer() {
        if(context==null){
        return;
        }
       ClassPathXmlApplicationContext c= (ClassPathXmlApplicationContext)context;
       if( c.isRunning()){
            c.stop();
       }
     
    }
    
}
