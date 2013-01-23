package org.springframework.social.alfresco.connect;

import org.springframework.social.ServiceProvider;
import org.springframework.social.alfresco.api.Alfresco;
import org.springframework.social.alfresco.api.impl.BasicAuthAlfrescoTemplate;

public class BasicAuthServiceProvider implements ServiceProvider<Alfresco>
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
	
	public BasicAuthServiceProvider(String scheme, String host, int port, String username, String password, boolean production,
			int maxNumberOfConnections, int connectionTimeoutMs, int socketTimeoutMs, int socketTtlMs)
	{
		this.scheme = scheme;
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.production = production;
		this.maxNumberOfConnections= maxNumberOfConnections;
		this.connectionTimeoutMs = connectionTimeoutMs;
		this.socketTimeoutMs = socketTimeoutMs;
		this.socketTtlMs= socketTtlMs; 
	}

	public Alfresco getApi()
	{
        return new BasicAuthAlfrescoTemplate(scheme, host, port, username, password, production,
        		maxNumberOfConnections, connectionTimeoutMs, socketTimeoutMs, socketTtlMs);
	}
}
