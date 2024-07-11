import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Form } from 'react-bootstrap';

const Chips = ({ setSelectedChips }) => {
  const [chips, setChips] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/chips').then(response => {
      setChips(response.data);
    });
  }, []);

  return (
    <Form.Group controlId="chipsSelect">
      <Form.Label>Chips</Form.Label>
      <Form.Select onChange={(e) => setSelectedChips(e.target.value)}>
        <option value="">Select Chips</option>
        {chips.map((chip, index) => (
          <option key={index} value={chip}>{chip}</option>
        ))}
      </Form.Select>
    </Form.Group>
  );
};

export default Chips;
