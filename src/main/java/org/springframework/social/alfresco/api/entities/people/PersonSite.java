
package org.springframework.social.alfresco.api.entities.people;


import org.springframework.social.alfresco.api.entities.Role;
import org.springframework.social.alfresco.api.entities.Site;


public class PersonSite
{
    private Site   site;
    private String id;
    private Role   role;


    public Site getSite()
    {
        return site;
    }


    public void setSite(Site site)
    {
        this.site = site;
    }


    public String getId()
    {
        return id;
    }


    public void setId(String id)
    {
        this.id = id;
    }


    public Role getRole()
    {
        return role;
    }


    public void setRole(Role role)
    {
        this.role = role;
    }
}
