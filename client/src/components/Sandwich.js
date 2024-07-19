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
  const [selectedTopping, setSelectedTopping] = useState(null);
  const [selectedSauce, setSelectedSauce] = useState(null);
  const [selectedSide, setSelectedSide] = useState(null);
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
    const toppingId = parseInt(e.target.value);
    const selectedTopping = toppings.find(topping => topping.regularToppingsId === toppingId);
    setSelectedTopping(selectedTopping);
  };

  const handleSauceChange = (e) => {
    const sauceId = parseInt(e.target.value);
    const selectedSauce = sauces.find(sauce => sauce.sauceId === sauceId);
    setSelectedSauce(selectedSauce);
  };

  const handleSideChange = (e) => {
    const sideId = parseInt(e.target.value);
    const selectedSide = sides.find(side => side.sideId === sideId);
    setSelectedSide(selectedSide);
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

    // No charge for topping, sauce, or side
    setTotalPrice(price);
  };

  useEffect(() => {
    calculateTotalPrice();
  }, [selectedBread, selectedMeat, selectedCheese, extraMeat, extraCheese]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const sandwichOrder = {
      type:'Sandwich',
      bread_id: selectedBread ? selectedBread.breadId : null,
      meat_id: selectedMeat ? selectedMeat.meatId : null,
      cheese_id: selectedCheese ? selectedCheese.cheeseId : null,
      topping_id: selectedTopping ? selectedTopping.regularToppingsId : null,
      sauce_id: selectedSauce ? selectedSauce.sauceId : null,
      side_id: selectedSide ? selectedSide.sideId : null,
      price: totalPrice,
      sandwich_price: totalPrice,
    }

    console.log(JSON.stringify(sandwichOrder, null, 2));
    try {
      const response = await axios.post('http://localhost:8080/sandwiches', sandwichOrder);
      console.log('Sandwich added:', response.data);
      sandwichOrder.id = response.data.sandwichId;
      addToCart(sandwichOrder);
    } catch (error) {
      console.error('Error adding sandwich:', error);
    }
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

        <Form.Group controlId="formTopping">
          <Form.Label>Topping</Form.Label>
          <Form.Select onChange={handleToppingChange}>
            <option>Select topping</option>
            {toppings.map((topping) => (
              <option key={topping.regularToppingsId} value={topping.regularToppingsId}>
                {topping.regularToppingsType}
              </option>
            ))}
          </Form.Select>
        </Form.Group>

        <Form.Group controlId="formSauce">
          <Form.Label>Sauce</Form.Label>
          <Form.Select onChange={handleSauceChange}>
            <option>Select sauce</option>
            {sauces.map((sauce) => (
              <option key={sauce.sauceId} value={sauce.sauceId}>
                {sauce.sauceType}
              </option>
            ))}
          </Form.Select>
        </Form.Group>

        <Form.Group controlId="formSide">
          <Form.Label>Side</Form.Label>
          <Form.Select onChange={handleSideChange}>
            <option>Select side</option>
            {sides.map((side) => (
              <option key={side.sideId} value={side.sideId}>
                {side.sideType}
              </option>
            ))}
          </Form.Select>
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
