/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import common.CommonMethod;
import common.HibernateUtil;
import javax.swing.JFrame;

/**
 *
 * @author KenyDinh
 */
public class ParentGui extends JFrame {

    public ParentGui() {
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                HibernateUtil.closeSessionFactory();
                System.exit(0);
            }
        });
    }
    
    public void initLocation(){
        this.setLocation(CommonMethod.getWidth(this.getWidth()), CommonMethod.getHeight(this.getHeight()));
    }
}
