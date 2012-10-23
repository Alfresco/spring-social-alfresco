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


    public Session getCMISSession(String network);


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
            IOException;;
}
