package org.springframework.social.alfresco.api.entities;


public class DocumentFavouriteTarget implements FavouriteTarget
{
	private Document file;

	public DocumentFavouriteTarget(Document file)
	{
		super();
		this.file = file;
	}

	public Document getFile()
	{
		return file;
	}
	
	public String getGuid()
	{
		return file.getId();
	}

	@Override
	public String toString()
	{
		return "DocumentFavouriteTarget [file=" + file + "]";
	}
}
