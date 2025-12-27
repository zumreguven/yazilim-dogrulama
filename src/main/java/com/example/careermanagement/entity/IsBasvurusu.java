package com.example.careermanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "is_basvurulari")
@Getter
@Setter
public class IsBasvurusu extends TemelVarlik {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ilan_id", nullable = false)
    private IsIlanlari ilan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basvuran_id", nullable = false)
    private Kullanici basvuran;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String ozgecmis;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BasvuruDurumu durum = BasvuruDurumu.BEKLEMEDE;

    @Column(columnDefinition = "TEXT")
    private String degerlendirmeNotu;
}