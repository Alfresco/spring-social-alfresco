package org.springframework.social.alfresco.api.entities.exceptions;

public class PublicApiException extends RuntimeException {
	private static final long serialVersionUID = 3158871384294793338L;

	public PublicApiException() {
		super();
	}

	public PublicApiException(String message, Throwable cause) {
		super(message, cause);
	}

	public PublicApiException(String message) {
		super(message);
	}

	public PublicApiException(Throwable cause) {
		super(cause);
	}

}
