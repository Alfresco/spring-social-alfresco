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
	private String baseUrl;

    public AlfrescoServiceProvider(String baseUrl, String authUrl, String tokenUrl, String consumerKey,
    		String consumerSecret)
    {
        super(new OAuth2Template(consumerKey, consumerSecret, authUrl, tokenUrl));
        this.baseUrl = baseUrl;
    }
    
    public AlfrescoServiceProvider(String consumerKey, String consumerSecret)
    {
        super(new OAuth2Template(consumerKey, consumerSecret, "https://api.alfresco.com/auth/oauth/versions/2/authorize", "https://api.alfresco.com/auth/oauth/versions/2/token"));
    }


    @Override
    public Alfresco getApi(String accessToken)
    {
        return new AlfrescoTemplate(baseUrl, accessToken);
    }

}
