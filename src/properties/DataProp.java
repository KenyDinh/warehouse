/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package properties;

import common.CommonDefine;
import common.CommonMethod;
import common.HashContent;
import java.util.Properties;

/**
 *
 * @author KenyDinh
 */
public class DataProp {

    private static final Properties CONFIG_DATABASE = new Properties();
    private static final Properties CONFIG_BACKUP = new Properties();
    private static final Properties CONFIG_LICENSE = new Properties();
    private static final Properties CONFIG_INFO = new Properties();

    public static String loadConfigProperties() {
        String content = CommonMethod.getContentFile(CommonDefine.CONFIG_DB_FILE);
        if (content == null) {
            return "Không tìm thấy tập tin cấu hình cơ sỡ dữ liệu!";
        }
        if (!initProperties(CONFIG_DATABASE, HashContent.getDecryptContent(content))) {
            return "Tập tin cấu hình cơ sỡ sữ liệu không hợp lệ!";
        }
        //load backup properties // not checked yet
        content = CommonMethod.getContentFile(CommonDefine.CONFIG_BACKUP_FILE);
        if (content != null) {
            initProperties(CONFIG_BACKUP, HashContent.getDecryptContent(content));
        }
        //
        content = CommonMethod.getContentFile(CommonDefine.CONFIG_LICENSE_FILE);
        if (content == null) {
            return "Không tìm thấy tập tin license!";
        }
        if (!initProperties(CONFIG_LICENSE, HashContent.getDecryptContent(content))) {
            return "Thông tin License không hợp lệ!";
        }
        //
        content = CommonMethod.getContentFile(CommonDefine.CONFIG_INFO_FILE);
        if (content == null) {
            return "Không tìm thấy tập tin cấu hình chung của ứng dụng!";
        }
        if (!initProperties(CONFIG_INFO, HashContent.getDecryptContent(content))) {
            return "Tập tin cấu hình chung của ứng dụng không hợp lệ!";
        }
        return CommonDefine.MESSAGE_CONFIG_OK;
    }

    public static boolean initProperties(Properties prop, String content) {
        if (content == null) {
            return false;
        }
        for (String s : content.split(CommonDefine.BREAK_LINE)) {
            if (s != null && !s.trim().isEmpty() && !s.contains(CommonDefine.SEPARATOR_KEY_VALUE)) {
                return false;
            }
            String arr[] = s.split(CommonDefine.SEPARATOR_KEY_VALUE, 2);
            if (arr == null || arr.length != 2) {
                return false;
            }
            if (arr[0].trim().isEmpty()) {
                return false;
            }
            prop.setProperty(arr[0], arr[1]);
        }
        return true;
    }

    public static Properties getDbProperties() {
        return CONFIG_DATABASE;
    }

    public static Properties getBackupProperties() {
        return CONFIG_BACKUP;
    }

    public static Properties getLicenseProperties() {
        return CONFIG_LICENSE;
    }

    public static Properties getInfoProperties() {
        return CONFIG_INFO;
    }
}
