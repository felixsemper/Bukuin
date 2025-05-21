package com.library.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "peminjaman")
@Data
public class Peminjaman {
    @Id
    private String idPeminjaman;

    @ManyToOne
    @JoinColumn(name = "id_buku")
    private Buku buku;

    @ManyToOne
    @JoinColumn(name = "id_anggota")
    private Anggota anggota;

    private Date tanggalPinjam;
    private Date batasKembali;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        MENUNGGU, DISETUJUI, DITOLAK
    }
}