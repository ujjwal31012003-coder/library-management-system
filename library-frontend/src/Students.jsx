import { useEffect, useState } from "react";
import axios from "axios";

function Students() {

  const [students, setStudents] = useState([]);

  const [showForm, setShowForm] = useState(false);

  const [studentId, setStudentId] = useState("");
  const [name, setName] = useState("");
  const [age, setAge] = useState("");
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");
  const [course, setCourse] = useState("");
  const [year, setYear] = useState("");

  const [editingId, setEditingId] = useState(null);


  // =========================
  // GET ALL STUDENTS
  // =========================

  useEffect(() => {
    getStudents();
  }, []);


  const getStudents = async () => {

    try {

      const token = localStorage.getItem("token");

      const response = await axios.get(
        "http://localhost:8080/students",
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      setStudents(response.data);

    } catch (error) {

      console.log("Error loading students:", error);

    }
  };


  // =========================
  // ADD STUDENT
  // =========================

  const addStudent = async (event) => {

    event.preventDefault();

    try {

      const token = localStorage.getItem("token");

      const studentData = {
        studentId: studentId,
        name: name,
        age: Number(age),
        email: email,
        phone: phone,
        course: course.trim(),
        year: Number(year)
      };

      console.log("Sending student data:", studentData);

      await axios.post(
        "http://localhost:8080/students",
        studentData,
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      alert("Student added successfully!");

      closeForm();

      getStudents();

    } catch (error) {

      console.log("Error adding student:", error);

      showError(error);
    }
  };


  // =========================
  // OPEN EDIT FORM
  // =========================

  const editStudent = (student) => {

    setEditingId(student.id);

    setStudentId(student.studentId);
    setName(student.name);
    setAge(student.age);
    setEmail(student.email);
    setPhone(student.phone);
    setCourse(student.course);
    setYear(student.year);

    setShowForm(true);
  };


  // =========================
  // UPDATE STUDENT
  // =========================

  const updateStudent = async (event) => {

    event.preventDefault();

    try {

      const token = localStorage.getItem("token");

      const studentData = {
        studentId: studentId,
        name: name,
        age: Number(age),
        email: email,
        phone: phone,
        course: course.trim(),
        year: Number(year)
      };

      console.log("Updating student:", studentData);

      await axios.put(
        `http://localhost:8080/students/${editingId}`,
        studentData,
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      alert("Student updated successfully!");

      closeForm();

      getStudents();

    } catch (error) {

      console.log("Error updating student:", error);

      showError(error);
    }
  };

const deleteStudent = async (id) => {

  const confirmDelete = window.confirm(
    "Are you sure you want to delete this student?"
  );

  if (!confirmDelete) {
    return;
  }

  try {

    const token = localStorage.getItem("token");

    await axios.delete(
      `http://localhost:8080/students/${id}`,
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    );

    alert("Student deleted successfully!");

    getStudents();

  } catch (error) {

    console.log("Error deleting student:", error);

    showError(error);
  }
};


  // =========================
  // CLOSE FORM
  // =========================

  const closeForm = () => {

    setShowForm(false);

    setEditingId(null);

    setStudentId("");
    setName("");
    setAge("");
    setEmail("");
    setPhone("");
    setCourse("");
    setYear("");
  };


  // =========================
  // ERROR MESSAGE
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
  // PAGE
  // =========================

  return (
    <div>
      <button onClick={() => window.location.href = "/"}>
        ← Back to Dashboard
      </button>

      <h1>Students</h1>


      {/* ADD STUDENT BUTTON */}

      <button
        onClick={() => {
          setEditingId(null);
          setShowForm(true);
        }}
      >
        Add Student
      </button>


      {/* FORM */}

      {showForm && (

        <form
          onSubmit={
            editingId
              ? updateStudent
              : addStudent
          }
        >

          <h2>
            {editingId
              ? "Edit Student"
              : "Add Student"
            }
          </h2>


          <label>Student ID</label>

          <input
            type="text"
            value={studentId}
            onChange={(event) =>
              setStudentId(event.target.value)
            }
          />


          <label>Name</label>

          <input
            type="text"
            value={name}
            onChange={(event) =>
              setName(event.target.value)
            }
          />


          <label>Age</label>

          <input
            type="number"
            value={age}
            onChange={(event) =>
              setAge(event.target.value)
            }
          />


          <label>Email</label>

          <input
            type="email"
            value={email}
            onChange={(event) =>
              setEmail(event.target.value)
            }
          />


          <label>Phone</label>

          <input
            type="text"
            value={phone}
            onChange={(event) =>
              setPhone(event.target.value)
            }
          />


          <label>Course</label>

          <input
            type="text"
            value={course}
            onChange={(event) =>
              setCourse(event.target.value)
            }
          />


          <label>Year</label>

          <input
            type="number"
            value={year}
            onChange={(event) =>
              setYear(event.target.value)
            }
          />


          <br />
          <br />


          <button type="submit">
            {editingId
              ? "Update"
              : "Save"
            }
          </button>


          <button
            type="button"
            onClick={closeForm}
          >
            Cancel
          </button>

        </form>
      )}


      <br />


      {/* STUDENT TABLE */}

      <table>

        <thead>

          <tr>

            <th>ID</th>

            <th>Student ID</th>

            <th>Name</th>

            <th>Email</th>

            <th>Course</th>

            <th>Year</th>

            <th>Actions</th>

          </tr>

        </thead>


        <tbody>

          {students.map((student) => (

            <tr key={student.id}>

              <td>
                {student.id}
              </td>

              <td>
                {student.studentId}
              </td>

              <td>
                {student.name}
              </td>

              <td>
                {student.email}
              </td>

              <td>
                {student.course}
              </td>

              <td>
                {student.year}
              </td>

              <td>

                <button
                  onClick={() =>
                    editStudent(student)
                  }
                >
                  Edit
                </button>

           <button
             onClick={() => deleteStudent(student.id)}
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

export default Students;