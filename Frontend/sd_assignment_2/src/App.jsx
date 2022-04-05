import logo from './logo.svg';
import './App.css';
import { React } from 'react';
import { Component } from 'react';
import Home from './components/home';
import {Route, Routes} from 'react-router-dom';
import LogIn from './components/login';
import Register from './components/register';

import ReactDOM from 'react-dom/client'
import CustomerPage from './components/customerPage';
import AdminPage from './components/adminPage';
import AdminRestaurants from './components/adminRestaurants';
import AdminOrders from './components/adminOrders';
import CustomerRestaurants from './components/customerRestaurants';
import CustomerOrders from './components/customerOrders';
import AdminRestaurantPage from './components/adminRestaurantPage';
import CustomerRestaurantPage from './components/customerRestaurantPage';


class App extends Component {
  state = {  } 
  render() { 
    return (
      <div className="App">
        <div className='content'>
          <Routes>
            <Route path='/login' element={<LogIn/>}></Route> 
            <Route path='/register' element={<Register />}></Route>
            <Route path='/customer/:customerId' element={<CustomerPage/>}>
              <Route path='restaurants/:restaurantId' element={<CustomerRestaurantPage/>}></Route> 
              <Route path='restaurants' element={<CustomerRestaurants/>}></Route> 
              <Route path='orders' element={<CustomerOrders/>}></Route> 
            </Route> 
            <Route path='/admin/:adminId' exact element={<AdminPage/>}>
              <Route path='restaurants/:restaurantId' element={<AdminRestaurantPage/>}></Route> 
              <Route path='restaurants' element={<AdminRestaurants/>}></Route> 
              <Route path='orders' element={<AdminOrders/>}></Route> 
            </Route> 
            <Route path='/' element={<Home />}></Route>
          </Routes>
        </div>
      </div>);
  }
}
 
export default App;
