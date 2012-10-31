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


/**
 * Object describing a collection of entries
 * 
 * @author jottley
 * 
 */
public class Pagination
{
    private long               count;
    private boolean            hasMoreItems;
    private long               totalItems;
    private long               skipCount;
    private long               maxItems;

    /* Query Parameters */
    /**
     * Pagination query parameter constant
     */
    public final static String SKIPCOUNT    = "skipCount";
    /**
     * Pagination query parameter constant
     */
    public final static String MAXITEMS     = "maxItems";
    /**
     * Pagination query parameter constant
     */
    public final static String COUNT        = "count";
    /**
     * Pagination query parameter constant
     */
    public final static String HASMOREITEMS = "hasMoreItems";
    /**
     * Pagination query parameter constant
     */
    public final static String TOTALITEMS   = "totalItems";


    /**
     * @return The number of objects in the entries array.
     */
    public long getCount()
    {
        return count;
    }


    /**
     * Set the number of objects in the entries array.
     * 
     * @param count
     */
    public void setCount(int count)
    {
        this.count = count;
    }


    /**
     * @return true if there are more entities in the collection beyond those in this response. A true value means request with a
     * larger value for the skipCount or the maxItems parameter will return more entities.
     */
    public boolean isHasMoreItems()
    {
        return hasMoreItems;
    }


    /**
     * Set true if there are more entities in the collection beyond those in this response. A true value means request with a larger
     * value for the skipCount or the maxItems parameter will return more entities.
     * 
     * @param hasMoreItems
     */
    public void setHasMoreItems(boolean hasMoreItems)
    {
        this.hasMoreItems = hasMoreItems;
    }


    /**
     * @return An integer describing the total number of entities in the collection. The API may not be able to determine this
     * value, in which case this property will not be present.
     */
    public long getTotalItems()
    {
        return totalItems;
    }


    /**
     * Set integer describing the total number of entities in the collection. The API may not be able to determine this value, in
     * which case this property will not be present.
     * 
     * @param totalItems
     */
    public void setTotalItems(long totalItems)
    {
        this.totalItems = totalItems;
    }


    /**
     * @return An integer describing how many entities exist in the collection before those included in this list.
     */
    public long getSkipCount()
    {
        return skipCount;
    }


    /**
     * Set integer describing how many entities exist in the collection before those included in this list.
     * 
     * @param skipCount
     */
    public void setSkipCount(long skipCount)
    {
        this.skipCount = skipCount;
    }


    /**
     * @return The maxItems parameter used to generate this list, or if there was no maxItems parameter the default value, 10.
     */
    public long getMaxItems()
    {
        return maxItems;
    }


    /**
     * Set the maxItems parameter used to generate this list, or if there was no maxItems parameter the default value, 10.
     * 
     * @param maxItems
     */
    public void setMaxItems(long maxItems)
    {
        this.maxItems = maxItems;
    }
}
