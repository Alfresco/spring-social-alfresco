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

    public AlfrescoConnectionFactory(String consumerKey, String consumerSecret)
    {
        super("alfresco", new AlfrescoServiceProvider(consumerKey, consumerSecret), new AlfrescoAdapter());
    }

}
