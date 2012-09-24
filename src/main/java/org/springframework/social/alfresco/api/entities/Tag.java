
package org.springframework.social.alfresco.api.entities;


import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;


@JsonSerialize(include = Inclusion.NON_NULL)
public class Tag
{
    private String id;
    private String tag;


    public String getId()
    {
        return id;
    }


    public void setId(String id)
    {
        this.id = id;
    }


    public String getTag()
    {
        return tag;
    }


    public void setTag(String tag)
    {
        this.tag = tag;
    }


}
