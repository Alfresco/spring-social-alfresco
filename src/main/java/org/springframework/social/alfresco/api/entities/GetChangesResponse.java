package org.springframework.social.alfresco.api.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author steveglover
 *
 */
public class GetChangesResponse implements Serializable
{
    private static final long serialVersionUID = -4755631720141220031L;

    public static enum Status { ready, notReady, error };

    private String syncId;
    private Status status; // ok, not ready, error
    private String message; // if status==error
    private List<Change> changes;
    
    public GetChangesResponse()
    {        
    }
    
    @SuppressWarnings("unchecked")
    public GetChangesResponse(String syncId, boolean ready, List<Change> changes)
    {
        this.syncId = syncId;
        this.changes = changes; 
        if(this.changes == null)
        {
            this.changes = Collections.EMPTY_LIST;
        }
        this.status = ready ? Status.ready : Status.notReady;
        this.message = null;
        
        if (syncId == null) throw new IllegalArgumentException("syncId null");
        if (changes == null) throw new IllegalArgumentException("changes null");
    }

    @SuppressWarnings("unchecked")
    public GetChangesResponse(String syncId, String errorMessage)
    {
        this.syncId = syncId;
        this.changes = Collections.EMPTY_LIST;
        this.status = Status.error;
        this.message = errorMessage;
    }
    
    public String getSyncId()
    {
        return syncId;
    }
    
    public List<Change> getChanges()
    {
        return changes;
    }
    
    public void setSyncId(String syncId)
    {
        this.syncId = syncId;
    }

    public void setChanges(List<Change> changes)
    {
        this.changes = changes;
    }
    
    public void setStatus(Status status)
    {
        this.status = status;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
    
    public String getStatus()
    {
        return status.toString();
    }
    
    public String getMessage()
    {
        return message;
    }
    
    @Override
    public String toString()
    {
        return "GetChangesResponse[syncId="+syncId+",status="+status+",changes="+changes+",message="+message+"]";
    }
}
