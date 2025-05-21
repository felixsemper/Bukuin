package com.library.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "anggota")
@Data
public class Anggota extends User {
    private String noAnggota;
    private Boolean statusAktif;
}