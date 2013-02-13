package org.springframework.social.alfresco.api.entities;

/**
 * Representation of a folder node.
 * 
 * @author steveglover
 *
 */
public class Folder extends Node
{
	public Folder()
	{
	}

	@Override
	public String toString()
	{
		return "Folder [id=" + id + ", name=" + name + ", title=" + title
				+ ", guid=" + guid + ", description=" + description
				+ ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ "]";
	}
}
