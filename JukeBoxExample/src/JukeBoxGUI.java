import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import jdk.dynalink.beans.StaticClass;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystemNotFoundException;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Frame;
import java.awt.TextField;

import javax.swing.JTextField;
import javax.swing.JTextArea;

public class JukeBoxGUI extends JFrame {

	private JPanel contentPane;
	private JButton btnPlaySong;
	private JLabel lblNewLabel;
	private JLabel currentPlayLabel;
	private JTextField songToAdd;
	private JButton btnAddToList;
	private JButton btnNoSloppyLove;
	private JukeBox boomBox;
	private JLabel lblCurrentPlaylist;
	private JTextArea currentPlayList;
	private JLabel numberUnusual;
	private JLabel numberPopular;
	private JButton btnNewButton;
	private JButton btnNumberPopular;
	private JButton sort;
	private JButton revSort;
	private JButton btnUploadSongs;
	private JButton btnBrowse;
	private JTextArea uploadField;
	private JButton btnNewButton_1;
	private JButton btnQuit;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JukeBoxGUI frame = new JukeBoxGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JukeBoxGUI() {
		boomBox = new JukeBox();
		boomBox.startUp();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 909, 704);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnPlaySong());
		contentPane.add(getLblNewLabel());
		contentPane.add(getCurrentPlayLabel());
		contentPane.add(getSongToAdd());
		contentPane.add(getBtnAddToList());
		contentPane.add(getBtnNoSloppyLove());
		contentPane.add(getLblCurrentPlaylist());
		contentPane.add(getCurrentPlayList());
		contentPane.add(getNumberUnusual());
		contentPane.add(getNumberPopular());
		contentPane.add(getBtnNewButton());
		contentPane.add(getBtnNumberPopular());
		contentPane.add(getSort());
		contentPane.add(getRevSort());
		contentPane.add(getBtnUploadSongs());
		contentPane.add(getBtnBrowse());
		contentPane.add(getUploadField());
		contentPane.add(getBtnNewButton_1());
		contentPane.add(getBtnQuit());
		updatePlayList();
		
	}
	
	
	
	//play song button
	private JButton getBtnPlaySong() {
		if (btnPlaySong == null) {
			btnPlaySong = new JButton("Play Song");
			btnPlaySong.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnPlaySong.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//start of button event.
					if(boomBox.size() > 0) {
						Songs currentSong = boomBox.play();
						updateSong(currentSong.getTitle());	
						updatePlayList();
					}
					else {
						updateSong("There are no songs to be played");
					}
				
				}
			});
			btnPlaySong.setBounds(117, 139, 117, 56);
		}
		return btnPlaySong;
	}
	
	
	private void updateSong(String title) {
		currentPlayLabel.setText(title);
		
	}
	
	private void updatePlayList() {
		String trackList = boomBox.printPlayList();
		currentPlayList.setText(trackList);
	}
	
	
	
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Currently Playing:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNewLabel.setBounds(62, 57, 153, 72);
		}
		return lblNewLabel;
	}
	private JLabel getCurrentPlayLabel() {
		if (currentPlayLabel == null) {
			currentPlayLabel = new JLabel("");
			currentPlayLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
			currentPlayLabel.setBounds(281, 72, 382, 42);
		}
		return currentPlayLabel;
	}
	private JTextField getSongToAdd() {
		if (songToAdd == null) {
			songToAdd = new JTextField();
			songToAdd.setBounds(157, 615, 241, 32);
			songToAdd.setColumns(10);
		}
		return songToAdd;
	}
	private JButton getBtnAddToList() {
		if (btnAddToList == null) {
			btnAddToList = new JButton("Add to List");
			btnAddToList.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//add song to list button
					if(songToAdd.getText().length() > 0) {
						String song = songToAdd.getText();
						songToAdd.setText("");
						Songs songToApend = new Songs(song);
						boomBox.addSong(songToApend);
						updatePlayList();
					}
		
				}
			});
			btnAddToList.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnAddToList.setBounds(10, 601, 117, 56);
		}
		return btnAddToList;
	}
	private JButton getBtnNoSloppyLove() {
		if (btnNoSloppyLove == null) {
			btnNoSloppyLove = new JButton("No Sloppy Love Songs");
			btnNoSloppyLove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					boomBox.noSoppySongs();
					updatePlayList();
				}
			});
			btnNoSloppyLove.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnNoSloppyLove.setBounds(29, 237, 215, 56);
		}
		return btnNoSloppyLove;
	}
	private JLabel getLblCurrentPlaylist() {
		if (lblCurrentPlaylist == null) {
			lblCurrentPlaylist = new JLabel("Current PlayList");
			lblCurrentPlaylist.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblCurrentPlaylist.setBounds(664, 211, 161, 42);
		}
		return lblCurrentPlaylist;
	}
	private JTextArea getCurrentPlayList() {
		if (currentPlayList == null) {
			currentPlayList = new JTextArea();
			currentPlayList.setBounds(580, 262, 306, 385);
		}
		return currentPlayList;
	}
	private JLabel getNumberUnusual() {
		if (numberUnusual == null) {
			numberUnusual = new JLabel("");
			numberUnusual.setBounds(438, 580, 83, 25);
		}
		return numberUnusual;
	}
	private JLabel getNumberPopular() {
		if (numberPopular == null) {
			numberPopular = new JLabel("");
			numberPopular.setBounds(438, 517, 83, 25);
		}
		return numberPopular;
	}
	//number unusual
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Number Unusual");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				numberUnusual.setText(Integer.toString(boomBox.numberUnusual()));	
				}
			});
			btnNewButton.setBounds(281, 566, 147, 39);
		}
		return btnNewButton;
	}
	private JButton getBtnNumberPopular() {
		if (btnNumberPopular == null) {
			btnNumberPopular = new JButton("Number Popular");
			btnNumberPopular.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int numberPop = boomBox.numberPopular();
					numberPopular.setText(Integer.toString(numberPop));
					updatePlayList();
				}
			});
			btnNumberPopular.setBounds(281, 510, 147, 42);
		}
		return btnNumberPopular;
	}
	//sort playlist
	private JButton getSort() {
		if (sort == null) {
			sort = new JButton("Sort");
			sort.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					boomBox.sort();
					updatePlayList();
				}
			});
			sort.setBounds(602, 159, 117, 42);
		}
		return sort;
	}
	//reverse sort
	private JButton getRevSort() {
		if (revSort == null) {
			revSort = new JButton("Reverse Sort");
			revSort.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					boomBox.reverseSort();
					updatePlayList();
					
					
				}
			});
			revSort.setBounds(769, 159, 117, 42);
		}
		return revSort;
	}
	
	//upload songs button
	private JButton getBtnUploadSongs() {
		if (btnUploadSongs == null) {
			btnUploadSongs = new JButton("Upload Songs");
			btnUploadSongs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(uploadField.getText().length() > 0) {
						String filePath = uploadField.getText();
						boomBox.loadPlayList(filePath);
						uploadField.setText("");
						updatePlayList();
					}
					
					
				}
			});
			btnUploadSongs.setBounds(117, 403, 117, 42);
		}
		return btnUploadSongs;
	}
	
	//browse button
	private JButton getBtnBrowse() {
		if (btnBrowse == null) {
			btnBrowse = new JButton("Browse");
			btnBrowse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					final JFileChooser fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(new File("C:\\Users\\Steve\\Desktop\\Uni\\Software Development 2\\Week 5"));
					fileChooser.setDialogTitle("Please select a csv file");
					int choice = fileChooser.showOpenDialog(JukeBoxGUI.this);
					fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					if(choice == fileChooser.APPROVE_OPTION) {
						uploadField.setText(fileChooser.getSelectedFile().toString());
					}
					else {
						uploadField.setText("Please choose a valid file to upload");
					}
					
					
					
				}
			});
			btnBrowse.setBounds(293, 403, 105, 42);
		}
		return btnBrowse;
	}
	private JTextArea getUploadField() {
		if (uploadField == null) {
			uploadField = new JTextArea();
			uploadField.setBounds(10, 303, 547, 90);
		}
		return uploadField;
	}
	
	//save tracklist
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Save Tracklist");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					boomBox.saveGame();
					
				}
			});
			btnNewButton_1.setBounds(788, 51, 98, 42);
		}
		return btnNewButton_1;
	}
	private JButton getBtnQuit() {
		if (btnQuit == null) {
			btnQuit = new JButton("Quit");
			btnQuit.setBounds(788, 103, 98, 42);
		}
		return btnQuit;
	}

	

	
	
}
