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

import java.util.Date;

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
	private String subscriberId;
	private String subscriptionId;
	private String targetNodeId;
	private String targetPath;
	private SubscriptionType subscriptionType;
	private Date createdAt;

	public Subscription(String subscriberId, String targetPath, SubscriptionType subscriptionType)
	{
		super();
		this.subscriberId = subscriberId;
		this.targetPath = targetPath;
		this.subscriptionType = subscriptionType;
	}

	public String getSubscriberId()
	{
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId)
	{
		this.subscriberId = subscriberId;
	}

	public String getSubscriptionId()
	{
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId)
	{
		this.subscriptionId = subscriptionId;
	}

	public String getTargetNodeId()
	{
		return targetNodeId;
	}

	public void setTargetNodeId(String targetNodeId)
	{
		this.targetNodeId = targetNodeId;
	}

	public Date getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(Date createdAt)
	{
		this.createdAt = createdAt;
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
		return "Subscription [targetPath=" + targetPath + ", subscriptionType="
				+ subscriptionType + "]";
	}
	
}
