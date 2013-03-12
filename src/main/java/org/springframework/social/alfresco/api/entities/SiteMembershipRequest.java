package org.springframework.social.alfresco.api.entities;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class SiteMembershipRequest
{
    private String id; // site id
    private String message;
    private Date createdAt;
    private Date modifiedAt;
    private Site site;
    
    public SiteMembershipRequest()
    {
    }

    public Site getSite()
	{
		return site;
	}

	public void setSite(Site site)
	{
		this.site = site;
	}

	public String getId()
    {
		return id;
    }

    public void setId(String id)
    {
		this.id = id;
    }
    
	public Date getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(Date createdAt)
	{
		this.createdAt = createdAt;
	}

	public Date getModifiedAt()
	{
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt)
	{
		this.modifiedAt = modifiedAt;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	@Override
	public String toString()
	{
		return "SiteMembershipRequest [id=" + id + ", message=" + message + ", createdAt=" + createdAt
				+ ", modifiedAt=" + modifiedAt + "]";
	}
}
