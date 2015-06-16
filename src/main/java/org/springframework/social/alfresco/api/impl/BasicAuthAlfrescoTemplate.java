package org.springframework.social.alfresco.api.impl;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class BasicAuthAlfrescoTemplate extends AbstractAlfrescoTemplate
{
    private final ClientConnectionManager repoHttpClientCM;
    private final HttpParams repoHttpParams;
	private final String repoPassword;
	private final String repoUsername;

    private final ClientConnectionManager syncHttpClientCM;
    private final HttpParams syncHttpParams;
	private final String syncPassword;
	private final String syncUsername;

	public BasicAuthAlfrescoTemplate(ConnectionDetails repoConnectionData, ConnectionDetails syncConnectionData,
			ConnectionDetails subsConnectionData)
	{
		super(BasicAuthAlfrescoTemplate.getBaseUrl(repoConnectionData),
				BasicAuthAlfrescoTemplate.getBaseUrl(syncConnectionData),
				BasicAuthAlfrescoTemplate.getBaseUrl(subsConnectionData),
				        repoConnectionData.getPublicApiServletName(),
				        repoConnectionData.getServiceServletName());
		{
			this.repoUsername = repoConnectionData.getUsername();
			this.repoPassword = repoConnectionData.getPassword();
			this.repoHttpClientCM = repoConnectionData.getHttpClientCM();
			this.repoHttpParams = repoConnectionData.getParams();
	
			DefaultHttpClient client = new DefaultHttpClient(repoHttpClientCM, repoHttpParams);
	
	        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
	        credentialsProvider.setCredentials(
	                new AuthScope(AuthScope.ANY),
	                new UsernamePasswordCredentials(repoUsername, repoPassword));
	        client.setCredentialsProvider(credentialsProvider);
	
			HttpComponentsClientHttpRequestFactory commons = new HttpComponentsClientHttpRequestFactory(client);
	
			repoRestTemplate = new RestTemplate(commons);
			repoRestTemplate.setMessageConverters(getMessageConverters());
			configureRestTemplate();
	        headers.setContentType(MediaType.APPLICATION_JSON);
		}

		{
			this.syncUsername = syncConnectionData.getUsername();
			this.syncPassword = syncConnectionData.getPassword();
			this.syncHttpClientCM = syncConnectionData.getHttpClientCM();
			this.syncHttpParams = syncConnectionData.getParams();
	
			DefaultHttpClient client = new DefaultHttpClient(syncHttpClientCM, syncHttpParams);
	
	        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
	        credentialsProvider.setCredentials(
	                new AuthScope(AuthScope.ANY),
	                new UsernamePasswordCredentials(syncUsername, syncPassword));
	        client.setCredentialsProvider(credentialsProvider);
	
			HttpComponentsClientHttpRequestFactory commons = new HttpComponentsClientHttpRequestFactory(client);

			syncRestTemplate = new RestTemplate(commons);
			syncRestTemplate.setMessageConverters(getMessageConverters());
		}
	}

	protected Map<String, String> getCMISParameters()
	{
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(SessionParameter.USER, repoUsername);
		parameters.put(SessionParameter.PASSWORD, repoPassword);
		return parameters;
	}
	
	private static String getBaseUrl(ConnectionDetails connectionDetails)
	{
		String baseUrl = null;
		if(connectionDetails != null)
		{
			StringBuilder sb = new StringBuilder(connectionDetails.getScheme());
			sb.append("://");
			sb.append(connectionDetails.getHost());
			if(connectionDetails.getPort() != null)
			{
				sb.append(":");
				sb.append(String.valueOf(connectionDetails.getPort()));
			}
			sb.append("/");
			sb.append(connectionDetails.getContext());
			sb.append("/");

			baseUrl = sb.toString();
		}
		return baseUrl;
	}

	protected List<HttpMessageConverter<?>> getMessageConverters()
	{
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new StringHttpMessageConverter());
		messageConverters.add(getFormMessageConverter());
		messageConverters.add(getJsonMessageConverter());
		messageConverters.add(getByteArrayMessageConverter());
		return messageConverters;
	}
	
	/**
	 * Returns an {@link FormHttpMessageConverter} to be used by the internal {@link RestTemplate}.
	 * By default, the message converter is set to use "UTF-8" character encoding.
	 * Override to customize the message converter (for example, to set supported media types or message converters for the parts of a multipart message). 
	 * To remove/replace this or any of the other message converters that are registered by default, override the getMessageConverters() method instead.
	 */
	protected FormHttpMessageConverter getFormMessageConverter() {
		FormHttpMessageConverter converter = new FormHttpMessageConverter();
		converter.setCharset(Charset.forName("UTF-8"));
		return converter;
	}
	
	/**
	 * Returns a {@link MappingJacksonHttpMessageConverter} to be used by the internal {@link RestTemplate}.
	 * Override to customize the message converter (for example, to set a custom object mapper or supported media types).
	 * To remove/replace this or any of the other message converters that are registered by default, override the getMessageConverters() method instead.
	 */
	protected MappingJacksonHttpMessageConverter getJsonMessageConverter() {
		return new MappingJacksonHttpMessageConverter(); 
	}
	
	/**
	 * Returns a {@link ByteArrayHttpMessageConverter} to be used by the internal {@link RestTemplate} when consuming image or other binary resources.
	 * By default, the message converter supports "image/jpeg", "image/gif", and "image/png" media types.
	 * Override to customize the message converter (for example, to set supported media types).
	 * To remove/replace this or any of the other message converters that are registered by default, override the getMessageConverters() method instead.	 
	 */
	protected ByteArrayHttpMessageConverter getByteArrayMessageConverter() {
		ByteArrayHttpMessageConverter converter = new ByteArrayHttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.IMAGE_JPEG, MediaType.IMAGE_GIF, MediaType.IMAGE_PNG));
		return converter;
	}
}
