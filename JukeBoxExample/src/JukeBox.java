

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;


import javax.swing.JOptionPane;

import com.sun.org.apache.bcel.internal.generic.ReturnInstruction;

public class JukeBox implements Serializable{
	
	private LinkedList<Songs> playlist = new LinkedList<Songs>();
	PopularSongTitleWords word;
	final String path = "game.ser";
	final int loadGameChoice =1;
	
	//constructor
	public JukeBox() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//methods
	public void addSong(Songs s) {
		this.playlist.add(s);
	}
	
	public Songs play() {
		Songs nextSong = playlist.peekFirst();
		//JOptionPane.showMessageDialog(null, playlist.peekFirst());
		this.playlist.remove(playlist.peekFirst());
		return nextSong;
		
	}
	
	public String printPlayList() {
		String tracks ="";
		for(Songs song: this.playlist) {
			tracks += song.getTitle() + "\n";
		}
		return tracks;
		//JOptionPane.showMessageDialog(null, tracks);
	}
	
	public int size() {
		return this.playlist.size();
	}
	
	
	public void addSongs(LinkedList<Songs> i) {
		this.playlist.addAll(i);
		
	}
	

	
	public int numberPopular() {
		int count = 0;
		for (Songs song: this.playlist) {
			int tempCount =0;
			String[] titleWords = song.toString().split("\\s+");
			for(int loop =0; loop < titleWords.length; loop++) {
				for(PopularSongTitleWords word: PopularSongTitleWords.values()) {
					if(titleWords[loop].equalsIgnoreCase(word.toString())) {
						tempCount++;
					}
				}
			}
			if(tempCount > 0) {
				count++;
			}
		}
		
		
		
		return count;
		
	}
	
	
	
	
	public int numberUnusual() {
		
		//bad practice (should just call numberPopular and calculate difference from list size and returned int
		int count = 0;
		for (Songs song: this.playlist) {
			int tempCount =0;
			String[] titleWords = song.toString().split("\\s+");
			for(int loop =0; loop < titleWords.length; loop++) {
				for(PopularSongTitleWords word: PopularSongTitleWords.values()) {
					if(titleWords[loop].equalsIgnoreCase(word.toString())) {
						tempCount++;
					}
				}
			}
			if(tempCount == 0) {
				count++;
			}
		}
		
		
		
		return count;
	}
	public void sort() {
		Collections.sort(this.playlist, Songs.titleComparator);
		
		
	}
	public void reverseSort() {
		Collections.sort(this.playlist, Songs.reverseTitleComparator);
		
	}
	
	
	public void noSoppySongs() {
		HashSet<Songs> songsToRemove = new HashSet<Songs>();
		for (Songs song: this.playlist) {
			try {
				String[] titleWords = song.toString().split("\\s+");
				for(int loop =0; loop < titleWords.length; loop++) {
					if(titleWords[loop].toLowerCase().equals(PopularSongTitleWords.LOVE.toString().toLowerCase())) {
						throw new NoSloopyLoveSongs();
					} //end if
				} //end for
			} catch (NoSloopyLoveSongs e)
			{
				songsToRemove.add(song);
				System.out.println(e.toString());
				
			} //end catch
		} //end for
		
	
		for(Songs song: songsToRemove) {
			this.playlist.remove(song);
		}
		
		

	} //end noSoppy
	
	
	
	public void loadPlayList(String filePath) {
		//String filePath ="C:\\Users\\Steve\\Desktop\\Uni\\Software Development 2\\Week 5\\playlist.txt";
		File file = new File(filePath);
		String inputSong;
		LinkedList<Songs> songstoBeAdded = new LinkedList<Songs>();
		
		try {
			Scanner inputStream = new Scanner(new BufferedReader(new FileReader(file)));
			while(inputStream.hasNextLine() ==true) {
				inputSong = inputStream.nextLine();
				Songs song = new Songs(inputSong);
				songstoBeAdded.add(song);
			}
			inputStream.close();
			
		} //end try
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for(Songs song: songstoBeAdded) {
			this.playlist.add(song);
		}
		
		
		
		
	} //end loadPlaylist








	

	
	@SuppressWarnings("unchecked")
	public void startUp() {
		int selection;
		String[] options = {"Empty Playlist", "Load Playlist"};
		selection = JOptionPane.showOptionDialog(null, "please choose", "Select", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if(selection == this.loadGameChoice) {
			LinkedList<Songs> loadList;
			try {
				FileInputStream fis = new FileInputStream(this.path);
				@SuppressWarnings("resource")
				ObjectInputStream objectInputStream = new ObjectInputStream(fis);
				loadList = (LinkedList<Songs>) objectInputStream.readObject();
				setPlaylist(loadList);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			catch(IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		
	}
	
	public void saveGame() {
		try {
			FileOutputStream fos= new FileOutputStream(this.path);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			//write whole object or just tracklist?
			oos.writeObject(getPlaylist());
			oos.close();
			
		}
		catch (IOException e){
			e.printStackTrace();
			
		}	
	}




	public LinkedList<Songs> getPlaylist() {
		return this.playlist;
	}


	public void setPlaylist(LinkedList<Songs> playlist) {
		this.playlist = playlist;
	}

	
	
	
	

} //end class
