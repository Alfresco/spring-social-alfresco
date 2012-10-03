/**
 * 
 */

package org.springframework.social.alfresco.connect;


import org.springframework.social.alfresco.api.Alfresco;
import org.springframework.social.alfresco.api.impl.AlfrescoTemplate;
import org.springframework.social.alfresco.api.impl.OAuthCMISAuthenticationProvider;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;


/**
 * @author jottley
 * 
 */
public class OAuthServiceProvider
    extends AbstractOAuth2ServiceProvider<Alfresco>
{
	private String baseUrl;

	// "https://stagapi.alfresco.com/auth/oauth/versions/2/authorize"
	// "https://stagapi.alfresco.com/auth/oauth/versions/2/token"
    public OAuthServiceProvider(String baseUrl, String authUrl, String tokenUrl, String consumerKey,
    		String consumerSecret)
    {
        super(new OAuth2Template(consumerKey, consumerSecret, authUrl, tokenUrl));
        this.baseUrl = baseUrl;
    }


    @Override
    public Alfresco getApi(String accessToken)
    {
        return new AlfrescoTemplate(baseUrl, accessToken);
    }

}
