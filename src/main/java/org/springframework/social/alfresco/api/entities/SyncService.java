package org.springframework.social.alfresco.api.entities;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * 
 * @author steveglover
 *
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class SyncService
{
	private String id;
	private String uri;

	public SyncService()
	{
	}

	public SyncService(String id, String uri)
	{
		super();
		this.id = id;
		this.uri = uri;
	}

	public String getUri()
	{
		return uri;
	}

	public void setUri(String uri)
	{
		this.uri = uri;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	@Override
	public String toString()
	{
		return "SyncService [id=" + id + ", uri=" + uri + "]";
	}
}
