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
public enum Permission {
    NONE(0), INSERT(1), UPDATE_MINE(2), UPDATE(4), DELETE_MINE(8), DELETE(16);
    
    private int id;

    private Permission(int id) {
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
}
