import React, { Component } from 'react';
import { useParams } from 'react-router-dom';
function CustomerOrders() {
    let {customerId} = useParams();
    customerId= customerId.slice(1);
    return (
        <div>
            <h1>Customer orders {customerId}</h1>
        </div>
      );
}

export default CustomerOrders;