package nl.hu.dp.ovchip.DAO;
import nl.hu.dp.ovchip.domein.OVChipkaart;
import nl.hu.dp.ovchip.domein.Product;
import java.util.List;

public interface ProductDAO {
    boolean save(Product product);

    boolean update(Product product);

    boolean delete(Product product);

    List<Product> findAll();
    List<Product> findByOVChipkaart(OVChipkaart ovChipkaart);
}
