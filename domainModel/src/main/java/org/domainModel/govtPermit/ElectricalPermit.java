package org.domainModel.govtPermit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ElectricalPermit")
public class ElectricalPermit {
	
	@Id
	@Column(name="permitApplicationNumber")
	String permitApplicationNumber;
	
	@Column(name="permitStatus")
	PermitStatus permitStatus;
	
	@Column(name="electricalConnnectionNumber")
	String electricalConnnectionNumber;

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

	public String getElectricalConnnectionNumber() {
		return electricalConnnectionNumber;
	}

	public void setElectricalConnnectionNumber(String electricalConnnectionNumber) {
		this.electricalConnnectionNumber = electricalConnnectionNumber;
	}
	
	@Override
	public String toString(){
		return ("permitApplicationNumber: "+permitApplicationNumber+"\t permitStatus:"+permitStatus.toString()
		+"\t electricalConnnectionNumber:"+electricalConnnectionNumber+"\n");
	}
	
}
