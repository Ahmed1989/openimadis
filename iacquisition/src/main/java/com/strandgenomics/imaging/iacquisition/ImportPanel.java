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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Displays status of currently going imports
 * 
 * @author Anup Kulkarni
 */
@SuppressWarnings("serial")
public class ImportPanel extends JPanel {
	/**
	 * table model that holds the records being imported
	 */
	ImportTableModel dataModel;

	public ImportPanel(ImportTableModel model) {
		setLayout(new BorderLayout());
		setName("importer");
		
		dataModel = model;

		createUI();
	}
	
	private void createUI() 
	{
		JScrollPane scrollPane = createScrollPane();
		add(scrollPane, BorderLayout.CENTER);
	}

	private JScrollPane createScrollPane() 
	{
		final JTable importPanel = new JTable();
		importPanel.setModel(dataModel);
		
		JButton cancelAll = new JButton("Stop All");
		cancelAll.setToolTipText("Stops All Imports");
		cancelAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataModel.cancelAll();
			}
		});
		
		JButton cancelSelected = new JButton("Cancel");
		cancelSelected.setToolTipText("Stop Import ");
		cancelSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataModel.cancelSelected(importPanel.getSelectedRows());
			}
		});
		
		JButton reindexSelected = new JButton("Import");
		reindexSelected.setToolTipText("Start Import");
		reindexSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataModel.reindexSelected(importPanel.getSelectedRows());
			}
		});
		
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.LINE_AXIS));
		btnPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		btnPanel.add(Box.createHorizontalGlue());
		btnPanel.add(cancelSelected);
		btnPanel.add(Box.createHorizontalStrut(5));
		btnPanel.add(cancelAll);
		btnPanel.add(Box.createHorizontalStrut(5));
		btnPanel.add(reindexSelected);
		
		add(btnPanel, BorderLayout.PAGE_END);
		
		JScrollPane scrollPane = new  JScrollPane(importPanel);
		return scrollPane;
	}
}
