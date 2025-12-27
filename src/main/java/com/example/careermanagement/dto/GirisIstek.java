package com.example.careermanagement.dto;

import jakarta.validation.constraints.NotBlank;

public class GirisIstek {
    @NotBlank
    private String kullaniciAdi;

    @NotBlank
    private String sifre;

    // Getter ve Setter'lar
    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
}