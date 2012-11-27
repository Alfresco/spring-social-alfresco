/*
 * Copyright 2012 Alfresco Software Limited.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * This file is part of an unsupported extension to Alfresco.
 */
package org.springframework.social.alfresco.api.entities;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.social.alfresco.api.entities.exceptions.UnknownActivityTypeException;
import org.springframework.social.alfresco.api.entities.exceptions.UnknownRoleException;

public class Activity
{
    public static final String SITEID = "siteId";
    public static final String WHO    = "who";


    public static enum Who
    {
        me, others
    };


    private String          postPersonId;
    private String          id;
    private String          siteId;
    private String          networkId;
    private String          feedPersonId;
    private ActivitySummary activitySummary;
    private String          activityType;
    private Date            postedAt;


    public String getPostPersonId()
    {
        return postPersonId;
    }


    public void setPostPersonId(String postPersonId)
    {
        this.postPersonId = postPersonId;
    }


    public String getId()
    {
        return id;
    }


    public void setId(String id)
    {
        this.id = id;
    }


    public String getSiteId()
    {
        return siteId;
    }


    public void setSiteId(String siteId)
    {
        this.siteId = siteId;
    }


    public String getNetworkId()
    {
        return networkId;
    }


    public void setNetworkId(String networkId)
    {
        this.networkId = networkId;
    }


    public String getFeedPersonId()
    {
        return feedPersonId;
    }


    public void setFeedPersonId(String feedPersonId)
    {
        this.feedPersonId = feedPersonId;
    }


    public ActivitySummary getActivitySummary()
    {
        return activitySummary;
    }


    public void setActivitySummary(ActivitySummary activitySummary)
    {
        this.activitySummary = activitySummary;
    }


    public String getActivityType()
    {
        return activityType;
    }


    public void setActivityType(String activityType)
    {
        if (!validateActivityType(activityType))
        {
            throw new UnknownActivityTypeException(activityType);
        }
        this.activityType = activityType;
    }


    public Date getPostedAt()
    {
        return postedAt;
    }


    public void setPostedAt(Date postedAt)
    {
        this.postedAt = postedAt;
    }


    public static class ActivitySummary
    {
        private String firstName;
        private String lastName;
        private String title;
        private String objectId;
        private String parentNodeRef;
        private String memberPersonId;
        private String memberFirstName;
        private String memberLastName;
        private String role;


        public String getFirstName()
        {
            return firstName;
        }


        public void setFirstName(String firstName)
        {
            this.firstName = firstName;
        }


        public String getLastName()
        {
            return lastName;
        }


        public void setLastName(String lastName)
        {
            this.lastName = lastName;
        }


        public String getTitle()
        {
            return title;
        }


        public void setTitle(String title)
        {
            this.title = title;
        }


        public String getObjectId()
        {
            return objectId;
        }


        public void setObjectId(String objectId)
        {
            this.objectId = objectId;
        }


        public String getParentNodeRef()
        {
            return parentNodeRef;
        }


        public void setParentNodeRef(String parentNodeRef)
        {
            this.parentNodeRef = parentNodeRef;
        }


        public String getMemberPersonId()
        {
            return memberPersonId;
        }


        public void setMemberPersonId(String memberPersonId)
        {
            this.memberPersonId = memberPersonId;
        }


        public String getMemberFirstName()
        {
            return memberFirstName;
        }


        public void setMemberFirstName(String memberFirstName)
        {
            this.memberFirstName = memberFirstName;
        }


        public String getMemberLastName()
        {
            return memberLastName;
        }


        public void setMemberLastName(String memberLastName)
        {
            this.memberLastName = memberLastName;
        }


        public Role getRole()
        {
            if (this.role != null)
            {
                return Role.valueOf(role);
            }
            else
            {
                return null;
            }

        }


        public void setRole(String role)
        {
            if (!validateRole(role))
            {
                throw new UnknownRoleException(role);
            }
            this.role = role;

        }
    }


    // TODO need to add to setActivityType
    private static boolean validateActivityType(String activityType)
    {
        List<String> activityTypes = new ArrayList<String>(22);
        activityTypes.add("org.alfresco.comments.comment-created");
        activityTypes.add("org.alfresco.comments.comment-updated");
        activityTypes.add("org.alfresco.comments.comment-deleted");
        activityTypes.add("org.alfresco.documentlibrary.files-added");
        activityTypes.add("org.alfresco.documentlibrary.files-updated");
        activityTypes.add("org.alfresco.documentlibrary.files-deleted");
        activityTypes.add("org.alfresco.documentlibrary.file-added");
        activityTypes.add("org.alfresco.documentlibrary.file-created");
        activityTypes.add("org.alfresco.documentlibrary.file-deleted");
        activityTypes.add("org.alfresco.documentlibrary.file-liked");
        activityTypes.add("org.alfresco.documentlibrary.file-updated");
        activityTypes.add("org.alfresco.documentlibrary.inline-edit");
        activityTypes.add("org.alfresco.documentlibrary.folder-liked");
        activityTypes.add("org.alfresco.site.user-joined");
        activityTypes.add("org.alfresco.site.user-left");
        activityTypes.add("org.alfresco.site.user-role-changed");
        activityTypes.add("org.alfresco.site.group-added");
        activityTypes.add("org.alfresco.site.group-removed");
        activityTypes.add("org.alfresco.site.group-role-changed");
        activityTypes.add("org.alfresco.discussions.reply-created");
        activityTypes.add("org.alfresco.subscriptions.followed");
        activityTypes.add("org.alfresco.subscriptions.subscribed");

        return activityTypes.contains(activityType);
    }


    private static boolean validateRole(String role)
    {
        List<String> roles = new ArrayList<String>(5);
        roles.add("");
        roles.add("SiteContributor");
        roles.add("SiteManager");
        roles.add("SiteCollaborator");
        roles.add("SiteConsumer");

        return roles.contains(role);
    }
}
