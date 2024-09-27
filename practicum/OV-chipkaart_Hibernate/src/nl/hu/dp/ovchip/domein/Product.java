package nl.hu.dp.ovchip.domein;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Product")
public class Product {
    @Id
    @Column(name = "product_nummer")
    public int product_nummer;
    @Column(name = "naam")
    public String name;
    @Column(name = "prijs")
    public Double prijs;
    @Column(name = "beschrijving")
    public String beschrijving;


    public Product(int product_nummer, String name, Double prijs, String beschrijving) {
        this.product_nummer = product_nummer;
        this.name = name;
        this.prijs = prijs;
        this.beschrijving = beschrijving;
    }

    public Product() {

    }

    public int getProduct_nummer() {
        return product_nummer;
    }

    public String getName() {
        return name;
    }

    public Double getPrijs() {
        return prijs;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setProduct_nummer(int product_nummer) {
        this.product_nummer = product_nummer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrijs(Double prijs) {
        this.prijs = prijs;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_nummer=" + product_nummer +
                ", name='" + name + '\'' +
                ", prijs=" + prijs +
                ", beschrijving='" + beschrijving +
                '}';
    }
}


