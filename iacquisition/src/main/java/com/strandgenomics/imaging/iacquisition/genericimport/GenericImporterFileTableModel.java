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

package com.strandgenomics.imaging.iacquisition.genericimport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class GenericImporterFileTableModel extends AbstractTableModel{

	private List<String> fileList;
	public GenericImporterFileTableModel()
	{
		fileList = new ArrayList<String>();
	}
	
	public void setData(List<String> fileList)
	{
		Collections.sort(fileList);
		
		this.fileList = fileList;
		fireTableDataChanged();
	}
	
	@Override
	public int getRowCount()
	{
		return fileList.size();
	}
	
	@Override
	public String getColumnName(int columnIndex)
	{
		return "Source File";
	}

	@Override
	public int getColumnCount()
	{
		return 1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		return fileList.get(rowIndex);
	}

}
