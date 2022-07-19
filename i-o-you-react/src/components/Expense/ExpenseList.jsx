import { useEffect, useState } from "react";
import axios from "axios";

export const ExpenseList = () => {
    const [expenses, setExpenses] = useState([]); //empty array by default

    useEffect(() => {
        //get request to the backend
        //returns a promise
        axios.get('http://localhost:8080/i-o-you/expenses')
            .then(res => console.log(res));
    }, []); //fire up one backend request

    return (
        <>
            <h1>Expense List</h1>
        </>
    );
};
