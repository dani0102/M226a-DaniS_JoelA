package ch.nyp;

import java.time.LocalDate;

public class Media {
	String title;
	LocalDate lendDate;
	LocalDate returnDate;
	boolean available;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getLendDate() {
		return lendDate;
	}
	public void setLendDate(LocalDate lendDate) {
		this.lendDate = lendDate;
	}
	public LocalDate getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
}
