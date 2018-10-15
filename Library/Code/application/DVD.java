package application;

public class DVD extends Media {

	private String author;
	private int lengthMin;
	
	public DVD(String author, int lengthMin, String title, String lendDate, String returnDate, String available, String user) {
		super(title, lendDate, returnDate, available, user);
		this.author = author;
		this.lengthMin = lengthMin;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public int getLengthMin() {
		return lengthMin;
	}
	
	public void setLengthMin(int lengthMin) {
		this.lengthMin = lengthMin;
	}
	
}
