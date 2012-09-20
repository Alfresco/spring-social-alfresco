
package org.springframework.social.alfresco.api.entities.exceptions;


public class UnknownRoleException
    extends RuntimeException
{

    private static final long serialVersionUID = -1000609622132230409L;


    public UnknownRoleException(String role)
    {
        super("Unknown Role: " + role);
    }


    public UnknownRoleException(String role, Throwable cause)
    {
        super("Unknown Role: " + role, cause);
    }

}
