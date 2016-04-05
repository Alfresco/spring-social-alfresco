package org.springframework.social.alfresco.connect;

import org.springframework.social.ServiceProvider;
import org.springframework.social.alfresco.api.Alfresco;
import org.springframework.social.alfresco.api.impl.BasicAuthAlfrescoTemplate;
import org.springframework.social.alfresco.api.impl.ConnectionDetails;

public class BasicAuthServiceProvider implements ServiceProvider<Alfresco>
{
    private ConnectionDetails repoConnectionData;
    private ConnectionDetails syncConnectionData;

    public BasicAuthServiceProvider(ConnectionDetails repoConnectionData,
            ConnectionDetails syncConnectionData)
    {
        this.repoConnectionData = repoConnectionData;
        this.syncConnectionData = syncConnectionData;
    }

    public Alfresco getApi()
    {
        return new BasicAuthAlfrescoTemplate(repoConnectionData,
                syncConnectionData);
    }
}
