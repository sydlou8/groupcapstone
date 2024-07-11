import React from 'react';
import { Container, Button } from 'react-bootstrap';
import { useHistory } from 'react-router-dom';
import ShoppingCart from './ShoppingCart';

const Checkout = () => {
  const history = useHistory();

  const handleCheckout = () => {
    // Code to handle the submission to backend
    console.log('Order submitted!');
    // After submission, redirect the user to confirmation page/reset the cart
    history.push('/confirmation');
  };

  return (
    <Container>
      <h1>Checkout</h1>
      <ShoppingCart />
      <Button variant="primary" onClick={handleCheckout}>Submit Order</Button>
    </Container>
  );
};

export default Checkout;
