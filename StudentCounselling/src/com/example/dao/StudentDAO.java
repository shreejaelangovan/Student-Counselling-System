package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.entity.Student;
import com.example.interfc.StudentInterface;
import com.example.util.DBConnection;

public class StudentDAO extends StudentInterface {
	public Student getStudentById(int studentId) {
	    Student student = null;
	    String query = "SELECT * FROM students WHERE studentId = ?";
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {
	        connection = DBConnection.getConnection();
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, studentId);
	        resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            String name = resultSet.getString("studentName");
	            String email = resultSet.getString("studentEmail");
//	            System.out.println("\nID : "+studentId+"\nName : "+name+"\nEmail : "+email);
//                System.out.println();
	            student = new Student(studentId, name, email);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    finally {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (statement != null) {
	                statement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return student;
	}


    public void saveOrUpdateStudent(Student student) {
        String query;
        if (student.getStudentId() == 0) {
            query = "INSERT INTO students (studentName, studentEmail) VALUES (?, ?)";
        } else {
            query = "UPDATE students SET studentName = ?, studentEmail = ? WHERE studentId = ?";
        }

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, student.getStudentName());
            statement.setString(2, student.getStudentEmail());

            if (student.getStudentId() != 0) {
                statement.setInt(3, student.getStudentId());
            }

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void deleteStudent(int studentId) {
        String query = "DELETE FROM students WHERE studentId = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, studentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("studentId");
                String name = resultSet.getString("studentName");
                String email = resultSet.getString("studentEmail");
//                System.out.println("\nID : "+id+"\nName : "+name+"\nEmail : "+email);
//                System.out.println();
                students.add(new Student(id, name, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return students;
    }

}