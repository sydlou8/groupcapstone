"use client";
import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import OrderScreen from '../components/OrderScreen';
import Checkout from '../components/Checkout';
import PastOrders from "../components/PastOrders";

export default function Home() {
  return (
    <Router>
      <Switch>
        <Route path="/checkout">
          <Checkout />
        </Route>
        <Route path="/past-orders">
          <PastOrders />
        </Route>
        <Route path="/">
          <OrderScreen />
        </Route>
      </Switch>
    </Router>
  );
};


