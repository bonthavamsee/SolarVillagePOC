package org.customWorkItemHandlers;

import java.util.HashMap;

import org.domainModel.controller.RestClient;
import org.domainModel.govtPermit.BaseResponse;
import org.domainModel.govtPermit.ElectricalPermit;
import org.domainModel.govtPermit.StructuralPermit;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;

public class RescindPermit implements org.kie.api.runtime.process.WorkItemHandler {
	
	@Override
	public void abortWorkItem(WorkItem arg0, WorkItemManager arg1) {
		
		System.out.println("In Raise Request abortWorkItem");
		arg1.abortWorkItem(arg0.getId());
		
	}

	@Override
	public void executeWorkItem(WorkItem arg0, WorkItemManager arg1) {
		
		System.out.println("In Rescind Request WorkItem");
		//Read Params and Execute Business Logic
		RestClient client=new RestClient();
		String url=arg0.getParameter("url").toString();
		String paramValue=arg0.getParameter("paramValue").toString();
		url=url+paramValue;
		String requestType=arg0.getParameter("requestType").toString();
		BaseResponse bsr=null;
		bsr=(BaseResponse)client.restGet(url, "BaseResponse");
		
		//Pass Results				
		if(bsr==null)
			throw new RuntimeException("RescindPermit Null Response");
		HashMap<String, Object> results=new HashMap<String, Object>();
		results.put("response", bsr.toString());
		arg1.completeWorkItem(arg0.getId(), results);	
		
	}
}
