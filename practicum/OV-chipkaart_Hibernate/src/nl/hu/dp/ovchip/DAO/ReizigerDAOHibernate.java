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
        return false;
    }

    @Override
    public boolean update(Reiziger reiziger) {
        return false;
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        return false;
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
        return null;
    }

    @Override
    public List<Reiziger> findByGbdatum(Date datum) {
        return List.of();
    }
}
