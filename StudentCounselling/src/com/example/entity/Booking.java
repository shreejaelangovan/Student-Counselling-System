package com.example.entity;


public class Booking {

	private int bookingId;
    private String bookingDay;
    private String bookingSlot;
    private int studentId;
    private int appointmentId;
    
    public Booking(int bookingId, String bookingDay, String bookingSlot, int studentId, int appointmentId) {
    	super();
    	this.bookingId = bookingId;
    	this.bookingDay = bookingDay;
    	this.bookingSlot = bookingSlot;
    	this.studentId = studentId;
    	this.appointmentId = appointmentId;
    }
    
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getBookingDay() {
		return bookingDay;
	}
	public void setBookingDay(String bookingDay) {
		this.bookingDay = bookingDay;
	}
	public String getBookingSlot() {
		return bookingSlot;
	}
	public void setBookingSlot(String bookingSlot) {
		this.bookingSlot = bookingSlot;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getAppointmentId() {
		return appointmentId;
	}
	
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", bookingDay=" + bookingDay + ", bookingSlot=" + bookingSlot
				+ ", studentId=" + studentId + ", appointmentId=" + appointmentId + "]";
	}

    
}
