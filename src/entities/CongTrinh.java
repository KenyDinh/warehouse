/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import common.CommonDefine;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author KenyDinh
 */
@Entity
@Table(name = "congtrinh", catalog = "warehouse")
public class CongTrinh implements EntityComponent {

    @Transient
    private static Field[] fields;
    @Transient
    private static Map<String, Field> mapFields;
    @Transient
    private static List<String> listPrimaryKeys;

    @Transient
    private String queryOption;
    @Transient
    private String querySelect;
    @Transient
    private int limit;

    @Id
    @Column(name = "macongtrinh", nullable = false)
    public String macongtrinh;

    @Column(name = "tencongtrinh", nullable = false)
    public String tencongtrinh;

    @Column(name = "diachicongtrinh", nullable = false)
    public String diachicongtrinh;

    @Column(name = "ngaybatdau", nullable = false)
    public String ngaybatdau;

    @Column(name = "ngayketthuc", nullable = false)
    public String ngayketthuc;

    @Column(name = "ngaycapnhat", nullable = false)
    public String ngaycapnhat;

    @Column(name = "trangthai", nullable = false)
    public String trangthai;

    public CongTrinh() {
        this.macongtrinh = "";
        this.tencongtrinh = "";
        this.diachicongtrinh = "";
        this.ngaybatdau = CommonDefine.DEFAULT_DATE_STRING;
        this.ngayketthuc = CommonDefine.DEFAULT_DATE_STRING;
        this.ngaycapnhat = CommonDefine.DEFAULT_DATE_STRING;
        this.trangthai = "";

        if (fields == null || fields.length == 0) {
            fields = this.getClass().getFields();
        }
        if (mapFields == null || mapFields.isEmpty()) {
            mapFields = new HashMap<>();
            for (Field f : fields) {
                f.setAccessible(true);
                mapFields.put(f.getName(), f);
            }

        }
        if (listPrimaryKeys == null || listPrimaryKeys.isEmpty()) {
            listPrimaryKeys = new ArrayList<>();
            listPrimaryKeys.add("macongtrinh");
        }
    }

    public String getMacongtrinh() {
        return macongtrinh;
    }

    public void setMacongtrinh(String macongtrinh) {
        this.macongtrinh = macongtrinh;
    }

    public String getTencongtrinh() {
        return tencongtrinh;
    }

    public void setTencongtrinh(String tencongtrinh) {
        this.tencongtrinh = tencongtrinh;
    }

    public String getDiachicongtrinh() {
        return diachicongtrinh;
    }

    public void setDiachicongtrinh(String diachicongtrinh) {
        this.diachicongtrinh = diachicongtrinh;
    }

    public String getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(String ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    public String getNgayketthuc() {
        return ngayketthuc;
    }

    public void setNgayketthuc(String ngayketthuc) {
        this.ngayketthuc = ngayketthuc;
    }

    public String getNgaycapnhat() {
        return ngaycapnhat;
    }

    public void setNgaycapnhat(String ngaycapnhat) {
        this.ngaycapnhat = ngaycapnhat;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    @Override
    public Field[] getFields() {
        return fields;
    }

    @Override
    public Map<String, Field> getFieldsAsMap() {
        return mapFields;
    }

    /**
     *
     * @return
     */
    @Override
    public List<String> getPrimaryKeys() {
        return listPrimaryKeys;
    }

    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getSelectQuery() {
        return querySelect;
    }

    @Override
    public String getSelectOption() {
        return queryOption;
    }

    @Override
    public void setSelectQuery(String selectQuery) {
        this.querySelect = selectQuery;
    }

    @Override
    public void setSelectOption(String selectOption) {
        this.queryOption = selectOption;
    }

    @Override
    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public int getLimit() {
        return limit;
    }

}
