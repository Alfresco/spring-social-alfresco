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
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
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
	private String username;
	private String password;
	
	public BasicAuthAlfrescoTemplate(String host, int port, String username, String password)
	{
		super(BasicAuthAlfrescoTemplate.getBaseUrl(host, port));

		this.username = username;
		this.password = password;

		HttpParams params = new BasicHttpParams();
		DefaultHttpClient client = new DefaultHttpClient(params);
		client.getCredentialsProvider().setCredentials(
                 new AuthScope(host, port),
                 new UsernamePasswordCredentials(username, password));

//		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("your_user","your_password");
//		client.getState().setCredentials(new AuthScope("thehost", 9090, AuthScope.ANY_REALM), credentials);
		HttpComponentsClientHttpRequestFactory commons = new HttpComponentsClientHttpRequestFactory(client);

		restTemplate = new RestTemplate(commons);
		restTemplate.setMessageConverters(getMessageConverters());
		configureRestTemplate(restTemplate);
        headers.setContentType(MediaType.APPLICATION_JSON);
	}
	
	protected Map<String, String> getCMISParameters()
	{
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(SessionParameter.USER, username);
		parameters.put(SessionParameter.PASSWORD, password);
		return parameters;
	}
	
	private static String getBaseUrl(String host, int port)
	{
		StringBuilder sb = new StringBuilder("http://");
		sb.append(host);
		sb.append(":");
		sb.append(String.valueOf(port));
		sb.append("/alfresco/a/");
		return sb.toString();
	}
	
	protected void configureRestTemplate(RestTemplate restTemplate) {
	}

	protected List<HttpMessageConverter<?>> getMessageConverters() {
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
