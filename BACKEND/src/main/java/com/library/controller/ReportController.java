package com.library.controller;

import com.library.entity.Laporan;
import com.library.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @PostMapping("/{jenis}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Laporan> generateReport(@PathVariable Laporan.Jenis jenis) {
        return ResponseEntity.ok(reportService.generateReport(jenis));
    }
}