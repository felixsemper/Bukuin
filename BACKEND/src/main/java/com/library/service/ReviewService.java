package com.library.service;

import com.library.entity.Ulasan;
import com.library.repository.UlasanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {
    @Autowired
    private UlasanRepository ulasanRepository;

    public Ulasan submitReview(Ulasan ulasan) {
        ulasan.setIdUlasan(UUID.randomUUID().toString());
        return ulasanRepository.save(ulasan);
    }

    public List<Ulasan> getReviewsByBook(String bookId) {
        return ulasanRepository.findByBukuIdBuku(bookId);
    }
}