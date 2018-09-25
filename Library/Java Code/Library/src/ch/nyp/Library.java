package ch.nyp;

import java.time.LocalDate;
import java.util.ArrayList;

public class Library {
	
	public static final int BOOK_LEND_DAYS = 28;
	public static final int DVD_LEND_DAYS = 7;
	
	private ArrayList<Media> mediaList;
	
	public void addMedia(Media media) {
		mediaList.add(media);
	}
	
	public void removeMedia(Media media) {
		mediaList.remove(media);
	}
	
	public void lendMedia(User user, Media media) {
		media.setAvailable(false);
		media.setLendDate(LocalDate.now());
		
		if (media instanceof Book) {
			media.setReturnDate(LocalDate.now().plusDays(BOOK_LEND_DAYS));
		} else if (media instanceof DVD) {
			media.setReturnDate(LocalDate.now().plusDays(DVD_LEND_DAYS));
		}
		
		user.borrowMedia(media);
		
	}
}
