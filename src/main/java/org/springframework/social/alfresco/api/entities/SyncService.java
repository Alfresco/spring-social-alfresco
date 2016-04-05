package org.springframework.social.alfresco.api.entities;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * 
 * @author steveglover
 *
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class SyncService
{
    private String id;
    private String uri;
    private SyncServiceConfig config;

    public SyncService() {
    }

    public SyncService(String id, String uri, SyncServiceConfig config) {
        super();
        this.id = id;
        this.uri = uri;
        this.config = config;
    }

    public SyncServiceConfig getConfig() {
        return config;
    }

    public void setConfig(SyncServiceConfig config) {
        this.config = config;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SyncService [id=" + id + ", uri=" + uri + ", config=" + config
                + "]";
    }
}
