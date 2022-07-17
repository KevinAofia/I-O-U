package com.skillstorm.models;

public class Expense {

	// Attributes

	private int id;
	private String firstName;
	private String lastName;
	private String date;
	private String reason;
	private ReimbursementStatus status;
	
	// Constructors

	public Expense() {
		super();
	}

	public Expense(String firstName, String lastName, String date, String reason) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.date = date;
		this.reason = reason;
	}

	// Getters and Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public ReimbursementStatus getStatus() {
		return status;
	}

	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}

	// Methods

	@Override
	public String toString() {
		return "Expense [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", date=" + date
				+ ", reason=" + reason + ", status=" + status + "]";
	}

}
