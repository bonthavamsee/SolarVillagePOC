package org.solarvillage.neworders;

/**
 * This class was automatically generated by the data modeler tool.
 */

public class Order implements java.io.Serializable {

	static final long serialVersionUID = 1L;

	@org.kie.api.definition.type.Label(value = "Applicant Name")
	private java.lang.String applicantName;
	@org.kie.api.definition.type.Label(value = "Applicant Address")
	private java.lang.String address;
	@org.kie.api.definition.type.Label(value = "Property Ownership Type")
	private java.lang.String propertyOwnershipType;
	@org.kie.api.definition.type.Label(value = "Electrical Connection Number")
	private java.lang.String electricalConnectionNumber;
	@org.kie.api.definition.type.Label(value = "Building Registration Number")
	private java.lang.String buildingRegistrationNumber;
	@org.kie.api.definition.type.Label(value = "Building Description")
	private java.lang.String buildingDescription;
	@org.kie.api.definition.type.Label(value = "Application Approval Status")
	private java.lang.String approvalStatus;

	public Order() {
	}

	public java.lang.String getApplicantName() {
		return this.applicantName;
	}

	public void setApplicantName(java.lang.String applicantName) {
		this.applicantName = applicantName;
	}

	public java.lang.String getAddress() {
		return this.address;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	public java.lang.String getPropertyOwnershipType() {
		return this.propertyOwnershipType;
	}

	public void setPropertyOwnershipType(java.lang.String propertyOwnershipType) {
		this.propertyOwnershipType = propertyOwnershipType;
	}

	public java.lang.String getElectricalConnectionNumber() {
		return this.electricalConnectionNumber;
	}

	public void setElectricalConnectionNumber(
			java.lang.String electricalConnectionNumber) {
		this.electricalConnectionNumber = electricalConnectionNumber;
	}

	public java.lang.String getBuildingRegistrationNumber() {
		return this.buildingRegistrationNumber;
	}

	public void setBuildingRegistrationNumber(
			java.lang.String buildingRegistrationNumber) {
		this.buildingRegistrationNumber = buildingRegistrationNumber;
	}

	public java.lang.String getBuildingDescription() {
		return this.buildingDescription;
	}

	public void setBuildingDescription(java.lang.String buildingDescription) {
		this.buildingDescription = buildingDescription;
	}

	public java.lang.String getApprovalStatus() {
		return this.approvalStatus;
	}

	public void setApprovalStatus(java.lang.String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Order(java.lang.String applicantName, java.lang.String address,
			java.lang.String propertyOwnershipType,
			java.lang.String electricalConnectionNumber,
			java.lang.String buildingRegistrationNumber,
			java.lang.String buildingDescription,
			java.lang.String approvalStatus) {
		this.applicantName = applicantName;
		this.address = address;
		this.propertyOwnershipType = propertyOwnershipType;
		this.electricalConnectionNumber = electricalConnectionNumber;
		this.buildingRegistrationNumber = buildingRegistrationNumber;
		this.buildingDescription = buildingDescription;
		this.approvalStatus = approvalStatus;
	}
	
	@Override
	public String toString(){
	    return (new String("Applicant Name: "+applicantName+"\nApplicant Address:"+address+"\nProperty Ownership Type:"+propertyOwnershipType+"\nElectrical Connection Number:"+electricalConnectionNumber
	            +"\nBuilding Registration Number:"+buildingRegistrationNumber+"\nBuilding Description:"+buildingDescription+"\nApplication Approval Status:"+approvalStatus+"\n"));
	}

}