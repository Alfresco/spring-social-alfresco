/**
 * 
 */

package org.springframework.social.alfresco.connect;


import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.social.OperationNotPermittedException;
import org.springframework.social.alfresco.api.Alfresco;
import org.springframework.social.alfresco.api.entities.Network;
import org.springframework.social.alfresco.connect.exception.AlfrescoException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;


/**
 * @author jottley
 * 
 */
public class AlfrescoAdapter
    implements ApiAdapter<Alfresco>
{

    public UserProfile fetchUserProfile(Alfresco alfresco)
    {
        throw new OperationNotPermittedException("updateStatus not implemented.");
    }


    public Network fetchHomeNetwork(Alfresco alfresco)
    {
        Network homeNetwork = null;
        try
        {
            homeNetwork = alfresco.getHomeNetwork();
        }
        catch (JsonParseException e)
        {
            throw new AlfrescoException("Unable to parse JSON Response: " + e.getMessage(), e.getCause());
        }
        catch (JsonMappingException e)
        {
            throw new AlfrescoException("JSON Mapping Error: " + e.getMessage(), e.getCause());
        }
        catch (IOException e)
        {
            throw new AlfrescoException("Error reading response" + e.getMessage(), e.getCause());
        }
        return homeNetwork;
    }


    public void setConnectionValues(Alfresco alfresco, ConnectionValues values)
    {
        //Not implemented at this time
    }


    public boolean test(Alfresco alfresco)
    {
        try
        {
            alfresco.getNetworks();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }

    }


    public void updateStatus(Alfresco alfresco, String status)
    {
        throw new OperationNotPermittedException("updateStatus not implemented.");

    }

}
