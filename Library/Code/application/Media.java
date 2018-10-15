package application;

public class Media {

	private String title;
	private String lendDate;
	private String returnDate;
	private String available;
	private String user;
		
	public Media(String title, String lendDate, String returnDate, String available, String user) {
		this.title = title;
		this.lendDate = lendDate;
		this.returnDate = returnDate;
		this.available = available;
		this.user = user;
	}
		
	public String getTitle() {
		return title;
	}
		
	public void setTitle(String title) {
		this.title = title;
	}
		
	public String getLendDate() {
		return lendDate;
	}
	
	public void setLendDate(String lendDate) {
		this.lendDate = lendDate;
	}
	
	public String getReturnDate() {
		return returnDate;
	}
	
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	
	public String getAvailable() {
		return available;
	}
	
	public void setAvailable(String available) {
		this.available = available;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
}
