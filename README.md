spring-social-alfresco
======================

How to use
````java
    AlfrescoConnectionFactory connectionFactory = new AlfrescoConnectionFactory(consumerKey, consumerSecret);
    
    OAuth2Parameters parameters = new OAuth2Parameters();
    parameters.setRedirectUri("http://localhost:8080/alfoauthsample/mycallback.html");  
    parameters.setScope(Alfresco.DEFAULT_SCOPE);
    parameters.setState("test");
    
    
    String authUrl = connectionFactory.getOAuthOperations().buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, parameters);
    
    //Do the dance here ...
    
    AccessGrant accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(accessToken, redirectUri, null);
    
    //Persist your tokens here ...
    
    Connection<Alfresco> connection = connectionFactory.createConnection(accessGrant);
    Alfresco alfresco = connection.getApi();
    
    //Use Alfresco api here
````
    
    
Refreshing Tokens
 ````java   
    //Refresh AccessGrant & Connection 
    accessGrant = connectionFactory.getOAuthOperations().refreshAccess(accessGrant.getRefreshToken(), Alfresco.DEFAULT_SCOPE, null);
    alfresco = connectionFactory.createConnection(accessGrant).getApi();
````    
    Note: Refresh tokens are only returned on the initial request.  When refreshing access, you will not receive a new refresh token.  Insure you persist your refresh token so it is not lost.