// src/main/java/com/example/careermanagement/entity/Kullanici.java
package com.example.careermanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "kullanicilar",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "kullanici_adi"),
                @UniqueConstraint(columnNames = "eposta")
        })
@Getter
@Setter
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

    public Kullanici() {
    }

    public Kullanici(String kullaniciAdi, String eposta, String sifre, String adSoyad) {
        this.kullaniciAdi = kullaniciAdi;
        this.eposta = eposta;
        this.sifre = sifre;
        this.adSoyad = adSoyad;
    }
}