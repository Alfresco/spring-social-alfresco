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
package org.springframework.social.alfresco.connect.test;


import org.springframework.social.alfresco.api.entities.AlfrescoList;


class Response<T>
{
    private AlfrescoList<T> list;
    private T       entry;


    public void setList(AlfrescoList<T> list)
    {
        this.list = list;
    }


    public AlfrescoList<T> getList()
    {
        return list;
    }


    public void setEntry(T entry)
    {
        this.entry = entry;
    }


    public T getEntry()
    {
        return entry;
    }


    public boolean isEntry()
    {
        return entry != null ? true : false;
    }


    public boolean isList()
    {
        return list != null ? true : false;
    }

}
