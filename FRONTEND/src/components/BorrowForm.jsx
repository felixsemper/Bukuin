import React, { useState } from 'react';
import axios from 'axios';

const BorrowForm = ({ bookId, userId }) => {
    const [error, setError] = useState('');

    const handleBorrow = async () => {
        try {
            await axios.post('http://localhost:8080/api/borrow', {
                idBuku: bookId,
                idAnggota: userId
            }, {
                headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }
            });
            alert('Borrow request submitted');
        } catch (err) {
            setError('Failed to submit borrow request');
        }
    };

    return (
        <div className="p-4">
            {error && <p className="text-red-500 mb-4">{error}</p>}
            <button
                onClick={handleBorrow}
                className="bg-green-500 text-white p-2 rounded"
            >
                Request Borrow
            </button>
        </div>
    );
};

export default BorrowForm;