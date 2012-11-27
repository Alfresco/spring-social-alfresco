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

package org.springframework.social.alfresco.api.impl;


import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.web.client.RestTemplate;

/**
 * @author jottley
 * @author sglover
 * 
 */
public class AlfrescoTemplate extends AbstractAlfrescoTemplate
{
	private OAuth2 oauth2;

	/*
	 * A new AlfrescoTemplate is created each time a new accessToken is generated, including
	 * after a token refresh.
	 */
    public AlfrescoTemplate(String baseUrl, String accessToken, boolean production)
    {
        super(baseUrl, production);
        this.authenticationProvider = OAuthCMISAuthenticationProvider.alfrescoOAuthProvider(accessToken);
        this.oauth2 = new OAuth2(accessToken);
        this.restTemplate = oauth2.getTemplateOverride();
        configureRestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    /*
     * Override the oauth2 binding so that I can wrap the rest template with
     * custom error handling behaviour.
     */
	static class OAuth2 extends AbstractOAuth2ApiBinding
	{
		private RestTemplate templateOverride;

		OAuth2(String accessToken)
		{
			super(accessToken);
			templateOverride = getRestTemplate();
			configureRestTemplate(this.templateOverride);
		}

		public RestTemplate getTemplateOverride()
		{
			return templateOverride;
		}
	}

	protected Map<String, String> getCMISParameters()
	{
		return null;
	}

    private final ObjectMapper mapper  = new ObjectMapper();
    private final HttpHeaders  headers = new HttpHeaders();
}
