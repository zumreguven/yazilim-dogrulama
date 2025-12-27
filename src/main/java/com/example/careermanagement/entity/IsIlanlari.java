package com.example.careermanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "is_ilanlari")
@Getter
@Setter
public class IsIlanlari extends TemelVarlik {
    @NotBlank
    @Size(max = 100)
    private String baslik;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String aciklama;

    @NotBlank
    @Size(max = 100)
    private String sirket;

    @NotBlank
    @Size(max = 100)
    private String konum;

    @Column(name = "son_basvuru_tarihi")
    private LocalDate sonBasvuruTarihi;

    private boolean aktif = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "yayinlayan_id", nullable = false)
    private Kullanici yayinlayan;
}