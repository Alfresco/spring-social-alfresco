/*
 * Copyright 2013 Alfresco Software Limited.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * 
 * This file is part of an unsupported extension to Alfresco.
 */
package org.springframework.social.alfresco.api.entities;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * 
 * @author steveglover
 *
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class Subscriber
{
	private String id;
	private SyncService syncService;
	private String deviceOS;
	private String syncServiceId;
	private String createdAt;

	public Subscriber()
	{
	}

	public String getSyncServiceId()
	{
		return syncServiceId;
	}

	public void setSyncServiceId(String syncServiceId)
	{
		this.syncServiceId = syncServiceId;
	}


	public void setCreatedAt(String createdAt)
	{
		this.createdAt = createdAt;
	}


	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getDeviceOS()
	{
		return deviceOS;
	}

	public void setDeviceOS(String deviceOS)
	{
		this.deviceOS = deviceOS;
	}

	public String getSubscriberId()
	{
		return id;
	}
	
	public void setSubscriberId(String subscriberId)
	{
		this.id = subscriberId;
	}

	public SyncService getSyncService()
	{
		return syncService;
	}
	
	public void setSyncService(SyncService syncService)
	{
		this.syncService = syncService;
	}

	@Override
	public String toString() {
		return "Subscriber [id=" + id
				+ ", createdAt=" + createdAt + ", syncService=" + syncService
				+ "]";
	}
	

	
}
