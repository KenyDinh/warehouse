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
import java.io.File;
import java.io.IOException;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import properties.DataProp;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author KenyDinh
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

//    static {
//        try {
//            // Create the SessionFactory from standard (hibernate.cfg.xml) 
//            // config file.
//            Configuration config = new Configuration();
//            config.addAnnotatedClass(ChucVu.class);
//            config.addAnnotatedClass(CongTrinh.class);
//            config.addAnnotatedClass(DonVi.class);
//            config.addAnnotatedClass(DuToan.class);
//            config.addAnnotatedClass(HangHoa.class);
//            config.addAnnotatedClass(LoaiHangHoa.class);
//            config.addAnnotatedClass(NguoiBan.class);
//            config.addAnnotatedClass(TaiKhoan.class);
//            config.addAnnotatedClass(VatLieuNhap.class);
//            config.addAnnotatedClass(VatLieuTonKho.class);
//            config.addAnnotatedClass(VatLieuXuat.class);
//            Properties prop = new Properties();
//            prop.load(new FileInputStream("db-config.properties"));
//            config.setProperties(prop);
//            ServiceRegistry service = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
//            sessionFactory = config.buildSessionFactory(service);
//        } catch (HibernateException ex) {
//            System.out.println(ex.toString());
//            throw new ExceptionInInitializerError(ex);
//        } catch (FileNotFoundException ex) {
//            System.out.println(ex.toString());
//            throw new ExceptionInInitializerError(ex);
//        } catch (IOException ex) {
//            System.out.println(ex.toString());
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
    public static boolean initSessionFatory() {
        if (null == sessionFactory || sessionFactory.isClosed()) {
            try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
                // config file.
                Configuration config = new Configuration();
                config.addAnnotatedClass(ChucVu.class);
                config.addAnnotatedClass(CongTrinh.class);
                config.addAnnotatedClass(DonVi.class);
                config.addAnnotatedClass(DuToan.class);
                config.addAnnotatedClass(HangHoa.class);
                config.addAnnotatedClass(LoaiHangHoa.class);
                config.addAnnotatedClass(NguoiBan.class);
                config.addAnnotatedClass(TaiKhoan.class);
                config.addAnnotatedClass(VatLieuNhap.class);
                config.addAnnotatedClass(VatLieuTonKho.class);
                config.addAnnotatedClass(VatLieuXuat.class);
                config.addAnnotatedClass(History.class);
                config.setProperties(DataProp.getDbProperties());
                ServiceRegistry service = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
                sessionFactory = config.buildSessionFactory(service);
            } catch (HibernateException ex) {
                CommonMethod.writeExceptionLog(ex.getStackTrace());
                return false;
            }
        }
        return true;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

//    public static void main(String[] args) {
//        try {
//            if(!DataProp.loadConfigProperties()){
//                System.out.println("Error!");
//            }
//            Configuration config = new Configuration();
//            config.addAnnotatedClass(ChucVu.class);
//            config.addAnnotatedClass(CongTrinh.class);
//            config.addAnnotatedClass(DonVi.class);
//            config.addAnnotatedClass(DuToan.class);
//            config.addAnnotatedClass(HangHoa.class);
//            config.addAnnotatedClass(LoaiHangHoa.class);
//            config.addAnnotatedClass(NguoiBan.class);
//            config.addAnnotatedClass(TaiKhoan.class);
//            config.addAnnotatedClass(VatLieuNhap.class);
//            config.addAnnotatedClass(VatLieuTonKho.class);
//            config.addAnnotatedClass(VatLieuXuat.class);
//            config.addAnnotatedClass(History.class);
//            config.setProperties(DataProp.getDbProperties());
//            SchemaExport se = new SchemaExport(config);
//            File file = new File("warehouse.sql");
//            file.createNewFile();
//            se.setDelimiter(";");
//            se.setOutputFile(file.getPath());
//            se.setHaltOnError(false);
//            se.create(true, true);
//        } catch (IOException ex) {
//            System.out.println(ex.toString());
//        }
//    }
}
