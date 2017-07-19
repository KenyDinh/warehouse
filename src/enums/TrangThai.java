/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enums;

/**
 *
 * @author KenyDinh
 */
public enum TrangThai {
    NONE(0,"NONE"),
    CREATED(1,"CREATED"),
    STARTING(2,"STARTING"),
    PROCESSING(3,"PROCESSING"),
    FINISHED(4,"FINISHED"),
    ;
    
    private int id;
    private String name;
    
    private static final TrangThai DEFAULT_STATUS = NONE;
    
    private TrangThai(int id, String name){
        this.id = id;
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public TrangThai getTrangThai(String name){
        for(TrangThai tt : TrangThai.values()){
            if(tt.getName().equals(name)){
                return tt;
            }
        }
        return DEFAULT_STATUS;
    }
}
