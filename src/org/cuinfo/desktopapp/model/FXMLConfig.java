/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.desktopapp.model;

import java.util.Objects;



/**
 *
 * 模板配置
 * @author xiaobo
 */
public class FXMLConfig {
    /**
     * 模板名
     */
    private String name;
    /**
     * 资源文件名
     */
    private String resourceName;

    public FXMLConfig(String name) {
        this.name = name;
    }

    public FXMLConfig(String name, String resourceName) {
        this.name = name;
        this.resourceName = resourceName;
    }
    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FXMLConfig other = (FXMLConfig) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    
}
