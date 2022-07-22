import { Navbar } from "./components/Navbar"
import { ExpenseList } from "./components/Expense";
import "bootstrap/dist/css/bootstrap.min.css"

export const App = () => {
    return (
        <>
            <Navbar />
            <ExpenseList />
        </>
    );
}