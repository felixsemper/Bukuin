import React, { useState, useEffect } from 'react';
import axios from 'axios';

const NotificationList = ({ userId }) => {
    const [notifications, setNotifications] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        const fetchNotifications = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/notifications/user/${userId}`, {
                    headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }
                });
                setNotifications(response.data);
            } catch (err) {
                setError('Failed to fetch notifications');
            }
        };
        fetchNotifications();
    }, [userId]);

    const markAsRead = async (id) => {
        try {
            await axios.put(`http://localhost:8080/api/notifications/read/${id}`, {}, {
                headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }
            });
            setNotifications(notifications.map(n => n.idNotifikasi === id ? { ...n, statusBaca: true } : n));
        } catch (err) {
            setError('Failed to mark notification as read');
        }
    };

    return (
        <div className="p-4">
            <h2 className="text-xl font-bold mb-4">Notifications</h2>
            {error && <p className="text-red-500 mb-4">{error}</p>}
            <ul className="space-y-2">
                {notifications.map((notification) => (
                    <li key={notification.idNotifikasi} className="border p-4 rounded flex justify-between">
                        <div>
                            <p>{notification.pesan}</p>
                            <p className="text-sm text-gray-500">{new Date(notification.tanggal).toLocaleDateString()}</p>
                        </div>
                        {!notification.statusBaca && (
                            <button
                                onClick={() => markAsRead(notification.idNotifikasi)}
                                className="bg-blue-500 text-white p-2 rounded"
                            >
                                Mark as Read
                            </button>
                        )}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default NotificationList;