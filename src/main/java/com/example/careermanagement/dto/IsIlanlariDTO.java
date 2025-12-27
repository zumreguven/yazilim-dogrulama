package com.example.careermanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class IsIlanlariDTO {
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String baslik;

    private String aciklama;

    @NotBlank
    @Size(max = 100)
    private String sirket;

    @NotBlank
    @Size(max = 100)
    private String konum;

    private LocalDate sonBasvuruTarihi;

    @NotNull
    private Long yayinlayanId;
}