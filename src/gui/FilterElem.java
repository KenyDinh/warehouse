/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import enums.FilterType;

/**
 *
 * @author KenyDinh
 */
public class FilterElem {

    private String guiName;

    private String dbName;

    private FilterType type;

    public FilterElem(String guiName, String dbName, FilterType type) {
        this.guiName = guiName;
        this.dbName = dbName;
        this.type = type;
    }

    public FilterElem() {
    }

    public String getGuiName() {
        return guiName;
    }

    public void setGuiName(String guiName) {
        this.guiName = guiName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public FilterType getType() {
        return type;
    }

    public void setType(FilterType type) {
        this.type = type;
    }

}
