import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Container, Row, Col } from 'react-bootstrap';

const Orders = () => {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const response = await axios.get('http://localhost:8080/orders');
        console.log(response.data);
        setOrders(response.data);
      } catch (error) {
        console.error('Error fetching orders:', error);
      }
    };

    fetchOrders();
  }, []);

  console.log(orders);
  
  return (
    <Container>
      <Row>
        <Col>
          <h2>Orders</h2>
        </Col>
      </Row>
      {orders.length === 0 ? (
        <Row>
          <Col>
            <p>No orders found.</p>
          </Col>
        </Row>
      ) : (
        orders.map((order, index) => (
          <Row key={index}>
            <Col>
              <h3>Order #{index + 1}</h3>
              <p>Total Price: {order.orderPrice !== undefined ? '$ ' + order.orderPrice.toFixed(2) : 'N/A'}</p>
              <h4>Sandwiches</h4>
              {order.sandwiches && order.sandwiches.length > 0 ? (
                order.sandwiches.map((sandwich, i) => (
                  <p key={i}>
                    Custome Sandwich, Price: $  {sandwich.price.toFixed(2)}
                  </p>
                ))
              ) : (
                <p>No sandwiches</p>
              )}
              <h4>Drinks</h4>
              {order.drinks && order.drinks.length > 0 ? (
                order.drinks.map((drink, i) => (
                  <p key={i}>
                    Drink Type: {drink.drinkType}, Price: $  {drink.drinkPrice.toFixed(2)}
                  </p>
                ))
              ) : (
                <p>No drinks</p>
              )}
              <h4>Chips</h4>
              {order.chips && order.chips.length > 0 ? (
                order.chips.map((chip, i) => (
                  <p key={i}>
                    Chip Type: {chip.chipType}, Price: $  {chip.chipsPrice.toFixed(2)}
                  </p>
                ))
              ) : (
                <p>No chips</p>
              )}
            </Col>
          </Row>
        ))
      )}
    </Container>
  );
};

export default Orders;
