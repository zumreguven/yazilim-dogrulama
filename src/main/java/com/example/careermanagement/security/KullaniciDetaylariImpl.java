package com.example.careermanagement.security;

import com.example.careermanagement.entity.Kullanici;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class KullaniciDetaylariImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String kullaniciAdi;
    private String eposta;

    @JsonIgnore
    private String sifre;

    private Collection<? extends GrantedAuthority> yetkiler;

    public KullaniciDetaylariImpl(Long id, String kullaniciAdi, String eposta, String sifre,
                                  Collection<? extends GrantedAuthority> yetkiler) {
        this.id = id;
        this.kullaniciAdi = kullaniciAdi;
        this.eposta = eposta;
        this.sifre = sifre;
        this.yetkiler = yetkiler;
    }

    public static KullaniciDetaylariImpl build(Kullanici kullanici) {
        List<GrantedAuthority> yetkiler = kullanici.getRoller().stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getAdi().name()))
                .collect(Collectors.toList());

        return new KullaniciDetaylariImpl(
                kullanici.getId(),
                kullanici.getKullaniciAdi(),
                kullanici.getEposta(),
                kullanici.getSifre(),
                yetkiler);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return yetkiler;
    }

    public Long getId() {
        return id;
    }

    public String getEposta() {
        return eposta;
    }

    @Override
    public String getPassword() {
        return sifre;
    }

    @Override
    public String getUsername() {
        return kullaniciAdi;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        KullaniciDetaylariImpl kullanici = (KullaniciDetaylariImpl) o;
        return Objects.equals(id, kullanici.id);
    }
}