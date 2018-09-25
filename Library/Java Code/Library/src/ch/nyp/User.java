package ch.nyp;

import java.util.ArrayList;

public class User {
	String username;
	ArrayList<Media> borrowedMedia;
	
	public void borrowMedia(Media media) {
		borrowedMedia.add(media);
	}
}
