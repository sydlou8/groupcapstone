// src/context/CartContext.js
import React, { createContext, useReducer, useContext } from 'react';

// Create Context
const CartContext = createContext();

// Initial State
const initialState = {
  cartItems: [],
  totalPrice: 0,
};

// Reducer Function
const cartReducer = (state, action) => {
  switch (action.type) {
    case 'ADD_TO_CART':
      console.log('Adding to cart:', action.payload);
      return {
        ...state,
        cartItems: [...state.cartItems, action.payload]
      };
    case 'REMOVE_FROM_CART':
      return {
        ...state,
        cartItems: state.cartItems.filter((_, index) => index !== action.payload)
      };
    case 'CLEAR_CART':
      return {
        ...state,
        cartItems: []
      };
    default:
      return state;
  }
};

// Provider Component
const CartProvider = ({ children }) => {
  const [state, dispatch] = useReducer(cartReducer, initialState);

  // Function to add an item to the cart
  const addToCart = (item) => {
    const id = item.sandwichId || item.drinkId || item.chipsId;
    console.log('Item to add:', item, 'Computed ID:', id);

    dispatch({
      type: 'ADD_TO_CART',
      payload: {
        ...item,
        id,
        price: item.price
      }
    });
  };

  // Function to clear the cart
  const clearCart = () => {
    dispatch({ type: 'CLEAR_CART' });
  };

  // Function to remove an item from the cart
  const removeFromCart = (index) => {
    dispatch({ type: 'REMOVE_FROM_CART', payload: index });
  };

  return (
    <CartContext.Provider value={{ state, dispatch, addToCart, clearCart, removeFromCart }}>
      {children}
    </CartContext.Provider>
  );
};

// Custom Hook to use CartContext
const useCart = () => {
  const context = useContext(CartContext);
  if (!context) {
    throw new Error('useCart must be used within a CartProvider');
  }
  return context;
};

export { CartProvider, useCart };
