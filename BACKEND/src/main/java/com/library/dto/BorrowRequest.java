package com.library.dto;

import lombok.Data;

@Data
public class BorrowRequest {
    private String idBuku;
    private String idAnggota;
}