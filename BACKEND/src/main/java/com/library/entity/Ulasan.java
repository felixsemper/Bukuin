package com.library.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ulasan")
@Data
public class Ulasan {
    @Id
    private String idUlasan;

    @ManyToOne
    @JoinColumn(name = "id_buku")
    private Buku buku;

    @ManyToOne
    @JoinColumn(name = "id_anggota")
    private Anggota anggota;

    private Integer rating;
    private String komentar;
}