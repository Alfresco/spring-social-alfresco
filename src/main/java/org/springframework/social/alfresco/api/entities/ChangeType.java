package org.springframework.social.alfresco.api.entities;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum ChangeType
{
    CREATE_REPOS, UPDATE_REPOS, UPDATE_CONTENT_REPOS, UPDATE_METADATA_REPOS, DELETE_REPOS, MOVE_REPOS, RENAME_REPOS, RENAME_UPDATE_REPOS, // change has happened on the server
    CREATE_LOCAL, UPDATE_LOCAL, DELETE_LOCAL, MOVE_LOCAL, RENAME_LOCAL, RENAME_UPDATE_LOCAL, // change has happened on the client
    SUBSCRIBE,                                                                               // client must reflect subscribe
    UNSUBSCRIBE,                                                                             // client must reflect unsubscribe
	CONFLICT_CREATE_REPOS, CONFLICT_UPDATE_REPOS, CONFLICT_DELETE_REPOS, CONFLICT_MOVE_REPOS,// client must resolve (these need to be removed when we swap over to the new conflict resolution mechanism)
    ERROR;                                                                                   // client should report

    public boolean isClientChange()
    {
        return ClientChangeTypes.contains(this);
    }

    public boolean isRepoChange()
    {
        return RepoChangeTypes.contains(this);
    }
    
    public boolean isNodeMove()
    {
        return NodeMoveChangeTypes.contains(this);
    }
    
    public boolean isCRUD()
    {
        return NodeCRUDChangeTypes.contains(this);
    }
    
    public boolean isRemove()
    {
        return NodeRemoveChangeTypes.contains(this);
    }

    public boolean isUpdate()
    {
        return NodeUpdateChangeTypes.contains(this);
    }
    
    public boolean isSameSource(ChangeType other)
    {
        boolean isClientChange = isClientChange();
        boolean otherIsClientChange = other.isClientChange();
        return isClientChange == otherIsClientChange;
    }

    public static int MAX_ORDINAL = UNSUBSCRIBE.ordinal();
    public static Set<ChangeType> ClientChangeTypes = new HashSet<ChangeType>(Arrays.asList(CREATE_LOCAL, UPDATE_LOCAL, DELETE_LOCAL, MOVE_LOCAL, RENAME_LOCAL, RENAME_UPDATE_LOCAL));
    public static Set<ChangeType> RepoChangeTypes = new HashSet<ChangeType>(Arrays.asList(CREATE_REPOS, UPDATE_REPOS, DELETE_REPOS, MOVE_REPOS, RENAME_REPOS, RENAME_UPDATE_REPOS));
    public static Set<ChangeType> NodeMoveChangeTypes = new HashSet<ChangeType>(Arrays.asList(MOVE_LOCAL, MOVE_REPOS, RENAME_LOCAL, RENAME_REPOS, RENAME_UPDATE_LOCAL, RENAME_UPDATE_REPOS));
    public static Set<ChangeType> NodeCRUDChangeTypes = new HashSet<ChangeType>(Arrays.asList(CREATE_LOCAL, UPDATE_LOCAL, DELETE_LOCAL, CREATE_REPOS, UPDATE_REPOS, DELETE_REPOS));
    public static Set<ChangeType> NodeRemoveChangeTypes = new HashSet<ChangeType>(Arrays.asList(DELETE_LOCAL, DELETE_REPOS));
    public static Set<ChangeType> NodeUpdateChangeTypes = new HashSet<ChangeType>(Arrays.asList(UPDATE_LOCAL, UPDATE_REPOS));
}
