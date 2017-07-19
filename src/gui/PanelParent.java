/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import common.CommonDefine;
import javax.swing.JPanel;

/**
 *
 * @author KenyDinh
 */
public class PanelParent extends JPanel {

    public PanelParent() {
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if(isEnabled()){
                    setBackground(CommonDefine.BUTTON_HIGHLIGHT_COLOR);
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if(isEnabled()){
                    setBackground(CommonDefine.BUTTON_NORMAL_COLOR);
                }
            }
        });
    }
}
