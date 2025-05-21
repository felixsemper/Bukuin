package com.library.repository;

import com.library.entity.Buku;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BukuRepository extends JpaRepository<Buku, String> {
    List<Buku> findByJudulContainingIgnoreCaseOrPenulisContainingIgnoreCaseOrIsbn(String judul, String penulis, String isbn);
}