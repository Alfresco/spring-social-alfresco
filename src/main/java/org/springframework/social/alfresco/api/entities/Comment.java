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

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class Comment
{
	private String nodeId;
	private boolean edited;
    private String  content;
    private String  id;
    private Date    modifiedAt;
    private Date    createdAt;
    private Person  createdBy;
    private boolean canDelete;
    private Person  modifiedBy;
    private boolean canEdit;


    public String getNodeId()
	{
		return nodeId;
	}


	public void setNodeId(String nodeId)
	{
		this.nodeId = nodeId;
	}


	public boolean isEdited()
    {
        return edited;
    }


    public void setEdited(boolean edited)
    {
        this.edited = edited;
    }


    public String getContent()
    {
        return content;
    }


    public void setContent(String content)
    {
        this.content = content;
    }


    public String getId()
    {
        return id;
    }


    public void setId(String id)
    {
        this.id = id;
    }


    public Date getModifiedAt()
    {
        return modifiedAt;
    }


    public void setModifiedAt(Date modifiedAt)
    {
        this.modifiedAt = modifiedAt;
    }


    public Date getCreatedAt()
    {
        return createdAt;
    }


    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }


    public Person getCreatedBy()
    {
        return createdBy;
    }


    public void setCreatedBy(Person createdBy)
    {
        this.createdBy = createdBy;
    }


    public boolean isCanDelete()
    {
        return canDelete;
    }


    public void setCanDelete(boolean canDelete)
    {
        this.canDelete = canDelete;
    }


    public Person getModifiedBy()
    {
        return modifiedBy;
    }


    public void setModifiedBy(Person modifiedBy)
    {
        this.modifiedBy = modifiedBy;
    }


    public boolean canEdit()
    {
        return canEdit;
    }


    public void setCanEdit(boolean canEdit)
    {
        this.canDelete = canEdit;
    }
}
