/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warehouse;

import common.CommonDefine;
import common.HibernateUtil;
import gui.*;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import properties.DataProp;
import valid.Validation;

/**
 *
 * @author KenyDinh
 */
public class Warehouse {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (UnsupportedLookAndFeelException ex) {
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Load data, init Session Factory">
        //load data(prop, config, .... valid)
        String message = DataProp.loadConfigProperties();
        if (!message.equals(CommonDefine.MESSAGE_CONFIG_OK)) {
            JOptionPane.showMessageDialog(null, message);
            return;
        }
        if (!Validation.loadAddress()) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin mạng!");
            return;
        }
        message = Validation.checkLicense(DataProp.getLicenseProperties());
        if (!message.equals(CommonDefine.MESSAGE_CONFIG_OK)) {
            JOptionPane.showMessageDialog(null, message);
            return;
        }
        message = Validation.checkDatabase(DataProp.getDbProperties());
        if (!message.equals(CommonDefine.MESSAGE_CONFIG_OK)) {
            JOptionPane.showMessageDialog(null, message);
            return;
        }
        message = Validation.checkInfo(DataProp.getInfoProperties());
        if (!message.equals(CommonDefine.MESSAGE_CONFIG_OK)) {
            JOptionPane.showMessageDialog(null, message);
            return;
        }
        //init sessionfactory
        if (!HibernateUtil.initSessionFatory()) {
            JOptionPane.showMessageDialog(null, "Không kết nối được cơ sở dữ liệu!");
            return;
        }
        //</editor-fold>

        Login login = new Login();
        login.setVisible(true);

    }

}
