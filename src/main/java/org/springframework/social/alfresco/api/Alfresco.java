/*
 * Copyright 2012 Alfresco Software Limited.
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

import org.apache.chemistry.opencmis.client.api.Session;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.social.alfresco.api.entities.Activity;
import org.springframework.social.alfresco.api.entities.Comment;
import org.springframework.social.alfresco.api.entities.Container;
import org.springframework.social.alfresco.api.entities.AlfrescoList;
import org.springframework.social.alfresco.api.entities.Metadata;
import org.springframework.social.alfresco.api.entities.Person;
import org.springframework.social.alfresco.api.entities.Preference;
import org.springframework.social.alfresco.api.entities.Member;
import org.springframework.social.alfresco.api.entities.Network;
import org.springframework.social.alfresco.api.entities.Rating;
import org.springframework.social.alfresco.api.entities.Role;
import org.springframework.social.alfresco.api.entities.Site;
import org.springframework.social.alfresco.api.entities.Tag;
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

    public final String        ROOT_ATOMPUB_URL          = "https://api.alfresco.com/cmis/versions/1.0/atom";
    public final String        ATOMPUB_URL               = "https://api.alfresco.com/{network}/public/cmis/versions/1.0/atom";
    public final String        NETWORKS_URL              = "https://api.alfresco.com/";
    public final String        NETWORK_URL               = "https://api.alfresco.com/{network}/public/alfresco/versions/1/networks/{network}";
    public final String        SITES_URL                 = "https://api.alfresco.com/{network}/public/alfresco/versions/1/sites";
    public final String        SITE_URL                  = "https://api.alfresco.com/{network}/public/alfresco/versions/1/sites/{site}";
    public final String        CONTAINERS_URL            = "https://api.alfresco.com/{network}/public/alfresco/versions/1/sites/{site}/containers";
    public final String        CONTAINER_URL             = "https://api.alfresco.com/{network}/public/alfresco/versions/1/sites/{site}/containers/{container}";
    public final String        MEMBERS_URL               = "https://api.alfresco.com/{network}/public/alfresco/versions/1/sites/{site}/members";
    public final String        MEMBER_URL                = "https://api.alfresco.com/{network}/public/alfresco/versions/1/sites/{site}/members/{member}";
    public final String        PEOPLE_URL                = "https://api.alfresco.com/{network}/public/alfresco/versions/1/people/{person}";
    public final String        PEOPLE_SITES_URL          = "https://api.alfresco.com/{network}/public/alfresco/versions/1/people/{person}/sites";
    public final String        PEOPLE_SITE_URL           = "https://api.alfresco.com/{network}/public/alfresco/versions/1/people/{person}/sites/{site}";
    public final String        PEOPLE_FAVORITE_SITES_URL = "https://api.alfresco.com/{network}/public/alfresco/versions/1/people/{person}/favorite-sites";
    public final String        PEOPLE_PREFERENCES_URL    = "https://api.alfresco.com/{network}/public/alfresco/versions/1/people/{person}/preferences";
    public final String        PEOPLE_PREFERENCE_URL     = "https://api.alfresco.com/{network}/public/alfresco/versions/1/people/{person}/preferences/{preference}";
    public final String        PEOPLE_NETWORKS_URL       = "https://api.alfresco.com/{network}/public/alfresco/versions/1/people/{person}/networks";
    public final String        PEOPLE_NETWORK_URL        = "https://api.alfresco.com/{network}/public/alfresco/versions/1/people/{person}/networks/{network}";
    public final String        PEOPLE_ACTIVITIES_URL     = "https://api.alfresco.com/{network}/public/alfresco/versions/1/people/{person}/activities";
    public final String        TAGS_URL                  = "https://api.alfresco.com/{network}/public/alfresco/versions/1/tags";
    public final String        TAG_URL                   = "https://api.alfresco.com/{network}/public/alfresco/versions/1/tags/{tag}";
    public final String        NODE_COMMENTS_URL         = "https://api.alfresco.com/{network}/public/alfresco/versions/1/nodes/{node}/comments";
    public final String        NODE_COMMENT_URL          = "https://api.alfresco.com/{network}/public/alfresco/versions/1/nodes/{node}/comments/{comment}";
    public final String        NODE_TAGS_URL             = "https://api.alfresco.com/{network}/public/alfresco/versions/1/nodes/{node}/tags";
    public final String        NODE_TAG_URL              = "https://api.alfresco.com/{network}/public/alfresco/versions/1/nodes/{node}/tags/{tag}";
    public final String        NODE_RATINGS_URL          = "https://api.alfresco.com/{network}/public/alfresco/versions/1/nodes/{node}/ratings";
    public final String        NODE_RATING_URL           = "https://api.alfresco.com/{network}/public/alfresco/versions/1/nodes/{node}/ratings/{rating}";


    /**
     * Additional Query Parameters
     * 
     * @author jottley
     */
    public static class QueryParams
    {
        /**
         * Query Properties Parameter
         */
        public final static String PROPERTIES = "properties";
    }

    /**
     * Template Parmater Constant names
     * 
     * Url: https://api.alfresco.com/{network}/public/alfresco/versions/1/networks/{network}
     * 
     * @author jottley
     */
    public static class TemplateParams
    {
        /**
         * Network Parameter
         */
        public final static String NETWORK    = "network";
        /**
         * Site Parameter
         */
        public final static String SITE       = "site";
        /**
         * Container Parameter
         */
        public final static String CONTAINER  = "container";
        /**
         * Preference Parameter
         */
        public final static String PREFERENCE = "preference";
        /**
         * Tag Parameter
         */
        public final static String TAG        = "tag";
        /**
         * Rating Parameter
         */
        public final static String RATING     = "rating";
        /**
         * Comment Parameter
         */
        public final static String COMMENT    = "comment";
        /**
         * Node Parameter
         */
        public final static String NODE       = "node";
        /**
         * Person Parameter
         */
        public final static String PERSON     = "person";
        /**
         * Member Parameter
         */
        public final static String MEMBER     = "member";
    }


    /**
     * @param network
     * @return
     */
    public Session getCMISSession(String network);


    /**
     * Get the Alfresco Network for the user
     * 
     * @param network
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public Network getNetwork(String network)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get all Alfresco Networks that the user is a member of
     * 
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Network> getNetworks()
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get all Alfresco Networks that the user is a member of filter by parameters
     * 
     * @param parameters
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Network> getNetworks(Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get site in network
     * 
     * @param site
     * @param network
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public Site getSite(String site, String network)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get all sites in network
     * 
     * @param network
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Site> getSites(String network)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get all sites in network filtered by parameters
     * 
     * @param network
     * @param parameters
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Site> getSites(String network, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get Container in site in network
     * 
     * @param network
     * @param site
     * @param contatiner
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public Container getContainer(String network, String site, String contatiner)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get all containers in site in network
     * 
     * @param network
     * @param site
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Container> getContainers(String network, String site)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get all containers in site in network filtered by parameters
     * 
     * @param network
     * @param site
     * @param parameters
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Container> getContainers(String network, String site, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get member of site in network
     * 
     * @param network
     * @param site
     * @param person
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public Member getMember(String network, String site, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get all members of site in network
     * 
     * @param network
     * @param site
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Member> getMembers(String network, String site)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get all mermbers of site in network filtered by parameters
     * 
     * @param network
     * @param site
     * @param parameters
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Member> getMembers(String network, String site, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Add member to site in network with role
     * 
     * @param network
     * @param site
     * @param personId - person must exist in network
     * @param role - must be one Role.SiteManager, Role.SiteContributor, Role.SiteCollaborator, Role.SiteConsumer
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public Member addMember(String network, String site, String personId, Role role)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Update Role of member of site in network
     * 
     * @param network
     * @param site
     * @param personId - person must be member of site
     * @param role - must be one Role.SiteManager, Role.SiteContributor, Role.SiteCollaborator, Role.SiteConsumer
     * @throws RestClientException
     */
    public void updateMember(String network, String site, String personId, Role role)
        throws RestClientException;


    /**
     * Delete person membership from site in network
     * 
     * @param network
     * @param site
     * @param personId
     * @throws RestClientException
     */
    public void deleteMember(String network, String site, String personId)
        throws RestClientException;


    /**
     * Get person from network
     * 
     * @param network
     * @param person
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public Person getPerson(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get sites in network that the user is a member of
     * 
     * @param network
     * @param person
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Site> getSites(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get sites in network that the user is a member of filtered by parameters
     * 
     * @param network
     * @param person
     * @param parameters
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Site> getSites(String network, String person, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get site in network that the person is a member of
     * 
     * @param network
     * @param person
     * @param site
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public Site getSite(String network, String person, String site)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get favorited sites of person in network
     * 
     * @param network
     * @param person
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Site> getFavoriteSites(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get favorited sites of person in network filtered by parameters
     * 
     * @param network
     * @param person
     * @param parameters
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Site> getFavoriteSites(String network, String person, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get preference for person in network
     * 
     * @param network
     * @param person
     * @param preference
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public Preference getPreference(String network, String person, String preference)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get all preferences for person in network
     * 
     * @param network
     * @param person
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Preference> getPreferences(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get all preferences for person in network filtered by parameters
     * 
     * @param network
     * @param person
     * @param parameters
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Preference> getPreferences(String network, String person, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get network for person
     * 
     * @param network
     * @param person
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public Network getNetwork(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get all networks for person
     * 
     * @param network
     * @param person
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Network> getNetworks(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get all networks for person filtered by parameters
     * 
     * @param network
     * @param person
     * @param parameters
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Network> getNetworks(String network, String person, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get activities for person in network
     * 
     * @param network
     * @param person
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Activity> getActivities(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get activities for person in network filtered by parameters
     * 
     * @param network
     * @param person
     * @param parameters
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Activity> getActivities(String network, String person, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get tag in network
     * 
     * @param network
     * @param tag
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public Tag getTag(String network, String tag)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get all tags in network
     * 
     * @param network
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Tag> getTags(String network)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * @param network
     * @param parameters
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Tag> getTags(String network, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Update tag in network (rename tag)
     * 
     * @param network
     * @param tagId - Existing tag
     * @param tag - new tag name
     * @throws RestClientException
     */
    public void updateTag(String network, String tagId, String tag)
        throws RestClientException;


    /**
     * Get Comments for node in network
     * 
     * @param network
     * @param node
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Comment> getComments(String network, String node)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get comments for node in network filtered by parameters
     * 
     * @param network
     * @param node
     * @param parameters
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Comment> getComments(String network, String node, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Create comment on node in network
     * 
     * @param network
     * @param node
     * @param comment
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public Comment createComment(String network, String node, String comment)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Create multiple comments on node in network
     * 
     * @param network
     * @param node
     * @param comments
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Comment> createComments(String network, String node, List<String> comments)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Update comment on node in network
     * 
     * @param network
     * @param node
     * @param commentId - existing commentId
     * @param comment - updated comment
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public void updateComment(String network, String node, String commentId, String comment)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Delete comment from node in network
     * 
     * @param network
     * @param node
     * @param commentId
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public void deleteComment(String network, String node, String commentId)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get tags on node in network
     * 
     * @param network
     * @param node
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Tag> getNodesTags(String network, String node)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get tags on node in network filtered by parameters
     * 
     * @param network
     * @param node
     * @param parameters
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Tag> getNodesTags(String network, String node, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Add tag to node in network
     * 
     * @param network
     * @param node
     * @param tag - tag name
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public Tag addTagToNode(String network, String node, String tag)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Add multiple tags to node
     * 
     * @param network
     * @param node
     * @param tags
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Tag> addTagsToNode(String network, String node, List<String> tags)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Remove Tag from node in network
     * 
     * @param network
     * @param node
     * @param tagId
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public void removeTagFromNode(String network, String node, String tagId)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get ratings for node in network
     * 
     * @param network
     * @param node
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Rating> getNodeRatings(String network, String node)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get ratings for node in network filtered by parameters
     * 
     * @param network
     * @param node
     * @param parameters
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public AlfrescoList<Rating> getNodeRatings(String network, String node, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get rating for node in network
     * 
     * @param network
     * @param node
     * @param rating - Rating must be of type Rating.STARS or Rating.LIKES
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public Rating getNodeRating(String network, String node, String rating)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Remove rating from node in network
     * 
     * @param network
     * @param node
     * @param ratingId
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public void removeNodeRating(String network, String node, String ratingId)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Like node
     * 
     * @param network
     * @param node
     * @param like
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public Rating rateNode(String network, String node, boolean like)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Rate node (Stars)
     * 
     * @param network
     * @param node
     * @param stars - must be 1 - 5 stars
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public Rating rateNode(String network, String node, int stars)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /*
     * public Rating rateNode(String network, String node, String ratingType, Serializable rating) throws JsonParseException,
     * JsonMappingException, IOException;
     */


    /**
     * Get home network for user
     * 
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public Network getHomeNetwork()
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get the current users profile
     * 
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public Person getCurrentUser()
        throws JsonParseException,
            JsonMappingException,
            IOException;


    /**
     * Get the HTTP OPTIONS for the network
     * 
     * @param network
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @Deprecated
    public AlfrescoList<Metadata> networkOptions(String network)
        throws JsonParseException,
            JsonMappingException,
            IOException;
}
