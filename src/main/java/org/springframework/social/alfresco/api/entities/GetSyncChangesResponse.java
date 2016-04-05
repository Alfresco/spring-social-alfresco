package org.springframework.social.alfresco.api.entities;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 
 * @author steveglover
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class GetSyncChangesResponse implements Serializable
{
    private static final long serialVersionUID = 5369856774191393297L;

    public static enum Status { ready, notReady, error };

    private String syncId;
    private Status status; // ok, not ready, error, cancelled
    private String message; // if status==error
    private List<SyncChange> changes;
    private SyncReset reset;

    public GetSyncChangesResponse()
    {
    }

    public String getSyncId()
    {
        return syncId;
    }
    
    public SyncReset getReset()
    {
        return reset;
    }

    public void setReset(SyncReset reset)
    {
        this.reset = reset;
    }

    public void reset(SyncReset reset)
    {
        this.reset = reset;
    }

    public void setChanges(List<SyncChange> changes)
    {
        this.changes = changes;
    }

    public List<SyncChange> getChanges()
    {
        return changes;
    }
    
    public void setSyncId(String syncId)
    {
        this.syncId = syncId;
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
        return "GetChangesResponse [syncId=" + syncId + ", status=" + status
                + ", message=" + message + ", changes=" + changes + ", reset="
                + reset + "]";
    }
}