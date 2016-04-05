/*
 * Copyright 2016 Alfresco Software Limited.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * 
 * This file is part of an unsupported extension to Alfresco.
 */
package org.springframework.social.alfresco.api.entities;

import java.util.Collection;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * @author sglover
 *
 */
public class SyncFilters
{
    private Collection<String> nodeTypes;
    private Collection<String> nodeAspects;

    public SyncFilters()
    {
    }

    public SyncFilters(String filteredNodeTypes, String filteredNodeAspects)
    {
        this.nodeTypes = new LinkedList<String>();
        StringTokenizer st = new StringTokenizer(filteredNodeTypes, ",");
        while (st.hasMoreTokens())
        {
            this.nodeTypes.add(st.nextToken().trim());
        }

        this.nodeAspects = new LinkedList<String>();
        st = new StringTokenizer(filteredNodeAspects, ",");
        while (st.hasMoreTokens())
        {
            this.nodeAspects.add(st.nextToken().trim());
        }
    }

    public SyncFilters(Collection<String> nodeTypes,
            Collection<String> nodeAspects)
    {
        this.nodeTypes = nodeTypes;
        this.nodeAspects = nodeAspects;
    }

    public void setNodeTypes(Collection<String> nodeTypes)
    {
        this.nodeTypes = nodeTypes;
    }

    public void setNodeAspects(Collection<String> nodeAspects)
    {
        this.nodeAspects = nodeAspects;
    }

    public Collection<String> getNodeTypes()
    {
        return nodeTypes;
    }

    public Collection<String> getNodeAspects()
    {
        return nodeAspects;
    }

    @Override
    public String toString()
    {
        return "Filters [nodeTypes=" + nodeTypes + ", nodeAspects="
                + nodeAspects + "]";
    }
}