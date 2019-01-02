package org.govtPermit.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.domainModel.govtPermit.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/solar")
public class RequestResponseStatic {
	
	private static HashMap<String, ElectricalPermit> dataStoreE=new HashMap<String, ElectricalPermit>();
	private static HashMap<String, StructuralPermit> dataStoreS=new HashMap<String, StructuralPermit>();

	private static final String SUCCESS_STATUS = "success";
	private static final String ERROR_STATUS = "error";
	private static final int CODE_SUCCESS = 100;
	private static final int CODE_FAILURE = 102;
	
	//Raise Electrical Request
	@RequestMapping(value = "/raiseElectricalRequest", method = RequestMethod.POST)
	public BaseResponse raiseElectrialReq(@RequestBody ElectricalPermit request) {
		 BaseResponse response = new BaseResponse();
		 try{
		  if(dataStoreE.get(request.getPermitApplicationNumber())!=null)
			  throw new Exception("Electrical Permit Application Number already exists");
		  dataStoreE.put(request.getPermitApplicationNumber(), request);
		  System.out.println("RECORD SAVED TO MAP SUCCESSFULLY");
		  response.setStatus(SUCCESS_STATUS);
		  response.setCode(CODE_SUCCESS);
		  response.setMessage("Electrical Permit Request Received Successfully");
		 }
		 catch(Exception e)
		 {
		  System.out.println("---------STACK TRACE START---------------");
		  e.printStackTrace();
		  System.out.println("---------STACK TRACE END---------------");
		  response.setStatus(ERROR_STATUS);
		  response.setCode(CODE_FAILURE);
		  response.setMessage("Electrical Permit Request Unsuccessful..Please Try Later...");
		 }
		 
	  return response;
	 }
	
	
	//Raise Structural Request
	@RequestMapping(value = "/raiseStructuralRequest", method = RequestMethod.POST)
	public BaseResponse raiseStructuralReq(@RequestBody StructuralPermit request) {
		BaseResponse response = new BaseResponse();
		try{
		  if(dataStoreS.get(request.getPermitApplicationNumber())!=null)
			  throw new Exception(" Structural Permit Application Number already exists");
		  dataStoreS.put(request.getPermitApplicationNumber(), request);
		  System.out.println("RECORD SAVED TO MAP SUCCESSFULLY");
		  response.setStatus(SUCCESS_STATUS);
		  response.setCode(CODE_SUCCESS);
		  response.setMessage("Structural Permit Request Received Successfully");
		}
		catch(Exception e)
		{
			System.out.println("---------STACK TRACE START---------------");
			e.printStackTrace();
			System.out.println("---------STACK TRACE END---------------");
			response.setStatus(ERROR_STATUS);
			response.setCode(CODE_FAILURE);
			response.setMessage("Structural Permit Request Unsuccessful..Please Try Later...");
		}
			 
		return response;
	}
	
	//Get Electrical Request Status
	@RequestMapping(value = "/getElectricalReqStatus", method = RequestMethod.GET)
	public ElectricalPermit getElectricalReq(@RequestParam(value = "id")String Id) {
		ElectricalPermit ep=null;
		try {
			ep=dataStoreE.get(Id);
			if(ep==null)
				throw new Exception("Electrical Permit Application Number not Found");
		}
		catch(Exception e)
		{
			 System.out.println("---------STACK TRACE START---------------");
			 e.printStackTrace();
			 System.out.println("---------STACK TRACE END---------------");
		}

		return ep;
	}	
	
	
	//Get Structural Request Status
	@RequestMapping(value = "/getStructuralReqStatus", method = RequestMethod.GET)
	public StructuralPermit getStructuralReq(@RequestParam(value = "id")String Id) {
		StructuralPermit sp=null;
		try {
			sp=dataStoreS.get(Id);
			if(sp==null)
				throw new Exception("Structural Permit Application Number not Found");
		}
		catch(Exception e)
		{
			System.out.println("---------STACK TRACE START---------------");
			e.printStackTrace();
			System.out.println("---------STACK TRACE END---------------");
		}

		return sp;
	}	
	
	
	//Rescind Electrical Request
	@RequestMapping(value = "/rescindElectricalRequest", method = RequestMethod.GET)
	public BaseResponse rescindElectrialReq(@RequestParam(value = "id") String Id) {
		BaseResponse response = new BaseResponse();
		ElectricalPermit ep=null;

		try{
			ep=dataStoreE.get(Id);
			if(ep!=null)
			{
				dataStoreE.remove(Id);
				response.setStatus(SUCCESS_STATUS);
				response.setCode(CODE_SUCCESS);
				response.setMessage("Electrical Permit Request Rescinded Successfully");
			}
			else
			{
				response.setStatus(ERROR_STATUS);
				response.setCode(CODE_FAILURE);
				response.setMessage("Electrical Permit Request ID Not Found..");
			}
		}
		catch(Exception e)
		{
			System.out.println("---------STACK TRACE START---------------");
			e.printStackTrace();
			System.out.println("---------STACK TRACE END---------------");
			response.setStatus(ERROR_STATUS);
			response.setCode(CODE_FAILURE);
			response.setMessage("Electrical Permit Rescinded Unsuccessfully..Please Try Later...");
		}

		return response;
	}
	
	//Rescind Structural Request
	@RequestMapping(value = "/rescindStructuralRequest", method = RequestMethod.GET)
	public BaseResponse rescindStructuralReq(@RequestParam(value = "id") String Id) {
		BaseResponse response = new BaseResponse();
		StructuralPermit sp=null;

		try{
			sp=dataStoreS.get(Id);
			if(sp!=null)
			{
				dataStoreS.remove(Id);
				response.setStatus(SUCCESS_STATUS);
				response.setCode(CODE_SUCCESS);
				response.setMessage("Structural Permit Request Rescinded Successfully");
			}
			else
			{
				response.setStatus(ERROR_STATUS);
				response.setCode(CODE_FAILURE);
				response.setMessage("Structural Permit Request ID Not Found..");
			}
		}
		catch(Exception e)
		{
			System.out.println("---------STACK TRACE START---------------");
			e.printStackTrace();
			System.out.println("---------STACK TRACE END---------------");
			response.setStatus(ERROR_STATUS);
			response.setCode(CODE_FAILURE);
			response.setMessage("Structural Permit Rescinded Unsuccessfully..Please Try Later...");
		}

		return response;
	}
	
	//Change Structural Status
	@RequestMapping(value = "/changeStructuralStatus", method = RequestMethod.POST)
	public BaseResponse changeStructuralStats(@RequestParam(value = "id") String Id,@RequestParam(value = "status") PermitStatus status) {
		BaseResponse response = new BaseResponse();
		StructuralPermit sp=null;

		try{
			sp=dataStoreS.get(Id);
			if(sp!=null)
			{
				/*if(status.equalsIgnoreCase("Accepted"))
					sp.setPermitStatus(PermitStatus.ACCEPTED);
				else if(status.equalsIgnoreCase("Rejected"))
					sp.setPermitStatus(PermitStatus.REJECTED);
				else if(status.equalsIgnoreCase("Pending"))
					sp.setPermitStatus(PermitStatus.PENDING);
				else
					sp.setPermitStatus(PermitStatus.UNDEFINED);*/
				sp.setPermitStatus(status);
				dataStoreS.put(Id, sp);
				response.setStatus(SUCCESS_STATUS);
				response.setCode(CODE_SUCCESS);
				response.setMessage("Structural Permit Status Changed Successfully to "+status);
			}
			else
			{
				response.setStatus(ERROR_STATUS);
				response.setCode(CODE_FAILURE);
				response.setMessage("Structural Permit Request ID Not Found..");
			}
		}
		catch(Exception e)
		{
			System.out.println("---------STACK TRACE START---------------");
			e.printStackTrace();
			System.out.println("---------STACK TRACE END---------------");
			response.setStatus(ERROR_STATUS);
			response.setCode(CODE_FAILURE);
			response.setMessage("Structural Permit Status changed Unsuccessfully..Please Try Later...");
		}

		return response;
	}

	//Change Electrical Status
		@RequestMapping(value = "/changeElectricalStatus", method = RequestMethod.POST)
		public BaseResponse changeElectricalStats(@RequestParam(value = "id") String Id,@RequestParam(value = "status") PermitStatus status) {
			BaseResponse response = new BaseResponse();
			ElectricalPermit ep=null;

			try{
				ep=dataStoreE.get(Id);
				if(ep!=null)
				{
					/*if(status.equalsIgnoreCase("Accepted"))
						ep.setPermitStatus(PermitStatus.ACCEPTED);
					else if(status.equalsIgnoreCase("Rejected"))
						ep.setPermitStatus(PermitStatus.REJECTED);
					else if(status.equalsIgnoreCase("Pending"))
						ep.setPermitStatus(PermitStatus.PENDING);
					else
						ep.setPermitStatus(PermitStatus.UNDEFINED);*/
					ep.setPermitStatus(status);
					dataStoreE.put(Id, ep);
					response.setStatus(SUCCESS_STATUS);
					response.setCode(CODE_SUCCESS);
					response.setMessage("Electrical Permit Status Changed Successfully to "+status);
				}
				else
				{
					response.setStatus(ERROR_STATUS);
					response.setCode(CODE_FAILURE);
					response.setMessage("Electrical Permit Request ID Not Found..");
				}
			}
			catch(Exception e)
			{
				System.out.println("---------STACK TRACE START---------------");
				e.printStackTrace();
				System.out.println("---------STACK TRACE END---------------");
				response.setStatus(ERROR_STATUS);
				response.setCode(CODE_FAILURE);
				response.setMessage("Electrical Permit Status changed Unsuccessfully..Please Try Later...");
			}

			return response;
		}
		
		//Get dataStoreE file
		@RequestMapping(value = "/getdataStoreE", method = RequestMethod.GET)
		public String getdataStoreE() {
			
			FileWriter fileWriter = null;
			String fileString="";
			try {
				ElectricalPermit ep=null;
				Set<String> keySet=dataStoreE.keySet();
				Iterator<String> it=keySet.iterator();
				
				while(it.hasNext())
				{
					ep=dataStoreE.get(it.next());
					fileString+=ep.getPermitApplicationNumber()+"\t"+ep.getElectricalConnnectionNumber()+"\t"+ep.getPermitStatus().toString()+"\n";
				}
			    fileWriter = new FileWriter("c:/temp/dataStoreE.txt");
			    fileWriter.write(fileString);
			    fileWriter.close();
			}
			catch(Exception e)
			{
				 System.out.println("---------STACK TRACE START---------------");
				 e.printStackTrace();
				 System.out.println("---------STACK TRACE END---------------");
			}

			return fileString;
		}	
		
		//Get dataStoreS file
		@RequestMapping(value = "/getdataStoreS", method = RequestMethod.GET)
		public String getdataStoreS() {
			
			FileWriter fileWriter = null;
			String fileString="";
			try {
				StructuralPermit sp=null;
				Set<String> keySet=dataStoreS.keySet();
				Iterator<String> it=keySet.iterator();
				
				while(it.hasNext())
				{
					sp=dataStoreS.get(it.next());
					fileString+=sp.getPermitApplicationNumber()+"\t"+sp.getBuildingRegistrationNumber()+"\t"+sp.getPermitStatus().toString()+"\n";
				}
			    fileWriter = new FileWriter("c:/temp/dataStoreS.txt");
			    fileWriter.write(fileString);
			    fileWriter.close();
			}
			catch(Exception e)
			{
				 System.out.println("---------STACK TRACE START---------------");
				 e.printStackTrace();
				 System.out.println("---------STACK TRACE END---------------");
			}

			return fileString;
		}	
}
