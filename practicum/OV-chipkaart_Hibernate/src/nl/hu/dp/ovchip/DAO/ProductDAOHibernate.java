package nl.hu.dp.ovchip.DAO;

import nl.hu.dp.ovchip.domein.Adres;
import nl.hu.dp.ovchip.domein.OVChipkaart;
import nl.hu.dp.ovchip.domein.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

public class ProductDAOHibernate implements ProductDAO {
    private SessionFactory sessionFactory;


    public ProductDAOHibernate(SessionFactory sf) throws SQLException {
        this.sessionFactory = sf;
    }

    @Override
    public boolean save(Product product) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try (session) {
            session.save(product);
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
    public boolean update(Product product) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try (session) {
            session.update(product);
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
    public boolean delete(Product product) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try (session) {
            product = session.get(Product.class, product.getProduct_nummer());
            product.getOvChipkaarten().clear();
            session.update(product);
            session.delete(product);
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
    public List<Product> findAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try (session) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> query = builder.createQuery(Product.class);
            query.select(query.from(Product.class));
            tx.commit();
            return session.createQuery(query).getResultList();
        } catch (Exception e){
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Product> findByOVChipkaart(OVChipkaart ovChipkaart) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try (session) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Product> query = cb.createQuery(Product.class);
            Root<Product> root = query.from(Product.class);
            query.where(cb.isMember(ovChipkaart, root.get("ovchipKaarten")));

            return session.createQuery(query).getResultList();
        } catch (Exception e){
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
