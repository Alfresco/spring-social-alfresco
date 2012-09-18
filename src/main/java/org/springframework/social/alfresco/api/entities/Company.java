
package org.springframework.social.alfresco.api.entities;


public class Company
{
    private String organization;
    private String address1;
    private String address2;
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
