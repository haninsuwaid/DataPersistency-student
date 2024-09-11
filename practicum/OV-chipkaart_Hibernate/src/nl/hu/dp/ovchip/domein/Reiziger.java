package nl.hu.dp.ovchip.domein;


import javax.persistence.*;
import java.sql.Date;
@Entity(name = "Reiziger")
@Table(name = "Reiziger")
public class Reiziger {
    @Id
    @GeneratedValue
    private int id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;
    @OneToOne(
            mappedBy = "Reiziger",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Adres adres;

    public Reiziger() {}

    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String getNaam() {
        if (tussenvoegsel == null || tussenvoegsel.isEmpty()){
            return voorletters + " " + achternaam;
        }
        else {
            return voorletters + " " + tussenvoegsel + " " + achternaam;
        }
    }

    public String toString() {
        return "Reiziger" +
                "id= " + id +
                ", voorletters= '" + voorletters + '\'' +
                ", tussenvoegsel= '" + tussenvoegsel + '\'' +
                ", achternaam= '" + achternaam + '\'' +
                ", geboortedatum= " + geboortedatum;
    }
}
