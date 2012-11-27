package org.springframework.social.alfresco.connect;

import org.springframework.social.alfresco.api.Alfresco;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.AbstractConnection;

public class BasicAuthConnection extends AbstractConnection<Alfresco>
{
	private BasicAuthServiceProvider serviceProvider;
	private Alfresco api;

	public BasicAuthConnection(BasicAuthServiceProvider serviceProvider, ApiAdapter<Alfresco> apiAdapter)
	{
		super(apiAdapter);
		this.serviceProvider = serviceProvider;
		initApi();
	}

	public BasicAuthConnection(ConnectionData data, BasicAuthServiceProvider serviceProvider, ApiAdapter<Alfresco> apiAdapter)
	{
		super(data, apiAdapter);
		this.serviceProvider = serviceProvider;
		initApi();
	}

	private void initApi()
	{
		api = serviceProvider.getApi();
	}

	@Override
	public Alfresco getApi()
	{
		return api;
	}

	@Override
	public ConnectionData createData()
	{
		return null;
	}

}
