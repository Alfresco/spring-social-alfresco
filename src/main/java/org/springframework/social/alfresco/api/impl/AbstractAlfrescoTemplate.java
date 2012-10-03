package org.springframework.social.alfresco.api.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.spi.AuthenticationProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.social.OperationNotPermittedException;
import org.springframework.social.alfresco.api.Alfresco;
import org.springframework.social.alfresco.api.entities.Activity;
import org.springframework.social.alfresco.api.entities.Comment;
import org.springframework.social.alfresco.api.entities.Container;
import org.springframework.social.alfresco.api.entities.List;
import org.springframework.social.alfresco.api.entities.Member;
import org.springframework.social.alfresco.api.entities.Metadata;
import org.springframework.social.alfresco.api.entities.Network;
import org.springframework.social.alfresco.api.entities.Pagination;
import org.springframework.social.alfresco.api.entities.Person;
import org.springframework.social.alfresco.api.entities.Preference;
import org.springframework.social.alfresco.api.entities.Rating;
import org.springframework.social.alfresco.api.entities.Role;
import org.springframework.social.alfresco.api.entities.Site;
import org.springframework.social.alfresco.api.entities.Tag;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractAlfrescoTemplate implements Alfresco
{
    protected static final Log   log     = LogFactory.getLog(AlfrescoTemplate.class);

	protected ObjectMapper mapper  = new ObjectMapper();
	protected HttpHeaders  headers = new HttpHeaders();
	protected RestTemplate restTemplate;
	protected String baseUrl;
	protected JSONParser parser = new JSONParser();
	protected AuthenticationProvider authenticationProvider;

	private ThreadLocal<CMISSessions> cmisSession = new ThreadLocal<CMISSessions>() {
		@Override
		protected CMISSessions initialValue()
		{
			return new CMISSessions();
		}
	};

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	protected abstract Session createCMISSession(String networkId);

	public Session getCMISSession(String networkId) {
		CMISSessions sessions = cmisSession.get();
		Session session = sessions.getSession(networkId);
		return session;
	}

	public Network getNetwork(String network)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = Collections.singletonMap(TemplateParams.NETWORK, network);
		String response = getRestTemplate().getForObject(getUrl(NETWORK_URL), String.class, vars);
		log.debug("getNetwork: " + response);
		Response<Network> n = mapper.readValue(response, entryResponseType(Network.class));
		return n.getEntry();
	}

	public List<Network> getNetworks()
			throws JsonParseException,
			JsonMappingException,
			IOException {
		return getNetworks(null);
	}

	public List<Network> getNetworks(Map<String, String> parameters)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		String response = getRestTemplate().getForObject(getUrl(NETWORKS_URL) + generateQueryString(parameters), String.class);
		log.debug("getNetworks: " + response);
		Response<Network> n = mapper.readValue(response, entryResponseType(Network.class));
		return n.getList();
	}

	public Site getSite(String site, String network)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.SITE, site);

		String response = getRestTemplate().getForObject(getUrl(SITE_URL), String.class, vars);
		log.debug("getSite: " + response);
		Response<Site> s = mapper.readValue(response, entryResponseType(Site.class));
		return s.getEntry();
	}


	public List<Site> getSites(String network)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		// Use empty hashmap to avoid ambiguity in method signature with a null
		return getSites(network, new HashMap<String, String>());
	}


	public List<Site> getSites(String network, Map<String, String> parameters)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = Collections.singletonMap(TemplateParams.NETWORK + generateQueryString(parameters), network);
		String response = getRestTemplate().getForObject(getUrl(SITES_URL), String.class, vars);
		log.debug("getSites: " + response);
		Response<Site> s = mapper.readValue(response, entryResponseType(Site.class));
		return s.getList();
	}

	public Container getContainer(String network, String site, String contatiner)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.SITE, site);
		vars.put(TemplateParams.CONTAINER, contatiner);

		String response = getRestTemplate().getForObject(getUrl(CONTAINER_URL), String.class, vars);
		log.debug("getContainer: " + response);
		Response<Container> c = mapper.readValue(response, entryResponseType(Container.class));
		return c.getEntry();
	}


	public List<Container> getContainers(String network, String site)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		return getContainers(network, site, null);
	}


	public List<Container> getContainers(String network, String site, Map<String, String> parameters)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.SITE, site);

		String response = getRestTemplate().getForObject(getUrl(CONTAINERS_URL) + generateQueryString(parameters), String.class, vars);
		log.debug("getContainers: " + response);
		Response<Container> c = mapper.readValue(response, entryResponseType(Container.class));
		return c.getList();
	}


	public Member getMember(String network, String site, String person)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.SITE, site);
		vars.put(TemplateParams.MEMBER, person);

		String response = getRestTemplate().getForObject(getUrl(MEMBER_URL), String.class, vars);
		log.debug("getMember: " + response);
		Response<Member> m = mapper.readValue(response, entryResponseType(Member.class));
		return m.getEntry();
	}


	public List<Member> getMembers(String network, String site)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		return getMembers(network, site, null);
	}
	

	public List<Member> getMembers(String network, String site, Map<String, String> parameters)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.SITE, site);

		String response = getRestTemplate().getForObject(getUrl(MEMBERS_URL) + generateQueryString(parameters), String.class, vars);
		log.debug("getMembers: " + response);
		Response<Member> m = mapper.readValue(response, entryResponseType(Member.class));
		return m.getList();
	}


	public Member addMember(String network, String site, String personId, Role role)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.SITE, site);

		Member member = new Member();
		member.setId(personId);
		member.setRole(role);

		String response = getRestTemplate().postForObject(getUrl(MEMBERS_URL), new HttpEntity<Member>(member, headers), String.class, vars);
		log.debug("addMember: " + response);
		Response<Member> m = mapper.readValue(response, entryResponseType(Member.class));
		return m.getEntry();
	}


	// TODO should this make the additional call to get the updated entity or just move forward
	public void updateMember(String network, String site, String personId, Role role)
			throws RestClientException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.SITE, site);
		vars.put(TemplateParams.MEMBER, personId);

		Member member = new Member();
		member.setRole(role);

		getRestTemplate().put(getUrl(MEMBER_URL), new HttpEntity<Member>(member, headers), vars);
		log.debug("updateMember: member: " + personId + " to Role: " + role);

	}


	// TODO should this make the additional call to get the updated entity or just move forward
	public void deleteMember(String network, String site, String personId)
			throws RestClientException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.SITE, site);
		vars.put(TemplateParams.MEMBER, personId);

		getRestTemplate().delete(getUrl(MEMBER_URL), vars);
		log.debug("deleteMember: " + personId + " from site: " + site);

	}


	public Person getPerson(String network, String person)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.PERSON, person);

		String response = getRestTemplate().getForObject(getUrl(PEOPLE_URL), String.class, vars);
		log.debug("getPerson: " + response);
		Response<Person> p = mapper.readValue(response, entryResponseType(Person.class));
		return p.getEntry();
	}


	public List<Site> getSites(String network, String person)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		return getSites(network, person, null);
	}


	public List<Site> getSites(String network, String person, Map<String, String> parameters)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.PERSON, person);

		String response = getRestTemplate().getForObject(getUrl(PEOPLE_SITES_URL) + generateQueryString(parameters), String.class, vars);
		log.debug("getSites: " + response);
		Response<Site> s = mapper.readValue(response, entryResponseType(Site.class));
		return s.getList();
	}


	public Site getSite(String network, String person, String site)
			throws JsonParseException,
			JsonMappingException,
			IOException {

		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.PERSON, person);
		vars.put(TemplateParams.SITE, site);

		String response = getRestTemplate().getForObject(getUrl(PEOPLE_SITE_URL), String.class, vars);
		log.debug("getSite: " + response);
		Response<Site> s = mapper.readValue(response, entryResponseType(Site.class));
		return s.getEntry();
	}


	public List<Site> getFavoriteSites(String network, String person)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		return getFavoriteSites(network, person, null);
	}


	public List<Site> getFavoriteSites(String network, String person, Map<String, String> parameters)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.PERSON, person);

		String response = getRestTemplate().getForObject(getUrl(PEOPLE_FAVORITE_SITES_URL) + generateQueryString(parameters), String.class, vars);
		log.debug("getFavoriteSites: " + response);
		Response<Site> s = mapper.readValue(response, entryResponseType(Site.class));
		return s.getList();
	}


	public Preference getPreference(String network, String person, String preference)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.PERSON, person);
		vars.put(TemplateParams.PREFERENCE, preference);

		String response = getRestTemplate().getForObject(getUrl(PEOPLE_PREFERENCE_URL), String.class, vars);
		log.debug("getPreference: " + response);
		Response<Preference> p = mapper.readValue(response, entryResponseType(Preference.class));
		return p.getEntry();
	}


	public List<Preference> getPreferences(String network, String person)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		return getPreferences(network, person, null);
	}


	public List<Preference> getPreferences(String network, String person, Map<String, String> parameters)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.PERSON, person);

		String response = getRestTemplate().getForObject(getUrl(PEOPLE_PREFERENCES_URL) + generateQueryString(parameters), String.class, vars);
		log.debug("getPreferences: " + response);
		Response<Preference> p = mapper.readValue(response, entryResponseType(Preference.class));
		return p.getList();
	}


	public Network getNetwork(String network, String person)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.PERSON, person);

		String response = getRestTemplate().getForObject(getUrl(PEOPLE_NETWORK_URL), String.class, vars);
		log.debug("getNetwork: " + response);
		Response<Network> n = mapper.readValue(response, entryResponseType(Network.class));
		return n.getEntry();
	}


	public List<Network> getNetworks(String network, String person)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		return getNetworks(network, person, null);
	}


	public List<Network> getNetworks(String network, String person, Map<String, String> parameters)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.PERSON, person);

		String response = getRestTemplate().getForObject(getUrl(PEOPLE_NETWORKS_URL) + generateQueryString(parameters), String.class, vars);
		log.debug("getNetworks: " + response);
		Response<Network> n = mapper.readValue(response, entryResponseType(Network.class));
		return n.getList();
	}


	public List<Activity> getActivities(String network, String person)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		return getActivities(network, person, null);
	}


	public List<Activity> getActivities(String network, String person, Map<String, String> parameters)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.PERSON, person);

		String response = getRestTemplate().getForObject(getUrl(PEOPLE_ACTIVITIES_URL) + generateQueryString(parameters), String.class, vars);
		log.debug("getActivities: " + response);
		Response<Activity> a = mapper.readValue(response, entryResponseType(Activity.class));
		return a.getList();
	}


	public Tag getTag(String network, String tag)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		int count = 0;
		final int MAXITEMS = 10;

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(Pagination.MAXITEMS, Integer.toString(MAXITEMS));

		boolean found = false;
		Tag tagFound = null;

		List<Tag> response = getTags(network, parameters);
		while (!found)
		{
			for (Iterator<Tag> iterator = response.getEntries().iterator(); iterator.hasNext();)
			{
				Tag _tag = iterator.next();
				if (_tag.getTag().equals(tag))
				{
					tagFound = _tag;
					found = true;
					break;
				}
				else
				{
					if (!iterator.hasNext())
					{
						if (response.getPagination().isHasMoreItems())
						{
							parameters.put(Pagination.SKIPCOUNT, Integer.toString(count = count + MAXITEMS));
							response = getTags(network, parameters);
						}
						else
						{
							found = true;
							break;
						}
					}
				}
			}
		}
		return tagFound;
	}


	public List<Tag> getTags(String network)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		return getTags(network, null);
	}


	public List<Tag> getTags(String network, Map<String, String> parameters)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = Collections.singletonMap(TemplateParams.NETWORK, network);

		String response = getRestTemplate().getForObject(getUrl(TAGS_URL) + generateQueryString(parameters), String.class, vars);
		log.debug("getTags: " + response);
		Response<Tag> t = mapper.readValue(response, entryResponseType(Tag.class));
		return t.getList();
	}


	public void updateTag(String network, String tagId, String tag)
			throws RestClientException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.TAG, tagId);

		Tag _tag = new Tag();
		_tag.setTag(tag);

		getRestTemplate().put(TAG_URL, new HttpEntity<Tag>(_tag, headers), vars);
		log.debug("updateTag: " + tag);

	}


	public List<Comment> getComments(String network, String node)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		return getComments(network, node, null);
	}


	public List<Comment> getComments(String network, String node, Map<String, String> parameters)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.NODE, node);

		String response = getRestTemplate().getForObject(getUrl(NODE_COMMENTS_URL) + generateQueryString(parameters), String.class, vars);
		log.debug("getComments: " + response);
		Response<Comment> c = mapper.readValue(response, entryResponseType(Comment.class));
		return c.getList();
	}


	public Comment createComment(String network, String node, String comment)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.NODE, node);

		Comment _comment = new Comment();
		_comment.setContent(comment);

		String response = getRestTemplate().postForObject(getUrl(NODE_COMMENTS_URL), new HttpEntity<Comment>(_comment, headers), String.class, vars);
		log.debug("createComment: " + response);
		Response<Comment> c = mapper.readValue(response, entryResponseType(Comment.class));
		return c.getEntry();

	}


	public List<Comment> createComments(String network, String node, java.util.List<String> comments)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.NODE, node);

		java.util.List<Comment> _comments = new ArrayList<Comment>();
		for (String content : comments)
		{
			Comment _comment = new Comment();
			_comment.setContent(content);
			_comments.add(_comment);
		}

		String response = getRestTemplate().postForObject(getUrl(NODE_COMMENTS_URL), new HttpEntity<java.util.List<Comment>>(_comments, headers), String.class, vars);
		log.debug("createComments: " + response);
		Response<Comment> c = mapper.readValue(response, entryResponseType(Comment.class));
		return c.getList();
	}


	public void updateComment(String network, String node, String commentId, String comment)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.NODE, node);
		vars.put(TemplateParams.COMMENT, commentId);

		Comment _comment = new Comment();
		_comment.setContent(comment);

		getRestTemplate().put(NODE_COMMENT_URL, new HttpEntity<Comment>(_comment, headers), vars);
		log.debug("updateComment: " + comment);
	}


	public void deleteComment(String network, String node, String commentId)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.NODE, node);
		vars.put(TemplateParams.COMMENT, commentId);

		getRestTemplate().delete(getUrl(NODE_COMMENT_URL), vars);
		log.debug("deleteComment: " + commentId);
	}


	public List<Tag> getNodesTags(String network, String node)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		return getNodesTags(network, node, null);
	}


	public List<Tag> getNodesTags(String network, String node, Map<String, String> parameters)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.NODE, node);

		String response = getRestTemplate().getForObject(getUrl(NODE_TAGS_URL) + generateQueryString(parameters), String.class, vars);
		log.debug("getNodeTafs: " + response);
		Response<Tag> t = mapper.readValue(response, entryResponseType(Tag.class));
		return t.getList();
	}


	public Tag addTagToNode(String network, String node, String tag)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.NODE, node);

		Tag _tag = new Tag();
		_tag.setTag(tag);

		String response = getRestTemplate().postForObject(getUrl(NODE_TAG_URL), new HttpEntity<Tag>(_tag, headers), String.class, vars);
		log.debug("addTagToNode: " + response);
		Response<Tag> t = mapper.readValue(response, entryResponseType(Tag.class));
		return t.getEntry();
	}


	public List<Tag> addTagsToNode(String network, String node, java.util.List<String> tags)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.NODE, node);

		java.util.List<Tag> _tags = new ArrayList<Tag>();
		for (String tag : tags)
		{
			Tag _tag = new Tag();
			_tag.setTag(tag);
			_tags.add(_tag);
		}

		String response = getRestTemplate().postForObject(getUrl(NODE_TAGS_URL), new HttpEntity<java.util.List<Tag>>(_tags, headers), String.class, vars);
		log.debug("addTagsToNode: " + response);
		Response<Tag> t = mapper.readValue(response, entryResponseType(Tag.class));
		return t.getList();
	}


	public void removeTagFromNode(String network, String node, String tagId)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.NODE, node);
		vars.put(TemplateParams.TAG, tagId);

		getRestTemplate().delete(NODE_TAG_URL, vars);
		log.debug("removeTagFromNode: " + tagId);
	}


	public List<Rating> getNodeRatings(String network, String node)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		return getNodeRatings(network, node, null);
	}


	public List<Rating> getNodeRatings(String network, String node, Map<String, String> parameters)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.NODE, node);

		String response = getRestTemplate().getForObject(getUrl(NODE_RATINGS_URL) + generateQueryString(parameters), String.class, vars);
		log.debug("getNodeRatings: " + response);
		Response<Rating> r = mapper.readValue(response, entryResponseType(Rating.class));
		return r.getList();
	}


	public Rating getNodeRating(String network, String node, String rating)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.NODE, node);
		vars.put(TemplateParams.RATING, rating);

		String response = getRestTemplate().getForObject(getUrl(NODE_RATING_URL), String.class, vars);
		log.debug("getNodeRatings: " + response);
		Response<Rating> r = mapper.readValue(response, entryResponseType(Rating.class));
		return r.getEntry();
	}


	public void removeNodeRating(String network, String node, String ratingId)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.NODE, node);
		vars.put(TemplateParams.RATING, ratingId);

		getRestTemplate().delete(getUrl(NODE_RATING_URL), vars);
		log.debug("removeNodeRating: " + ratingId);
	}


	public Rating rateNode(String network, String node, String ratingType, String rating)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(TemplateParams.NETWORK, network);
		vars.put(TemplateParams.NODE, node);

		Rating _rating = new Rating();
		_rating.setId(ratingType);
		_rating.setMyRating(rating);

		String response = getRestTemplate().postForObject(getUrl(NODE_RATINGS_URL), new HttpEntity<Rating>(_rating, headers), String.class, vars);
		log.debug("rateNode: " + response);
		Response<Rating> r = mapper.readValue(response, entryResponseType(Rating.class));
		return r.getEntry();
	}


	private JavaType entryResponseType(Class<?> type)
	{
		return mapper.getTypeFactory().constructParametricType(Response.class, type);
	}


	public Network getHomeNetwork()
			throws JsonParseException,
			JsonMappingException,
			IOException {
		Network homeNetwork = null;
		List<Network> response = getNetworks();

		for (Iterator<Network> iterator = response.getEntries().iterator(); iterator.hasNext();)
		{
			Network network = iterator.next();

			if (network.isHomeNetwork())
				homeNetwork = network;
		}
		return homeNetwork;
	}


	/**
	 * Not Implemented yet
	 */
	@Deprecated
	public List<Metadata> networkOptions(String network)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		throw new OperationNotPermittedException("HTTP OPTIONS Not implemented yet");
		// Map<String, String> vars = Collections.singletonMap(TemplateParams.NETWORK, network);
		// ResponseEntity<String> response =
		// getRestTemplate().exchange("https://api.alfresco.com/{network}/public/alfresco/versions/1/nodes", HttpMethod.OPTIONS,
		// null, String.class, vars);
		// System.out.println("Network Options: " + response.getBody());
		// return mapper.readValue(response.getBody(), entryResponseType(Metadata.class));

	}


	/**
	 * Build QueryString
	 * 
	 * @param parameters
	 * @return
	 */
	private String generateQueryString(Map<String, String> parameters)
	{
		StringBuilder queryString = new StringBuilder();
		if (parameters != null && parameters.size() > 0)
		{
			queryString.append("?");

			Iterator<java.util.Map.Entry<String, String>> entries = parameters.entrySet().iterator();
			while (entries.hasNext())
			{
				java.util.Map.Entry<String, String> thisEntry = entries.next();
				queryString.append(thisEntry.getKey()).append("=").append(thisEntry.getValue());
				if (entries.hasNext())
				{
					queryString.append("&");
				}
			}
		}

		log.debug("queryString: " + queryString.toString());
		return queryString.toString();
	}

	private static class TemplateParams
	{
		public final static String NETWORK    = "network";
		public final static String SITE       = "site";
		public final static String CONTAINER  = "container";
		public final static String PREFERENCE = "preference";
		public final static String TAG        = "tag";
		public final static String RATING     = "rating";
		public final static String COMMENT    = "comment";
		public final static String NODE       = "node";
		public final static String PERSON     = "person";
		public final static String MEMBER     = "member";
	}

	public static class QueryParams
	{
		public final static String PROPERTIES = "properties";
	}

	public String getUrl(String api)
	{
		return baseUrl + api;
	}

	private class CMISSessions
	{
		private Map<String, Session> sessions = new HashMap<String, Session>();

		Session getSession(String networkId)
		{
			Session session = sessions.get(networkId);
			if(session == null)
			{
				session = createCMISSession(networkId);
				sessions.put(networkId, session);
			}
			return session;
		}
	}


	protected final int    VERSION_NO                = 1;
	protected final String VERSION                   = "/public/alfresco/versions/" + VERSION_NO + "/";
	protected final String ROOT_ATOMPUB_URL          = "/cmis/versions/1.0/atom";
	protected final String ATOMPUB_URL               = "{network}" + "/public/cmis/versions/1.0/atom";
	protected final String NETWORKS_URL              = "";
	protected final String NETWORK_URL               = "{network}" + VERSION + "networks/{network}";
	protected final String SITES_URL                 = "{network}" + VERSION + "sites";
	protected final String SITE_URL                  = SITES_URL + "/{site}";
	protected final String CONTAINERS_URL            = SITE_URL + "/containers";
	protected final String CONTAINER_URL             = CONTAINERS_URL + "/{container}";
	protected final String MEMBERS_URL               = SITE_URL + "/members";
	protected final String MEMBER_URL                = MEMBERS_URL + "/{member}";
	protected final String PEOPLE_URL                = "{network}" + VERSION + "people/{person}";
	protected final String PEOPLE_SITES_URL          = PEOPLE_URL + "/sites";
	protected final String PEOPLE_SITE_URL           = PEOPLE_SITES_URL + "/{site}";
	protected final String PEOPLE_FAVORITE_SITES_URL = PEOPLE_URL + "/favorite-sites";
	protected final String PEOPLE_PREFERENCES_URL    = PEOPLE_URL + "/preferences";
	protected final String PEOPLE_PREFERENCE_URL     = PEOPLE_PREFERENCES_URL + "/{preference}";
	protected final String PEOPLE_NETWORKS_URL       = PEOPLE_URL + "/networks";
	protected final String PEOPLE_NETWORK_URL        = PEOPLE_NETWORKS_URL + "/{network}";
	protected final String PEOPLE_ACTIVITIES_URL     = PEOPLE_URL + "/activities";
	protected final String TAGS_URL                  = "{network}" + VERSION + "tags";
	protected final String TAG_URL                   = TAGS_URL + "/{tag}";
	protected final String BASE_NODE_URL             = "{network}" + VERSION + "nodes/{node}/";
	protected final String NODE_COMMENTS_URL         = BASE_NODE_URL + "comments";
	protected final String NODE_COMMENT_URL          = NODE_COMMENTS_URL + "/{comment}";
	protected final String NODE_TAGS_URL             = BASE_NODE_URL + "tags";
	protected final String NODE_TAG_URL              = NODE_TAGS_URL + "/{tag}";
	protected final String NODE_RATINGS_URL          = BASE_NODE_URL + "ratings";
	protected final String NODE_RATING_URL           = NODE_RATINGS_URL + "/{rating}";
}
