
package org.springframework.social.alfresco.api.entities;


import java.util.ArrayList;


public class List<T>
{


    private Pagination          pagination;
    private ArrayList<Entry<T>> entries;


    public void setPagination(Pagination pagination)
    {
        this.pagination = pagination;
    }


    public Pagination getPagination()
    {
        return pagination;
    }


    public void setEntries(ArrayList<Entry<T>> entries)
    {
        this.entries = entries;
    }


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


    private static class Entry<T>
    {
        private T entry;


        @SuppressWarnings("unused")
        public void setEntry(T entry)
        {
            this.entry = entry;
        }


        public T getEntry()
        {
            return entry;
        }
    }
}
