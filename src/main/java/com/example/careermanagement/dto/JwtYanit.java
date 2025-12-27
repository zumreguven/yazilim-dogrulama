package com.example.careermanagement.dto;

import java.util.List;

public class JwtYanit {
    private String token;
    private String tip = "Bearer";
    private Long id;
    private String kullaniciAdi;
    private String eposta;
    private List<String> roller;

    public JwtYanit(String token, Long id, String kullaniciAdi, String eposta, List<String> roller) {
        this.token = token;
        this.id = id;
        this.kullaniciAdi = kullaniciAdi;
        this.eposta = eposta;
        this.roller = roller;
    }

    // Getter ve Setter'lar
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public List<String> getRoller() {
        return roller;
    }

    public void setRoller(List<String> roller) {
        this.roller = roller;
    }
}