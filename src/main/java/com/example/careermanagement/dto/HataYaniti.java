package com.example.careermanagement.dto;

import java.util.Date;

public class HataYaniti {
    private int durumKodu;
    private Date zamanDamgasi;
    private String mesaj;
    private String detay;

    public HataYaniti(Date zamanDamgasi, String mesaj, String detay, int durumKodu) {
        this.zamanDamgasi = zamanDamgasi;
        this.mesaj = mesaj;
        this.detay = detay;
        this.durumKodu = durumKodu;
    }

    // Getter ve Setter'lar
    public int getDurumKodu() {
        return durumKodu;
    }

    public void setDurumKodu(int durumKodu) {
        this.durumKodu = durumKodu;
    }

    public Date getZamanDamgasi() {
        return zamanDamgasi;
    }

    public void setZamanDamgasi(Date zamanDamgasi) {
        this.zamanDamgasi = zamanDamgasi;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public String getDetay() {
        return detay;
    }

    public void setDetay(String detay) {
        this.detay = detay;
    }
}