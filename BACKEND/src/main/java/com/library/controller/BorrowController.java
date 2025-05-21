package com.library.controller;

import com.library.entity.Peminjaman;
import com.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;

    @PostMapping
    @PreAuthorize("hasRole('ANGGOTA')")
    public ResponseEntity<Peminjaman> requestBorrow(@RequestBody Peminjaman peminjaman) {
        return ResponseEntity.ok(borrowService.requestBorrow(peminjaman));
    }

    @PutMapping("/approve/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Peminjaman> approveBorrow(@PathVariable String id) {
        return ResponseEntity.ok(borrowService.approveBorrow(id));
    }

    @PutMapping("/reject/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Peminjaman> rejectBorrow(@PathVariable String id) {
        return ResponseEntity.ok(borrowService.rejectBorrow(id));
    }
}