package com.example.careermanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.Set;

@Data
public class KayitIstek {
    @NotBlank
    @Size(min = 3, max = 20)
    private String kullaniciAdi;

    @NotBlank
    @Size(max = 50)
    @Email
    private String eposta;

    @NotBlank
    @Size(min = 6, max = 40)
    private String sifre;

    @NotBlank
    @Size(max = 50)
    private String adSoyad;

    private Set<String> roller;
}