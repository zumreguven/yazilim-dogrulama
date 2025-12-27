package com.example.careermanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roller")
@Getter
@Setter
public class Rol extends TemelVarlik {
    @Enumerated(EnumType.STRING)
    @Column(length = 20, unique = true)
    private RolTuru adi;

    public Rol() {
    }

    public Rol(RolTuru adi) {
        this.adi = adi;
    }
}