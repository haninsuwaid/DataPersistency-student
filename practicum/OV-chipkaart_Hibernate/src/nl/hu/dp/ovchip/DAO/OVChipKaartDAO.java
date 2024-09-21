package nl.hu.dp.ovchip.DAO;

import nl.hu.dp.ovchip.domein.Adres;
import nl.hu.dp.ovchip.domein.OVChipkaart;
import nl.hu.dp.ovchip.domein.Reiziger;

import java.util.List;

public interface OVChipKaartDAO {
    boolean save(OVChipkaart ovchipkaart);
    boolean update(OVChipkaart ovchipkaart);
    boolean delete(OVChipkaart ovchipkaart);
    OVChipkaart findByReiziger(Reiziger reiziger);
    List<OVChipkaart> findAll();
}
