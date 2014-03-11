package org.springframework.social.alfresco.api;

import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.CmisVersion;

/**
 * 
 * @author sglover
 *
 */
public class CMISEndpoint
{
	private BindingType binding;
	private CmisVersion version;

	public CMISEndpoint(BindingType binding, CmisVersion version) {
		super();
		if(binding == BindingType.BROWSER && version == CmisVersion.CMIS_1_0)
		{
			throw new IllegalArgumentException("Browser binding not supported for CMIS 1.0");
		}
		this.binding = binding;
		this.version = version;
	}

	public BindingType getBinding()
	{
		return binding;
	}

	public CmisVersion getVersion()
	{
		return version;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((binding == null) ? 0 : binding.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CMISEndpoint other = (CMISEndpoint) obj;
		if (binding != other.binding)
			return false;
		if (version != other.version)
			return false;
		return true;
	}

	@Override
	public String toString() 
	{
		return "CMISEndpoint [binding=" + binding + ", version=" + version
				+ "]";
	}
}
