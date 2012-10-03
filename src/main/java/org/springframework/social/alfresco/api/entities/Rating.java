
package org.springframework.social.alfresco.api.entities;


import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class Rating
{
    private String    id;
    private Aggregate aggregate;
    private Date      ratedAt;
    private String   myRating;


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


    public String isMyRating()
    {
        return myRating;
    }


    public void setMyRating(String myRating)
    {
        this.myRating = myRating;
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
