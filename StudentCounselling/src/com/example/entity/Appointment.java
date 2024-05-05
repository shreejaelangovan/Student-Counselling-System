package com.example.entity;

public class Appointment {

	private int appointmentId;
    private String appointmentDay;
    private String appointmentSlot;
    private int counselorId;
    private int studentId;
    
    public Appointment(int appointmentId, String appointmentDay, String appointmentSlot, int counselorId,int studentId) {
    	super();
    	this.appointmentId = appointmentId;
    	this.appointmentDay = appointmentDay;
    	this.appointmentSlot = appointmentSlot;
    	this.counselorId = counselorId;
    	this.studentId = studentId;
    }
    
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	public String getAppointmentDay() {
		return appointmentDay;
	}
	public void setAppointmentDay(String appointmentDay) {
		this.appointmentDay = appointmentDay;
	}
	public String getAppointmentSlot() {
		return appointmentSlot;
	}
	public void setAppointmentSlot(String appointmentSlot) {
		this.appointmentSlot = appointmentSlot;
	}
	public int getCounselorId() {
		return counselorId;
	}
	public void setCounselorId(int counselorId) {
		this.counselorId = counselorId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", appointmentDay=" + appointmentDay
				+ ", appointmentSlot=" + appointmentSlot + ", counselorId=" + counselorId + ", studentId=" + studentId
				+ "]";
	}


}
