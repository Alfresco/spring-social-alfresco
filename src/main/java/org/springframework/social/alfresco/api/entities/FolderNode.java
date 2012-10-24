package org.springframework.social.alfresco.api.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.chemistry.opencmis.client.api.FileableCmisObject;
import org.apache.chemistry.opencmis.client.api.Tree;

public class FolderNode extends Node implements Serializable
{
	private static final long serialVersionUID = -7069586854942264572L;

	private List<FolderNode> folderNodes = new ArrayList<FolderNode>();
	private List<Node> documentNodes = new ArrayList<Node>();

	private Random random = new Random();

	public FolderNode(List<Tree<FileableCmisObject>> nodeTree)
	{
		for(Tree<FileableCmisObject> tree : nodeTree)
		{
			Node node = Node.createNode(tree);
			if(node instanceof FolderNode)
			{
				folderNodes.add((FolderNode)node);
			}
			else
			{
				documentNodes.add(node);
			}
		}
	}

	public FolderNode()
	{
		super();
	}

	public boolean isFolder()
	{
		return true;
	}
	
	public void addFolder(FolderNode folder)
	{
		folderNodes.add(folder);
	}
	
	public void addNode(Node node)
	{
		documentNodes.add(node);
	}
	
	public List<FolderNode> getFolderNodes()
	{
		return folderNodes;
	}

	public List<Node> getDocumentNodes()
	{
		return documentNodes;
	}
	
	public Node selectRandomFolderNode()
	{
		int idx = random.nextInt(folderNodes.size());
		return folderNodes.get(idx);
	}
	
	public Node selectRandomDocumentNode()
	{
		int idx = random.nextInt(documentNodes.size());
		return documentNodes.get(idx);
	}
}
