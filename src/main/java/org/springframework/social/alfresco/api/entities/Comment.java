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

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;


/**
 * A comment on folders and individual items to give other users information or notes specific to that content.
 * 
 * @author jottley
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class Comment
{
    private boolean edited;
    private String  content;
    private String  id;
    private Date    modifiedAt;
    private Date    createdAt;
    private Person  createdBy;
    private boolean canDelete;
    private Person  modifiedBy;
    private boolean canEdit;


    /**
     * @return True if the comment has been edited since it was first created
     */
    public boolean isEdited()
    {
        return edited;
    }


    /**
     * Set True if comment has been edited since it was first created
     * 
     * @param edited
     */
    public void setEdited(boolean edited)
    {
        this.edited = edited;
    }


    /**
     * @return The comment itself
     */
    public String getContent()
    {
        return content;
    }


    /**
     * Set the comment
     * 
     * @param content
     */
    public void setContent(String content)
    {
        this.content = content;
    }


    /**
     * @return A unique opaque string id
     */
    public String getId()
    {
        return id;
    }


    /**
     * Set the unique opaque string id
     * 
     * @param id
     */
    public void setId(String id)
    {
        this.id = id;
    }


    /**
     * @return The date time that the comment was last modified
     */
    public Date getModifiedAt()
    {
        return modifiedAt;
    }


    /**
     * Set the date time that the comment was last modified
     * 
     * @param modifiedAt
     */
    public void setModifiedAt(Date modifiedAt)
    {
        this.modifiedAt = modifiedAt;
    }


    /**
     * @return The date time that the comment was created
     */
    public Date getCreatedAt()
    {
        return createdAt;
    }


    /**
     * Set the date time that the comment was created
     * 
     * @param createdAt
     */
    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }


    /**
     * @return An embedded People object describing the person who created this comment
     */
    public Person getCreatedBy()
    {
        return createdBy;
    }


    /**
     * Set the People object describing the person who created this comment
     * 
     * @param createdBy
     */
    public void setCreatedBy(Person createdBy)
    {
        this.createdBy = createdBy;
    }


    /**
     * @return True if this comment can be deleted by the current authenticated user. False if not, or if the node that is being
     * commented upon is either a working copy or locked.
     */
    public boolean isCanDelete()
    {
        return canDelete;
    }


    /**
     * Set True if this comment can be deleted by the current authenticated user. False if not, or if the node that is being
     * commented upon is either a working copy or locked.
     * 
     * @param canDelete
     */
    public void setCanDelete(boolean canDelete)
    {
        this.canDelete = canDelete;
    }


    /**
     * @return An embedded People object describing the person who last modified this comment
     */
    public Person getModifiedBy()
    {
        return modifiedBy;
    }


    /**
     * Set the People object describing the person who last modified this comment
     * 
     * @param modifiedBy
     */
    public void setModifiedBy(Person modifiedBy)
    {
        this.modifiedBy = modifiedBy;
    }


    /**
     * @return True if this comment can be edited by the current authenticated user. False if not, or if the node that is being
     * commented upon is either a working copy or locked.
     */
    public boolean canEdit()
    {
        return canEdit;
    }


    /**
     * Set True if this comment can be edited by the current authenticated user. False if not, or if the node that is being
     * commented upon is either a working copy or locked.
     * 
     * @param canEdit
     */
    public void setCanEdit(boolean canEdit)
    {
        this.canDelete = canEdit;
    }
}
