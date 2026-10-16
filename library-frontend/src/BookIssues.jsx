import { useEffect, useState } from "react";
import axios from "axios";

function BookIssues() {

  const [issues, setIssues] = useState([]);

  const [showForm, setShowForm] = useState(false);

  const [studentId, setStudentId] = useState("");
  const [bookId, setBookId] = useState("");

  useEffect(() => {
    getIssues();
  }, []);

  // =========================
  // GET ALL BOOK ISSUES
  // =========================

  const getIssues = async () => {

    try {

      const token = localStorage.getItem("token");

      const response = await axios.get(
        "http://localhost:8080/BookIssues",
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      console.log("Book Issues:", response.data);

      setIssues(response.data);

    } catch (error) {

      console.log("Error loading book issues:", error);

      if (error.response) {

        alert(
          `Error ${error.response.status}: ${
            typeof error.response.data === "string"
              ? error.response.data
              : JSON.stringify(error.response.data)
          }`
        );

      } else {

        alert("Could not connect to the backend");

      }
    }
  };


  // =========================
  // ISSUE BOOK
  // =========================

  const issueBook = async (event) => {

    event.preventDefault();

    try {

      const token = localStorage.getItem("token");

      const issueData = {
        studentId: Number(studentId),
        bookId: Number(bookId)
      };

      console.log("Sending issue data:", issueData);

      await axios.post(
        "http://localhost:8080/BookIssues",
        issueData,
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      alert("Book issued successfully!");

      // Close form
      setShowForm(false);

      // Clear fields
      setStudentId("");
      setBookId("");

      // Refresh issues
      getIssues();

    } catch (error) {

      console.log("Error issuing book:", error);

      if (error.response) {

        alert(
          `Error ${error.response.status}: ${
            typeof error.response.data === "string"
              ? error.response.data
              : JSON.stringify(error.response.data)
          }`
        );

      } else {

        alert("Could not connect to the backend");

      }
    }
  };


  // =========================
  // RETURN BOOK
  // =========================

  const returnBook = async (id) => {

    const confirmReturn = window.confirm(
      "Are you sure you want to return this book?"
    );

    if (!confirmReturn) {
      return;
    }

    try {

      const token = localStorage.getItem("token");

      await axios.put(
        `http://localhost:8080/BookIssues/${id}/return`,
        {},
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      alert("Book returned successfully!");

      // Refresh table
      getIssues();

    } catch (error) {

      console.log("Error returning book:", error);

      if (error.response) {

        alert(
          `Error ${error.response.status}: ${
            typeof error.response.data === "string"
              ? error.response.data
              : JSON.stringify(error.response.data)
          }`
        );

      } else {

        alert("Could not connect to the backend");

      }
    }
  };


  // =========================
  // JSX
  // =========================

  return (
    <div>
      <button onClick={() => window.location.href = "/"}>
        ← Back to Dashboard
      </button>

      <h1>Book Issues</h1>


      {/* =========================
          ISSUE BOOK BUTTON
          ========================= */}

      <button onClick={() => setShowForm(true)}>
        Issue Book
      </button>


      <br />
      <br />


      {/* =========================
          ISSUE BOOK FORM
          ========================= */}

      {showForm && (

        <form onSubmit={issueBook}>

          <h2>Issue Book</h2>


          <label>Student ID</label>

          <input
            type="number"
            value={studentId}
            onChange={(event) =>
              setStudentId(event.target.value)
            }
          />


          <br />
          <br />


          <label>Book ID</label>

          <input
            type="number"
            value={bookId}
            onChange={(event) =>
              setBookId(event.target.value)
            }
          />


          <br />
          <br />


          <button type="submit">
            Issue
          </button>


          <button
            type="button"
            onClick={() => {
              setShowForm(false);
              setStudentId("");
              setBookId("");
            }}
          >
            Cancel
          </button>

        </form>
      )}


      <br />
      <br />


      {/* =========================
          BOOK ISSUES TABLE
          ========================= */}

      <table border="1">

        <thead>

          <tr>

            <th>ID</th>

            <th>Student ID</th>

            <th>Book ID</th>

            <th>Issue Date</th>

            <th>Return Date</th>

            <th>Due Date</th>

            <th>Status</th>

            <th>Action</th>

          </tr>

        </thead>


        <tbody>

          {issues.map((issue) => (

            <tr key={issue.id}>

              <td>
                {issue.id}
              </td>

              <td>
                {issue.studentId}
              </td>

              <td>
                {issue.bookId}
              </td>

              <td>
                {issue.issueDate}
              </td>

              <td>
                {issue.returnDate || "-"}
              </td>

              <td>
                {issue.dueDate}
              </td>

              <td>
                {issue.status}
              </td>

              <td>

                {issue.status === "ISSUED" && (

                  <button
                    onClick={() => returnBook(issue.id)}
                  >
                    Return
                  </button>

                )}

                {issue.status === "RETURNED" && (
                  <span>
                    Returned
                  </span>
                )}

              </td>

            </tr>

          ))}

        </tbody>

      </table>

    </div>
  );
}

export default BookIssues;