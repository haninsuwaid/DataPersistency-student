package nl.hu.dp.ovchip;

import nl.hu.dp.ovchip.DAO.ReizigerDAO;

import nl.hu.dp.ovchip.DAO.ReizigerDAOHibernate;
import nl.hu.dp.ovchip.domein.Reiziger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.SQLException;
import java.sql.Date;
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

        Reiziger reiziger = rdao.findById(4);
        System.out.println("[Test] ReizigerDAO.findById() geeft de volgende reiziger:");
        System.out.println(reiziger);
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(78, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");
        System.out.println();

        // test gebruiker/s vinden bij geboorte datum
        System.out.println("[Test] ReizigerDAO.findByGbdatum() geeft de volgende reizigers:");
        Date geboorteDatum = new Date(102, 11, 3);
        List<Reiziger> geboorteDatumReizigers = rdao.findByGbdatum(geboorteDatum);
        for (Reiziger r : geboorteDatumReizigers) {
            System.out.println(r);
        }
        System.out.println();


        // test gebruiker aanpassen
        System.out.println("[Test] ReizigerDAO.update() doet het volgende:");
        System.out.println("Reiziger voor het aanpassen van de gegevens " + reiziger);
        reiziger.setAchternaam("vander");
        reiziger.setVoorletters("H");
        rdao.update(reiziger);
        System.out.println("Reiziger na het aanpassen van de gegevens " + reiziger);
        System.out.println();


        // test gebruiker uit db verwijderen
        System.out.println("[Test] ReizigerDAO.delete():");
        rdao.delete(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");
        System.out.println();
    }

    public static void main(String[] args) throws SQLException {
        ReizigerDAO rdao = new ReizigerDAOHibernate(sessionFactory);
        testReizigerDAO(rdao);
    }
}