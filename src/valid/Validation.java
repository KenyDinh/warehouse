/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valid;

import common.CommonDefine;
import common.CommonMethod;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author KenyDinh
 */
public class Validation {

    private static String ipAddress;
    private static String macAddress;
    private static final Date NOW = new Date();

    public static boolean loadAddress() {
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            ipAddress = ip.getHostAddress();

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            macAddress = sb.toString().trim();
        } catch (UnknownHostException e) {
            return false;
        } catch (SocketException e) {
            return false;
        }
        return true;
    }

    public static String checkLicense(Properties license) {
        if (!license.containsKey(CommonDefine.LICENSE_SERIAL_KEY)) {
            return "Không tìm thấy mã đăng ký!";
        }
        String value = license.getProperty(CommonDefine.LICENSE_SERIAL_KEY);
        if (value == null) {
            return "Mã đăng ký không hợp lệ!";
        }
        if (value.equals(CommonDefine.LICENSE_VALUE_TEMP)) {
            if (!license.containsKey(CommonDefine.LICENSE_EXPIRE)) {
                return "Ngày đăng ký tạm thời không hợp lệ!";
            }
            String expire = license.getProperty(CommonDefine.LICENSE_EXPIRE);
            Date expireDate = CommonMethod.getDate(expire, CommonDefine.DATE_FORMAT_LICENSE);
            if (expireDate == null) {
                return "Ngày đăng ký tạm thời không hợp lệ!!";
            }
            if (expireDate.before(NOW)) {
                return "Mã đăng ký đã hết hạn!";
            }
        } else {
            if (!value.equals(macAddress)) {
                return "Mã đăng ký không hợp lệ!!";
            }
        }
        return CommonDefine.MESSAGE_CONFIG_OK;
    }

    public static String checkBackup(Properties backup) {
        if (!backup.containsKey(CommonDefine.BACKUP_DATABASE)) {
            return "Không tìm thấy thông tin cơ sỡ dữ liệu!";
        }
        String db = backup.getProperty(CommonDefine.BACKUP_DATABASE);
        if (db == null || db.isEmpty()) {
            return "Thông tin cơ sở dữ liệu không hợp lệ!";
        }
        if (!backup.containsKey(CommonDefine.BACKUP_USERNAME)) {
            return "Không tìm thấy thông tin tên đang nhập!";
        }
        String user = backup.getProperty(CommonDefine.BACKUP_USERNAME);
        if (user == null || user.isEmpty()) {
            return "Thông tin tên đăng nhập không hợp lệ!";
        }
        if (!backup.containsKey(CommonDefine.BACKUP_PASSWORD)) {
            return "Không tìm thấy thông tin mật khẩu!";
        }
        return CommonDefine.MESSAGE_CONFIG_OK;
    }

    public static String checkDatabase(Properties database) {
        String mess = "Không tìm thấy thông tin cấu hình kết nối cơ sở dữ liệu!";
        if (!database.containsKey(CommonDefine.HIBERNATE_DIALECT_KEY)) {
            return mess;
        }
        if (!database.containsKey(CommonDefine.HIBERNATE_SESSION_CONTEXT_KEY)) {
            return mess;
        }
        if (!database.containsKey(CommonDefine.HIBERNATE_SESSION_CACHE_PROVIDER_KEY)) {
            return mess;
        }
        if (!database.containsKey(CommonDefine.HIBERNATE_CONNECTION_USERNAME_KEY)) {
            return mess;
        }
        if (!database.containsKey(CommonDefine.HIBERNATE_CONNECTION_URL_KEY)) {
            return mess;
        }
        if (!database.containsKey(CommonDefine.HIBERNATE_CONNECTION_PASSWORD_KEY)) {
            return mess;
        }
        if (!database.containsKey(CommonDefine.HIBERNATE_CONNECTION_DRIVER_CLASS_KEY)) {
            return mess;
        }
        String dialect = database.getProperty(CommonDefine.HIBERNATE_DIALECT_KEY);
        String context = database.getProperty(CommonDefine.HIBERNATE_SESSION_CONTEXT_KEY);
        String cache = database.getProperty(CommonDefine.HIBERNATE_SESSION_CACHE_PROVIDER_KEY);
        String user = database.getProperty(CommonDefine.HIBERNATE_CONNECTION_USERNAME_KEY);
        String pass = database.getProperty(CommonDefine.HIBERNATE_CONNECTION_PASSWORD_KEY);
        String url = database.getProperty(CommonDefine.HIBERNATE_CONNECTION_URL_KEY);
        String driver = database.getProperty(CommonDefine.HIBERNATE_CONNECTION_DRIVER_CLASS_KEY);

        mess = "Thông tin cấu hình kết nối cơ sở dữ liệu không hợp lệ!";
        if (dialect == null || !dialect.equals(CommonDefine.HIBERNATE_DIALECT_VALUE)) {
            return mess;
        }
        if (context == null || !context.equals(CommonDefine.HIBERNATE_SESSION_CONTEXT_VALUE)) {
            return mess;
        }
        if (cache == null || !cache.equals(CommonDefine.HIBERNATE_SESSION_CACHE_PROVIDER_VALUE)) {
            return mess;
        }
        if (driver == null || !driver.equals(CommonDefine.HIBERNATE_CONNECTION_DRIVER_CLASS_VALUE)) {
            return mess;
        }
        if (user == null || pass == null || url == null || user.trim().isEmpty() || url.trim().isEmpty()) {
            return mess;
        }
        return CommonDefine.MESSAGE_CONFIG_OK;
    }

    public static String checkInfo(Properties info) {
        if (!info.containsKey(CommonDefine.INFO_KEY)) {
            return "Không tìm thấy thông tin về ứng dụng!";
        }
        String v = info.getProperty(CommonDefine.INFO_KEY);
        if (v == null || !v.equals(CommonDefine.INFO_VALUE)) {
            return "Thông tin về ứng dụng không hợp lệ";
        }
        return CommonDefine.MESSAGE_CONFIG_OK;
    }

}
