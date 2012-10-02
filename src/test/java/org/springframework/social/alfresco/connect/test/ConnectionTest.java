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
import java.util.Map;
import java.util.Properties;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Session;
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
import org.springframework.social.alfresco.api.entities.List;
import org.springframework.social.alfresco.api.entities.Member;
import org.springframework.social.alfresco.api.entities.Network;
import org.springframework.social.alfresco.api.entities.Pagination;
import org.springframework.social.alfresco.api.entities.Role;
import org.springframework.social.alfresco.api.entities.Tag;
import org.springframework.social.alfresco.connect.AlfrescoConnectionFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.web.client.RestClientException;

/**
 * 
 * @author jottley
 */
public class ConnectionTest
{
    private static final String              CONSUMER_KEY    = "l7xx2cda1272414b4739b972bbf874eb40ae";
    private static final String              CONSUMER_SECRET = "43828d1d77d245c482f9477d29affa95";

    private static final String              REDIRECT_URI    = "http://localhost:8181/oauthsample/mycallback.html";

    private static final String              STATE           = "test";

    private static AlfrescoConnectionFactory connectionFactory;
    private static AuthUrl                   authUrlObject;
    private static AccessGrant               accessGrant;

    private static Alfresco                  alfresco;

    private static Server                    server;


    private static String              testTag         = "testtag0";
    private static String              testTag1        = "newtesttag0";

    private static String              network         = "alfresco.com";
    private static String              person          = "jared.ottley@alfresco.com";
    private static String              memberId        = "peter.monks@alfresco.com";
    private static String              site            = "spring-social-alfresco";
    private static String              container       = "documentLibrary";
    private static String              preference      = "org.alfresco.share.siteWelcome.spring-social-alfresco";
    private static String              node            = "59372f48-0a18-49f2-a559-8659b697427c";
    private static String              rating          = "likes";
    private static String 			   objectPath;
    
    private static final String 			 baseUrl         = "https://api.alfresco.com/";
    private static final String 			 baseAuthUrl     = baseUrl + "auth/oauth/versions/2/authorize";
    private static final String 			 tokenUrl        = baseUrl + "auth/oauth/versions/2/token";

//    @Test
//    public void test()
//    {
//        connectionFactory = new AlfrescoConnectionFactory(baseUrl, baseAuthUrl, tokenUrl, CONSUMER_KEY, CONSUMER_SECRET);
//
//        assertEquals("alfresco", connectionFactory.getProviderId());
//    }

    public static String getPropertyValue(Properties properties, String propertyName, String defaultValue)
    {
        String ret = (String)properties.getProperty(propertyName);
        if(ret == null || ret.equals(""))
        {
        	ret = defaultValue;
        }
        return ret;
    }

    @BeforeClass
    public static void setUp()
        throws Exception
    {
        Properties properties = new Properties();
        properties.load(new FileReader(new File("src/test/resources/connectionTest.properties")));

        setupServer();

        authenticate();

        // overrides
        node = getPropertyValue(properties, "node", node);
        testTag = getPropertyValue(properties, "testTag", testTag);
        testTag1 = getPropertyValue(properties, "testTag1", testTag1);
        memberId = getPropertyValue(properties, "memberId", memberId);
        person = getPropertyValue(properties, "person", person);
        testTag = getPropertyValue(properties, "testTag", testTag);
        site = getPropertyValue(properties, "site", site);
        container = getPropertyValue(properties, "container", container);
        preference = getPropertyValue(properties, "preference", preference);
        rating = getPropertyValue(properties, "rating", rating);
        objectPath = "/Sites/" + site + "/documentLibrary/ReadMe - Alfresco in the Cloud.pdf";

        GetAPI(properties.getProperty("username"), properties.getProperty("password"));
    }


    @Test
    public void getNetwork()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
//<<<<<<< HEAD
//        // Wait for the authorization code
//        System.out.println("Type the code you received here: ");
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        String authCode = null;
//
//        authCode = in.readLine();
//
//        AccessGrant accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(authCode, REDIRECT_URI, null);
//
//        System.out.println("Access: " + accessGrant.getAccessToken() + " Refresh: " + accessGrant.getRefreshToken() + " Scope: "
//                           + accessGrant.getScope() + " expires: " + accessGrant.getExpireTime());
//
//        Connection<Alfresco> connection = connectionFactory.createConnection(accessGrant);
//        alfresco = connection.getApi();
//
//=======
//>>>>>>> 118beb307d59438b14612b6ca1bbf30c5b46b806

        alfresco.getNetwork(network);
    }
    
    @Test
    public void CMIS()
    		throws JsonParseException,
    		JsonMappingException,
    		IOException {
    	Session session = alfresco.getCMISSession(network);
		Document doc = (Document) session.getObjectByPath(objectPath);
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
    public void addMember()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Member member = alfresco.addMember(network, site, memberId, Role.SiteConsumer);
    }

    @Test
    public void updateMember()
        throws RestClientException,
            JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.updateMember(network, site, memberId, Role.SiteContributor);
        Member member = alfresco.getMember(network, site, memberId);

        assertNotNull(member);
        assertEquals(memberId, member.getId());
        assertEquals(Role.SiteContributor, member.getRole());
    }

    @Test
    public void deleteMember()
        throws RestClientException,
        JsonParseException,
        JsonMappingException,
        IOException
    {
        alfresco.deleteMember(network, site, memberId);

        Member member = alfresco.getMember(network, site, memberId);

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
    public void getAlotOfTags()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put(Pagination.MAXITEMS, "300");

        List<Tag> response = alfresco.getTags(network, parameters);

        assertEquals(300, response.getPagination().getCount());
    }


    @Test
    public void getTagsNoIds()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put(Alfresco.QueryParams.PROPERTIES, "tag");

        List<Tag> response = alfresco.getTags(network, parameters);

        assertNull(response.getEntries().get(0).getId());
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

        Comment comment = alfresco.createComment(network, node, firstComment);

        assertEquals(firstComment, comment.getContent());
        commentId = comment.getId();

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
        java.util.List<String> comments = new ArrayList<String>();
        comments.add("This is comment 1");
        comments.add("This is comment 2");

        List<Comment> c = alfresco.createComments(network, node, comments);

        for (Comment comment : c.getEntries())
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
        Tag tag = alfresco.addTagToNode(network, node, "test");

        assertEquals("test", tag.getTag());

        alfresco.removeTagFromNode(network, node, tag.getId());

        // TODO test tag was removed from node
    }


    @Test
    public void nodeTagOperations2()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        java.util.List<String> tags = new ArrayList<String>();
        tags.add("test1");
        tags.add("test2");

        List<Tag> t = alfresco.addTagsToNode(network, node, tags);

        for (Tag tag : t.getEntries())
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


    @Test
    public void refreshTicket()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        // Refresh AccessGrant & Connection
        accessGrant = connectionFactory.getOAuthOperations().refreshAccess(accessGrant.getRefreshToken(), Alfresco.DEFAULT_SCOPE, null);
        alfresco = connectionFactory.createConnection(accessGrant).getApi();

        // quickTests
        getNetwork();
        getSite();
        // getContainer();
        // getActivities();
        getHomeNetwork();

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
        connectionFactory = new AlfrescoConnectionFactory(baseUrl, baseAuthUrl, tokenUrl, CONSUMER_KEY, CONSUMER_SECRET);
//        connectionFactory = new AlfrescoConnectionFactory(baseUrl, CONSUMER_KEY, CONSUMER_SECRET);

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

        java.util.List<WebElement> webElements = driver.findElementsByTagName("form");

        WebElement usernameElement = driver.findElementById("username");
        usernameElement.sendKeys(username);
        WebElement passwordElement = driver.findElementById("password");
        passwordElement.sendKeys(password);
        webElements.get(0).submit();

        CodeUrl codeUrl = new CodeUrl(driver.getCurrentUrl());

        accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(codeUrl.getQueryMap().get(CodeUrl.CODE), REDIRECT_URI, null);

        Connection<Alfresco> connection = connectionFactory.createConnection(accessGrant);
        alfresco = connection.getApi();
    }

}
