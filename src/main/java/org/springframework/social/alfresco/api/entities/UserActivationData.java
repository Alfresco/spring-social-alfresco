package org.springframework.social.alfresco.api.entities;

public class UserActivationData
{
	private UserRegistrationResponse registration;
	private Long defaultId;

	public UserRegistrationResponse getRegistration() {
		return registration;
	}

	public void setRegistration(UserRegistrationResponse registration) {
		this.registration = registration;
	}

	public Long getDefault() {
		return defaultId;
	}

	public void setDefault(Long defaultId) {
		this.defaultId = defaultId;
	}
	
	
	
}
