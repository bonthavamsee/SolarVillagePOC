package org.govtPermit.controller;

import org.hibernate.Session;
import org.domainModel.govtPermit.*;
import org.domainModel.controller.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/solar")
public class RequestResponse {

	private static final String SUCCESS_STATUS = "success";
	private static final String ERROR_STATUS = "error";
	private static final int CODE_SUCCESS = 100;
	private static final int CODE_FAILURE = 102;
	
	//Raise Electrical Request
	@RequestMapping(value = "/raiseElectricalRequest", method = RequestMethod.POST)
	public BaseResponse raiseElectrialReq(@RequestBody ElectricalPermit request) {
		 BaseResponse response = new BaseResponse();
		 Session sessionObj=null;
		 try{
		  //sessionObj=HibernateController.buildSessionFactory().openSession();
		  sessionObj=HibernateController.buildSessionFactory().openSession();
		  sessionObj.beginTransaction();
		  sessionObj.save(request);
		  System.out.println("RECORD SAVED TO DB SUCCESSFULLY");
		  sessionObj.getTransaction().commit();
		  response.setStatus(SUCCESS_STATUS);
		  response.setCode(CODE_SUCCESS);
		  response.setMessage("Electrical Permit Request Received Successfully");
		 }
		 catch(Exception e)
		 {
		  System.out.println("---------STACK TRACE START---------------");
		  e.printStackTrace();
		  System.out.println("---------STACK TRACE END---------------");
		  if(null!=sessionObj.getTransaction())
		  {
			  System.out.println("TRANSACTION ROLLED BACK");
			  sessionObj.getTransaction().rollback();
		  }
		  response.setStatus(ERROR_STATUS);
		  response.setCode(CODE_FAILURE);
		  response.setMessage("Electrical Permit Request Unsuccessful..Please Try Later...");
		 }
		 finally{
		  if(sessionObj !=null)
			  sessionObj.close();
		  HibernateController.close();
		 }
		 HibernateController.close();
		 
	  return response;
	 }
	
	//Raise Structural Request
	@RequestMapping(value = "/raiseStructuralRequest", method = RequestMethod.POST)
	public BaseResponse raiseStructuralReq(@RequestBody StructuralPermit request) {
		 BaseResponse response = new BaseResponse();
		 Session sessionObj=null;
		 try{
		  //sessionObj=HibernateController.buildSessionFactory().openSession();
			 sessionObj=HibernateController.buildSessionFactory().openSession();
		  sessionObj.beginTransaction();
		  sessionObj.save(request);
		  System.out.println("RECORD SAVED TO DB SUCCESSFULLY");
		  sessionObj.getTransaction().commit();
		  response.setStatus(SUCCESS_STATUS);
		  response.setCode(CODE_SUCCESS);
		  response.setMessage("Structural Permit Request Received Successfully");
		 }
		 catch(Exception e)
		 {
		  System.out.println("---------STACK TRACE START---------------");
		  e.printStackTrace();
		  System.out.println("---------STACK TRACE END---------------");
		  if(null!=sessionObj.getTransaction())
		  {
			  System.out.println("TRANSACTION ROLLED BACK");
			  sessionObj.getTransaction().rollback();
		  }
		  response.setStatus(ERROR_STATUS);
		  response.setCode(CODE_FAILURE);
		  response.setMessage("Structural Permit Request Unsuccessful..Please Try Later...");
		 }
		 finally{
		  if(sessionObj !=null)
			  sessionObj.close();
		  HibernateController.close();
		 }
		 
	  return response;
	 }
	
	//Get Electrical Request Status
	@RequestMapping(value = "/getElectricalReqStatus", method = RequestMethod.GET)
	public ElectricalPermit getElectricalReq(@RequestParam(value = "id")String Id) {
		 Session sessionObj=null;
		 ElectricalPermit ep=null;

		 try{
		  //sessionObj=HibernateController.buildSessionFactory().openSession();
		  sessionObj=HibernateController.buildSessionFactory().openSession();
		  ep = (ElectricalPermit) sessionObj.get(ElectricalPermit.class, Id);
		 }
		 catch(Exception e)
		 {
		  System.out.println("---------STACK TRACE START---------------");
		  e.printStackTrace();
		  System.out.println("---------STACK TRACE END---------------");
		 }
		 finally{
		  if(sessionObj !=null)
			  sessionObj.close();
		  HibernateController.close();
		 }

	  return ep;
	 }
	
	//Get Structural Request Status
		@RequestMapping(value = "/getStructuralReqStatus", method = RequestMethod.GET)
		public StructuralPermit getStructuralReq(@RequestParam(value = "id")String Id) {
			 Session sessionObj=null;
			 StructuralPermit sp=null;

			 try{
			  //sessionObj=HibernateController.buildSessionFactory().openSession();
			  sessionObj=HibernateController.buildSessionFactory().openSession();
			  sp = (StructuralPermit) sessionObj.get(StructuralPermit.class, Id);
			 }
			 catch(Exception e)
			 {
			  System.out.println("---------STACK TRACE START---------------");
			  e.printStackTrace();
			  System.out.println("---------STACK TRACE END---------------");
			 }
			 finally{
			  if(sessionObj !=null)
				  sessionObj.close();
			  HibernateController.close();
			 }

		  return sp;
		 }
		
		//Rescind Electrical Request
		@RequestMapping(value = "/rescindElectricalRequest", method = RequestMethod.GET)
		public BaseResponse rescindElectrialReq(@RequestParam(value = "id") String Id) {
			 BaseResponse response = new BaseResponse();
			 Session sessionObj=null;
			 ElectricalPermit ep=null;

			 try{
			  //sessionObj=HibernateController.buildSessionFactory().openSession();
			  sessionObj=HibernateController.buildSessionFactory().openSession();
			  sessionObj.beginTransaction();
			  ep=(ElectricalPermit)sessionObj.load(ElectricalPermit.class, Id);
			  if(ep!=null)
			  {
				  sessionObj.delete(ep);
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
			  
			  sessionObj.getTransaction().commit();
			 }
			 catch(Exception e)
			 {
			  System.out.println("---------STACK TRACE START---------------");
			  e.printStackTrace();
			  System.out.println("---------STACK TRACE END---------------");
			  if(null!=sessionObj.getTransaction())
			  {
				  System.out.println("TRANSACTION ROLLED BACK");
				  sessionObj.getTransaction().rollback();
			  }
			  response.setStatus(ERROR_STATUS);
			  response.setCode(CODE_FAILURE);
			  response.setMessage("Electrical Permit Rescinded Unsuccessfully..Please Try Later...");
			 }
			 finally{
			  if(sessionObj !=null)
				  sessionObj.close();
			  HibernateController.close();
			 }

		  return response;
		 }
		
		//Rescind Structural Request
		@RequestMapping(value = "/rescindStructuralRequest", method = RequestMethod.GET)
		public BaseResponse rescindStructuralReq(@RequestParam(value = "id") String Id) {
			 BaseResponse response = new BaseResponse();
			 Session sessionObj=null;
			 StructuralPermit ep=null;

			 try{
			  //sessionObj=HibernateController.buildSessionFactory().openSession();
			  sessionObj=HibernateController.buildSessionFactory().openSession();
			  sessionObj.beginTransaction();
			  ep=(StructuralPermit)sessionObj.load(StructuralPermit.class, Id);
			  if(ep!=null)
			  {
				  sessionObj.delete(ep);
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
			  
			  sessionObj.getTransaction().commit();
			 }
			 catch(Exception e)
			 {
			  System.out.println("---------STACK TRACE START---------------");
			  e.printStackTrace();
			  System.out.println("---------STACK TRACE END---------------");
			  if(null!=sessionObj.getTransaction())
			  {
				  System.out.println("TRANSACTION ROLLED BACK");
				  sessionObj.getTransaction().rollback();
			  }
			  response.setStatus(ERROR_STATUS);
			  response.setCode(CODE_FAILURE);
			  response.setMessage("Structural Permit Rescinded Unsuccessfully..Please Try Later...");
			 }
			 finally{
			  if(sessionObj !=null)
				  sessionObj.close();
			  HibernateController.close();
			 }

		  return response;
		 }
	
}
