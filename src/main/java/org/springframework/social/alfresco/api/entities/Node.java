package org.springframework.social.alfresco.api.entities;
import java.util.Date;

/**
 * Concrete class carrying general information for <b>alf_node</b> data
 * 
 * @author steveglover
 */
public class Node
{
	protected String id;
	protected String name;
	protected String title;
	protected String guid;
	protected String description;
    protected Date createdAt;
    protected Date modifiedAt;
    protected String createdBy;
    protected String modifiedBy;
    
    public Node()
    {
    }
    
    public void setTitle(String title)
	{
		this.title = title;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setCreatedAt(Date createdAt)
	{
		this.createdAt = createdAt;
	}

	public void setModifiedAt(Date modifiedAt)
	{
		this.modifiedAt = modifiedAt;
	}

	public void setModifiedBy(String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
	}

	public String getGuid()
	{
		return guid;
	}
    
	public void setGuid(String guid)
	{
		this.guid = guid;
	}

	public String getTitle()
	{
		return title;
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
        return this.createdAt;
    }

    public void setCreated(Date createdAt)
    {
        this.createdAt = createdAt;
    }

	public Date getModifiedAt()
	{
		return modifiedAt;
	}

	public String getModifiedBy()
	{
		return modifiedBy;
	}

    public String getDescription()
	{
		return description;
	}

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCreatedBy()
    {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy)
    {
        this.createdBy = createdBy;
    }

	@Override
	public String toString()
	{
		return "Node [id=" + id + ", name=" + name + ", title=" + title
				+ ", guid=" + guid + ", description=" + description
				+ ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ "]";
	}
}
