/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 *
 * @author KenyDinh
 */
//@Inheritance
public interface EntityComponent extends Serializable{
    
    public Field[] getFields();
    
    public Map<String,Field> getFieldsAsMap();
    
    public List<String> getPrimaryKeys();
    
    public String getClassName();
    
    public String getSelectQuery();
    
    public String getSelectOption();
    
    public void setSelectQuery(String selectQuery);
    
    public void setSelectOption(String selectOption);
    
    public void setLimit(int limit);
    
    public int getLimit();
    
}
