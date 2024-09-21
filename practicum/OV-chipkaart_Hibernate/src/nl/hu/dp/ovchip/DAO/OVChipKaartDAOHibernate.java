package nl.hu.dp.ovchip.DAO;

import nl.hu.dp.ovchip.domein.OVChipkaart;
import nl.hu.dp.ovchip.domein.Reiziger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.SQLException;
import java.util.List;

public class OVChipKaartDAOHibernate implements OVChipKaartDAO {
    private SessionFactory sessionFactory;


    public OVChipKaartDAOHibernate(SessionFactory sf) throws SQLException {
        this.sessionFactory = sf;
    }

    @Override
    public boolean save(OVChipkaart ovchipkaart) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try (session) {
            session.save(ovchipkaart);
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
    public boolean update(OVChipkaart ovchipkaart) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try (session) {
            session.update(ovchipkaart);
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
    public boolean delete(OVChipkaart ovchipkaart) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try (session) {
            session.delete(ovchipkaart);
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
    public OVChipkaart findByReiziger(Reiziger reiziger) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try (session) {
            OVChipkaart ovChipkaart = session.get(OVChipkaart.class, reiziger.getId());
            tx.commit();
            return ovChipkaart;
        } catch (Exception e){
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<OVChipkaart> findAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try (session) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<OVChipkaart> query = builder.createQuery(OVChipkaart.class);
            query.select(query.from(OVChipkaart.class));
            tx.commit();
            return session.createQuery(query).getResultList();
        } catch (Exception e){
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
