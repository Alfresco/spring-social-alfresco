package org.springframework.social.alfresco.api.entities;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/*
 * 	{
		  "data" : {
		    "registration" : {
		      "email" : "joe.bloggs@mingmong",
		      "registrationDate" : "2012-11-13T14:08:37.000Z",
		      "id" : "activiti$20078",
		      "key" : "fe76ca23-3dbb-40ff-a20c-c19b0da46f85"
		    },
		    "default" : 114,
		    "home" : {
		      "id" : 114,
		      "name" : "mingmong",
		      "type" : 0,
		      "typeDisplayName" : "Free Network (0)",
		      "enabled" : true,
		      "className" : "PRIVATE_EMAIL_DOMAIN",
		      "classDisplayName" : "Free",
		      "creationDate" : "2012-11-13T15:42:13.324Z",
		      "usageQuota" : {
		        "fileUploadSizeUQ" : {
		          "q" : 52428800
		        },
		        "fileSizeUQ" : {
		          "u" : -2,
		          "q" : 10737418240
		        },
		        "siteCountUQ" : {
		          "u" : 0,
		          "q" : -1
		        },
		        "personCountUQ" : {
		          "u" : 0,
		          "q" : -1
		        },
		        "personIntOnlyCountUQ" : {
		          "u" : 0,
		          "q" : -1
		        },
		        "personNetworkAdminCountUQ" : {
		          "u" : 0,
		          "q" : 0
		        }
		      },
		      "domains" : [ "mingmong" ],
		      "tenant" : "mingmong"
		    },
		    "secondary" : [ ]
		  }
		}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserActivationResponse
{
//	private UserActivationData data;
//	
//	public UserActivationData getData() {
//		return data;
//	}
//
//	public void setData(UserActivationData data) {
//		this.data = data;
//	}
	
}
