
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.util.List;
import realtimeweb.simplebusiness.domain.*;
import realtimeweb.simplebusiness.*;

/**
 * Program 8
 * 
 * The program will simulating YELP application
 * by using data from online API
 * 
 * CS108-02
 * 12.07.17
 * 
 * @author Quang Trinh
 */
@SuppressWarnings("serial")
public class YelpByRating extends JFrame implements ActionListener {
	
	private JTextArea outputArea;                  // Displays search results
	private JButton searchButton;                  // Triggers search button
	private JButton sortButton;					  // Triggers sort button
	private JTextField cityField;  		  	      // Holds city
	private JTextField kindField; 		          // Holds kind
    
	/**
	 * Constructor creates GUI components and add
	 * GUI components using a GridBagLayout.
	 */
	YelpByRating() {
		GridBagConstraints layoutConst = null; // Used to specify GUI component layout
	    JScrollPane scrollPane = null;         // Container that adds a scroll bar
	    JLabel cityLabel = null;               // Label for city
	    JLabel kindLabel = null;               // Label for kind
	    JLabel outputLabel = null;             // Label for search results

	    // Set frame's title
	    setTitle("Yelp simulator by Quang Trinh");
	    
	    // Create labels
	    cityLabel = new JLabel("Location:");
	    kindLabel = new JLabel("Term:");
	    outputLabel = new JLabel("Results:");
	    
	    // Create output and add it to scroll pane
	    outputArea = new JTextArea(30,25);
	    scrollPane = new JScrollPane(outputArea);
	    outputArea.setEditable(false);
	    
	    // Create search button
	    searchButton = new JButton("Search");
	    searchButton.addActionListener(this);
	    
	    // Create sort button
	    sortButton = new JButton("Sort");
	    sortButton.addActionListener(this);
	    
	    // Create city field
	    cityField = new JTextField(10);
	    cityField.setEditable(true);
	    cityField.setText("City, State");
	    
	    // Create kind field
	    kindField = new JTextField(10);
	    kindField.setEditable(true);
	    kindField.setText("Looking for...");
	    
	    // Use a GridBagLayout
	    setLayout(new GridBagLayout());
	    
	    // Cell(0,0)
	    layoutConst = new GridBagConstraints();
	    layoutConst.insets = new Insets(5, 10, 5, 1);
	    layoutConst.anchor = GridBagConstraints.LINE_END;
	    layoutConst.gridx = 0;
	    layoutConst.gridy = 0;
	    add(cityLabel, layoutConst);
	    
	    // Cell(1,0)
	    layoutConst = new GridBagConstraints();
	    layoutConst.insets = new Insets(10, 1, 5, 10);
	    layoutConst.fill = GridBagConstraints.HORIZONTAL;
	    layoutConst.gridx = 1;
	    layoutConst.gridy = 0;
	    add(cityField, layoutConst);
	    
	    // Cell(0,1)
	    layoutConst = new GridBagConstraints();
	    layoutConst.insets = new Insets(5, 10, 5, 1);
	    layoutConst.anchor = GridBagConstraints.LINE_END;
	    layoutConst.gridx = 0;
	    layoutConst.gridy = 1;
	    add(kindLabel, layoutConst);
	    
	    // Cell(1,1)
	    layoutConst = new GridBagConstraints();
	    layoutConst.insets = new Insets(5, 1, 5, 10);
	    layoutConst.fill = GridBagConstraints.HORIZONTAL;
	    layoutConst.gridx = 1;
	    layoutConst.gridy = 1;
	    add(kindField, layoutConst);
	    
	    // Cell(2,0)
	    layoutConst = new GridBagConstraints();
	    layoutConst.insets = new Insets(5, 5, 5, 10);
	    layoutConst.fill = GridBagConstraints.BOTH;
	    layoutConst.gridx = 2;
	    layoutConst.gridy = 0;
	    add(searchButton, layoutConst);
	    
	    // Cell(2,1)
	    layoutConst = new GridBagConstraints();
	    layoutConst.insets = new Insets(2, 5, 2, 10);
	    layoutConst.fill = GridBagConstraints.BOTH;
	    layoutConst.gridx = 2;
	    layoutConst.gridy = 1;
	    add(sortButton, layoutConst);
	    
	    // Cell(0,3)
	    layoutConst = new GridBagConstraints();
	    layoutConst.insets = new Insets(10, 10, 1, 10);
	    layoutConst.fill = GridBagConstraints.HORIZONTAL;
	    layoutConst.gridx = 0;
	    layoutConst.gridy = 3;
	    add(outputLabel, layoutConst);
	    
	    // Cell(0,4)
	    layoutConst = new GridBagConstraints();
	    layoutConst.insets = new Insets(1, 10, 10, 10);
	    layoutConst.fill = GridBagConstraints.HORIZONTAL;
	    layoutConst.gridx = 0;
	    layoutConst.gridy = 4;
	    layoutConst.gridwidth = 3;
	    add(scrollPane, layoutConst);    
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String cityInput = "";    // User city input
		String kindInput = "";	 // User kind input
		
		JButton sourceEvent = (JButton)event.getSource();
		
		if (sourceEvent == searchButton) {
			// Get input from fields
			cityInput = cityField.getText();
			kindInput = kindField.getText();
		
			// Clear the output text area
			outputArea.setText("");
		
			// Get data from Online API
			SimpleBusiness yelp = new SimpleBusiness();  // RealTime Data
		
			List<Business> businesses = yelp.search(kindInput, cityInput);
		
			for (Business b : businesses) {
				System.out.println(b.getName() + " " + b.getRating());
				outputArea.append(b.getName() + "  " + b.getRating() + "\n");
				outputArea.append("-----------------------------------------\n");
			}
		}
		else if (sourceEvent == sortButton) {
			// Get input from fields
			cityInput = cityField.getText();
			kindInput = kindField.getText();
					
			// Clear the output text area
			outputArea.setText("");
					
			// Get data from Online API
			SimpleBusiness yelp = new SimpleBusiness();  // RealTime Data
					
			List<Business> businesses = yelp.search(kindInput, cityInput);
			
			// Sort the list
			selectionSort(businesses, businesses.size() - 1);
					
			for (Business b : businesses) {
				System.out.println(b.getName() + " " + b.getRating());
				outputArea.append(b.getName() + "  " + b.getRating() + "\n");
				outputArea.append("-----------------------------------------\n");
			}
		}
		
		return;
	}
	
	/**
	 * Sorting Business Object Rating descending by selection sort algorithm
	 * @param businesses
	 * @param size
	 */
	private static void selectionSort(List<Business> businesses, int size) {
		int i = 0;
		int j = 0;
		int indexLargest = 0;
		Business temp = null;
		
		for (i = 0; i < size; ++i) {
			indexLargest = i;
			for (j = i + 1; j < size; ++j) {
				if(businesses.get(j).getRating() > businesses.get(indexLargest).getRating()) {
					indexLargest = j;
				}
			}
			// Swap 2 objects
			temp = businesses.get(i);
			businesses.set(i, businesses.get(indexLargest));
			businesses.set(indexLargest, temp);
		}
	}
	
	/**
	 * Return author's identification
	 * @return
	 */
	public static String getIdentificationString() {
		return ("Quang Trinh - Program 8");
	}

	public static void main(String[] args) {
		
		YelpByRating myFrame = new YelpByRating();
		
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.pack();
		myFrame.setVisible(true);
		
		return;
	}

}
