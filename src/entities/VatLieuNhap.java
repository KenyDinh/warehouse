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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author KenyDinh
 */
@Entity
@Table(name = "vatlieunhap", catalog = "warehouse")
public class VatLieuNhap implements EntityComponent {

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long id;

    @Column(name = "ngaynhap", nullable = false)
    public String ngaynhap;

    @Column(name = "macongtrinh", nullable = false)
    public String macongtrinh;

    @Column(name = "mahanghoa", nullable = false)
    public String mahanghoa;

    @Column(name = "khoiluong", nullable = false)
    public int khoiluong;

    @Column(name = "manguoinhap", nullable = false)
    public String manguoinhap;

    public VatLieuNhap() {
        this.id = 0l;
        this.ngaynhap = CommonDefine.DEFAULT_DATE_STRING;
        this.macongtrinh = "";
        this.mahanghoa = "";
        this.khoiluong = 0;
        this.manguoinhap = "";

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
            listPrimaryKeys.add("id");
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(String ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    public String getMacongtrinh() {
        return macongtrinh;
    }

    public void setMacongtrinh(String macongtrinh) {
        this.macongtrinh = macongtrinh;
    }

    public String getMahanghoa() {
        return mahanghoa;
    }

    public void setMahanghoa(String mahanghoa) {
        this.mahanghoa = mahanghoa;
    }

    public int getKhoiluong() {
        return khoiluong;
    }

    public void setKhoiluong(int khoiluong) {
        this.khoiluong = khoiluong;
    }

    public String getManguoinhap() {
        return manguoinhap;
    }

    public void setManguoinhap(String manguoinhap) {
        this.manguoinhap = manguoinhap;
    }

    @Override
    public Field[] getFields() {
        return fields;
    }

    @Override
    public Map<String, Field> getFieldsAsMap() {
        return mapFields;
    }

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
