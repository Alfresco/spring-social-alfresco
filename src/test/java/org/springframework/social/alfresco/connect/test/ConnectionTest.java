
package org.springframework.social.alfresco.connect.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;
import org.springframework.social.alfresco.api.Alfresco;
import org.springframework.social.alfresco.api.entities.people.Activity;
import org.springframework.social.alfresco.connect.AlfrescoConnectionFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Parameters;


/**
 * 
 * @author jottley
 * 
 */
public class ConnectionTest
{

    private static final String              CONSUMER_KEY    = "l7xx16247a05ab7b46968625d4dda1f45aeb";
    private static final String              CONSUMER_SECRET = "3af3780039de4da5892519d2d6d856b9";

    private static final String              REDIRECT_URI    = "http://localhost:8080/alfoauthsample/mycallback.html";
    private static final String              STATE           = "test";
    private static final String              SCOPE           = "public_api";

    private static AlfrescoConnectionFactory connectionFactory;
    private static AuthUrl                   authUrlObject;

    private static Alfresco                  alfresco;


    private static final String              network         = "alfresco.com";
    private static final String              person          = "jared.ottley@alfresco.com";
    private static final String              site            = "oauth-sample-clients";
    private static final String              container       = "documentLibrary";
    private static final String              preference      = "org.alfresco.share.siteWelcome.oauth-sample-clients";
    private static final String              node            = "790968f3-b7ba-4774-834a-53d5248a3cdb";
    private static final String              rating          = "likes";


    @Test
    public void test()
    {
        connectionFactory = new AlfrescoConnectionFactory(CONSUMER_KEY, CONSUMER_SECRET);

        assertEquals("alfresco", connectionFactory.getProviderId());
    }


    @Test
    public void UrlTest()
    {
        OAuth2Parameters parameters = new OAuth2Parameters();
        parameters.setRedirectUri(REDIRECT_URI);
        parameters.setScope(SCOPE);
        parameters.setState(STATE);

        String authUrl = connectionFactory.getOAuthOperations().buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, parameters);

        System.out.println(authUrl);

        try
        {
            authUrlObject = new AuthUrl(authUrl);
            assertEquals("https://api.alfresco.com/auth/oauth/versions/2/authorize", authUrlObject.getBase());
        }
        catch (MalformedURLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Test
    public void hasQueryParams()
    {
        assertNotNull(authUrlObject.getQuery());
        assertNotNull(authUrlObject.getQueryMap().get(AuthUrl.CLIENT_ID));
        assertEquals(CONSUMER_KEY, authUrlObject.getQueryMap().get(AuthUrl.CLIENT_ID));
        assertNotNull(authUrlObject.getQueryMap().get(AuthUrl.REDIRECT_URI));
        assertNotNull(authUrlObject.getQueryMap().get(AuthUrl.RESPONSE_TYPE));
        assertNotNull(authUrlObject.getQueryMap().get(AuthUrl.SCOPE));
        assertNotNull(authUrlObject.getQueryMap().get(AuthUrl.STATE));
        assertEquals(STATE, authUrlObject.getQueryMap().get(AuthUrl.STATE));
    }


    @Test
    public void GetAPI()
    {
        // Wait for the authorization code
        System.out.println("Type the code you received here: ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String accessToken = null;
        try
        {
            accessToken = in.readLine();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        AccessGrant accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(accessToken, REDIRECT_URI, null);

        System.out.println("Access: " + accessGrant.getAccessToken() + " Refresh: " + accessGrant.getRefreshToken() + " Scope: "
                           + accessGrant.getScope() + " expires: " + accessGrant.getExpireTime());

        Connection<Alfresco> connection = connectionFactory.createConnection(accessGrant);
        alfresco = connection.getApi();


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
        alfresco.getSite(network, site);
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
        Map<String, String> parameters = null;
        // alfresco.getActivities(network, person, parameters);

        parameters = new HashMap<String, String>();
        // alfresco.getActivities(network, person, parameters);

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
    public void getTags()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        alfresco.getTags(network);
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
            alfresco.getNodeRating(network, node);
        }

}
