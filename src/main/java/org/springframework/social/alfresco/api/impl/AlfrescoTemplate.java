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


import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.alfresco.api.entities.exceptions.PublicApiException;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.ResponseErrorHandler;
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
    public AlfrescoTemplate(String baseUrl, String accessToken)
    {
        super(baseUrl);
        this.authenticationProvider = OAuthCMISAuthenticationProvider.alfrescoOAuthProvider(accessToken);
        this.oauth2 = new OAuth2(accessToken);
//        this.restTemplate = oauth2.getTemplateOverride();
        this.restTemplate = oauth2.getRestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }
    
    /*
     * Override to allow reading and handling of an error response without causing the read response
     * logic to re-read the response (as seems to be the default behaviour).
     */
/*    static class RestTemplateWrapper extends RestTemplate
    {
    	RestTemplateWrapper(RestTemplate delegate)
    	{
    		super(delegate.getRequestFactory());
    	}

    	// shame these two methods aren't protected...
    	private void logResponseStatus(HttpMethod method, URI url, ClientHttpResponse response) {
    		if (logger.isDebugEnabled()) {
    			try {
    				logger.debug(
    						method.name() + " request for \"" + url + "\" resulted in " + response.getStatusCode() + " (" +
    								response.getStatusText() + ")");
    			}
    			catch (IOException e) {
    				// ignore
    			}
    		}
    	}

    	private void handleResponseError(HttpMethod method, URI url, ClientHttpResponse response) throws IOException {
    		if (logger.isWarnEnabled()) {
    			try {
    				logger.warn(
    						method.name() + " request for \"" + url + "\" resulted in " + response.getStatusCode() + " (" +
    								response.getStatusText() + "); invoking error handler");
    			}
    			catch (IOException e) {
    				// ignore
    			}
    		}
    		getErrorHandler().handleError(response);
    	}

    	
    	 * Override to allow reading and handling of an error response without causing the read response
    	 * logic to re-read the response (as seems to be the default behaviour).
    	 * 
    	 * (non-Javadoc)
    	 * @see org.springframework.web.client.RestTemplate#doExecute(java.net.URI, org.springframework.http.HttpMethod, org.springframework.web.client.RequestCallback, org.springframework.web.client.ResponseExtractor)
    	 
    	protected <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback,
    			ResponseExtractor<T> responseExtractor) throws RestClientException {

    		Assert.notNull(url, "'url' must not be null");
    		Assert.notNull(method, "'method' must not be null");
    		ClientHttpResponse response = null;
    		try {
    			ClientHttpRequest request = createRequest(url, method);
    			if (requestCallback != null) {
    				requestCallback.doWithRequest(request);
    			}
    			response = request.execute();
    			if (!getErrorHandler().hasError(response)) {
    				logResponseStatus(method, url, response);
        			if (responseExtractor != null) {
        				return responseExtractor.extractData(response);
        			}
        			else {
        				return null;
        			}
    			}
    			else {
    				handleResponseError(method, url, response);
    				return null;
    			}
    		}
    		catch (IOException ex) {
    			throw new ResourceAccessException("I/O error: " + ex.getMessage(), ex);
    		}
    		finally {
    			if (response != null) {
    				response.close();
    			}
    		}
    	}
    	
//    	@Override
//    	protected <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback,
//    			ResponseExtractor<T> responseExtractor) throws RestClientException {
//    		responseExtractor = new HttpMessageConverterExtractor<T>(responseType, getMessageConverters(), logger);
//    		super.doExecute(url, method, requestCallback, responseExtractor);
//    	}
    }*/

    /*
     * Override the oauth2 binding so that I can wrap the rest template with
     * custom error handling behaviour.
     */
	static class OAuth2 extends AbstractOAuth2ApiBinding
	{
//		private RestTemplate templateOverride;

		OAuth2(String accessToken)
		{
			super(accessToken);
//			this.templateOverride = new RestTemplateWrapper(getRestTemplate());
//			this.templateOverride.setMessageConverters(getMessageConverters());
//			configureRestTemplate(this.templateOverride);
		}

//		public RestTemplate getTemplateOverride() {
//			return templateOverride;
//		}

		protected void configureRestTemplate(RestTemplate restTemplate) {
			restTemplate.setErrorHandler(new ResponseErrorHandler() {

				public boolean hasError(ClientHttpResponse response) throws IOException {
					HttpStatus status = response.getStatusCode();
					Series series = status.series();
					return series == Series.CLIENT_ERROR || series == Series.SERVER_ERROR;
				}

				private byte[] getResponseBody(ClientHttpResponse response) {
					try {
			            InputStream responseBody = response.getBody();
			            if (responseBody != null) {
			                return FileCopyUtils.copyToByteArray(responseBody);
			            }
					}
					catch (IOException ex) {
			            // ignore
					}
			        return new byte[0];
				}

				public void handleError(ClientHttpResponse response) throws IOException {
					HttpStatus statusCode = response.getStatusCode();
					MediaType contentType = response.getHeaders().getContentType();
					System.out.println(statusCode + ":" + response.getStatusText());

					Charset charset = contentType != null ? contentType.getCharSet() : null;
					byte[] body = getResponseBody(response);
					String message = statusCode + ":" + response.getStatusText() + ":" + new String(body, charset);
					log.error(message);
					throw new PublicApiException(message);
				}
			});
		}
	}

	protected Map<String, String> getCMISParameters()
	{
		return null;
	}

    private final ObjectMapper mapper  = new ObjectMapper();
    private final HttpHeaders  headers = new HttpHeaders();
}
