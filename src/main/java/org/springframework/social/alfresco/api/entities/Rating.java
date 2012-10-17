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

@JsonSerialize(include = Inclusion.NON_NULL)
public class Rating
{
    private String    id;
    private Aggregate aggregate;
    private Date      ratedAt;
    private Object    myRating;
    
    public final static String STARS = "fiveStar";
    public final static String LIKES = "likes";


    public String getId()
    {
        return id;
    }


    public void setId(String id)
    {
        this.id = id;
    }


    public Aggregate getAggregate()
    {
        return aggregate;
    }


    public void setAggregate(Aggregate aggregate)
    {
        this.aggregate = aggregate;
    }


    public Date getRatedAt()
    {
        return ratedAt;
    }


    public void setRatedAt(Date ratedAt)
    {
        this.ratedAt = ratedAt;
    }


    public void setMyRating(Object myRating)
    {
        this.myRating = myRating;
    }


    public Object getMyRating()
    {
        return myRating;
    }


    public static class Aggregate
    {
        private long numberOfRatings;


        public long getNumberOfRatings()
        {
            return numberOfRatings;
        }


        public void setNumberOfRatings(long numberOfRatings)
        {
            this.numberOfRatings = numberOfRatings;
        }


    }

}
