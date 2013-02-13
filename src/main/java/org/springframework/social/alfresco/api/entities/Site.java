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

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
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
    private String     guid;
    private String     sitePreset;
    private Role       role;
    private Site       site;
    private AlfrescoList<Container> containers;


    public String getTitle()
    {
        return title;
    }


    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public String getGuid()
	{
		return guid;
	}

	public void setGuid(String guid)
	{
		this.guid = guid;
	}

	public AlfrescoList<Container> getContainers()
	{
		return containers;
	}

	public void setContainers(AlfrescoList<Container> containers)
	{
		this.containers = containers;
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


	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Site other = (Site) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Site [title=" + title + ", description=" + description
				+ ", visibility=" + visibility + ", id=" + id + ", sitePreset="
				+ sitePreset + ", role=" + role + ", site=" + site + "]";
	}
}
