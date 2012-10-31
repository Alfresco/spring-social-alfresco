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

package org.springframework.social.alfresco.connect;


import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.social.OperationNotPermittedException;
import org.springframework.social.alfresco.api.Alfresco;
import org.springframework.social.alfresco.api.entities.Network;
import org.springframework.social.alfresco.api.entities.Person;
import org.springframework.social.alfresco.connect.exception.AlfrescoException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;


/**
 * An ApiAdapter that bridges between the connection and the API client
 * 
 * @author jottley
 */
public class AlfrescoAdapter
    implements ApiAdapter<Alfresco>
{

    /**
     * Returns the Alfresco user profile for a user
     * 
     * @param alfresco - Alfresco Api client interface
     * @return Spring social UserProfile
     * @throws AlfrescoException
     * @throws APIException
     */
    public UserProfile fetchUserProfile(Alfresco alfresco)
    {
        UserProfileBuilder userProfile = new UserProfileBuilder();
        try
        {
            Person currentUser = alfresco.getCurrentUser();

            userProfile.setEmail(currentUser.getEmail()).setFirstName(currentUser.getFirstName()).setLastName(currentUser.getLastName()).setUsername(currentUser.getId());
        }
        catch (JsonParseException e)
        {
            throw new AlfrescoException(e.getMessage(), e);
        }
        catch (JsonMappingException e)
        {
            throw new AlfrescoException(e.getMessage(), e);
        }
        catch (IOException e)
        {
            throw new AlfrescoException(e.getMessage(), e);
        }

        return userProfile.build();
    }


    /**
     * Returns the Alfresco home network for a user
     * 
     * @param alfresco - Alfresco Api client interface
     * @return Alfresco Network
     * @throws AlfrescoException
     * @throws APIException
     */
    public Network fetchHomeNetwork(Alfresco alfresco)
    {
        Network homeNetwork = null;
        try
        {
            homeNetwork = alfresco.getHomeNetwork();
        }
        catch (JsonParseException e)
        {
            throw new AlfrescoException(e.getMessage(), e);
        }
        catch (JsonMappingException e)
        {
            throw new AlfrescoException(e.getMessage(), e);
        }
        catch (IOException e)
        {
            throw new AlfrescoException(e.getMessage(), e);
        }

        return homeNetwork;
    }


    /**
     * Set connection values DisplayName, ProviderUserId
     * 
     * @param alfresco - Alfresco Api client interface
     * @param values - Spring Social Connection Values
     */
    public void setConnectionValues(Alfresco alfresco, ConnectionValues values)
    {
        try
        {
            Person currentUser = alfresco.getCurrentUser();
            values.setDisplayName(currentUser.getFirstName() + " " + currentUser.getLastName());
            values.setProviderUserId(currentUser.getId());
        }
        catch (JsonParseException e)
        {
            throw new AlfrescoException(e.getMessage(), e);
        }
        catch (JsonMappingException e)
        {
            throw new AlfrescoException(e.getMessage(), e);
        }
        catch (IOException e)
        {
            throw new AlfrescoException(e.getMessage(), e);
        }
    }


    /**
     * Is the Api functional
     * 
     * @param alfresco - Alfresco Api client interface
     * @return true if functional
     */
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
