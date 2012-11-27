/*
 * Copyright 2012 Alfresco Software Limited.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * This file is part of an unsupported extension to Alfresco.
 */
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
    
    @Override
	public String toString()
    {
		return "Pagination [count=" + count + ", hasMoreItems=" + hasMoreItems
				+ ", totalItems=" + totalItems + ", skipCount=" + skipCount
				+ ", maxItems=" + maxItems + "]";
	}
}
