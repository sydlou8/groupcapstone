import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Container, ListGroup, Spinner } from 'react-bootstrap';

const PastOrders = () => {
  const [pastOrders, setPastOrders] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchPastOrders = async () => {
      try {
        const response = await axios.get('http://localhost:8080/orders');
        setPastOrders(response.data);
        setLoading(false);
      } catch (error) {
        console.error('Error fetching past orders:', error);
        setLoading(false);
      }
    };

    fetchPastOrders();
  }, []);

  if (loading) {
    return (
      <Container>
        <Spinner animation="border" />
      </Container>
    );
  }

  return (
    <Container>
      <h1>Past Orders</h1>
      <ListGroup>
        {pastOrders.map((order, index) => (
          <ListGroup.Item key={index}>
            <h5>Order #{order.id}</h5>
            <p>Date: {new Date(order.date).toLocaleDateString()}</p>
            <p>Items:</p>
            <ul>
              {order.items.map((item, idx) => (
                <li key={idx}>{item}</li>
              ))}
            </ul>
          </ListGroup.Item>
        ))}
      </ListGroup>
    </Container>
  );
};

export default PastOrders;
