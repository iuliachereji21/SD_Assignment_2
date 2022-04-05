import React, { useEffect, useState  } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios'
import Select from 'react-select';

function AdminOrders() {
    let {adminId} = useParams();
    adminId= adminId.slice(1);
    const [data, setData] = useState([]);
    const [updated, setUpdated] = useState(0);

    useEffect(()=>{
        axios.get(`http://localhost:8080/sd_assignment2/admin/${adminId}/orders`)
            .then(res =>{
                var data = res.data;
                data.map((order)=>{
                    switch(order.status){
                        case "PENDING": 
                            order.options = [{value: "DECLINED", label: "DECLINED"},{value: "ACCEPTED", label: "ACCEPTED"},];
                            break;
                        case "ACCEPTED":
                            order.options = [{value: "IN DELIVERY", label: "IN DELIVERY"}];
                            break
                        case "IN DELIVERY":
                            order.options = [{value: "DELIVERED", label: "DELIVERED"}];
                            break
                        default:
                            order.options=[];
                            break;
                    }
                });
                setData(data);
                console.log(data);
            })
            .catch(err =>{
                console.log(err);
            })
    },[updated]);

    const updateStatus = (selected, order)=>{
        console.log(order);
        console.log(selected);
        axios.patch(`http://localhost:8080/sd_assignment2/admin/${adminId}/orders/${order.id}`,{status: selected.value})
            .then(res=>{
                console.log(res);
                var up = updated;
                up++;
                setUpdated(up);
            })
            .catch(err =>{
                console.log(err);
            })
    };

    return (
        <div>
            <h1>Admin orders {adminId}</h1>
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
                            <td>
                                <Select options={order.options} onChange={(selected)=>updateStatus(selected,order)}></Select>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
      );
}

export default AdminOrders;