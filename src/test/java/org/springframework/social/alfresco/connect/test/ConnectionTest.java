
package org.springframework.social.alfresco.connect.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.RewriteHandler;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.social.alfresco.api.Alfresco;
import org.springframework.social.alfresco.api.entities.Activity;
import org.springframework.social.alfresco.api.entities.Comment;
import org.springframework.social.alfresco.api.entities.Member;
import org.springframework.social.alfresco.api.entities.Network;
import org.springframework.social.alfresco.api.entities.Pagination;
import org.springframework.social.alfresco.api.entities.Role;
import org.springframework.social.alfresco.api.entities.Tag;
import org.springframework.social.alfresco.api.impl.Response;
import org.springframework.social.alfresco.connect.AlfrescoConnectionFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Parameters;


/**
 * 
 * @author jottley
 */
public class ConnectionTest
{

    private static final String              CONSUMER_KEY    = "l7xx16247a05ab7b46968625d4dda1f45aeb";
    private static final String              CONSUMER_SECRET = "3af3780039de4da5892519d2d6d856b9";

    private static final String              REDIRECT_URI    = "http://localhost:9876";
    private static final String              STATE           = "test";

    private static AlfrescoConnectionFactory connectionFactory;
    private static AuthUrl                   authUrlObject;

    private static Alfresco                  alfresco;

    private static Server                    server;


    private static final String              network         = "alfresco.com";
    private static final String              person          = "jared.ottley@alfresco.com";
    private static final String              site            = "spring-social-alfresco";
    private static final String              container       = "documentLibrary";
    private static final String              preference      = "org.alfresco.share.siteWelcome.spring-social-alfresco";
    private static final String              node            = "8c368b84-4a88-4d62-9e7e-8e7eabe39969";
    private static final String              rating          = "likes";


    @BeforeClass
    public static void setUp()
        throws Exception
    {
        Properties properties = new Properties();
        properties.load(new FileReader(new File("src/test/resources/connectionTest.properties")));

        setupServer();

        authenticate();

        GetAPI(properties.getProperty("username"), properties.getProperty("password"));
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
    public void getHomeNetwork()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Network homeNetwork = alfresco.getHomeNetwork();

        assertEquals(network, homeNetwork.getId());
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
    public void memberOperations()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        String pmonks = "pmonks@alfresco.com";

        Response<Member> member = alfresco.addMember(network, site, pmonks, Role.SiteConsumer);

        assertNotNull(member);
        assertEquals(pmonks, member.getEntry().getId());
        assertEquals(Role.SiteConsumer, member.getEntry().getRole());

        alfresco.updateMember(network, site, pmonks, Role.SiteContributor);
        member = alfresco.getMember(network, site, pmonks);

        assertEquals(Role.SiteContributor, member.getEntry().getRole());

        alfresco.deleteMember(network, site, pmonks);

        member = alfresco.getMember(network, site, pmonks);

        assertNull(member);
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
    public void getNamedTag()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Tag tag = alfresco.getTag(network, "test");

        assertEquals("test", tag.getTag());
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
        parameters.put(Alfresco.QueryParams.PROPERTIES, "tag");

        Response<Tag> response = alfresco.getTags(network, parameters);

        assertNull(response.getList().getEntries().get(0).getId());
    }


    @Test
    public void updateTag()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Tag tag = alfresco.getTag(network, "spring-social-alfresco");

        alfresco.updateTag(network, tag.getId(), "spring-social-alfresco-test");

        tag = alfresco.getTag(network, "spring-social-alfresco-test");

        assertNotNull(tag);

        alfresco.updateTag(network, tag.getId(), "spring-social-alfresco");
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
    public void commentOperations()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        String commentId = null;
        String firstComment = "This is a comment created by spring-social-alfresco";
        String updatedComment = "This is an updated comment";

        Response<Comment> comment = alfresco.createComment(network, node, firstComment);

        assertEquals(firstComment, comment.getEntry().getContent());
        commentId = comment.getEntry().getId();

        alfresco.updateComment(network, node, commentId, updatedComment);

        // TODO Do we need an individual get Comment?

        alfresco.deleteComment(network, node, commentId);

        // TODO check to see if comment is there


    }


    @Test
    public void commentOperations2()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        List<String> comments = new ArrayList<String>();
        comments.add("This is comment 1");
        comments.add("This is comment 2");

        Response<Comment> c = alfresco.createComments(network, node, comments);

        for (Comment comment : c.getList().getEntries())
        {
            alfresco.deleteComment(network, node, comment.getId());
        }
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
    public void nodeTagOperations()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Response<Tag> tag = alfresco.addTagToNode(network, node, "test");

        assertEquals("test", tag.getEntry().getTag());

        alfresco.removeTagFromNode(network, node, tag.getEntry().getId());

        // TODO test tag was removed from node
    }


    @Test
    public void nodeTagOperations2()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        List<String> tags = new ArrayList<String>();
        tags.add("test1");
        tags.add("test2");

        Response<Tag> t = alfresco.addTagsToNode(network, node, tags);

        for (Tag tag : t.getList().getEntries())
        {
            alfresco.removeTagFromNode(network, node, tag.getId());
        }
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
    public void rateNode()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.rateNode(network, node, rating, "true");
    }


    @Test
    public void removeRating()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.removeNodeRating(network, node, rating);
    }


    // =================================================//

    private static void setupServer()
        throws Exception
    {
        server = new Server(9876);
        server.setHandler(new RewriteHandler());
        server.start();
    }


    private static void authenticate()
        throws MalformedURLException
    {
        connectionFactory = new AlfrescoConnectionFactory(CONSUMER_KEY, CONSUMER_SECRET);

        OAuth2Parameters parameters = new OAuth2Parameters();
        parameters.setRedirectUri(REDIRECT_URI);
        parameters.setScope(Alfresco.DEFAULT_SCOPE);
        parameters.setState(STATE);

        authUrlObject = new AuthUrl(connectionFactory.getOAuthOperations().buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, parameters));
    }


    private static void GetAPI(String username, String password)
        throws IOException
    {
        HtmlUnitDriver driver = new HtmlUnitDriver();
        driver.get(authUrlObject.toString());

        List<WebElement> webElements = driver.findElementsByTagName("form");

        WebElement usernameElement = driver.findElementById("username");
        usernameElement.sendKeys(username);
        WebElement passwordElement = driver.findElementById("password");
        passwordElement.sendKeys(password);
        webElements.get(0).submit();

        CodeUrl codeUrl = new CodeUrl(driver.getCurrentUrl());

        AccessGrant accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(codeUrl.getQueryMap().get(CodeUrl.CODE), REDIRECT_URI, null);

        Connection<Alfresco> connection = connectionFactory.createConnection(accessGrant);
        alfresco = connection.getApi();
    }
}
