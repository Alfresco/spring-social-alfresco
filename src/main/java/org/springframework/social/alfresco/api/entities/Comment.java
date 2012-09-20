
package org.springframework.social.alfresco.api.entities;


import java.util.Date;



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
