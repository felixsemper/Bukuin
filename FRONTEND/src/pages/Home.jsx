import React from 'react';
import BookSearch from '../components/BookSearch';

const Home = () => {
    return (
        <div className="p-4">
            <h2 className="text-2xl font-bold mb-4">Welcome to Digital Library</h2>
            <BookSearch />
        </div>
    );
};

export default Home;