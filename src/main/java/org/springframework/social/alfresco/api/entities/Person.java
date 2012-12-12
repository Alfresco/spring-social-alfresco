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


/**
 * A person entity describes the user as they are known to Alfresco.
 * 
 * @author jottley
 * 
 */
public class Person
{
    private boolean enabled;
    private String  id;
    private String  firstName;
    private String  lastName;
    private String  location;
    private String  instantMessageId;
    private String  avatarId;
    private String  avatar;
    private String  googleId;
    private String  skypeId;
    private String  telephone;
    private String  mobile;
    private String  jobTitle;
    private String  email;
    private String  description;
    private Company company;
    private Date    statusUpdatedAt;
    private String  userStatus;
    private Date    createdAt;
    private boolean canEdit;
    private boolean emailNotificationsEnabled;


    /**
     * @return Is this person currently enabled?
     */
    public boolean isEnabled()
    {
        return enabled;
    }


    /**
     * Set is this person currently enabled?
     * 
     * @param enabled
     */
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }


    /**
     * @return The person's personId - the email address with which the person registered
     */
    public String getId()
    {
        return id;
    }


    /**
     * Set The person's personId - the email address with which the person registered
     * 
     * @param id
     */
    public void setId(String id)
    {
        this.id = id;
    }


    /**
     * @return The person's first name
     */
    public String getFirstName()
    {
        return firstName;
    }


    /**
     * Set the person's first name
     * 
     * @param firstName
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }


    /**
     * @return the person's last name
     */
    public String getLastName()
    {
        return lastName;
    }


    /**
     * set the person's last name
     * 
     * @param lastName
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }


    /**
     * @return The person's location or address
     */
    public String getLocation()
    {
        return location;
    }


    /**
     * Set the person's location or address
     * 
     * @param location
     */
    public void setLocation(String location)
    {
        this.location = location;
    }


    /**
     * @return The person's instant message Id
     */
    public String getInstantMessageId()
    {
        return instantMessageId;
    }


    /**
     * Set the person's instant message Id
     * 
     * @param instantMessageId
     */
    public void setInstantMessageId(String instantMessageId)
    {
        this.instantMessageId = instantMessageId;
    }


    /**
     * @return The id of the person's avatar
     */
    public String getAvatarId()
    {
        return avatarId;
    }


    /**
     * Set the id of the person's avatar
     * 
     * @param avatarId
     */
    public void setAvatarId(String avatarId)
    {
        this.avatarId = avatarId;
    }


    /**
     * @return
     */
    @Deprecated
    public String getAvatar()
    {
        return avatar;
    }


    /**
     * @param avatar
     */
    @Deprecated
    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }


    /**
     * @return The person's Google Id
     */
    public String getGoogleId()
    {
        return googleId;
    }


    /**
     * Set the person's Google Id
     * 
     * @param googleId
     */
    public void setGoogleId(String googleId)
    {
        this.googleId = googleId;
    }


    /**
     * @return The person's Skype Id
     */
    public String getSkypeId()
    {
        return skypeId;
    }


    /**
     * Set the person's Skype Id
     * 
     * @param skypeId
     */
    public void setSkypeId(String skypeId)
    {
        this.skypeId = skypeId;
    }


    /**
     * @return The person's telephone number
     */
    public String getTelephone()
    {
        return telephone;
    }


    /**
     * Set the person's telephone number
     * 
     * @param telephone
     */
    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }


    /**
     * @return The person's mobile number
     */
    public String getMobile()
    {
        return mobile;
    }


    /**
     * Set the person's mobile number
     * 
     * @param mobile
     */
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }


    /**
     * @return The person's job title
     */
    public String getJobTitle()
    {
        return jobTitle;
    }


    /**
     * Set the person's job title
     * 
     * @param jobTitle
     */
    public void setJobTitle(String jobTitle)
    {
        this.jobTitle = jobTitle;
    }


    /**
     * @return The person's email address
     */
    public String getEmail()
    {
        return email;
    }


    /**
     * Set the person's email address
     * 
     * @param email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }


    /**
     * @return The person's description
     */
    public String getDescription()
    {
        return description;
    }


    /**
     * Set the person's description
     * 
     * @param description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }


    /**
     * @return An embedded company object describing the person's company
     */
    public Company getCompany()
    {
        return company;
    }


    /**
     * Set company object describing the person's company
     * 
     * @param company
     */
    public void setCompany(Company company)
    {
        this.company = company;
    }


    /**
     * @return The date time of the person last status update
     */
    public Date getStatusUpdatedAt()
    {
        return statusUpdatedAt;
    }


    /**
     * Set the date time of the person last status update
     * 
     * @param statusUpdatedAt
     */
    public void setStatusUpdatedAt(Date statusUpdatedAt)
    {
        this.statusUpdatedAt = statusUpdatedAt;
    }


    /**
     * @return The persons's status
     */
    public String getUserStatus()
    {
        return userStatus;
    }


    /**
     * Set the person's status
     * 
     * @param userStatus
     */
    public void setUserStatus(String userStatus)
    {
        this.userStatus = userStatus;
    }


    /**
     * @return The Date time the person was created at
     */
    public Date getCreatedAt()
    {
        return createdAt;
    }


    /**
     * Set the date time the person was created at
     * 
     * @param createdAt
     */
    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }


    /**
     * @return Can the person object be edited
     */
    public boolean isCanEdit()
    {
        return canEdit;
    }


    /**
     * Set that the person object can be edited
     * 
     * @param canEdit
     */
    public void setCanEdit(boolean canEdit)
    {
        this.canEdit = canEdit;
    }


    /**
     * @return is person's email Notifications Enabled 
     */
    public boolean isEmailNotifcationsEnabled()
    {
        return emailNotificationsEnabled;
    }


    /**
     * Enable person's email notification
     * 
     * @param emailNotificationsEnabled
     */
    public void setEmailNotificationsEnabled(boolean emailNotificationsEnabled)
    {
        this.emailNotificationsEnabled = emailNotificationsEnabled;
    }


    /**
     * @author jottley
     * 
     */
    public static class Company
    {
        private String organization;
        private String address1;
        private String address2;
        private String address3;
        private String postcode;
        private String telephone;
        private String fax;
        private String email;


        /**
         * @return The company organization
         */
        public String getOrganization()
        {
            return organization;
        }


        /**
         * Set the company organization
         * 
         * @param organization
         */
        public void setOrganization(String organization)
        {
            this.organization = organization;
        }


        /**
         * @return Company Address1
         */
        public String getAddress1()
        {
            return address1;
        }


        /**
         * Set Company Address1
         * 
         * @param address1
         */
        public void setAddress1(String address1)
        {
            this.address1 = address1;
        }


        /**
         * @return Company Address2
         */
        public String getAddress2()
        {
            return address2;
        }


        /**
         * Set Company Address2
         * 
         * @param address2
         */
        public void setAddress2(String address2)
        {
            this.address2 = address2;
        }


        /**
         * @return Company Address3
         */
        public String getAddress3()
        {
            return address3;
        }


        /**
         * Set Company Address3
         * 
         * @param address3
         */
        public void setAddress3(String address3)
        {
            this.address3 = address3;
        }


        /**
         * @return Company Postcode
         */
        public String getPostcode()
        {
            return postcode;
        }


        /**
         * Set Company Postcode
         * 
         * @param postcode
         */
        public void setPostcode(String postcode)
        {
            this.postcode = postcode;
        }


        /**
         * @return Company Telephone
         */
        public String getTelephone()
        {
            return telephone;
        }


        /**
         * Set Company Telephone
         * 
         * @param telephone
         */
        public void setTelephone(String telephone)
        {
            this.telephone = telephone;
        }


        /**
         * @return Company Fax
         */
        public String getFax()
        {
            return fax;
        }


        /**
         * Set Company Fax
         * 
         * @param fax
         */
        public void setFax(String fax)
        {
            this.fax = fax;
        }


        /**
         * @return Company Email
         */
        public String getEmail()
        {
            return email;
        }


        /**
         * Set Company Email
         * 
         * @param email
         */
        public void setEmail(String email)
        {
            this.email = email;
        }
    }

}
