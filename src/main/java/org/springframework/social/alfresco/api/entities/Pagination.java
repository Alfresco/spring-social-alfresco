
package org.springframework.social.alfresco.api.entities;


public class Pagination
{
    private long    count;
    private boolean hasMoreItems;
    private long    totalItems;
    private long    skipCount;
    private long    maxItems;
    
    /* Query Parameters */
    public final static String SKIPCOUNT    = "skipCount";
    public final static String MAXITEMS     = "maxItems";
    public final static String COUNT        = "count";
    public final static String HASMOREITEMS = "hasMoreItems";
    public final static String TOTALITEMS   = "totalItems";


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
