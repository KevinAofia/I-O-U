import { useEffect, useState } from "react";
import axios from "axios";

export const Expense = () => {
    const [expense, setExpense] = useState([0]); //empty array by default


    useEffect(() => {
        //get request to the backend
        //returns a promise
        axios.get('http://localhost:8080/i-o-you/expenses/1')
            .then(result => console.log(result.data));
        // .then(result => setExpenses(result.data));
    }, []); //fire up one backend request

    return (
        <>
            <h1>Expense List</h1>

            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                    </tr>

                </thead>
                <tbody>
                    {expense.map(() => { })}
                    <tr>
                        <td>
                            value
                        </td>
                    </tr>

                </tbody>
            </table>
            <h2>{expense}</h2>
            <button onClick={() => alert("added expense")}>+</button>

        </>

    );
};
