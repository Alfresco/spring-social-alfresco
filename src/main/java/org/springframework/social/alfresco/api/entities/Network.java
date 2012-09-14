
package org.springframework.social.alfresco.api.entities;


import java.util.ArrayList;
import java.util.Date;


public class Network
{
    private String           id;
    private String           type;
    private boolean          enabled;
    private Date             creationDate;
    private ArrayList<Quota> quotas;
    private String           accountClassName;
    private String           accountType;
    private String           accountClassDisplayName;


    Network(String id, String type, boolean enabled, Date creationDate, ArrayList<Quota> quotas, String accountClassName,
            String accountType, String accountClassDisplayName)
    {
        this.id = id;
        this.type = type;
        this.enabled = enabled;
        this.creationDate = creationDate;
        this.quotas = quotas;
        this.accountClassName = accountClassName;
        this.accountType = accountType;
        this.accountClassDisplayName = accountClassDisplayName;
    };


    public String getId()
    {
        return id;
    }


    public String getType()
    {
        return type;
    }


    public boolean isEnabled()
    {
        return enabled;
    }


    public Date getCreationDate()
    {
        return creationDate;
    }


    public ArrayList<Quota> getQuotas()
    {
        return quotas;
    }


    public String getAccountClassName()
    {
        return accountClassName;
    }


    public String getAccountType()
    {
        return accountType;
    }


    public String getAccountClassDisplayName()
    {
        return accountClassDisplayName;
    }


    public class Quota
    {
        private long   limit;
        private long   usage;
        private String id;


        public Quota(long limit, long usage, String id)
        {
            this.limit = limit;
            this.usage = usage;
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
