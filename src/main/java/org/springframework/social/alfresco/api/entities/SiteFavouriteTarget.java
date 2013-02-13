package org.springframework.social.alfresco.api.entities;

public class SiteFavouriteTarget implements FavouriteTarget
{
	private Site site;

	public SiteFavouriteTarget(Site site)
	{
		super();
		this.site = site;
	}

	public Site getSite()
	{
		return site;
	}

	public String getGuid()
	{
		return site.getGuid();
	}

	@Override
	public String toString()
	{
		return "SiteFavouriteTarget [site=" + site + "]";
	}
}
