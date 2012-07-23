/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.desktopapp.exception;

/**
 *
 * @author Administrator
 */
public class SystemException extends Exception{
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    
    
    @Override
    public String getMessage() {
        return message;
    }
    
}
