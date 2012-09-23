
package org.springframework.social.alfresco.api;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.social.alfresco.api.entities.Activity;
import org.springframework.social.alfresco.api.entities.Comment;
import org.springframework.social.alfresco.api.entities.Container;
import org.springframework.social.alfresco.api.entities.Metadata;
import org.springframework.social.alfresco.api.entities.Person;
import org.springframework.social.alfresco.api.entities.Preference;
import org.springframework.social.alfresco.api.entities.Member;
import org.springframework.social.alfresco.api.entities.Network;
import org.springframework.social.alfresco.api.entities.Rating;
import org.springframework.social.alfresco.api.entities.Role;
import org.springframework.social.alfresco.api.entities.Site;
import org.springframework.social.alfresco.api.entities.Tag;
import org.springframework.social.alfresco.api.impl.Response;
import org.springframework.web.client.RestClientException;


public interface Alfresco
{

    public Response<Network> getNetwork(String network)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Network> getNetworks()
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Network> getNetworks(Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Site> getSite(String site, String network)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Site> getSites(String network)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Site> getSites(String network, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Container> getContainer(String network, String site, String contatiner)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Container> getContainers(String network, String site)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Container> getContainers(String network, String site, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Member> getMember(String network, String site, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Member> getMembers(String network, String site)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Member> getMembers(String network, String site, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Member> addMember(String network, String site, String personId, Role role)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public void updateMember(String network, String site, String personId, Role role)
        throws RestClientException;


    public void deleteMember(String network, String site, String personId)
        throws RestClientException;


    public Response<Person> getPerson(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Site> getSites(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Site> getSites(String network, String person, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Site> getSite(String network, String person, String site)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Site> getFavoriteSites(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Site> getFavoriteSites(String network, String person, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Preference> getPreference(String network, String person, String preference)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Preference> getPreferences(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Preference> getPreferences(String network, String person, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Network> getNetwork(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Network> getNetworks(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Network> getNetworks(String network, String person, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Activity> getActivities(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Activity> getActivities(String network, String person, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Tag> getTags(String network)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Tag> getTags(String network, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Tag> updateTag(String network, String tagId, Tag tag)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Comment> getComments(String network, String node)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Comment> getComments(String network, String node, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Comment> createComment(String network, String node, Comment comment)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Comment> createComments(String network, String node, List<Comment> comments)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Comment> updateComment(String network, String node, String commentId, Comment comment)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public boolean deleteComment(String network, String node, String commentId)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Tag> getNodesTags(String network, String node)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Tag> getNodesTags(String network, String node, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Tag> addTagToNode(String network, String node, Tag tag)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Tag> addTagsToNode(String network, String node, List<Tag> tags)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public boolean removeTagFromNode(String network, String node, String tag)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Rating> getNodeRatings(String network, String node)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Rating> getNodeRatings(String network, String node, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Rating> getNodeRating(String network, String node, String rating)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public boolean removeNodeRating(String network, String node, String rating)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Rating> rateNode(String network, String node, Rating rating)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Network getHomeNetwork()
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Response<Metadata> networkOptions(String network)
        throws JsonParseException,
            JsonMappingException,
            IOException;;
}
