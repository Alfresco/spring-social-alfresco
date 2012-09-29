
package org.springframework.social.alfresco.connect.test;


import org.springframework.social.alfresco.api.entities.List;


class Response<T>
{
    private List<T> list;
    private T       entry;


    public void setList(List<T> list)
    {
        this.list = list;
    }


    public List<T> getList()
    {
        return list;
    }


    public void setEntry(T entry)
    {
        this.entry = entry;
    }


    public T getEntry()
    {
        return entry;
    }


    public boolean isEntry()
    {
        return entry != null ? true : false;
    }


    public boolean isList()
    {
        return list != null ? true : false;
    }

}
