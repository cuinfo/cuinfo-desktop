/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.util;
import java.util.Properties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyResourceConfigurer;

public class SpringPropertiesConfigurer extends PropertyResourceConfigurer{
	 private Properties properties;

	 protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
	            throws BeansException {
	        this.properties = props;
	  }

	  public String get(String key) {
	        return (String) this.properties.get(key);
	  }
}
