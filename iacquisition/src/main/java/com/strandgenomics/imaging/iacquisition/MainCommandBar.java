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

import java.awt.event.ActionListener;

import com.jidesoft.action.CommandMenuBar;
import com.jidesoft.swing.JideButton;
import com.strandgenomics.imaging.iviewer.UIUtils;

public class MainCommandBar extends CommandMenuBar {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3722042742867359089L;
	private Context context;

	public MainCommandBar(Context context, ActionListener listener) {
		this.context = context;
		setOpaque(true);
		setVisible(true);
		createGUI(listener);
	}
	
	private void createGUI(ActionListener listener) {

		JideButton indexButton = UIUtils.createCommandBarButton("folder.png",
				"Import File or Folder");
		indexButton.setActionCommand("index");
		indexButton.addActionListener(listener);	
		add(indexButton);
		
		JideButton stopButton = UIUtils.createCommandBarButton("StopNormal.png","Stop Import");
		stopButton.setActionCommand("stopIndexing");
		stopButton.addActionListener(listener);	
		add(stopButton);
		
		JideButton mergeButton = UIUtils.createCommandBarButton("expand.png","Merge Records");
		mergeButton.setActionCommand("mergeRecords");
		mergeButton.addActionListener(listener);	
		add(mergeButton);
		
		
		JideButton uploadButton = UIUtils.createCommandBarButton("upload.gif","Upload Records");
		uploadButton.setActionCommand("upload");
		uploadButton.addActionListener(listener);	
		add(uploadButton);
		
		JideButton deleteButton = UIUtils.createCommandBarButton("delete.png","Delete Records");
		deleteButton.setActionCommand("delete");
		deleteButton.addActionListener(listener);	
		add(deleteButton);
		
		JideButton addPropertyButton = UIUtils.createCommandBarButton("expandall.png","Add Property");
		addPropertyButton.setActionCommand("addProperty");
		addPropertyButton.addActionListener(listener);
		add(addPropertyButton);
		
		JideButton removePropertyButton = UIUtils.createCommandBarButton("collapseall.png","Remove Property");
		removePropertyButton.setActionCommand("removeProperty");
		removePropertyButton.addActionListener(listener);
		add(removePropertyButton);		
		
		JideButton changeProjectButton = UIUtils.createCommandBarButton("table_refresh.png","Change Project");
		changeProjectButton.setActionCommand("changeProject");
		changeProjectButton.addActionListener(listener);
		add(changeProjectButton);
		
		JideButton thumbnailButton = UIUtils.createCommandBarButton("addimage.png","Set Thumbnail");
		thumbnailButton.setActionCommand("setThumbnail");
		thumbnailButton.addActionListener(listener);
		add(thumbnailButton);
		
		JideButton exportButton = UIUtils.createCommandBarButton("save.png","Export Current Image");
		exportButton.setActionCommand("exportImage");
		exportButton.addActionListener(listener);
		add(exportButton);
		
//		JideButton scriptEditorButton = UIUtils.createCommandBarButton("script.png","Open Script Editor");
//		scriptEditorButton.setActionCommand("openEditor");
//		scriptEditorButton.addActionListener(listener);
//		add(scriptEditorButton);
		
	}
}
