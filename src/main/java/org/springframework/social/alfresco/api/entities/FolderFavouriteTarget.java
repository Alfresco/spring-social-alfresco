package org.springframework.social.alfresco.api.entities;

public class FolderFavouriteTarget implements FavouriteTarget
{
	private Folder folder;

	public FolderFavouriteTarget(Folder folder)
	{
		super();
		this.folder = folder;
	}

	public Folder getFolder()
	{
		return folder;
	}

	public String getGuid()
	{
		return folder.getId();
	}

	@Override
	public String toString()
	{
		return "FolderFavouriteTarget [folder=" + folder + "]";
	}
}
