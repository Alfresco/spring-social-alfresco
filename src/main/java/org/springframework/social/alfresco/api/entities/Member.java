
package org.springframework.social.alfresco.api.entities;

import org.springframework.social.alfresco.api.entities.people.Person;


public class Member
{
    private Role   role;
    private String id;
    private Person person;


    public Role getRole()
    {
        return role;
    }


    public void setRole(Role role)
    {
        this.role = role;
    }


    public String getId()
    {
        return id;
    }


    public void setId(String id)
    {
        this.id = id;
    }


    public Person getPerson()
    {
        return person;
    }


    public void setPerson(Person person)
    {
        this.person = person;
    }

}
