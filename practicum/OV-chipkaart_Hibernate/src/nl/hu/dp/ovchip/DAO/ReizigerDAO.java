package nl.hu.dp.ovchip.DAO;

import nl.hu.dp.ovchip.domein.Reiziger;
import java.sql.Date;
import java.util.List;

public interface ReizigerDAO {
    boolean save(Reiziger reiziger);
    boolean update(Reiziger reiziger);
    boolean delete(Reiziger reiziger);
    List<Reiziger> findAll();
    Reiziger findById(int id);
    List<Reiziger> findByGbdatum(Date datum);
}
