/*
 * RowSet.java
 *
 * AVADIS Image Management System
 * Core Engine Database Module
 *
 * Copyright 2011-2012 by Strand Life Sciences
 * 5th Floor, Kirloskar Business Park, 
 * Bellary Road, Hebbal
 * Bangalore 560024
 * Karnataka, India
 * 
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Strand Life Sciences., ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Strand Life Sciences.
 */

package com.strandgenomics.imaging.icore.db;

import java.util.ArrayList;
import java.util.List;

/**
 * a RowSet instance represents the result of a sql select query
 * within a specified window
 * @author arunabha
 */
public class RowSet<T> {

    /**
     * window offset of the result
     */
    private int m_offset = 0;
    /**
     * total number of rows matching the query
     */
    private int m_totalHits = 0;
    /**
     * actual query result rows
     */
    private List<T> m_rowList = null;

    public RowSet()
    {
        this(0, 0, new ArrayList<T>());
    }

    public RowSet(int offset, int totalMatches, List<T> queryResult)
    {
        m_offset    = offset;
        m_totalHits = totalMatches;
        m_rowList   = queryResult;
    }

    public int getOffset()
    {
        return m_offset;
    }

    public int getTotalHits() 
    {
        return m_totalHits;
    }

    public List<T> getRows() 
    {
        return m_rowList;
    }

    public boolean isEmpty()
    {
        return (m_rowList == null || m_rowList.isEmpty());
    }

    public int size()
    {
        return (m_rowList == null ? 0 :  m_rowList.size());
    }

    public void destroy()
    {
        m_rowList = null;
    }

    public void add(T row)
    {
        if(row != null){
            m_rowList.add(row);
        }
    }
}
