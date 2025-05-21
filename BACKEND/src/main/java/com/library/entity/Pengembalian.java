package com.library.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "pengembalian")
@Data
public class Pengembalian {
    @Id
    private String idPengembalian;

    @OneToOne
    @JoinColumn(name = "id_peminjaman")
    private Peminjaman peminjaman;

    private Date tanggalKembali;
    private Double denda;
}