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
package org.springframework.social.alfresco.connect.cmis;


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
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 
 * @author sglover
 * @author jottley
 *
 */
public class CMISOAuthAuthenticationProvider
    extends AbstractAuthenticationProvider
{
    private static final long         serialVersionUID  = -4631829446010151197L;

    private static final String       WSSE_NAMESPACE    = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
    private static final String       WSU_NAMESPACE     = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";

    private boolean                   sendUsernameToken = false;
    private CmisCookieManager         cookieManager;
    private Map<String, List<String>> fixedHeaders      = new HashMap<String, List<String>>();

    private String                    accessToken;

    public CMISOAuthAuthenticationProvider(String accessToken)
    {
        this.accessToken = accessToken;
    }


    @Override
    public void setSession(BindingSession session)
    {
        super.setSession(session);

        if (isTrue(SessionParameter.COOKIES))
        {
            cookieManager = new CmisCookieManager();
        }
    }


    @Override
    public Map<String, List<String>> getHTTPHeaders(String url)
    {
        Map<String, List<String>> result = new HashMap<String, List<String>>(fixedHeaders);


        StringBuilder sb = new StringBuilder("Bearer ");
        sb.append(accessToken);
        result.put("Authorization", Collections.singletonList(sb.toString()));


        // cookies
        if (cookieManager != null)
        {
            Map<String, List<String>> cookies = cookieManager.get(url, result);
            if (!cookies.isEmpty())
            {
                result.putAll(cookies);
            }
        }

        return result.isEmpty() ? null : result;
    }


    @Override
    public void putResponseHeaders(String url, int statusCode, Map<String, List<String>> headers)
    {
        if (cookieManager != null)
        {
            cookieManager.put(url, headers);
        }
    }


    @Override
    public org.w3c.dom.Element getSOAPHeaders(Object portObject)
    {
        // only send SOAP header if configured
        if (!sendUsernameToken)
        {
            return null;
        }

        // get user and password
        String user = getUser();
        String password = getPassword();

        // if no user is set, don't create SOAP header
        if (user == null)
        {
            return null;
        }

        if (password == null)
        {
            password = "";
        }

        // set time
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        long created = System.currentTimeMillis();
        long expires = created + 24 * 60 * 60 * 1000; // 24 hours

        // create the SOAP header
        try
        {
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
            passwordElement.setAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
            usernameTokenElement.appendChild(passwordElement);

            Element createdElement = document.createElementNS(WSU_NAMESPACE, "Created");
            createdElement.appendChild(document.createTextNode(sdf.format(created)));
            usernameTokenElement.appendChild(createdElement);

            return wsseSecurityElement;
        }
        catch (ParserConfigurationException e)
        {
            // shouldn't happen...
            e.printStackTrace();
        }

        return null;
    }


    /**
     * Returns the HTTP headers that are sent with all requests. The returned map is mutable but not synchronized!
     */
    protected Map<String, List<String>> getFixedHeaders()
    {
        return fixedHeaders;
    }


    /**
     * Creates a basic authentication header value from a username and a password.
     */
    protected List<String> createBasicAuthHeaderValue(String username, String password)
    {
        if (password == null)
        {
            password = "";
        }

        try
        {
            return Collections.singletonList("Basic " + Base64.encodeBytes((username + ":" + password).getBytes("ISO-8859-1")));
        }
        catch (UnsupportedEncodingException e)
        {
            // shouldn't happen...
            return Collections.emptyList();
        }
    }


    /**
     * Returns <code>true</code> if the given parameter exists in the session and is set to true, <code>false</code> otherwise.
     */
    protected boolean isTrue(String parameterName)
    {
        Object value = getSession().get(parameterName);

        if (value instanceof Boolean)
        {
            return ((Boolean)value).booleanValue();
        }

        if (value instanceof String)
        {
            return Boolean.parseBoolean((String)value);
        }

        return false;
    }
}
