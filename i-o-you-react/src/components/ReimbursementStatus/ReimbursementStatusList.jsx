import { useEffect, useState } from "react";
import axios from "axios";
import { ReimbursementStatus } from "./ReimbursementStatus";

export const ReimbursementStatusList = () => {
    const [reimbursementStatuses, setReimbursementStatuses] = useState([]); //empty array by default

    // hook into component lifecycles here
    useEffect(() => {
        axios.get(`http://localhost:8080/i-o-you/reimbursementstatuses`)
            .then(response => setReimbursementStatuses(response.data));
    }, []);
    return (
        <>
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    {reimbursementStatuses.map((reimbursementStatus) => {
                        return (
                            <ReimbursementStatus key={reimbursementStatus.id}
                                reimbursementStatus={reimbursementStatus}
                            />
                        );
                    })}
                </tbody>
            </table>
        </>
    );
};