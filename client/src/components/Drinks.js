import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Form } from 'react-bootstrap';

const Drinks = ({ setSelectedDrink }) => {
  const [drinks, setDrinks] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/drinks').then(response => {
      setDrinks(response.data);
    });
  }, []);

  return (
    <Form.Group controlId="drinkSelect">
      <Form.Label>Drink</Form.Label>
      <Form.Select onChange={(e) => setSelectedDrink(e.target.value)}>
        <option value="">Select Drink</option>
        {drinks.map((drink, index) => (
          <option key={index} value={drink}>{drink}</option>
        ))}
      </Form.Select>
    </Form.Group>
  );
};

export default Drinks;
