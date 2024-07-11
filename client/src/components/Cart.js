import React, { useState } from 'react';
import { Container, ListGroup, Button } from 'react-bootstrap';
import OrderForm from './OrderForm';
import Drinks from './Drinks';
import Chips from './Chips';

const Cart = () => {
  const [cartItems, setCartItems] = useState([]);
  const [selectedDrink, setSelectedDrink] = useState('');
  const [selectedChips, setSelectedChips] = useState('');

  const addToCart = (item) => {
    setCartItems([...cartItems, item]);
  };

  const handleDrinkChange = (drink) => {
    setSelectedDrink(drink);
    addToCart({ type: 'Drink', item: drink });
  };

  const handleChipsChange = (chips) => {
    setSelectedChips(chips);
    addToCart({ type: 'Chips', item: chips });
  };

  const handleRemoveItem = (index) => {
    const newCartItems = cartItems.filter((_, i) => i !== index);
    setCartItems(newCartItems);
  };

  return (
    <Container>
      <h1>Shopping Cart</h1>
      <OrderForm addToCart={addToCart} />
      <Drinks setSelectedDrink={handleDrinkChange} />
      <Chips setSelectedChips={handleChipsChange} />

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
