
package org.springframework.social.alfresco.connect.exception;


public class AlfrescoException
    extends RuntimeException
{
    private static final long serialVersionUID = 4856627580445924523L;


    public AlfrescoException(String message)
    {
        super(message);
    }


    public AlfrescoException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
