function Dashboard() {
  return (
    <div className="dashboard">

      <header className="dashboard-header">
        <h1>📚 Library Management System</h1>

        <button
          className="logout-button"
          onClick={() => {
            localStorage.removeItem("token");
            window.location.href = "/";
          }}
        >
          Logout
        </button>
      </header>

      <div className="dashboard-content">

        <h2>Dashboard</h2>

        <p>Welcome to the Library Management System</p>

        <div className="dashboard-cards">

          <div className="dashboard-card">
            <h3>👨‍🎓 Students</h3>
            <p>Manage students</p>
            <button onClick={() => window.location.href = "/students"}>
                      View Students
                    </button>
          </div>

          <div className="dashboard-card">
            <h3>📚 Books</h3>
            <p>Manage books</p>
            <button onClick={() => window.location.href = "/books"}>
              View Books
            </button>
          </div>

          <div className="dashboard-card">
            <h3>📕 Book Issues</h3>
            <p>Issue and return books</p>
            <button onClick={() => window.location.href = "/book-issues"}>
              View Issues
            </button>
          </div>

          <div className="dashboard-card">
            <h3>👤 Librarians</h3>
            <p>Manage librarians</p>
             <button onClick={() => window.location.href = "/librarians"}>
                View Librarians
              </button>
          </div>

        </div>

      </div>

    </div>
  );
}

export default Dashboard;