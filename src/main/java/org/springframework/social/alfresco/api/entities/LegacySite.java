package org.springframework.social.alfresco.api.entities;

import java.util.List;

/**
 * Represents a site in the current (non-public) sites api.
 * 
 * @author steveglover
 *
 */
public class LegacySite
{
	private String shortName;
	private String sitePreset;
	private String title;
	private String description;
	private String visibility;
	private String url;
	private Boolean isPublic;
	private String siteRole;
	private List<String> siteManagers;
	private String tagScope;
	private String node;

	public void setShortName(String shortName)
	{
		this.shortName = shortName;
	}
	
	public void setSitePreset(String sitePreset)
	{
		this.sitePreset = sitePreset;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public void setVisibility(String visibility)
	{
		this.visibility = visibility;
	}

	public String getShortName() {
		return shortName;
	}

	public String getSitePreset() {
		return sitePreset;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getVisibility() {
		return visibility;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public String getSiteRole() {
		return siteRole;
	}

	public void setSiteRole(String siteRole) {
		this.siteRole = siteRole;
	}

	public List<String> getSiteManagers() {
		return siteManagers;
	}

	public void setSiteManagers(List<String> siteManagers) {
		this.siteManagers = siteManagers;
	}

	public String getTagScope() {
		return tagScope;
	}

	public void setTagScope(String tagScope) {
		this.tagScope = tagScope;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	@Override
	public String toString()
	{
		return "LegacySite [shortName=" + shortName + ", sitePreset="
				+ sitePreset + ", title=" + title + ", description="
				+ description + ", visibility=" + visibility + ", url=" + url
				+ ", isPublic=" + isPublic + ", siteRole=" + siteRole
				+ ", siteManagers=" + siteManagers + ", tagScope=" + tagScope
				+ ", node=" + node + "]";
	}
}
