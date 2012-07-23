/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.dao;

import java.util.List;
import org.cuinfo.pojo.BasePOJO;

/**
 *���ݿ���������
 * @author Administrator
 */
public interface BaseDAO<T extends BasePOJO> {
    /**
     * ����һ��
     * @param t 
     */
    void save(T entity);
    /**
     * ����һ��
     * @param t 
     */
    void update(T entity);
    /**
     * ɾ��һ��
     */
    void delete(T entity);
    
    
    /*
     * ������е�
     */
    List<T> viewAll();
    /*
     * ͨ��ID���һ��
     */
    T view(int id);
}
