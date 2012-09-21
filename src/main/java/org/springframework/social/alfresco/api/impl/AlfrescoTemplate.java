/**
 * 
 */

package org.springframework.social.alfresco.api.impl;


import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.social.alfresco.api.Alfresco;
import org.springframework.social.alfresco.api.entities.Activity;
import org.springframework.social.alfresco.api.entities.Comment;
import org.springframework.social.alfresco.api.entities.Container;
import org.springframework.social.alfresco.api.entities.Person;
import org.springframework.social.alfresco.api.entities.Preference;
import org.springframework.social.alfresco.api.entities.Member;
import org.springframework.social.alfresco.api.entities.Network;
import org.springframework.social.alfresco.api.entities.Rating;
import org.springframework.social.alfresco.api.entities.Site;
import org.springframework.social.alfresco.api.entities.Tag;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;


/**
 * @author jottley
 * 
 */
public class AlfrescoTemplate
    extends AbstractOAuth2ApiBinding
    implements Alfresco
{

    private final ObjectMapper mapper = new ObjectMapper();


    public AlfrescoTemplate(String accessToken)
    {
        super(accessToken);
    }


    public Response<Network> getNetwork(String network)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = Collections.singletonMap(TemplateParams.NETWORK, network);
        String response = getRestTemplate().getForObject(NETWORK_URL, String.class, vars);
        return mapper.readValue(response, entryResponseType(Network.class));


    }


    public Response<Network> getNetworks()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        return getNetworks(null);
    }


    public Response<Network> getNetworks(Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        String response = getRestTemplate().getForObject(NETWORKS_URL + generateQueryString(parameters), String.class);
        return mapper.readValue(response, entryResponseType(Network.class));
    }


    public Response<Site> getSite(String site, String network)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.SITE, site);

        String response = getRestTemplate().getForObject(SITE_URL, String.class, vars);
        return mapper.readValue(response, entryResponseType(Site.class));
    }


    public Response<Site> getSites(String network)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        // Use empty hashmap to avoid ambiguity in method signature with a null
        return getSites(network, new HashMap<String, String>());
    }


    public Response<Site> getSites(String network, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = Collections.singletonMap(TemplateParams.NETWORK + generateQueryString(parameters), network);
        String response = getRestTemplate().getForObject(SITES_URL, String.class, vars);
        return mapper.readValue(response, entryResponseType(Site.class));
    }


    public Response<Container> getContainer(String network, String site, String contatiner)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.SITE, site);
        vars.put(TemplateParams.CONTAINER, contatiner);

        String response = getRestTemplate().getForObject(CONTAINER_URL, String.class, vars);
        return mapper.readValue(response, entryResponseType(Container.class));
    }


    public Response<Container> getContainers(String network, String site)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        return getContainers(network, site, null);
    }


    public Response<Container> getContainers(String network, String site, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.SITE, site);

        String response = getRestTemplate().getForObject(CONTAINERS_URL + generateQueryString(parameters), String.class, vars);
        return mapper.readValue(response, entryResponseType(Container.class));
    }


    public Response<Member> getMember(String network, String site, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.SITE, site);
        vars.put(TemplateParams.MEMBER, person);

        String response = getRestTemplate().getForObject(MEMBER_URL, String.class, vars);
        System.out.println("getMemember URL: " + MEMBER_URL);
        System.out.println("Member: " + response);
        return mapper.readValue(response, entryResponseType(Member.class));
    }


    public Response<Member> getMembers(String network, String site)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        return getMembers(network, site, null);
    }


    public Response<Member> getMembers(String network, String site, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.SITE, site);

        String response = getRestTemplate().getForObject(MEMBERS_URL + generateQueryString(parameters), String.class, vars);
        return mapper.readValue(response, entryResponseType(Member.class));
    }


    public Response<Member> createMember(String network, String site, Member member)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        // TODO Auto-generated method stub
        return null;
    }


    public Response<Member> updateMember(String network, String site, Member member)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        // TODO Auto-generated method stub
        return null;
    }


    public boolean deleteMember(String network, String site, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        // TODO Auto-generated method stub
        return false;
    }


    public Response<Person> getPerson(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.PERSON, person);

        String response = getRestTemplate().getForObject(PEOPLE_URL, String.class, vars);
        System.out.println("Person: " + response);
        return mapper.readValue(response, entryResponseType(Person.class));
    }


    public Response<Site> getSites(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        return getSites(network, person, null);
    }


    public Response<Site> getSites(String network, String person, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.PERSON, person);

        String response = getRestTemplate().getForObject(PEOPLE_SITES_URL + generateQueryString(parameters), String.class, vars);
        System.out.println("Person Sites: " + response);
        return mapper.readValue(response, entryResponseType(Site.class));
    }


    public Response<Site> getSite(String network, String person, String site)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {

        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.PERSON, person);
        vars.put(TemplateParams.SITE, site);

        String response = getRestTemplate().getForObject(PEOPLE_SITE_URL, String.class, vars);
        System.out.println("Person Site: " + response);
        return mapper.readValue(response, entryResponseType(Site.class));
    }


    public Response<Site> getFavoriteSites(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        return getFavoriteSites(network, person, null);
    }


    public Response<Site> getFavoriteSites(String network, String person, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.PERSON, person);

        String response = getRestTemplate().getForObject(PEOPLE_FAVORITE_SITES_URL + generateQueryString(parameters), String.class, vars);
        System.out.println("Favorite Sites: " + response);
        return mapper.readValue(response, entryResponseType(Site.class));
    }


    public Response<Preference> getPreference(String network, String person, String preference)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.PERSON, person);
        vars.put(TemplateParams.PREFERENCE, preference);

        String response = getRestTemplate().getForObject(PEOPLE_PREFERENCE_URL, String.class, vars);
        System.out.println("Preference: " + response);
        return mapper.readValue(response, entryResponseType(Preference.class));
    }


    public Response<Preference> getPreferences(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        return getPreferences(network, person, null);
    }


    public Response<Preference> getPreferences(String network, String person, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.PERSON, person);

        String response = getRestTemplate().getForObject(PEOPLE_PREFERENCES_URL + generateQueryString(parameters), String.class, vars);
        System.out.println("Preferences: " + response);
        return mapper.readValue(response, entryResponseType(Preference.class));
    }


    public Response<Network> getNetwork(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.PERSON, person);

        String response = getRestTemplate().getForObject(PEOPLE_NETWORK_URL, String.class, vars);
        System.out.println("Person Network: " + response);
        return mapper.readValue(response, entryResponseType(Network.class));
    }


    public Response<Network> getNetworks(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        return getNetworks(network, person, null);
    }


    public Response<Network> getNetworks(String network, String person, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.PERSON, person);

        String response = getRestTemplate().getForObject(PEOPLE_NETWORKS_URL + generateQueryString(parameters), String.class, vars);
        System.out.println("Person Networks: " + response);
        return mapper.readValue(response, entryResponseType(Network.class));
    }


    public Response<Activity> getActivities(String network, String person)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        return getActivities(network, person, null);
    }


    public Response<Activity> getActivities(String network, String person, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.PERSON, person);

        String response = getRestTemplate().getForObject(PEOPLE_ACTIVITIES_URL + generateQueryString(parameters), String.class, vars);
        System.out.println("getActivities URL: " + PEOPLE_ACTIVITIES_URL + generateQueryString(parameters));
        System.out.println("activities: " + response);
        return mapper.readValue(response, entryResponseType(Activity.class));
    }


    public Response<Tag> getTags(String network)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        return getTags(network, null);
    }


    public Response<Tag> getTags(String network, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = Collections.singletonMap(TemplateParams.NETWORK, network);

        String response = getRestTemplate().getForObject(TAGS_URL + generateQueryString(parameters), String.class, vars);
        System.out.println("Tags: " + response);
        return mapper.readValue(response, entryResponseType(Tag.class));
    }


    public Response<Tag> updateTag(String network, String tagId, Tag tag)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        // TODO Auto-generated method stub
        return null;
    }


    public Response<Comment> getComments(String network, String node)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        return getComments(network, node, null);
    }


    public Response<Comment> getComments(String network, String node, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.NODE, node);

        String response = getRestTemplate().getForObject(NODE_COMMENTS_URL + generateQueryString(parameters), String.class, vars);
        System.out.println("Comments: " + response);
        return mapper.readValue(response, entryResponseType(Comment.class));
    }


    public Response<Comment> createComment(String network, String node, Comment comment)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        // TODO Auto-generated method stub
        return null;
    }


    public Response<Comment> createComments(String network, String node, List<Comment> comments)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        // TODO Auto-generated method stub
        return null;
    }


    public Response<Comment> updateComment(String network, String node, String commentId, Comment comment)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        // TODO Auto-generated method stub
        return null;
    }


    public boolean deleteComment(String network, String node, String commentId)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        // TODO Auto-generated method stub
        return false;
    }


    public Response<Tag> getNodesTags(String network, String node)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        return getNodesTags(network, node, null);
    }


    public Response<Tag> getNodesTags(String network, String node, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.NODE, node);

        String response = getRestTemplate().getForObject(NODE_TAGS_URL + generateQueryString(parameters), String.class, vars);
        System.out.println("Node Tags: " + response);
        return mapper.readValue(response, entryResponseType(Tag.class));
    }


    public Response<Tag> addTagToNode(String network, String node, Tag tag)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        // TODO Auto-generated method stub
        return null;
    }


    public Response<Tag> addTagsToNode(String network, String node, List<Tag> tags)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        // TODO Auto-generated method stub
        return null;
    }


    public boolean removeTagFromNode(String network, String node, String tag)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        // TODO Auto-generated method stub
        return false;
    }


    public Response<Rating> getNodeRatings(String network, String node)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        return getNodeRatings(network, node, null);
    }


    public Response<Rating> getNodeRatings(String network, String node, Map<String, String> parameters)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.NODE, node);

        String resposne = getRestTemplate().getForObject(NODE_RATINGS_URL + generateQueryString(parameters), String.class, vars);
        System.out.println("Node Ratings: " + resposne);
        return mapper.readValue(resposne, entryResponseType(Rating.class));
    }


    public Response<Rating> getNodeRating(String network, String node, String rating)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.NODE, node);
        vars.put(TemplateParams.RATING, rating);

        String resposne = getRestTemplate().getForObject(NODE_RATING_URL, String.class, vars);
        System.out.println("Node Rating: " + resposne);
        return mapper.readValue(resposne, entryResponseType(Rating.class));
    }


    public boolean removeNodeRating(String network, String node, String rating)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        // TODO Auto-generated method stub
        return false;
    }


    public Response<Rating> rateNode(String network, String node, Rating rating)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        // TODO Auto-generated method stub
        return null;
    }


    private JavaType entryResponseType(Class<?> type)
    {
        return mapper.getTypeFactory().constructParametricType(Response.class, type);
    }


    /**
     * Build QueryString
     * 
     * @param parameters
     * @return
     */
    private String generateQueryString(Map<String, String> parameters)
    {
        StringBuilder queryString = new StringBuilder();
        if (parameters != null && parameters.size() > 0)
        {
            queryString.append("?");

            Iterator<java.util.Map.Entry<String, String>> entries = parameters.entrySet().iterator();
            while (entries.hasNext())
            {
                java.util.Map.Entry<String, String> thisEntry = entries.next();
                queryString.append(thisEntry.getKey()).append("=").append(thisEntry.getValue());
                if (entries.hasNext())
                {
                    queryString.append("&");
                }
            }
        }

        return queryString.toString();
    }


    private static class TemplateParams
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


    private final int    VERSION_NO                = 1;
    private final String VERSION                   = "/public/alfresco/versions/" + VERSION_NO + "/";
    private final String BASE_URL                  = "https://api.alfresco.com/";
    private final String NETWORKS_URL              = BASE_URL;
    private final String NETWORK_URL               = BASE_URL + "{network}" + VERSION + "networks/{network}";
    private final String SITES_URL                 = BASE_URL + "{network}" + VERSION + "sites";
    private final String SITE_URL                  = SITES_URL + "/{site}";
    private final String CONTAINERS_URL            = SITE_URL + "/containers";
    private final String CONTAINER_URL             = CONTAINERS_URL + "/{container}";
    private final String MEMBERS_URL               = SITE_URL + "/members";
    private final String MEMBER_URL                = MEMBERS_URL + "/{member}";
    private final String PEOPLE_URL                = BASE_URL + "{network}" + VERSION + "people/{person}";
    private final String PEOPLE_SITES_URL          = PEOPLE_URL + "/sites";
    private final String PEOPLE_SITE_URL           = PEOPLE_SITES_URL + "/{site}";
    private final String PEOPLE_FAVORITE_SITES_URL = PEOPLE_URL + "/favorite-sites";
    private final String PEOPLE_PREFERENCES_URL    = PEOPLE_URL + "/preferences";
    private final String PEOPLE_PREFERENCE_URL     = PEOPLE_PREFERENCES_URL + "/{preference}";
    private final String PEOPLE_NETWORKS_URL       = PEOPLE_URL + "/networks";
    private final String PEOPLE_NETWORK_URL        = PEOPLE_NETWORKS_URL + "/{network}";
    private final String PEOPLE_ACTIVITIES_URL     = PEOPLE_URL + "/activities";
    private final String TAGS_URL                  = BASE_URL + "{network}" + VERSION + "tags";
    private final String TAG_URL                   = TAGS_URL + "/{tag}";
    private final String BASE_NODE_URL             = BASE_URL + "{network}" + VERSION + "nodes/{node}/";
    private final String NODE_COMMENTS_URL         = BASE_NODE_URL + "comments";
    private final String NODE_COMMENT_URL          = NODE_COMMENTS_URL + "/{comment}";
    private final String NODE_TAGS_URL             = BASE_NODE_URL + "tags";
    private final String NODE_TAG_URL              = NODE_TAGS_URL + "/{tag}";
    private final String NODE_RATINGS_URL          = BASE_NODE_URL + "ratings";
    private final String NODE_RATING_URL           = NODE_RATINGS_URL + "/{rating}";
}
