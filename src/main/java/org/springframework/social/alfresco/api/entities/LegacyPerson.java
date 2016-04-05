package org.springframework.social.alfresco.api.entities;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonIgnoreProperties
@JsonSerialize(include = Inclusion.NON_NULL)
public class LegacyPerson
{
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String url;
	private Boolean enabled;
	private String jobtitle;
	private String organization;
	private String organizationId; 
	private String location;
	private String telephone;
	private String mobile;
	private String companyaddress1;
	private String companyaddress2;
	private String companyaddress3;
	private String companypostcode;
	private String companytelephone;
	private String companyfax;
	private String companyemail;
	private String skype;
	private String instantmsg;
	private String userStatus;
	private Long userStatusTime;
	private String googleusername;
	private Integer quota;
	private Integer sizeCurrent;
	private Boolean emailFeedDisabled;
	private String persondescription;

	public LegacyPerson()
	{
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getJobtitle() {
		return jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCompanyaddress1() {
		return companyaddress1;
	}

	public void setCompanyaddress1(String companyaddress1) {
		this.companyaddress1 = companyaddress1;
	}

	public String getCompanyaddress2() {
		return companyaddress2;
	}

	public void setCompanyaddress2(String companyaddress2) {
		this.companyaddress2 = companyaddress2;
	}

	public String getCompanyaddress3() {
		return companyaddress3;
	}

	public void setCompanyaddress3(String companyaddress3) {
		this.companyaddress3 = companyaddress3;
	}

	public String getCompanypostcode() {
		return companypostcode;
	}

	public void setCompanypostcode(String companypostcode) {
		this.companypostcode = companypostcode;
	}

	public String getCompanytelephone() {
		return companytelephone;
	}

	public void setCompanytelephone(String companytelephone) {
		this.companytelephone = companytelephone;
	}

	public String getCompanyfax() {
		return companyfax;
	}

	public void setCompanyfax(String companyfax) {
		this.companyfax = companyfax;
	}

	public String getCompanyemail() {
		return companyemail;
	}

	public void setCompanyemail(String companyemail) {
		this.companyemail = companyemail;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getInstantmsg() {
		return instantmsg;
	}

	public void setInstantmsg(String instantmsg) {
		this.instantmsg = instantmsg;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public Long getUserStatusTime() {
		return userStatusTime;
	}

	public void setUserStatusTime(Long userStatusTime) {
		this.userStatusTime = userStatusTime;
	}

	public String getGoogleusername() {
		return googleusername;
	}

	public void setGoogleusername(String googleusername) {
		this.googleusername = googleusername;
	}

	public Integer getQuota() {
		return quota;
	}

	public void setQuota(Integer quota) {
		this.quota = quota;
	}

	public Integer getSizeCurrent() {
		return sizeCurrent;
	}

	public void setSizeCurrent(Integer sizeCurrent) {
		this.sizeCurrent = sizeCurrent;
	}

	public Boolean getEmailFeedDisabled() {
		return emailFeedDisabled;
	}

	public void setEmailFeedDisabled(Boolean emailFeedDisabled) {
		this.emailFeedDisabled = emailFeedDisabled;
	}

	public String getPersondescription() {
		return persondescription;
	}

	public void setPersondescription(String persondescription) {
		this.persondescription = persondescription;
	}

	@Override
	public String toString() {
		return "LegacyPerson [userName=" + userName + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", url=" + url + ", enabled="
				+ enabled + ", jobtitle=" + jobtitle + ", organization="
				+ organization + ", organizationId=" + organizationId
				+ ", location=" + location + ", telephone=" + telephone
				+ ", mobile=" + mobile + ", companyaddress1=" + companyaddress1
				+ ", companyaddress2=" + companyaddress2 + ", companyaddress3="
				+ companyaddress3 + ", companypostcode=" + companypostcode
				+ ", companytelephone=" + companytelephone + ", companyfax="
				+ companyfax + ", companyemail=" + companyemail + ", skype="
				+ skype + ", instantmsg=" + instantmsg + ", userStatus="
				+ userStatus + ", userStatusTime=" + userStatusTime
				+ ", googleusername=" + googleusername + ", quota=" + quota
				+ ", sizeCurrent=" + sizeCurrent + ", emailFeedDisabled="
				+ emailFeedDisabled + ", persondescription="
				+ persondescription + "]";
	}
}
