package com.library.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "buku")
@Data
public class Buku {
    @Id
    private String idBuku;

    private String judul;
    private String penulis;
    private String isbn;
    private Integer tahunTerbit;
    private String sinopsis;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String coverPath;

    @ManyToOne
    @JoinColumn(name = "id_kategori")
    private Kategori kategori;

    public enum Status {
        TERSEDIA, DIPINJAM
    }
}