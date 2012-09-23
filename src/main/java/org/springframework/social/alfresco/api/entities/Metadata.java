
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
