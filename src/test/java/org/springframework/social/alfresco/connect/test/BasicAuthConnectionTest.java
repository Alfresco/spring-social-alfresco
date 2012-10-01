
package org.springframework.social.alfresco.connect.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;
import org.springframework.social.alfresco.api.Alfresco;
import org.springframework.social.alfresco.api.entities.Activity;
import org.springframework.social.alfresco.api.entities.Comment;
import org.springframework.social.alfresco.api.entities.Member;
import org.springframework.social.alfresco.api.entities.Network;
import org.springframework.social.alfresco.api.entities.Pagination;
import org.springframework.social.alfresco.api.entities.Role;
import org.springframework.social.alfresco.api.entities.Tag;
import org.springframework.social.alfresco.api.impl.AlfrescoTemplate;
import org.springframework.social.alfresco.api.impl.BasicAuthAlfrescoTemplate;
import org.springframework.social.alfresco.api.impl.Response;
import org.springframework.web.client.RestClientException;


/**
 * 
 * @author jottley
 * 
 */
public class BasicAuthConnectionTest
{
    private static Alfresco                  alfresco;


    private static final String              testTag = "testtag0";
    private static final String              testTag1 = "newtesttag0";

    private static final String              network         = "alfresco.com";
    private static final String              person          = "steven.glover@alfresco.com";
    private static final String              memberId          = "david.bowie@alfresco.com";
    private static final String              site            = "steven-glover-alfresco-com";
    private static final String              container       = "documentLibrary";
    private static final String              preference      = "org.alfresco.share.siteWelcome.spring-social-alfresco";
    private static final String              node            = "59372f48-0a18-49f2-a559-8659b697427c";
    private static final String              rating          = "likes";

    private static String              commentId       = null;


    @Test
    public void GetAPI()
        throws IOException
    {
        alfresco = new BasicAuthAlfrescoTemplate();
    }

    @Test
    public void getNetwork()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.getNetwork(network);
    }


    @Test
    public void getNetworks()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.getNetworks();
    }


    @Test
    public void getSite()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.getSite(site, network);
    }


    @Test
    public void getSites()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.getSites(network);

    }


    @Test
    public void getContainer()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.getContainer(network, site, container);
    }


    @Test
    public void getContainers()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.getContainers(network, site);
    }


    @Test
    public void getMember()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.getMember(network, site, person);
    }


    @Test
    public void getMembers()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.getMembers(network, site);
    }


    @Test
    public void addMember()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.addMember(network, site, memberId, Role.SiteConsumer);
    }


    @Test
    public void updateMember()
        throws RestClientException,
            JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.updateMember(network, site, memberId, Role.SiteContributor);
        Response<Member> member = alfresco.getMember(network, site, memberId);

        assertEquals(Role.SiteContributor, member.getEntry().getRole());
    }


    @Test
    public void deleteMember()
        throws RestClientException
    {
        alfresco.deleteMember(network, site, memberId);
    }


    @Test
    public void getPerson()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.getPerson(network, person);
    }


    @Test
    public void getPersonSites()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.getSites(network, person);
    }


    @Test
    public void getPersonSite()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.getSite(network, person, site);
    }


    @Test
    public void getFavoriteSites()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.getFavoriteSites(network, person);
    }


    @Test
    public void getPreference()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.getPreference(network, person, preference);
    }


    @Test
    public void getPreferences()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.getPreferences(network, person);
    }


    @Test
    public void getPersonNetwork()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.getNetwork(network, person);
    }


    @Test
    public void getPersonNetworks()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.getNetworks(network, person);
    }


    @Test
    public void getActivities()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> parameters = null; // alfresco.getActivities(network, person, parameters);

        parameters = new HashMap<String, String>(); // alfresco.getActivities(network, person, parameters);

        parameters.put(Activity.SITEID, site);
        alfresco.getActivities(network, person, parameters);

        parameters.put(Activity.WHO, Activity.Who.me.toString());
        alfresco.getActivities(network, person, parameters);

        parameters = new HashMap<String, String>();
        parameters.put(Activity.SITEID, site);
        parameters.put(Activity.WHO, Activity.Who.others.toString());
        alfresco.getActivities(network, person, parameters);

        parameters = new HashMap<String, String>();
        parameters.put(Activity.WHO, Activity.Who.me.toString());
        alfresco.getActivities(network, person, parameters);

        parameters = new HashMap<String, String>();
        parameters.put(Activity.WHO, Activity.Who.others.toString());
        alfresco.getActivities(network, person, parameters);
    }

    @Test
    public void addNodeTag()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.addTagToNode(network, node, testTag);
    }

    @Test
    public void getNamedTag()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Tag tag = alfresco.getTag(network, testTag);

        assertEquals(testTag, tag.getTag());
    }


    @Test
    public void getTags()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.getTags(network);
    }


    @Test
    public void updateTag()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Tag tag = alfresco.getTag(network, testTag);

        alfresco.updateTag(network, tag.getId(), testTag1);

        tag = alfresco.getTag(network, testTag1);

        assertNotNull(tag);

        //alfresco.updateTag(network, tag.getId(), "spring-social-alfresco");
    }


    @Test
    public void getComments()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.getComments(network, node);
    }


    @Test
    public void createComment()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Response<Comment> comment = alfresco.createComment(network, node, "This is a comment created by spring-social-alfresco");

        commentId = comment.getEntry().getId();
    }


    @Test
    public void createComments()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        List<String> comments = new ArrayList<String>();
        comments.add("This is comment 1");
        comments.add("This is comment 2");

        alfresco.createComments(network, node, comments);
    }
    
    @Test
    public void updateComment()
        throws JsonParseException, JsonMappingException, IOException{
        alfresco.updateComment(network, node, commentId, "This is an updated comment");
    }
    
    @Test
    public void deleteComment()
        throws JsonParseException, JsonMappingException, IOException{
        alfresco.deleteComment(network, node, commentId);
    }


    @Test
    public void getNodeTags()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.getNodesTags(network, node);
    }


    @Test
    public void getNodeRating()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.getNodeRating(network, node, rating);
    }


    @Test
    public void getNodeRatings()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.getNodeRatings(network, node);
    }


    @Test
    public void getAlotOfTags()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put(Pagination.MAXITEMS, "300");

        Response<Tag> response = alfresco.getTags(network, parameters);

        assertEquals(300, response.getList().getPagination().getCount());
    }


    @Test
    public void getTagsNoIds()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put(AlfrescoTemplate.QueryParams.PROPERTIES, "tag");

        Response<Tag> response = alfresco.getTags(network, parameters);

        assertNull(response.getList().getEntries().get(0).getId());
    }


    @Test
    public void getHomeNetwork()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Network homeNetwork = alfresco.getHomeNetwork();

        assertEquals(network, homeNetwork.getId());
    }
}
