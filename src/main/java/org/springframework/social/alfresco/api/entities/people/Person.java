
package org.springframework.social.alfresco.api.entities.people;

import org.springframework.social.alfresco.api.entities.Company;


public class Person
{
    private boolean enabled;
    private String  id;
    private String  firstName;
    private String  lastName;
    private String  location;
    private String  instantMessageId;
    private String  avatarId;
    private String  googleId;
    private String  skypeId;
    private String  telephone;
    private String  mobile;
    private String  jobTitle;
    private String  email;
    private String  description;
    private Company company;


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
}
