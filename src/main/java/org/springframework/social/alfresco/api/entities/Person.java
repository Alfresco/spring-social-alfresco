
package org.springframework.social.alfresco.api.entities;


import java.util.Date;



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


    public boolean isEnabled()
    {
        return enabled;
    }


    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }


    public String getId()
    {
        return id;
    }


    public void setId(String id)
    {
        this.id = id;
    }


    public String getFirstName()
    {
        return firstName;
    }


    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }


    public String getLastName()
    {
        return lastName;
    }


    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }


    public String getLocation()
    {
        return location;
    }


    public void setLocation(String location)
    {
        this.location = location;
    }


    public String getInstantMessageId()
    {
        return instantMessageId;
    }


    public void setInstantMessageId(String instantMessageId)
    {
        this.instantMessageId = instantMessageId;
    }


    public String getAvatarId()
    {
        return avatarId;
    }


    public void setAvatarId(String avatarId)
    {
        this.avatarId = avatarId;
    }


    public String getAvatar()
    {
        return avatar;
    }


    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }


    public String getGoogleId()
    {
        return googleId;
    }


    public void setGoogleId(String googleId)
    {
        this.googleId = googleId;
    }


    public String getSkypeId()
    {
        return skypeId;
    }


    public void setSkypeId(String skypeId)
    {
        this.skypeId = skypeId;
    }


    public String getTelephone()
    {
        return telephone;
    }


    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }


    public String getMobile()
    {
        return mobile;
    }


    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }


    public String getJobTitle()
    {
        return jobTitle;
    }


    public void setJobTitle(String jobTitle)
    {
        this.jobTitle = jobTitle;
    }


    public String getEmail()
    {
        return email;
    }


    public void setEmail(String email)
    {
        this.email = email;
    }


    public String getDescription()
    {
        return description;
    }


    public void setDescription(String description)
    {
        this.description = description;
    }


    public Company getCompany()
    {
        return company;
    }


    public void setCompany(Company company)
    {
        this.company = company;
    }


    public Date getStatusUpdatedAt()
    {
        return statusUpdatedAt;
    }


    public void setStatusUpdatedAt(Date statusUpdatedAt)
    {
        this.statusUpdatedAt = statusUpdatedAt;
    }


    public String getUserStatus()
    {
        return userStatus;
    }


    public void setUserStatus(String userStatus)
    {
        this.userStatus = userStatus;
    }


    public Date getCreatedAt()
    {
        return createdAt;
    }


    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }


    public boolean isCanEdit()
    {
        return canEdit;
    }


    public void setCanEdit(boolean canEdit)
    {
        this.canEdit = canEdit;
    }
    
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


        public String getOrganization()
        {
            return organization;
        }


        public void setOrganization(String organization)
        {
            this.organization = organization;
        }


        public String getAddress1()
        {
            return address1;
        }


        public void setAddress1(String address1)
        {
            this.address1 = address1;
        }
        
        public String getAddress2()
        {
            return address2;
        }


        public void setAddress2(String address2)
        {
            this.address2 = address2;
        }
        
        public String getAddress3()
        {
            return address3;
        }


        public void setAddress3(String address3)
        {
            this.address3 = address3;
        }


        public String getPostcode()
        {
            return postcode;
        }


        public void setPostcode(String postcode)
        {
            this.postcode = postcode;
        }


        public String getTelephone()
        {
            return telephone;
        }


        public void setTelephone(String telephone)
        {
            this.telephone = telephone;
        }


        public String getFax()
        {
            return fax;
        }


        public void setFax(String fax)
        {
            this.fax = fax;
        }


        public String getEmail()
        {
            return email;
        }


        public void setEmail(String email)
        {
            this.email = email;
        }
    }

}
