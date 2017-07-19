/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import common.CommonMethod;
import entities.EntityComponent;
import javax.swing.JFrame;

/**
 *
 * @author KenyDinh
 */
public class ElementGui extends JFrame {

    public ElementGui() {
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                Home.getInstance(false).setEnabled(true);
                dispose();
            }
        });
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        Home.getInstance(false).setEnabled(false);
    }

    public void okCloseFrame(EntityComponent entity) {
        //
        Home.getInstance(false).setEnabled(true);
        Home.getInstance(false).reloadDataTable(entity);
        dispose();
    }
    
    public void okCloseFrame() {
        //
        Home.getInstance(false).setEnabled(true);
        Home.getInstance(false).reloadDataTable();
        dispose();
    }
    
    public void initData(EntityComponent entity){
        
    }

    public void initLocation() {
        this.setLocation(CommonMethod.getWidth(this.getWidth()), CommonMethod.getHeight(this.getHeight()));
    }

}
