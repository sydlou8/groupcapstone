import React, { useContext } from 'react';
import axios from 'axios';
import { useCart } from '../context/CartContext';
import { Button, Container, Row, Col } from 'react-bootstrap';

const Checkout = () => {
  const { state, dispatch } = useCart();

  // Function to calculate total price from cart items
  const calculateTotalPrice = () => {
    console.log('Cart items:', state.cartItems); // Log cart items
    return state.cartItems.reduce((total, item) => {
      console.log(`Item type: ${item.type}, Item price: ${item.price}`); // Log item type and price
      return total + (item.price || 0); // Ensure item.price is a number
    }, 0);
  };

  const handleCheckout = async () => {
    // Check if cartItems is defined and not empty
    if (!state.cartItems || state.cartItems.length === 0) {
      alert('Your cart is empty.');
      return;
    }
  
    // Extract items from cart
    const sandwiches = state.cartItems.filter(item => item.type === 'Sandwich');
    const drinks = state.cartItems.filter(item => item.type === 'Drink');
    const chips = state.cartItems.filter(item => item.type === 'Chips');
  
    console.log('Sandwiches:', sandwiches);
    console.log('Drinks:', drinks);
    console.log('Chips:', chips);
  
    // Calculate total price
    const totalPrice = calculateTotalPrice();
    console.log(`Total Price: ${totalPrice}`); // Log total price
  
    // Create the order payload
    const order = {
      sandwiches: sandwiches.map(sandwich => ({
        sandwichId: sandwich.sandwichId,
        breadId: sandwich.breadId,
        meatId: sandwich.meatId,
        cheeseId: sandwich.cheeseId,
        toppingId: sandwich.toppingId,
        sauceId: sandwich.sauceId,
        sideId: sandwich.sideId,
        price: sandwich.price
      })),
      drinks: drinks.map(drink => ({
        drinkId: drink.drinkId, // Ensure this field exists in the drink object
        drinkType: drink.drinkType,
        drinkPrice: drink.price // Ensure this field matches the backend expectation
      })),
      chips: chips.map(chip => ({
        chipsId: chip.chipsId, // Ensure this field exists in the chip object
        chipType: chip.chipType,
        chipsPrice: chip.price // Ensure this field matches the backend expectation
      })),
      totalPrice: totalPrice // Include the calculated totalPrice
    };
  
    console.log('Order payload:', order); // Log order payload
  
    try {
      // Make POST request for the order
      await axios.post('http://localhost:8080/orders', order);
  
      // Clear the cart and notify the user
      dispatch({ type: 'CLEAR_CART' });
      alert('Order placed successfully!');
    } catch (error) {
      console.error('Error placing order', error);
      alert('Failed to place order.');
    }
  };
  
  
  

  return (
    <Container>
      <Row>
        <Col>
          <h2>Checkout</h2>
        </Col>
      </Row>
      <Row>
        <Col>
          <Button variant="primary" onClick={handleCheckout}>Place Order</Button>
        </Col>
      </Row>
    </Container>
  );
};

export default Checkout;
