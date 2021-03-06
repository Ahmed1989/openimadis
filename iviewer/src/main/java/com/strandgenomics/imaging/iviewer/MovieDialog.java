/**
 * openImaDis - Open Image Discovery: Image Life Cycle Management Software
 * Copyright (C) 2011-2016  Strand Life Sciences
 *   
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.strandgenomics.imaging.iviewer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;


public class MovieDialog extends JPanel {

	private JFrame parent;
	
    private JLabel lFile;
    private JTextField tfFile;
    private JButton fcButton;
    private String file;	

    

    private JLabel lFPS;
    private JTextField tfFPS;
    private String fps;

    private JLabel lNotes;
    private JTextArea tfNotes;
    private String notes;
    
    private JLabel lOption;
    private ButtonGroup bg1; 
    private String option;
    private JButton submitButton;
    private JButton cancelButton;
	private boolean valid;

    private void build() {
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            // Row 1 - Filename
                    // Col 1
            this.lFile = new JLabel("File :");
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(5, 0, 0, 10);
            gbc.anchor = GridBagConstraints.LINE_END;
            this.add(this.lFile, gbc);

                    // Col 2
            this.tfFile = new JTextField(20);
            this.tfFile.setEditable(false);
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.LINE_START;
            this.add(this.tfFile, gbc);

            
            
            
            this.fcButton = new JButton("Browse");
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.LINE_START;
            this.fcButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fc = new JFileChooser();
                    fc.setFileFilter(new FileNameExtensionFilter(".mp4", "mp4"));
                    int returnVal = fc.showOpenDialog(null);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        MovieDialog.this.tfFile.setText(fc.getSelectedFile().getAbsolutePath());
                }

            } }) ;
            this.add(this.fcButton, gbc);

            

//            this.lFileName = new JLabel("File Name:");
//            gbc.gridx = 0;
//            gbc.gridy = 2;
//            gbc.anchor = GridBagConstraints.LINE_END;
//            gbc.insets = new Insets(15, 0, 0, 10);
//            this.add(this.lFileName, gbc);

//            this.tfFileName = new JTextField(15);
//            gbc.gridx = 1;
//            gbc.gridy = 2;
//            gbc.anchor = GridBagConstraints.LINE_START;
//            this.add(this.tfFileName, gbc);

            this.lOption = new JLabel("FPS Option:");
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.insets = new Insets(5, 0, 0, 10);
            gbc.anchor = GridBagConstraints.LINE_END;
            this.add(this.lOption, gbc);
            
            this.bg1 = new ButtonGroup();
            JRadioButton butn1 = new JRadioButton("Use given Frame Rate");
            JRadioButton butn2 = new JRadioButton("Use Elapsed Time ");
            //JRadioButton thin3 = new JRadioButton("Thin3");
            //JRadioButton thin4 = new JRadioButton("Thin4");
            bg1.add(butn1);
            bg1.add(butn2);
            bg1.setSelected(butn1.getModel(), true);
            butn1.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                	tfFPS.setEnabled(true);
                } }) ;
            butn2.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                	tfFPS.setEnabled(false);
                } }) ;
            //bg1.add(thin3);
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.LINE_START;
            this.add(butn1, gbc);
            gbc.gridx = 2;
            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.LINE_START;
            this.add(butn2, gbc);
            
            
            
            
            this.lFPS = new JLabel("FPS: ");
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.LINE_END;
            gbc.insets = new Insets(5, 0, 0, 10);
            this.add(lFPS, gbc);

                    // Col 2
            this.tfFPS = new JTextField("10",15);
            gbc.gridx = 1;
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.LINE_START;
            this.add(tfFPS, gbc);

            this.lNotes = new JLabel("Notes : ");
            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.anchor = GridBagConstraints.LINE_END;
            gbc.insets = new Insets(5, 0, 0, 10);
            this.add(lNotes, gbc);

                    // Col 2
            this.tfNotes = new JTextArea(5,20);
            tfNotes.setEditable(true);
            tfNotes.setLineWrap(true);
            tfNotes.setRows(3);
            gbc.gridx = 1;
            gbc.gridy = 5;
            gbc.anchor = GridBagConstraints.LINE_START;
            this.add(tfNotes, gbc);
          
            this.submitButton = new JButton("Submit");
            gbc.gridx = 1;
            gbc.gridy = 9;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.insets = new Insets(25, 0, 0, 10);
            

            //this.submitButton.addActionListener(new GridBagController(this));
            this.submitButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                	file = tfFile.getText();
                	//fileName = tfFileName.getText();
                	fps = tfFPS.getText();
                	notes = tfNotes.getText();
                	System.out.println("fps: " + fps );
                	System.out.println("directory: " + file );
                	option = getSelectedButtonText(bg1);
                	if(option!= null && option.length() > 0  && file.length() > 0   ){
                		if(getOption() == 1 && fps.length() == 0)
                			valid = false;
                		else
                		{
                			valid = true;
                			parent.setVisible(false);
                        	
                		}
                		
                	}
                	
                } }) ;
            this.add(this.submitButton, gbc);
            
            this.cancelButton = new JButton("Cancel");
            gbc.gridx = 2;
            gbc.gridy = 9;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.insets = new Insets(25, 0, 0, 10);
            this.cancelButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                	parent.setVisible(false);
                } }) ;
            this.add(this.cancelButton, gbc);
    }


    // CONSTRUCTOR
    public MovieDialog (JFrame f) {
    	this.parent = f;
        this.build();
        this.valid = false;
        setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    private String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
    // GETTERS AND SETTERS

    public String getFile() {
        return file;
    }



    
    


    public float getFPS() {
        return Float.valueOf(fps);
    }
    public void setFPS(String fps) {
        this.fps = fps;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public boolean getValidation() {
        return valid;
    }
    public int  getOption(){
    	if(option.contains("Frame"))
    		return 1;
    	else if(option.contains("Elapsed"))
    		return 2;
    	return 0;
    }
    

}