import React, { Component, useEffect, useState } from 'react';
import { useParams, useNavigate, Link} from 'react-router-dom';
import AddRestaurantDialog from './addRestaurantDialog';
import axios from 'axios'
import AdminRestaurantsTable from './adminRestaurantsTable';

function AdminRestaurants() {
    let {adminId} = useParams();
    adminId= adminId.slice(1);

    const [isAddRestaurantDialogOpen, setIsAddRestaurantDialogOpen]= useState(false);
    const [data, setData] = useState([]);

    useEffect(()=>{
        console.log("use Effect");
        axios.get(`http://localhost:8080/sd_assignment2/admin/${adminId}/restaurants`)
            .then(res =>{
                setData(res.data);
            })
            .catch(err =>{
                console.log(err);
            })
    },[])

    

    return (
        <div>
            <h1>Admin restaurants {adminId}</h1>
            <button onClick={()=>{setIsAddRestaurantDialogOpen(true)}}>Add</button>
            <AddRestaurantDialog 
                adminId = {adminId}
                isvisible = {isAddRestaurantDialogOpen} 
                onSave = {(obj)=>{
                    console.log(obj);
                    data.push(obj);
                    setIsAddRestaurantDialogOpen(false)
                    console.log("on Save");
                }}
                onCancel = {()=>{
                    setIsAddRestaurantDialogOpen(false);
                    }}>
            </AddRestaurantDialog>
            {/* <AdminRestaurantsTable restaurants={data}>

            </AdminRestaurantsTable> */}

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
                                <Link to={"/admin/:"+adminId+"/restaurants/:"+restaurant.id}>View Menu</Link>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
      );
}

export default AdminRestaurants;