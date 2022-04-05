import React, { useState, useEffect } from 'react';
import { useParams, Link} from 'react-router-dom';
import axios from 'axios'

function CustomerRestaurantPage() {
    let {restaurantId} = useParams();
    restaurantId= restaurantId.slice(1);
    let {customerId} = useParams();
    customerId= customerId.slice(1);

    const [data, setData] = useState([]);
    const [cart, setCart] = useState([]);
    const [cartTotalPrice, setCartTotalPrice] = useState(0);
    const [errorMessage, setMessage]= useState("");

    useEffect(()=>{
        axios.get(`http://localhost:8080/sd_assignment2/admin/${customerId}/restaurants/${restaurantId}`)
            .then(res =>{
                console.log(res);
                setData(res.data);
            })
            .catch(err =>{
                console.log(err);
            })
    },[])

    const addToCart = (foodObject)=>{
        var cart2 = [...cart];
        cart2.push(foodObject);
        setCart(cart2);
        setCartTotalPrice(cartTotalPrice+foodObject.price)
    };

    const placeOrder = () =>{
        console.log(cart);
        axios.post(`http://localhost:8080/sd_assignment2/customer/${customerId}/restaurants/${restaurantId}`,{cart: cart})
        .then(response =>{
            console.log(response);
            setMessage("");
            setCart([]);
            
        })
        .catch(({ response }) => { 
            console.log(response.data.message);
            setMessage(response.data.message);
        })
    };

    return ( 
        <div>
            Customer restaurant page {restaurantId}
            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Category</th>
                    </tr>
                </thead>
                <tbody>
                    {data.map((food)=>(
                        <tr>
                            <td>{food.id}</td>
                            <td>{food.name}</td>
                            <td>{food.description}</td>
                            <td>{food.price}</td>
                            <td>{food.category}</td>
                            <td><button onClick={()=>addToCart(food)}>Add to cart</button></td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <table>
                <thead>
                    <h3>cart</h3>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Category</th>
                    </tr>
                </thead>
                <tbody>
                    {cart.map((food)=>(
                        <tr>
                            <td>{food.id}</td>
                            <td>{food.name}</td>
                            <td>{food.description}</td>
                            <td>{food.price}</td>
                            <td>{food.category}</td>
                        </tr>
                    ))}
                </tbody>
                <h3>Total price: {cartTotalPrice}</h3>
                <button onClick={()=>placeOrder()}>Place Order</button>
                <div>
                    <label>{errorMessage} </label>
                </div>
                
            </table>

        </div>
     );
}

export default CustomerRestaurantPage;




    