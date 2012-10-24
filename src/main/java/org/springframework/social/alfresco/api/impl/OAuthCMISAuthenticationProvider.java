package org.springframework.social.alfresco.api.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.chemistry.opencmis.client.bindings.spi.AbstractAuthenticationProvider;
import org.apache.chemistry.opencmis.client.bindings.spi.BindingSession;
import org.apache.chemistry.opencmis.client.bindings.spi.cookies.CmisCookieManager;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.impl.Base64;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * An OpenCMIS OAuth authentication provider.
 * 
 * org.apache.chemistry.opencmis.binding.spi.type=atompub
 * org.apache.chemistry.opencmis.binding.atompub.url=https://api.alfresco.com/cmis/versions/1.0/atom
 * org.apache.chemistry.opencmis.binding.auth.classname=org.alfresco.cmis.client.authentication.OAuthCMISAuthenticationProvider
 * org.apache.chemistry.opencmis.binding.auth.http.basic=false
 * accessToken=7acc0801-9211-47e2-9854-8002c1a6f117
 * org.apache.chemistry.opencmis.binding.compression=true
 * org.apache.chemistry.opencmis.binding.cookies=true

 * @author steveglover
 *
 */
public class OAuthCMISAuthenticationProvider extends AbstractAuthenticationProvider
{
	public static final String ALFRESCO_ACCESS_TOKEN_URL = "https://api.alfresco.com/auth/oauth/versions/2/token";
	public static final String ALFRESCO_REFRESH_TOKEN_URL = "https://api.alfresco.com/auth/oauth/versions/2/token";

    private static final long serialVersionUID = 1L;

    private static final String WSSE_NAMESPACE = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
    private static final String WSU_NAMESPACE = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";

    private boolean sendUsernameToken = false;
    private CmisCookieManager cookieManager;
    private Map<String, List<String>> fixedHeaders = new HashMap<String, List<String>>();

    private String clientId;
    private String clientSecret;
    private String redirectUrl;
    
    private String authCode;
    private String accessTokenUrl;
    private String refreshTokenUrl;
    private HttpClient client;

    private String accessToken;
    private AccessToken accessTokenData;

	public static OAuthCMISAuthenticationProvider alfrescoOAuthProvider(String accessToken)
	{
		return new OAuthCMISAuthenticationProvider(accessToken);
	}

	public static OAuthCMISAuthenticationProvider alfrescoOAuthProvider(String clientId, String clientSecret, String redirectUrl, String authCode)
	{
		return new OAuthCMISAuthenticationProvider(clientId, clientSecret, redirectUrl, ALFRESCO_ACCESS_TOKEN_URL, ALFRESCO_REFRESH_TOKEN_URL, authCode);
	}

    public OAuthCMISAuthenticationProvider()
    {
    }

    /**
     * Authenticates with the Alfresco CMIS Public Api with a previously-generated access token (no refresh token support).
     * 
     * @param accessToken
     */
    public OAuthCMISAuthenticationProvider(String accessToken)
    {
    	this.accessToken = accessToken;
    }

    /**
     * Authenticates with the Alfresco CMIS Public Api by generating an access token to communicate with the public api.
     * Refresh support is provided.
     * 
     * @param accessToken
     */
    public OAuthCMISAuthenticationProvider(String clientId, String clientSecret, String redirectUrl, String accessTokenUrl, String refreshTokenUrl, String authCode)
    {
    	this.clientId = clientId;
    	this.clientSecret = clientSecret;
    	this.redirectUrl = redirectUrl;

    	this.accessTokenUrl = accessTokenUrl;
    	this.refreshTokenUrl = refreshTokenUrl;
    	this.authCode = authCode;
    	
        // Initialize manager
        MultiThreadedHttpConnectionManager manager = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
        params.setMaxTotalConnections(2);
        params.setMaxConnectionsPerHost(HostConfiguration.ANY_HOST_CONFIGURATION, 2);

        // Create the client
        client = new HttpClient(manager);

        generateAccessToken();
    }
    
    public AccessToken getAccessTokenData()
    {
		return accessTokenData;
	}

    private void generateAccessToken()
    {
    	PostMethod method = new PostMethod(accessTokenUrl);
    	NameValuePair[] data =
    	{
    	  new NameValuePair("redirect_uri", redirectUrl),
          new NameValuePair("client_id", clientId),
          new NameValuePair("client_secret", clientSecret),
          new NameValuePair("code", authCode),
          new NameValuePair("grant_type", "authorization_code")
        };
    	method.setRequestBody(data);

        try
        {
			int result = client.executeMethod(null, method, null);
			if(result == HttpStatus.SC_OK)
			{
				JSONParser parser = new JSONParser();
				try
				{
					JSONObject json = (JSONObject)parser.parse(method.getResponseBodyAsString());
					accessTokenData = new AccessToken(json);
				}
				catch (ParseException e)
				{
					throw new RuntimeException(e);
				}
			}
			else
			{
				throw new RuntimeException(method.getStatusText());
			}
		}
        catch (HttpException e)
        {
			throw new RuntimeException(e);
		}
        catch (IOException e)
        {
			throw new RuntimeException(e);
		}
    }
    
    private void refreshToken()
    {
    	PostMethod method = new PostMethod(refreshTokenUrl);
    	NameValuePair[] data =
    	{
    			new NameValuePair("refresh_token", clientId),
    			new NameValuePair("client_id", clientId),
    			new NameValuePair("client_secret", clientSecret),
    			new NameValuePair("grant_type", "refresh_token")
        };
    	method.setRequestBody(data);

        try
        {
			int result = client.executeMethod(null, method, null);
			if(result == HttpStatus.SC_OK)
			{
				JSONParser parser = new JSONParser();
				try
				{
					JSONObject json = (JSONObject)parser.parse(method.getResponseBodyAsString());
					accessTokenData = new AccessToken(json);
				}
				catch (ParseException e)
				{
					throw new RuntimeException(e);
				}
			}
			else
			{
				throw new RuntimeException(method.getStatusText());
			}
		}
        catch (HttpException e)
        {
			throw new RuntimeException(method.getStatusText());
		}
        catch (IOException e)
        {
			throw new RuntimeException(method.getStatusText());
		}
    }

    @Override
    public void setSession(BindingSession session) {
        super.setSession(session);

        if (isTrue(SessionParameter.COOKIES)) {
            cookieManager = new CmisCookieManager();
        }
    }
    
    private String getAccessToken()
    {
	    Object accessTokenObject = getSession().get("accessToken");
	    if (accessTokenObject instanceof String) {
	        return (String) accessTokenObject;
	    }
	
	    return null;
    }

    @Override
    public Map<String, List<String>> getHTTPHeaders(String url) {
        Map<String, List<String>> result = new HashMap<String, List<String>>(fixedHeaders);

        if(accessToken == null)
        {
        	accessToken = getAccessToken();
        }

        if(accessToken == null)
        {
	        long time = System.currentTimeMillis();
	        if(time > accessTokenData.getExpiresAt())
	        {
	        	refreshToken();
	        }
	        
	        StringBuilder sb = new StringBuilder("Bearer ");
	        sb.append(accessTokenData.getAccessToken());
	        result.put("Authorization", Collections.singletonList(sb.toString()));
        }
        else
        {
	        StringBuilder sb = new StringBuilder("Bearer ");
	        sb.append(accessToken);
	        result.put("Authorization", Collections.singletonList(sb.toString()));	
        }
        
        // cookies
        if (cookieManager != null) {
            Map<String, List<String>> cookies = cookieManager.get(url, result);
            if (!cookies.isEmpty()) {
                result.putAll(cookies);
            }
        }

        return result.isEmpty() ? null : result;
    }

    @Override
    public void putResponseHeaders(String url, int statusCode, Map<String, List<String>> headers) {
        if (cookieManager != null) {
            cookieManager.put(url, headers);
        }
    }

    @Override
    public org.w3c.dom.Element getSOAPHeaders(Object portObject) {
        // only send SOAP header if configured
        if (!sendUsernameToken) {
            return null;
        }

        // get user and password
        String user = getUser();
        String password = getPassword();

        // if no user is set, don't create SOAP header
        if (user == null) {
            return null;
        }

        if (password == null) {
            password = "";
        }

        // set time
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        long created = System.currentTimeMillis();
        long expires = created + 24 * 60 * 60 * 1000; // 24 hours

        // create the SOAP header
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element wsseSecurityElement = document.createElementNS(WSSE_NAMESPACE, "Security");

            Element wsuTimestampElement = document.createElementNS(WSU_NAMESPACE, "Timestamp");
            wsseSecurityElement.appendChild(wsuTimestampElement);

            Element tsCreatedElement = document.createElementNS(WSU_NAMESPACE, "Created");
            tsCreatedElement.appendChild(document.createTextNode(sdf.format(created)));
            wsuTimestampElement.appendChild(tsCreatedElement);

            Element tsExpiresElement = document.createElementNS(WSU_NAMESPACE, "Expires");
            tsExpiresElement.appendChild(document.createTextNode(sdf.format(expires)));
            wsuTimestampElement.appendChild(tsExpiresElement);

            Element usernameTokenElement = document.createElementNS(WSSE_NAMESPACE, "UsernameToken");
            wsseSecurityElement.appendChild(usernameTokenElement);

            Element usernameElement = document.createElementNS(WSSE_NAMESPACE, "Username");
            usernameElement.appendChild(document.createTextNode(user));
            usernameTokenElement.appendChild(usernameElement);

            Element passwordElement = document.createElementNS(WSSE_NAMESPACE, "Password");
            passwordElement.appendChild(document.createTextNode(password));
            passwordElement.setAttribute("Type",
                    "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
            usernameTokenElement.appendChild(passwordElement);

            Element createdElement = document.createElementNS(WSU_NAMESPACE, "Created");
            createdElement.appendChild(document.createTextNode(sdf.format(created)));
            usernameTokenElement.appendChild(createdElement);

            return wsseSecurityElement;
        } catch (ParserConfigurationException e) {
            // shouldn't happen...
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Returns the HTTP headers that are sent with all requests. The returned
     * map is mutable but not synchronized!
     */
    protected Map<String, List<String>> getFixedHeaders() {
        return fixedHeaders;
    }

    /**
     * Creates a basic authentication header value from a username and a
     * password.
     */
    protected List<String> createBasicAuthHeaderValue(String username, String password) {
        if (password == null) {
            password = "";
        }

        try {
            return Collections.singletonList("Basic "
                    + Base64.encodeBytes((username + ":" + password).getBytes("ISO-8859-1")));
        } catch (UnsupportedEncodingException e) {
            // shouldn't happen...
            return Collections.emptyList();
        }
    }

    /**
     * Returns <code>true</code> if the given parameter exists in the session
     * and is set to true, <code>false</code> otherwise.
     */
    protected boolean isTrue(String parameterName) {
        Object value = getSession().get(parameterName);

        if (value instanceof Boolean) {
            return ((Boolean) value).booleanValue();
        }

        if (value instanceof String) {
            return Boolean.parseBoolean((String) value);
        }

        return false;
    }
    
	public static class AccessToken
    {
    	private String accessToken;
    	private String tokenType;
    	private Long expiresAt;
    	private String refreshToken;
    	private String scope;

    	public AccessToken(JSONObject json)
    	{
    		this.accessToken = (String)json.get("access_token");
    		this.tokenType = (String)json.get("token_type");
    		Long expiresIn = (Long)json.get("expires_in");
    		this.expiresAt = System.currentTimeMillis() + expiresIn * 60 * 60 * 1000;
    		this.refreshToken = (String)json.get("refresh_token");
    		this.scope = (String)json.get("scope");
    	}

		public AccessToken(String accessToken, String tokenType, Long expiresIn, String refreshToken, String scope)
		{
			super();
			this.accessToken = accessToken;
			this.tokenType = tokenType;
			Long expires = expiresIn;
    		this.expiresAt = System.currentTimeMillis() + expires * 60 * 60 * 1000;
			this.refreshToken = refreshToken;
			this.scope = scope;
		}

		public String getAccessToken()
		{
			return accessToken;
		}

		public String getTokenType()
		{
			return tokenType;
		}

		public Long getExpiresAt()
		{
			return expiresAt;
		}

		public String getRefreshToken()
		{
			return refreshToken;
		}

		public String getScope()
		{
			return scope;
		}

		@Override
		public String toString()
		{
			return "AccessToken ["
					+ (accessToken != null ? "accessToken=" + accessToken
							+ ", " : "")
					+ (tokenType != null ? "tokenType=" + tokenType + ", " : "")
					+ (expiresAt != null ? "expiresAt=" + expiresAt + ", " : "")
					+ (refreshToken != null ? "refreshToken=" + refreshToken
							+ ", " : "")
					+ (scope != null ? "scope=" + scope : "") + "]";
		}
    }
}