package nl.hu.dp.ovchip;

import nl.hu.dp.ovchip.DAO.ReizigerDAO;

import nl.hu.dp.ovchip.DAO.ReizigerDAOHibernate;
import nl.hu.dp.ovchip.domein.Reiziger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.SQLException;
import java.util.List;

public class Main {


    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
 * P2. Reiziger DAO: persistentie van een klasse
 *
 * Deze methode test de CRUD-functionaliteit van de Reiziger DAO
 *
 * @throws SQLException
 */
    private static void testReizigerDAO (ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

    }

    public static void main(String[] args) throws SQLException {
        ReizigerDAO rdao = new ReizigerDAOHibernate(sessionFactory);
        testReizigerDAO(rdao);
    }
}