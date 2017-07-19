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
@Table(name = "history", catalog = "warehouse")
public class History implements EntityComponent{

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
    
    @Column(name = "datemodify", nullable = false)
    public String datemodify;
    
    @Column(name = "action", nullable = false)
    public String action;
    
    @Column(name = "type", nullable = false)
    public String type;
    
    @Column(name = "dateold", nullable = false)
    public String dateold;
    
    @Column(name = "datenew", nullable = false)
    public String datenew;
    
    @Column(name = "macongtrinh", nullable = false)
    public String macongtrinh;
    
    @Column(name = "mahanghoacu", nullable = false)
    public String mahanghoacu;
    
    @Column(name = "mahanghoamoi", nullable = false)
    public String mahanghoamoi;
    
    @Column(name = "khoiluongcu", nullable = false)
    public int khoiluongcu;
    
    @Column(name = "khoiluongmoi", nullable = false)
    public int khoiluongmoi;
    
    @Column(name = "tennguoidung", nullable = false)
    public String tennguoidung;

    public History() {
        this.datemodify = CommonDefine.DEFAULT_DATE_STRING;
        this.action = "";
        this.type = "";
        this.dateold = CommonDefine.DEFAULT_DATE_STRING;
        this.datenew = CommonDefine.DEFAULT_DATE_STRING;
        this.macongtrinh = "";
        this.mahanghoacu = "";
        this.mahanghoamoi = "";
        this.khoiluongcu = 0;
        this.khoiluongmoi = 0;
        this.tennguoidung = "";
        
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

    public String getDatemodify() {
        return datemodify;
    }

    public void setDatemodify(String datemodify) {
        this.datemodify = datemodify;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateold() {
        return dateold;
    }

    public void setDateold(String dateold) {
        this.dateold = dateold;
    }

    public String getDatenew() {
        return datenew;
    }

    public void setDatenew(String datenew) {
        this.datenew = datenew;
    }

    public String getMacongtrinh() {
        return macongtrinh;
    }

    public void setMacongtrinh(String macongtrinh) {
        this.macongtrinh = macongtrinh;
    }

    public String getMahanghoacu() {
        return mahanghoacu;
    }

    public void setMahanghoacu(String mahanghoacu) {
        this.mahanghoacu = mahanghoacu;
    }

    public String getMahanghoamoi() {
        return mahanghoamoi;
    }

    public void setMahanghoamoi(String mahanghoamoi) {
        this.mahanghoamoi = mahanghoamoi;
    }

    public int getKhoiluongcu() {
        return khoiluongcu;
    }

    public void setKhoiluongcu(int khoiluongcu) {
        this.khoiluongcu = khoiluongcu;
    }

    public int getKhoiluongmoi() {
        return khoiluongmoi;
    }

    public void setKhoiluongmoi(int khoiluongmoi) {
        this.khoiluongmoi = khoiluongmoi;
    }

    public String getTennguoidung() {
        return tennguoidung;
    }

    public void setTennguoidung(String tennguoidung) {
        this.tennguoidung = tennguoidung;
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
        return this.limit;
    }
    
}
