import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Form, Button, Container } from 'react-bootstrap';

const Drinks = ({ addToCart }) => {
  const [drinkOptions, setDrinkOptions] = useState([]);
  const [selectedDrink, setSelectedDrink] = useState(null);
  const [totalPrice, setTotalPrice] = useState(0);

  useEffect(() => {
    const fetchDrinks = async () => {
      try {
        const response = await axios.get('http://localhost:8080/drinks');
        setDrinkOptions(response.data);
      } catch (error) {
        console.error('Error fetching drinks:', error);
      }
    };

    fetchDrinks();
  }, []);

  const handleDrinkChange = (e) => {
    const drinkId = parseInt(e.target.value);
    const selectedDrink = drinkOptions.find(drink => drink.drinkId === drinkId);
    setSelectedDrink(selectedDrink);
    setTotalPrice(selectedDrink ? selectedDrink.drinkPrice : 0);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (selectedDrink) {
      const orderItem = {
        type: 'Drink',
        drinkId: selectedDrink.drinkId,
        drinkType: selectedDrink.drinkType,
        price: totalPrice,
      };
      addToCart(orderItem);
    }
  };

  return (
    <Container>
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="formDrink">
          <Form.Label>Drink</Form.Label>
          <Form.Select onChange={handleDrinkChange}>
            <option>Select drink</option>
            {drinkOptions.map((drink) => (
              <option key={drink.drinkId} value={drink.drinkId}>
                {drink.drinkType} - ${drink.drinkPrice.toFixed(2)}
              </option>
            ))}
          </Form.Select>
        </Form.Group>

        <h5>Drink Price: ${totalPrice.toFixed(2)}</h5>

        <Button variant="primary" type="submit">
          Add Drink to Cart
        </Button>
      </Form>
    </Container>
  );
};

export default Drinks;
