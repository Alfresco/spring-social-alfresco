
package org.springframework.social.alfresco.api.entities;


public class Site
{
    public enum Visibility
    {
        PRIVATE, PUBLIC, MODERATED
    };


    private String     title;
    private String     description;
    private Visibility visibility;
    private String     id;
    private String     sitePreset;
    private Role       role;
    private Site       site;


    public String getTitle()
    {
        return title;
    }


    public void setTitle(String title)
    {
        this.title = title;
    }


    public String getDescription()
    {
        return description;
    }


    public void setDescription(String description)
    {
        this.description = description;
    }


    public Visibility getVisibility()
    {
        return visibility;
    }


    public void setVisibility(Visibility visibility)
    {
        this.visibility = visibility;
    }


    public String getId()
    {
        return id;
    }


    public void setId(String id)
    {
        this.id = id;
    }


    public String getSitePreset()
    {
        return sitePreset;
    }


    public void setSitePreset(String sitePreset)
    {
        this.sitePreset = sitePreset;
    }


    public Site getSite()
    {
        return site;
    }


    public void setSite(Site site)
    {
        this.site = site;
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
