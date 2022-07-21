import { useEffect, useState } from "react";
import axios from "axios";
import { Expense } from "./Expense";

export const ExpenseList = () => {
    const [expenses, setExpenses] = useState([]); //empty array by default

    // hook into component lifecycles here
    useEffect(() => {
        axios.get('http://localhost:8080/i-o-you/expenses')
            .then(result => setExpenses(result.data));
    }, []);


    return (
        <>
            <h1>Expense List</h1>
            <table>
                <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Amount</th>
                        <th>Date</th>
                        <th>Reason</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    {expenses.map((expense) => {
                        return (
                            // passing object as property
                            <Expense key={expense.id} expense={expense}/>
                        );
                    })}
                </tbody>
            </table>
            <button onClick={() => alert("Expense Created")}>Create Expense</button>
        </>
    );
};
