/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import entities.ChucVu;
import entities.CongTrinh;
import entities.DonVi;
import entities.DuToan;
import entities.HangHoa;
import entities.History;
import entities.LoaiHangHoa;
import entities.NguoiBan;
import entities.TaiKhoan;
import entities.VatLieuNhap;
import entities.VatLieuTonKho;
import entities.VatLieuXuat;
import java.awt.Color;

/**
 *
 * @author KenyDinh
 */
public class CommonDefine {

//    public static final String DATE_FORMAT_GUI = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_FORMAT_GUI = "yyyy-MM-dd";
    public static final String DATE_FORMAT_DB = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_LICENSE = "yyyyMMdd_HHmmss";
    public static final String DATE_FORMAT_TRANSFER = "yyyy/MM/dd-HH/mm/ss";
    public static final String DATE_FORMAT_FILENAME = "yyyyMMdd_HHmmss";
    public static final String MAIL_PATTERN = "\\w{6,16}@[a-z]+\\.[a-z]+(.[a-z]+){0,}";
    public static final String FILTER_NUMBER_PATTERN_BETWEEN = "[0-9]+-[0-9]+";
    public static final String FILTER_NUMBER_PATTERN_IN = "[0-9]+(,[0-9]+)+";
    
    public static final String DEFAULT_DATE_STRING = "1970-01-01 00:00:00";
    
    public static final String BREAK_LINE = System.getProperty("line.separator");
    public static final String SEPARATOR_KEY_VALUE = ":";
    
    public static final String MESSAGE_CONFIG_OK = "OK";
    
    public static final String LOG_FOLDER_NAME = "log/";
    public static final String CONFIG_DB_FILE = "config/secure/database.cfg";
    public static final String CONFIG_BACKUP_FILE = "config/secure/backup.cfg";
    public static final String CONFIG_LICENSE_FILE = "config/secure/license.cfg";
    public static final String CONFIG_INFO_FILE = "config/secure/info.cfg";
    public static final String CONFIG_LANGUAGE_FOLDER = "config/language/";
    public static final String CONFIG_LANGUAGE_VI = CONFIG_LANGUAGE_FOLDER + "vi.properties";
    public static final String CONFIG_LANGUAGE_EN = CONFIG_LANGUAGE_FOLDER + "en.properties";
    
    public static final String HIBERNATE_MYSQL_DEFAULT_PORT = "3306";
    public static final String HIBERNATE_APPLICATION_DEFAULT_DB = "warehouse";
    public static final String HIBERNATE_DIALECT_KEY = "hibernate.dialect";
    public static final String HIBERNATE_DIALECT_VALUE = "org.hibernate.dialect.MySQLDialect";
    public static final String HIBERNATE_CONNECTION_DRIVER_CLASS_KEY = "hibernate.connection.driver_class";
    public static final String HIBERNATE_CONNECTION_DRIVER_CLASS_VALUE = "com.mysql.jdbc.Driver";
    public static final String HIBERNATE_SESSION_CONTEXT_KEY = "current_session_context_class";
    public static final String HIBERNATE_SESSION_CONTEXT_VALUE = "thread";
    public static final String HIBERNATE_SESSION_CACHE_PROVIDER_KEY = "cache.provider_class";
    public static final String HIBERNATE_SESSION_CACHE_PROVIDER_VALUE = "org.hibernate.cache.internal.NoCacheProvider";
    public static final String HIBERNATE_CONNECTION_URL_KEY = "hibernate.connection.url";
    public static final String HIBERNATE_CONNECTION_URL_VALUE = "jdbc:mysql://[IP]:[PORT]/[DB]";
    public static final String HIBERNATE_CONNECTION_USERNAME_KEY = "hibernate.connection.username";
    public static final String HIBERNATE_CONNECTION_PASSWORD_KEY = "hibernate.connection.password";
    
    public static final String LICENSE_SERIAL_KEY = "license.serial.key";
    public static final String LICENSE_VALUE_TEMP = "license.value.temporary";
    public static final String LICENSE_EXPIRE = "license.expire";
    
    public static final String BACKUP_USERNAME = "backup.username";
    public static final String BACKUP_PASSWORD = "backup.password";
    public static final String BACKUP_DATABASE = "backup.database";
    
    public static final String INFO_KEY = "info.key";
    public static final String INFO_VALUE = "info.version.1.0";
    
    
    public static final Color BUTTON_NORMAL_COLOR = new Color(0, 102, 0);
    public static final Color BUTTON_HIGHLIGHT_COLOR = new Color(0, 170, 0);
    
    public static final Color TABLE_HEADER_COLOR = new Color(240, 240, 240);
    public static final Color TABLE_SELECTED_ODD_FCOLOR = new Color(255, 255, 255);
    public static final Color TABLE_SELECTED_EVEN_FCOLOR = new Color(255, 255, 255);
    public static final Color TABLE_ODD_COLOR = new Color(255, 255, 255);
    public static final Color TABLE_EVEN_COLOR = new Color(134, 220, 229);
    public static final Color TABLE_ODD_FCOLOR = new Color(0, 0, 0);
    public static final Color TABLE_EVEN_FCOLOR = new Color(0, 0, 0);
    public static final Color TABLE_SELECTED_ODD_COLOR = new Color(0, 170, 0);
    public static final Color TABLE_SELECTED_EVEN_COLOR = new Color(0, 170, 0);
    
    public static final String CHUCVU = ChucVu.class.getSimpleName();
    public static final String CONGTRINH = CongTrinh.class.getSimpleName();
    public static final String DONVI = DonVi.class.getSimpleName();
    public static final String DUTOAN = DuToan.class.getSimpleName();
    public static final String HANGHOA = HangHoa.class.getSimpleName();
    public static final String HISTORY = History.class.getSimpleName();
    public static final String LOAIHANGHOA = LoaiHangHoa.class.getSimpleName();
    public static final String NGUOIBAN = NguoiBan.class.getSimpleName();
    public static final String TAIKHOAN = TaiKhoan.class.getSimpleName();
    public static final String VATLIEUNHAP = VatLieuNhap.class.getSimpleName();
    public static final String VATLIEUTONKHO = VatLieuTonKho.class.getSimpleName();
    public static final String VATLIEUXUAT = VatLieuXuat.class.getSimpleName();
}
