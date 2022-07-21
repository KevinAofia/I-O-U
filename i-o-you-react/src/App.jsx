import { Navbar } from "./components/Navbar"
import { ExpenseList } from "./components/Expense";

export const App = () => {
    return (
        <>
            <Navbar />
            <h1>I-O-You</h1>
            <ExpenseList />
        </>
    );
}

// export default App; 