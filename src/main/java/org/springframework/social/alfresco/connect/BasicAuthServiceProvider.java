package org.springframework.social.alfresco.connect;

import org.springframework.social.ServiceProvider;
import org.springframework.social.alfresco.api.Alfresco;
import org.springframework.social.alfresco.api.impl.BasicAuthAlfrescoTemplate;
import org.springframework.social.alfresco.api.impl.ConnectionDetails;

public class BasicAuthServiceProvider implements ServiceProvider<Alfresco>
{
	private ConnectionDetails repoConnectionData;
	private ConnectionDetails syncConnectionData;
	private ConnectionDetails subsConnectionData;

	public BasicAuthServiceProvider(ConnectionDetails repoConnectionData, ConnectionDetails syncConnectionData,
			ConnectionDetails subsConnectionData)
	{
		this.repoConnectionData = repoConnectionData;
		this.syncConnectionData = syncConnectionData;
		this.subsConnectionData = subsConnectionData;
	}

	public Alfresco getApi()
	{
        return new BasicAuthAlfrescoTemplate(repoConnectionData, syncConnectionData, subsConnectionData);
	}
}
