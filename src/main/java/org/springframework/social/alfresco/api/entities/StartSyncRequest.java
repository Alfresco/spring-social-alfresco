package org.springframework.social.alfresco.api.entities;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author steveglover
 *
 */
public class StartSyncRequest implements Serializable
{
    private static final long serialVersionUID = -3038156090439325659L;
    
    private List<Change> changes;
    
    public StartSyncRequest()
    {        
    }
    
    public StartSyncRequest(List<Change> changes)
    {
        this.changes = changes;  
        if (changes == null) throw new IllegalArgumentException("changes null");
    }
    
    public List<Change> getChanges()
    {
        return changes;
    }
    
    public void setChanges(List<Change> changes)
    {
        this.changes = changes;
    }

    @Override
    public String toString()
    {
        return "StartSyncRequest2["+changes+"]";
    }

}
