package com.example.careermanagement.security;

import com.example.careermanagement.entity.Kullanici;
import com.example.careermanagement.repository.KullaniciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KullaniciDetayServisi implements UserDetailsService {
    @Autowired
    KullaniciRepository kullaniciRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String kullaniciAdi) throws UsernameNotFoundException {
        Kullanici kullanici = kullaniciRepository.findByKullaniciAdi(kullaniciAdi)
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + kullaniciAdi));

        return KullaniciDetaylariImpl.build(kullanici);
    }
}