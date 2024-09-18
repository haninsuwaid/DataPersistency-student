package nl.hu.dp.ovchip.DAO;

import nl.hu.dp.ovchip.domein.Reiziger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class ReizigerDAOHibernate implements ReizigerDAO  {
    private SessionFactory sessionFactory = null;


    public ReizigerDAOHibernate(SessionFactory sf) throws SQLException {
        this.sessionFactory = sf;
    }


    @Override
    public boolean save(Reiziger reiziger) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try (session) {
            session.save(reiziger);
            tx.commit();
            return true;
        } catch (Exception e){
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Reiziger reiziger) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try (session) {
            session.update(reiziger);
            tx.commit();
            return true;
        } catch (Exception e){
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try (session) {
            session.delete(reiziger);
            tx.commit();
            return true;
        } catch (Exception e){
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Reiziger> findAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try (session) {
            String hql = "FROM Reiziger";
            Query<Reiziger> query = session.createQuery(hql, Reiziger.class);
            tx.commit();
            return query.list();
        } catch (Exception e){
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }


    @Override
    public Reiziger findById(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try (session) {
            String hql = "FROM Reiziger r WHERE r.id = :id";
            Query<Reiziger> query = session.createQuery(hql, Reiziger.class);
            query.setParameter("id", id);
            tx.commit();
            return query.uniqueResult();
        } catch (Exception e){
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Reiziger> findByGbdatum(Date datum) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try (session) {
            String hql = "FROM Reiziger WHERE geboortedatum = :datum";
            Query<Reiziger> query = session.createQuery(hql, Reiziger.class);
            query.setParameter("datum", datum);
            tx.commit();
            return query.list();
        } catch (Exception e){
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
