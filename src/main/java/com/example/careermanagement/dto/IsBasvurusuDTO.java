package com.example.careermanagement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IsBasvurusuDTO {
    private Long id;

    @NotNull
    private Long ilanId;

    @NotNull
    private Long basvuranId;

    private String ozgecmis;

    private String durum;

    private String degerlendirmeNotu;
}