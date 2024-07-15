import Image from "next/image";
import styles from "./page.module.css";
import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import OrderScreen from '@/components/OrderScreen';
import Checkout from '@/components/Checkout';
import PastOrders from "@/components/PastOrders";

const Home = () => {
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

export default Home;
