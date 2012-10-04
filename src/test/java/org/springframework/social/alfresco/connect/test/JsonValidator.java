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
package org.springframework.social.alfresco.connect.test;


import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import org.springframework.social.alfresco.api.entities.Activity;
import org.springframework.social.alfresco.api.entities.Comment;
import org.springframework.social.alfresco.api.entities.Container;
import org.springframework.social.alfresco.api.entities.Person;
import org.springframework.social.alfresco.api.entities.Preference;
import org.springframework.social.alfresco.api.entities.Member;
import org.springframework.social.alfresco.api.entities.Network;
import org.springframework.social.alfresco.api.entities.Rating;
import org.springframework.social.alfresco.api.entities.Site;
import org.springframework.social.alfresco.api.entities.Tag;


public class JsonValidator
{
    private final ObjectMapper mapper = new ObjectMapper();


    @Test
    public void TestNetwork()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        mapper.readValue(new File("src/test/resources/json/network.json"), mapper.getTypeFactory().constructParametricType(Response.class, Network.class));
    }


    @Test
    public void TestNetworks()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        mapper.readValue(new File("src/test/resources/json/networks.json"), mapper.getTypeFactory().constructParametricType(Response.class, Network.class));
    }


    @Test
    public void TestSite()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        mapper.readValue(new File("src/test/resources/json/site.json"), mapper.getTypeFactory().constructParametricType(Response.class, Site.class));
    }


    @Test
    public void TestSites()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        mapper.readValue(new File("src/test/resources/json/sites.json"), mapper.getTypeFactory().constructParametricType(Response.class, Site.class));
    }


    @Test
    public void TestContaiter()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        mapper.readValue(new File("src/test/resources/json/container.json"), mapper.getTypeFactory().constructParametricType(Response.class, Container.class));
    }


    @Test
    public void TestContainers()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        mapper.readValue(new File("src/test/resources/json/containers.json"), mapper.getTypeFactory().constructParametricType(Response.class, Container.class));
    }


    @Test
    public void TestSiteMember()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        mapper.readValue(new File("src/test/resources/json/member.json"), mapper.getTypeFactory().constructParametricType(Response.class, Member.class));
    }


    @Test
    public void TestSiteMembers()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        mapper.readValue(new File("src/test/resources/json/members.json"), mapper.getTypeFactory().constructParametricType(Response.class, Member.class));
    }


    @Test
    public void TestPerson()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        mapper.readValue(new File("src/test/resources/json/person.json"), mapper.getTypeFactory().constructParametricType(Response.class, Person.class));
    }


    @Test
    public void TestSiteMembership()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        mapper.readValue(new File("src/test/resources/json/sitemembership.json"), mapper.getTypeFactory().constructParametricType(Response.class, Site.class));
    }


    @Test
    public void TestSiteMemberships()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        mapper.readValue(new File("src/test/resources/json/sitememberships.json"), mapper.getTypeFactory().constructParametricType(Response.class, Site.class));
    }


    @Test
    public void TestSiteFavorites()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        mapper.readValue(new File("src/test/resources/json/sitefavorites.json"), mapper.getTypeFactory().constructParametricType(Response.class, Site.class));
    }


    @Test
    public void TestPreference()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        mapper.readValue(new File("src/test/resources/json/preference.json"), mapper.getTypeFactory().constructParametricType(Response.class, Preference.class));
    }


    @Test
    public void TestPreferences()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        mapper.readValue(new File("src/test/resources/json/preferences.json"), mapper.getTypeFactory().constructParametricType(Response.class, Preference.class));
    }


    @Test
    public void TestActivities()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        mapper.readValue(new File("src/test/resources/json/activities.json"), mapper.getTypeFactory().constructParametricType(Response.class, Activity.class));
    }


    @Test
    public void TestTags()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        mapper.readValue(new File("src/test/resources/json/tags.json"), mapper.getTypeFactory().constructParametricType(Response.class, Tag.class));
    }


    @Test
    public void TestComments()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        mapper.readValue(new File("src/test/resources/json/comments.json"), mapper.getTypeFactory().constructParametricType(Response.class, Comment.class));
    }


    @Test
    public void TestRating()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        mapper.readValue(new File("src/test/resources/json/rating.json"), mapper.getTypeFactory().constructParametricType(Response.class, Rating.class));
    }


    @Test
    public void TestRatings()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        mapper.readValue(new File("src/test/resources/json/ratings.json"), mapper.getTypeFactory().constructParametricType(Response.class, Rating.class));
    }

}
