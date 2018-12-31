package org.domainModel.govtPermit;

public enum PermitStatus {
	ACCEPTED("Accepted"),
	REJECTED("Rejected"),
	PENDING("Pending"),
	UNDEFINED("Undefined");
	
	String value;
	private PermitStatus(String value)
	{
		this.value=value;
	}
}
