package org.selfproject.cinema_app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "BiletAl")
public class BiletAlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String secilenSinema;
    private String secilenTarih;
    private String secilenSeans;
    private int ogrenciBiletSayisi;
    private int tamBiletSayisi;

    public BiletAlEntity() {
    }

    public BiletAlEntity(String secilenSinema, String secilenTarih, String secilenSeans, int ogrenciBiletSayisi, int tamBiletSayisi) {
        this.secilenSinema = secilenSinema;
        this.secilenTarih = secilenTarih;
        this.secilenSeans = secilenSeans;
        this.ogrenciBiletSayisi = ogrenciBiletSayisi;
        this.tamBiletSayisi = tamBiletSayisi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSecilenSinema() {
        return secilenSinema;
    }

    public void setSecilenSinema(String secilenSinema) {
        this.secilenSinema = secilenSinema;
    }

    public String getSecilenTarih() {
        return secilenTarih;
    }

    public void setSecilenTarih(String secilenTarih) {
        this.secilenTarih = secilenTarih;
    }

    public String getSecilenSeans() {
        return secilenSeans;
    }

    public void setSecilenSeans(String secilenSeans) {
        this.secilenSeans = secilenSeans;
    }

    public int getOgrenciBiletSayisi() {
        return ogrenciBiletSayisi;
    }

    public void setOgrenciBiletSayisi(int ogrenciBiletSayisi) {
        this.ogrenciBiletSayisi = ogrenciBiletSayisi;
    }

    public int getTamBiletSayisi() {
        return tamBiletSayisi;
    }

    public void setTamBiletSayisi(int tamBiletSayisi) {
        this.tamBiletSayisi = tamBiletSayisi;
    }

    @Override
    public String toString() {
        return "BiletAlEntity{" +
                "id=" + id +
                ", secilenSinema='" + secilenSinema + '\'' +
                ", secilenTarih='" + secilenTarih + '\'' +
                ", secilenSeans='" + secilenSeans + '\'' +
                ", ogrenciBiletSayisi=" + ogrenciBiletSayisi +
                ", tamBiletSayisi=" + tamBiletSayisi +
                '}';
    }
}