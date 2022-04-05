import React, { useEffect, useState  } from 'react';
import axios from 'axios'
import { useParams } from 'react-router-dom';

function CustomerOrders() {
    let {customerId} = useParams();
    customerId= customerId.slice(1);
    const [data, setData] = useState([]);

    useEffect(()=>{
        axios.get(`http://localhost:8080/sd_assignment2/customer/${customerId}/orders`)
            .then(res =>{
                setData(res.data);
                console.log(data);
            })
            .catch(err =>{
                console.log(err);
            })
    },[]);

    return (
        <div>
            <h1>Customer orders {customerId}</h1>
            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Customer</th>
                        <th>Restaurant</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    {data.map((order)=>(
                        <tr>
                            <td>{order.id}</td>
                            <td>{order.customer_name}</td>
                            <td>{order.restaurant_name}</td>
                            <td>{order.status}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
      );
}

export default CustomerOrders;