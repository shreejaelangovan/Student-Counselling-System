package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.entity.Booking;
import com.example.interfc.BookingInterface;
import com.example.util.DBConnection;

public class BookingDAO extends BookingInterface {
	public void saveOrUpdateBooking(Booking booking) {
	    String query = "";
	    if (booking.getBookingId() == 0) {
	        String checkQuery = "SELECT appointmentId FROM appointments WHERE appointmentId = ?";
	        Connection connection = null;
	        PreparedStatement checkStatement = null;
	        ResultSet resultSet = null;

	        try {
	            connection = DBConnection.getConnection();
	            checkStatement = connection.prepareStatement(checkQuery);
	            checkStatement.setInt(1, booking.getAppointmentId());
	            resultSet = checkStatement.executeQuery();

	            if (!resultSet.next()) {
	                System.out.println();
	                return;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return;
	        } finally {
	            try {
	                if (resultSet != null) {
	                    resultSet.close();
	                }
	                if (checkStatement != null) {
	                    checkStatement.close();
	                }
	                if (connection != null) {
	                    connection.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        query = "INSERT INTO bookings (appointmentId, bookingDay, bookingSlot, studentId) VALUES (?, ?, ?, ?)";
	    } else {
	        query = "UPDATE bookings SET appointmentId = ?, bookingDay = ?, bookingSlot = ?, studentId = ? WHERE bookingId = ?";
	    }

	    Connection connection = null;
	    PreparedStatement statement = null;

	    try {
	        connection = DBConnection.getConnection();
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, booking.getAppointmentId());
	        statement.setString(2, booking.getBookingDay());
	        statement.setString(3, booking.getBookingSlot());
	        statement.setInt(4, booking.getStudentId());

	        if (booking.getBookingId() != 0) {
	            statement.setInt(5, booking.getBookingId());
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

	
	public void deleteBookingByAppointmentId(int appointmentId) {
	    String query = "DELETE FROM bookings WHERE appointmentId = ?";
	    Connection connection = null;
	    PreparedStatement statement = null;

	    try {
	        connection = DBConnection.getConnection();
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, appointmentId);
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

}