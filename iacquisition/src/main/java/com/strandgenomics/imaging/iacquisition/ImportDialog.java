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

package com.strandgenomics.imaging.iacquisition;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

/**
 * Generic dialog box for importing using differnt import strategies
 * 
 * @author Anup Kulkarni
 * @author Navneet Joshi
 */
public class ImportDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5472446863303502413L;
	/**
	 * selected files
	 */
	private File[] selectedFiles = null;
	/**
	 * state of the checkbox
	 */
	private boolean buttonSelected = false;
	/**
	 * return value of the file chooser dialog box
	 */
	private int ret;

	public ImportDialog(JFrame parent, String title, String currentDirectory, FileSystemView fsv, final ImportDialogType type)
	{
		super(parent, title, true);

		final JDialog dialog = this;

		final JFileChooser fileChooser = new JFileChooser()
		{
			private static final long serialVersionUID = -2033644054067976810L;

			@Override
			public void approveSelection()
			{
				ret = JFileChooser.APPROVE_OPTION;
				boolean selection = true;
				if (type.equals(ImportDialogType.DIRECT_UPLOAD_IMPORT_DIALOG))
				{
					
					if(this.getSelectedFile().exists()){
						int returnVal = JOptionPane.showConfirmDialog(null, "Do you want to import the selected file/directory ?", "Browse or Import", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (returnVal == JOptionPane.YES_OPTION)
						{
							File[] temp = new File[1];
							temp[0] = this.getSelectedFile();
							selectedFiles = temp;

							super.approveSelection();

							dialog.dispose();
						}
						else
							super.setCurrentDirectory(this.getSelectedFile());
					}
					else
						JOptionPane.showMessageDialog(null, "Specified Directory does not exist", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if (this.getSelectedFiles().length == 1 && !this.getSelectedFile().exists())
					JOptionPane.showMessageDialog(null, "Specified Directory/File does not exist", "Error", JOptionPane.ERROR_MESSAGE);
				else
				{
					selectedFiles = this.getSelectedFiles();
					super.approveSelection();
				}
				System.setProperty("imanage.user.dir", getCurrentDirectory().getAbsolutePath());
				if(selection)
					dialog.dispose();
			}

			@Override
			public void cancelSelection()
			{
				ret = JFileChooser.CANCEL_OPTION;
				super.cancelSelection();
				System.setProperty("imanage.user.dir", getCurrentDirectory().getAbsolutePath());
				dialog.dispose();
			}
		};
		
		
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		if(type.equals(ImportDialogType.NORMAL_IMPORT_DIALOG))
			fileChooser.setMultiSelectionEnabled(true);
		
		getContentPane().add(fileChooser);
		
		JPanel checkBoxPane = new JPanel();
		final JCheckBox button = new JCheckBox(type.getCheckboxString());
		button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				buttonSelected = button.isSelected();
			}
		});

		checkBoxPane.add(button);
		getContentPane().add(checkBoxPane, BorderLayout.SOUTH);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setLocationRelativeTo(parent);
		setVisible(true);
	}

	/**
	 * returns the selected files on approve, null otherwise
	 * 
	 * @return the selected files on approve, null otherwise
	 */
	public File[] getImportFiles()
	{
		if (ret == JFileChooser.APPROVE_OPTION)
		{
			return selectedFiles;
		}
		else
			return null;
	}

	/**
	 * returns the state of the checkbox
	 * 
	 * @return the state of the checkbox
	 */
	public boolean isCheckBoxSelected()
	{
		return buttonSelected;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		setVisible(false);
		dispose();
	}
}
