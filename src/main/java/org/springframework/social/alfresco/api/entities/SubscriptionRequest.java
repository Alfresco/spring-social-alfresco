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
public class SubscriptionRequest
{
	private String targetPath;
	private String subscriptionType;

	public SubscriptionRequest(String targetPath, SubscriptionType subscriptionType)
	{
		super();
		this.targetPath = targetPath;
		this.subscriptionType = subscriptionType.toString();
	}

	public String getTargetPath()
	{
		return targetPath;
	}

	public void setTargetPath(String targetPath)
	{
		this.targetPath = targetPath;
	}

	public String getSubscriptionType()
	{
		return subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType)
	{
		this.subscriptionType = subscriptionType;
	}

	@Override
    public String toString()
    {
	    return "SubscriptionRequest [targetPath=" + targetPath
	            + ", subscriptionType=" + subscriptionType + "]";
    }
}
