import axios from 'axios';
import React, { useState, useEffect } from 'react';
import { Form, Container, Button } from 'react-bootstrap';

const Sandwich = ({ addToCart }) => {
    const [breads, setBreads] = useState([]);
    const [meats, setMeats] = useState([]);
    const [cheeses, setCheeses] = useState([]);
    const [toppings, setToppings] = useState([]);
    const [sides, setSides] = useState([]);
    const [selectedBread, setSelectedBread] = useState('');
    const [selectedSize, setSelectedSize] = useState('medium');
    const [selectedMeat, setSelectedMeat] = useState('');
    const [extraMeat, setExtraMeat] = useState(false);
    const [selectedCheese, setSelectedCheese] = useState('');
    const [extraCheese, setExtraCheese] = useState(false);
    const [selectedToppings, setSelectedToppings] = useState([]);
    const [selectedSides, setSelectedSides] = useState([]);
  
    useEffect(() => {
      axios.get('http://localhost:8080/bread').then(response => {
        setBreads(response.data);
      });
      axios.get('http://localhost:8080/meat').then(response => {
        setMeats(response.data);
      });
      axios.get('http://localhost:8080/cheese').then(response => {
        setCheeses(response.data);
      });
      axios.get('http://localhost:8080/toppings').then(response => {
        setToppings(response.data);
      });
      axios.get('http://localhost:8080/sides').then(response => {
        setSides(response.data);
      });
    }, []);
  
    const handleAddToCart = () => {
      const orderData = {
        bread: selectedBread,
        size: selectedSize,
        meat: selectedMeat,
        extraMeat: extraMeat,
        cheese: selectedCheese,
        extraCheese: extraCheese,
        toppings: selectedToppings,
        sides: selectedSides,
      };
      addToCart(orderData);
    };
  
    return (
      <Container>
        <h1>Create Your Sandwich</h1>
        <Form>
          <Form.Group controlId="breadSelect">
            <Form.Label>Bread</Form.Label>
            <Form.Select value={selectedBread} onChange={(e) => setSelectedBread(e.target.value)}>
              <option value="">Select Bread</option>
              {breads.map((bread, index) => (
                <option key={index} value={bread}>{bread}</option>
              ))}
            </Form.Select>
          </Form.Group>
          <Form>
            <Form.Group controlId="sizeSelect">
              <Form.Label>Size</Form.Label>
              <Form.Select value={selectedSize} onChange={(e) => setSelectedSize(e.target.value)}>
                <option value="small">Small</option>
                <option value="medium">Medium</option>
                <option value="large">Large</option>
              </Form.Select>
            </Form.Group>
          </Form>
  
          <Form.Group controlId="meatSelect">
            <Form.Label>Meat</Form.Label>
            <Form.Select value={selectedMeat} onChange={(e) => setSelectedMeat(e.target.value)}>
              <option value="">Select Meat</option>
              {meats.map((meat, index) => (
                <option key={index} value={meat}>{meat}</option>
              ))}
            </Form.Select>
            <Form.Check 
              type="switch"
              id="extraMeatSwitch"
              label="Extra Meat"
              checked={extraMeat}
              onChange={(e) => setExtraMeat(e.target.checked)}
            />
          </Form.Group>
  
          <Form.Group controlId="cheeseSelect">
            <Form.Label>Cheese</Form.Label>
            <Form.Select value={selectedCheese} onChange={(e) => setSelectedCheese(e.target.value)}>
              <option value="">Select Cheese</option>
              {cheeses.map((cheese, index) => (
                <option key={index} value={cheese}>{cheese}</option>
              ))}
            </Form.Select>
            <Form.Check 
              type="switch"
              id="extraCheeseSwitch"
              label="Extra Cheese"
              checked={extraCheese}
              onChange={(e) => setExtraCheese(e.target.checked)}
            />
          </Form.Group>
  
          <Form.Group controlId="toppingsSelect">
            <Form.Label>Toppings</Form.Label>
            {toppings.map((topping, index) => (
              <Form.Check 
                key={index}
                type="checkbox"
                label={topping}
                value={topping}
                onChange={(e) => {
                  const checked = e.target.checked;
                  setSelectedToppings(prev =>
                    checked ? [...prev, topping] : prev.filter(t => t !== topping)
                  );
                }}
              />
            ))}
          </Form.Group>
  
          <Form.Group controlId="sidesSelect">
            <Form.Label>Sides</Form.Label>
            {sides.map((side, index) => (
              <Form.Check 
                key={index}
                type="checkbox"
                label={side}
                value={side}
                onChange={(e) => {
                  const checked = e.target.checked;
                  setSelectedSides(prev =>
                    checked ? [...prev, side] : prev.filter(s => s !== side)
                  );
                }}
              />
            ))}
          </Form.Group>
  
          <Button variant="primary" onClick={handleAddToCart}>Add to Cart</Button>
        </Form>
      </Container>
    );
  };

export default Sandwich;