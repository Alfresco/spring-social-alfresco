spring-social-alfresco
======================

How to use

    AlfrescoConnectionFactory connectionFactory = new AlfrescoConnectionFactory(consumerKey, consumerSecret);
    
    OAuth2Parameters parameters = new OAuth2Parameters();
    parameters.setRedirectUri("http://localhost:8080/alfoauthsample/mycallback.html");  parameters.setScope("public_api");
    parameters.setState("test");
    
    
    String authUrl = connectionFactory.getOAuthOperations().buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, parameters);
    
    //Do the dance here ...
    
    AccessGrant accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(accessToken, redirectUri, null);
    
    //Persist your tokens here ...
    
    Connection<Alfresco> connection = connectionFactory.createConnection(accessGrant);
    Alfresco alfresco = connection.getApi();
    
    //Use Alfresco api here