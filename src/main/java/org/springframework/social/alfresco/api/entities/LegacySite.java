package org.springframework.social.alfresco.api.entities;

import java.util.List;

public class LegacySite
{
	/*
	<#if site.node?exists>
	"node": "${url.serviceContext + "/api/node/" + site.node.storeType + "/" + site.node.storeId + "/" + site.node.id}",
	"tagScope": "${url.serviceContext + "/api/tagscopes/" + site.node.storeType + "/" + site.node.storeId + "/" + site.node.id}",
	</#if>
	<#if site.customProperties?size != 0>
	"customProperties":
	{
		<#list site.customProperties?keys as prop>
		<#assign propDetails = site.customProperties[prop]>
		"${prop}":
		{
			"name": "${prop}",
			"value":
			<#if propDetails.value?is_enumerable>
			[
			<#list propDetails.value as v>
			"${v?string}"<#if v_has_next>,</#if>
			</#list>
			]
			<#else>
			"${propDetails.value?string}"
			</#if>,
			"type": <#if propDetails.type??>"${propDetails.type}"<#else>null</#if>, 
			"title": <#if propDetails.title??>"${propDetails.title}"<#else>null</#if>
		}
		<#if prop_has_next>,</#if>
		</#list>
	},
	</#if>
	<#if roles = "user">
	"siteRole": "${site.getMembersRole(userid)!""}",
	<#elseif roles = "managers">
	"siteManagers":
	[
		<#assign managers = site.listMembers(null, "SiteManager", 0, true)?keys />
		<#list managers as manager>
			"${manager}"<#if manager_has_next>,</#if>
		</#list>
	],
	</#if>
*/
		
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
	
	
}
