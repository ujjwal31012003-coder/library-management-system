import { useEffect, useState } from "react";
import axios from "axios";

function Books() {

  const [books, setBooks] = useState([]);

  const [showForm, setShowForm] = useState(false);

  // Form fields
  const [isbn, setIsbn] = useState("");
  const [title, setTitle] = useState("");
  const [author, setAuthor] = useState("");
  const [publisher, setPublisher] = useState("");
  const [category, setCategory] = useState("");
  const [quantity, setQuantity] = useState("");
  const [availableQuantity, setAvailableQuantity] = useState("");
  const [shelfNumber, setShelfNumber] = useState("");

  // Stores ID of book being edited
  const [editingId, setEditingId] = useState(null);


  // =========================
  // GET ALL BOOKS
  // =========================

  useEffect(() => {
    getBooks();
  }, []);


  const getBooks = async () => {

    try {

      const token = localStorage.getItem("token");

      const response = await axios.get(
        "http://localhost:8080/books",
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      setBooks(response.data);

    } catch (error) {

      console.log("Error loading books:", error);

    }
  };


  // =========================
  // ADD BOOK
  // =========================

  const addBook = async (event) => {

    event.preventDefault();

    try {

      const token = localStorage.getItem("token");

      const bookData = {
        isbn: isbn,
        title: title,
        author: author,
        publisher: publisher,
        category: category,
        quantity: Number(quantity),
        availableQuantity: Number(availableQuantity),
        shelfNumber: shelfNumber
      };

      console.log("Sending book data:", bookData);

      await axios.post(
        "http://localhost:8080/books",
        bookData,
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      alert("Book added successfully!");

      closeForm();

      getBooks();

    } catch (error) {

      console.log("Error adding book:", error);

      showError(error);
    }
  };


  // =========================
  // EDIT BUTTON
  // =========================

  const editBook = (book) => {

    setEditingId(book.id);

    setIsbn(book.isbn);
    setTitle(book.title);
    setAuthor(book.author);
    setPublisher(book.publisher);
    setCategory(book.category);
    setQuantity(book.quantity);
    setAvailableQuantity(book.availableQuantity);
    setShelfNumber(book.shelfNumber);

    setShowForm(true);
  };


  // =========================
  // UPDATE BOOK
  // =========================

  const updateBook = async (event) => {

    event.preventDefault();

    try {

      const token = localStorage.getItem("token");

      const bookData = {
        isbn: isbn,
        title: title,
        author: author,
        publisher: publisher,
        category: category,
        quantity: Number(quantity),
        availableQuantity: Number(availableQuantity),
        shelfNumber: shelfNumber
      };

      console.log("Updating book:", bookData);

      await axios.put(
        `http://localhost:8080/books/${editingId}`,
        bookData,
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      alert("Book updated successfully!");

      closeForm();

      getBooks();

    } catch (error) {

      console.log("Error updating book:", error);

      showError(error);
    }
  };
// deleteBook //

const deleteBook = async (id) => {

  const confirmDelete = window.confirm(
    "Are you sure you want to delete this book?"
  );

  if (!confirmDelete) {
    return;
  }

  try {

    const token = localStorage.getItem("token");

    await axios.delete(
      `http://localhost:8080/books/${id}`,
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    );

    alert("Book deleted successfully!");

    getBooks();

  } catch (error) {

    console.log("Error deleting book:", error);

    showError(error);
  }
};


  // =========================
  // CLOSE FORM
  // =========================

  const closeForm = () => {

    setShowForm(false);

    setEditingId(null);

    setIsbn("");
    setTitle("");
    setAuthor("");
    setPublisher("");
    setCategory("");
    setQuantity("");
    setAvailableQuantity("");
    setShelfNumber("");
  };


  // =========================
  // SHOW ERROR
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

      alert("Could not connect to the backend");

    }
  };


  // =========================
  // UI
  // =========================

  return (

    <div>
      <button onClick={() => window.location.href = "/"}>
        ← Back to Dashboard
      </button>

      <h1>Books</h1>


      {/* ADD BOOK BUTTON */}

      <button
        onClick={() => {
          setEditingId(null);
          setShowForm(true);
        }}
      >
        Add Book
      </button>


      <br />
      <br />


      {/* ADD / EDIT FORM */}

      {showForm && (

        <form
          onSubmit={editingId ? updateBook : addBook}
        >

          <h2>
            {editingId ? "Edit Book" : "Add Book"}
          </h2>


          {/* ISBN */}

          <label>ISBN</label>

          <input
            type="text"
            value={isbn}
            onChange={(event) =>
              setIsbn(event.target.value)
            }
          />


          {/* TITLE */}

          <label>Title</label>

          <input
            type="text"
            value={title}
            onChange={(event) =>
              setTitle(event.target.value)
            }
          />


          {/* AUTHOR */}

          <label>Author</label>

          <input
            type="text"
            value={author}
            onChange={(event) =>
              setAuthor(event.target.value)
            }
          />


          {/* PUBLISHER */}

          <label>Publisher</label>

          <input
            type="text"
            value={publisher}
            onChange={(event) =>
              setPublisher(event.target.value)
            }
          />


          {/* CATEGORY */}

          <label>Category</label>

          <input
            type="text"
            value={category}
            onChange={(event) =>
              setCategory(event.target.value)
            }
          />


          {/* QUANTITY */}

          <label>Quantity</label>

          <input
            type="number"
            value={quantity}
            onChange={(event) =>
              setQuantity(event.target.value)
            }
          />


          {/* AVAILABLE QUANTITY */}

          <label>Available Quantity</label>

          <input
            type="number"
            value={availableQuantity}
            onChange={(event) =>
              setAvailableQuantity(event.target.value)
            }
          />


          {/* SHELF NUMBER */}

          <label>Shelf Number</label>

          <input
            type="text"
            value={shelfNumber}
            onChange={(event) =>
              setShelfNumber(event.target.value)
            }
          />


          <br />
          <br />


          {/* SAVE / UPDATE */}

          <button type="submit">

            {editingId ? "Update" : "Save"}

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


      {/* BOOKS TABLE */}

      <table border="1">

        <thead>

          <tr>

            <th>ID</th>

            <th>ISBN</th>

            <th>Title</th>

            <th>Author</th>

            <th>Publisher</th>

            <th>Category</th>

            <th>Quantity</th>

            <th>Available Quantity</th>

            <th>Shelf Number</th>

            <th>Actions</th>

          </tr>

        </thead>


        <tbody>

          {books.map((book) => (

            <tr key={book.id}>

              <td>{book.id}</td>

              <td>{book.isbn}</td>

              <td>{book.title}</td>

              <td>{book.author}</td>

              <td>{book.publisher}</td>

              <td>{book.category}</td>

              <td>{book.quantity}</td>

              <td>{book.availableQuantity}</td>

              <td>{book.shelfNumber}</td>

          <td>

            <button
              onClick={() => editBook(book)}
            >
              Edit
            </button>

            <button
              onClick={() => deleteBook(book.id)}
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

export default Books;