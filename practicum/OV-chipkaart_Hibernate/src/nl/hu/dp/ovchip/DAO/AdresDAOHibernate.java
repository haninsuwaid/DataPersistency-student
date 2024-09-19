package nl.hu.dp.ovchip.DAO;

import nl.hu.dp.ovchip.domein.Adres;
import nl.hu.dp.ovchip.domein.Reiziger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class AdresDAOHibernate implements AdresDAO {
    private SessionFactory sessionFactory;


    public AdresDAOHibernate(SessionFactory sf) throws SQLException {
        this.sessionFactory = sf;
    }


    @Override
    public boolean save(Adres adres) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try (session) {
            session.save(adres);
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
    public boolean update(Adres adres) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try (session) {
            session.update(adres);
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
    public boolean delete(Adres adres) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try (session) {
            session.delete(adres);
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
    public Adres findByReiziger(Reiziger reiziger) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try (session) {
            String hql = "FROM Adres a WHERE a.reiziger.id = :id";
            Query<Adres> query = session.createQuery(hql, Adres.class);
            query.setParameter("id", reiziger.getId());
            Adres adres = query.uniqueResult();
            tx.commit();
            return adres;
        } catch (Exception e){
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Adres> findAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try (session) {
            String hql = "FROM Adres";
            Query<Adres> query = session.createQuery(hql, Adres.class);
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
