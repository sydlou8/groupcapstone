"use client";

import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import { CartProvider } from '../context/CartContext';
import Checkout from '../components/Checkout';
import Orders from '../components/Orders';
import OrderScreen from '../components/OrderScreen';
import Header from '../components/Header';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Container } from 'react-bootstrap';

export default function Home() {
    return (
        <CartProvider>
            <Router>
              <div>
                <Header />
                <Container className="mt-4">
                    <Routes>
                        <Route path="/checkout" element={<Checkout />} />
                        <Route path="/orders" element={<Orders />} />
                        <Route path="/" element={<OrderScreen />} />
                    </Routes>
                </Container>
              </div>
            </Router>
        </CartProvider>
    );
}
