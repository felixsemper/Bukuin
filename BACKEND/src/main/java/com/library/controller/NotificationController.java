package com.library.controller;

import com.library.entity.Notifikasi;
import com.library.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Notifikasi> sendNotification(@RequestBody Notifikasi notifikasi) {
        return ResponseEntity.ok(notificationService.sendNotification(notifikasi));
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ANGGOTA')")
    public ResponseEntity<List<Notifikasi>> getNotificationsByUser(@PathVariable String userId) {
        return ResponseEntity.ok(notificationService.getNotificationsByUser(userId));
    }

    @PutMapping("/read/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ANGGOTA')")
    public ResponseEntity<Notifikasi> markAsRead(@PathVariable String id) {
        return ResponseEntity.ok(notificationService.markAsRead(id));
    }
}