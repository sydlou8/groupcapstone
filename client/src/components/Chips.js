import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Form, Button, Container } from 'react-bootstrap';

const Chips = ({ addToCart }) => {
  const [chipsOptions, setChipsOptions] = useState([]);
  const [selectedChips, setSelectedChips] = useState(null);
  const [totalPrice, setTotalPrice] = useState(0);

  useEffect(() => {
    const fetchChips = async () => {
      try {
        const response = await axios.get('http://localhost:8080/chips');
        setChipsOptions(response.data);
      } catch (error) {
        console.error('Error fetching chips:', error);
      }
    };

    fetchChips();
  }, []);

  const handleChipsChange = (e) => {
    const chipsId = parseInt(e.target.value);
    const selectedChips = chipsOptions.find(chips => chips.chipsId === chipsId);
    setSelectedChips(selectedChips);
    setTotalPrice(selectedChips ? selectedChips.chipsPrice : 0);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (selectedChips) {
      const orderItem = {
        type: 'Chips',
        chipType: selectedChips.chipType,
        price: totalPrice,
      };
      addToCart(orderItem);
    }
  };

  return (
    <Container>
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="formChips">
          <Form.Label>Chips</Form.Label>
          <Form.Select onChange={handleChipsChange}>
            <option>Select chips</option>
            {chipsOptions.map((chips) => (
              <option key={chips.chipsId} value={chips.chipsId}>
                {chips.chipType} - ${chips.chipsPrice.toFixed(2)}
              </option>
            ))}
          </Form.Select>
        </Form.Group>

        <h5>Chips Price: ${totalPrice.toFixed(2)}</h5>

        <Button variant="primary" type="submit">
          Add Chips to Cart
        </Button>
      </Form>
    </Container>
  );
};

export default Chips;
