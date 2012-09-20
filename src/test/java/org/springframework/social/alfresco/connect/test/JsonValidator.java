
package org.springframework.social.alfresco.connect.test;


import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import org.springframework.social.alfresco.api.Response;
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
        Response<Network> network = mapper.readValue(new File("src/test/resources/network.json"), mapper.getTypeFactory().constructParametricType(Response.class, Network.class));
    }


    @Test
    public void TestNetworks()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Response<Network> networks = mapper.readValue(new File("src/test/resources/networks.json"), mapper.getTypeFactory().constructParametricType(Response.class, Network.class));
    }


    @Test
    public void TestSite()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Response<Site> site = mapper.readValue(new File("src/test/resources/site.json"), mapper.getTypeFactory().constructParametricType(Response.class, Site.class));
    }


    @Test
    public void TestSites()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Response<Site> sites = mapper.readValue(new File("src/test/resources/sites.json"), mapper.getTypeFactory().constructParametricType(Response.class, Site.class));
    }


    @Test
    public void TestContaiter()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Response<Container> container = mapper.readValue(new File("src/test/resources/container.json"), mapper.getTypeFactory().constructParametricType(Response.class, Container.class));
    }


    @Test
    public void TestContainers()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Response<Container> containers = mapper.readValue(new File("src/test/resources/containers.json"), mapper.getTypeFactory().constructParametricType(Response.class, Container.class));
    }


    @Test
    public void TestSiteMember()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Response<Member> member = mapper.readValue(new File("src/test/resources/member.json"), mapper.getTypeFactory().constructParametricType(Response.class, Member.class));
    }


    @Test
    public void TestSiteMembers()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Response<Member> members = mapper.readValue(new File("src/test/resources/members.json"), mapper.getTypeFactory().constructParametricType(Response.class, Member.class));
    }


    @Test
    public void TestPerson()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Response<Person> person = mapper.readValue(new File("src/test/resources/person.json"), mapper.getTypeFactory().constructParametricType(Response.class, Person.class));
    }


    @Test
    public void TestSiteMembership()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Response<Site> siteMembership = mapper.readValue(new File("src/test/resources/sitemembership.json"), mapper.getTypeFactory().constructParametricType(Response.class, Site.class));
    }


    @Test
    public void TestSiteMemberships()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Response<Site> siteMemberships = mapper.readValue(new File("src/test/resources/sitememberships.json"), mapper.getTypeFactory().constructParametricType(Response.class, Site.class));
    }


    @Test
    public void TestSiteFavorites()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Response<Site> siteFavorites = mapper.readValue(new File("src/test/resources/sitefavorites.json"), mapper.getTypeFactory().constructParametricType(Response.class, Site.class));
    }


    @Test
    public void TestPreference()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Response<Preference> preference = mapper.readValue(new File("src/test/resources/preference.json"), mapper.getTypeFactory().constructParametricType(Response.class, Preference.class));
    }


    @Test
    public void TestPreferences()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Response<Preference> preferences = mapper.readValue(new File("src/test/resources/preferences.json"), mapper.getTypeFactory().constructParametricType(Response.class, Preference.class));
    }


    @Test
    public void TestActivities()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Response<Activity> activities = mapper.readValue(new File("src/test/resources/activities.json"), mapper.getTypeFactory().constructParametricType(Response.class, Activity.class));
    }


    @Test
    public void TestTags()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Response<Tag> tags = mapper.readValue(new File("src/test/resources/tags.json"), mapper.getTypeFactory().constructParametricType(Response.class, Tag.class));
    }


    @Test
    public void TestComments()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Response<Comment> comments = mapper.readValue(new File("src/test/resources/comments.json"), mapper.getTypeFactory().constructParametricType(Response.class, Comment.class));
    }


    @Test
    public void TestRating()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Response<Rating> rating = mapper.readValue(new File("src/test/resources/rating.json"), mapper.getTypeFactory().constructParametricType(Response.class, Rating.class));
    }


    @Test
    public void TestRatings()
        throws JsonParseException,
            JsonMappingException,
            IOException
    {
        Response<Rating> ratings = mapper.readValue(new File("src/test/resources/ratings.json"), mapper.getTypeFactory().constructParametricType(Response.class, Rating.class));
    }

}
