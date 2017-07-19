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
public enum Action {
    INSERT("Thêm mới"),
    UPDATE("Cập nhật"),
    DELETE("Xóa"),;
    
    private String name;

    private Action(String name) {
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
}
