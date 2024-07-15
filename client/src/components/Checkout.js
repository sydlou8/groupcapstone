import React from 'react';
import { Container, Button } from 'react-bootstrap';
import { useHistory } from 'react-router-dom';

const Checkout = ({ cartItems }) => {
  const history = useHistory();

  const handleCheckout = () => {
    // Handle the submission to your backend
    console.log('Order submitted!', cartItems);
    // Redirect the user to a confirmation page or reset the cart
    history.push('/confirmation');
  };

  return (
    <Container>
      <h1>Checkout</h1>
      <ListGroup>
        {cartItems.map((item, index) => (
          <ListGroup.Item key={index}>
            {item.type ? `${item.type}: ${item.item}` : `Sandwich: ${JSON.stringify(item)}`}
          </ListGroup.Item>
        ))}
      </ListGroup>
      <Button variant="primary" onClick={handleCheckout}>Submit Order</Button>
    </Container>
  );
};

export default Checkout;
