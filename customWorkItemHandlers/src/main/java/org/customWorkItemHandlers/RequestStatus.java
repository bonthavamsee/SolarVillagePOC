package org.customWorkItemHandlers;

import java.util.HashMap;

import org.domainModel.controller.RestClient;
import org.domainModel.govtPermit.BaseResponse;
import org.domainModel.govtPermit.ElectricalPermit;
import org.domainModel.govtPermit.PermitStatus;
import org.domainModel.govtPermit.StructuralPermit;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;

public class RequestStatus implements org.kie.api.runtime.process.WorkItemHandler{

	@Override
	public void abortWorkItem(WorkItem arg0, WorkItemManager arg1) {
		
		System.out.println("In Raise Request abortWorkItem");
		arg1.abortWorkItem(arg0.getId());
		
	}

	@Override
	public void executeWorkItem(WorkItem arg0, WorkItemManager arg1) {
		
		System.out.println("In Get Request Status WorkItem");
		
		//Read Params and Execute Business Logic
		ElectricalPermit ep=null;
		StructuralPermit sp=null;
		RestClient client=new RestClient();
		String url=arg0.getParameter("url").toString();
		String paramValue=arg0.getParameter("paramValue").toString();
		url=url+paramValue;
		String requestType=arg0.getParameter("requestType").toString();
		Object response=null;
		HashMap<String, Object> results=new HashMap<String, Object>();
		if(requestType.equalsIgnoreCase("ElectricalPermit")){
			ep=(ElectricalPermit)client.restGet(url, "ElectricalPermit");
			if(ep==null)
				results.put("permitStatus", PermitStatus.UNDEFINED.toString());
			else
			results.put("permitStatus", ep.getPermitStatus().toString());
			
		}
		else if(requestType.equalsIgnoreCase("StructuralPermit")){
			sp=(StructuralPermit)client.restGet(url, "StructuralPermit");
			if(sp==null)
				results.put("permitStatus", PermitStatus.UNDEFINED.toString());
			else
			results.put("permitStatus", sp.getPermitStatus().toString());
		}
		else{
			throw new RuntimeException("RequestStatus Invalid Request Type:"+requestType);
		}
		
		//Pass Results
		arg1.completeWorkItem(arg0.getId(), results);
	}
}
