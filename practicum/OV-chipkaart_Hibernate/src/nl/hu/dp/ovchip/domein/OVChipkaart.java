package nl.hu.dp.ovchip.domein;
import javax.persistence.*;
import java.sql.Date;

import java.sql.Date;

@Entity
@Table(name = "OV_Chipkaart")
public class OVChipkaart {
    @Id
    @Column(name = "kaart_nummer")
    private int id;

    @Column(name = "geldig_tot")
    private Date geldig_tot;

    @Column(name = "klasse")
    private int klasse;

    @Column(name = "saldo")
    private double saldo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger;

    public OVChipkaart(int id, Date geldig_tot, int klasse, double saldo, Reiziger reiziger) {
        this.id = id;
        this.geldig_tot = geldig_tot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;
    }

    public OVChipkaart() {

    }

    @Override
    public String toString() {
        return "OVChipkaart{" +
                "id=" + id +
                ", geldig_tot=" + geldig_tot +
                ", klasse=" + klasse +
                ", saldo=" + saldo +
                ", reiziger=" + reiziger +
                '}';
    }
}
