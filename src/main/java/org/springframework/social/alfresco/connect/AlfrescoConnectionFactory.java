/*
 * Copyright 2012 Alfresco Software Limited.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * This file is part of an unsupported extension to Alfresco.
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

    /**
     * An ApiAdapter that bridges between the connection and the API client
     * 
     * @param consumerKey - Alfresco Api Key
     * @param consumerSecret - Alfresco Key Secret
     */
    public AlfrescoConnectionFactory(String consumerKey, String consumerSecret)
    {
        super("alfresco", new AlfrescoServiceProvider(consumerKey, consumerSecret), new AlfrescoAdapter());
    }

    /**
     * An ApiAdapter that bridges between the connection and the API client
     *
     * @param consumerKey - Alfresco Api Key
     * @param consumerSecret - Alfresco Key Secret
     * @param authorizeUrl - Authorization URL
     * @param accessTokenUrl - Access Token URL
     */
    public AlfrescoConnectionFactory(String consumerKey, String consumerSecret, String authorizeUrl, String accessTokenUrl)
    {
        super("alfresco", new AlfrescoServiceProvider(consumerKey, consumerSecret, authorizeUrl, accessTokenUrl), new AlfrescoAdapter());
    }

}
