package org.springframework.social.alfresco.api.entities;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Bean representing a node change.
 * 
 * @author steveglover
 *
 */
public class Change implements Serializable
{
    private static final long serialVersionUID = 2071943412640607641L;
    private static final AtomicLong NEXT_ID = new AtomicLong();
    
    protected boolean conflict;
    protected boolean skip;
    protected String id;
    protected ChangeType type;
    protected String name;
    protected String toName;
    protected String parentNodeId;
    protected String toParentNodeId;
    protected String path;
	protected String toPath;
    protected String nodeId;
    protected Long timestamp;
    protected String checksum;
    protected Long size;
    private String nodeType;
    
    public Change()
    {        
    }

    /**
     * Used by the desktop sync Java client.
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
    public Change(
            ChangeType type,
            String name,
            String toName,
            String path,
            String toPath,
            String parentNodeId,
            String toParentNodeId,
            String nodeId,
            Long timestamp,
            String checksum,
            Long size,
            String nodeType)
    {
        this(type, name, toName, path, toPath, parentNodeId, toParentNodeId, nodeId, timestamp, checksum, size, nodeType, false, false);
    }

    private Change(
            ChangeType type,
            String name,
            String toName,
            String path,
            String toPath,
            String parentNodeId,
            String toParentNodeId,
            String nodeId,
            Long timestamp,
            String checksum,
            Long size,
            String nodeType,
            boolean skip,
            boolean conflict)
    {
        this.id = String.valueOf(NEXT_ID.incrementAndGet());
        this.name = name;
        this.toName = toName;
        this.type = type;
        this.path = path;
        this.toPath = toPath;
        this.parentNodeId = parentNodeId;
        this.toParentNodeId = toParentNodeId;
        this.nodeId = nodeId;
        this.timestamp = timestamp;
        this.checksum = checksum;
        this.size = size;
        this.nodeType = nodeType;
        this.skip = skip;
        this.conflict = conflict;
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

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
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

    public Long getTimestamp()
    {
        return this.timestamp;
    }

    public void setTimestamp(Long timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getChecksum()
    {
        return this.checksum;
    }

    public void setChecksum(String checksum)
    {
        this.checksum = checksum;
    }
    
    public String getToParentNodeId()
    {
        return toParentNodeId;
    }

    public void setToParentNodeId(String toParentNodeId)
    {
        this.toParentNodeId = toParentNodeId;
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

    @Override
    public String toString()
    {
        return "Change [conflict=" + conflict + ", skip=" + skip + ", id=" + id
                + ", type=" + type + ", name=" + name + ", toName=" + toName
                + ", parentNodeId=" + parentNodeId + ", toParentNodeId="
                + toParentNodeId + ", path=" + path + ", toPath=" + toPath
                + ", nodeId=" + nodeId + ", timestamp=" + timestamp
                + ", checksum=" + checksum + ", size=" + size + ", nodeType="
                + nodeType + "]";
    }
}
