
package org.springframework.social.alfresco.connect.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.junit.Test;
import org.springframework.social.alfresco.api.Alfresco;
import org.springframework.social.alfresco.api.entities.Network;
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

    private static final String              CONSUMER_KEY    = "<INSERT CLIENT ID HERE";
    private static final String              CONSUMER_SECRET = "<INSET CLIENT SECRET HERE>";

    private static final String              REDIRECT_URI    = "<INSERT REDIRECT URI HERE>"; // "http://localhost:8080/alfoauthsample/mycallback.html"
    private static final String              STATE           = "test";
    private static final String              SCOPE           = "public_api";

    private static AlfrescoConnectionFactory connectionFactory;
    private static AuthUrl                   authUrlObject;


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
    public void API()
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
        Alfresco alfresco = connection.getApi();

        Network network = alfresco.getNetwork("alfresco.com");

        System.out.println(network.getId());


    }

}
