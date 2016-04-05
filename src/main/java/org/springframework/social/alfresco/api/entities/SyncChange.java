/*
 * Copyright 2014 Alfresco Software, Ltd.  All rights reserved.
 *
 * License rights for this program may be obtained from Alfresco Software, Ltd. 
 * pursuant to a written agreement and any use of this program without such an 
 * agreement is prohibited. 
 */
package org.springframework.social.alfresco.api.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Bean representing a (node) change.
 * 
 * @author steveglover
 *
 */
public class SyncChange implements Serializable
{
	private static final long serialVersionUID = 6046151699346180318L;

	private static final AtomicLong NEXT_ID = new AtomicLong();
    
    private boolean conflict;
    private boolean skip;
    private String id;
    private String username; // the id of the user who made the change
    private ChangeType type;
    private String name;
    private String toName;
    private List<String> parentNodeIds;
    private List<String> toParentNodeIds;
    private String path;
    private String toPath;
	private String nodeId;
    private Long eventTimestamp;
    private String checksum;
    private Long size;
    private String nodeType;
    private Long nodeTimestamp;
    private boolean error;
    private Set<String> aspects;

    public SyncChange()
    {   
        id = String.valueOf(NEXT_ID.incrementAndGet());
    }

    /**
     * Used by the desktop sync Java client.
     * 
     * @param type
     * @param name
     * @param toName
     * @param path
     * @param toPath
     * @param parentNodeId
     * @param toParentNodeId
     * @param nodeId
     * @param timestamp
     * @param checksum
     * @param size
     * @param nodeType
     * @param skip
     * @param conflict
     */
    public SyncChange(
            ChangeType type,
            String name,
            String toName,
            String path,
            String toPath,
            List<String> parentNodeIds,
            List<String> toParentNodeIds,
            String nodeId,
            Long eventTimestamp,
            String checksum,
            Long size,
            String nodeType,
            Long repositoryTimestamp,
            String username)
    {
        this(type, name, toName, path, toPath, parentNodeIds, toParentNodeIds, nodeId, eventTimestamp, checksum, size, nodeType, false, false, false, repositoryTimestamp, username);
    }

    public SyncChange(
    		String id,
            ChangeType type,
            String name,
            String toName,
            String path,
            String toPath,
            List<String> parentNodeIds,
            List<String> toParentNodeIds,
            String nodeId,
            Long eventTimestamp,
            String checksum,
            Long size,
            String nodeType,
            boolean skip,
            boolean conflict,
            boolean error,
            Long nodeTimestamp,
            String username)
    {
        this.id = id;
        this.name = name;
        this.toName = toName;
        this.type = type;
        this.path = path;
        this.toPath = toPath;
        this.parentNodeIds = parentNodeIds;
        this.toParentNodeIds = toParentNodeIds;
        this.nodeId = nodeId;
        this.eventTimestamp = eventTimestamp;
        this.checksum = checksum;
        this.size = size;
        this.nodeType = nodeType;
        this.skip = skip;
        this.error = error;
        this.conflict = conflict;
        this.nodeTimestamp = nodeTimestamp;
        this.username = username;
    }
    
    public SyncChange(
            ChangeType type,
            String name,
            String toName,
            String path,
            String toPath,
            List<String> parentNodeIds,
            List<String> toParentNodeIds,
            String nodeId,
            Long eventTimestamp,
            String checksum,
            Long size,
            String nodeType,
            boolean skip,
            boolean conflict,
            boolean error,
            Long nodeTimestamp,
            String username)
    {
    	this(String.valueOf(NEXT_ID.incrementAndGet()), type, name, toName, path, toPath, parentNodeIds, toParentNodeIds,
    			nodeId, eventTimestamp, checksum, size, nodeType, skip, conflict, error, nodeTimestamp, username);
    }

    public static SyncChange rename(String name, String toName, String path, List<String> parentNodeIds,
            String nodeId, long timestamp, String checksum, long size, String nodeType, boolean skip, boolean conflict,
            boolean error, Long repositoryTimestamp, String username)
    {
        SyncChange change = new SyncChange(ChangeType.RENAME_LOCAL, name, toName, path, null, parentNodeIds, null, nodeId,
        		timestamp, checksum, size,
                nodeType, skip, conflict, error, repositoryTimestamp, username);
        return change;
    }

    public static SyncChange create(String name, String path, List<String> parentNodeIds, String nodeId, long timestamp,
    		String checksum, long size, String nodeType, boolean skip, boolean conflict, boolean error,
    		Long repositoryTimestamp, String username)
    {
        SyncChange change = new SyncChange(ChangeType.CREATE_LOCAL, name, null, path, null, parentNodeIds, null,
        		nodeId, timestamp, checksum, size,
                nodeType, skip, conflict, error, repositoryTimestamp, username);
        return change;
    }
    
    public static SyncChange update(String name, String path, List<String> parentNodeIds, String nodeId, long timestamp, String checksum, long size,
            String nodeType, boolean skip, boolean conflict, boolean error, Long repositoryTimestamp, String username)
    {
        SyncChange change = new SyncChange(ChangeType.UPDATE_LOCAL, name, null, path, null, parentNodeIds, null,
        		nodeId, timestamp, checksum, size,
                nodeType, skip, conflict, error, repositoryTimestamp, username);
        return change;
    }

    public static SyncChange delete(String name, String path, List<String> parentNodeIds, String nodeId, long timestamp, String checksum, long size,
            String nodeType, boolean skip, boolean conflict, boolean error, Long repositoryTimestamp, String username)
    {
        SyncChange change = new SyncChange(ChangeType.DELETE_LOCAL, name, null, path, null, parentNodeIds, null,
        		nodeId, timestamp, checksum, size,
                nodeType, skip, conflict, error, repositoryTimestamp, username);
        return change;
    }

    public static SyncChange move(String name, String path, String toPath, List<String> parentNodeIds, List<String> toParentNodeIds,
            String nodeId, long timestamp, String checksum, long size, String nodeType, boolean skip, boolean conflict,
            boolean error, Long repositoryTimestamp, String username)
    {
        SyncChange change = new SyncChange(ChangeType.MOVE_LOCAL, name, null, path, toPath, parentNodeIds, toParentNodeIds,
        		nodeId, timestamp, checksum, size,
                nodeType, skip, conflict, error, repositoryTimestamp, username);
        return change;
    }

    public Set<String> getAspects()
    {
		return aspects;
	}

	public void setAspects(Set<String> aspects)
	{
		this.aspects = aspects;
	}

	public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public Long getNodeTimestamp()
    {
        return nodeTimestamp;
    }

    public void setNodeTimestamp(Long nodeTimestamp)
    {
        this.nodeTimestamp = nodeTimestamp;
    }

    public String getId()
    {
        return this.id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
    
	public boolean isSkip()
	{
        return skip;
    }

    public void setSkip(boolean skip)
    {
        this.skip = skip;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName)
    {
        this.toName = toName;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<String> getParentNodeIds()
    {
        return parentNodeIds;
    }

    public void setParentNodeIds(List<String> parentNodeIds)
    {
        this.parentNodeIds = parentNodeIds;
    }

    public ChangeType getType()
    {
        return this.type;
    }

    public void setType(ChangeType type)
    {
        this.type = type;
    }

    public String getToPath()
    {
		return toPath;
	}

	public void setToPath(String toPath)
	{
		this.toPath = toPath;
	}

	public String getPath()
    {
        return this.path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public String getNodeId()
    {
        return this.nodeId;
    }

    public void setNodeId(String nodeId)
    {
        this.nodeId = nodeId;
    }

    public Long getEventTimestamp()
    {
        return this.eventTimestamp;
    }

    public void setEventTimestamp(Long eventTimestamp)
    {
        this.eventTimestamp = eventTimestamp;
    }

    public String getChecksum()
    {
        return this.checksum;
    }

    public void setChecksum(String checksum)
    {
        this.checksum = checksum;
    }
    
    public List<String> getToParentNodeIds()
    {
        return toParentNodeIds;
    }

    public void setToParentNodeIds(List<String> toParentNodeIds)
    {
        this.toParentNodeIds = toParentNodeIds;
    }

    public Long getSize()
    {
        return size;
    }

    public void setSize(Long size)
    {
        this.size = size;
    }

    public boolean isConflict()
    {
        return conflict;
    }

    public void setConflict(boolean conflict)
    {
        this.conflict = conflict;
    }

    public String getNodeType()
    {
        return nodeType;
    }

    public void setNodeType(String nodeType)
    {
        this.nodeType = nodeType;
    }
    
    public void setError(boolean error)
    {
        this.error = error;
    }

    public boolean isError()
    {
        return error;
    }


    @Override
    public String toString()
    {
        return "Change [conflict=" + conflict + ", skip=" + skip + ", id=" + id
                + ", error=" + error + ", username=" + username
                + ", type=" + type + ", name=" + name + ", toName=" + toName
                + ", parentNodeIds=" + parentNodeIds + ", toParentNodeIds="
                + toParentNodeIds + ", path=" + path + ", toPath=" + toPath
                + ", nodeId=" + nodeId + ", eventTimestamp=" + eventTimestamp
                + ", checksum=" + checksum + ", size=" + size + ", nodeType="
                + nodeType + ", nodeTimestamp=" + nodeTimestamp
                + ", aspects=" + aspects + "]";
    }
}
