
package org.springframework.social.alfresco.api.entities;


public class NetworkExtended
{
    private String  id;
    private boolean homeNetwork;
    private Network network;


    public String getId()
    {
        return id;
    }


    public void setId(String id)
    {
        this.id = id;
    }


    public boolean isHomeNetwork()
    {
        return homeNetwork;
    }


    public void setHomeNetwork(boolean homeNetwork)
    {
        this.homeNetwork = homeNetwork;
    }


    public Network getNetwork()
    {
        return network;
    }


    public void setNetwork(Network network)
    {
        this.network = network;
    }


}
