package org.springframework.social.alfresco.api.entities;

public class UserRegistration {
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String source;
	private String sourceUrl;

	public UserRegistration()
	{
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	
	
}
