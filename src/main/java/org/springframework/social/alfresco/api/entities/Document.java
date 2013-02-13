package org.springframework.social.alfresco.api.entities;

import java.math.BigInteger;

/**
 * Representation of a document node.
 * 
 * @author steveglover
 *
 */
public class Document extends Node
{
	private String mimeType;
	private BigInteger sizeInBytes;
	private String versionLabel;

	public Document()
	{
	}

	public String getMimeType()
	{
		return mimeType;
	}

	public BigInteger getSizeInBytes()
	{
		return sizeInBytes;
	}

	public String getVersionLabel()
	{
		return versionLabel;
	}

	@Override
	public String toString()
	{
		return "Document [mimeType=" + mimeType + ", sizeInBytes="
				+ sizeInBytes + ", versionLabel=" + versionLabel + "]";
	}
}
