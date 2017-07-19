/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.elem;

import common.CommonDefine;
import common.CommonMethod;
import entities.CongTrinh;
import entities.EntityComponent;
import enums.TrangThai;
import gui.ElementGui;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import service.dao.ServiceDAOFactory;

/**
 *
 * @author KenyDinh
 */
public class CongTrinhFrame extends ElementGui {

    /**
     * Creates new form CongTrinhFrame
     */
    public CongTrinhFrame() {
        initComponents();
        initLocation();
        initStatus();
        initDateFormat();
        ImageIcon icon = CommonMethod.getImageIcon("congtrinh_1");
        if (icon != null) {
            setIconImage(icon.getImage());
        }
    }

    private void initStatus() {
        DefaultComboBoxModel<String> listTT = new DefaultComboBoxModel<>();
        for (TrangThai tt : TrangThai.values()) {
            listTT.addElement(tt.getName());
        }
        cbb_trangthai.setModel(listTT);
    }

    private void initDateFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat(CommonDefine.DATE_FORMAT_GUI);
        date_ngaybatdau.setFormats(sdf);
        date_ngayketthuc.setFormats(sdf);
    }

    @Override
    public void initData(EntityComponent entity) {
        if (entity != null && entity instanceof CongTrinh) {
            CongTrinh ct = (CongTrinh) entity;
            lb_title.setText("Cập Nhật Công Trình");
            tf_macongtrinh.setText(ct.getMacongtrinh());
            tf_macongtrinh.setEditable(false);
            btn_ok.setText("Cập Nhập");
            tf_diachicongtrinh.setText(ct.getDiachicongtrinh());
            tf_tencongtrinh.setText(ct.getTencongtrinh());
            cbb_trangthai.setSelectedItem(ct.getTrangthai());
            Date nbd = CommonMethod.getDateStringDB(ct.getNgaybatdau());
            if (nbd != null) {
                date_ngaybatdau.setDate(nbd);
            }
            Date nkt = CommonMethod.getDateStringDB(ct.getNgayketthuc());
            if (nkt != null) {
                date_ngayketthuc.setDate(nkt);
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
        jPanel3 = new javax.swing.JPanel();
        lb_tct = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tf_tencongtrinh = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        lb_dc = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tf_diachicongtrinh = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        tf_macongtrinh = new javax.swing.JTextField();
        lb_mct = new javax.swing.JLabel();
        lb_nbd = new javax.swing.JLabel();
        cbb_trangthai = new javax.swing.JComboBox();
        lb_tt = new javax.swing.JLabel();
        lb_nkt = new javax.swing.JLabel();
        date_ngayketthuc = new org.jdesktop.swingx.JXDatePicker();
        date_ngaybatdau = new org.jdesktop.swingx.JXDatePicker();
        jPanel6 = new javax.swing.JPanel();
        btn_ok = new javax.swing.JButton();
        lb_mess = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Quản Lý Công Trình");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 102, 0));

        lb_title.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_title.setForeground(new java.awt.Color(255, 255, 255));
        lb_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_title.setText("Thêm Công Trình Mới");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_title, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_title, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 102, 0));

        lb_tct.setForeground(new java.awt.Color(255, 255, 255));
        lb_tct.setText("Tên công trình:");

        tf_tencongtrinh.setColumns(20);
        tf_tencongtrinh.setLineWrap(true);
        tf_tencongtrinh.setRows(3);
        tf_tencongtrinh.setTabSize(4);
        tf_tencongtrinh.setWrapStyleWord(true);
        jScrollPane1.setViewportView(tf_tencongtrinh);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lb_tct, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 373, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lb_tct, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 102, 0));

        lb_dc.setForeground(new java.awt.Color(255, 255, 255));
        lb_dc.setText("Địa chỉ công trình:");

        tf_diachicongtrinh.setColumns(20);
        tf_diachicongtrinh.setLineWrap(true);
        tf_diachicongtrinh.setRows(3);
        tf_diachicongtrinh.setTabSize(4);
        tf_diachicongtrinh.setWrapStyleWord(true);
        jScrollPane2.setViewportView(tf_diachicongtrinh);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lb_dc, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lb_dc, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(0, 102, 0));

        tf_macongtrinh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lb_mct.setForeground(new java.awt.Color(255, 255, 255));
        lb_mct.setText("Mã Công Trình:");

        lb_nbd.setForeground(new java.awt.Color(255, 255, 255));
        lb_nbd.setText("Ngày Bắt Đầu:");

        cbb_trangthai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lb_tt.setForeground(new java.awt.Color(255, 255, 255));
        lb_tt.setText("Trạng Thái:");

        lb_nkt.setForeground(new java.awt.Color(255, 255, 255));
        lb_nkt.setText("Ngày Kết Thúc:");

        date_ngayketthuc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        date_ngaybatdau.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lb_tt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_mct, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(tf_macongtrinh, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(cbb_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_nbd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_nkt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(date_ngayketthuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(date_ngaybatdau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_mct, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_nbd, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_macongtrinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date_ngaybatdau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_tt)
                    .addComponent(lb_nkt))
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date_ngayketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {date_ngayketthuc, lb_mct, lb_nkt, lb_tt});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbb_trangthai, lb_nbd});

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel6.setBackground(new java.awt.Color(0, 102, 0));

        btn_ok.setBackground(new java.awt.Color(255, 255, 255));
        btn_ok.setText("Thêm Mới");
        btn_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_okActionPerformed(evt);
            }
        });

        lb_mess.setForeground(new java.awt.Color(255, 255, 255));
        lb_mess.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(btn_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lb_mess, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lb_mess, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btn_ok)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_okActionPerformed
        okButton();
    }//GEN-LAST:event_btn_okActionPerformed

    private void okButton() {
        lb_mess.setText("");
        String macongtrinh = tf_macongtrinh.getText();
        String trangthai = cbb_trangthai.getSelectedItem().toString();
        String tencongtrinh = tf_tencongtrinh.getText();
        String diachicongtrinh = tf_diachicongtrinh.getText();
        Date ngaybatdau = date_ngaybatdau.getDate();
        Date ngayketthuc = date_ngayketthuc.getDate();
        if (macongtrinh == null || macongtrinh.trim().isEmpty()) {
            lb_mess.setText("Mã Công Trình không được bỏ trống!");
            return;
        }
        if (tencongtrinh == null || tencongtrinh.trim().isEmpty()) {
            lb_mess.setText("Tên Công Trình không được bỏ trống!");
            return;
        }
        if (diachicongtrinh == null || diachicongtrinh.trim().isEmpty()) {
            lb_mess.setText("Địa chỉ Công Trình không được bỏ trống!");
            return;
        }
        if (ngaybatdau == null) {
            lb_mess.setText("Ngày bắt đầu Công Trình không được bỏ trống!");
            return;
        }
        if (ngayketthuc == null) {
            lb_mess.setText("Ngày kết thúc Công Trình không được bỏ trống!");
            return;
        }
        if (tf_macongtrinh.isEditable()) {
            CongTrinh temp = new CongTrinh();
            temp.setMacongtrinh(macongtrinh.trim());
            if (ServiceDAOFactory.select(temp) != null) {
                lb_mess.setText("Mã Công Trình đã tồn tại!");
                return;
            }
        }
        CongTrinh ct = new CongTrinh();
        ct.setMacongtrinh(macongtrinh.trim());
        ct.setTencongtrinh(tencongtrinh.trim());
        ct.setDiachicongtrinh(diachicongtrinh.trim());
        ct.setNgaybatdau(CommonMethod.getFormatDateStringDB(ngaybatdau));
        ct.setNgayketthuc(CommonMethod.getFormatDateStringDB(ngayketthuc));
        ct.setNgaycapnhat(CommonMethod.getFormatDateStringDB(new Date()));
        ct.setTrangthai(trangthai);

        if (!ServiceDAOFactory.insertOrUpdate(ct)) {
            if (tf_macongtrinh.isEditable()) {
                lb_mess.setText("Lỗi:Không thêm mới được Công Trình!");
            } else {
                lb_mess.setText("Lỗi:Không cập nhật được Công Trình!");
            }
            return;
        }
        okCloseFrame(new CongTrinh());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ok;
    private javax.swing.JComboBox cbb_trangthai;
    private org.jdesktop.swingx.JXDatePicker date_ngaybatdau;
    private org.jdesktop.swingx.JXDatePicker date_ngayketthuc;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_dc;
    private javax.swing.JLabel lb_mct;
    private javax.swing.JLabel lb_mess;
    private javax.swing.JLabel lb_nbd;
    private javax.swing.JLabel lb_nkt;
    private javax.swing.JLabel lb_tct;
    private javax.swing.JLabel lb_title;
    private javax.swing.JLabel lb_tt;
    private javax.swing.JTextArea tf_diachicongtrinh;
    private javax.swing.JTextField tf_macongtrinh;
    private javax.swing.JTextArea tf_tencongtrinh;
    // End of variables declaration//GEN-END:variables
}
