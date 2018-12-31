package org.domainModel.govtPermit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="StructuralPermit")
public class StructuralPermit {
	
	@Id
	@Column(name="permitApplicationNumber")
	String permitApplicationNumber;
	
	@Column(name="permitStatus")
	PermitStatus permitStatus;
	
	@Column(name="buildingRegistrationNumber")
	String buildingRegistrationNumber;

	public String getPermitApplicationNumber() {
		return permitApplicationNumber;
	}

	public void setPermitApplicationNumber(String permitApplicationNumber) {
		this.permitApplicationNumber = permitApplicationNumber;
	}

	public PermitStatus getPermitStatus() {
		return permitStatus;
	}

	public void setPermitStatus(PermitStatus permitStatus) {
		this.permitStatus = permitStatus;
	}

	public String getBuildingRegistrationNumber() {
		return buildingRegistrationNumber;
	}

	public void setBuildingRegistrationNumber(String buildingRegistrationNumber) {
		this.buildingRegistrationNumber = buildingRegistrationNumber;
	}
	
	@Override
	public String toString(){
		return ("permitApplicationNumber: "+permitApplicationNumber+"\t permitStatus:"+permitStatus.toString()
		+"\t buildingRegistrationNumber:"+buildingRegistrationNumber+"\n");
	}
}
