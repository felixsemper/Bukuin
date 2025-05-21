package com.library.controller;

import com.library.entity.Ulasan;
import com.library.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    @PreAuthorize("hasRole('ANGGOTA')")
    public ResponseEntity<Ulasan> submitReview(@RequestBody Ulasan ulasan) {
        return ResponseEntity.ok(reviewService.submitReview(ulasan));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Ulasan>> getReviewsByBook(@PathVariable String bookId) {
        return ResponseEntity.ok(reviewService.getReviewsByBook(bookId));
    }
}