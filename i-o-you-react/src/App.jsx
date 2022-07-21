import { Navbar } from "./components/Navbar"
import { ExpenseList } from "./components/Expense";
import { ReimbursementStatusList } from "./components/ReimbursementStatus";
import "bootstrap/dist/css/bootstrap.min.css"
export const App = () => {
    return (
        <>
            <ReimbursementStatusList />
            <Navbar />
            <h1>I-O-You</h1>
            <ExpenseList />
        </>
    );
}

// export default App; 