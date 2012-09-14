/**
 * 
 */

package org.springframework.social.alfresco.api.impl;


import org.springframework.social.alfresco.api.Alfresco;
import org.springframework.social.alfresco.api.entities.Network;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;


/**
 * @author jottley
 * 
 */
public class AlfrescoTemplate
    extends AbstractOAuth2ApiBinding
    implements Alfresco
{
    private String accessToken;


    public AlfrescoTemplate(String accessToken)
    {
        super(accessToken);
        this.accessToken = accessToken;
    }

    //TODO Add API Calls
    public Network getNetwork(String network)
    {
        //return getRestTemplate().getFor
        return null;
    }

    private final int    VERSION      = 1;
    private final String BASE_URL     = "https://api.alfresco.com/";
    private final String NETWORK_URL  = BASE_URL + "{network}/public/alfresco/versions/" + VERSION + "/networks/{network}";
    private final String NETWORKS_URL = BASE_URL;


}
