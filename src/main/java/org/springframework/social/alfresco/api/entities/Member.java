/*
 * Copyright 2012 Alfresco Software Limited.
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
 * Members are the people who collaborate on a site.
 * 
 * @author jottley
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class Member
{
    private Role   role;
    private String id;
    private Person person;


    /**
     * @return The member's role. Possible values are SiteManager, SiteContributor, SiteConsumer and SiteCollaborator.
     */
    public Role getRole()
    {
        return role;
    }


    /**
     * Set the member's role. Possible values are SiteManager, SiteContributor, SiteConsumer and SiteCollaborator.
     * 
     * @param role
     */
    public void setRole(Role role)
    {
        this.role = role;
    }


    /**
     * @return The person's personId - the email address with which the person registered
     */
    public String getId()
    {
        return id;
    }


    /**
     * Set the person's personId - the email address with which the person registered
     * 
     * @param id
     */
    public void setId(String id)
    {
        this.id = id;
    }


    /**
     * @return An embedded person object describing this member.
     */
    public Person getPerson()
    {
        return person;
    }


    /**
     * Set the person object describing this member.
     * 
     * @param person
     */
    public void setPerson(Person person)
    {
        this.person = person;
    }

}
