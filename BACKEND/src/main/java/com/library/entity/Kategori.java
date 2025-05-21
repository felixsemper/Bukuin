package com.library.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "kategori")
@Data
public class Kategori {
    @Id
    private String idKategori;
    private String namaKategori;
}