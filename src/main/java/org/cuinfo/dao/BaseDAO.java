/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.dao;

import java.util.List;
import org.cuinfo.pojo.BasePOJO;

/**
 *数据库表基本几口
 * @author Administrator
 */
public interface BaseDAO<T extends BasePOJO> {
    /**
     * 创建一行
     * @param t 
     */
    void save(T entity);
    /**
     * 更新一行
     * @param t 
     */
    void update(T entity);
    /**
     * 删除一行
     */
    void delete(T entity);
    
    
    /*
     * 获得所有的
     */
    List<T> viewAll();
    /*
     * 通过ID获得一行
     */
    T view(int id);
}
