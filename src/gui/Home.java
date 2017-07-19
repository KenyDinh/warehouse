/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import common.CommonDefine;
import common.CommonMethod;
import common.HibernateUtil;
import entities.*;
import enums.Action;
import enums.FilterType;
import enums.HistoryType;
import enums.Permission;
import enums.Role;
import gui.elem.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import properties.DataProp;
import service.dao.ServiceDAOFactory;
import valid.Validation;

/**
 *
 * @author KenyDinh
 */
public class Home extends ParentGui {

    private static Home INSTANCE = new Home();

    public static synchronized Home getInstance(boolean createNew) {
        if (createNew) {
            INSTANCE = new Home();
        }
        return INSTANCE;
    }

    /**
     * Creates new form MainMenu
     */
    private Home() {
        initComponents();
        initLocation();
        ImageIcon icon = CommonMethod.getImageIcon("home");
        if (icon != null) {
            setIconImage(icon.getImage());
        }
        toggleConstructionsButton(false);
        hideAllFilterAction();
        initconfigDate();
        table.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
                comp.setBackground(CommonDefine.TABLE_HEADER_COLOR);
                return comp;
            }

        });
//        table.setFillsViewportHeight(true);
        scroll_table.getViewport().setBackground(table.getBackground());
        lb_backup.setVisible(false);
    }

    private static final String GUI_CLASS_PATH = "gui.elem.";
    private static final String ENTITY_CLASS_PATH = "entities.";
    private static final String CHUCVU = CommonDefine.CHUCVU;
    private static final String CONGTRINH = CommonDefine.CONGTRINH;
    private static final String DONVI = CommonDefine.DONVI;
    private static final String DUTOAN = CommonDefine.DUTOAN;
    private static final String HANGHOA = CommonDefine.HANGHOA;
    private static final String HISTORY = CommonDefine.HISTORY;
    private static final String LOAIHANGHOA = CommonDefine.LOAIHANGHOA;
    private static final String NGUOIBAN = CommonDefine.NGUOIBAN;
    private static final String TAIKHOAN = CommonDefine.TAIKHOAN;
    private static final String VATLIEUNHAP = CommonDefine.VATLIEUNHAP;
    private static final String VATLIEUTONKHO = CommonDefine.VATLIEUTONKHO;
    private static final String VATLIEUXUAT = CommonDefine.VATLIEUXUAT;
    private static final int TABLE_WIDTH = 852;
    private final FileNameExtensionFilter filter = new FileNameExtensionFilter("Exel 97-2003 Workbook", "xls");

    public static String tennguoidung = "";

    private String frameName;
    private long id_taikhoan;
    private CongTrinh congtrinh;
    private int selectedIndex = -1;

    private List<EntityComponent> listCurrentData;
    private Map<String, Permission[]> mapPermissions;
    private List<FilterElem> listFilter;
    private Role role;

    private Object[] column;
    private Object[][] data;
    private Set<Integer> listNumberValue;

    public void setTaiKhoan(TaiKhoan tk) {
        id_taikhoan = tk.getId();
        tennguoidung = tk.getUsername();
        lb_user.setText(tennguoidung + "-" + Role.valueOf(tk.getRole()).getName());
        if (Role.valueOf(tk.getRole()) == Role.ADMIN) {
            lb_backup.setVisible(true);
        }
        checkRoleUser();
    }

    private void initconfigDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(CommonDefine.DATE_FORMAT_GUI);
        date_filter_1.setFormats(sdf);
        date_filter_2.setFormats(sdf);
    }

    private ElementGui getElementGUI(String name) {
        if (frameName == null) {
            return null;
        }
        String className = GUI_CLASS_PATH + name + "Frame";
        try {
            Class<? extends Object> guiClass = Class.forName(className);
            Object o = guiClass.newInstance();
            return (ElementGui) o;
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }
        return getElementGuiBySimpleName(name);
    }

    //hardcode
    private ElementGui getElementGuiBySimpleName(String name) {
        if (name.equals(CHUCVU)) {
            return new ChucVuFrame();
        }
        if (name.equals(CONGTRINH)) {
            return new CongTrinhFrame();
        }
        if (name.equals(DONVI)) {
            return new DonViFrame();
        }
        if (name.equals(DUTOAN)) {
            return new DuToanFrame();
        }
        if (name.equals(HANGHOA)) {
            return new HangHoaFrame();
        }
        if (name.equals(LOAIHANGHOA)) {
            return new LoaiHangHoaFrame();
        }
        if (name.equals(NGUOIBAN)) {
            return new NguoiBanFrame();
        }
        if (name.equals(TAIKHOAN)) {
            //return new HangHoaFrame();
        }
        if (name.equals(VATLIEUNHAP)) {
            return new VatLieuNhapFrame();
        }
        if (name.equals(VATLIEUXUAT)) {
            return new VatLieuXuatFrame();
        }
        return null;
    }

    private EntityComponent getEntity(String name) {
        if (name == null) {
            return null;
        }
        String className = ENTITY_CLASS_PATH + name;
        try {
            Class<? extends Object> entity = Class.forName(className);
            Object o = entity.newInstance();
            return (EntityComponent) o;
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }
        return getEntityBySimpleName(name);
    }

    //hardcode
    private EntityComponent getEntityBySimpleName(String name) {
        if (name.equals(CHUCVU)) {
            return new ChucVu();
        }
        if (name.equals(CONGTRINH)) {
            return new CongTrinh();
        }
        if (name.equals(DONVI)) {
            return new DonVi();
        }
        if (name.equals(DUTOAN)) {
            return new DuToan();
        }
        if (name.equals(HISTORY)) {
            return new History();
        }
        if (name.equals(HANGHOA)) {
            return new HangHoa();
        }
        if (name.equals(LOAIHANGHOA)) {
            return new LoaiHangHoa();
        }
        if (name.equals(NGUOIBAN)) {
            return new NguoiBan();
        }
        if (name.equals(TAIKHOAN)) {
            return new TaiKhoan();
        }
        if (name.equals(VATLIEUNHAP)) {
            return new VatLieuNhap();
        }
        if (name.equals(VATLIEUTONKHO)) {
            return new VatLieuTonKho();
        }
        if (name.equals(VATLIEUXUAT)) {
            return new VatLieuXuat();
        }
        return null;
    }

    //function check permission
    private void checkRoleUser() {
        pn_add.setVisible(false);
        pn_update.setVisible(false);
        pn_delete.setVisible(false);
        pn_refresh.setVisible(true);
        //
        pn_cv.setVisible(true);
        pn_dv.setVisible(true);
        pn_hh.setVisible(true);
        pn_lhh.setVisible(true);
        pn_nb.setVisible(true);
        pn_tk.setVisible(true);
        //
        TaiKhoan taikhoan = checkTaiKhoan();
        Role r = Role.valueOf(taikhoan.getRole());
        if (role == null || role.getId() != r.getId() || mapPermissions == null) {
            role = r;
            mapPermissions = role.getMapPermission();
        }
        if (!mapPermissions.containsKey(CHUCVU)) {
            pn_cv.setVisible(false);
        }
        if (!mapPermissions.containsKey(CONGTRINH)) {
            pn_ct.setVisible(false);
        }
        if (!mapPermissions.containsKey(DONVI)) {
            pn_dv.setVisible(false);
        }
        if (!mapPermissions.containsKey(DUTOAN)) {
            pn_dt.setVisible(false);
        }
        if (!mapPermissions.containsKey(HANGHOA)) {
            pn_hh.setVisible(false);
        }
        if (!mapPermissions.containsKey(HISTORY)) {
            pn_history.setVisible(false);
        }
        if (!mapPermissions.containsKey(LOAIHANGHOA)) {
            pn_lhh.setVisible(false);
        }
        if (!mapPermissions.containsKey(NGUOIBAN)) {
            pn_nb.setVisible(false);
        }
        if (!mapPermissions.containsKey(VATLIEUNHAP)) {
            pn_vln.setVisible(false);
        }
        if (!mapPermissions.containsKey(VATLIEUTONKHO)) {
            pn_vltk.setVisible(false);
        }
        if (!mapPermissions.containsKey(VATLIEUXUAT)) {
            pn_vlx.setVisible(false);
        }
        if (!mapPermissions.containsKey(TAIKHOAN)) {
            pn_tk.setVisible(false);
        }
        //
        checkActionButton();
        checkRootAccount();
    }

    private void checkRootAccount() {
        if (Home.tennguoidung.equals("root")) {
//            pn_cv.setVisible(false);
            pn_dv.setVisible(false);
            pn_hh.setVisible(false);
            pn_nb.setVisible(false);
            pn_lhh.setVisible(false);
            pn_ct.setVisible(false);
            pn_vln.setVisible(false);
            pn_vlx.setVisible(false);
            pn_vltk.setVisible(false);
            pn_history.setVisible(false);
            pn_dt.setVisible(false);
        }
    }

    private TaiKhoan checkTaiKhoan() {
        TaiKhoan t = new TaiKhoan();
        t.setId(id_taikhoan);
        EntityComponent entity = ServiceDAOFactory.select(t);
        if (entity != null) {
            TaiKhoan taikhoan = (TaiKhoan) entity;
            if (!taikhoan.isDeleted()) {
                return taikhoan;
            }
        }
        showErrorMessage("Tài khoản không tồn tại!", JOptionPane.ERROR_MESSAGE);
        closeApp();
        return null;
    }

    public void closeApp() {
        HibernateUtil.closeSessionFactory();
        System.exit(0);
    }

    private void checkActionButton() {
        if (frameName == null) {
            return;
        }
        if (mapPermissions != null && mapPermissions.containsKey(frameName)) {
            Permission[] permissions = mapPermissions.get(frameName);
            for (Permission p : permissions) {
                if (p.getId() == Permission.INSERT.getId()) {
                    pn_add.setVisible(true);
                } else if (p.getId() == Permission.UPDATE.getId() || p.getId() == Permission.UPDATE_MINE.getId()) {
                    pn_update.setVisible(true);
                } else if (p.getId() == Permission.DELETE.getId() || p.getId() == Permission.DELETE_MINE.getId()) {
                    pn_delete.setVisible(true);
                }
            }
        }
    }

    private void toggleConstructionsButton(boolean visible) {
        pn_vln.setVisible(visible);
        pn_vltk.setVisible(visible);
        pn_vlx.setVisible(visible);
        pn_dt.setVisible(visible);
        pn_history.setVisible(visible);
        pn_ct.setVisible(!visible);
        pn_mid.setVisible(visible);
    }

    private void initFilterAcction() {
        listFilter = null;
        choice_filter_type.removeAllItems();
        hideAllFilterAction();
        if (frameName == null) {
            return;
        }
        pn_action_top.setVisible(true);
        pn_export_data.setVisible(true);
        if (frameName.equals(VATLIEUNHAP) || frameName.equals(VATLIEUXUAT) || frameName.equals(HISTORY)) {
            listFilter = new ArrayList<>();
            pn_choose_filter.setVisible(true);
            if (frameName.equals(VATLIEUNHAP)) {
                listFilter.add(new FilterElem("None", "none", FilterType.NONE));
                listFilter.add(new FilterElem("Mã vật liệu", "mahanghoa", FilterType.STRING));
                listFilter.add(new FilterElem("Khối lượng", "khoiluong", FilterType.NUMBER));
                listFilter.add(new FilterElem("Ngày nhập", "ngaynhap", FilterType.DATE));
            } else if (frameName.equals(VATLIEUXUAT)) {
                listFilter.add(new FilterElem("None", "none", FilterType.NONE));
                listFilter.add(new FilterElem("Mã vật liệu", "mahanghoa", FilterType.STRING));
                listFilter.add(new FilterElem("Khối lượng", "khoiluong", FilterType.NUMBER));
                listFilter.add(new FilterElem("Ngày xuất", "ngayxuat", FilterType.DATE));
            } else if (frameName.equals(HISTORY)) {
                listFilter.add(new FilterElem("None", "none", FilterType.NONE));
                listFilter.add(new FilterElem("Thời gian", "datemodify", FilterType.DATE));
                listFilter.add(new FilterElem("Thao tác dữ liệu", "action", FilterType.STRING));
                listFilter.add(new FilterElem("Hành động", "type", FilterType.STRING));
            }
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            for (FilterElem fe : listFilter) {
                model.addElement(fe.getGuiName());
            }
            choice_filter_type.setModel(model);
        }
    }

    private void hideAllFilterAction() {
        pn_action_top.setVisible(false);
        pn_choose_filter.setVisible(false);
        pn_filter_string.setVisible(false);
        pn_filter_number.setVisible(false);
        pn_filter_date.setVisible(false);
        pn_btn_filter.setVisible(false);
        pn_export_data.setVisible(false);
    }

    private void showButton(boolean b) {
        if (frameName == null) {
            return;
        }
        if (frameName.equals(CONGTRINH)) {
            toggleConstructionsButton(b);
            checkRoleUser();
        }
    }

    private void onClickButton() {
        if (frameName == null) {
            return;
        }
        if (!frameName.equals(VATLIEUNHAP) && !frameName.equals(VATLIEUTONKHO) && !frameName.equals(VATLIEUXUAT) && !frameName.equals(DUTOAN) && !frameName.equals(HISTORY)) {
            congtrinh = null;
            toggleConstructionsButton(false);
        }
        EntityComponent entity = getEntity(frameName);
        if (entity != null) {
            if (congtrinh != null) {
                for (Field f : entity.getFields()) {
                    if (f.getName().equals("macongtrinh")) {
                        try {
                            f.set(entity, congtrinh.getMacongtrinh());
                        } catch (IllegalArgumentException ex) {
                            showErrorMessage("Có lỗi xảy ra, không thể tải dữ liệu!", JOptionPane.ERROR_MESSAGE);
                            return;
                        } catch (IllegalAccessException ex) {
                            showErrorMessage("Có lỗi xảy ra, không thể tải dữ liệu!", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }
            }
            getDataFromDB(entity);
        }
        checkRoleUser();
        initFilterAcction();
    }

    public void reloadDataTable() {
        EntityComponent entity = getEntity(frameName);
        if (entity != null) {
            getDataFromDB(entity);
        }
    }

    public void reloadDataTable(EntityComponent entity) {
        if (entity != null) {
            getDataFromDB(entity);
        }
    }

    private void getDataFromDB(EntityComponent entity) {
        if (frameName == null) {
            return;
        }
        selectedIndex = -1;
        listCurrentData = new ArrayList<>();
        List<EntityComponent> listEntitys;
        if (frameName.equals(TAIKHOAN) && entity instanceof TaiKhoan) {
            TaiKhoan tk = new TaiKhoan();
            tk.setSelectQuery("FROM " + TAIKHOAN + " WHERE id != " + id_taikhoan + " AND deleted = 0 AND username != 'root'");
            listEntitys = ServiceDAOFactory.executeQueryList(tk);
        } else {
            if (entity.getSelectQuery() != null) {
                listEntitys = ServiceDAOFactory.executeQueryList(entity);
            } else {
                listEntitys = ServiceDAOFactory.selectList(entity);
            }
        }
        Object[] columnNames;
        Object[][] datas;
        Set<Integer> listFixSmallColumnIndex = new HashSet<>();
        Set<Integer> listFixLargeColumnIndex = new HashSet<>();
        listNumberValue = new HashSet<>();
        int numColumn, numRow;
        if (listEntitys.isEmpty()) {
            numRow = 1;
            numColumn = 1;
            columnNames = new Object[numColumn];
            columnNames[0] = "Data";
            datas = new Object[numRow][numColumn];
            datas[0][0] = "No Data To Display!";
        } else {
            numRow = listEntitys.size();
            if (frameName.equals(CHUCVU)) {
                numColumn = 2;
                columnNames = new Object[numColumn];
                datas = new Object[numRow][numColumn];
                columnNames[0] = "Mã Chức Vụ";
                columnNames[1] = "Tên Chức Vụ";
//                listFixSmallColumnIndex.add(0);
                int row = 0;
                for (EntityComponent e : listEntitys) {
                    if (e instanceof ChucVu) {
                        ChucVu cv = (ChucVu) e;
                        datas[row][0] = cv.getMachucvu();
                        datas[row][1] = cv.getTenchucvu();
                        row++;
                    }
                }
            } else if (frameName.equals(CONGTRINH)) {
                numColumn = 7;
                columnNames = new Object[numColumn];
                datas = new Object[numRow][numColumn];
                columnNames[0] = "Mã Công Trình";
                columnNames[1] = "Tên Công Trình";
                columnNames[2] = "Địa Chỉ";
                columnNames[3] = "Ngày Bắt Đầu";
                columnNames[4] = "Ngày Kết Thúc";
                columnNames[5] = "Ngày Cập Nhật";
                columnNames[6] = "Trạng Thái";
                listFixSmallColumnIndex.add(0);
                listFixLargeColumnIndex.add(1);
                listFixLargeColumnIndex.add(2);
                int row = 0;
                for (EntityComponent e : listEntitys) {
                    if (e instanceof CongTrinh) {
                        CongTrinh ct = (CongTrinh) e;
                        datas[row][0] = ct.getMacongtrinh();
                        datas[row][1] = ct.getTencongtrinh();
                        datas[row][2] = ct.getDiachicongtrinh();
                        datas[row][3] = CommonMethod.convertDateDbtoGui(ct.getNgaybatdau());
                        datas[row][4] = CommonMethod.convertDateDbtoGui(ct.getNgayketthuc());
                        datas[row][5] = ct.getNgaycapnhat();
                        datas[row][6] = ct.getTrangthai();
                        row++;
                    }
                }
            } else if (frameName.equals(DONVI)) {
                numColumn = 2;
                columnNames = new Object[numColumn];
                datas = new Object[numRow][numColumn];
                columnNames[0] = "Mã Đơn Vị";
                columnNames[1] = "Tên Đơn Vị";
//                listFixSmallColumnIndex.add(0);
//                listFixSmallColumnIndex.add(1);
                int row = 0;
                for (EntityComponent e : listEntitys) {
                    if (e instanceof DonVi) {
                        DonVi dv = (DonVi) e;
                        datas[row][0] = dv.getMadonvi();
                        datas[row][1] = dv.getTendonvi();
                        row++;
                    }
                }
            } else if (frameName.equals(DUTOAN)) {
                numColumn = 6;
                columnNames = new Object[numColumn];
                datas = new Object[numRow][numColumn];
                columnNames[0] = "Mã Vật Liệu";
                columnNames[1] = "Tên Vật Liệu";
                columnNames[2] = "Tên Đơn Vị";
                columnNames[3] = "Tên Công Trình";
                columnNames[4] = "Tổng Khối Lượng";
                columnNames[5] = "Khối Lượng Dự Toán";
                listFixSmallColumnIndex.add(0);
                listFixSmallColumnIndex.add(1);
                listFixSmallColumnIndex.add(2);
                listFixLargeColumnIndex.add(3);
                listNumberValue.add(4);
                listNumberValue.add(5);
                int row = 0;
                List<EntityComponent> listDonVi = ServiceDAOFactory.selectList(new DonVi());
                List<EntityComponent> listHangHoa = ServiceDAOFactory.selectList(new HangHoa());
                List<EntityComponent> listCongTrinh = ServiceDAOFactory.selectList(new CongTrinh());
                for (EntityComponent e : listEntitys) {
                    if (e instanceof DuToan) {
                        DuToan dt = (DuToan) e;
                        HangHoa hh = null;
                        DonVi dv = null;
                        CongTrinh ct = null;
                        for (EntityComponent eh : listHangHoa) {
                            HangHoa h = (HangHoa) eh;
                            if (h.getMahanghoa().equals(dt.getMahanghoa())) {
                                hh = h;
                                break;
                            }
                        }
                        if (hh == null) {
                            continue;
                        }
                        for (EntityComponent ed : listDonVi) {
                            DonVi d = (DonVi) ed;
                            if (d.getMadonvi().equals(hh.getMadonvi())) {
                                dv = d;
                                break;
                            }
                        }
                        if (dv == null) {
                            continue;
                        }
                        for (EntityComponent ec : listCongTrinh) {
                            CongTrinh c = (CongTrinh) ec;
                            if (c.getMacongtrinh().equals(dt.getMacongtrinh())) {
                                ct = c;
                                break;
                            }
                        }
                        if (ct == null) {
                            continue;
                        }
                        datas[row][0] = dt.getMahanghoa();
                        datas[row][1] = hh.getTenhanghoa();
                        datas[row][2] = dv.getTendonvi();
                        datas[row][3] = ct.getTencongtrinh();
                        datas[row][4] = dt.getTongkhoiluong();
                        datas[row][5] = dt.getKhoiluongdutoan();
                        row++;
                    }
                }
            } else if (frameName.equals(HANGHOA)) {
                numColumn = 5;
                columnNames = new Object[numColumn];
                columnNames[0] = "Mã Vật Liệu";
                columnNames[1] = "Tên Vật Liệu";
                columnNames[2] = "Tên Loại Vật Liệu";
                columnNames[3] = "Đơn Vị";
                columnNames[4] = "Giá Vật Liệu";
                listFixSmallColumnIndex.add(0);
                listFixSmallColumnIndex.add(1);
                listFixSmallColumnIndex.add(3);
                listNumberValue.add(4);
                datas = new Object[numRow][numColumn];
                int row = 0;
                List<EntityComponent> listDonVi = ServiceDAOFactory.selectList(new DonVi());
                List<EntityComponent> listLoaiHangHoa = ServiceDAOFactory.selectList(new LoaiHangHoa());
                for (EntityComponent e : listEntitys) {
                    if (e instanceof HangHoa) {
                        HangHoa hh = (HangHoa) e;
                        DonVi dv = null;
                        LoaiHangHoa lhh = null;
                        for (EntityComponent ed : listDonVi) {
                            DonVi d = (DonVi) ed;
                            if (d.getMadonvi().equals(hh.getMadonvi())) {
                                dv = d;
                                break;
                            }
                        }
                        if (dv == null) {
                            continue;
                        }
                        for (EntityComponent elh : listLoaiHangHoa) {
                            LoaiHangHoa lh = (LoaiHangHoa) elh;
                            if (lh.getMaloaihanghoa().equals(hh.getMaloaihanghoa())) {
                                lhh = lh;
                                break;
                            }
                        }
                        if (lhh == null) {
                            continue;
                        }
                        datas[row][0] = hh.getMahanghoa();
                        datas[row][1] = hh.getTenhanghoa();
                        datas[row][2] = lhh.getTenloaihanghoa();
                        datas[row][3] = dv.getTendonvi();
                        datas[row][4] = hh.getGia();
                        row++;
                    }
                }
            } else if (frameName.equals(HISTORY)) {
                numColumn = 12;
                columnNames = new Object[numColumn];
                columnNames[0] = "Thời gian";
                columnNames[1] = "Thao tác dữ liệu";
                columnNames[2] = "Hành động";
                columnNames[3] = "Tên công trình";
                columnNames[4] = "Ngày trước cập nhật";
                columnNames[5] = "Ngày sau cập nhật";
                columnNames[6] = "Mã vật liệu cũ";
                columnNames[7] = "Mã vật liệu mới";
                columnNames[8] = "Tên vật liệu cũ";
                columnNames[9] = "Tên vật liệu mới";
                columnNames[10] = "Khối lượng cũ";
                columnNames[11] = "Khối lượng mới";
                listFixSmallColumnIndex.add(1);
                listFixSmallColumnIndex.add(2);
                listFixSmallColumnIndex.add(6);
                listFixSmallColumnIndex.add(7);
                listFixSmallColumnIndex.add(8);
                listFixSmallColumnIndex.add(9);
                listFixLargeColumnIndex.add(3);
                listNumberValue.add(10);
                listNumberValue.add(11);
                datas = new Object[numRow][numColumn];
                int row = 0;
                List<EntityComponent> listECongTrinh = ServiceDAOFactory.selectList(new CongTrinh());
                List<EntityComponent> listEHangHoa = ServiceDAOFactory.selectList(new HangHoa());
                for (EntityComponent e : listEntitys) {
                    if (e instanceof History) {
                        History history = (History) e;
                        CongTrinh ct = null;
                        HangHoa hh_old = new HangHoa();
                        HangHoa hh_new = new HangHoa();
                        for (EntityComponent ec : listECongTrinh) {
                            CongTrinh c = (CongTrinh) ec;
                            if (c.getMacongtrinh().equals(history.getMacongtrinh())) {
                                ct = c;
                                break;
                            }
                        }
                        if (ct == null) {
                            continue;
                        }
                        for (EntityComponent eh : listEHangHoa) {
                            HangHoa h = (HangHoa) eh;
                            if (h.getMahanghoa().equals(history.getMahanghoacu())) {
                                hh_old = h;
                                continue;
                            }
                            if (h.getMahanghoa().equals(history.getMahanghoamoi())) {
                                hh_new = h;
                            }
                        }
                        datas[row][0] = history.getDatemodify();
                        datas[row][1] = history.getAction();
                        datas[row][2] = history.getType();
                        datas[row][3] = ct.getTencongtrinh();
                        datas[row][4] = CommonMethod.convertDateDbtoGui(history.getDateold());
                        datas[row][5] = CommonMethod.convertDateDbtoGui(history.getDatenew());
                        datas[row][6] = hh_old.getMahanghoa();
                        datas[row][7] = hh_new.getMahanghoa();
                        datas[row][8] = hh_old.getTenhanghoa();
                        datas[row][9] = hh_new.getTenhanghoa();
                        datas[row][10] = history.getKhoiluongcu();
                        datas[row][11] = history.getKhoiluongmoi();
                        row++;
                    }
                }
            } else if (frameName.equals(LOAIHANGHOA)) {
                numColumn = 2;
                columnNames = new Object[numColumn];
                columnNames[0] = "Mã Loại Vật Liệu";
                columnNames[1] = "Tên Loại Vật Liệu";
                datas = new Object[numRow][numColumn];
                int row = 0;
                for (EntityComponent e : listEntitys) {
                    if (e instanceof LoaiHangHoa) {
                        LoaiHangHoa lhh = (LoaiHangHoa) e;
                        datas[row][0] = lhh.getMaloaihanghoa();
                        datas[row][1] = lhh.getTenloaihanghoa();
                        row++;
                    }
                }
            } else if (frameName.equals(NGUOIBAN)) {
                numColumn = 5;
                columnNames = new Object[numColumn];
                columnNames[0] = "Mã Nhà Phân Phối";
                columnNames[1] = "Tên Nhà Phân Phối";
                columnNames[2] = "Địa Chỉ";
                columnNames[3] = "Email";
                columnNames[4] = "Số Điện Thoại";
                listFixSmallColumnIndex.add(0);
                datas = new Object[numRow][numColumn];
                int row = 0;
                for (EntityComponent e : listEntitys) {
                    if (e instanceof NguoiBan) {
                        NguoiBan nb = (NguoiBan) e;
                        datas[row][0] = nb.getManguoiban();
                        datas[row][1] = nb.getTennguoiban();
                        datas[row][2] = nb.getDiachi();
                        datas[row][3] = nb.getEmail();
                        datas[row][4] = nb.getSodienthoai();
                        row++;
                    }
                }
            } else if (frameName.equals(TAIKHOAN)) {
                numColumn = 7;
                columnNames = new Object[numColumn];
                columnNames[0] = "Id";
                columnNames[1] = "Tên Đăng Nhập";
                columnNames[2] = "Tên Chức Vụ";
                columnNames[3] = "Địa Chỉ";
                columnNames[4] = "Email";
                columnNames[5] = "Số Điện Thoại";
                columnNames[6] = "Role";
                listFixSmallColumnIndex.add(0);
                listNumberValue.add(0);
                datas = new Object[numRow][numColumn];
                int row = 0;
                List<EntityComponent> listEChucVu = ServiceDAOFactory.selectList(new ChucVu());
                for (EntityComponent e : listEntitys) {
                    if (e instanceof TaiKhoan) {
                        TaiKhoan tk = (TaiKhoan) e;
                        ChucVu cv = null;
                        for (EntityComponent ec : listEChucVu) {
                            ChucVu c = (ChucVu) ec;
                            if (c.getMachucvu().equals(tk.getMachucvu())) {
                                cv = c;
                                break;
                            }
                        }
                        if (cv == null) {
                            continue;
                        }
                        datas[row][0] = tk.getId();
                        datas[row][1] = tk.getUsername();
                        datas[row][2] = cv.getTenchucvu();
                        datas[row][3] = tk.getDiachi();
                        datas[row][4] = tk.getEmail();
                        datas[row][5] = tk.getSodienthoai();
                        datas[row][6] = Role.valueOf(tk.getRole()).getName();
                        row++;
                    }
                }
            } else if (frameName.equals(VATLIEUNHAP)) {
                numColumn = 7;
                columnNames = new Object[numColumn];
                columnNames[0] = "Id";
                columnNames[1] = "Ngày Nhập";
                columnNames[2] = "Tên Công Trình";
                columnNames[3] = "Mã Vật Liệu";
                columnNames[4] = "Tên Vật Liệu";
                columnNames[5] = "Tên Đơn Vị";
                columnNames[6] = "Khối Lượng";
                listFixSmallColumnIndex.add(0);
                listFixSmallColumnIndex.add(3);
                listFixSmallColumnIndex.add(4);
                listFixSmallColumnIndex.add(5);
                listFixSmallColumnIndex.add(6);
                listFixLargeColumnIndex.add(2);
                listNumberValue.add(0);
                listNumberValue.add(6);
                datas = new Object[numRow][numColumn];
                int row = 0;
                List<EntityComponent> listECongTrinh = ServiceDAOFactory.selectList(new CongTrinh());
                List<EntityComponent> listEHangHoa = ServiceDAOFactory.selectList(new HangHoa());
                List<EntityComponent> listEDonVi = ServiceDAOFactory.selectList(new DonVi());
                for (EntityComponent e : listEntitys) {
                    if (e instanceof VatLieuNhap) {
                        VatLieuNhap vln = (VatLieuNhap) e;
                        CongTrinh ct = null;
                        HangHoa hh = null;
                        DonVi dv = null;
                        for (EntityComponent ec : listECongTrinh) {
                            CongTrinh c = (CongTrinh) ec;
                            if (c.getMacongtrinh().equals(vln.getMacongtrinh())) {
                                ct = c;
                                break;
                            }
                        }
                        if (ct == null) {
                            continue;
                        }
                        for (EntityComponent eh : listEHangHoa) {
                            HangHoa h = (HangHoa) eh;
                            if (h.getMahanghoa().equals(vln.getMahanghoa())) {
                                hh = h;
                                break;
                            }
                        }
                        if (hh == null) {
                            continue;
                        }
                        for (EntityComponent ed : listEDonVi) {
                            DonVi d = (DonVi) ed;
                            if (d.getMadonvi().equals(hh.getMadonvi())) {
                                dv = d;
                                break;
                            }
                        }
                        if (dv == null) {
                            continue;
                        }
                        datas[row][0] = vln.getId();
                        datas[row][1] = CommonMethod.convertDateDbtoGui(vln.getNgaynhap());
                        datas[row][2] = ct.getTencongtrinh();
                        datas[row][3] = hh.getMahanghoa();
                        datas[row][4] = hh.getTenhanghoa();
                        datas[row][5] = dv.getTendonvi();
                        datas[row][6] = vln.getKhoiluong();
                        row++;
                    }
                }
            } else if (frameName.equals(VATLIEUTONKHO)) {
                numColumn = 3;
                columnNames = new Object[numColumn];
                columnNames[0] = "Tên Vật Liệu";
                columnNames[1] = "Tên Công Trình";
                columnNames[2] = "Khối Lượng Tồn";
                listNumberValue.add(2);
                datas = new Object[numRow][numColumn];
                int row = 0;
                List<EntityComponent> listECongTrinh = ServiceDAOFactory.selectList(new CongTrinh());
                List<EntityComponent> listEHangHoa = ServiceDAOFactory.selectList(new HangHoa());
                for (EntityComponent e : listEntitys) {
                    if (e instanceof VatLieuTonKho) {
                        VatLieuTonKho vltk = (VatLieuTonKho) e;
                        CongTrinh ct = null;
                        HangHoa hh = null;
                        for (EntityComponent ec : listECongTrinh) {
                            CongTrinh c = (CongTrinh) ec;
                            if (c.getMacongtrinh().equals(vltk.getMacongtrinh())) {
                                ct = c;
                                break;
                            }
                        }
                        if (ct == null) {
                            continue;
                        }
                        for (EntityComponent eh : listEHangHoa) {
                            HangHoa h = (HangHoa) eh;
                            if (h.getMahanghoa().equals(vltk.getMahanghoa())) {
                                hh = h;
                                break;
                            }
                        }
                        if (hh == null) {
                            continue;
                        }
                        datas[row][0] = hh.getTenhanghoa();
                        datas[row][1] = ct.getTencongtrinh();
                        datas[row][2] = vltk.getKhoiluongton();
                        row++;
                    }
                }
            } else if (frameName.equals(VATLIEUXUAT)) {
                numColumn = 7;
                columnNames = new Object[numColumn];
                columnNames[0] = "Id";
                columnNames[1] = "Ngày Xuất";
                columnNames[2] = "Tên Công Trình";
                columnNames[3] = "Mã Vật Liệu";
                columnNames[4] = "Tên Vật Liệu";
                columnNames[5] = "Tên Đơn Vị";
                columnNames[6] = "Khối Lượng";
                listFixSmallColumnIndex.add(0);
                listFixSmallColumnIndex.add(3);
                listFixSmallColumnIndex.add(4);
                listFixSmallColumnIndex.add(5);
                listFixSmallColumnIndex.add(6);
                listFixLargeColumnIndex.add(2);
                listNumberValue.add(0);
                listNumberValue.add(6);
                datas = new Object[numRow][numColumn];
                int row = 0;
                List<EntityComponent> listECongTrinh = ServiceDAOFactory.selectList(new CongTrinh());
                List<EntityComponent> listEHangHoa = ServiceDAOFactory.selectList(new HangHoa());
                List<EntityComponent> listEDonVi = ServiceDAOFactory.selectList(new DonVi());
                for (EntityComponent e : listEntitys) {
                    if (e instanceof VatLieuXuat) {
                        VatLieuXuat vlx = (VatLieuXuat) e;
                        CongTrinh ct = null;
                        HangHoa hh = null;
                        DonVi dv = null;
                        for (EntityComponent ec : listECongTrinh) {
                            CongTrinh c = (CongTrinh) ec;
                            if (c.getMacongtrinh().equals(vlx.getMacongtrinh())) {
                                ct = c;
                                break;
                            }
                        }
                        if (ct == null) {
                            continue;
                        }
                        for (EntityComponent eh : listEHangHoa) {
                            HangHoa h = (HangHoa) eh;
                            if (h.getMahanghoa().equals(vlx.getMahanghoa())) {
                                hh = h;
                                break;
                            }
                        }
                        if (hh == null) {
                            continue;
                        }
                        for (EntityComponent ed : listEDonVi) {
                            DonVi d = (DonVi) ed;
                            if (d.getMadonvi().equals(hh.getMadonvi())) {
                                dv = d;
                                break;
                            }
                        }
                        if (dv == null) {
                            continue;
                        }
                        datas[row][0] = vlx.getId();
                        datas[row][1] = CommonMethod.convertDateDbtoGui(vlx.getNgayxuat());
                        datas[row][2] = ct.getTencongtrinh();
                        datas[row][3] = hh.getMahanghoa();
                        datas[row][4] = hh.getTenhanghoa();
                        datas[row][5] = dv.getTendonvi();
                        datas[row][6] = vlx.getKhoiluong();
                        row++;
                    }
                }
            } else {
                numColumn = 1;
                numRow = 1;
                columnNames = new Object[numColumn];
                columnNames[0] = "Data";
                datas = new Object[numRow][numColumn];
                datas[0][0] = "No Data To Display!";
            }
        }
        listCurrentData.addAll(listEntitys);
        initDataTable(datas, columnNames);
        fixTableStyle(listFixSmallColumnIndex, listFixLargeColumnIndex);
    }

    private void initDataTable(Object[][] data, Object[] columnName) {
        this.column = new Object[columnName.length];
        this.data = data;
        String[] header = new String[columnName.length];
        for (int i = 0; i < header.length; i++) {
            header[i] = columnName[i].toString();
        }
        for (int i = 0; i < columnName.length; i++) {
            this.column[i] = columnName[i].toString();
            columnName[i] = "<html><div style=\"font-family:Segoe UI;font-size:10px;color:#101010;\">" + columnName[i] + "</div></html>";
        }
        TableModel tableModel = new DefaultTableModel(data, columnName) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.removeAll();
        table.setModel(tableModel);
    }

    private void fixTableStyle(Set<Integer> listFixSmallColumnIndex, Set<Integer> listFixLargeColumnIndex) {
        int normal_width = 150;
        int small_width = 100;
        int large_width = 200;
        int c = table.getColumnModel().getColumnCount();
        int real_width = (TABLE_WIDTH - small_width * listFixSmallColumnIndex.size() - large_width * listFixLargeColumnIndex.size()) / (c - listFixSmallColumnIndex.size() - listFixLargeColumnIndex.size());
        for (int i = 0; i < c; i++) {
            TableColumn tblColumn = table.getColumnModel().getColumn(i);
            if (c < 5) {
                tblColumn.setPreferredWidth((int) (TABLE_WIDTH / c));
            } else {
                if (listFixSmallColumnIndex.contains(i)) {
                    tblColumn.setPreferredWidth(small_width);
                } else if (listFixLargeColumnIndex.contains(i)) {
                    tblColumn.setPreferredWidth(large_width);
                } else {
                    tblColumn.setPreferredWidth(Math.max(normal_width, real_width));
                }
            }
        }
    }

    private void showErrorMessage(String mess, int messType) {
        ImageIcon imageicon;
        if (messType == JOptionPane.ERROR_MESSAGE) {
            imageicon = CommonMethod.getImageIcon("option_error");
        } else if (messType == JOptionPane.WARNING_MESSAGE) {
            imageicon = CommonMethod.getImageIcon("option_warning");
        } else {
            imageicon = CommonMethod.getImageIcon("option_info");
        }
        JOptionPane.showMessageDialog(this, mess, "Notification!", messType, imageicon);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu_tab = new javax.swing.JTabbedPane();
        tab_system = new javax.swing.JPanel();
        pn_tk = new PanelParent();
        lb_tk = new javax.swing.JLabel();
        pn_mk = new PanelParent();
        lb_mk = new javax.swing.JLabel();
        pn_dx = new PanelParent();
        lb_dx = new javax.swing.JLabel();
        lb_backup = new javax.swing.JLabel();
        tab_category = new javax.swing.JPanel();
        pn_cv = new PanelParent();
        lb_cv = new javax.swing.JLabel();
        pn_lhh = new PanelParent();
        lb_lhh = new javax.swing.JLabel();
        pn_nb = new PanelParent();
        lb_nb = new javax.swing.JLabel();
        pn_dv = new PanelParent();
        lb_dv = new javax.swing.JLabel();
        pn_hh = new PanelParent();
        lb_hh = new javax.swing.JLabel();
        tab_project = new javax.swing.JPanel();
        pn_vln = new PanelParent();
        lb_vln = new javax.swing.JLabel();
        pn_ct = new PanelParent();
        lb_ct = new javax.swing.JLabel();
        pn_vltk = new PanelParent();
        lb_vltk = new javax.swing.JLabel();
        pn_dt = new PanelParent();
        lb_dt = new javax.swing.JLabel();
        pn_history = new PanelParent();
        lb_history = new javax.swing.JLabel();
        pn_vlx = new PanelParent();
        lb_vlx = new javax.swing.JLabel();
        tab_help = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        mid_panel = new javax.swing.JPanel();
        scroll_table = new javax.swing.JScrollPane();
        table = new javax.swing.JTable(){
            private static final long serialVersionUID = 1L;

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (row % 2 == 0) {
                    if(isCellSelected(row, column)){
                        ((JComponent) c).setBackground(CommonDefine.TABLE_SELECTED_EVEN_COLOR);
                        ((JComponent) c).setForeground(CommonDefine.TABLE_SELECTED_EVEN_FCOLOR);
                    }else{
                        ((JComponent) c).setBackground(CommonDefine.TABLE_EVEN_COLOR);
                        ((JComponent) c).setForeground(CommonDefine.TABLE_EVEN_FCOLOR);
                    }
                } else {
                    if(isCellSelected(row, column)){
                        ((JComponent) c).setBackground(CommonDefine.TABLE_SELECTED_ODD_COLOR);
                        ((JComponent) c).setForeground(CommonDefine.TABLE_SELECTED_ODD_FCOLOR);
                    }else{
                        ((JComponent) c).setBackground(CommonDefine.TABLE_ODD_COLOR);
                        ((JComponent) c).setForeground(CommonDefine.TABLE_ODD_FCOLOR);
                    }
                }
                return c;
            }
        };
        left_panel = new javax.swing.JPanel();
        pn_action_top = new javax.swing.JPanel();
        pn_filter_string = new javax.swing.JPanel();
        tf_filter_string = new javax.swing.JTextField();
        pn_filter_number = new javax.swing.JPanel();
        tf_filter_number = new javax.swing.JTextField();
        pn_choose_filter = new javax.swing.JPanel();
        lb_filter_type = new javax.swing.JLabel();
        lb_help_filter = new javax.swing.JLabel();
        choice_filter_type = new javax.swing.JComboBox();
        pn_filter_date = new javax.swing.JPanel();
        date_filter_1 = new org.jdesktop.swingx.JXDatePicker();
        date_filter_2 = new org.jdesktop.swingx.JXDatePicker();
        pn_btn_filter = new javax.swing.JPanel();
        btn_filter = new javax.swing.JButton();
        pn_export_data = new javax.swing.JPanel();
        btn_export_data = new javax.swing.JButton();
        separator_export = new javax.swing.JSeparator();
        bot_panel = new javax.swing.JPanel();
        lb_user = new javax.swing.JLabel();
        pn_add = new PanelParent();
        lb_add = new javax.swing.JLabel();
        pn_update = new PanelParent();
        lb_update = new javax.swing.JLabel();
        pn_delete = new PanelParent();
        lb_delete = new javax.swing.JLabel();
        pn_refresh = new PanelParent();
        lb_refresh = new javax.swing.JLabel();
        pn_close = new PanelParent();
        lb_close = new javax.swing.JLabel();
        pn_mid = new javax.swing.JPanel();
        pn_mid_left_temp = new javax.swing.JPanel();
        pn_mid_ctname = new javax.swing.JPanel();
        lb_ctname = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);

        menu_tab.setBackground(new java.awt.Color(0, 102, 0));
        menu_tab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        tab_system.setBackground(new java.awt.Color(0, 102, 0));

        pn_tk.setBackground(new java.awt.Color(0, 102, 0));
        pn_tk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pn_tk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_tkMouseClicked(evt);
            }
        });

        lb_tk.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lb_tk.setForeground(new java.awt.Color(255, 255, 255));
        lb_tk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/taikhoan.png"))); // NOI18N
        lb_tk.setText("<html><div style='text-align:center;'>Quản Lý<br>Người Dùng</div></html>");

        javax.swing.GroupLayout pn_tkLayout = new javax.swing.GroupLayout(pn_tk);
        pn_tk.setLayout(pn_tkLayout);
        pn_tkLayout.setHorizontalGroup(
            pn_tkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_tk, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        );
        pn_tkLayout.setVerticalGroup(
            pn_tkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_tkLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lb_tk)
                .addContainerGap())
        );

        pn_mk.setBackground(new java.awt.Color(0, 102, 0));
        pn_mk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pn_mk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_mkMouseClicked(evt);
            }
        });

        lb_mk.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lb_mk.setForeground(new java.awt.Color(255, 255, 255));
        lb_mk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/doimatkhau.png"))); // NOI18N
        lb_mk.setText("<html><div style='text-align:center;'>Đổi<br>Mật Khẩu</div></html>");

        javax.swing.GroupLayout pn_mkLayout = new javax.swing.GroupLayout(pn_mk);
        pn_mk.setLayout(pn_mkLayout);
        pn_mkLayout.setHorizontalGroup(
            pn_mkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_mkLayout.createSequentialGroup()
                .addComponent(lb_mk, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pn_mkLayout.setVerticalGroup(
            pn_mkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_mkLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lb_mk)
                .addContainerGap())
        );

        pn_dx.setBackground(new java.awt.Color(0, 102, 0));
        pn_dx.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pn_dx.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_dxMouseClicked(evt);
            }
        });

        lb_dx.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lb_dx.setForeground(new java.awt.Color(255, 255, 255));
        lb_dx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dangxuat.png"))); // NOI18N
        lb_dx.setText("<html><div style='text-align:center;'>ĐăngXuất</div></html>");

        javax.swing.GroupLayout pn_dxLayout = new javax.swing.GroupLayout(pn_dx);
        pn_dx.setLayout(pn_dxLayout);
        pn_dxLayout.setHorizontalGroup(
            pn_dxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_dxLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lb_dx, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        pn_dxLayout.setVerticalGroup(
            pn_dxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_dxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_dx)
                .addContainerGap())
        );

        lb_backup.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lb_backup.setForeground(new java.awt.Color(255, 255, 255));
        lb_backup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backup.png"))); // NOI18N
        lb_backup.setText("Backup Database");
        lb_backup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lb_backup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_backupMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_backupMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_backupMouseExited(evt);
            }
        });

        javax.swing.GroupLayout tab_systemLayout = new javax.swing.GroupLayout(tab_system);
        tab_system.setLayout(tab_systemLayout);
        tab_systemLayout.setHorizontalGroup(
            tab_systemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_systemLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(pn_tk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pn_mk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pn_dx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 515, Short.MAX_VALUE)
                .addComponent(lb_backup)
                .addContainerGap())
        );
        tab_systemLayout.setVerticalGroup(
            tab_systemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_dx, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pn_mk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pn_tk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(tab_systemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_backup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        menu_tab.addTab("Hệ Thống", tab_system);

        tab_category.setBackground(new java.awt.Color(0, 102, 0));

        pn_cv.setBackground(new java.awt.Color(0, 102, 0));
        pn_cv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pn_cv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_cvMouseClicked(evt);
            }
        });

        lb_cv.setBackground(new java.awt.Color(255, 255, 255));
        lb_cv.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lb_cv.setForeground(new java.awt.Color(255, 255, 255));
        lb_cv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/chucvu.png"))); // NOI18N
        lb_cv.setText("<html><div style='text-align:center;'>Chức Vụ</div></html>");

        javax.swing.GroupLayout pn_cvLayout = new javax.swing.GroupLayout(pn_cv);
        pn_cv.setLayout(pn_cvLayout);
        pn_cvLayout.setHorizontalGroup(
            pn_cvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_cv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pn_cvLayout.setVerticalGroup(
            pn_cvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pn_cvLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_cv, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        pn_lhh.setBackground(new java.awt.Color(0, 102, 0));
        pn_lhh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pn_lhh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_lhhMouseClicked(evt);
            }
        });

        lb_lhh.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lb_lhh.setForeground(new java.awt.Color(255, 255, 255));
        lb_lhh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/loaivatlieu.png"))); // NOI18N
        lb_lhh.setText("<html><div style='text-align:center;'>Loại Vật Liệu</div></html>");

        javax.swing.GroupLayout pn_lhhLayout = new javax.swing.GroupLayout(pn_lhh);
        pn_lhh.setLayout(pn_lhhLayout);
        pn_lhhLayout.setHorizontalGroup(
            pn_lhhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_lhhLayout.createSequentialGroup()
                .addComponent(lb_lhh, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        pn_lhhLayout.setVerticalGroup(
            pn_lhhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_lhhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_lhh)
                .addContainerGap())
        );

        pn_nb.setBackground(new java.awt.Color(0, 102, 0));
        pn_nb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pn_nb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_nbMouseClicked(evt);
            }
        });

        lb_nb.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lb_nb.setForeground(new java.awt.Color(255, 255, 255));
        lb_nb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nhaphanphoi.png"))); // NOI18N
        lb_nb.setText("<html><div style='text-align:center;'>Nhà Phân Phối</div></html>");

        javax.swing.GroupLayout pn_nbLayout = new javax.swing.GroupLayout(pn_nb);
        pn_nb.setLayout(pn_nbLayout);
        pn_nbLayout.setHorizontalGroup(
            pn_nbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_nbLayout.createSequentialGroup()
                .addComponent(lb_nb, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        pn_nbLayout.setVerticalGroup(
            pn_nbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_nbLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_nb)
                .addContainerGap())
        );

        pn_dv.setBackground(new java.awt.Color(0, 102, 0));
        pn_dv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pn_dv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_dvMouseClicked(evt);
            }
        });

        lb_dv.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lb_dv.setForeground(new java.awt.Color(255, 255, 255));
        lb_dv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/donvi.png"))); // NOI18N
        lb_dv.setText("<html><div style='text-align:center;'>Đơn Vị</div></html>");

        javax.swing.GroupLayout pn_dvLayout = new javax.swing.GroupLayout(pn_dv);
        pn_dv.setLayout(pn_dvLayout);
        pn_dvLayout.setHorizontalGroup(
            pn_dvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_dv, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        );
        pn_dvLayout.setVerticalGroup(
            pn_dvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_dvLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_dv)
                .addContainerGap())
        );

        pn_hh.setBackground(new java.awt.Color(0, 102, 0));
        pn_hh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pn_hh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_hhMouseClicked(evt);
            }
        });

        lb_hh.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lb_hh.setForeground(new java.awt.Color(255, 255, 255));
        lb_hh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vatlieu.png"))); // NOI18N
        lb_hh.setText("<html><div style='text-align:center;'>Vật Liệu</div></html>");

        javax.swing.GroupLayout pn_hhLayout = new javax.swing.GroupLayout(pn_hh);
        pn_hh.setLayout(pn_hhLayout);
        pn_hhLayout.setHorizontalGroup(
            pn_hhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_hhLayout.createSequentialGroup()
                .addComponent(lb_hh, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        pn_hhLayout.setVerticalGroup(
            pn_hhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_hhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_hh)
                .addContainerGap())
        );

        javax.swing.GroupLayout tab_categoryLayout = new javax.swing.GroupLayout(tab_category);
        tab_category.setLayout(tab_categoryLayout);
        tab_categoryLayout.setHorizontalGroup(
            tab_categoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_categoryLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(pn_cv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pn_dv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pn_hh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pn_nb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pn_lhh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(364, Short.MAX_VALUE))
        );
        tab_categoryLayout.setVerticalGroup(
            tab_categoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_cv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pn_nb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pn_lhh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pn_dv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pn_hh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        menu_tab.addTab("Danh Mục", tab_category);

        tab_project.setBackground(new java.awt.Color(0, 102, 0));

        pn_vln.setBackground(new java.awt.Color(0, 102, 0));
        pn_vln.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pn_vln.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_vlnMouseClicked(evt);
            }
        });

        lb_vln.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lb_vln.setForeground(new java.awt.Color(255, 255, 255));
        lb_vln.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vatlieunhap.png"))); // NOI18N
        lb_vln.setText("<html><div style='text-align:center;'>Nhập<br>Vật Liệu</div></html>");

        javax.swing.GroupLayout pn_vlnLayout = new javax.swing.GroupLayout(pn_vln);
        pn_vln.setLayout(pn_vlnLayout);
        pn_vlnLayout.setHorizontalGroup(
            pn_vlnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_vlnLayout.createSequentialGroup()
                .addComponent(lb_vln, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        pn_vlnLayout.setVerticalGroup(
            pn_vlnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_vlnLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lb_vln, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pn_ct.setBackground(new java.awt.Color(0, 102, 0));
        pn_ct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pn_ct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_ctMouseClicked(evt);
            }
        });

        lb_ct.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lb_ct.setForeground(new java.awt.Color(255, 255, 255));
        lb_ct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/congtrinh.png"))); // NOI18N
        lb_ct.setText("<html><div style='text-align:center;'>Quản Lý<br>Công Trình</div></html>");

        javax.swing.GroupLayout pn_ctLayout = new javax.swing.GroupLayout(pn_ct);
        pn_ct.setLayout(pn_ctLayout);
        pn_ctLayout.setHorizontalGroup(
            pn_ctLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_ctLayout.createSequentialGroup()
                .addComponent(lb_ct, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        pn_ctLayout.setVerticalGroup(
            pn_ctLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_ctLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lb_ct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pn_vltk.setBackground(new java.awt.Color(0, 102, 0));
        pn_vltk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pn_vltk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_vltkMouseClicked(evt);
            }
        });

        lb_vltk.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lb_vltk.setForeground(new java.awt.Color(255, 255, 255));
        lb_vltk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vatlieutonkho.png"))); // NOI18N
        lb_vltk.setText("<html><div style='text-align:center;'>Vật Liệu<br>Tồn Kho</div></html>");

        javax.swing.GroupLayout pn_vltkLayout = new javax.swing.GroupLayout(pn_vltk);
        pn_vltk.setLayout(pn_vltkLayout);
        pn_vltkLayout.setHorizontalGroup(
            pn_vltkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_vltkLayout.createSequentialGroup()
                .addComponent(lb_vltk, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        pn_vltkLayout.setVerticalGroup(
            pn_vltkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_vltkLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lb_vltk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pn_dt.setBackground(new java.awt.Color(0, 102, 0));
        pn_dt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pn_dt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_dtMouseClicked(evt);
            }
        });

        lb_dt.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lb_dt.setForeground(new java.awt.Color(255, 255, 255));
        lb_dt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dutoan.png"))); // NOI18N
        lb_dt.setText("<html><div style='text-align:center;'>Dự Toán</div></html>");

        javax.swing.GroupLayout pn_dtLayout = new javax.swing.GroupLayout(pn_dt);
        pn_dt.setLayout(pn_dtLayout);
        pn_dtLayout.setHorizontalGroup(
            pn_dtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_dtLayout.createSequentialGroup()
                .addComponent(lb_dt, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        pn_dtLayout.setVerticalGroup(
            pn_dtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_dtLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lb_dt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pn_history.setBackground(new java.awt.Color(0, 102, 0));
        pn_history.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pn_history.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_historyMouseClicked(evt);
            }
        });

        lb_history.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lb_history.setForeground(new java.awt.Color(255, 255, 255));
        lb_history.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/history.png"))); // NOI18N
        lb_history.setText("<html><div style='text-align:center;'>Lịch Sử<br>Nhập Xuất</div></html>");

        javax.swing.GroupLayout pn_historyLayout = new javax.swing.GroupLayout(pn_history);
        pn_history.setLayout(pn_historyLayout);
        pn_historyLayout.setHorizontalGroup(
            pn_historyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_historyLayout.createSequentialGroup()
                .addComponent(lb_history, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        pn_historyLayout.setVerticalGroup(
            pn_historyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_historyLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lb_history, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pn_vlx.setBackground(new java.awt.Color(0, 102, 0));
        pn_vlx.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pn_vlx.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_vlxMouseClicked(evt);
            }
        });

        lb_vlx.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lb_vlx.setForeground(new java.awt.Color(255, 255, 255));
        lb_vlx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vatlieuxuat.png"))); // NOI18N
        lb_vlx.setText("<html><div style='text-align:center;'>Xuất<br>Vật Liệu</div></html>");

        javax.swing.GroupLayout pn_vlxLayout = new javax.swing.GroupLayout(pn_vlx);
        pn_vlx.setLayout(pn_vlxLayout);
        pn_vlxLayout.setHorizontalGroup(
            pn_vlxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_vlxLayout.createSequentialGroup()
                .addComponent(lb_vlx, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        pn_vlxLayout.setVerticalGroup(
            pn_vlxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_vlxLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lb_vlx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tab_projectLayout = new javax.swing.GroupLayout(tab_project);
        tab_project.setLayout(tab_projectLayout);
        tab_projectLayout.setHorizontalGroup(
            tab_projectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_projectLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(pn_ct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pn_vln, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pn_vlx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pn_vltk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pn_dt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pn_history, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(228, Short.MAX_VALUE))
        );
        tab_projectLayout.setVerticalGroup(
            tab_projectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_projectLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(tab_projectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pn_history, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tab_projectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(pn_vltk, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pn_vln, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pn_vlx, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pn_ct, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pn_dt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        menu_tab.addTab("Công Trình", tab_project);

        tab_help.setBackground(new java.awt.Color(0, 102, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("This function not avaiable!");

        javax.swing.GroupLayout tab_helpLayout = new javax.swing.GroupLayout(tab_help);
        tab_help.setLayout(tab_helpLayout);
        tab_helpLayout.setHorizontalGroup(
            tab_helpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_helpLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1044, Short.MAX_VALUE)
                .addContainerGap())
        );
        tab_helpLayout.setVerticalGroup(
            tab_helpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_helpLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        menu_tab.addTab("Trợ Giúp", tab_help);

        mid_panel.setBackground(new java.awt.Color(255, 255, 255));

        scroll_table.setBackground(new java.awt.Color(255, 255, 255));
        scroll_table.setBorder(null);

        table.setAutoCreateRowSorter(true);
        table.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        table.setGridColor(new java.awt.Color(230, 230, 230));
        table.setIntercellSpacing(new java.awt.Dimension(5, 5));
        table.setRowHeight(24);
        table.setSelectionBackground(new java.awt.Color(0, 153, 0));
        table.setSelectionForeground(new java.awt.Color(51, 51, 51));
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        scroll_table.setViewportView(table);

        left_panel.setBackground(new java.awt.Color(0, 102, 0));
        left_panel.setMinimumSize(new java.awt.Dimension(204, 472));

        pn_action_top.setBackground(new java.awt.Color(0, 102, 0));
        pn_action_top.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pn_filter_string.setBackground(new java.awt.Color(0, 102, 0));

        tf_filter_string.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        tf_filter_string.setBorder(null);

        javax.swing.GroupLayout pn_filter_stringLayout = new javax.swing.GroupLayout(pn_filter_string);
        pn_filter_string.setLayout(pn_filter_stringLayout);
        pn_filter_stringLayout.setHorizontalGroup(
            pn_filter_stringLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_filter_stringLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tf_filter_string)
                .addContainerGap())
        );
        pn_filter_stringLayout.setVerticalGroup(
            pn_filter_stringLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_filter_stringLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(tf_filter_string, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pn_filter_number.setBackground(new java.awt.Color(0, 102, 0));

        tf_filter_number.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_filter_number.setBorder(null);

        javax.swing.GroupLayout pn_filter_numberLayout = new javax.swing.GroupLayout(pn_filter_number);
        pn_filter_number.setLayout(pn_filter_numberLayout);
        pn_filter_numberLayout.setHorizontalGroup(
            pn_filter_numberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_filter_numberLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tf_filter_number)
                .addContainerGap())
        );
        pn_filter_numberLayout.setVerticalGroup(
            pn_filter_numberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_filter_numberLayout.createSequentialGroup()
                .addComponent(tf_filter_number, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pn_choose_filter.setBackground(new java.awt.Color(0, 102, 0));

        lb_filter_type.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        lb_filter_type.setForeground(new java.awt.Color(255, 255, 255));
        lb_filter_type.setText("Tìm kiếm theo:");

        lb_help_filter.setBackground(new java.awt.Color(0, 102, 0));
        lb_help_filter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_help_filter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help_filter.png"))); // NOI18N
        lb_help_filter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lb_help_filter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_help_filterMouseClicked(evt);
            }
        });

        choice_filter_type.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        choice_filter_type.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                choice_filter_typeItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pn_choose_filterLayout = new javax.swing.GroupLayout(pn_choose_filter);
        pn_choose_filter.setLayout(pn_choose_filterLayout);
        pn_choose_filterLayout.setHorizontalGroup(
            pn_choose_filterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_choose_filterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_choose_filterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(choice_filter_type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pn_choose_filterLayout.createSequentialGroup()
                        .addComponent(lb_filter_type, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_help_filter, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pn_choose_filterLayout.setVerticalGroup(
            pn_choose_filterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_choose_filterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_choose_filterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lb_filter_type, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pn_choose_filterLayout.createSequentialGroup()
                        .addComponent(lb_help_filter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(choice_filter_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pn_filter_date.setBackground(new java.awt.Color(0, 102, 0));

        javax.swing.GroupLayout pn_filter_dateLayout = new javax.swing.GroupLayout(pn_filter_date);
        pn_filter_date.setLayout(pn_filter_dateLayout);
        pn_filter_dateLayout.setHorizontalGroup(
            pn_filter_dateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_filter_dateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_filter_dateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(date_filter_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(date_filter_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pn_filter_dateLayout.setVerticalGroup(
            pn_filter_dateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_filter_dateLayout.createSequentialGroup()
                .addComponent(date_filter_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date_filter_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pn_btn_filter.setBackground(new java.awt.Color(0, 102, 0));

        btn_filter.setBackground(new java.awt.Color(255, 255, 255));
        btn_filter.setText("Tìm kiếm");
        btn_filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_filterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_btn_filterLayout = new javax.swing.GroupLayout(pn_btn_filter);
        pn_btn_filter.setLayout(pn_btn_filterLayout);
        pn_btn_filterLayout.setHorizontalGroup(
            pn_btn_filterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_btn_filterLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btn_filter, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pn_btn_filterLayout.setVerticalGroup(
            pn_btn_filterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_btn_filterLayout.createSequentialGroup()
                .addComponent(btn_filter)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pn_export_data.setBackground(new java.awt.Color(0, 102, 0));

        btn_export_data.setBackground(new java.awt.Color(255, 255, 255));
        btn_export_data.setText("Xuất dữ liệu");
        btn_export_data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_export_dataActionPerformed(evt);
            }
        });

        separator_export.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pn_export_dataLayout = new javax.swing.GroupLayout(pn_export_data);
        pn_export_data.setLayout(pn_export_dataLayout);
        pn_export_dataLayout.setHorizontalGroup(
            pn_export_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(separator_export)
            .addGroup(pn_export_dataLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btn_export_data, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pn_export_dataLayout.setVerticalGroup(
            pn_export_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_export_dataLayout.createSequentialGroup()
                .addComponent(separator_export, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btn_export_data)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pn_action_topLayout = new javax.swing.GroupLayout(pn_action_top);
        pn_action_top.setLayout(pn_action_topLayout);
        pn_action_topLayout.setHorizontalGroup(
            pn_action_topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_filter_string, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pn_filter_number, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pn_choose_filter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pn_filter_date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pn_action_topLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_action_topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pn_btn_filter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pn_export_data, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pn_action_topLayout.setVerticalGroup(
            pn_action_topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_action_topLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pn_choose_filter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pn_filter_string, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pn_filter_number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pn_filter_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pn_btn_filter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pn_export_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout left_panelLayout = new javax.swing.GroupLayout(left_panel);
        left_panel.setLayout(left_panelLayout);
        left_panelLayout.setHorizontalGroup(
            left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_action_top, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        left_panelLayout.setVerticalGroup(
            left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(left_panelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(pn_action_top, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mid_panelLayout = new javax.swing.GroupLayout(mid_panel);
        mid_panel.setLayout(mid_panelLayout);
        mid_panelLayout.setHorizontalGroup(
            mid_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mid_panelLayout.createSequentialGroup()
                .addComponent(left_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(scroll_table, javax.swing.GroupLayout.PREFERRED_SIZE, 854, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        mid_panelLayout.setVerticalGroup(
            mid_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll_table, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
            .addComponent(left_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        bot_panel.setBackground(new java.awt.Color(0, 102, 0));

        lb_user.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lb_user.setForeground(new java.awt.Color(255, 255, 255));
        lb_user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/info.png"))); // NOI18N
        lb_user.setText("User Infomartion");

        pn_add.setBackground(new java.awt.Color(0, 102, 0));
        pn_add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pn_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_addMouseClicked(evt);
            }
        });

        lb_add.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lb_add.setForeground(new java.awt.Color(255, 255, 255));
        lb_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/them.png"))); // NOI18N
        lb_add.setText("Thêm");

        javax.swing.GroupLayout pn_addLayout = new javax.swing.GroupLayout(pn_add);
        pn_add.setLayout(pn_addLayout);
        pn_addLayout.setHorizontalGroup(
            pn_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_addLayout.createSequentialGroup()
                .addComponent(lb_add, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pn_addLayout.setVerticalGroup(
            pn_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pn_update.setBackground(new java.awt.Color(0, 102, 0));
        pn_update.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pn_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_updateMouseClicked(evt);
            }
        });

        lb_update.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lb_update.setForeground(new java.awt.Color(255, 255, 255));
        lb_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/capnhat.png"))); // NOI18N
        lb_update.setText("Sửa");

        javax.swing.GroupLayout pn_updateLayout = new javax.swing.GroupLayout(pn_update);
        pn_update.setLayout(pn_updateLayout);
        pn_updateLayout.setHorizontalGroup(
            pn_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_updateLayout.createSequentialGroup()
                .addComponent(lb_update, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pn_updateLayout.setVerticalGroup(
            pn_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pn_delete.setBackground(new java.awt.Color(0, 102, 0));
        pn_delete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pn_delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_deleteMouseClicked(evt);
            }
        });

        lb_delete.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lb_delete.setForeground(new java.awt.Color(255, 255, 255));
        lb_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/xoa.png"))); // NOI18N
        lb_delete.setText("Xóa");

        javax.swing.GroupLayout pn_deleteLayout = new javax.swing.GroupLayout(pn_delete);
        pn_delete.setLayout(pn_deleteLayout);
        pn_deleteLayout.setHorizontalGroup(
            pn_deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_deleteLayout.createSequentialGroup()
                .addComponent(lb_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pn_deleteLayout.setVerticalGroup(
            pn_deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pn_refresh.setBackground(new java.awt.Color(0, 102, 0));
        pn_refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pn_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_refreshMouseClicked(evt);
            }
        });

        lb_refresh.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lb_refresh.setForeground(new java.awt.Color(255, 255, 255));
        lb_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lammoi.png"))); // NOI18N
        lb_refresh.setText("Làm mới");

        javax.swing.GroupLayout pn_refreshLayout = new javax.swing.GroupLayout(pn_refresh);
        pn_refresh.setLayout(pn_refreshLayout);
        pn_refreshLayout.setHorizontalGroup(
            pn_refreshLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_refreshLayout.createSequentialGroup()
                .addComponent(lb_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pn_refreshLayout.setVerticalGroup(
            pn_refreshLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_refresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pn_close.setBackground(new java.awt.Color(0, 102, 0));
        pn_close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pn_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_closeMouseClicked(evt);
            }
        });

        lb_close.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lb_close.setForeground(new java.awt.Color(255, 255, 255));
        lb_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dong.png"))); // NOI18N
        lb_close.setText("Đóng");

        javax.swing.GroupLayout pn_closeLayout = new javax.swing.GroupLayout(pn_close);
        pn_close.setLayout(pn_closeLayout);
        pn_closeLayout.setHorizontalGroup(
            pn_closeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_closeLayout.createSequentialGroup()
                .addComponent(lb_close, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pn_closeLayout.setVerticalGroup(
            pn_closeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_close, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout bot_panelLayout = new javax.swing.GroupLayout(bot_panel);
        bot_panel.setLayout(bot_panelLayout);
        bot_panelLayout.setHorizontalGroup(
            bot_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bot_panelLayout.createSequentialGroup()
                .addComponent(lb_user, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(297, 297, 297)
                .addComponent(pn_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pn_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pn_close, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        bot_panelLayout.setVerticalGroup(
            bot_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_user, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
            .addComponent(pn_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pn_update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pn_delete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pn_refresh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pn_close, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pn_mid.setBackground(new java.awt.Color(255, 255, 255));

        pn_mid_left_temp.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pn_mid_left_tempLayout = new javax.swing.GroupLayout(pn_mid_left_temp);
        pn_mid_left_temp.setLayout(pn_mid_left_tempLayout);
        pn_mid_left_tempLayout.setHorizontalGroup(
            pn_mid_left_tempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );
        pn_mid_left_tempLayout.setVerticalGroup(
            pn_mid_left_tempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pn_mid_ctname.setBackground(new java.awt.Color(255, 255, 255));

        lb_ctname.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        lb_ctname.setForeground(new java.awt.Color(0, 102, 0));
        lb_ctname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pn_mid_ctnameLayout = new javax.swing.GroupLayout(pn_mid_ctname);
        pn_mid_ctname.setLayout(pn_mid_ctnameLayout);
        pn_mid_ctnameLayout.setHorizontalGroup(
            pn_mid_ctnameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_mid_ctnameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_ctname, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE)
                .addContainerGap())
        );
        pn_mid_ctnameLayout.setVerticalGroup(
            pn_mid_ctnameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_ctname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pn_midLayout = new javax.swing.GroupLayout(pn_mid);
        pn_mid.setLayout(pn_midLayout);
        pn_midLayout.setHorizontalGroup(
            pn_midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_midLayout.createSequentialGroup()
                .addComponent(pn_mid_left_temp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_mid_ctname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        pn_midLayout.setVerticalGroup(
            pn_midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_mid_ctname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pn_mid_left_temp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mid_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bot_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menu_tab, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(pn_mid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu_tab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pn_mid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mid_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(bot_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pn_tkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_tkMouseClicked
        if (!pn_tk.isEnabled()) {
            return;
        }
        removeHighlightSystem();
        pn_tk.setEnabled(false);
        pn_tk.setBackground(CommonDefine.BUTTON_HIGHLIGHT_COLOR);
        frameName = TAIKHOAN;
        onClickButton();
    }//GEN-LAST:event_pn_tkMouseClicked

    private void pn_cvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_cvMouseClicked
        if (!pn_cv.isEnabled()) {
            return;
        }
        removeHighlightSystem();
        pn_cv.setEnabled(false);
        pn_cv.setBackground(CommonDefine.BUTTON_HIGHLIGHT_COLOR);
        frameName = CHUCVU;
        onClickButton();
    }//GEN-LAST:event_pn_cvMouseClicked

    private void pn_dvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_dvMouseClicked
        if (!pn_dv.isEnabled()) {
            return;
        }
        removeHighlightSystem();
        pn_dv.setEnabled(false);
        pn_dv.setBackground(CommonDefine.BUTTON_HIGHLIGHT_COLOR);
        frameName = DONVI;
        onClickButton();
    }//GEN-LAST:event_pn_dvMouseClicked

    private void pn_hhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_hhMouseClicked
        if (!pn_hh.isEnabled()) {
            return;
        }
        removeHighlightSystem();
        pn_hh.setEnabled(false);
        pn_hh.setBackground(CommonDefine.BUTTON_HIGHLIGHT_COLOR);
        frameName = HANGHOA;
        onClickButton();
    }//GEN-LAST:event_pn_hhMouseClicked

    private void pn_nbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_nbMouseClicked
        if (!pn_nb.isEnabled()) {
            return;
        }
        removeHighlightSystem();
        pn_nb.setEnabled(false);
        pn_nb.setBackground(CommonDefine.BUTTON_HIGHLIGHT_COLOR);
        frameName = NGUOIBAN;
        onClickButton();
    }//GEN-LAST:event_pn_nbMouseClicked

    private void pn_lhhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_lhhMouseClicked
        if (!pn_lhh.isEnabled()) {
            return;
        }
        removeHighlightSystem();
        pn_lhh.setEnabled(false);
        pn_lhh.setBackground(CommonDefine.BUTTON_HIGHLIGHT_COLOR);
        frameName = LOAIHANGHOA;
        onClickButton();
    }//GEN-LAST:event_pn_lhhMouseClicked

    private void pn_ctMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_ctMouseClicked
        if (!pn_ct.isEnabled()) {
            return;
        }
        removeHighlightSystem();
        pn_ct.setEnabled(false);
        pn_ct.setBackground(CommonDefine.BUTTON_HIGHLIGHT_COLOR);
        frameName = CONGTRINH;
        onClickButton();
    }//GEN-LAST:event_pn_ctMouseClicked

    private void pn_vlnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_vlnMouseClicked
        if (!pn_vln.isEnabled()) {
            return;
        }
        removeHighlightSystem();
        pn_vln.setEnabled(false);
        pn_vln.setBackground(CommonDefine.BUTTON_HIGHLIGHT_COLOR);
        frameName = VATLIEUNHAP;
        onClickButton();
    }//GEN-LAST:event_pn_vlnMouseClicked

    private void pn_vlxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_vlxMouseClicked
        if (!pn_vlx.isEnabled()) {
            return;
        }
        removeHighlightSystem();
        pn_vlx.setEnabled(false);
        pn_vlx.setBackground(CommonDefine.BUTTON_HIGHLIGHT_COLOR);
        frameName = VATLIEUXUAT;
        onClickButton();
    }//GEN-LAST:event_pn_vlxMouseClicked

    private void pn_vltkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_vltkMouseClicked
        if (!pn_vltk.isEnabled()) {
            return;
        }
        removeHighlightSystem();
        pn_vltk.setEnabled(false);
        pn_vltk.setBackground(CommonDefine.BUTTON_HIGHLIGHT_COLOR);
        frameName = VATLIEUTONKHO;
        onClickButton();
    }//GEN-LAST:event_pn_vltkMouseClicked

    private void pn_dtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_dtMouseClicked
        if (!pn_dt.isEnabled()) {
            return;
        }
        removeHighlightSystem();
        pn_dt.setEnabled(false);
        pn_dt.setBackground(CommonDefine.BUTTON_HIGHLIGHT_COLOR);
        frameName = DUTOAN;
        onClickButton();
    }//GEN-LAST:event_pn_dtMouseClicked

    private void pn_historyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_historyMouseClicked
        if (!pn_history.isEnabled()) {
            return;
        }
        removeHighlightSystem();
        pn_history.setEnabled(false);
        pn_history.setBackground(CommonDefine.BUTTON_HIGHLIGHT_COLOR);
        frameName = HISTORY;
        onClickButton();
    }//GEN-LAST:event_pn_historyMouseClicked

    private void pn_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_addMouseClicked
        if (frameName != null) {
            ElementGui elem = getElementGUI(frameName);
            if (elem != null) {
                if (congtrinh != null) {
                    elem.initData(congtrinh);
                }
                elem.setVisible(true);
            }
        }
    }//GEN-LAST:event_pn_addMouseClicked

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        if (SwingUtilities.isRightMouseButton(evt)) {
            selectedIndex = -1;
            table.clearSelection();
            return;
        }
        if (frameName == null) {
            return;
        }
        selectedIndex = table.getSelectedRow();
        if (evt.getClickCount() == 2 && frameName.equals(CONGTRINH)) {
            if (pn_ct.isVisible()) {
                EntityComponent entity = getSelectedEntityTable();
                if (entity != null && entity instanceof CongTrinh) {
                    congtrinh = (CongTrinh) entity;
                    reloadDataTable(congtrinh);
                    showButton(true);
                    pn_add.setVisible(false);
                    pn_update.setVisible(false);
                    pn_delete.setVisible(false);
                    pn_refresh.setVisible(false);
                    pn_export_data.setVisible(false);
                    //hiển thị tên công trình
                    StringBuilder sb = new StringBuilder();
                    sb.append("<html><div style=\"width:560px;text-align:center;padding:4px;\"><span>");
                    sb.append("Quản lý công trình: ");
                    sb.append(congtrinh.getTencongtrinh());
                    sb.append("</span></div></html>");
                    lb_ctname.setText(sb.toString());
                }
            }
        }
    }//GEN-LAST:event_tableMouseClicked

    private void pn_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_updateMouseClicked
        EntityComponent entity = getSelectedEntityTable();
        if (entity != null) {
            ElementGui elem = getElementGUI(frameName);
            if (elem != null) {
                elem.initData(entity);
                elem.setVisible(true);
            }
        } else {
            showErrorMessage("Vui lòng chọn thông tin để cập nhật!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_pn_updateMouseClicked

    private void pn_deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_deleteMouseClicked
        EntityComponent entity = getSelectedEntityTable();
        if (checkDeleteData(entity)) {
            int choose = JOptionPane.showConfirmDialog(this, "Xác nhận xóa bản ghi này?", "Xóa!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, CommonMethod.getImageIcon("option_delete"));
            if (choose == JOptionPane.YES_OPTION) {
                if (frameName.equals(TAIKHOAN)) {
                    if (entity instanceof TaiKhoan) {
                        TaiKhoan tk = (TaiKhoan) entity;
                        tk.setDeleted(true);
                        if (ServiceDAOFactory.insertOrUpdate(tk)) {
                            reloadDataTable();
                        } else {
                            showErrorMessage("Xóa thông tin thất bại, hãy kiểm tra lại dữ liệu của bạn!", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    if (ServiceDAOFactory.delete(entity)) {
                        updateDeleteEntity(entity);
                        reloadDataTable();
                    } else {
                        showErrorMessage("Xóa thông tin thất bại, hãy kiểm tra lại dữ liệu của bạn!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }//GEN-LAST:event_pn_deleteMouseClicked

    private void pn_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_refreshMouseClicked
        if (frameName == null) {
            return;
        }
//        if (frameName.equals(CONGTRINH) && congtrinh != null) {
//            reloadDataTable(congtrinh);
//            return;
//        }
//        EntityComponent entity = getEntity(frameName);
//        //set some filter
//        reloadDataTable(entity);
        onClickButton();
    }//GEN-LAST:event_pn_refreshMouseClicked

    private void pn_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_closeMouseClicked
        table.clearSelection();
        removeHighlightSystem();
        congtrinh = null;
        TableModel tableModel = new DefaultTableModel(new Object[][]{}, new Object[]{});
        table.setModel(tableModel);
        frameName = null;
        toggleConstructionsButton(false);
        hideAllFilterAction();
    }//GEN-LAST:event_pn_closeMouseClicked

    private void pn_dxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_dxMouseClicked
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_pn_dxMouseClicked

    private void pn_mkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_mkMouseClicked
        TaiKhoan tk = new TaiKhoan();
        tk.setId(id_taikhoan);
        EntityComponent entity = ServiceDAOFactory.select(tk);
        if (entity == null) {
            showErrorMessage("Tài khoản không tồn tại!", JOptionPane.ERROR_MESSAGE);
            closeApp();
        }
        ChangePassFrame changpass = new ChangePassFrame();
        changpass.initData(entity);
        changpass.setVisible(true);
    }//GEN-LAST:event_pn_mkMouseClicked

    private void btn_filterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_filterActionPerformed
        if (isValidFilter()) {
            int index = choice_filter_type.getSelectedIndex();
            if (index >= 0 && index < listFilter.size()) {
                FilterElem fe = listFilter.get(index);
                EntityComponent entity = getEntity(frameName);
                if (entity == null) {
                    showErrorMessage("Đã có lỗi xảy ra, không thể xác định dữ liệu!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (fe.getType() == FilterType.STRING) {
                    String value = tf_filter_string.getText();
                    if (value == null || value.trim().isEmpty()) {
                        showErrorMessage("Bạn cần nhập chuỗi cần tìm trước!", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("FROM ").append(entity.getClassName()).append(" WHERE macongtrinh='").append(congtrinh.getMacongtrinh()).append("' AND ");
                    if (value.contains("%")) {
                        sb.append(fe.getDbName()).append(" LIKE '").append(value.trim()).append("'");
                    } else {
                        sb.append(fe.getDbName()).append(" = '").append(value.trim()).append("'");
                    }
                    entity.setSelectQuery(sb.toString());
                    reloadDataTable(entity);
                } else if (fe.getType() == FilterType.NUMBER) {
                    String value = tf_filter_number.getText();
                    if (value == null || value.trim().isEmpty()) {
                        showErrorMessage("Bạn cần nhập giá trị cần tìm trước!", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if (!value.matches(CommonDefine.FILTER_NUMBER_PATTERN_IN) && !value.matches(CommonDefine.FILTER_NUMBER_PATTERN_BETWEEN) && !CommonMethod.isNumberic(value)) {
                        showErrorMessage("Nhập sai định dạng, không thể tìm kiếm!", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("FROM ").append(entity.getClassName()).append(" WHERE macongtrinh='").append(congtrinh.getMacongtrinh()).append("' AND ");
                    if (CommonMethod.isNumberic(value)) {
                        sb.append(fe.getDbName()).append(" = ").append(value.trim()).append("");
                        entity.setSelectQuery(sb.toString());
                        reloadDataTable(entity);
                    } else if (value.matches(CommonDefine.FILTER_NUMBER_PATTERN_BETWEEN)) {
                        sb.append(fe.getDbName()).append(" >= ").append(value.split("-")[0]).append(" AND ");
                        sb.append(fe.getDbName()).append(" <= ").append(value.split("-")[1]);
                        entity.setSelectQuery(sb.toString());
                        reloadDataTable(entity);
                    } else {
                        sb.append(fe.getDbName()).append(" IN ").append("(").append(value).append(")");
                        entity.setSelectQuery(sb.toString());
                        reloadDataTable(entity);
                    }
                } else if (fe.getType() == FilterType.DATE) {
                    Date start = date_filter_1.getDate();
                    Date end = date_filter_2.getDate();
                    if (start == null && end == null) {
                        showErrorMessage("Bạn cần nhập ngày cần tìm trước!", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("FROM ").append(entity.getClassName()).append(" WHERE macongtrinh='").append(congtrinh.getMacongtrinh()).append("' AND ");
                    if (start == null || end == null) {
                        String date_search = (start == null) ? CommonMethod.getFormatDateStringDB(end) : CommonMethod.getFormatDateStringDB(start);
                        sb.append(fe.getDbName()).append(" = '").append(date_search).append("'");
                        entity.setSelectQuery(sb.toString());
                        reloadDataTable(entity);
                    } else {
                        if (start.after(end)) {
                            showErrorMessage("Ngày bắt đầu tìm kiếm không thể sau ngày kết thúc!", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        sb.append(fe.getDbName()).append(" >= '").append(CommonMethod.getFormatDateStringDB(start)).append("' AND ");
                        sb.append(fe.getDbName()).append(" <= '").append(CommonMethod.getFormatDateStringDB(end)).append("'");
                        entity.setSelectQuery(sb.toString());
                        reloadDataTable(entity);
                    }
                }
            }
        }
    }//GEN-LAST:event_btn_filterActionPerformed

    private void lb_help_filterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_help_filterMouseClicked
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>");
        sb.append("<di style=\"width:300px;\">");
        sb.append("Chức năng này cho phép tìm kiếm dữ liệu theo một chỉ số nhất định.");
        sb.append("<br>Chọn một trong các tùy chọn tại <i>Tìm kiếm theo:</i> rồi tìm kiếm.");
        sb.append("<ul>");
        sb.append("<li>");
        sb.append("<b>").append("Tìm kiếm theo chuỗi").append("</b>");
        sb.append("<p>");
        sb.append("<i>'Tên'='warehouse'</i>: Tìm kiếm tất cả những dữ liệu có giá trị 'Tên' bằng 'warehouse'.");
        sb.append("</p>");
        sb.append("<p>");
        sb.append("<i>'Tên'='warehouse'</i>: Tìm kiếm tất cả những dữ liệu có giá trị 'Tên' kết thúc 'warehouse'.");
        sb.append("</p>");
        sb.append("<p>");
        sb.append("<i>'Tên'='warehouse'</i>: Tìm kiếm tất cả những dữ liệu có giá trị 'Tên' bắt đầu 'warehouse'.");
        sb.append("</p>");
        sb.append("<p>");
        sb.append("<i>'Tên'='warehouse'</i>: Tìm kiếm tất cả những dữ liệu có giá trị 'Tên' chứa 'warehouse'.");
        sb.append("</p>");
        sb.append("</li>");

        sb.append("<li>");
        sb.append("<b>").append("Tìm kiếm theo số lượng").append("</b>");
        sb.append("<p>");
        sb.append("<i>'Khối lượng'=10</i>: Tìm kiếm tất cả những dữ liệu có giá trị 'Khối lượng' bằng 10.");
        sb.append("</p>");
        sb.append("<p>");
        sb.append("<i>'Khối lượng'=10-50</i>: Tìm kiếm tất cả những dữ liệu có giá trị 'Khối lượng' từ 10 đến 50.");
        sb.append("</p>");
        sb.append("<p>");
        sb.append("<i>'Khối lượng'=10,11</i>: Tìm kiếm tất cả những dữ liệu có giá trị 'Khối lượng' bằng 10 hoặc 11.");
        sb.append("</p>");
        sb.append("</li>");

        sb.append("<li>");
        sb.append("<b>").append("Tìm kiếm theo thời gian").append("</b>");
        sb.append("<p>");
        sb.append("<i>Nhập thời gian X vào ô đầu tiên và thời gian Y vào ô thứ 2 (X < Y):</i>");
        sb.append("<br>");
        sb.append("Tìm kiếm trong khoảng thời gian X và Y, nếu X hoặc Y bỏ trống sẽ tìm kiếm chính xác bằng X hoặc Y.");
        sb.append("</p>");
        sb.append("</li>");

        sb.append("</ul>");
        sb.append("</div>");
        sb.append("</body></html>");
        JOptionPane.showMessageDialog(this, sb.toString(), "Help", JOptionPane.INFORMATION_MESSAGE, CommonMethod.getImageIcon("help"));
    }//GEN-LAST:event_lb_help_filterMouseClicked

    private void btn_export_dataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_export_dataActionPerformed
        if (frameName == null) {
            showErrorMessage("Có lỗi xảy ra, không thể xuất dữ liệu!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        TableModel tableModel = table.getModel();
        if (tableModel == null) {
            showErrorMessage("Không có bất kỳ dữ liệu nào để xuất!", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (column == null || data == null) {
            showErrorMessage("Không có bất kỳ dữ liệu nào để xuất!", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (column.length == 1) {
            showErrorMessage("Không có bất kỳ dữ liệu nào để xuất!", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(this);
        File file = chooser.getSelectedFile();
        if (file != null) {
            String pathfile = file.getPath();
            if (file.getName().lastIndexOf(".") == -1) {
                pathfile += ".xls";
            } else if (!file.getName().substring(file.getName().lastIndexOf(".") + 1).equals("xls")) {
                pathfile += ".xls";
            }
//            System.out.println(pathfile);
            if (!CommonMethod.generateExcelData(pathfile, column, data, listNumberValue)) {
                showErrorMessage("Xuất dữ liệu thất bại!", JOptionPane.ERROR_MESSAGE);
            } else {
                showErrorMessage("Xuất dữ liệu thành công!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_export_dataActionPerformed

    private void lb_backupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_backupMouseClicked
        String mess = Validation.checkBackup(DataProp.getBackupProperties());
        if (!mess.equals(CommonDefine.MESSAGE_CONFIG_OK)) {
            int choice = JOptionPane.showConfirmDialog(this, "<html>Hiện tại chưa có thông tin backup.<br>Bạn có muốn tạo không?</html>", "Notify!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(choice == JOptionPane.YES_OPTION){
                new BackupFrame().setVisible(true);
            }
            return;
        }
        int choose = JOptionPane.showConfirmDialog(this, "Bạn muốn sao lưu dữ liệu?", "Backup?", JOptionPane.YES_NO_CANCEL_OPTION);
        if (choose != JOptionPane.YES_OPTION) {
            return;
        }
        if (CommonMethod.agentBackupDB()) {
            showErrorMessage("Backup dữ liệu thành công, hãy kiểm tra lại!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            showErrorMessage("Backup dữ liệu thất bại!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lb_backupMouseClicked

    private void lb_backupMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_backupMouseEntered
        lb_backup.setForeground(Color.YELLOW);
        lb_backup.setIcon(CommonMethod.getImageIcon("backup_1"));
    }//GEN-LAST:event_lb_backupMouseEntered

    private void lb_backupMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_backupMouseExited
        lb_backup.setForeground(Color.WHITE);
        lb_backup.setIcon(CommonMethod.getImageIcon("backup"));
    }//GEN-LAST:event_lb_backupMouseExited

    private void choice_filter_typeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_choice_filter_typeItemStateChanged
        onChangeSearchField(evt);
    }//GEN-LAST:event_choice_filter_typeItemStateChanged

    private void onChangeSearchField(java.awt.event.ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (isValidFilter()) {
                int index = choice_filter_type.getSelectedIndex();
                if (index >= 0 && index < listFilter.size()) {
                    FilterElem fe = listFilter.get(index);
                    hideAllFilterType();
                    if (fe.getType() == FilterType.STRING) {
                        pn_filter_string.setVisible(true);
                        pn_btn_filter.setVisible(true);
                    } else if (fe.getType() == FilterType.NUMBER) {
                        pn_filter_number.setVisible(true);
                        pn_btn_filter.setVisible(true);
                    } else if (fe.getType() == FilterType.DATE) {
                        pn_filter_date.setVisible(true);
                        pn_btn_filter.setVisible(true);
                    }
                }
            }
        }
    }

    private void hideAllFilterType() {
        pn_filter_date.setVisible(false);
        pn_filter_number.setVisible(false);
        pn_filter_string.setVisible(false);
        pn_btn_filter.setVisible(false);
        tf_filter_string.setText("");
        tf_filter_number.setText("");
        date_filter_1.setDate(null);
        date_filter_2.setDate(null);
    }

    private boolean isValidFilter() {
        if (frameName == null) {
            hideAllFilterAction();
            return false;
        }
        if (listFilter == null || listFilter.isEmpty()) {
            hideAllFilterAction();
            return false;
        }
        if (!frameName.equals(VATLIEUNHAP) && !frameName.equals(VATLIEUXUAT) && !frameName.equals(HISTORY)) {
            hideAllFilterAction();
            return false;
        }
        if (congtrinh == null) {
            hideAllFilterAction();
            return false;
        }
        return true;
    }

    private boolean checkDeleteData(EntityComponent entity) {
        if (entity == null) {
            showErrorMessage("Vui lòng chọn thông tin để xóa!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (frameName != null) {
            if (frameName.equals(VATLIEUNHAP) || frameName.equals(VATLIEUXUAT)) {
                try {
                    boolean mine = false;
                    for (Field f : entity.getFields()) {
                        if (f.getName().equals("manguoixuat") || f.getName().equals("manguoinhap")) {
                            String manguoidung = f.get(entity).toString();
                            if (mapPermissions != null && mapPermissions.containsKey(frameName)) {
                                boolean b = Home.tennguoidung.equals(manguoidung);
                                for (Permission p : mapPermissions.get(frameName)) {
                                    if (p.getId() == Permission.DELETE.getId()) {
                                        return true;
                                    } else if (p.getId() == Permission.DELETE_MINE.getId()) {
                                        mine = true;
                                        if (b) {
                                            return true;
                                        }
                                    }
                                }
                            }
                            break;
                        }
                    }
                    if (mine) {
                        showErrorMessage("Bạn chỉ có thể xóa thông tin do bạn tạo ra!", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        showErrorMessage("Bạn không có quyền xóa thông tin này!", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (IllegalArgumentException ex) {
                    showErrorMessage("Không thể xóa thông tin này!", JOptionPane.WARNING_MESSAGE);
                } catch (IllegalAccessException ex) {
                    showErrorMessage("Không thể xóa thông tin này!", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                if (mapPermissions != null && mapPermissions.containsKey(frameName)) {
                    boolean b = false;
                    for (Permission p : mapPermissions.get(frameName)) {
                        if (p.getId() == Permission.DELETE.getId()) {
                            b = true;
                            break;
                        }
                    }
                    if (b) {
                        if (frameName.equals(CHUCVU) && entity instanceof ChucVu) {
                            ChucVu cv = (ChucVu) entity;
                            TaiKhoan tk = new TaiKhoan();
                            tk.setMachucvu(cv.getMachucvu());
                            if (ServiceDAOFactory.selectList(tk).isEmpty()) {
                                return true;
                            }
                            showErrorMessage("Chức vụ này đang được bổ nhiệm, không thể xóa!", JOptionPane.WARNING_MESSAGE);
                        } else if (frameName.equals(LOAIHANGHOA) && entity instanceof LoaiHangHoa) {
                            LoaiHangHoa lhh = (LoaiHangHoa) entity;
                            HangHoa hh = new HangHoa();
                            hh.setMaloaihanghoa(lhh.getMaloaihanghoa());
                            if (ServiceDAOFactory.selectList(hh).isEmpty()) {
                                return true;
                            }
                            showErrorMessage("Loại vật liệu này đang được sử dụng, không thể xóa!", JOptionPane.WARNING_MESSAGE);
                        } else if (frameName.equals(HANGHOA) && entity instanceof HangHoa) {
                            HangHoa hh = (HangHoa) entity;
                            VatLieuTonKho vltk = new VatLieuTonKho();
                            vltk.setMahanghoa(hh.getMahanghoa());
                            if (ServiceDAOFactory.selectList(vltk).isEmpty()) {
                                return true;
                            }
                            showErrorMessage("Vật liệu này đã được lưu vào kho, không thể xóa!", JOptionPane.WARNING_MESSAGE);
                        } else if (frameName.equals(DONVI) && entity instanceof DonVi) {
                            DonVi dv = (DonVi) entity;
                            HangHoa hh = new HangHoa();
                            hh.setMadonvi(dv.getMadonvi());
                            if (ServiceDAOFactory.selectList(hh).isEmpty()) {
                                return true;
                            }
                            showErrorMessage("Đơn vị này đang được sử dụng, không thể xóa!", JOptionPane.WARNING_MESSAGE);
                        } else {
                            return true;
                        }
                    } else {
                        showErrorMessage("Bạn không có quyền xóa thông tin này!", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }
        return false;
    }

    private void updateDeleteEntity(EntityComponent entity) {
        if (frameName.equals(VATLIEUNHAP) || frameName.equals(VATLIEUXUAT)) {
            String mahanghoa = null;
            String macongtrinh = null;
            String strkhoiluong = null;
            String datetime = null;
            String nguoidung = null;
            try {
                for (Field f : entity.getFields()) {
                    if (f.getName().equals("mahanghoa")) {
                        mahanghoa = f.get(entity).toString();
                    } else if (f.getName().equals("macongtrinh")) {
                        macongtrinh = f.get(entity).toString();
                    } else if (f.getName().equals("khoiluong")) {
                        strkhoiluong = f.get(entity).toString();
                    } else if (f.getName().equals("ngayxuat") || f.getName().equals("ngaynhap")) {
                        datetime = f.get(entity).toString();
                    } else if (f.getName().equals("manguoixuat") || f.getName().equals("manguoinhap")) {
                        nguoidung = f.get(entity).toString();
                    }
                }
            } catch (IllegalArgumentException ex) {
            } catch (IllegalAccessException ex) {
            }
            if (macongtrinh == null || mahanghoa == null || strkhoiluong == null || datetime == null || nguoidung == null) {
                showErrorMessage("Không xác định được thông tin Công Trình, Vật Liệu!", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (frameName.equals(VATLIEUNHAP)) {
                DuToan dt = new DuToan();
                dt.setMacongtrinh(macongtrinh);
                dt.setMahanghoa(mahanghoa);
                EntityComponent e = ServiceDAOFactory.select(dt);
                if (e != null) {
                    DuToan dutoan = (DuToan) e;
                    dutoan.setTongkhoiluong(dutoan.getTongkhoiluong() - CommonMethod.stringToInt(strkhoiluong.trim()));
                    if (dutoan.getTongkhoiluong() <= 0) {
                        ServiceDAOFactory.delete(dutoan);
                    } else {
                        if (!ServiceDAOFactory.insertOrUpdate(dutoan)) {
                            showErrorMessage("Không cập nhật được tổng khối lượng dự toán!", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }
            }
            VatLieuTonKho vltk = new VatLieuTonKho();
            vltk.setMacongtrinh(macongtrinh);
            vltk.setMahanghoa(mahanghoa);

            EntityComponent e = ServiceDAOFactory.select(vltk);
            if (e != null) {
                VatLieuTonKho vatlieutonkho = (VatLieuTonKho) e;
                int kl = CommonMethod.stringToInt(strkhoiluong.trim());
                if (frameName.equals(VATLIEUNHAP)) {
                    vatlieutonkho.setKhoiluongton(vatlieutonkho.getKhoiluongton() - kl);
                } else {
                    vatlieutonkho.setKhoiluongton(vatlieutonkho.getKhoiluongton() + kl);
                }
                if (!ServiceDAOFactory.insertOrUpdate(vatlieutonkho)) {
                    showErrorMessage("Không cập nhật được vật liệu tồn kho!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                History history = new History();
                history.setDatemodify(CommonMethod.getFormatDateStringDB(new Date()));
                history.setAction(Action.DELETE.getName());
                history.setDateold(datetime);
                history.setKhoiluongcu(kl);
                history.setMahanghoacu(mahanghoa);
                history.setMacongtrinh(macongtrinh);
                if (frameName.equals(VATLIEUNHAP)) {
                    history.setType(HistoryType.IN.getName());
                } else {
                    history.setType(HistoryType.OUT.getName());
                }
                history.setTennguoidung(Home.tennguoidung);
                if (!ServiceDAOFactory.insertOrUpdate(history)) {
                    showErrorMessage("Không cập nhật được lịch sử nhập xuất!", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (frameName.equals(CONGTRINH) && entity instanceof CongTrinh) {
            CongTrinh ct = (CongTrinh) entity;
            VatLieuNhap execute = new VatLieuNhap();
            execute.setSelectQuery("DELETE " + VATLIEUNHAP + " WHERE macongtrinh='" + ct.getMacongtrinh() + "'");
            if (!ServiceDAOFactory.executeQuery(execute)) {
                showErrorMessage("Xóa thông tin vật liệu nhập thất bại!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            execute.setSelectQuery("DELETE " + VATLIEUXUAT + " WHERE macongtrinh='" + ct.getMacongtrinh() + "'");
            if (!ServiceDAOFactory.executeQuery(execute)) {
                showErrorMessage("Xóa thông tin vật liệu xuất thất bại!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            execute.setSelectQuery("DELETE " + VATLIEUTONKHO + " WHERE macongtrinh='" + ct.getMacongtrinh() + "'");
            if (!ServiceDAOFactory.executeQuery(execute)) {
                showErrorMessage("Xóa thông tin vật liệu tồn kho thất bại", JOptionPane.ERROR_MESSAGE);
                return;
            }
            execute.setSelectQuery("DELETE " + DUTOAN + " WHERE macongtrinh='" + ct.getMacongtrinh() + "'");
            if (!ServiceDAOFactory.executeQuery(execute)) {
                showErrorMessage("Xóa thông tin dự toán thất bại!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            execute.setSelectQuery("DELETE " + HISTORY + " WHERE macongtrinh='" + ct.getMacongtrinh() + "'");
            if (!ServiceDAOFactory.executeQuery(execute)) {
                showErrorMessage("Xóa thông tin lịch sử nhập xuất thất bại", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private EntityComponent getSelectedEntityTable() {
        if (listCurrentData != null && !listCurrentData.isEmpty()) {
            if (selectedIndex >= 0 && selectedIndex < listCurrentData.size()) {
                return listCurrentData.get(selectedIndex);
            }
        }
        return null;
    }

    private void removeHighlightSystem() {
        pn_tk.setBackground(CommonDefine.BUTTON_NORMAL_COLOR);
        pn_mk.setBackground(CommonDefine.BUTTON_NORMAL_COLOR);
        pn_dx.setBackground(CommonDefine.BUTTON_NORMAL_COLOR);
        pn_cv.setBackground(CommonDefine.BUTTON_NORMAL_COLOR);
        pn_dv.setBackground(CommonDefine.BUTTON_NORMAL_COLOR);
        pn_hh.setBackground(CommonDefine.BUTTON_NORMAL_COLOR);
        pn_nb.setBackground(CommonDefine.BUTTON_NORMAL_COLOR);
        pn_lhh.setBackground(CommonDefine.BUTTON_NORMAL_COLOR);
        pn_ct.setBackground(CommonDefine.BUTTON_NORMAL_COLOR);
        pn_vln.setBackground(CommonDefine.BUTTON_NORMAL_COLOR);
        pn_vlx.setBackground(CommonDefine.BUTTON_NORMAL_COLOR);
        pn_vltk.setBackground(CommonDefine.BUTTON_NORMAL_COLOR);
        pn_dt.setBackground(CommonDefine.BUTTON_NORMAL_COLOR);
        pn_history.setBackground(CommonDefine.BUTTON_NORMAL_COLOR);

        pn_tk.setEnabled(true);
        pn_mk.setEnabled(true);
        pn_dx.setEnabled(true);
        pn_cv.setEnabled(true);
        pn_dv.setEnabled(true);
        pn_hh.setEnabled(true);
        pn_nb.setEnabled(true);
        pn_lhh.setEnabled(true);
        pn_ct.setEnabled(true);
        pn_vln.setEnabled(true);
        pn_vlx.setEnabled(true);
        pn_vltk.setEnabled(true);
        pn_dt.setEnabled(true);
        pn_history.setEnabled(true);
    }
    //<editor-fold defaultstate="collapsed" desc="Variable">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bot_panel;
    private javax.swing.JButton btn_export_data;
    private javax.swing.JButton btn_filter;
    private javax.swing.JComboBox choice_filter_type;
    private org.jdesktop.swingx.JXDatePicker date_filter_1;
    private org.jdesktop.swingx.JXDatePicker date_filter_2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lb_add;
    private javax.swing.JLabel lb_backup;
    private javax.swing.JLabel lb_close;
    private javax.swing.JLabel lb_ct;
    private javax.swing.JLabel lb_ctname;
    private javax.swing.JLabel lb_cv;
    private javax.swing.JLabel lb_delete;
    private javax.swing.JLabel lb_dt;
    private javax.swing.JLabel lb_dv;
    private javax.swing.JLabel lb_dx;
    private javax.swing.JLabel lb_filter_type;
    private javax.swing.JLabel lb_help_filter;
    private javax.swing.JLabel lb_hh;
    private javax.swing.JLabel lb_history;
    private javax.swing.JLabel lb_lhh;
    private javax.swing.JLabel lb_mk;
    private javax.swing.JLabel lb_nb;
    private javax.swing.JLabel lb_refresh;
    private javax.swing.JLabel lb_tk;
    private javax.swing.JLabel lb_update;
    private javax.swing.JLabel lb_user;
    private javax.swing.JLabel lb_vln;
    private javax.swing.JLabel lb_vltk;
    private javax.swing.JLabel lb_vlx;
    private javax.swing.JPanel left_panel;
    private javax.swing.JTabbedPane menu_tab;
    private javax.swing.JPanel mid_panel;
    private javax.swing.JPanel pn_action_top;
    private javax.swing.JPanel pn_add;
    private javax.swing.JPanel pn_btn_filter;
    private javax.swing.JPanel pn_choose_filter;
    private javax.swing.JPanel pn_close;
    private javax.swing.JPanel pn_ct;
    private javax.swing.JPanel pn_cv;
    private javax.swing.JPanel pn_delete;
    private javax.swing.JPanel pn_dt;
    private javax.swing.JPanel pn_dv;
    private javax.swing.JPanel pn_dx;
    private javax.swing.JPanel pn_export_data;
    private javax.swing.JPanel pn_filter_date;
    private javax.swing.JPanel pn_filter_number;
    private javax.swing.JPanel pn_filter_string;
    private javax.swing.JPanel pn_hh;
    private javax.swing.JPanel pn_history;
    private javax.swing.JPanel pn_lhh;
    private javax.swing.JPanel pn_mid;
    private javax.swing.JPanel pn_mid_ctname;
    private javax.swing.JPanel pn_mid_left_temp;
    private javax.swing.JPanel pn_mk;
    private javax.swing.JPanel pn_nb;
    private javax.swing.JPanel pn_refresh;
    private javax.swing.JPanel pn_tk;
    private javax.swing.JPanel pn_update;
    private javax.swing.JPanel pn_vln;
    private javax.swing.JPanel pn_vltk;
    private javax.swing.JPanel pn_vlx;
    private javax.swing.JScrollPane scroll_table;
    private javax.swing.JSeparator separator_export;
    private javax.swing.JPanel tab_category;
    private javax.swing.JPanel tab_help;
    private javax.swing.JPanel tab_project;
    private javax.swing.JPanel tab_system;
    private javax.swing.JTable table;
    private javax.swing.JTextField tf_filter_number;
    private javax.swing.JTextField tf_filter_string;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
