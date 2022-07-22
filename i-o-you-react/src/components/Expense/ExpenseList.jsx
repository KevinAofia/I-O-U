import { useEffect, useState } from "react";
import axios from "axios";
import { CreateExpenseForm, Expense } from "./Expense";

export const ExpenseList = () => {
    const [expenses, setExpenses] = useState([]);
    const [expenseUpdated, setExpenseUpdated] = useState(false);
    const [expenseDeleted, setExpenseDeleted] = useState(false);

    useEffect(() => {
        axios.get(`http://localhost:8080/i-o-you/expenses`)
            .then(response => setExpenses(response.data));
    }, []);

    // props are changed inside single expense components and cleaned up
    useEffect(() => {
        axios.get(`http://localhost:8080/i-o-you/expenses`)
            .then(response => setExpenses(response.data));
        return () => {
            setExpenseDeleted(false)
            setExpenseUpdated(false)
        }
    }, [expenseDeleted, expenseUpdated]);

    return (
        <>
            <CreateExpenseForm />

            <table className="table table-bordered table-striped">
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
                                expenseUpdated={expenseUpdated}
                                setExpenseUpdated={setExpenseUpdated}
                                expenseDeleted={expenseDeleted}
                                setExpenseDeleted={setExpenseDeleted}
                            />
                        );
                    })}
                </tbody>
            </table>
        </>
    );
};
