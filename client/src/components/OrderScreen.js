import React, { useState } from 'react';
import { Container, Row, Col, Button, Navbar, Nav } from 'react-bootstrap';
import { useHistory } from 'react-router-dom';
import Sandwich from '@/components/Sandwich';
import Drinks from '@/components/Drinks';
import Chips from '@/components/Chips';
import Cart from '@/components/Cart';

const OrderScreen = () => {
  const [cartItems, setCartItems] = useState([]);
  const [selectedDrink, setSelectedDrink] = useState('');
  const [selectedChips, setSelectedChips] = useState('');
  const history = useHistory();

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

  const handleProceedToCheckout = () => {
    history.push('/checkout', { cartItems });
  };

  const navigateToPastOrders = () => {
    history.push('/past-orders');
  };

  return (
    <Container>
      <Navbar bg="light" expand="lg">
        <Navbar.Brand href="#">Sandwich Shop</Navbar.Brand>
        <Nav className="mr-auto">
          <Nav.Link onClick={navigateToPastOrders}>Past Orders</Nav.Link>
        </Nav>
      </Navbar>
      <Row>
        <Col md={8}>
          <h1>Create Your Order</h1>
          <Sandwich addToCart={addToCart} />
          <Drinks setSelectedDrink={handleDrinkChange} />
          <Chips setSelectedChips={handleChipsChange} />
        </Col>
        <Col md={4}>
          <h1>Shopping Cart</h1>
          <Cart cartItems={cartItems} handleRemoveItem={handleRemoveItem} />
          <Button variant="primary" onClick={handleProceedToCheckout}>
            Proceed to Checkout
          </Button>
        </Col>
      </Row>
    </Container>
  );
};

export default OrderScreen;
