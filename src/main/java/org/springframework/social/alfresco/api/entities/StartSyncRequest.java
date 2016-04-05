package org.springframework.social.alfresco.api.entities;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author sglover
 *
 */
public class StartSyncRequest implements Serializable
{
    private static final long serialVersionUID = -3038156090439325659L;
    
    private List<SyncChange> changes;
    
    public StartSyncRequest()
    {        
    }
    
    public StartSyncRequest(List<SyncChange> changes)
    {
        this.changes = changes;  
        if (changes == null) throw new IllegalArgumentException("changes null");
    }
    
    public List<SyncChange> getChanges()
    {
        return changes;
    }
    
    public void setChanges(List<SyncChange> changes)
    {
        this.changes = changes;
    }

    @Override
    public String toString()
    {
        return "StartSyncRequest["+changes+"]";
    }
}
