import React from 'react';
import { Outlet, useParams } from 'react-router-dom';
import NavBarLogOut from './navBarLogOut';
import { Link } from 'react-router-dom';

function CustomerPage() {
    let {customerId} = useParams();
    customerId=customerId.slice(1);
    let {token} = useParams();
    token=token.slice(1);
    return (
        <div>
            <NavBarLogOut/>
            <h1>Customer page {customerId}</h1>
            <div>
                  <Link to={"/customer/:"+customerId+"/:"+token+"/restaurants"}>Restaurants</Link>
                  <Link to={"/customer/:"+customerId+"/:"+token+"/orders"}>Orders</Link>
              </div>
              <Outlet/>
        </div>
      );
}

export default CustomerPage;