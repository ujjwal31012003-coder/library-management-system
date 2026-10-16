import { useEffect, useState } from "react";
import axios from "axios";

function Librarians() {

  const [librarians, setLibrarians] = useState([]);

  const [showForm, setShowForm] = useState(false);

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("LIBRARIAN");

  const [editingId, setEditingId] = useState(null);


  // =========================
  // GET ALL LIBRARIANS
  // =========================

  useEffect(() => {
    getLibrarians();
  }, []);


  const getLibrarians = async () => {

    try {

      const token = localStorage.getItem("token");

      const response = await axios.get(
        "http://localhost:8080/librarians",
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      console.log("Librarians:", response.data);

      setLibrarians(response.data);

    } catch (error) {

      console.log("Error loading librarians:", error);

      showError(error);
    }
  };


  // =========================
  // ADD LIBRARIAN
  // =========================

  const addLibrarian = async (event) => {

    event.preventDefault();

    try {

      const token = localStorage.getItem("token");

      const librarianData = {
        username: username,
        password: password,
        role: role
      };

      console.log(
        "Sending librarian data:",
        librarianData
      );

      await axios.post(
        "http://localhost:8080/librarians",
        librarianData,
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      alert("Librarian added successfully!");

      closeForm();

      getLibrarians();

    } catch (error) {

      console.log(
        "Error adding librarian:",
        error
      );

      showError(error);
    }
  };


  // =========================
  // EDIT LIBRARIAN
  // =========================

  const editLibrarian = (librarian) => {

    setEditingId(librarian.id);

    setUsername(librarian.username);

    // Password is left empty when editing
    setPassword("");

    setRole(librarian.role);

    setShowForm(true);
  };


  // =========================
  // UPDATE LIBRARIAN
  // =========================

  const updateLibrarian = async (event) => {

    event.preventDefault();

    try {

      const token = localStorage.getItem("token");

      const librarianData = {
        username: username,
        password: password,
        role: role
      };

      console.log(
        "Updating librarian:",
        librarianData
      );

      await axios.put(
        `http://localhost:8080/librarians/${editingId}`,
        librarianData,
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      alert("Librarian updated successfully!");

      closeForm();

      getLibrarians();

    } catch (error) {

      console.log(
        "Error updating librarian:",
        error
      );

      showError(error);
    }
  };

 const deleteLibrarian = async (id) => {

   const confirmDelete = window.confirm(
     "Are you sure you want to delete this librarian?"
   );

   if (!confirmDelete) {
     return;
   }

   try {

     const token = localStorage.getItem("token");

     await axios.delete(
       `http://localhost:8080/librarians/${id}`,
       {
         headers: {
           Authorization: `Bearer ${token}`
         }
       }
     );

     alert("Librarian deleted successfully!");

     getLibrarians();

   } catch (error) {

     console.log("Error deleting librarian:", error);

     showError(error);
   }
 };

  // =========================
  // CLOSE FORM
  // =========================

  const closeForm = () => {

    setShowForm(false);

    setEditingId(null);

    setUsername("");

    setPassword("");

    setRole("LIBRARIAN");
  };


  // =========================
  // ERROR HANDLING
  // =========================

  const showError = (error) => {

    if (error.response) {

      alert(
        `Error ${error.response.status}: ${
          typeof error.response.data === "string"
            ? error.response.data
            : JSON.stringify(error.response.data)
        }`
      );

    } else {

      alert(
        "Could not connect to the backend"
      );
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

      <h1>Librarian</h1>


      {/* =========================
          ADD LIBRARIAN BUTTON
          ========================= */}

      <button
        onClick={() => {
          setEditingId(null);
          setUsername("");
          setPassword("");
          setRole("LIBRARIAN");
          setShowForm(true);
        }}
      >
        Add Librarian
      </button>


      <br />
      <br />


      {/* =========================
          ADD / EDIT FORM
          ========================= */}

      {showForm && (

        <form
          onSubmit={
            editingId
              ? updateLibrarian
              : addLibrarian
          }
        >

          <h2>
            {editingId
              ? "Edit Librarian"
              : "Add Librarian"}
          </h2>


          {/* USERNAME */}

          <label>
            Username
          </label>

          <input
            type="text"
            value={username}
            onChange={(event) =>
              setUsername(event.target.value)
            }
          />


          <br />
          <br />


          {/* PASSWORD */}

          <label>
            Password
          </label>

          <input
            type="password"
            value={password}
            onChange={(event) =>
              setPassword(event.target.value)
            }
            placeholder={
              editingId
                ? "Enter new password"
                : "Enter password"
            }
          />


          <br />
          <br />


          {/* ROLE */}

          <label>
            Role
          </label>

          <select
            value={role}
            onChange={(event) =>
              setRole(event.target.value)
            }
          >

            <option value="LIBRARIAN">
              LIBRARIAN
            </option>

            <option value="ADMIN">
              ADMIN
            </option>

          </select>


          <br />
          <br />


          {/* SUBMIT */}

          <button type="submit">

            {editingId
              ? "Update"
              : "Save"}

          </button>


          {/* CANCEL */}

          <button
            type="button"
            onClick={closeForm}
          >
            Cancel
          </button>

        </form>
      )}


      <br />
      <br />


      {/* =========================
          LIBRARIANS TABLE
          ========================= */}

      <table border="1">

        <thead>

          <tr>

            <th>ID</th>

            <th>Username</th>

            <th>Role</th>

            <th>Action</th>

          </tr>

        </thead>


        <tbody>

          {librarians.map((librarian) => (

            <tr key={librarian.id}>

              <td>
                {librarian.id}
              </td>

              <td>
                {librarian.username}
              </td>

              <td>
                {librarian.role}
              </td>

             <td>

               <button
                 onClick={() =>
                   editLibrarian(librarian)
                 }
               >
                 Edit
               </button>

               <button
                 onClick={() =>
                   deleteLibrarian(librarian.id)
                 }
               >
                 Delete
               </button>

             </td>

            </tr>

          ))}

        </tbody>

      </table>

    </div>
  );
}

export default Librarians;