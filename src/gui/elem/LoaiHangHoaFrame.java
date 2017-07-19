/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.elem;

import common.CommonMethod;
import entities.EntityComponent;
import entities.LoaiHangHoa;
import gui.ElementGui;
import javax.swing.ImageIcon;
import service.dao.ServiceDAOFactory;

/**
 *
 * @author KenyDinh
 */
public class LoaiHangHoaFrame extends ElementGui {

    /**
     * Creates new form ChucVuFrame
     */
    public LoaiHangHoaFrame() {
        initComponents();
        initLocation();
        ImageIcon icon = CommonMethod.getImageIcon("loaivatlieu_1");
        if (icon != null) {
            setIconImage(icon.getImage());
        }
    }

    @Override
    public void initData(EntityComponent entity) {
        if (entity != null && entity instanceof LoaiHangHoa) {
            LoaiHangHoa lhh = (LoaiHangHoa) entity;
            tf_maloaihanghoa.setText(lhh.getMaloaihanghoa());
            tf_tenloaihanghoa.setText(lhh.getTenloaihanghoa());
            tf_maloaihanghoa.setEditable(false);
            btn_ok.setText("Cập Nhập");
            lb_title.setText("Cập Nhập Loại Vật Liệu");
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
        tf_maloaihanghoa = new javax.swing.JTextField();
        tf_tenloaihanghoa = new javax.swing.JTextField();
        btn_ok = new javax.swing.JButton();
        lb_mess = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Quản Lý Loại Vật Liệu");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 102, 0));

        lb_title.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_title.setForeground(new java.awt.Color(255, 255, 255));
        lb_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_title.setText("Thêm Loại Vật Liệu Mới");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_title, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
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
        jLabel1.setText("Mã loại vật liệu:");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tên loại vật liệu:");

        tf_maloaihanghoa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        tf_tenloaihanghoa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btn_ok.setBackground(new java.awt.Color(255, 255, 255));
        btn_ok.setText("Thêm Mới");
        btn_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_okActionPerformed(evt);
            }
        });

        lb_mess.setForeground(new java.awt.Color(255, 255, 255));
        lb_mess.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(148, 148, 148))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_maloaihanghoa, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addComponent(tf_tenloaihanghoa))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_mess, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_maloaihanghoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_tenloaihanghoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_mess, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_ok)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2, tf_maloaihanghoa, tf_tenloaihanghoa});

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

    private void okbutton() {
        lb_mess.setText("");
        String maloaihanghoa = tf_maloaihanghoa.getText();
        String tenloaihanghoa = tf_tenloaihanghoa.getText();
        if (maloaihanghoa == null || maloaihanghoa.trim().isEmpty()) {
            lb_mess.setText("Mã Loại Vật Liệu không được bỏ trống!");
            return;
        }
        if (tenloaihanghoa == null || tenloaihanghoa.trim().isEmpty()) {
            lb_mess.setText("Tên Loại Vật Liệu không được bỏ trống!");
            return;
        }
        if (tf_maloaihanghoa.isEditable()) {
            LoaiHangHoa temp = new LoaiHangHoa();
            temp.setMaloaihanghoa(maloaihanghoa.trim());
            if (ServiceDAOFactory.select(temp) != null) {
                lb_mess.setText("Mã Loại Vật Liệu đã tồn tại!");
                return;
            }
        }
        LoaiHangHoa lhh = new LoaiHangHoa();
        lhh.setMaloaihanghoa(maloaihanghoa.trim());
        lhh.setTenloaihanghoa(tenloaihanghoa.trim());
        if (!ServiceDAOFactory.insertOrUpdate(lhh)) {
            if (tf_maloaihanghoa.isEditable()) {
                lb_mess.setText("Lỗi:Không thêm được Loại Vật Liệu mới!");
            } else {
                lb_mess.setText("Lỗi:Không cập nhật được Loại Vật Liệu!");
            }
            return;
        }
        okCloseFrame(new LoaiHangHoa());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lb_mess;
    private javax.swing.JLabel lb_title;
    private javax.swing.JTextField tf_maloaihanghoa;
    private javax.swing.JTextField tf_tenloaihanghoa;
    // End of variables declaration//GEN-END:variables
}
