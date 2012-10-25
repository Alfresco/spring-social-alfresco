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
import org.springframework.social.alfresco.api.entities.AlfrescoList;
import org.springframework.social.alfresco.api.entities.Comment;
import org.springframework.social.alfresco.api.entities.Member;
import org.springframework.social.alfresco.api.entities.Network;
import org.springframework.social.alfresco.api.entities.Pagination;
import org.springframework.social.alfresco.api.entities.Person;
import org.springframework.social.alfresco.api.entities.Role;
import org.springframework.social.alfresco.api.entities.Tag;
import org.springframework.social.alfresco.connect.AlfrescoConnectionFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.web.client.HttpClientErrorException;


/**
 * 
 * @author jottley
 * @author sglover
 */
public class ConnectionTest
{
    private static final String              CONSUMER_KEY    = "l7xx16247a05ab7b46968625d4dda1f45aeb";
    private static final String              CONSUMER_SECRET = "";

    private static final String              REDIRECT_URI    = "http://localhost:9876";
    private static final String              STATE           = "test";

    private static AlfrescoConnectionFactory connectionFactory;
    private static Connection<Alfresco>      connection;
    private static AuthUrl                   authUrlObject;
    private static AccessGrant               accessGrant;

    private static Alfresco                  alfresco;

    private static Server                    server;


    private static String                    network         = "alfresco.com";
    private static String                    person          = "jared.ottley@alfresco.com";
    private static String                    memberId        = "pmonks@alfresco.com";
    private static String                    site            = "spring-social-alfresco";
    private static String                    container       = "documentLibrary";
    private static String                    preference      = "org.alfresco.share.siteWelcome.spring-social-alfresco";
    private static String                    node            = "8c368b84-4a88-4d62-9e7e-8e7eabe39969";
    private static String                    rating          = "likes";
    private static String                    tag             = "spring-social-alfresco";
    private static String                    testTag         = "test1";
    private static String                    testTag1        = "test2";
    private static String                    filename        = "full-codekit.pdf";
    private static String                    objectPath      = "/Sites/" + site + "/documentLibrary/" + filename;


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
        tag = getPropertyValue(properties, "tag", tag);
        testTag = getPropertyValue(properties, "testTag", testTag);
        testTag1 = getPropertyValue(properties, "testTag1", testTag1);
        memberId = getPropertyValue(properties, "memberId", memberId);
        person = getPropertyValue(properties, "person", person);
        testTag = getPropertyValue(properties, "testTag", testTag);
        site = getPropertyValue(properties, "site", site);
        container = getPropertyValue(properties, "container", container);
        preference = getPropertyValue(properties, "preference", preference);
        rating = getPropertyValue(properties, "rating", rating);
        filename = getPropertyValue(properties, "filename", filename);
        objectPath = getPropertyValue(properties, "objectPath", objectPath);

        GetAPI(properties.getProperty("username"), properties.getProperty("password"));
    }


    @Test
    public void CMIS()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Session session = alfresco.getCMISSession(network);
        Document doc = (Document)session.getObjectByPath(objectPath);

        assertEquals(filename, doc.getName());
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
    public void getCurrentUser()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Person currentUser = alfresco.getCurrentUser();

        assertEquals(person, currentUser.getId());
    }


    @Test
    public void AdapterTest()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Person currentUser = alfresco.getCurrentUser();
        String displayName = connection.getDisplayName();
        
        assertEquals(currentUser.getFirstName() + " " + currentUser.getLastName(), displayName);
        
        UserProfile userProfile = connection.fetchUserProfile();
        
        assertEquals(currentUser.getEmail(), userProfile.getEmail());
        assertEquals(currentUser.getFirstName(), userProfile.getFirstName());
        assertEquals(currentUser.getLastName(), userProfile.getLastName());
        assertEquals(currentUser.getId(), userProfile.getUsername());
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

        Member member = alfresco.addMember(network, site, memberId, Role.SiteConsumer);

        assertNotNull(member);
        assertEquals(memberId, member.getId());
        assertEquals(Role.SiteConsumer, member.getRole());

        alfresco.updateMember(network, site, memberId, Role.SiteContributor);
        member = alfresco.getMember(network, site, memberId);

        assertEquals(Role.SiteContributor, member.getRole());

        // need to wait some time for things to settle before we try and delete the member
        long max = Long.valueOf(Integer.MAX_VALUE) * 10;
        long min = 0;
        while (min < max)
        {
            min++;
        }

        alfresco.deleteMember(network, site, memberId);

        try
        {
            member = alfresco.getMember(network, site, memberId);
        }
        catch (HttpClientErrorException e)
        {
            if (e.getStatusCode().value() == 404)
            {

            }
            else
            {
                throw e;
            }
        }

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

        AlfrescoList<Tag> response = alfresco.getTags(network, parameters);

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

        AlfrescoList<Tag> response = alfresco.getTags(network, parameters);

        assertNull(response.getEntries().get(0).getId());
    }


    @Test
    public void updateTag()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Tag _tag = alfresco.getTag(network, tag);

        alfresco.updateTag(network, _tag.getId(), tag + "-test");

        _tag = alfresco.getTag(network, tag + "-test");

        assertNotNull(_tag);

        alfresco.updateTag(network, _tag.getId(), tag);
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
        List<String> comments = new ArrayList<String>();
        comments.add("This is comment 1");
        comments.add("This is comment 2");

        AlfrescoList<Comment> c = alfresco.createComments(network, node, comments);

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
        List<String> tags = new ArrayList<String>();
        tags.add(testTag);
        tags.add(testTag1);

        AlfrescoList<Tag> t = alfresco.addTagsToNode(network, node, tags);

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
    public void rateNode1()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.rateNode(network, node, true);
    }


    // TODO you can't rate your own content so this test will currently fail
    public void rateNode2()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.rateNode(network, node, 2);
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
        connectionFactory = new AlfrescoConnectionFactory("https://api.alfresco.com/", CONSUMER_KEY, CONSUMER_SECRET);

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

        accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(codeUrl.getQueryMap().get(CodeUrl.CODE), REDIRECT_URI, null);

        connection = connectionFactory.createConnection(accessGrant);
        alfresco = connection.getApi();
    }


    public static String getPropertyValue(Properties properties, String propertyName, String defaultValue)
    {
        String ret = (String)properties.getProperty(propertyName);
        if (ret == null || ret.equals(""))
        {
            ret = defaultValue;
        }
        return ret;
    }
}
