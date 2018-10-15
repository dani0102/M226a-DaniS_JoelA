package application;

public class Book extends Media {

	private String author;
	private int pages;
		
	public Book(String author, int pages, String title, String lendDate, String returnDate, String available, String user) {
		super(title, lendDate, returnDate, available, user);
		this.author = author;
		this.pages = pages;
	}
		
	public String getAuthor() {
		return author;
	}
		
	public void setAuthor(String author) {
		this.author = author;
	}
		
	public int getPages() {
		return pages;
	}
	
	public void setPages(int pages) {
		this.pages = pages;
	}


	
}
