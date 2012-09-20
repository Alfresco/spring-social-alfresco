
package org.springframework.social.alfresco.api.entities;


import java.util.ArrayList;
import java.util.Date;


public class Network
{
    private String           id;
    private Date             createdAt;
    private boolean          homeNetwork;
    private boolean          isEnabled;
    private ArrayList<Quota> quotas;
    private boolean          paidNetwork;
    private String           subscriptionLevel;
    private Network          network;


    public void setId(String id)
    {
        this.id = id;
    }


    public void setIsEnabled(boolean enabled)
    {
        this.isEnabled = enabled;
    }


    public void setHomeNetwork(boolean homeNetwork)
    {
        this.homeNetwork = homeNetwork;
    }


    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }


    public void setQuotas(ArrayList<Quota> quotas)
    {
        this.quotas = quotas;
    }


    public void setPaidNetwork(boolean paidNetwork)
    {
        this.paidNetwork = paidNetwork;
    }


    public void setSubscriptionLevel(String subscriptionLevel)
    {
        this.subscriptionLevel = subscriptionLevel;
    }


    public String getId()
    {
        return id;
    }


    public boolean isHomeNetwork()
    {
        return homeNetwork;
    }


    public boolean isEnabled()
    {
        return isEnabled;
    }


    public Date getCreatedAt()
    {
        return createdAt;
    }


    public ArrayList<Quota> getQuotas()
    {
        return quotas;
    }


    public boolean isPaidNetwork()
    {
        return paidNetwork;
    }


    public String getSubscriptionLevel()
    {
        return subscriptionLevel;
    }


    public Network getNetwork()
    {
        return network;
    }


    public void setNetwork(Network network)
    {
        this.network = network;
    }


    public static class Quota
    {
        private long   limit;
        private long   usage;
        private String id;


        public void setLimit(long limit)
        {
            this.limit = limit;
        }


        public void setUsage(long usage)
        {
            this.usage = usage;
        }


        public void setId(String id)
        {
            this.id = id;
        }


        public long getLimit()
        {
            return limit;
        }


        public long getUsage()
        {
            return usage;
        }


        public String getId()
        {
            return id;
        }
    }
}
