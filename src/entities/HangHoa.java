/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

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
@Table(name = "hanghoa", catalog = "warehouse")
public class HangHoa implements EntityComponent {

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
    @Column(name = "mahanghoa", nullable = false)
    public String mahanghoa;

    @Column(name = "tenhanghoa", nullable = false)
    public String tenhanghoa;

    @Column(name = "donvi", nullable = false)
    public String madonvi;

    @Column(name = "gia", nullable = false)
    public int gia;

    @Column(name = "maloaihanghoa", nullable = false)
    public String maloaihanghoa;

    public HangHoa() {
        this.mahanghoa = "";
        this.tenhanghoa = "";
        this.madonvi = "";
        this.gia = 0;
        this.maloaihanghoa = "";

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
            listPrimaryKeys.add("mahanghoa");
        }
    }

    public String getMahanghoa() {
        return mahanghoa;
    }

    public void setMahanghoa(String mahanghoa) {
        this.mahanghoa = mahanghoa;
    }

    public String getTenhanghoa() {
        return tenhanghoa;
    }

    public void setTenhanghoa(String tenhanghoa) {
        this.tenhanghoa = tenhanghoa;
    }

    public String getMadonvi() {
        return madonvi;
    }

    public void setMadonvi(String madonvi) {
        this.madonvi = madonvi;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getMaloaihanghoa() {
        return maloaihanghoa;
    }

    public void setMaloaihanghoa(String maloaihanghoa) {
        this.maloaihanghoa = maloaihanghoa;
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
