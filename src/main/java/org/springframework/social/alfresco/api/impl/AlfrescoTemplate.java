/**
 * 
 */
package org.springframework.social.alfresco.api.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;

/**
 * @author jottley
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
        super();
        this.authenticationProvider = OAuthAuthenticationProvider.alfrescoOAuthProvider(accessToken);
        this.oauth2 = new OAuth2(accessToken);
        this.restTemplate = oauth2.getRestTemplate();
        this.baseUrl = baseUrl;
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

	protected Session createCMISSession(String networkId)
	{
		// default factory implementation
		SessionFactoryImpl sessionFactory = SessionFactoryImpl.newInstance();
		Map<String, String> parameters = new HashMap<String, String>();

		// connection settings
		parameters.put(SessionParameter.ATOMPUB_URL, getUrl(ATOMPUB_URL).replace("{network}", networkId));
		parameters.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
		parameters.put(SessionParameter.REPOSITORY_ID, networkId);

		// create session
		Session session = sessionFactory.createSession(parameters, null, authenticationProvider, null);
		return session;
	}
    
	protected java.util.List<Repository> getCMISNetworks()
	{
		// default factory implementation
		SessionFactoryImpl sessionFactory = SessionFactoryImpl.newInstance();
		Map<String, String> parameters = new HashMap<String, String>();

		// connection settings
		parameters.put(SessionParameter.ATOMPUB_URL, getUrl(ROOT_ATOMPUB_URL));
		parameters.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

		return sessionFactory.getRepositories(parameters, null, authenticationProvider, null);
	}
	
	private static class OAuth2 extends AbstractOAuth2ApiBinding
	{
		OAuth2(String accessToken)
		{
			super(accessToken);
		}
	}

//	protected void configureRestTemplate(RestTemplate restTemplate) {
//	restTemplate.setErrorHandler(new ResponseErrorHandler() {
//		
//		public boolean hasError(ClientHttpResponse response) throws IOException {
////			HttpStatus status = response.getStatusCode();
////			return !status.equals(HttpStatus.OK);
//			return false;
//		}
//		
//		public void handleError(ClientHttpResponse response) throws IOException {
////			System.out.println(response);
////			try
////			{
////				JSONObject o = (JSONObject)parser.parse(new InputStreamReader(response.getBody(), "UTF-8"));
////				System.out.println(o);
////			}
////			catch(ParseException e)
////			{
////				e.printStackTrace();
////			}
//		}
//	});
//}

    private final ObjectMapper mapper  = new ObjectMapper();
    private final HttpHeaders  headers = new HttpHeaders();
}
