package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

import application.Database;
import application.Main;

public class Testing {

	@Test
	public final void testing() {
		System.out.println("dd");
		
		assertEquals(true, Database.checkLogin("t", "t"));
		//assertEquals(true, Database.addLoan(1, 1));
		//assertEquals(true, Database.addReturn(1, 1));
		//assertEquals(true, Database.addMedia(null, null, 1));
		
	}
	
}
