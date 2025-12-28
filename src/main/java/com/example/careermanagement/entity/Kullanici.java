package com.example.careermanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "kullanicilar",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "kullanici_adi"),
                @UniqueConstraint(columnNames = "eposta")
        })
public class Kullanici extends TemelVarlik {
    @NotBlank
    @Size(max = 20)
    @Column(name = "kullanici_adi")
    private String kullaniciAdi;

    @NotBlank
    @Size(max = 50)
    @Email
    private String eposta;

    @NotBlank
    @Size(max = 120)
    private String sifre;

    @NotBlank
    @Size(max = 50)
    private String adSoyad;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "kullanici_rolleri",
            joinColumns = @JoinColumn(name = "kullanici_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roller = new HashSet<>();

    // Getter ve Setter'lar
    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getEposta() {
        return eposta;
    }

    public Long getKimlik() {
        return super.getKimlik();
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public Set<Rol> getRoller() {
        return roller;
    }

    public void setRoller(Set<Rol> roller) {
        this.roller = roller;
    }

    public Kullanici() {
    }

    public Kullanici(String kullaniciAdi, String eposta, String sifre, String adSoyad) {
        this.kullaniciAdi = kullaniciAdi;
        this.eposta = eposta;
        this.sifre = sifre;
        this.adSoyad = adSoyad;
    }
}