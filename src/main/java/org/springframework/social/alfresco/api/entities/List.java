
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


    public static class Pagination
    {
        private long    count;
        private boolean hasMoreItems;
        private long    totalItems;
        private long    skipCount;
        private long    maxItems;


        public long getCount()
        {
            return count;
        }


        public void setCount(int count)
        {
            this.count = count;
        }


        public boolean isHasMoreItems()
        {
            return hasMoreItems;
        }


        public void setHasMoreItems(boolean hasMoreItems)
        {
            this.hasMoreItems = hasMoreItems;
        }


        public long getTotalItems()
        {
            return totalItems;
        }


        public void setTotalItems(long totalItems)
        {
            this.totalItems = totalItems;
        }


        public long getSkipCount()
        {
            return skipCount;
        }


        public void setSkipCount(long skipCount)
        {
            this.skipCount = skipCount;
        }


        public long getMaxItems()
        {
            return maxItems;
        }


        public void setMaxItems(long maxItems)
        {
            this.maxItems = maxItems;
        }
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
