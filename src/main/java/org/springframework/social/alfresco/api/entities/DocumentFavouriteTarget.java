package org.springframework.social.alfresco.api.entities;


public class DocumentFavouriteTarget implements FavouriteTarget
{
	private Document document;

	public DocumentFavouriteTarget(Document document)
	{
		super();
		this.document = document;
	}

	public Document getDocument()
	{
		return document;
	}
	
	public String getGuid()
	{
		return document.getId();
	}

	@Override
	public String toString()
	{
		return "DocumentFavouriteTarget [document=" + document + "]";
	}
}
