package com.example.interfc;

import java.util.List;

import com.example.entity.Appointment;

public abstract class AppointmentInterface {
	public abstract void saveOrUpdateAppointment(Appointment appointment);
	public abstract void deleteAppointmentById(int appointmentId);
	public abstract List<Appointment> getAppointmentsByStudent(int studentId);
	public abstract List<Appointment> getAppointmentsByCounselor(int counselorId);
	public abstract boolean isSlotBooked(String day, String slot, int counselorId);
	public abstract Appointment getAppointmentsById(int appointmentId);

}
