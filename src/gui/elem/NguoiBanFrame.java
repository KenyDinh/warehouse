/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.elem;

import common.CommonMethod;
import entities.EntityComponent;
import entities.NguoiBan;
import gui.ElementGui;
import javax.swing.ImageIcon;
import service.dao.ServiceDAOFactory;

/**
 *
 * @author KenyDinh
 */
public class NguoiBanFrame extends ElementGui {

    /**
     * Creates new form HangHoaFrame
     */
    public NguoiBanFrame() {
        initComponents();
        initLocation();
        ImageIcon icon = CommonMethod.getImageIcon("nhaphanphoi_1");
        if (icon != null) {
            setIconImage(icon.getImage());
        }
    }

    @Override
    public void initData(EntityComponent entity) {
        if (entity != null && entity instanceof NguoiBan) {
            NguoiBan nb = (NguoiBan) entity;
            lb_title.setText("Cập Nhật Thông Tin Nhà Phân Phối");
            tf_manguoiban.setText(nb.getManguoiban());
            tf_manguoiban.setEditable(false);
            tf_diachi.setText(nb.getDiachi());
            tf_email.setText(nb.getEmail());
            tf_sodienthoai.setText(nb.getSodienthoai());
            tf_tennguoiban.setText(nb.getTennguoiban());
            btn_ok.setText("Cập Nhật");
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
        tf_manguoiban = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tf_sodienthoai = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lb_mess = new javax.swing.JLabel();
        btn_ok = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tf_tennguoiban = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        tf_email = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tf_diachi = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Quản Lý Nhà Phân Phối");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 102, 0));

        lb_title.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_title.setForeground(new java.awt.Color(255, 255, 255));
        lb_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_title.setText("Thêm Nhà Phân Phối Mới");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
        jLabel1.setText("Mã nhà phân phối:");

        tf_manguoiban.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tên nhà phân phối:");

        tf_sodienthoai.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Phone:");

        lb_mess.setForeground(new java.awt.Color(255, 255, 255));
        lb_mess.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btn_ok.setBackground(new java.awt.Color(255, 255, 255));
        btn_ok.setText("Thêm Mới");
        btn_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_okActionPerformed(evt);
            }
        });

        tf_tennguoiban.setColumns(20);
        tf_tennguoiban.setLineWrap(true);
        tf_tennguoiban.setRows(3);
        tf_tennguoiban.setTabSize(4);
        tf_tennguoiban.setWrapStyleWord(true);
        jScrollPane1.setViewportView(tf_tennguoiban);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Email:");

        tf_email.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Địa chỉ:");

        tf_diachi.setColumns(20);
        tf_diachi.setLineWrap(true);
        tf_diachi.setRows(3);
        tf_diachi.setTabSize(4);
        tf_diachi.setWrapStyleWord(true);
        jScrollPane2.setViewportView(tf_diachi);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lb_mess, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tf_manguoiban, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tf_sodienthoai, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tf_email)))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(btn_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_manguoiban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(tf_sodienthoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(tf_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel5)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(lb_mess, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btn_ok)
                .addGap(10, 10, 10))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5});

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
        okButton();
    }//GEN-LAST:event_btn_okActionPerformed

    private void okButton() {
        lb_mess.setText("");
        String manguoibang = tf_manguoiban.getText();
        String tennguoiban = tf_tennguoiban.getText();
        String diachi = tf_diachi.getText();
        String sodienthoai = tf_sodienthoai.getText();
        String email = tf_email.getText();

        if (manguoibang == null || manguoibang.trim().isEmpty()) {
            lb_mess.setText("Mã nhà phân phối không được bỏ trống!");
            return;
        }
        if (tennguoiban == null || tennguoiban.trim().isEmpty()) {
            lb_mess.setText("Tên nhà phân phối không được bỏ trống!");
            return;
        }
        if (diachi == null || diachi.trim().isEmpty()) {
            lb_mess.setText("Địa chỉ nhà phân phối không được bỏ trống!");
            return;
        }
        if (sodienthoai == null || sodienthoai.trim().isEmpty()) {
            lb_mess.setText("Số điện thoại nhà phân phối không được bỏ trống!");
            return;
        }
//        if(!CommonMethod.isNumberic(sodienthoai)){
//            lb_mess.setText("Số điện thoại không hợp lệ!");
//            return;
//        }
        if (email == null) {
            email = "";
        }
        //valid email
        if (tf_manguoiban.isEditable()) {
            NguoiBan temp = new NguoiBan();
            temp.setManguoiban(manguoibang.trim());
            if (ServiceDAOFactory.select(temp) != null) {
                lb_mess.setText("Mã nhà phân phối đã tồn tại!");
                return;
            }
        }
        NguoiBan nb = new NguoiBan();
        nb.setManguoiban(manguoibang.trim());
        nb.setTennguoiban(tennguoiban.trim());
        nb.setDiachi(diachi.trim());
        nb.setEmail(email);
        nb.setSodienthoai(sodienthoai.trim());
        if (!ServiceDAOFactory.insertOrUpdate(nb)) {
            if (tf_manguoiban.isEditable()) {
                lb_mess.setText("Lỗi:Cập nhật thông tin thất bại!");
            } else {
                lb_mess.setText("Lỗi:Thêm thông tin thất bại!");
            }
            return;
        }
        okCloseFrame(new NguoiBan());
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_mess;
    private javax.swing.JLabel lb_title;
    private javax.swing.JTextArea tf_diachi;
    private javax.swing.JTextField tf_email;
    private javax.swing.JTextField tf_manguoiban;
    private javax.swing.JTextField tf_sodienthoai;
    private javax.swing.JTextArea tf_tennguoiban;
    // End of variables declaration//GEN-END:variables
}
