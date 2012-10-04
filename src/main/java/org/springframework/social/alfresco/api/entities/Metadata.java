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


public class Metadata
{
    private String               uniqueId;
    private String               type;
    private ArrayList<Operation> operations;
    private String               parentResources;


    public String getUniqueId()
    {
        return uniqueId;
    }


    public void setUniqueId(String uniqueId)
    {
        this.uniqueId = uniqueId;
    }


    public String getType()
    {
        return type;
    }


    public void setType(String type)
    {
        this.type = type;
    }


    public ArrayList<Operation> getOperations()
    {
        return operations;
    }


    public void setOperations(ArrayList<Operation> operations)
    {
        this.operations = operations;
    }


    public String getParentResources()
    {
        return parentResources;
    }


    public void setParentResources(String parentResources)
    {
        this.parentResources = parentResources;
    }


    public static class Operation
    {
        private String               httpMethod;
        private String               title;
        private String               description;
        private ArrayList<Parameter> parameters;


        public String getHttpMethod()
        {
            return httpMethod;
        }


        public void setHttpMethod(String httpMethod)
        {
            this.httpMethod = httpMethod;
        }


        public String getTitle()
        {
            return title;
        }


        public void setTitle(String title)
        {
            this.title = title;
        }


        public String getDescription()
        {
            return description;
        }


        public void setDescription(String description)
        {
            this.description = description;
        }


        public ArrayList<Parameter> getParameters()
        {
            return parameters;
        }


        public void setParameters(ArrayList<Parameter> parameters)
        {
            this.parameters = parameters;
        }


        public static class Parameter
        {
            private String  name;
            private boolean required;
            private String  title;
            private String  description;
            private String  dataType;
            private boolean allowMultiple;
            private String  paramType;


            public String getName()
            {
                return name;
            }


            public void setName(String name)
            {
                this.name = name;
            }


            public boolean isRequired()
            {
                return required;
            }


            public void setRequired(boolean required)
            {
                this.required = required;
            }


            public String getTitle()
            {
                return title;
            }


            public void setTitle(String title)
            {
                this.title = title;
            }


            public String getDescription()
            {
                return description;
            }


            public void setDescription(String description)
            {
                this.description = description;
            }


            public String getDataType()
            {
                return dataType;
            }


            public void setDataType(String dataType)
            {
                this.dataType = dataType;
            }


            public boolean isAllowMultiple()
            {
                return allowMultiple;
            }


            public void setAllowMultiple(boolean allowMultiple)
            {
                this.allowMultiple = allowMultiple;
            }


            public String getParamType()
            {
                return paramType;
            }


            public void setParamType(String paramType)
            {
                this.paramType = paramType;
            }
        }
    }
}
