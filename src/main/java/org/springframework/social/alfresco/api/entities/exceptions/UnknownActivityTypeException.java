
package org.springframework.social.alfresco.api.entities.exceptions;


public class UnknownActivityTypeException
    extends RuntimeException
{
    private static final long serialVersionUID = 5308662077069821525L;


    public UnknownActivityTypeException(String activityType)
    {
        super("Unknown Activity Type: " + activityType);
    }


    public UnknownActivityTypeException(String activityType, Throwable cause)
    {
        super("Unknown Activity Type: " + activityType, cause);
    }

}
