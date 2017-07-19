/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import entities.EntityComponent;
import enums.Permission;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import properties.DataProp;

/**
 *
 * @author KenyDinh
 */
public class CommonMethod {

    private static final GraphicsDevice GD = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    public static int getHeight(int height) {
        int monitor_height = GD.getDisplayMode().getHeight();
        int result = (int) (monitor_height - height) / 2;
        return (int) (result - result / 4);
    }

    public static int getWidth(int width) {
        int monitor_width = GD.getDisplayMode().getWidth();
        return (int) (monitor_width - width) / 2;
    }

    public static String getFormatDateStringDB(Date date) {
        return getFormatDateString(date, CommonDefine.DATE_FORMAT_DB);
    }

    public static String getFormatDateString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Date getDateStringDB(String s) {
        return getDate(s, CommonDefine.DATE_FORMAT_DB);
    }

    public static Date getDate(String s, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = sdf.parse(s);
        } catch (ParseException ex) {
//            writeExceptionLog(ex.getStackTrace());
        }
        return date;
    }

    public static String convertDateDbtoGui(String date_db) {
        return date_db.split("\\s")[0];
    }

    public static String encryptedPasswordMD5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashcode = number.toString(16);
            return hashcode;
        } catch (NoSuchAlgorithmException e) {
            return password;
        }
    }

    private static EntityComponent getObjectEntity(String table) {
        String tableName = getFullTableName(table);
        try {
            Class<? extends Object> clazz = Class.forName(tableName);
            EntityComponent entity = (EntityComponent) clazz.newInstance();
            return entity;
        } catch (ClassNotFoundException ex) {
            writeExceptionLog(ex.getStackTrace());
        } catch (InstantiationException ex) {
            writeExceptionLog(ex.getStackTrace());
        } catch (IllegalAccessException ex) {
            writeExceptionLog(ex.getStackTrace());
        }
        return null;
    }

    private static String getFullTableName(String tableName) {
        return "entities." + tableName;
    }

    public static String getContentFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        return getContentFile(file);
    }

    public static String getContentFile(File file) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.getProperty("line.separator"));
            }
            br.close();
        } catch (IOException e) {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                }
            }
        }
        return sb.toString();
    }

    public static boolean createFile(String path, String content) {
        File file = new File(path);
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }
        OutputStream output = null;
        try {
            output = new FileOutputStream(file);
            byte[] byte_content = content.getBytes("UTF-8");
            output.write(byte_content);
            output.flush();
            output.close();
        } catch (IOException e) {
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ex) {
                }
            }
        }
        return true;
    }

    private static boolean deleteFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            return file.delete();
        }
        return true;
    }

    public static void writeLog(String writer, String log) {

        System.out.println("[" + writer + "] " + log);
    }

    public static void writeExceptionLog(StackTraceElement[] stackTraces) {
        if (stackTraces == null) {
            return;
        }
        for (StackTraceElement ste : stackTraces) {
            writeLog("Exception", ste.toString());
        }
    }

    public static boolean copyObject(EntityComponent o1, EntityComponent o2) {
        try {
            Field[] fields = o1.getFields();
            for (Field field : fields) {
                field.set(o1, field.get(o2));
            }
        } catch (IllegalArgumentException ex) {
            writeExceptionLog(ex.getStackTrace());
            return false;
        } catch (IllegalAccessException ex) {
            writeExceptionLog(ex.getStackTrace());
            return false;
        }
        return true;
    }

    public static String getObjectAsString(EntityComponent object) {
        try {
            StringBuilder sb = new StringBuilder();
            Field[] fields = object.getFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                sb.append(field.getName()).append(":").append(field.get(object).toString());
                if (i < fields.length - 1) {
                    sb.append(",");
                }
            }
            return sb.toString();
        } catch (IllegalArgumentException ex) {
            writeExceptionLog(ex.getStackTrace());
        } catch (IllegalAccessException ex) {
            writeExceptionLog(ex.getStackTrace());
        }
        return null;
    }

    public static String getStringInsertObject(EntityComponent object) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ").append(object.getClassName()).append(" VALUES(");
            Field[] fields = object.getFields();
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                String value = f.get(object).toString();
                if (value.equals("0")) {
                    sb.append("DEFAULT");
                } else {
                    sb.append(value);
                }
                if (i < fields.length - 1) {
                    sb.append(",");
                }
            }
            sb.append(");");
            return sb.toString();
        } catch (IllegalArgumentException ex) {
            writeExceptionLog(ex.getStackTrace());
        } catch (IllegalAccessException ex) {
            writeExceptionLog(ex.getStackTrace());
        }
        return "";
    }

    public static String getStringUpdateObject(EntityComponent old_o, EntityComponent new_o) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ").append(old_o.getClassName()).append(" SET ");
            Field[] fields = old_o.getFields();
            StringBuilder option = new StringBuilder();
            option.append(" WHERE ");
            int cnt = 0;
            int cnt2 = 0;
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                boolean b = f.get(old_o).equals(f.get(new_o));
                String old = f.get(old_o).toString();
                String next = f.get(new_o).toString();
                if (cnt > 0 && !b) {
                    sb.append(", ");
                }
                if (cnt2 > 0) {
                    option.append(" AND ");
                }
                if (f.getType() == Long.TYPE || f.getType() == Integer.TYPE) {
                    if (!b) {
                        sb.append(f.getName()).append(" = ").append(next);
                    }
                    option.append(f.getName()).append(" = ").append(old);
                } else {
                    if (!b) {
                        sb.append(f.getName()).append(" = ").append("'").append(next).append("'");
                    }
                    option.append(f.getName()).append(" = ").append("'").append(old).append("'");
                }
                if (!b) {
                    cnt++;
                }
                cnt2++;
            }
            sb.append(option.toString()).append(";");
            return sb.toString();
        } catch (IllegalArgumentException ex) {
            writeExceptionLog(ex.getStackTrace());
        } catch (IllegalAccessException ex) {
            writeExceptionLog(ex.getStackTrace());
        }
        return "";
    }

    public static boolean isNumberic(String s) {
        if (s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    public static int stringToInt(String s) {
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            n = 10 * n + (s.charAt(i) - '0');
        }
        return n;
    }

    public static ImageIcon getImageIcon(String name) {
        String path = "/images/" + name + ".png";
        ImageIcon icon = new ImageIcon(CommonMethod.class.getClass().getResource(path));
        return icon;
    }

    public static String getTableNameFromLowerCase(String table) {
        if (table.equalsIgnoreCase(CommonDefine.CHUCVU)) {
            return CommonDefine.CHUCVU;
        }
        if (table.equalsIgnoreCase(CommonDefine.CONGTRINH)) {
            return CommonDefine.CONGTRINH;
        }
        if (table.equalsIgnoreCase(CommonDefine.DONVI)) {
            return CommonDefine.DONVI;
        }
        if (table.equalsIgnoreCase(CommonDefine.DUTOAN)) {
            return CommonDefine.DUTOAN;
        }
        if (table.equalsIgnoreCase(CommonDefine.HANGHOA)) {
            return CommonDefine.HANGHOA;
        }
        if (table.equalsIgnoreCase(CommonDefine.HISTORY)) {
            return CommonDefine.HISTORY;
        }
        if (table.equalsIgnoreCase(CommonDefine.LOAIHANGHOA)) {
            return CommonDefine.LOAIHANGHOA;
        }
        if (table.equalsIgnoreCase(CommonDefine.NGUOIBAN)) {
            return CommonDefine.NGUOIBAN;
        }
        if (table.equalsIgnoreCase(CommonDefine.TAIKHOAN)) {
            return CommonDefine.TAIKHOAN;
        }
        if (table.equalsIgnoreCase(CommonDefine.VATLIEUNHAP)) {
            return CommonDefine.VATLIEUNHAP;
        }
        if (table.equalsIgnoreCase(CommonDefine.VATLIEUTONKHO)) {
            return CommonDefine.VATLIEUTONKHO;
        }
        if (table.equalsIgnoreCase(CommonDefine.VATLIEUXUAT)) {
            return CommonDefine.VATLIEUXUAT;
        }
        return table;
    }

    public static Permission[] getPermissions(int permission) {
        List<Permission> listPermissions = new ArrayList<>();
        for (Permission p : Permission.values()) {
            if ((p.getId() & permission) != 0) {
                listPermissions.add(p);
            }
        }
        Permission[] permissions = new Permission[listPermissions.size()];
        for (int i = 0; i < listPermissions.size(); i++) {
            permissions[i] = listPermissions.get(i);
        }
        return permissions;
    }

    public static boolean generateExcelData(String pathfile, Object[] column, Object[][] data, Set<Integer> listNumber) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("data");
        HSSFCellStyle style = createStyleForTitle(workbook);
        int rownum = 0;
        Cell cell;
        Row row;
        row = sheet.createRow(rownum);
        for (int i = 0; i < column.length; i++) {
            cell = row.createCell(i, CellType.STRING);
            cell.setCellValue(column[i].toString());
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
        }
        for (int i = 0; i < data.length; i++) {
            rownum++;
            row = sheet.createRow(rownum);
            for (int j = 0; j < data[0].length; j++) {
//                String value = data[i][j].toString();
//                if(value.matches(CommonDefine.DATE_FORMAT_DB))
                if (data[i][j] == null) {
                    data[i][j] = "";
                }
                if (listNumber != null && listNumber.contains(j)) {
                    cell = row.createCell(j);
                    cell.setCellType(CellType.NUMERIC);
                    if (CommonMethod.isNumberic(data[i][j].toString().trim())) {
                        cell.setCellValue(CommonMethod.stringToInt(data[i][j].toString().trim()));
                    }
                } else {
                    cell = row.createCell(j);
                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(data[i][j].toString());
                }
                sheet.autoSizeColumn(j);
            }
        }
        boolean seccess = false;
        File file = new File(pathfile);
        FileOutputStream outFile = null;
        try {
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);
            seccess = true;
        } catch (IOException e) {
            writeExceptionLog(e.getStackTrace());
        } finally {
            if (outFile != null) {
                try {
                    outFile.close();
                } catch (IOException ex) {
                    writeExceptionLog(ex.getStackTrace());
                }
            }
        }
        return seccess;
    }

    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    private static boolean backupDatabase(String name) {
        final String folName = "backup";
        File file = new File(name);
        boolean success = false;
        if (file.exists()) {
            File fol = new File(folName);
            fol.mkdirs();
            try {
                Process p = Runtime.getRuntime().exec(name);
                p.waitFor();
                success = true;
            } catch (IOException ex) {
                writeExceptionLog(ex.getStackTrace());
            } catch (InterruptedException ex) {
                writeExceptionLog(ex.getStackTrace());
            }
        }
        return success;
    }

    public static boolean agentBackupDB() {
        String database = DataProp.getBackupProperties().getProperty(CommonDefine.BACKUP_DATABASE);
        String username = DataProp.getBackupProperties().getProperty(CommonDefine.BACKUP_USERNAME);
        String password = DataProp.getBackupProperties().getProperty(CommonDefine.BACKUP_PASSWORD);
        String name = "backup/warehouse_" + CommonMethod.getFormatDateString(new Date(), CommonDefine.DATE_FORMAT_FILENAME) + "_backup.sql";
        StringBuilder sb = new StringBuilder();
        sb.append("@echo off");
        sb.append(CommonDefine.BREAK_LINE);
        sb.append("mysqldump -u").append(username).append(" -p").append(password).append(" ").append(database).append(" > ");
        sb.append(name);
        String filename = "backup.bat";
        if(!createFile(filename, sb.toString())){
            return false;
        }
        if(!backupDatabase(filename)){
            return false;
        }
        return deleteFile(filename);
    }

}
