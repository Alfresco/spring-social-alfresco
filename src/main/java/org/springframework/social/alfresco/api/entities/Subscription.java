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
public class Subscription
{
//	  "targetNodeId" : "011cb041-1480-4ae8-be62-023de19a662c",
//	    "id" : "ee453619-8e9c-4984-ab7c-74f73cd97b33",
//	    "targetPath" : "/Company Home/Sites/site1/documentLibrary",
//	    "deviceSubscriptionId" : "fd4b6ed0-7e73-42e6-af21-781cdf087211",
//	    "createdAt" : "2015-06-11T15:47:09.295+0000",
//	    "state" : "VALID"

	private String id;
	private String deviceSubscriptionId; // subscriberId
	private String state;
	private String subscriptionId;
	private String targetNodeId;
	private String targetPath;
	private SubscriptionType subscriptionType;
	private String createdAt;

	public Subscription()
	{
	}

	public Subscription(String targetPath, SubscriptionType subscriptionType)
	{
		super();
		this.targetPath = targetPath;
		this.subscriptionType = subscriptionType;
	}

	public String getId()
	{
		return id;
	}


	public void setId(String id)
	{
		this.id = id;
	}


	public String getDeviceSubscriptionId()
	{
		return deviceSubscriptionId;
	}


	public void setDeviceSubscriptionId(String deviceSubscriptionId)
	{
		this.deviceSubscriptionId = deviceSubscriptionId;
	}


	public String getState()
	{
		return state;
	}


	public void setState(String state)
	{
		this.state = state;
	}


	public void setCreatedAt(String createdAt)
	{
		this.createdAt = createdAt;
	}

	public String getSubscriberId()
	{
		return deviceSubscriptionId;
	}

	public void setSubscriberId(String subscriberId)
	{
		this.deviceSubscriptionId = subscriberId;
	}

	public String getTargetNodeId()
	{
		return targetNodeId;
	}

	public void setTargetNodeId(String targetNodeId)
	{
		this.targetNodeId = targetNodeId;
	}

	public String getTargetPath()
	{
		return targetPath;
	}
	
	public void setTargetPath(String targetPath)
	{
		this.targetPath = targetPath;
	}
	
	public SubscriptionType getSubscriptionType()
	{
		return subscriptionType;
	}
	
	public void setSubscriptionType(SubscriptionType subscriptionType)
	{
		this.subscriptionType = subscriptionType;
	}

	@Override
    public String toString()
    {
	    return "Subscription [id=" + id + ", deviceSubscriptionId="
	            + deviceSubscriptionId + ", state=" + state
	            + ", subscriptionId=" + subscriptionId
	            + ", targetNodeId=" + targetNodeId + ", targetPath="
	            + targetPath + ", subscriptionType=" + subscriptionType
	            + ", createdAt=" + createdAt + "]";
    }
}
