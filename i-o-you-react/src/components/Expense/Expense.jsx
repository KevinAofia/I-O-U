import axios from "axios";
import { useState, useRef } from "react";
import { ReimbursementStatusList } from "../ReimbursementStatus";

export const Expense = (props) => {
    const [expenseUpdating, setExpenseUpdating] = useState(false);

    const firstNameRef = useRef();
    const lastNameRef = useRef();
    const amountRef = useRef();
    const dateRef = useRef();
    const reasonRef = useRef();
    const statusRef = useRef();

    const initializeUpdate = () => { setExpenseUpdating(true); }
    const cancelUpdate = () => { setExpenseUpdating(false); }

    const finalizeUpdate = async (id) => {
        try {
            await axios.put(
                `http://localhost:8080/i-o-you/expenses/${id}`,
                {
                    firstName: firstNameRef.current.value,
                    lastName: lastNameRef.current.value,
                    amount: amountRef.current.value,
                    date: dateRef.current.value,
                    reason: reasonRef.current.value,
                    status: {
                        id: statusRef.current.value
                    }
                }
            );
            props.setExpenseUpdated(true);
            setExpenseUpdating(false);
        }
        catch (err) {
            console.error(err);
        }


    }

    const handleDelete = async (id) => {
        try {
            await axios.delete(`http://localhost:8080/i-o-you/expenses/${id}`);
            props.setExpenseDeleted(true);
        }
        catch (err) {
            console.error(err);
        }
    }

    return (
        <tr>
            {expenseUpdating ? <td><input className="form-control" name="firstName" defaultValue={props.expense.firstName} ref={firstNameRef} placeholder={props.expense.firstName} /></td> : <td>{props.expense.firstName}</td>}

            {expenseUpdating ? <td><input className="form-control" name="lastName" defaultValue={props.expense.lastName} ref={lastNameRef} placeholder={props.expense.lastName} /></td> : <td>{props.expense.lastName}</td>}

            {expenseUpdating ? <td><input className="form-control" name="amount" defaultValue={props.expense.amount} ref={amountRef} placeholder={props.expense.amount} /></td> : <td>{props.expense.amount}</td>}

            {expenseUpdating ? <td><input className="form-control" name="date" defaultValue={props.expense.date} ref={dateRef} placeholder={props.expense.date} /></td> : <td>{props.expense.date}</td>}

            {expenseUpdating ? <td><input className="form-control" name="reason" defaultValue={props.expense.reason} ref={reasonRef} placeholder={props.expense.reason} /></td> : <td>{props.expense.reason}</td>}

            {expenseUpdating ? <td><input className="form-control" name="status" defaultValue={props.expense.status.id} ref={statusRef} /><ReimbursementStatusList /></td> : <td>{props.expense.status.status}</td>}

            {expenseUpdating ? <><td><button className="btn btn-success" onClick={() => finalizeUpdate(props.expense.id)}>submit</button><button className="btn btn-warning" onClick={() => cancelUpdate()}>cancel</button></td></> : <td><button className="btn btn-primary" onClick={() => { initializeUpdate() }}>edit</button></td>}

            <td><button className="btn btn-danger" onClick={() => { handleDelete(props.expense.id) }}>delete</button></td>
        </tr>
    );
}

export const CreateExpenseForm = () => {
    const firstNameRef = useRef();
    const lastNameRef = useRef();
    const amountRef = useRef();
    const dateRef = useRef();
    const reasonRef = useRef();

    const handleCreation = async () => {
        try {
            // extract data from form and send POST request
            await axios.post(
                `http://localhost:8080/i-o-you/expenses`,
                {
                    firstName: firstNameRef.current.value,
                    lastName: lastNameRef.current.value,
                    amount: amountRef.current.value,
                    date: dateRef.current.value,
                    reason: reasonRef.current.value
                }
            );
            // at this point our API has added an expense
            // and since we are not preventing any default renders
            // our page will re-render on submit with calling a
            // new GET request to fetch the expenses list component
        }
        catch (e) {
            console.error(e);
        }
    }

    return (
        <>
            <form className="" onSubmit={handleCreation}>
                <table className="table table-bordered table-striped">
                    <thead>
                        <tr >
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Amount</th>
                            <th>Date</th>
                            <th>Reason</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><input className="form-control" name="firstName" ref={firstNameRef} placeholder='First name' /></td>
                            <td><input className="form-control" name="lastName" ref={lastNameRef} placeholder='Last name' /></td>
                            <td><input className="form-control" name="amount" ref={amountRef} placeholder='Amount (i.e. $00.00)' /></td>
                            <td><input className="form-control" name="date" ref={dateRef} placeholder='Date (MM-DD-YYY)' /></td>
                            <td><input className="form-control" name="reason" ref={reasonRef} placeholder='Reason (i.e. lodging)' /></td>
                        </tr>
                    </tbody>
                </table>
                <button className="btn btn-warning">Create Expense</button>
            </form><br></br>
        </>
    );
}
