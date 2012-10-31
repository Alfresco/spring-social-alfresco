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
 * A container is a folder or space in a site
 * 
 * @author jottley
 * 
 */
public class Container
{
    private String folderId;
    private String id;


    /**
     * @return The container's descriptive name.
     */
    public String getFolderId()
    {
        return folderId;
    }


    /**
     * Set the container's descriptive name.
     * 
     * @param folderId
     */
    public void setFolderId(String folderId)
    {
        this.folderId = folderId;
    }


    /**
     * @return The container identifier. An opaque string which uniquely identifies this container.
     */
    public String getId()
    {
        return id;
    }


    /**
     * Set the container identifier. An opaque string which uniquely identifies this container.
     * 
     * @param id
     */
    public void setId(String id)
    {
        this.id = id;
    }


}
