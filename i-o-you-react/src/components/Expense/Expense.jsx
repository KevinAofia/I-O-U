export const Expense = (props) => {
    return (
        <tr>
            <td>{props.expense.firstName}</td>
            <td>{props.expense.lastName}</td>
            <td>{props.expense.amount}</td>
            <td>{props.expense.date}</td>
            <td>{props.expense.reason}</td>
            <td>{props.expense.status.status}</td>
        </tr>
    );
}
