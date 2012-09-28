
package org.springframework.social.alfresco.connect.test;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;


/**
 * 
 * @author jottley
 * 
 */
public class CodeUrl

{

    public static final String CODE  = "code";
    public static final String SCOPE = "scope";
    public static final String STATE = "state";


    private URL                url;


    public CodeUrl(String authUrl)
        throws MalformedURLException
    {
        this.url = new URL(authUrl);
    }


    public String getQuery()
    {
        return url.getQuery();
    }


    private String[] parseQuery()
    {
        return this.getQuery().split("&");
    }


    public HashMap<String, String> getQueryMap()
    {
        HashMap<String, String> queryMap = new HashMap<String, String>();

        String[] query = this.parseQuery();
        for (int i = 0; i < query.length; i++)
        {
            String[] kv = query[i].split("=");

            queryMap.put(kv[0], kv[1]);
        }

        return queryMap;
    }


    @Override
    public String toString()
    {
        return url.toString();
    }
}
