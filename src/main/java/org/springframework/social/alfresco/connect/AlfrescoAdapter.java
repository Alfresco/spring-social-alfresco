/**
 * 
 */

package org.springframework.social.alfresco.connect;


import org.springframework.social.OperationNotPermittedException;
import org.springframework.social.alfresco.api.Alfresco;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;


/**
 * @author jottley
 * 
 */
public class AlfrescoAdapter
    implements ApiAdapter<Alfresco>
{

    public UserProfile fetchUserProfile(Alfresco alfresco)
    {
        // TODO Auto-generated method stub
        return null;
    }


    public void setConnectionValues(Alfresco alfresco, ConnectionValues values)
    {
        // TODO Auto-generated method stub

    }


    public boolean test(Alfresco alfresco)
    {
        // TODO Auto-generated method stub
        return false;
    }


    public void updateStatus(Alfresco alfresco, String status)
    {
        throw new OperationNotPermittedException("updateStatus not implemented.");

    }

}
