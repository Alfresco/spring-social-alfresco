/*
 * Copyright 2012 Alfresco Software Limited.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * 
 * This file is part of an unsupported extension to Alfresco.
 */

package org.springframework.social.alfresco.api.entities;


import java.util.ArrayList;


/**
 * Has pagination information for a list of entities <T> available in this list
 * 
 * @author jottley
 * 
 * @param <T> - Entity Type
 */
public class AlfrescoList<T>
{


    private Pagination          pagination;
    private ArrayList<Entry<T>> entries;


    /**
     * @param pagination
     */
    public void setPagination(Pagination pagination)
    {
        this.pagination = pagination;
    }


    /**
     * @return
     */
    public Pagination getPagination()
    {
        return pagination;
    }


    /**
     * @param entries
     */
    public void setEntries(ArrayList<Entry<T>> entries)
    {
        this.entries = entries;
    }


    /**
     * @return
     */
    public ArrayList<T> getEntries()
    {
        ArrayList<T> t = new ArrayList<T>();
        if (!entries.isEmpty())
        {
            for (Entry<T> entry : entries)
            {
                t.add(entry.getEntry());
            }
        }
        return t;
    }


    /**
     * List entry
     * 
     * @author jottley
     * 
     * @param <T>
     */
    public static class Entry<T>
    {
        private T entry;


        /**
         * @param entry
         */
        public void setEntry(T entry)
        {
            this.entry = entry;
        }


        /**
         * @return
         */
        public T getEntry()
        {
            return entry;
        }
    }


}
