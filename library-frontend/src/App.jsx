import Login from "./Login";
import Dashboard from "./Dashboard";
import Students from "./Students";
import Books from "./Books";
import BookIssues from "./BookIssues";
import Librarians from "./Librarians";

function App() {

  const token = localStorage.getItem("token");

  if (!token) {
    return <Login />;
  }

  if (window.location.pathname === "/students") {
    return <Students />;
  }

  if (window.location.pathname === "/books") {
    return <Books />;
  }

  if (window.location.pathname === "/book-issues") {
    return <BookIssues />;
  }

  if (window.location.pathname === "/librarians") {
    return <Librarians />;
  }

  // If logged in and no specific path is selected,
  // show Dashboard
  return <Dashboard />;
}

export default App;