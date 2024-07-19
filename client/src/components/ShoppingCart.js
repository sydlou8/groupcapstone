import React from 'react';
import { ListGroup, ListGroupItem, Button } from 'react-bootstrap';
import { useCart } from '../context/CartContext';

const ShoppingCart = () => {
  const { state, removeFromCart } = useCart();

  const calculateTotalPrice = () => {
    return state.cartItems.reduce((total, item) => total + item.price, 0);
  };

  return (
    <div>
      <ListGroup>
        {state.cartItems.map((item, index) => (
          <ListGroupItem key={index}>
            {item.type}: {item.item} - ${item.price.toFixed(2)}
            <Button 
              variant="danger" 
              onClick={() => removeFromCart(index)}
              className="ms-2"
            >
              Remove
            </Button>
          </ListGroupItem>
        ))}
      </ListGroup>
      <h3>Total Price: ${calculateTotalPrice().toFixed(2)}</h3>
    </div>
  );
};

export default ShoppingCart;
