/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

import common.CommonMethod;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author KenyDinh
 */
public enum Role {

    ADMIN(1, "Administrator", new String[]{"donvi", "loaihanghoa", "hanghoa", "nguoiban", "vatlieunhap", "vatlieuxuat", "congtrinh", "taikhoan", "chucvu", "dutoan", "history", "vatlieutonkho"}, new int[]{21, 21, 21, 21, 21, 21, 21, 21, 21, 4, 0, 0}),
    KE_TOAN(2, "Kế Toán", new String[]{"donvi", "loaihanghoa", "hanghoa", "nguoiban", "vatlieunhap", "vatlieuxuat", "congtrinh"}, new int[]{21, 21, 21, 21, 11, 11, 5}),;
    private int id;
    private String name;
    private String[] tables;
    private int[] permissions;

    public static final Role DEFAULT_ROLE = KE_TOAN;

    private Role(int id, String name, String[] tables, int[] permission) {
        this.id = id;
        this.name = name;
        this.tables = tables;
        this.permissions = permission;
    }

    public Map<String, Permission[]> getMapPermission() {
        Map<String, Permission[]> mapPermission = new HashMap<>();
        for (int i = 0; i < tables.length; i++) {
            if (i < permissions.length) {
                mapPermission.put(CommonMethod.getTableNameFromLowerCase(tables[i]), CommonMethod.getPermissions(permissions[i]));
            }

        }
        return mapPermission;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static Role valueOf(int id) {
        for (Role role : Role.values()) {
            if (role.getId() == id) {
                return role;
            }
        }
        return DEFAULT_ROLE;
    }
}
