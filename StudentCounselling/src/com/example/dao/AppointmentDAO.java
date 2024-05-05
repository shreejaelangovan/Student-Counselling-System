package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.entity.Appointment;
import com.example.interfc.AppointmentInterface;
import com.example.util.DBConnection;

public class AppointmentDAO extends AppointmentInterface {
	@Override
	public void saveOrUpdateAppointment(Appointment appointment) {
	    String query;
	    if (appointment.getAppointmentId() == 0) {
	    	if (isSlotBooked(appointment.getAppointmentDay(), appointment.getAppointmentSlot(), appointment.getCounselorId())) {
	            System.out.println("Slot is already booked. Please choose another slot.");
	            return;
	        }else{
	        	System.out.println("Appointment Booked Successfully!!!");
	        }
	    } else {
	    	query = "INSERT INTO appointments (appointmentDay, appointmentSlot, counselorId, studentId) VALUES (?, ?, ?, ?)";
	    	// query = "UPDATE appointments SET appointmentDay = ?, appointmentSlot = ?, counselorId = ?, studentId = ? WHERE appointmentId = ?";

	    }

	    Connection connection = null;
	    PreparedStatement statement = null;

	    try {
	    	query = "INSERT INTO appointments (appointmentDay, appointmentSlot, counselorId, studentId) VALUES (?, ?, ?, ?)";
	        connection = DBConnection.getConnection();
	        statement = connection.prepareStatement(query);
	        statement.setString(1, appointment.getAppointmentDay());
	        statement.setString(2, appointment.getAppointmentSlot());
	        statement.setInt(3, appointment.getCounselorId());
	        statement.setInt(4, appointment.getStudentId());

	        if (appointment.getAppointmentId() != 0) {
	            statement.setInt(5, appointment.getAppointmentId());
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


	public void deleteAppointmentById(int appointmentId) {
	    String query = "DELETE FROM appointments WHERE appointmentId = ?";
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

	@Override
	public List<Appointment> getAppointmentsByStudent(int studentId) {
	    List<Appointment> appointments = new ArrayList<>();
	    String query = "SELECT * FROM appointments WHERE studentId = ?";
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {
	        connection = DBConnection.getConnection();
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, studentId);
	        resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            int id = resultSet.getInt("appointmentId");
	            String day = resultSet.getString("appointmentDay");
	            String slot = resultSet.getString("appointmentSlot");
	            int counselorId = resultSet.getInt("counselorId");
//	            System.out.println("\nID : "+id+"\nDay : "+day+"\nSlot : "+slot+"\nCounselorId : "+counselorId+"\nStudentId : "+studentId);
//                System.out.println();
	            appointments.add(new Appointment(id, day, slot, counselorId, studentId));
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
	    return appointments;
	}

	@Override
	public List<Appointment> getAppointmentsByCounselor(int counselorId) {
	    List<Appointment> appointments = new ArrayList<>();
	    String query = "SELECT * FROM appointments WHERE counselorId = ?";
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {
	        connection = DBConnection.getConnection();
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, counselorId);
	        resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            int id = resultSet.getInt("appointmentId");
	            String day = resultSet.getString("appointmentDay");
	            String slot = resultSet.getString("appointmentSlot");
	            int studentId = resultSet.getInt("studentId");
//	            System.out.println("\nID : "+id+"\nDay : "+day+"\nSlot : "+slot+"\nCounselorId : "+counselorId+"\nStudentId : "+studentId);
//                System.out.println();
	            appointments.add(new Appointment(id, day, slot, counselorId, studentId));
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
	    return appointments;
	}
	
	@Override
	public boolean isSlotBooked(String day, String slot, int counselorId) {
	    String query = "SELECT COUNT(*) FROM appointments WHERE appointmentDay = ? AND appointmentSlot = ? AND counselorId = ?";
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    int count=0;
	    try {
	        connection = DBConnection.getConnection();
	        statement = connection.prepareStatement(query);
	        statement.setString(1, day);
	        statement.setString(2, slot);
	        statement.setInt(3, counselorId);
	        resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	             count = resultSet.getInt(1);
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
	    return count>0;
	}
    
	@Override
	public Appointment getAppointmentsById(int appointmentId) {
	    Appointment appointment = null;
	    String query = "SELECT * FROM appointments WHERE appointmentId = ?";
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {
	        connection = DBConnection.getConnection();
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, appointmentId);
	        resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            String day = resultSet.getString("appointmentDay");
	            String slot = resultSet.getString("appointmentSlot");
	            int counselorId = resultSet.getInt("counselorId");
	            int studentId = resultSet.getInt("studentId");
//	            System.out.println("\nID : "+appointmentId+"\nDay : "+day+"\nSlot : "+slot+"\nCounselorId : "+counselorId+"\nStudentId : "+studentId);
//                System.out.println();
	            appointment = new Appointment(appointmentId, day, slot, counselorId, studentId);
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
	    return appointment;
	}


}