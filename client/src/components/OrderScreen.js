import React, { useState, useEffect, useContext } from 'react';
import { Container, Row, Col, Button, Navbar, Nav, ListGroup } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import { useCart } from '../context/CartContext'; // Ensure CartContext is used if you have one
import Sandwich from '../components/Sandwich'; // Adjust paths as necessary
import Drinks from '../components/Drinks';
import Chips from '../components/Chips';
import ShoppingCart from '../components/ShoppingCart';

const OrderScreen = () => {
  const { cartItems, addToCart } = useCart();  // Use CartContext if applicable
  const navigate = useNavigate();

  // Function to handle adding items to cart
  const handleAddToCart = (item) => {
    addToCart(item);
  };

  const handleProceedToCheckout = () => {
    navigate('/checkout');
  };

  return (
    <Container>
      <Row>
        <Col md={8}>
          <Row>
            <h1>Create Your Order</h1>
            <Col md={8}>
              <Sandwich addToCart={handleAddToCart} />
            </Col>
            <Col md={4}>
              <Drinks addToCart={handleAddToCart} />
              <Chips addToCart={handleAddToCart} />
            </Col>
          </Row>
        </Col>
        <Col md={4}>
          <h1>Shopping Cart</h1>
          <ShoppingCart cartItems={cartItems} />
          <Button variant="primary" onClick={handleProceedToCheckout}>
            Proceed to Checkout
          </Button>
        </Col>
      </Row>
    </Container>
  );
};

export default OrderScreen;
