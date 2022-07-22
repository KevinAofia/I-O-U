export const ReimbursementStatus = (props) => {
    return (
        <tr>
            <td>{props.reimbursementStatus.id} </td>
            <td>{props.reimbursementStatus.status}</td>
        </tr>
    );
}