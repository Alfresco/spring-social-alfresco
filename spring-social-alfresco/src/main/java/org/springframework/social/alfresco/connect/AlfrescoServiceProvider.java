/**
 * 
 */

package org.springframework.social.alfresco.connect;


import org.springframework.social.alfresco.api.Alfresco;
import org.springframework.social.alfresco.api.impl.AlfrescoTemplate;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;


/**
 * @author jottley
 * 
 */
public class AlfrescoServiceProvider
    extends AbstractOAuth2ServiceProvider<Alfresco>
{
    public AlfrescoServiceProvider(String consumerKey, String consumerSecret)
    {
        super(new OAuth2Template(consumerKey, consumerSecret, "https://api.alfresco.com/auth/oauth/versions/2/authorize", "https://api.alfresco.com/auth/oauth/versions/2/token"));
    }


    @Override
    public Alfresco getApi(String accessToken)
    {
        return new AlfrescoTemplate(accessToken);
    }

}
