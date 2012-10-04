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
