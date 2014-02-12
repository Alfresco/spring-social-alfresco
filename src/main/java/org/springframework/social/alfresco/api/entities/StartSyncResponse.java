package org.springframework.social.alfresco.api.entities;

import java.io.Serializable;

/**
 * 
 * @author steveglover
 *
 */
public class StartSyncResponse implements Serializable
{
    private static final long serialVersionUID = -4755631720141220031L;

    private String syncId;
    private String url; // get url including sync id
    private String status; // ok, error
    private String message; // if status==error
    private Exception error;

    public StartSyncResponse()
    {        
    }
    
    public StartSyncResponse(String syncId, String url)
    {
        this.syncId = syncId;
        this.url = url; 
        this.status = "ok";
        this.message = null;
    }
    
    public StartSyncResponse(Exception error)
    {
        this.syncId = null;
        this.url = null;
        this.status = "error";
        this.error = error;
    }

    public String getSyncId() {
        return syncId;
    }

    public void setSyncId(String syncId) {
        this.syncId = syncId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Exception getError() {
        return error;
    }

    public void setError(Exception error) {
        this.error = error;
    }

    public String getURL()
    {
        return url;
    }
    
    public void setURL(String url)
    {
        this.url = url;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getStatus()
    {
        return status;
    }
    
    public String getMessage()
    {
        return message;
    }
    
    @Override
    public String toString()
    {
        return "StartSyncResponse[url="+url+",status="+status+",message="+message+"]";
    }

}
