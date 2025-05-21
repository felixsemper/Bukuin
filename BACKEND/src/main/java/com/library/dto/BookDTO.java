package com.library.dto;

import lombok.Data;

@Data
public class BookDTO {
    private String idBuku;
    private String judul;
    private String penulis;
    private String isbn;
    private Integer tahunTerbit;
    private String sinopsis;
    private String status;
    private String coverPath;
    private String idKategori;
}