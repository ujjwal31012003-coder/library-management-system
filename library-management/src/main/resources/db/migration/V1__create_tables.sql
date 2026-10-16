CREATE TABLE students (

    id BIGSERIAL PRIMARY KEY,

    student_id VARCHAR(20) UNIQUE NOT NULL,

    name VARCHAR(100) NOT NULL,

    age INT NOT NULL,

    email VARCHAR(100) UNIQUE NOT NULL,

    phone VARCHAR(15),

    course VARCHAR(100),

    year INT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);

CREATE TABLE books (

    id BIGSERIAL PRIMARY KEY,

    isbn VARCHAR(20) UNIQUE NOT NULL,

    title VARCHAR(255) NOT NULL,

    author VARCHAR(150) NOT NULL,

    publisher VARCHAR(150),

    category VARCHAR(100),

    quantity INT NOT NULL,

    available_quantity INT NOT NULL,

    shelf_number VARCHAR(20),

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);

CREATE TABLE librarians (

    id BIGSERIAL PRIMARY KEY,

    username VARCHAR(100) UNIQUE NOT NULL,

    password VARCHAR(255) NOT NULL,

    role VARCHAR(30) NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);
CREATE TABLE book_issues (

    id BIGSERIAL PRIMARY KEY,

    student_id BIGINT NOT NULL,

    book_id BIGINT NOT NULL,

    issue_date DATE NOT NULL,

    due_date DATE NOT NULL,

    return_date DATE,

    status VARCHAR(20) NOT NULL,

    CONSTRAINT fk_student
        FOREIGN KEY(student_id)
        REFERENCES students(id),

    CONSTRAINT fk_book
        FOREIGN KEY(book_id)
        REFERENCES books(id)

);

