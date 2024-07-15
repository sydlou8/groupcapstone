import React from 'react';
import { Container, ListGroup, Button } from 'react-bootstrap';

const Cart = ({ cartItems, handleRemoveItem }) => {
  return (
    <Container>
      <ListGroup>
        {cartItems.map((item, index) => (
          <ListGroup.Item key={index}>
            {item.type ? `${item.type}: ${item.item}` : `Sandwich: ${JSON.stringify(item)}`}
            <Button variant="danger" onClick={() => handleRemoveItem(index)}>Remove</Button>
          </ListGroup.Item>
        ))}
      </ListGroup>
    </Container>
  );
};

export default Cart;