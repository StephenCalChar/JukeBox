import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JukeBoxTest {

	@Test
	void testAddSong() {
		JukeBox bigBoomer = new JukeBox();
		Songs s1 = new Songs("Music Sux");
		
		bigBoomer.addSong(s1);	
	}

}
