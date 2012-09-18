
package org.springframework.social.alfresco.api.entities;


import java.util.Date;

import org.springframework.social.alfresco.api.entities.people.Person;


public class ModifiedBy
    extends Person
{
    private Date    createdAt;
    private boolean canEdit;


    public Date getCreatedAt()
    {
        return createdAt;
    }


    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }


    public boolean isCanEdit()
    {
        return canEdit;
    }


    public void setCanEdit(boolean canEdit)
    {
        this.canEdit = canEdit;
    }


}
