package skillsbattle.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class GenericDAO<T> {

    private final Class<T> tClass;
    private static SessionFactory sessionFactory = Connector.getInstance().getSessionFactory();

    public GenericDAO(Class<T> tClass) {this.tClass = tClass;}

    public T get(Serializable serializable) {
        T t = null;
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            t = session.get(tClass, serializable);
            transaction.commit();
        }
        return t;
    }

    public T delete(T t) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
        }
        return t;
    }

    public void insert(T t) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(t);
            transaction.commit();
        }
    }

    public T update(T t) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
        }
        return t;
    }

    public List<T> getAll() {
        List<T> tList = null;
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            tList = session.createQuery("from " + tClass.getName(), tClass).list();
            transaction.commit();
        }
        return tList;
    }

    public List<T> namedQuery(String parameter, String queryName) {
        List<T> tList = null;
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            switch (queryName) {
                case "getPlayerByName" -> {
                    tList = session.createNamedQuery(queryName, tClass).setParameter("name", parameter).list();
                }
                case "searchForPassword" -> {
                    tList = session.createNamedQuery(queryName, tClass).setParameter("password", parameter).list();
                }
                case "getPlayerByEmail" -> {
                    tList = session.createNamedQuery(queryName, tClass).setParameter("email", parameter).list();
                }
            }
            transaction.commit();
        }
        return tList;
    }
}
