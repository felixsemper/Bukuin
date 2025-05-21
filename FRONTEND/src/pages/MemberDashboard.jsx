import React, { useState } from 'react';
import axios from 'axios';

const ReviewForm = ({ bookId, userId }) => {
    const [formData, setFormData] = useState({ rating: 1, komentar: '' });
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post('http://localhost:8080/api/reviews', {
                buku: { idBuku: bookId },
                anggota: { id: userId },
                ...formData
            }, {
                headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }
            });
            setFormData({ rating: 1, komentar: '' });
            alert('Review submitted');
        } catch (err) {
            setError('Failed to submit review');
        }
    };

    return (
        <div className="p-4">
            <h2 className="text-xl font-bold mb-4">Submit Review</h2>
            {error && <p className="text-red-500 mb-4">{error}</p>}
            <form onSubmit={handleSubmit}>
                <div className="mb-4">
                    <label className="block text-gray-700">Rating (1-5)</label>
                    <input
                        type="number"
                        min="1"
                        max="5"
                        value={formData.rating}
                        onChange={(e) => setFormData({ ...formData, rating: parseInt(e.target.value) })}
                        className="border p-2 rounded w-full"
                        required
                    />
                </div>
                <div className="mb-4">
                    <label className="block text-gray-700">Comment</label>
                    <textarea
                        value={formData.komentar}
                        onChange={(e) => setFormData({ ...formData, komentar: e.target.value })}
                        className="border p-2 rounded w-full"
                    />
                </div>
                <button type="submit" className="bg-blue-500 text-white p-2 rounded">
                    Submit Review
                </button>
            </form>
        </div>
    );
};

export default ReviewForm;