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
