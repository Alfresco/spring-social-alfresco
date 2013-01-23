package org.springframework.social.alfresco.api.impl;

import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
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
    private final PoolingClientConnectionManager httpClientCM;

	private String username;
	private String password;
	
	public BasicAuthAlfrescoTemplate(String scheme, String host, int port, String username, String password, boolean production,
			int maxNumberOfConnections, int connectionTimeoutMs, int socketTimeoutMs, int socketTtlMs)
	{
		super(BasicAuthAlfrescoTemplate.getBaseUrl(scheme, host, port, production), production);

		this.username = username;
		this.password = password;

        SSLSocketFactory sslSf = null;
        try
        {
            TrustStrategy sslTs = new TrustAnyTrustStrategy();
            sslSf = new SSLSocketFactory(sslTs, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        }
        catch (Throwable e)
        {
            throw new RuntimeException("Unable to construct HttpClientProvider.", e);
        }

        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(
                 new Scheme("http", 8080, PlainSocketFactory.getSocketFactory()));
        schemeRegistry.register(
                new Scheme("https", 443, sslSf));
        schemeRegistry.register(
                new Scheme("https", 80, sslSf));

        this.httpClientCM = new PoolingClientConnectionManager(schemeRegistry, (long) socketTtlMs, TimeUnit.MILLISECONDS);
        // Increase max total connections
        this.httpClientCM.setMaxTotal(maxNumberOfConnections);
        // Ensure that we don't throttle on a per-scheme basis (BENCH-45)
        this.httpClientCM.setDefaultMaxPerRoute(maxNumberOfConnections);

		HttpParams httpParams = new BasicHttpParams();
		
        HttpConnectionParams.setConnectionTimeout(httpParams, connectionTimeoutMs);
        HttpConnectionParams.setSoTimeout(httpParams, socketTimeoutMs);
        HttpConnectionParams.setTcpNoDelay(httpParams, true);
        HttpConnectionParams.setStaleCheckingEnabled(httpParams, true);
        HttpConnectionParams.setSoKeepalive(httpParams, true);

		DefaultHttpClient client = new DefaultHttpClient(httpClientCM, httpParams);
		
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                new AuthScope(AuthScope.ANY),
                new UsernamePasswordCredentials(username, password));
        client.setCredentialsProvider(credentialsProvider);
		
//		client.getCredentialsProvider().setCredentials(
//                 new AuthScope(host, port),
//                 new UsernamePasswordCredentials(username, password));

		HttpComponentsClientHttpRequestFactory commons = new HttpComponentsClientHttpRequestFactory(client);

		restTemplate = new RestTemplate(commons);
		restTemplate.setMessageConverters(getMessageConverters());
		configureRestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
	}
	
	protected Map<String, String> getCMISParameters()
	{
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(SessionParameter.USER, username);
		parameters.put(SessionParameter.PASSWORD, password);
		return parameters;
	}
	
	private static String getBaseUrl(String scheme, String host, int port, boolean production)
	{
		StringBuilder sb = new StringBuilder(scheme);
		sb.append("://");
		sb.append(host);
		sb.append(":");
		sb.append(String.valueOf(port));
		if(!production)
		{
			sb.append("/alfresco/");
		}
		return sb.toString();
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

    /**
     * A {@link TrustStrategy} that trusts any certificate.
     * 
     * @author Derek Hulley
     * @since 1.0
     */
    private class TrustAnyTrustStrategy implements TrustStrategy
    {
        /**
         * @return          Returns <tt>true</tt> always
         */
//        @Override
        public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException
        {
            return true;
        }
    }
}
