/**
 * 
 */

package org.springframework.social.alfresco.connect;


import org.springframework.social.alfresco.api.Alfresco;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;


/**
 * @author jottley
 * 
 */
public class AlfrescoConnectionFactory
    extends OAuth2ConnectionFactory<Alfresco>
{

    public AlfrescoConnectionFactory(String baseUrl, String authUrl, String tokenUrl, String consumerKey, String consumerSecret)
    {
        super("alfresco", new OAuthServiceProvider(baseUrl, authUrl, tokenUrl, consumerKey, consumerSecret), new AlfrescoAdapter());
    }

}
