package org.springframework.social.alfresco.api.entities;

import java.util.Date;

public class Favourite
{
	private String           targetGuid;
    private Date             createdAt;
    private FavouriteTarget  target;
    
	public Favourite()
	{
	}

	public String getTargetGuid()
	{
		return targetGuid;
	}

	public Date getCreatedAt()
	{
		return createdAt;
	}

	public FavouriteTarget getTarget()
	{
		return target;
	}

	public void setTargetGuid(String targetGuid)
	{
		this.targetGuid = targetGuid;
	}

	public void setCreatedAt(Date createdAt)
	{
		this.createdAt = createdAt;
	}

	public void setTarget(FavouriteTarget target)
	{
		this.target = target;
	}

	@Override
	public String toString()
	{
		return "Favourite [targetGuid=" + targetGuid + ", createdAt=" + createdAt + ", target="
				+ target + "]";
	}
}
