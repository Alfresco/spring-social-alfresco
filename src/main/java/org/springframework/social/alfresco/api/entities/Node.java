package org.springframework.social.alfresco.api.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.FileableCmisObject;
import org.apache.chemistry.opencmis.client.api.Property;
import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.apache.chemistry.opencmis.client.api.Relationship;
import org.apache.chemistry.opencmis.client.api.Tree;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.data.PropertyData;
import org.apache.chemistry.opencmis.commons.definitions.FolderTypeDefinition;

public class Node implements Serializable
{
	private static final long serialVersionUID = -5027938359868278498L;

	private String nodeId;
	private Map<String, Serializable> properties;

	public String getRawNodeId()
	{
		return nodeId;
	}

	public String getNodeId()
	{
		return nodeId;
	}

	public boolean isFolder()
	{
		return false;
	}

	public void setNodeId(String nodeId)
	{
		this.nodeId = nodeId;
	}

	public void setProperties(Map<String, Serializable> properties)
	{
		this.properties = properties;
	}

	public Map<String, Serializable> getProperties()
	{
		return properties;
	}
	
	// TODO getFirstValue replace with getValues
	// how to determine type of result to choose Node or FolderNode
	public static Node createNode(QueryResult qr)
	{
		Node n = new Node();
		List<PropertyData<?>> props = qr.getProperties();
		Map<String, Serializable> properties = new HashMap<String, Serializable>();

		for(PropertyData<?> p : props)
		{
			properties.put(p.getDisplayName(), (Serializable)p.getFirstValue());
		}
		
		n.setNodeId((String)qr.getPropertyById(PropertyIds.OBJECT_ID).getFirstValue());
		
		return n;
	}

	public static Node createNode(Tree<FileableCmisObject> tree)
	{
		FileableCmisObject fco = tree.getItem();
		FolderNode folder = (FolderNode)Node.createNode(fco);
		for(Tree<FileableCmisObject> child : tree.getChildren())
		{
			Node childNode = Node.createNode(child);
			folder.addNode(childNode);
		}
		return folder;
	}

	public static Node createNode(CmisObject o)
	{
		if(o instanceof FileableCmisObject)
		{
			return createNode((FileableCmisObject)o);
		}
		else if(o instanceof Relationship)
		{
			return createNode((Relationship)o);
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	public static Node createNode(Relationship r)
	{
		Node n = new Node();
		n.setNodeId(r.getId());

		Map<String, Serializable> properties = new HashMap<String, Serializable>();
		for(Property<?> p : r.getProperties())
		{
			properties.put(p.getDisplayName(), p.getValueAsString());
		}
		n.setProperties(properties);
		return n;
	}

	public static Node createNode(FileableCmisObject fco)
	{
		Node n = null;
		if(fco.getBaseType() instanceof FolderTypeDefinition)
		{
			n = new FolderNode();
		}
		else
		{
			n = new Node();
		}

		n.setNodeId(fco.getId());

		Map<String, Serializable> properties = new HashMap<String, Serializable>();
		for(Property<?> p : fco.getProperties())
		{
			properties.put(p.getDisplayName(), p.getValueAsString());
		}
		n.setProperties(properties);
		return n;
	}
}
