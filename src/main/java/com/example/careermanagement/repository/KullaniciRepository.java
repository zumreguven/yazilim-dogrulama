package com.example.careermanagement.repository;

import com.example.careermanagement.entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KullaniciRepository extends JpaRepository<Kullanici, Long> {
    Optional<Kullanici> findByKullaniciAdi(String kullaniciAdi);
    Boolean existsByKullaniciAdi(String kullaniciAdi);
    Boolean existsByEposta(String eposta);
}