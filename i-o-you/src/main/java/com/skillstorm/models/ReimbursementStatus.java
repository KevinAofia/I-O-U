package com.skillstorm.models;

public class ReimbursementStatus {

	// Attributes

	private int id;
	private String status;

	// Constructors

	public ReimbursementStatus() {
		super();
	}

	public ReimbursementStatus(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}
	// Getters and Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	// Methods

	@Override
	public String toString() {
		return "ReimbursementStatus [id=" + id + ", status=" + status + "]";
	}

}
