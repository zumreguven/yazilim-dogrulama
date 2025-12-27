package com.example.careermanagement.repository;

import com.example.careermanagement.entity.BasvuruDurumu;
import com.example.careermanagement.entity.IsBasvurusu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IsBasvurusuRepository extends JpaRepository<IsBasvurusu, Long> {
    List<IsBasvurusu> findByBasvuranId(Long basvuranId);
    List<IsBasvurusu> findByIlanId(Long ilanId);
    List<IsBasvurusu> findByIlanYayinlayanId(Long yayinlayanId);
    List<IsBasvurusu> findByDurum(BasvuruDurumu durum);
}