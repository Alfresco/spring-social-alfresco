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
package org.springframework.social.alfresco.api.entities.exceptions;


public class UnknownActivityTypeException
    extends RuntimeException
{
    private static final long serialVersionUID = 5308662077069821525L;


    public UnknownActivityTypeException(String activityType)
    {
        super("Unknown Activity Type: " + activityType);
    }


    public UnknownActivityTypeException(String activityType, Throwable cause)
    {
        super("Unknown Activity Type: " + activityType, cause);
    }

}
