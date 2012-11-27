/*
 * Copyright 2012 Alfresco Software Limited.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * This file is part of an unsupported extension to Alfresco.
 */
package org.springframework.social.alfresco.connect.exception;

import org.springframework.http.HttpStatus;


public class AlfrescoException
    extends RuntimeException
{
    private static final long serialVersionUID = 4856627580445924523L;

    private HttpStatus statusCode = null;
    
    public AlfrescoException(HttpStatus statusCode, String message)
    {
        super(message);
        this.statusCode = statusCode;
    }

    public AlfrescoException(String message)
    {
        super(message);
    }

    public AlfrescoException(String message, Throwable cause)
    {
        super(message, cause);
    }

	public HttpStatus getStatusCode()
	{
		return statusCode;
	}

}
