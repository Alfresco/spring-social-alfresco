
package org.springframework.social.alfresco.api.entities.people;

import org.springframework.social.alfresco.api.entities.Role;


public class SiteMembership
    extends PersonSite
{
    private Role role;


    public Role getRole()
    {
        return role;
    }


    public void setRole(Role role)
    {
        this.role = role;
    }
}
