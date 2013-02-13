package org.springframework.social.alfresco.api.impl;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class ConnectionDetails
{
	private String scheme;
	private String host;
	private int port;
	private String username;
	private String password;
	private boolean production;
	private int maxNumberOfConnections;
	private int connectionTimeoutMs;
	private int socketTimeoutMs;
	private int socketTtlMs;
	
	private PoolingClientConnectionManager httpClientCM;
	private HttpParams params;

	public ConnectionDetails(String scheme, String host, int port,
			String username, String password, boolean production,
			int maxNumberOfConnections, int connectionTimeoutMs,
			int socketTimeoutMs, int socketTtlMs)
	{
		super();
		this.scheme = scheme;
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.production = production;
		this.maxNumberOfConnections = maxNumberOfConnections;
		this.connectionTimeoutMs = connectionTimeoutMs;
		this.socketTimeoutMs = socketTimeoutMs;
		this.socketTtlMs = socketTtlMs;
		
    	this.params = new BasicHttpParams();
//    	DefaultHttpClient client = new DefaultHttpClient(params);
//    	client.getCredentialsProvider().setCredentials(
//    			new AuthScope(host, connectionData.getPort()),
//    			new UsernamePasswordCredentials(connectionData.getUsername(), connectionData.getPassword()));
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

    	this.httpClientCM = new PoolingClientConnectionManager(schemeRegistry,
    			(long)socketTimeoutMs, TimeUnit.MILLISECONDS);
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
	}

	public String getScheme()
	{
		return scheme;
	}

	public String getHost()
	{
		return host;
	}

	public int getPort()
	{
		return port;
	}

	public String getUsername()
	{
		return username;
	}

	public String getPassword()
	{
		return password;
	}

	public boolean isProduction()
	{
		return production;
	}

	public int getMaxNumberOfConnections()
	{
		return maxNumberOfConnections;
	}

	public int getConnectionTimeoutMs()
	{
		return connectionTimeoutMs;
	}

	public int getSocketTimeoutMs()
	{
		return socketTimeoutMs;
	}

	public int getSocketTtlMs()
	{
		return socketTtlMs;
	}

	public PoolingClientConnectionManager getHttpClientCM()
	{
		return httpClientCM;
	}

	public HttpParams getParams()
	{
		return params;
	}

	@Override
	public String toString()
	{
		return "ConnectionData [scheme=" + scheme + ", host=" + host
				+ ", port=" + port + ", username=" + username + ", password="
				+ password + ", production=" + production
				+ ", maxNumberOfConnections=" + maxNumberOfConnections
				+ ", connectionTimeoutMs=" + connectionTimeoutMs
				+ ", socketTimeoutMs=" + socketTimeoutMs + ", socketTtlMs="
				+ socketTtlMs + "]";
	}
	
    /**
     * A {@link TrustStrategy} that trusts any certificate.
     * 
     * @author Derek Hulley
     * @since 1.0
     */
    private static class TrustAnyTrustStrategy implements TrustStrategy
    {
        /**
         * @return          Returns <tt>true</tt> always
         */
        public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException
        {
            return true;
        }
    }
}
