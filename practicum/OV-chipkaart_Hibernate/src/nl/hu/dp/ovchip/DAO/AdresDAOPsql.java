package nl.hu.dp.ovchip.DAO;

import nl.hu.dp.ovchip.domein.Adres;
import nl.hu.dp.ovchip.domein.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO {

    private Connection connection = null;

    public AdresDAOPsql(Connection conn) throws SQLException {
        this.connection = conn;
    }


    @Override
    public boolean save(Adres adres) {
        try {
            String saveQuery = "INSERT INTO Adres (postcode, huisnummer, straat, woonplaats) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(saveQuery);
            statement.setString(1, adres.getPostcode());
            statement.setString(2, adres.getHuisnummer());
            statement.setString(3, adres.getStraat());
            statement.setString(4, adres.getWoonplaats());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException throwables) {
            System.err.println("SQLException: " + throwables.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Adres adres) {
        try {
            String updateQuery = "UPDATE Adres SET postcode = ?, huisnummer = ?, straat = ?, woonplaats = ? WHERE adres_id = ?";
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, adres.getPostcode());
            statement.setString(2, adres.getHuisnummer());
            statement.setString(3, adres.getStraat());
            statement.setString(4, adres.getWoonplaats());
            statement.setInt(5, adres.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException throwables) {
            System.err.println("SQLException: " + throwables.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Adres adres) {
        try {
            String deleteQuery = "DELETE FROM adres WHERE adres_id = ?";
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setInt(1, adres.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException throwables) {
            System.err.println("SQLException: " + throwables.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        try {
            String findByReizigerQuery = "SELECT * FROM adres WHERE reiziger_id = ?";
            PreparedStatement statement = connection.prepareStatement(findByReizigerQuery);
            statement.setInt(1, reiziger.getId());
            ResultSet resultaat = statement.executeQuery();
            if (resultaat.next()) {
                int id = resultaat.getInt("adres_id");
                String postcode = resultaat.getString("postcode");
                String huisnummer = resultaat.getString("huisnummer");
                String straat = resultaat.getString("straat");
                String woonplaats = resultaat.getString("woonplaats");
                Adres adres = new Adres();
                adres.setId(id);
                adres.setPostcode(postcode);
                adres.setStraat(straat);
                adres.setHuisnummer(huisnummer);
                adres.setWoonplaats(woonplaats);
                return adres;
            }
        } catch (Exception exc) {
            exc.printStackTrace();

        }
        return null;
    }

    @Override
    public List<Adres> findAll() {
        try {
            List<Adres> adressen = new ArrayList<>();
            String selectAllQeury = "SELECT * FROM adres";
            PreparedStatement statement = connection.prepareStatement(selectAllQeury);
            ResultSet resultaat = statement.executeQuery();
            while (resultaat.next()) {
                int id = resultaat.getInt("adres_id");
                String postcode = resultaat.getString("postcode");
                String huisnummer = resultaat.getString("huisnummer");
                String straat = resultaat.getString("straat");
                String woonplaats = resultaat.getString("woonplaats");

                Adres adres = new Adres();
                adres.setId(id);
                adres.setPostcode(postcode);
                adres.setStraat(straat);
                adres.setHuisnummer(huisnummer);
                adres.setWoonplaats(woonplaats);
                adressen.add(adres);

            }
            return adressen;
        } catch (SQLException throwables) {
            System.err.println("SQLException: " + throwables.getMessage());

        }
        return null;
    }

}
