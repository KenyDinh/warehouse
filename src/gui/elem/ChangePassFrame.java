/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.elem;

import common.CommonMethod;
import entities.ChucVu;
import entities.EntityComponent;
import entities.TaiKhoan;
import gui.ElementGui;
import gui.Home;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import service.dao.ServiceDAOFactory;

/**
 *
 * @author KenyDinh
 */
public class ChangePassFrame extends ElementGui {

    private TaiKhoan taikhoan;

    /**
     * Creates new form ChucVuFrame
     */
    public ChangePassFrame() {
        initComponents();
        initLocation();
        ImageIcon icon = CommonMethod.getImageIcon("doimatkhau_1");
        if (icon != null) {
            setIconImage(icon.getImage());
        }
    }

    @Override
    public void initData(EntityComponent entity) {
        if (entity != null && entity instanceof TaiKhoan) {
            taikhoan = (TaiKhoan) entity;
            if (taikhoan.isDeleted()) {
                taikhoan = null;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lb_title = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_ok = new javax.swing.JButton();
        lb_mess = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tf_oldpass = new javax.swing.JPasswordField();
        tf_newpass = new javax.swing.JPasswordField();
        tf_repass = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Quản Lý Chức Vụ");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 102, 0));

        lb_title.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_title.setForeground(new java.awt.Color(255, 255, 255));
        lb_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_title.setText("Đổi Mật Khẩu");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_title, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 102, 0));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mật khẩu hiện tại:");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mật khẩu mới:");

        btn_ok.setBackground(new java.awt.Color(255, 255, 255));
        btn_ok.setText("Xác Nhận");
        btn_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_okActionPerformed(evt);
            }
        });

        lb_mess.setForeground(new java.awt.Color(255, 255, 255));
        lb_mess.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nhập lại mật khẩu mới:");

        tf_oldpass.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_oldpass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_oldpassKeyPressed(evt);
            }
        });

        tf_newpass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_newpassKeyPressed(evt);
            }
        });

        tf_repass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_repassKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_mess, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_oldpass)
                    .addComponent(tf_newpass)
                    .addComponent(tf_repass, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(148, Short.MAX_VALUE)
                .addComponent(btn_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_oldpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_newpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_repass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_mess, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btn_ok)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_okActionPerformed
        okbutton();
    }//GEN-LAST:event_btn_okActionPerformed

    private void tf_oldpassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_oldpassKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tf_newpass.requestFocus();
        }
    }//GEN-LAST:event_tf_oldpassKeyPressed

    private void tf_newpassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_newpassKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tf_repass.requestFocus();
        }
    }//GEN-LAST:event_tf_newpassKeyPressed

    private void tf_repassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_repassKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            okbutton();
        }
    }//GEN-LAST:event_tf_repassKeyPressed

    private void okbutton() {
        lb_mess.setText("");
        if (taikhoan == null) {
            JOptionPane.showMessageDialog(this, "Tài khoản không tồn tại!", "Error!", JOptionPane.ERROR_MESSAGE);
            Home.getInstance(false).closeApp();
            return;
        }
        char[] old_p = tf_oldpass.getPassword();
        char[] new_p = tf_newpass.getPassword();
        char[] re_p = tf_repass.getPassword();
        if (old_p == null || old_p.length == 0) {
            lb_mess.setText("Vui lòng nhập mật khẩu hiện tại!");
            return;
        }
        if (new_p == null || new_p.length == 0) {
            lb_mess.setText("Vui lòng nhập mật khẩu mới!");
            return;
        }
        if (re_p == null || re_p.length == 0) {
            lb_mess.setText("Vui lòng nhập lại mật khẩu mới!");
            return;
        }
        String old_pass = new String(old_p);
        String new_pass = new String(new_p);
        String re_pass = new String(re_p);
        if (!taikhoan.getPassword().equals(CommonMethod.encryptedPasswordMD5(old_pass))) {
            lb_mess.setText("Mật khẩu hiện tại không đúng!");
            return;
        }
        if (!new_pass.equals(re_pass)) {
            lb_mess.setText("Nhập lại mật khẩu không khớp!");
            return;
        }
        taikhoan.setPassword(CommonMethod.encryptedPasswordMD5(new_pass));
        if (!ServiceDAOFactory.insertOrUpdate(taikhoan)) {
            lb_mess.setText("Lỗi:Đổi mật khẩu thất bại!");
            return;
        }
        okCloseFrame(new ChucVu());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lb_mess;
    private javax.swing.JLabel lb_title;
    private javax.swing.JPasswordField tf_newpass;
    private javax.swing.JPasswordField tf_oldpass;
    private javax.swing.JPasswordField tf_repass;
    // End of variables declaration//GEN-END:variables
}
