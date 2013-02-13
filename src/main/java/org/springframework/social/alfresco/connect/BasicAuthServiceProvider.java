package org.springframework.social.alfresco.connect;

import org.springframework.social.ServiceProvider;
import org.springframework.social.alfresco.api.Alfresco;
import org.springframework.social.alfresco.api.impl.BasicAuthAlfrescoTemplate;
import org.springframework.social.alfresco.api.impl.ConnectionDetails;

public class BasicAuthServiceProvider implements ServiceProvider<Alfresco>
{
	private ConnectionDetails connectionData;
	
	public BasicAuthServiceProvider(ConnectionDetails connectionData)
	{
		this.connectionData = connectionData;
	}

	public Alfresco getApi()
	{
        return new BasicAuthAlfrescoTemplate(connectionData);
	}
}
