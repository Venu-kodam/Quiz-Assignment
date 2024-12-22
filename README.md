# Quiz App
This project is a Quiz Application API developed using Spring Boot with an in-memory H2 Database. 
The application allows users to start a quiz session, answer multiple-choice questions, and track their progress.

## Technologies Used
- Java
- Spring Boot
- H2 Database (in-memory database)
- Postman (for API testing)
  
## Features
- Start a Quiz Session
  Users can initiate a new quiz session with their user ID.
- Get Random Questions
  Fetch a random multiple-choice question from the database.
- Submit Answers
  Users can submit their answers to questions and get immediate feedback on correctness.
- Track Quiz Statistics
  Get the total number of questions answered along with details on correct and incorrect submissions.

## API Endpoints
- Start a New Quiz Session
  - URL: /api/quiz/start
  - Method: POST
  - Request Body: <br>
    { <br>
      "userId": 1 <br>
    }
  - Response: <br>
    { <br>
      "sessionId": 1, <br>
      "userId": 1, <br>
      "startTime": "2024-12-18T12:00:00" <br>
    }
    
- Get a Random Question
  - URL: /api/quiz/question/{sessionId}
  - Method: GET
  - Response: <br>
    { <br>
      "questionId": 1, <br>
      "questionText": "What is 2 + 2?", <br>
      "options": ["2", "3", "4", "5"] <br>
    }
    
- Submit an Answer
  - URL: /api/quiz/submit
  - Method: POST
  - Request Body: <br>
    { <br>
      "sessionId": 1, <br>
      "questionId": 1, <br>
      "selectedOption": "4" <br>
    }
    
  - Response (if correct): <br>
    { <br>
      "correct": true, <br>
      "message": "Correct answer!" <br>
    }
    
  - Response (if incorrect): <br>
    { <br>
      "correct": false, <br>
      "message": "Incorrect answer!" <br>
    }
- Get Quiz Statistics
  - URL: /api/quiz/stats/{sessionId}
  - Method: GET
  - Response: <br>
    { <br>
      "totalAnswered": 3, <br>
      "correctAnswers": 2, <br>
      "incorrectAnswers": 1 <br>
    }

## Assumptions
- The database is pre-seeded with:
  - A single user (userId: 1).
  - 10 multiple-choice questions with options and correct answers.
- Only one active quiz session is supported per user at a time.
- Questions are stored in the database with their options and correct answers.

## How to Run
- Clone the repository.
- Open the project in your favorite IDE (e.g., IntelliJ, Eclipse).
- Run the application using the QuizAppApplication class.
  
## Access the H2 Console:
- URL: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:testdb
- Username: enter username
- Password: enter password
- Use Postman or any REST client to test the APIs.
