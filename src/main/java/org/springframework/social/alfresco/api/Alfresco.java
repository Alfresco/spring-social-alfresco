/*
\ * Copyright 2012 Alfresco Software Limited.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * 
 * This file is part of an unsupported extension to Alfresco.
 */

package org.springframework.social.alfresco.api;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.FileableCmisObject;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.Tree;
import org.apache.chemistry.opencmis.commons.enums.IncludeRelationships;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.social.alfresco.api.entities.Activity;
import org.springframework.social.alfresco.api.entities.AlfrescoList;
import org.springframework.social.alfresco.api.entities.Comment;
import org.springframework.social.alfresco.api.entities.Container;
import org.springframework.social.alfresco.api.entities.Favourite;
import org.springframework.social.alfresco.api.entities.LegacySite;
import org.springframework.social.alfresco.api.entities.Member;
import org.springframework.social.alfresco.api.entities.Metadata;
import org.springframework.social.alfresco.api.entities.Network;
import org.springframework.social.alfresco.api.entities.Person;
import org.springframework.social.alfresco.api.entities.Preference;
import org.springframework.social.alfresco.api.entities.Rating;
import org.springframework.social.alfresco.api.entities.Role;
import org.springframework.social.alfresco.api.entities.Site;
import org.springframework.social.alfresco.api.entities.Site.Visibility;
import org.springframework.social.alfresco.api.entities.SiteMembershipRequest;
import org.springframework.social.alfresco.api.entities.Tag;
import org.springframework.social.alfresco.api.entities.UserActivationResponse;
import org.springframework.social.alfresco.api.entities.UserRegistration;
import org.springframework.web.client.RestClientException;


/**
 * 
 * @author jottley
 * @author sglover
 * 
 */
public interface Alfresco
{
    public static final String DEFAULT_SCOPE             = "public_api";

    public static class QueryParams
    {
        public final static String PROPERTIES = "properties";
    }

    public static class TemplateParams
    {
        public final static String NETWORK    = "network";
        public final static String SITE       = "site";
        public final static String CONTAINER  = "container";
        public final static String PREFERENCE = "preference";
        public final static String TAG        = "tag";
        public final static String RATING     = "rating";
        public final static String COMMENT    = "comment";
        public final static String NODE       = "node";
        public final static String PERSON     = "person";
        public final static String MEMBER     = "member";
    }

	public java.util.List<Repository> getCMISNetworks();

	public Session getCMISSession(String networkId);

    public Network getNetwork(String network)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public AlfrescoList<Network> getNetworks()
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public AlfrescoList<Network> getNetworks(Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Site getSite(String site, String network)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public AlfrescoList<Site> getSites(String network)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public AlfrescoList<Site> getSites(String network, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Container getContainer(String network, String site, String contatiner)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public AlfrescoList<Container> getContainers(String network, String site)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public AlfrescoList<Container> getContainers(String network, String site, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Member getMember(String network, String site, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public AlfrescoList<Member> getMembers(String network, String site)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public AlfrescoList<Member> getMembers(String network, String site, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Member addMember(String network, String site, String personId, Role role)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public void updateMember(String network, String site, String personId, Role role)
        throws RestClientException;


    public void deleteMember(String network, String site, String personId)
        throws RestClientException;


    public Person getPerson(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public AlfrescoList<Site> getSites(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public AlfrescoList<Site> getSites(String network, String person, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Site getSite(String network, String person, String site)
        throws JsonParseException,
            JsonMappingException,
            IOException;

    public Site addFavoriteSite(String network, String personId, String siteId)
            throws JsonParseException,
                JsonMappingException,
                IOException;
    
    public AlfrescoList<Site> getFavoriteSites(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public AlfrescoList<Site> getFavoriteSites(String network, String person, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Preference getPreference(String network, String person, String preference)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public AlfrescoList<Preference> getPreferences(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public AlfrescoList<Preference> getPreferences(String network, String person, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Network getNetwork(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public AlfrescoList<Network> getNetworks(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public AlfrescoList<Network> getNetworks(String network, String person, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public AlfrescoList<Activity> getActivities(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public AlfrescoList<Activity> getActivities(String network, String person, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Tag getTag(String network, String tag)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public AlfrescoList<Tag> getTags(String network)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public AlfrescoList<Tag> getTags(String network, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public void updateTag(String network, String tagId, String tag)
        throws RestClientException;


    public AlfrescoList<Comment> getComments(String network, String node)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public AlfrescoList<Comment> getComments(String network, String node, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Comment createComment(String network, String node, String comment)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public AlfrescoList<Comment> createComments(String network, String node, List<String> comments)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public void updateComment(String network, String node, String commentId, String comment)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public void deleteComment(String network, String node, String commentId)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public AlfrescoList<Tag> getNodesTags(String network, String node)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public AlfrescoList<Tag> getNodesTags(String network, String node, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Tag addTagToNode(String network, String node, String tag)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public AlfrescoList<Tag> addTagsToNode(String network, String node, List<String> tags)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public void removeTagFromNode(String network, String node, String tagId)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public AlfrescoList<Rating> getNodeRatings(String network, String node)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public AlfrescoList<Rating> getNodeRatings(String network, String node, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Rating getNodeRating(String network, String node, String rating)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public void removeNodeRating(String network, String node, String ratingId)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Rating rateNode(String network, String node, boolean like)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Rating rateNode(String network, String node, int stars)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /*
     * public Rating rateNode(String network, String node, String ratingType, Serializable rating) throws JsonParseException,
     * JsonMappingException, IOException;
     */


    public Network getHomeNetwork()
        throws JsonParseException,
            JsonMappingException,
            IOException;

    public Person getCurrentUser()
        throws JsonParseException,
            JsonMappingException,
            IOException;
    
    @Deprecated
    public AlfrescoList<Metadata> networkOptions(String network)
        throws JsonParseException,
            JsonMappingException,
            IOException;

	public UserRegistration registerUser(String email, String firstName, String lastName, String password, String source, String sourceUrl)
			throws IOException;

	public UserActivationResponse activateUser(String id, String key, String email, String firstName, String lastName, String password)
			throws IOException;
            
	public LegacySite createSite(String network, String siteId, String sitePreset, String title, String description, Visibility visibility)
			throws IOException;
	
	public ItemIterable<CmisObject> getChildren(String networkId, String folderId, int skipCount, int maxItems, IncludeRelationships includeRelationships,
			Boolean includeAcls, Set<String> propertyFilter, Boolean includePolicies);
	
	public java.util.List<Tree<FileableCmisObject>> getDescendants(String networkId, String folderId, Integer depth, IncludeRelationships includeRelationships,
			Boolean includeAcls, Set<String> propertyFilter, Boolean includePolicies);

	public AlfrescoList<Favourite> getFavorites(String network, String person)
            throws JsonParseException,
                JsonMappingException,
                IOException;

    public AlfrescoList<Favourite> getFavorites(String network, String person, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;

    public Favourite addFavorite(String network, String personId, Favourite favourite)
            throws JsonParseException,
                JsonMappingException,
                IOException;
    
    public void removeFavourite(String network, String personId, String favouriteId)
            throws JsonParseException,
                JsonMappingException,
                IOException;

    public AlfrescoList<SiteMembershipRequest> getPersonSiteMembershipRequests(String network, String personId)
            throws JsonParseException,
                JsonMappingException,
                IOException;

    public AlfrescoList<SiteMembershipRequest> getPersonSiteMembershipRequests(String network, String person, Map<String, String> parameters)
            throws JsonParseException,
                JsonMappingException,
                IOException;

    public SiteMembershipRequest createPersonSiteMembershipRequest(String network, String personId, SiteMembershipRequest siteMembershipRequest)
            throws JsonParseException,
                JsonMappingException,
                IOException;
    
    public void cancelPersonSiteMembershipRequest(String network, String personId, String siteId)
            throws JsonParseException,
                JsonMappingException,
                IOException;
}
