package nl.hu.dp.ovchip;

import nl.hu.dp.ovchip.DAO.*;

import nl.hu.dp.ovchip.domein.Adres;
import nl.hu.dp.ovchip.domein.OVChipkaart;
import nl.hu.dp.ovchip.domein.Product;
import nl.hu.dp.ovchip.domein.Reiziger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    private static String gbdatum = "1981-03-14";
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

        Reiziger sietske = new Reiziger(78, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
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

        reizigers = rdao.findAll();
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
        reiziger.setAchternaam("Kaas");
        reiziger.setVoorletters("K");
        rdao.update(reiziger);
        System.out.println("Reiziger na het aanpassen van de gegevens " + reiziger);
        System.out.println();


        //test gebruiker uit db verwijderen
        System.out.println("[Test] ReizigerDAO.delete():");
        System.out.println(reizigers.size() + " reizigers\n");
        rdao.delete(sietske);

        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

    }

    /**
     * P2. Adres DAO: persistentie van een klasse
     *
     * Deze methode test de CRUD-functionaliteit van de Adres DAO
     *
     * @throws SQLException
     */
    private static void testAdresDAO (AdresDAO adao, ReizigerDAO rdao) throws SQLException {

        System.out.println("\n---------- Test AdresDAO -------------");
        System.out.println("[Test] AdresDAO.findAll() geeft de volgende Adressen:");
        List<Adres> adressen = adao.findAll();
        for (Adres a : adressen) {
            System.out.println(a);
        }
        System.out.println();
        Reiziger newReiziger = new Reiziger(44, "H", "", "Suwaid", java.sql.Date.valueOf(gbdatum));
//        rdao.save(newReiziger);
        Adres newAdres = new Adres();
        newAdres.setHuisnummer("22");
        newAdres.setPostcode("1122pp");
        newAdres.setWoonplaats("Leiden");
        newAdres.setStraat("Lelijkebenen");
        newAdres.setId(88);
        newAdres.setReiziger(newReiziger);

        System.out.println("[Test] AdresDAO.findByReiziger() geeft de volgende reiziger en adres:");
        System.out.println(newReiziger + " " + adao.findByReiziger(newReiziger));
        System.out.println();

        System.out.println("[Test] AdresDAO.update() doet het volgende:");
        System.out.println("Adres aanpassen ");
        newAdres.setHuisnummer("10");
        adao.update(newAdres);
        System.out.println("Adres na het aanpassen van de gegevens " + newReiziger + " " + newAdres);
        System.out.println();


        System.out.println("[Test] ReizigerDAO.delete():");
        System.out.println(adressen.size() + " aantal adressen voor het verwijderen\n");
        adao.delete(newAdres);
        adressen = adao.findAll();
        System.out.println(adressen.size() + " aantal adressen na het verwijderen\n");
        System.out.println();

        adao.save(newAdres);
    }

    /**
     * P2. OVChipKaartDAO DAO: persistentie van een klasse
     *
     * Deze methode test de CRUD-functionaliteit van de OVChipKaartDAO DAO
     *
     * @throws SQLException
     */
    private static void testOvChipKaartDAO (OVChipKaartDAO ovdao, ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test OvChipKaartDAO -------------");
        Reiziger reizigerforOvChipKaartTest = new Reiziger(45, "L", "", "Momo", java.sql.Date.valueOf(gbdatum));

        String geldigTot = "2025-03-14";
        OVChipkaart ovChipkaartTest = new OVChipkaart(45, java.sql.Date.valueOf(geldigTot), 2, 5.00, reizigerforOvChipKaartTest);


        System.out.println("[Test] OvChipKaartDAO.findAll() geeft de volgende Ovs:");
        List<OVChipkaart> kaarten = ovdao.findAll();
        for (OVChipkaart k : kaarten) {
            System.out.println(k);
        }
        System.out.println();

        System.out.println("[Test] OvChipKaartDAO.update() doet het volgende: \n");
        System.out.println("OvChipKaart aanpassen \n");
        System.out.println("OvChipKaart voor het aanpassen van de gegevens " + ovChipkaartTest);
        ovChipkaartTest.setKlasse(1);
        ovdao.update(ovChipkaartTest);
        System.out.println("OvChipKaart na het aanpassen van de gegevens " + ovChipkaartTest);
        System.out.println();

        OVChipkaart ovChipkaartTest1 = new OVChipkaart(46, java.sql.Date.valueOf(geldigTot), 2, 7.00, reizigerforOvChipKaartTest);
//        ovdao.save(ovChipkaartTest1);
        OVChipkaart ovChipkaartTest2 = new OVChipkaart(47, java.sql.Date.valueOf(geldigTot), 2, 9.00, reizigerforOvChipKaartTest);
//        ovdao.save(ovChipkaartTest2);
        OVChipkaart ovChipkaartTest3 = new OVChipkaart(48, java.sql.Date.valueOf(geldigTot), 2, 10.00, reizigerforOvChipKaartTest);
//        ovdao.save(ovChipkaartTest3);

        System.out.println("[Test] OvChipKaartDAO.delete():");
        System.out.println(kaarten.size() + " aantal ovkaarten voor het verwijderen\n");
        ovdao.delete(ovChipkaartTest);
        kaarten = ovdao.findAll();
        System.out.println(kaarten.size() + " aantal ovkaarten na het verwijderen\n");
        System.out.println();

        System.out.println("[Test] OvChipKaartDAO.findByReiziger() geeft de volgende reiziger en ovkaarten:");
        System.out.println(ovdao.findByReiziger(reizigerforOvChipKaartTest));
        System.out.println();

        ovdao.save(ovChipkaartTest);
    }

    /**
     * P2. testProductDAO DAO: persistentie van een klasse
     *
     * Deze methode test de CRUD-functionaliteit van de testProductDAO DAO
     *
     * @throws SQLException
     */
    private static void testProductDAO (OVChipKaartDAO ovdao, ProductDAO pdao) throws SQLException {
        System.out.println("\n---------- Test testProductDAO -------------");
        Reiziger reizigerforOvChipKaartTest = new Reiziger(45, "L", "", "Momo", java.sql.Date.valueOf(gbdatum));

        String geldigTot = "2025-03-14";
        OVChipkaart ovChipkaartTest = new OVChipkaart(45, java.sql.Date.valueOf(geldigTot), 2, 5.00, reizigerforOvChipKaartTest);
//        ovdao.save(ovChipkaartTest);
        Product productTest = new Product(19, "studentenOv", 0.0, "Gratis reizen");
        Set<OVChipkaart> kaarten = new HashSet<>();
        kaarten.add(ovChipkaartTest);
        productTest.setOvChipkaarten(kaarten);

        System.out.println("[Test] ProductDAO.findAll() geeft de volgende produten:");
        List<Product> products = pdao.findAll();
        for (Product p : products) {
            System.out.println(p);
        }
        System.out.println();

        System.out.println("[Test] ProductDAO.findByOvkaart() geeft de volgende:");
        List<Product> productList = pdao.findByOVChipkaart(ovChipkaartTest);
        for (Product p : productList) {
            System.out.println(p);
        }
        System.out.println();

        System.out.println("[Test] ProductDAO.update() doet het volgende:");
        System.out.println("Product voor het aanpassen van de gegevens " + productTest);
        productTest.setName("Nieuwe_naam");
        productTest.setBeschrijving("Niet meer gratis");
        pdao.update(productTest);
        System.out.println("Product na het aanpassen van de gegevens " + productTest);
        System.out.println();

        System.out.println("[Test] ProductDAO.delete():");
        System.out.println(products.size() + " aantal products voor het verwijderen\n");
        pdao.delete(productTest);
        products = pdao.findAll();
        System.out.println(products.size() + " aantal products na het verwijderen\n");
        System.out.println();

        pdao.save(productTest);

    }


    public static void main(String[] args) throws SQLException {
        ReizigerDAO rdao = new ReizigerDAOHibernate(sessionFactory);
        AdresDAO adao = new AdresDAOHibernate(sessionFactory);
        OVChipKaartDAO ovdao = new OVChipKaartDAOHibernate(sessionFactory);
        ProductDAO pdao = new ProductDAOHibernate(sessionFactory);
//        testReizigerDAO(rdao);
//        testAdresDAO(adao, rdao);
//        testOvChipKaartDAO(ovdao, rdao);
        testProductDAO(ovdao, pdao);

    }
}