package org.springframework.social.alfresco.connect;

import org.springframework.social.alfresco.api.Alfresco;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactory;

public class BasicAuthAlfrescoConnectionFactory extends ConnectionFactory<Alfresco>
{
    public BasicAuthAlfrescoConnectionFactory(String host, int port, String username, String password, boolean production)
    {
        super("alfresco", new BasicAuthServiceProvider(host, port, username, password, production), new AlfrescoAdapter());
    }

	public Connection<Alfresco> createConnection() {
		return new BasicAuthConnection(getBasicAuthServiceProvider(), getApiAdapter());		
	}

    @Override
	public Connection<Alfresco> createConnection(ConnectionData data)
	{
		return new BasicAuthConnection(data, getBasicAuthServiceProvider(), getApiAdapter());
	}
    
	// internal helpers
	
	private BasicAuthServiceProvider getBasicAuthServiceProvider()
	{
		return (BasicAuthServiceProvider)getServiceProvider();
	}
}
