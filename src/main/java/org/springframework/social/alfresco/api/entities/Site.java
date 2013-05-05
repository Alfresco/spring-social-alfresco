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


/**
 * An Alfresco site is a project area where you can share content and collaborate with other site members.
 * 
 * @author jottley
 * 
 */
public class Site
{
    /**
     * Visibility of the Site
     * 
     * @author jottley
     * 
     */
    public enum Visibility
    {
        PRIVATE, PUBLIC, MODERATED
    };


    private String     title;
    private String     description;
    private Visibility visibility;
    private String     id;
    private String     guid;
    private String     sitePreset;
    private Role       role;
    private Site       site;


    /**
     * @return The site's name (used in the site's list and on the sites dashboard).
     */
    public String getTitle()
    {
        return title;
    }


    /**
     * Set the site's name (used in the site's list and on the sites dashboard).
     * 
     * @param title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }


    /**
     * @return The description of the site
     */
    public String getDescription()
    {
        return description;
    }


    /**
     * Set the description of the site
     * 
     * @param description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }


    /**
     * @return The visibility of the site, PRIVATE, PUBLIC, or MODERATED.
     */
    public Visibility getVisibility()
    {
        return visibility;
    }


    /**
     * Set the visibility of the site, PRIVATE, PUBLIC, or MODERATED.
     * 
     * @param visibility
     */
    public void setVisibility(Visibility visibility)
    {
        this.visibility = visibility;
    }


    /**
     * @return The site identifier. An opaque string which uniquely identifies this site.
     */
    public String getId()
    {
        return id;
    }


    /**
     * Set the site identifier. An opaque string which uniquely identifies this site.
     * 
     * @param id
     */
    public void setId(String id)
    {
        this.id = id;
    }
    
    
    /**
     * @return The site GUID
     */
    public String getGuid()
    {
        return guid;
    }
    
    
    /**
     * Set the sites GUID.
     * 
     * @param guid
     */
    public void setGuid(String guid)
    {
        this.guid = guid;
    }


    /**
     * @return The site preset values
     */
    public String getSitePreset()
    {
        return sitePreset;
    }


    /**
     * Set the site preset values
     * 
     * @param sitePreset
     */
    public void setSitePreset(String sitePreset)
    {
        this.sitePreset = sitePreset;
    }


    /**
     * @return Utility function when site is nested
     */
    public Site getSite()
    {
        return site;
    }


    /**
     * Utility function when site is nested
     * 
     * @param site
     */
    public void setSite(Site site)
    {
        this.site = site;
    }


    /**
     * @return user role in site
     */
    public Role getRole()
    {
        return role;
    }


    /**
     * Set user role in Site
     * 
     * @param role
     */
    public void setRole(Role role)
    {
        this.role = role;
    }
}
