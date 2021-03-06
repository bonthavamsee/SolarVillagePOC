package org.solarvillage.neworders;

/**
 * This class was automatically generated by the data modeler tool.
 */

public class HOAMeeting implements java.io.Serializable {

	static final long serialVersionUID = 1L;

	@org.kie.api.definition.type.Label("Association Name")
	private java.lang.String associationName;
	@org.kie.api.definition.type.Label("Meeting Date")
	private java.util.Date meetingDate;
	@org.kie.api.definition.type.Label("Representative Name")
	private java.lang.String representativeName;
	@org.kie.api.definition.type.Label("Association Head")
	private java.lang.String associationHead;
	@org.kie.api.definition.type.Label("Meeting Details")
	private java.lang.String meetingDetails;
	@org.kie.api.definition.type.Label(value = "Approval Status")
	private java.lang.String approvalStatus;

	public HOAMeeting() {
	}

	public java.lang.String getAssociationName() {
		return this.associationName;
	}

	public void setAssociationName(java.lang.String associationName) {
		this.associationName = associationName;
	}

	public java.util.Date getMeetingDate() {
		return this.meetingDate;
	}

	public void setMeetingDate(java.util.Date meetingDate) {
		this.meetingDate = meetingDate;
	}

	public java.lang.String getRepresentativeName() {
		return this.representativeName;
	}

	public void setRepresentativeName(java.lang.String representativeName) {
		this.representativeName = representativeName;
	}

	public java.lang.String getAssociationHead() {
		return this.associationHead;
	}

	public void setAssociationHead(java.lang.String associationHead) {
		this.associationHead = associationHead;
	}

	public java.lang.String getMeetingDetails() {
		return this.meetingDetails;
	}

	public void setMeetingDetails(java.lang.String meetingDetails) {
		this.meetingDetails = meetingDetails;
	}

	public java.lang.String getApprovalStatus() {
		return this.approvalStatus;
	}

	public void setApprovalStatus(java.lang.String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public HOAMeeting(java.lang.String associationName,
			java.util.Date meetingDate, java.lang.String representativeName,
			java.lang.String associationHead, java.lang.String meetingDetails,
			java.lang.String approvalStatus) {
		this.associationName = associationName;
		this.meetingDate = meetingDate;
		this.representativeName = representativeName;
		this.associationHead = associationHead;
		this.meetingDetails = meetingDetails;
		this.approvalStatus = approvalStatus;
	}
	
	
	@Override
	public String toString() {
		return (new String("\nAssociation Name:" + associationName
				+ "\nMeeting Date:" + meetingDate + "\nRepresentative Name:"
				+ representativeName + "\nAssociation Head:" + associationHead
				+ "\nMeeting Details:" + meetingDetails + "\nApproval Status:"+approvalStatus+"\n"));
	}

}