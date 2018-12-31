package org.customWorkItemHandlers;

import java.util.HashMap;

import org.domainModel.controller.RestClient;
import org.domainModel.govtPermit.*;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;

public class RaisePermit implements org.kie.api.runtime.process.WorkItemHandler {

	@Override
	public void abortWorkItem(WorkItem arg0, WorkItemManager arg1) {
		
		System.out.println("In Raise Request abortWorkItem");
		arg1.abortWorkItem(arg0.getId());
		
	}

	@Override
	public void executeWorkItem(WorkItem arg0, WorkItemManager arg1) {
		
		System.out.println("In Raise Request WorkItem");
		
		//Read Params and Execute Business Logic
		ElectricalPermit ep=null;
		StructuralPermit sp=null;
		BaseResponse bsr=null;
		RestClient client=new RestClient();
		String url=arg0.getParameter("url").toString();
		String requestType=arg0.getParameter("requestType").toString();
		String applicationNumber=arg0.getParameter("applicationNumber").toString();
		String varNum=arg0.getParameter("varNum").toString();
		if(requestType.equalsIgnoreCase("ElectricalPermit"))
		{
			ep=new ElectricalPermit();
			ep.setPermitApplicationNumber(applicationNumber);
			ep.setElectricalConnnectionNumber(varNum);
			ep.setPermitStatus(PermitStatus.PENDING);
			bsr=(BaseResponse)client.restPost(ep, url, "ElectricalPermit", "BaseResponse");
		}
		else if(requestType.equalsIgnoreCase("StructuralPermit"))
		{
			sp=new StructuralPermit();
			sp.setPermitApplicationNumber(applicationNumber);
			sp.setBuildingRegistrationNumber(varNum);
			sp.setPermitStatus(PermitStatus.PENDING);
			bsr=(BaseResponse)client.restPost(sp, url, "StructuralPermit", "BaseResponse");
		}
		else
		{
			throw new RuntimeException("RaisePermit Invalid Request Type:"+requestType);
		}
		

		
		//Pass Results
		if(bsr == null)
			throw new RuntimeException("RaisePermit Null Response");
		HashMap<String, Object> results=new HashMap<String, Object>();
		results.put("response", bsr.toString());
		arg1.completeWorkItem(arg0.getId(), results);
		
	}

}
