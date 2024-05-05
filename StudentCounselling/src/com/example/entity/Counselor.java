package com.example.entity;

public class Counselor {
	private int counselorId;
    private String counselorName;
    private String counselorEmail;
    
    public Counselor(int counselorId, String counselorName, String counselorEmail) {
    	super();
    	this.counselorId = counselorId;
    	this.counselorName = counselorName;
    	this.counselorEmail = counselorEmail;
    }
    
	public int getCounselorId() {
		return counselorId;
	}
	public void setCounselorId(int counselorId) {
		this.counselorId = counselorId;
	}
	public String getCounselorName() {
		return counselorName;
	}
	public void setCounselorName(String counselorName) {
		this.counselorName = counselorName;
	}
	public String getCounselorEmail() {
		return counselorEmail;
	}
	public void setCounselorEmail(String counselorEmail) {
		this.counselorEmail = counselorEmail;
	}
	
	@Override
	public String toString() {
		return "Counselor [counselorId=" + counselorId + ", counselorName=" + counselorName + ", counselorEmail="
				+ counselorEmail + "]";
	}

}
