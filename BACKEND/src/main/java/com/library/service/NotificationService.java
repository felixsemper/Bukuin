package com.library.service;

import com.library.entity.Notifikasi;
import com.library.repository.NotifikasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {
    @Autowired
    private NotifikasiRepository notifikasiRepository;

    public Notifikasi sendNotification(Notifikasi notifikasi) {
        notifikasi.setIdNotifikasi(UUID.randomUUID().toString());
        notifikasi.setTanggal(new Date());
        notifikasi.setStatusBaca(false);
        return notifikasiRepository.save(notifikasi);
    }

    public List<Notifikasi> getNotificationsByUser(String userId) {
        return notifikasiRepository.findByUserId(userId);
    }

    public Notifikasi markAsRead(String id) {
        Notifikasi notifikasi = notifikasiRepository.findById(id).orElseThrow();
        notifikasi.setStatusBaca(true);
        return notifikasiRepository.save(notifikasi);
    }
}