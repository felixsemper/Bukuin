package com.library.service;

import com.library.entity.Buku;
import com.library.repository.BukuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {
    @Autowired
    private BukuRepository bukuRepository;

    public Buku addBook(Buku buku) {
        buku.setIdBuku(UUID.randomUUID().toString());
        return bukuRepository.save(buku);
    }

    public List<Buku> searchBooks(String query) {
        return bukuRepository.findByJudulContainingIgnoreCaseOrPenulisContainingIgnoreCaseOrIsbn(query, query, query);
    }

    public Buku updateBook(String id, Buku buku) {
        Buku existing = bukuRepository.findById(id).orElseThrow();
        existing.setJudul(buku.getJudul());
        existing.setPenulis(buku.getPenulis());
        existing.setIsbn(buku.getIsbn());
        existing.setTahunTerbit(buku.getTahunTerbit());
        existing.setSinopsis(buku.getSinopsis());
        existing.setStatus(buku.getStatus());
        existing.setCoverPath(buku.getCoverPath());
        existing.setKategori(buku.getKategori());
        return bukuRepository.save(existing);
    }

    public void deleteBook(String id) {
        bukuRepository.deleteById(id);
    }
}