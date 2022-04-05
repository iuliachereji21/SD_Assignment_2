import React, { Component, useEffect, useState } from 'react';
import { useParams, useNavigate, Link} from 'react-router-dom';
import axios from 'axios'

function CustomerRestaurants() {
    let {customerId} = useParams();
    customerId= customerId.slice(1);

    const [data, setData] = useState([]);

    useEffect(()=>{
        axios.get(`http://localhost:8080/sd_assignment2/customer/${customerId}/restaurants`)
            .then(res =>{
                setData(res.data);
            })
            .catch(err =>{
                console.log(err);
            })
    },[])

    return (
        <div>
            <h1>Customer restaurants {customerId}</h1>
            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Location</th>
                        <th>Available delivery zones</th>
                    </tr>
                </thead>
                <tbody>
                    {data.map((restaurant)=>(
                        <tr>
                            <td>{restaurant.id}</td>
                            <td>{restaurant.name}</td>
                            <td>{restaurant.location}</td>
                            <td>{restaurant.available_delivery_zones}</td>
                            <td>
                                <Link to={"/customer/:"+customerId+"/restaurants/:"+restaurant.id}>View Menu</Link>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
      );
}

export default CustomerRestaurants;