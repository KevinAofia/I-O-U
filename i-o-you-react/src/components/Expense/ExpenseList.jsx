import { useEffect, useState } from "react";
import axios from "axios";
import { CreateExpense, Expense } from "./Expense";

export const ExpenseList = () => {
    const [expenses, setExpenses] = useState([]); //empty array by default
    // const [expenseUpdated, setExpenseUpdated] = useState(false);
    // const [expenseUpdating, setExpenseUpdating] = useState(false);
    const [expenseDeleted, setExpenseDeleted] = useState(false);



    // hook into component lifecycles here
    useEffect(() => {
        axios.get('http://localhost:8080/i-o-you/expenses')
            .then(response => setExpenses(response.data));
    }, []);

    // props are changed inside single expense components and cleaned up
    useEffect(() => {
        axios.get('http://localhost:8080/i-o-you/expenses')
            .then(response => setExpenses(response.data));
        return () => {
            setExpenseDeleted(false)
        }
    }, [expenseDeleted]);

    return (
        <>
            <h1>Expense List</h1>
            <CreateExpense />

            <table className="table table-striped">
                <thead>
                    <tr >
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Amount</th>
                        <th>Date</th>
                        <th>Reason</th>
                        <th>Status</th>
                        <th>Update</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    {expenses.map((expense) => {
                        return (
                            // passing object as props to expense component
                            <Expense key={expense.id} 
                            expense={expense} 
                            // expenseUpdating={expenseUpdating} 
                            // setExpenseUpdating={setExpenseUpdating} 
                            // expenseUpdated={expenseUpdated} 
                            // setExpenseUpdated={setExpenseUpdated} 
                            expenseDeleted={expenseDeleted} 
                            setExpenseDeleted={setExpenseDeleted}/>
                        );
                    })}
                </tbody>
            </table>
        </>
    );
};
