
package org.springframework.social.alfresco.api;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.social.alfresco.api.entities.Comment;
import org.springframework.social.alfresco.api.entities.Container;
import org.springframework.social.alfresco.api.entities.Entries;
import org.springframework.social.alfresco.api.entities.Entry;
import org.springframework.social.alfresco.api.entities.Member;
import org.springframework.social.alfresco.api.entities.Network;
import org.springframework.social.alfresco.api.entities.NetworkExtended;
import org.springframework.social.alfresco.api.entities.Rating;
import org.springframework.social.alfresco.api.entities.Site;
import org.springframework.social.alfresco.api.entities.Tag;
import org.springframework.social.alfresco.api.entities.people.Activity;
import org.springframework.social.alfresco.api.entities.people.Person;
import org.springframework.social.alfresco.api.entities.people.PersonSite;
import org.springframework.social.alfresco.api.entities.people.Preference;


public interface Alfresco
{

    public Entry<Network> getNetwork(String network)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entries<Network> getNetworks()
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entry<Site> getSite(String network, String site)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entries<Site> getSites(String network)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entry<Container> getContainer(String network, String site, String contatiner)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entries<Container> getContainers(String network, String site)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entry<Member> getMember(String network, String site, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entries<Member> getMembers(String network, String site)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entry<Member> createMember(String network, String site, Member member)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entry<Member> updateMember(String network, String site, Member member)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public boolean deleteMember(String network, String site, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entry<Person> getPerson(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entries<PersonSite> getSites(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entry<PersonSite> getSite(String network, String person, String site)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entries<Site> getFavoriteSites(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entry<Preference> getPreference(String network, String person, String preference)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entries<Preference> getPreferences(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entry<NetworkExtended> getNetwork(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entries<NetworkExtended> getNetworks(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entries<Activity> getActivities(String network, String person, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entries<Tag> getTags(String network)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entry<Tag> updateTag(String network, String tagId, Tag tag)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entries<Comment> getComments(String network, String node)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entry<Comment> createComment(String network, String node, Comment comment)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entries<Comment> createComments(String network, String node, List<Comment> comments)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entry<Comment> updateComment(String network, String node, String commentId, Comment comment)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public boolean deleteComment(String network, String node, String commentId)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entries<Tag> getNodesTags(String network, String node)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entry<Tag> addTagToNode(String network, String node, Tag tag)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entries<Tag> addTagsToNode(String network, String node, List<Tag> tags)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public boolean removeTagFromNode(String network, String node, String tag)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entries<Rating> getNodeRating(String network, String node)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entry<Rating> getNodeRating(String network, String node, String rating)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public boolean removeNodeRating(String network, String node, String rating)
        throws JsonParseException,
            JsonMappingException,
            IOException;


    public Entry<Rating> rateNode(String network, String node, Rating rating)
        throws JsonParseException,
            JsonMappingException,
            IOException;
}
