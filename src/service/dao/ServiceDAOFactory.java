/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.dao;

import common.CommonDefine;
import common.CommonMethod;
import common.HibernateUtil;
import entities.EntityComponent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StaleStateException;
import org.hibernate.Transaction;

/**
 *
 * @author KenyDinh
 */
public class ServiceDAOFactory {

    public static boolean delete(EntityComponent entity) {
        if(entity == null) return false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE ").append(entity.getClassName()).append(" WHERE ");
            List<String> listKey = entity.getPrimaryKeys();
            Map<String, Field> mapFields = entity.getFieldsAsMap();
            for (int i = 0; i < listKey.size(); i++) {
                String key = listKey.get(i);
                if (mapFields.get(key).getType() == Long.TYPE || mapFields.get(key).getType() == Integer.TYPE) {
                    sql.append(key).append(" = ").append(mapFields.get(key).get(entity).toString());
                } else {
                    sql.append(key).append(" = '").append(mapFields.get(key).get(entity).toString()).append("'");
                }
                if (i < listKey.size() - 1) {
                    sql.append(" AND ");
                }
            }
            CommonMethod.writeLog("DELETE", sql.toString());
            Query query = session.createQuery(sql.toString());
            query.executeUpdate();
            tx.commit();
        } catch (StaleStateException ex) {
            CommonMethod.writeExceptionLog(ex.getStackTrace());
            return false;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            CommonMethod.writeExceptionLog(ex.getStackTrace());
            return false;
        } catch (IllegalArgumentException ex) {
            CommonMethod.writeExceptionLog(ex.getStackTrace());
            return false;
        } catch (IllegalAccessException ex) {
            CommonMethod.writeExceptionLog(ex.getStackTrace());
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean insertOrUpdate(EntityComponent entity) {
        if(entity == null) return false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            StringBuilder sql = new StringBuilder();
            sql.append("FROM ").append(entity.getClassName()).append(" WHERE ");
            List<String> listKey = entity.getPrimaryKeys();
            Map<String, Field> mapFields = entity.getFieldsAsMap();
            for (int i = 0; i < listKey.size(); i++) {
                String key = listKey.get(i);
                if (mapFields.get(key).getType() == Long.TYPE || mapFields.get(key).getType() == Integer.TYPE) {
                    sql.append(key).append(" = ").append(mapFields.get(key).get(entity).toString());
                } else {
                    sql.append(key).append(" = '").append(mapFields.get(key).get(entity).toString()).append("'");
                }
                if (i < listKey.size() - 1) {
                    sql.append(" AND ");
                }
            }
            CommonMethod.writeLog("SELECT", sql.toString());
            Query query = session.createQuery(sql.toString());
            List<EntityComponent> list = query.list();
            if (list != null && !list.isEmpty()) {
                EntityComponent exist = list.get(0);
                String out = CommonMethod.getStringUpdateObject(exist, entity);
                CommonMethod.copyObject(exist, entity);
                session.save(exist);
                CommonMethod.writeLog("UPDATE", out);
            } else {
                session.save(entity);
                CommonMethod.writeLog("INSERT", CommonMethod.getStringInsertObject(entity));
            }
            tx.commit();
        } catch (StaleStateException ex) {
            CommonMethod.writeExceptionLog(ex.getStackTrace());
            return false;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            ex.getStackTrace();
            CommonMethod.writeExceptionLog(ex.getStackTrace());
            return false;
        } catch (IllegalArgumentException ex) {
            CommonMethod.writeExceptionLog(ex.getStackTrace());
            return false;
        } catch (IllegalAccessException ex) {
            CommonMethod.writeExceptionLog(ex.getStackTrace());
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean deleteList(List<EntityComponent> listEntities) {
        if(listEntities == null || listEntities.isEmpty()) return false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (EntityComponent entity : listEntities) {
                StringBuilder sql = new StringBuilder();
                sql.append("DELETE ").append(entity.getClassName()).append(" WHERE ");
                List<String> listKey = entity.getPrimaryKeys();
                Map<String, Field> mapFields = entity.getFieldsAsMap();
                for (int i = 0; i < listKey.size(); i++) {
                    String key = listKey.get(i);
                    if (mapFields.get(key).getType() == Long.TYPE || mapFields.get(key).getType() == Integer.TYPE) {
                        sql.append(key).append(" = ").append(mapFields.get(key).get(entity).toString());
                    } else {
                        sql.append(key).append(" = '").append(mapFields.get(key).get(entity).toString()).append("'");
                    }
                    if (i < listKey.size() - 1) {
                        sql.append(" AND ");
                    }
                }
                CommonMethod.writeLog("DELETE", sql.toString());
                Query query = session.createQuery(sql.toString());
                query.executeUpdate();
            }
            tx.commit();
        } catch (StaleStateException ex) {
            CommonMethod.writeExceptionLog(ex.getStackTrace());
            return false;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            CommonMethod.writeExceptionLog(ex.getStackTrace());
            return false;
        } catch (IllegalArgumentException ex) {
            CommonMethod.writeExceptionLog(ex.getStackTrace());
            return false;
        } catch (IllegalAccessException ex) {
            CommonMethod.writeExceptionLog(ex.getStackTrace());
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean insertOrUpdateList(List<EntityComponent> listEntities) {
        if(listEntities == null || listEntities.isEmpty()) return false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (EntityComponent entity : listEntities) {
                StringBuilder sql = new StringBuilder();
                sql.append("FROM ").append(entity.getClassName()).append(" WHERE ");
                List<String> listKey = entity.getPrimaryKeys();
                Map<String, Field> mapFields = entity.getFieldsAsMap();
                for (int i = 0; i < listKey.size(); i++) {
                    String key = listKey.get(i);
                    if (mapFields.get(key).getType() == Long.TYPE || mapFields.get(key).getType() == Integer.TYPE) {
                        sql.append(key).append(" = ").append(mapFields.get(key).get(entity).toString());
                    } else {
                        sql.append(key).append(" = '").append(mapFields.get(key).get(entity).toString()).append("'");
                    }
                    if (i < listKey.size() - 1) {
                        sql.append(" AND ");
                    }
                }
                CommonMethod.writeLog("SELECT", sql.toString());
                Query query = session.createQuery(sql.toString());
                List<EntityComponent> list = query.list();
                if (list != null && !list.isEmpty()) {
                    EntityComponent exist = list.get(0);
                    String out = CommonMethod.getStringUpdateObject(exist, entity);
                    CommonMethod.copyObject(exist, entity);
                    session.save(exist);
                    CommonMethod.writeLog("UPDATE", out);
                } else {
                    session.save(entity);
                    CommonMethod.writeLog("INSERT", CommonMethod.getStringInsertObject(entity));
                }
            }
            tx.commit();
        } catch (StaleStateException ex) {
            CommonMethod.writeExceptionLog(ex.getStackTrace());
            return false;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            ex.getStackTrace();
            CommonMethod.writeExceptionLog(ex.getStackTrace());
            return false;
        } catch (IllegalArgumentException ex) {
            CommonMethod.writeExceptionLog(ex.getStackTrace());
            return false;
        } catch (IllegalAccessException ex) {
            CommonMethod.writeExceptionLog(ex.getStackTrace());
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static EntityComponent select(EntityComponent entity) {
        if(entity == null) return null;
        entity.setLimit(1);
        List<EntityComponent> list = selectList(entity);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public static List<EntityComponent> selectList(EntityComponent entity) {
        if(entity == null) return new ArrayList<EntityComponent>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<EntityComponent> list = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            StringBuilder sql = new StringBuilder();
            sql.append("FROM ").append(entity.getClassName());
            Field[] fields = entity.getFields();
            int cnt = 0;
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                String value = f.get(entity).toString();
                if (f.getType() == Boolean.TYPE) {
                    if (!sql.toString().contains("WHERE")) {
                        sql.append(" WHERE ");
                    }
                    if (cnt > 0) {
                        sql.append(" AND ");
                    }
                    sql.append(f.getName()).append(" = ").append((value.equals("true") ? "1" : "0"));
                    cnt++;
                } else if (f.getType() == Long.TYPE || f.getType() == Integer.TYPE) {
                    if (!value.equals("0")) {
                        if (!sql.toString().contains("WHERE")) {
                            sql.append(" WHERE ");
                        }
                        if (cnt > 0) {
                            sql.append(" AND ");
                        }
                        sql.append(f.getName()).append(" = ").append(value);
                        cnt++;
                    }
                } else if (!value.isEmpty() && !value.equals(CommonDefine.DEFAULT_DATE_STRING)) {
                    if (!sql.toString().contains("WHERE")) {
                        sql.append(" WHERE ");
                    }
                    if (cnt > 0) {
                        sql.append(" AND ");
                    }
                    sql.append(f.getName()).append(" = '").append(value).append("'");
                    cnt++;
                }
            }
            if (entity.getSelectOption() != null) {
                sql.append(" ").append(entity.getSelectOption());
            }
            CommonMethod.writeLog("SELECT", sql.toString());
            Query query = session.createQuery(sql.toString());
            if (entity.getLimit() > 0) {
                query.setMaxResults(entity.getLimit());
            }
            list = query.list();
            tx.commit();
        } catch (StaleStateException ex) {
            CommonMethod.writeExceptionLog(ex.getStackTrace());
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            CommonMethod.writeExceptionLog(ex.getStackTrace());
        } catch (IllegalArgumentException ex) {
            CommonMethod.writeExceptionLog(ex.getStackTrace());
        } catch (IllegalAccessException ex) {
            CommonMethod.writeExceptionLog(ex.getStackTrace());
        } finally {
            session.close();
        }
        return list;
    }

    public static List<EntityComponent> executeQueryList(EntityComponent entity) {
        if(entity == null) return new ArrayList<EntityComponent>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<EntityComponent> list = new ArrayList<>();
        try {
            if (entity.getSelectQuery() != null && !entity.getSelectQuery().isEmpty()) {
                tx = session.beginTransaction();
                StringBuilder sql = new StringBuilder();
                sql.append(entity.getSelectQuery());
                CommonMethod.writeLog("SELECT", sql.toString());
                Query query = session.createQuery(sql.toString());
                list = query.list();
                tx.commit();
            }
        } catch (StaleStateException ex) {
            CommonMethod.writeExceptionLog(ex.getStackTrace());
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            CommonMethod.writeExceptionLog(ex.getStackTrace());
        } catch (IllegalArgumentException ex) {
            CommonMethod.writeExceptionLog(ex.getStackTrace());
        } finally {
            session.close();
        }
        return list;
    }

    public static boolean executeQuery(EntityComponent entity) {
        if(entity == null) return false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            if (entity.getSelectQuery() != null && !entity.getSelectQuery().isEmpty()) {
                tx = session.beginTransaction();
                StringBuilder sql = new StringBuilder();
                sql.append(entity.getSelectQuery());
                CommonMethod.writeLog("EXECUTE", sql.toString());
                Query query = session.createQuery(sql.toString());
                query.executeUpdate();
                tx.commit();
            }
        } catch (StaleStateException ex) {
            CommonMethod.writeExceptionLog(ex.getStackTrace());
            return false;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            CommonMethod.writeExceptionLog(ex.getStackTrace());
            return false;
        } catch (IllegalArgumentException ex) {
            CommonMethod.writeExceptionLog(ex.getStackTrace());
            return false;
        } finally {
            session.close();
        }
        return true;
    }
    
}
