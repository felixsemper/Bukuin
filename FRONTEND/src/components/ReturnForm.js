import React, { useState } from 'react';
import axios from 'axios';

const ReturnForm = ({ peminjamanId }) => {
    const [error, setError] = useState('');

    const handleReturn = async () => {
        try {
            await axios.post(`http://localhost:8080/api/return/${peminjamanId}`, {}, {
                headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }
            });
            alert('Book returned successfully');
        } catch (err) {
            setError('Failed to return book');
        }
    };

    return (
        <div className="p-4">
            {error && <p className="text-red-500 mb-4">{error}</p>}
            <button
                onClick={handleReturn}
                className="bg-blue-500 text-white p-2 rounded"
            >
                Return Book
            </button>
        </div>
    );
};

export default ReturnForm;