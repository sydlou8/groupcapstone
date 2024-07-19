import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Form, Button, Container } from 'react-bootstrap';

const Sandwich = ({ addToCart }) => {
  const [breadOptions, setBreadOptions] = useState([]);
  const [meatOptions, setMeatOptions] = useState([]);
  const [cheeseOptions, setCheeseOptions] = useState([]);
  const [toppings, setToppings] = useState([]);
  const [sauces, setSauces] = useState([]);
  const [sides, setSides] = useState([]);
  
  const [selectedBread, setSelectedBread] = useState(null);
  const [selectedBreadSize, setSelectedBreadSize] = useState('4"');
  const [selectedMeat, setSelectedMeat] = useState(null);
  const [selectedCheese, setSelectedCheese] = useState(null);
  const [extraMeat, setExtraMeat] = useState(false);
  const [extraCheese, setExtraCheese] = useState(false);
  const [selectedToppings, setSelectedToppings] = useState([]);
  const [selectedSauces, setSelectedSauces] = useState([]);
  const [selectedSides, setSelectedSides] = useState([]);
  const [totalPrice, setTotalPrice] = useState(0);

  useEffect(() => {
    const fetchOptions = async () => {
      try {
        const breadResponse = await axios.get('http://localhost:8080/breads');
        const meatResponse = await axios.get('http://localhost:8080/meats');
        const cheeseResponse = await axios.get('http://localhost:8080/cheeses');
        const toppingsResponse = await axios.get('http://localhost:8080/regular_toppings');
        const saucesResponse = await axios.get('http://localhost:8080/sauces');
        const sidesResponse = await axios.get('http://localhost:8080/sides');

        setBreadOptions(breadResponse.data);
        setMeatOptions(meatResponse.data);
        setCheeseOptions(cheeseResponse.data);
        setToppings(toppingsResponse.data);
        setSauces(saucesResponse.data);
        setSides(sidesResponse.data);
      } catch (error) {
        console.error('Error fetching options:', error);
      }
    };

    fetchOptions();
  }, []);

  const handleBreadChange = (e) => {
    const breadId = parseInt(e.target.value);
    const selectedBread = breadOptions.find(bread => bread.breadId === breadId);
    setSelectedBread(selectedBread);
  };

  const handleBreadSizeChange = (e) => {
    setSelectedBreadSize(e.target.value);
  };

  const handleMeatChange = (e) => {
    const meatId = parseInt(e.target.value);
    const selectedMeat = meatOptions.find(meat => meat.meatId === meatId);
    setSelectedMeat(selectedMeat);
  };

  const handleCheeseChange = (e) => {
    const cheeseId = parseInt(e.target.value);
    const selectedCheese = cheeseOptions.find(cheese => cheese.cheeseId === cheeseId);
    setSelectedCheese(selectedCheese);
  };

  const handleToppingChange = (e) => {
    const topping = e.target.value;
    if (selectedToppings.includes(topping)) {
      setSelectedToppings(selectedToppings.filter(t => t !== topping));
    } else {
      setSelectedToppings([...selectedToppings, topping]);
    }
  };

  const handleSauceChange = (e) => {
    const sauce = e.target.value;
    if (selectedSauces.includes(sauce)) {
      setSelectedSauces(selectedSauces.filter(s => s !== sauce));
    } else {
      setSelectedSauces([...selectedSauces, sauce]);
    }
  };

  const handleSideChange = (e) => {
    const side = e.target.value;
    if (selectedSides.includes(side)) {
      setSelectedSides(selectedSides.filter(s => s !== side));
    } else {
      setSelectedSides([...selectedSides, side]);
    }
  };

  const calculateTotalPrice = () => {
    let price = 0;
    
    if (selectedBread) {
      const selectedSizePrice = selectedBread.breadPrice;
      price += selectedSizePrice;
    }

    if (selectedMeat) {
      if (extraMeat) {
        price += 1; // Additional charge if extra meat is selected
      }
    }

    if (selectedCheese) {
      if (extraCheese) {
        price += 1; // Additional charge if extra cheese is selected
      }
    }

    // No charge for toppings, sauces, or sides
    setTotalPrice(price);
  };

  useEffect(() => {
    calculateTotalPrice();
  }, [selectedBread, selectedMeat, selectedCheese, extraMeat, extraCheese]);

  const handleSubmit = (e) => {
    e.preventDefault();
    const orderItem = {
      type: 'Sandwich',
      bread: selectedBread ? selectedBread.breadName : 'None',
      breadSize: selectedBreadSize,
      meat: selectedMeat ? selectedMeat.meatType : 'None',
      extraMeat,
      cheese: selectedCheese ? selectedCheese.cheeseType : 'None',
      extraCheese,
      toppings: selectedToppings,
      sauces: selectedSauces,
      sides: selectedSides,
      price: totalPrice,
    };
    addToCart(orderItem); // Ensure addToCart is correctly passed and used
  };

  return (
    <Container>
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="formBread">
          <Form.Label>Bread</Form.Label>
          <Form.Select onChange={handleBreadChange}>
            <option>Select bread</option>
            {breadOptions.map((bread) => (
              <option key={bread.breadId} value={bread.breadId}>
                {bread.breadName} - ${bread.breadPrice.toFixed(2)}
              </option>
            ))}
          </Form.Select>
        </Form.Group>

        <Form.Group controlId="formMeat">
          <Form.Label>Meat</Form.Label>
          <Form.Select onChange={handleMeatChange}>
            <option>Select meat</option>
            {meatOptions.map((meat) => (
              <option key={meat.meatId} value={meat.meatId}>
                {meat.meatType}
              </option>
            ))}
          </Form.Select>
        </Form.Group>
        <Form.Group controlId="formExtraMeat">
          <Form.Check
            type="switch"
            label="Extra Meat"
            checked={extraMeat}
            onChange={(e) => setExtraMeat(e.target.checked)}
          />
        </Form.Group>

        <Form.Group controlId="formCheese">
          <Form.Label>Cheese</Form.Label>
          <Form.Select onChange={handleCheeseChange}>
            <option>Select cheese</option>
            {cheeseOptions.map((cheese) => (
              <option key={cheese.cheeseId} value={cheese.cheeseId}>
                {cheese.cheeseType}
              </option>
            ))}
          </Form.Select>
        </Form.Group>
        <Form.Group controlId="formExtraCheese">
          <Form.Check
            type="switch"
            label="Extra Cheese"
            checked={extraCheese}
            onChange={(e) => setExtraCheese(e.target.checked)}
          />
        </Form.Group>

        <Form.Group controlId="formToppings">
          <Form.Label>Toppings</Form.Label>
          <div/>
          {toppings.map((topping) => (
            <Form.Check
              key={topping.regularToppingsId}
              inline
              type="checkbox"
              label={topping.regularToppingsType}
              value={topping.regularToppingsType}
              onChange={handleToppingChange}
            />
          ))}
        </Form.Group>

        <Form.Group controlId="formSauces">
          <Form.Label>Sauces</Form.Label>
          <div/>
          {sauces.map((sauce) => (
            <Form.Check
              key={sauce.sauceId}
              inline
              type="checkbox"
              label={sauce.sauceType}
              value={sauce.sauceType}
              onChange={handleSauceChange}
            />
          ))}
        </Form.Group>

        <Form.Group controlId="formSides">
          <Form.Label>Sides</Form.Label>
          <div/>
          {sides.map((side) => (
            <Form.Check
              key={side.sideId}
              inline
              type="checkbox"
              label={side.sideType}
              value={side.sideType}
              onChange={handleSideChange}
            />
          ))}
        </Form.Group>

        <h5>Sandwich Price: ${totalPrice.toFixed(2)}</h5>

        <Button variant="primary" type="submit">
          Add Sandwich to Cart
        </Button>
      </Form>
    </Container>
  );
};

export default Sandwich;
