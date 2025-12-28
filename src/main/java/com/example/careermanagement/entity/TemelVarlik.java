package com.example.careermanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class TemelVarlik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kimlik;

    @CreatedDate
    @Column(name = "olusturma_tarihi", nullable = false, updatable = false)
    private LocalDateTime olusturmaTarihi;

    @LastModifiedDate
    @Column(name = "guncelleme_tarihi")
    private LocalDateTime guncellemeTarihi;


    public Long getKimlik() {
        return this.kimlik;
    }
}