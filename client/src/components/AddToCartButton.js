import React from 'react';
import { Button } from 'react-bootstrap';
import { useCart } from '../context/CartContext';

const AddToCartButton = ({ item }) => {
    const { addToCart } = useCart();

    return (
        <Button variant="primary" onClick={() => addToCart(item)}>
            Add to Cart
        </Button>
    );
};

export default AddToCartButton;
