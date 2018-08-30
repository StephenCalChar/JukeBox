import java.io.Serializable;
import java.util.Comparator;

public class Songs implements Serializable{
	
	private String title;
	
	
	public Songs() {
		// TODO Auto-generated constructor stub
	}
	
	public Songs(String song) {
		this.title = song;
	}
	
	
	public String toString() {
		return title;
		
	}

	// get & set
	
	public void setTitle(String title) {
		this.title = title;
		
	}
	
	
	public String getTitle() {
		return this.title;
		
	}

/*
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Songs songs = (Songs) o;
		int compare  = this.getTitle().toLowerCase().compareTo(songs.getTitle().toLowerCase());
		//JOptionPane.showMessageDialog(null, compare);
		if(compare > 0) {
			return 1;
			
		}
		if(compare < 0) {
			return -1;
			
		}
		return 0;	
	
	}
*/

	
	public static Comparator<Songs> titleComparator = new Comparator<Songs>() {

		@Override
		public int compare(Songs o1, Songs o2) {
			// TODO Auto-generated method stub
			return o1.getTitle().compareTo(o2.getTitle());
		}
		
	};
	public static Comparator<Songs> reverseTitleComparator = new Comparator<Songs>() {

		@Override
		public int compare(Songs o1, Songs o2) {
			// TODO Auto-generated method stub
			return (o2.getTitle().compareTo(o1.getTitle()));
		}
		
	};
	
	
	
	
	
	

}
