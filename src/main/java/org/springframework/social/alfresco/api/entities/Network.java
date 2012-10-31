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


import java.util.ArrayList;
import java.util.Date;


/**
 * A network is the group of users and sites that belong to an organization. Networks are organized by email domain. When a user
 * signs up for an Alfresco account , their email domain becomes their Home Network.
 * 
 * @author jottley
 * 
 */
public class Network
{
    private String           id;
    private Date             createdAt;
    private boolean          homeNetwork;
    private boolean          isEnabled;
    private ArrayList<Quota> quotas;
    private boolean          paidNetwork;
    private String           subscriptionLevel;


    /**
     * Set this network's unique id
     * 
     * @param id
     */
    public void setId(String id)
    {
        this.id = id;
    }


    /**
     * Is this network active?
     * 
     * @param enabled
     */
    public void setIsEnabled(boolean enabled)
    {
        this.isEnabled = enabled;
    }


    /**
     * Is this the users home network?
     * 
     * @param homeNetwork
     */
    public void setHomeNetwork(boolean homeNetwork)
    {
        this.homeNetwork = homeNetwork;
    }


    /**
     * Set the date time this network was created
     * 
     * @param createdAt
     */
    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }


    /**
     * Set Limits and usage of each quota. A network will have quotas for File space, the number of sites in the network, the number
     * of people in the network, and the number of network administrators.
     * 
     * @param quotas
     */
    public void setQuotas(ArrayList<Quota> quotas)
    {
        this.quotas = quotas;
    }


    /**
     * Is this a paid network?
     * 
     * @param paidNetwork
     */
    public void setPaidNetwork(boolean paidNetwork)
    {
        this.paidNetwork = paidNetwork;
    }


    /**
     * Set the type of subscription for this network. Possible values are Free, Standard, and Enterprise
     * 
     * @param subscriptionLevel
     */
    public void setSubscriptionLevel(String subscriptionLevel)
    {
        this.subscriptionLevel = subscriptionLevel;
    }


    /**
     * @return This network's unique id
     */
    public String getId()
    {
        return id;
    }


    /**
     * @return Is this the users home network
     */
    public boolean isHomeNetwork()
    {
        return homeNetwork;
    }


    /**
     * @return Is this network active?
     */
    public boolean isEnabled()
    {
        return isEnabled;
    }


    /**
     * @return The date time this network was created
     */
    public Date getCreatedAt()
    {
        return createdAt;
    }


    /**
     * @return Limits and usage of each quota. A network will have quotas for File space, the number of sites in the network, the
     * number of people in the network, and the number of network administrators.
     */
    public ArrayList<Quota> getQuotas()
    {
        return quotas;
    }


    /**
     * @return Is this a paid network?
     */
    public boolean isPaidNetwork()
    {
        return paidNetwork;
    }


    /**
     * @return The type of subscription for this network. Possible values are Free, Standard, and Enterprise
     */
    public String getSubscriptionLevel()
    {
        return subscriptionLevel;
    }


    /**
     * @author jottley
     * 
     */
    public static class Quota
    {
        public static enum QuotaId
        {
            fileUploadQuota, fileQuota, siteCountQuota, personCountQuota, personInternalOnlyCountQuota,
            personNetworkAdminCountQuota
        }


        private long   limit;
        private long   usage;
        private String id;


        /**
         * Set the limit for the quota. -1 is unlimited
         * 
         * @param limit
         */
        public void setLimit(long limit)
        {
            this.limit = limit;
        }


        /**
         * Set the current usage
         * 
         * @param usage
         */
        public void setUsage(long usage)
        {
            this.usage = usage;
        }


        /**
         * Set the quota id. Id will have a value of fileUploadQuota, fileQuota, siteCountQuota, personCountQuota,
         * personInternalOnlyCountQuota, personNetworkAdminCountQuota
         * 
         * @param id
         */
        public void setId(String id)
        {
            this.id = id;
        }


        /**
         * @return The limit for the quota, -1 for unlimited
         */
        public long getLimit()
        {
            return limit;
        }


        /**
         * @return The usage for this quota
         */
        public long getUsage()
        {
            return usage;
        }


        /**
         * @return The id of the quota. Will be one of fileUploadQuota, fileQuota, siteCountQuota, personCountQuota,
         * personInternalOnlyCountQuota, personNetworkAdminCountQuota
         */
        public String getId()
        {
            return id;
        }
    }
}
