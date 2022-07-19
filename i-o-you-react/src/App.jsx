import { ThisClass } from "./components/ClassComponent";
import { ExpenseList } from "./components/Expense";
// import { ExpenseList } from "./components/Expense/ExpenseList";

export const App = () => {
    return (
        <>
            <h1>Connect ME</h1>
            <ExpenseList />
        </>
    );
}
export const App2 = () => {
    return (
        <>
            <h1>Connect ME 2</h1>
            <h1>Connect ME Frag</h1>
            <ThisClass />
        </>
    );
}

// export default App; 