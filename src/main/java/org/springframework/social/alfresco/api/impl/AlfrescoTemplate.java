/**
 * 
 */

package org.springframework.social.alfresco.api.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.social.OperationNotPermittedException;
import org.springframework.social.alfresco.api.Alfresco;
import org.springframework.social.alfresco.api.entities.Activity;
import org.springframework.social.alfresco.api.entities.Comment;
import org.springframework.social.alfresco.api.entities.Container;
import org.springframework.social.alfresco.api.entities.Metadata;
import org.springframework.social.alfresco.api.entities.Pagination;
import org.springframework.social.alfresco.api.entities.Person;
import org.springframework.social.alfresco.api.entities.Preference;
import org.springframework.social.alfresco.api.entities.Member;
import org.springframework.social.alfresco.api.entities.Network;
import org.springframework.social.alfresco.api.entities.Rating;
import org.springframework.social.alfresco.api.entities.Role;
import org.springframework.social.alfresco.api.entities.Site;
import org.springframework.social.alfresco.api.entities.Tag;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.web.client.RestClientException;


/**
 * @author jottley
 * 
 */
public class AlfrescoTemplate
    extends AbstractOAuth2ApiBinding
    implements Alfresco
{
    private static final Log   log     = LogFactory.getLog(AlfrescoTemplate.class);

    private final ObjectMapper mapper  = new ObjectMapper();
    private final HttpHeaders  headers = new HttpHeaders();


    public AlfrescoTemplate(String accessToken)
    {
        super(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
    }


    public Response<Network> getNetwork(String network)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = Collections.singletonMap(TemplateParams.NETWORK, network);
        String response = getRestTemplate().getForObject(NETWORK_URL, String.class, vars);
        log.debug("getNetwork: " + response);
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
        log.debug("getNetworks: " + response);
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
        log.debug("getSite: " + response);
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
        log.debug("getSites: " + response);
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
        log.debug("getContainer: " + response);
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
        log.debug("getContainers: " + response);
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
        log.debug("getMember: " + response);
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
        log.debug("getMembers: " + response);
        return mapper.readValue(response, entryResponseType(Member.class));
    }


    public Response<Member> addMember(String network, String site, String personId, Role role)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.SITE, site);

        Member member = new Member();
        member.setId(personId);
        member.setRole(role);

        String response = getRestTemplate().postForObject(MEMBERS_URL, new HttpEntity<Member>(member, headers), String.class, vars);
        log.debug("addMember: " + response);
        return mapper.readValue(response, entryResponseType(Member.class));
    }


    // TODO should this make the additional call to get the updated entity or just move forward
    public void updateMember(String network, String site, String personId, Role role)
        throws RestClientException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.SITE, site);
        vars.put(TemplateParams.MEMBER, personId);

        Member member = new Member();
        member.setRole(role);

        getRestTemplate().put(MEMBER_URL, new HttpEntity<Member>(member, headers), vars);
        log.debug("updateMember: member: " + personId + " to Role: " + role);

    }


    // TODO should this make the additional call to get the updated entity or just move forward
    public void deleteMember(String network, String site, String personId)
        throws RestClientException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.SITE, site);
        vars.put(TemplateParams.MEMBER, personId);

        getRestTemplate().delete(MEMBER_URL, vars);
        log.debug("deleteMember: " + personId + " from site: " + site);

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
        log.debug("getPerson: " + response);
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
        log.debug("getSites: " + response);
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
        log.debug("getSite: " + response);
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
        log.debug("getFavoriteSites: " + response);
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
        log.debug("getPreference: " + response);
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
        log.debug("getPreferences: " + response);
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
        log.debug("getNetwork: " + response);
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
        log.debug("getNetworks: " + response);
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
        log.debug("getActivities: " + response);
        return mapper.readValue(response, entryResponseType(Activity.class));
    }


    public Tag getTag(String network, String tag)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        int count = 0;
        final int MAXITEMS = 10;

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put(Pagination.MAXITEMS, Integer.toString(MAXITEMS));

        boolean found = false;
        Tag tagFound = null;

        Response<Tag> response = getTags(network, parameters);
        while (!found)
        {
            for (Iterator<Tag> iterator = response.getList().getEntries().iterator(); iterator.hasNext();)
            {
                Tag _tag = iterator.next();
                if (_tag.getTag().equals(tag))
                {
                    tagFound = _tag;
                    found = true;
                    break;
                }
                else
                {
                    if (!iterator.hasNext())
                    {
                        if (response.getList().getPagination().isHasMoreItems())
                        {
                            parameters.put(Pagination.SKIPCOUNT, Integer.toString(count = count + MAXITEMS));
                            response = getTags(network, parameters);
                        }
                        else
                        {
                            found = true;
                            break;
                        }
                    }
                }
            }
        }
        return tagFound;
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
        log.debug("getTags: " + response);
        return mapper.readValue(response, entryResponseType(Tag.class));
    }


    public void updateTag(String network, String tagId, String tag)
        throws RestClientException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.TAG, tagId);

        Tag _tag = new Tag();
        _tag.setTag(tag);

        getRestTemplate().put(TAG_URL, new HttpEntity<Tag>(_tag, headers), vars);
        log.debug("updateTag: " + tag);

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
        log.debug("getComments: " + response);
        return mapper.readValue(response, entryResponseType(Comment.class));
    }


    public Response<Comment> createComment(String network, String node, String comment)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.NODE, node);

        Comment _comment = new Comment();
        _comment.setContent(comment);

        String response = getRestTemplate().postForObject(NODE_COMMENTS_URL, new HttpEntity<Comment>(_comment, headers), String.class, vars);
        log.debug("createComment: " + response);
        return mapper.readValue(response, entryResponseType(Comment.class));

    }


    public Response<Comment> createComments(String network, String node, List<String> comments)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.NODE, node);

        List<Comment> _comments = new ArrayList<Comment>();
        for (String content : comments)
        {
            Comment _comment = new Comment();
            _comment.setContent(content);
            _comments.add(_comment);
        }

        String response = getRestTemplate().postForObject(NODE_COMMENTS_URL, new HttpEntity<List<Comment>>(_comments, headers), String.class, vars);
        log.debug("createComments: " + response);
        return mapper.readValue(response, entryResponseType(Comment.class));
    }


    public void updateComment(String network, String node, String commentId, String comment)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.NODE, node);
        vars.put(TemplateParams.COMMENT, commentId);

        Comment _comment = new Comment();
        _comment.setContent(comment);

        getRestTemplate().put(NODE_COMMENT_URL, new HttpEntity<Comment>(_comment, headers), vars);
        log.debug("updateComment: " + comment);
    }


    public void deleteComment(String network, String node, String commentId)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.NODE, node);
        vars.put(TemplateParams.COMMENT, commentId);

        getRestTemplate().delete(NODE_COMMENT_URL, vars);
        log.debug("deleteComment: " + commentId);
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
        log.debug("getNodeTafs: " + response);
        return mapper.readValue(response, entryResponseType(Tag.class));
    }


    public Response<Tag> addTagToNode(String network, String node, String tag)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.NODE, node);

        Tag _tag = new Tag();
        _tag.setTag(tag);

        String response = getRestTemplate().postForObject(NODE_TAG_URL, new HttpEntity<Tag>(_tag, headers), String.class, vars);
        log.debug("addTagToNode: " + response);
        return mapper.readValue(response, entryResponseType(Tag.class));
    }


    public Response<Tag> addTagsToNode(String network, String node, List<String> tags)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.NODE, node);

        List<Tag> _tags = new ArrayList<Tag>();
        for (String tag : tags)
        {
            Tag _tag = new Tag();
            _tag.setTag(tag);
            _tags.add(_tag);
        }

        String response = getRestTemplate().postForObject(NODE_TAGS_URL, new HttpEntity<List<Tag>>(_tags, headers), String.class, vars);
        log.debug("addTagsToNode: " + response);
        return mapper.readValue(response, entryResponseType(Tag.class));
    }


    public void removeTagFromNode(String network, String node, String tagId)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.NODE, node);
        vars.put(TemplateParams.TAG, tagId);

        getRestTemplate().delete(NODE_TAG_URL, vars);
        log.debug("removeTagFromNode: " + tagId);
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

        String response = getRestTemplate().getForObject(NODE_RATINGS_URL + generateQueryString(parameters), String.class, vars);
        log.debug("getNodeRatings: " + response);
        return mapper.readValue(response, entryResponseType(Rating.class));
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

        String response = getRestTemplate().getForObject(NODE_RATING_URL, String.class, vars);
        log.debug("getNodeRatings: " + response);
        return mapper.readValue(response, entryResponseType(Rating.class));
    }


    public void removeNodeRating(String network, String node, String ratingId)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.NODE, node);
        vars.put(TemplateParams.RATING, ratingId);

        getRestTemplate().delete(NODE_RATING_URL, vars);
        log.debug("removeNodeRating: " + ratingId);
    }


    public Response<Rating> rateNode(String network, String node, String ratingType, String rating)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put(TemplateParams.NETWORK, network);
        vars.put(TemplateParams.NODE, node);

        Rating _rating = new Rating();
        _rating.setId(ratingType);
        _rating.setMyRating(rating);

        String response = getRestTemplate().postForObject(NODE_RATINGS_URL, new HttpEntity<Rating>(_rating, headers), String.class, vars);
        log.debug("rateNode: " + response);
        return mapper.readValue(response, entryResponseType(Rating.class));
    }


    private JavaType entryResponseType(Class<?> type)
    {
        return mapper.getTypeFactory().constructParametricType(Response.class, type);
    }


    public Network getHomeNetwork()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Network homeNetwork = null;
        Response<Network> response = getNetworks();

        for (Iterator<Network> iterator = response.getList().getEntries().iterator(); iterator.hasNext();)
        {
            Network network = iterator.next();

            if (network.isHomeNetwork())
                homeNetwork = network;
        }
        return homeNetwork;
    }


    /**
     * Not Implemented yet
     */
    @Deprecated
    public Response<Metadata> networkOptions(String network)
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        throw new OperationNotPermittedException("HTTP OPTIONS Not implemented yet");
        // Map<String, String> vars = Collections.singletonMap(TemplateParams.NETWORK, network);
        // ResponseEntity<String> response =
        // getRestTemplate().exchange("https://api.alfresco.com/{network}/public/alfresco/versions/1/nodes", HttpMethod.OPTIONS,
        // null, String.class, vars);
        // System.out.println("Network Options: " + response.getBody());
        // return mapper.readValue(response.getBody(), entryResponseType(Metadata.class));

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

        log.debug("queryString: " + queryString.toString());
        return queryString.toString();
    }
}
