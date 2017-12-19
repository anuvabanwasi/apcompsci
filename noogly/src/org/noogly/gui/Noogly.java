
/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

package org.noogly.gui;

/*
*
* NooglyLayout.java
*
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.noogly.model.Expression;
import org.noogly.query.BooleanSearchEngine;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;

/**
 * Build the JFrame representing the layout for the boolean search engine
 * @author anuva banwasi
 *
 */
public class Noogly extends JFrame{
    FlowLayout nooglyLayout = new FlowLayout();
    
    public Noogly(String name) {
        super(name);
    }
    
    public void addComponentsToPane(final Container pane) {
        final JPanel nooglyPanel = new JPanel();
        nooglyPanel.setLayout(nooglyLayout);
        nooglyLayout.setAlignment(FlowLayout.CENTER);
        JPanel controls = new JPanel();
        controls.setLayout(new FlowLayout());
       
        // Header icon
        ImageIcon icon = createImageIcon("icon1.png", "Noogly");
        JLabel header = new JLabel(icon);
        header.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        
        // Header title
        JLabel label = new JLabel("Noogly");
        label.setPreferredSize(new Dimension(750,50));
        label.setHorizontalAlignment(SwingConstants.CENTER);
       
        // Results text area
        final JTextArea resultsTextArea = new JTextArea(10, 70);
        resultsTextArea.setPreferredSize(new Dimension(240,100));
        resultsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsTextArea);
        
        // Search text field
        final JTextField searchField = new JTextField(70);
        searchField.setPreferredSize(new Dimension(70, 30));
        searchField.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				process(resultsTextArea, searchField);
			}
        	
        });
        
        // Add header, label and search text field
        nooglyPanel.add(header);
        nooglyPanel.add(label);
        nooglyPanel.add(searchField);
        
        // Search Button
        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(120, 30));
        nooglyPanel.add(searchButton);
        
        searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				process(resultsTextArea, searchField);
			}
        	
        });
        
        // Reset Button
        JButton resetButton = new JButton("Reset");
        resetButton.setPreferredSize(new Dimension(120, 30));
        nooglyPanel.add(resetButton);
        
        resetButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				searchField.setText("");
			}
        	
        });
        
        nooglyPanel.add(scrollPane);
        
        // Document 1
        JLabel docLabel1 = new JLabel("Document - 1.txt");
        docLabel1.setPreferredSize(new Dimension(250,15));
        JTextArea docTextArea1 = new JTextArea(15, 25);
        docTextArea1.setEditable(false);
        JScrollPane scrollPaneTextArea1 = new JScrollPane(docTextArea1);
        docTextArea1.append("The quick brown fox jumps over the lazy dog");
        
        // Document 2
        JLabel docLabel2 = new JLabel("Document - 2.txt");
        docLabel2.setPreferredSize(new Dimension(275,15));
        JTextArea docTextArea2 = new JTextArea(15, 25);
        docTextArea2.setEditable(false);
        JScrollPane scrollPaneTextArea2 = new JScrollPane(docTextArea2);
        docTextArea2.append("She sells sea shells on the sea shore dog dog");
        
        // Document 3
        JLabel docLabel3 = new JLabel("Document - 3.txt");
        docLabel3.setPreferredSize(new Dimension(250,15));
        JTextArea docTextArea3 = new JTextArea(15, 25);
        docTextArea3.setEditable(false);
        JScrollPane scrollPaneTextArea3 = new JScrollPane(docTextArea3);
        docTextArea3.append("Betty bought some butter but the butter was\nbitter so Betty bought some more better butter");
        
        nooglyPanel.add(docLabel1);
        nooglyPanel.add(docLabel2);
        nooglyPanel.add(docLabel3);
        nooglyPanel.add(scrollPaneTextArea1);
        nooglyPanel.add(scrollPaneTextArea2);
        nooglyPanel.add(scrollPaneTextArea3);
        
        //Left to right component orientation is selected by default
        nooglyPanel.setComponentOrientation(
                ComponentOrientation.LEFT_TO_RIGHT);
       
        pane.add(nooglyPanel, BorderLayout.CENTER);
        pane.add(controls, BorderLayout.SOUTH); ;
    }
    
    /**
     * Process user click on search button
     * @param resultsTextArea text area to show results of search
     * @param searchField text field containing search query
     */
    private void process(final JTextArea resultsTextArea, final JTextField searchField) {
		BooleanSearchEngine bse = new BooleanSearchEngine();
		
		resultsTextArea.setText("");
		
		// Get the search query
		String searchQuery = searchField.getText();
		
		// replace left parenthesis in query with leftP
		// replace right parenthesis in query with rightP
		searchQuery = searchQuery.replaceAll("\\)", " rightP ");  
		searchQuery = searchQuery.replaceAll("\\(", " leftP ");  
		
		// Split on space and create an expression
		String[] tokens = searchQuery.split(" ");
		
		Expression expression = new Expression();
		expression.setTokens(Arrays.asList(tokens));
		
		// Evaluate the expression to get the list of document ids satisfying boolean expression
		List<String> results = bse.search(expression);	
		for(String result:results) {
			resultsTextArea.append(result);
			resultsTextArea.append("\n");
		}
	}
    
    /**
     * Display the header image
     * @param path Path to the image icon 
     * @param description Alternate description
     * @return
     */
	private static ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = Noogly.class.getClassLoader().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        Noogly frame = new Noogly("Noogly Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(850,700));
        frame.setResizable(false);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        //frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        //Schedule a job for the event dispatchi thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}