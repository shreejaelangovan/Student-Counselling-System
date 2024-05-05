package com.example.interfc;

import com.example.entity.Booking;

public abstract class BookingInterface {
	public abstract void saveOrUpdateBooking(Booking booking);
	public abstract void deleteBookingByAppointmentId(int appointmentId);

}
