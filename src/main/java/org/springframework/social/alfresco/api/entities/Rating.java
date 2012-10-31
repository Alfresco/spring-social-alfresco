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


import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;


/**
 * 
 * @author jottley
 * @author sglover
 * 
 */

/**
 * @author jottley
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class Rating
{
    private String             id;
    private Aggregate          aggregate;
    private Date               ratedAt;
    private Object             myRating;

    /**
     * Rating Type: Five Stars
     */
    public final static String STARS = "fiveStar";
    /**
     * Rating Type: Likes
     */
    public final static String LIKES = "likes";


    /**
     * @return The rating scheme id. Currently there are two schemes defined, likes and fiveStar. Only the likes scheme is used in
     * Alfresco Cloud.
     */
    public String getId()
    {
        return id;
    }


    /**
     * Set the rating scheme id. Currently there are two schemes defined, likes and fiveStar. Only the likes scheme is used in
     * Alfresco Cloud.
     * 
     * @param id
     */
    public void setId(String id)
    {
        this.id = id;
    }


    /**
     * @return Aggregate object with properties specific to the rating scheme
     */
    public Aggregate getAggregate()
    {
        return aggregate;
    }


    /**
     * Set Aggregate object with properties specific to the rating scheme
     * 
     * @param aggregate
     */
    public void setAggregate(Aggregate aggregate)
    {
        this.aggregate = aggregate;
    }


    /**
     * @return The date time the current authenticated user rated the item of content.
     */
    public Date getRatedAt()
    {
        return ratedAt;
    }


    /**
     * Set the date time the current authenticated user rated the item of content.
     * 
     * @param ratedAt
     */
    public void setRatedAt(Date ratedAt)
    {
        this.ratedAt = ratedAt;
    }


    /**
     * Set the value of the rating. For the likes scheme, values are true or true. For the fiveStar scheme, the value is an integer
     * between one and five inclusively.
     * 
     * @param myRating
     */
    public void setMyRating(Object myRating)
    {
        this.myRating = myRating;
    }


    /**
     * @return The value of the rating. For the likes scheme, values are true or true. For the fiveStar scheme, the value is an
     * integer between one and five inclusively.
     */
    public Object getMyRating()
    {
        return myRating;
    }


    /**
     * An object with properties specific to the rating scheme. For likes this will contain a single property numberOfRatings . For
     * fiveStar this will contain numberOfRatings and average .
     * 
     * @author jottley
     * 
     */
    public static class Aggregate
    {
        private long numberOfRatings;


        /**
         * @return The number of ratings received
         */
        public long getNumberOfRatings()
        {
            return numberOfRatings;
        }


        /**
         * Set the number of ratings received
         * 
         * @param numberOfRatings
         */
        public void setNumberOfRatings(long numberOfRatings)
        {
            this.numberOfRatings = numberOfRatings;
        }


    }

}
