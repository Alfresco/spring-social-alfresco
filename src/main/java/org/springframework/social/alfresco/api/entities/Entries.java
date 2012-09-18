
package org.springframework.social.alfresco.api.entities;


import java.util.ArrayList;

public class Entries<T>
{
    private List<T> list;


    public void setList(List<T> list)
    {
        this.list = list;
    }


    public List<T> getList()
    {
        return list;
    }


    public static class List<T>
    {

        private Pagination   pagination;
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


        public ArrayList<Entry<T>> getEntries()
        {
            return entries;
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

    }
}
