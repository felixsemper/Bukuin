package com.library.controller;

import com.library.entity.Pengembalian;
import com.library.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/return")
public class ReturnController {
    @Autowired
    private ReturnService returnService;

    @PostMapping("/{peminjamanId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ANGGOTA')")
    public ResponseEntity<Pengembalian> returnBook(@PathVariable String peminjamanId) {
        return ResponseEntity.ok(returnService.returnBook(peminjamanId));
    }
}