import axios from "axios";
export const ReimbursementStatus = (props) => {
    return (
        <tr>
            <td>{props.reimbursementStatus.id} {props.reimbursementStatus.status}</td>
            {/* <td>{props.reimbursementStatus.status}</td> */}
        </tr>
    );
}

