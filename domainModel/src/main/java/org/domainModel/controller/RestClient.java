package org.domainModel.controller;

import org.domainModel.govtPermit.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.domainModel.govtPermit.BaseResponse;
import org.domainModel.govtPermit.ElectricalPermit;
import org.domainModel.govtPermit.PermitStatus;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestClient {
	
	private HttpURLConnection conn;
	private OutputStream os;
	private BufferedReader br;
	private BaseResponse bsr;
	private ElectricalPermit ep;
	private StructuralPermit sp;
	private int x;
	
	public Object restPost(Object obj,String url1,String inputClassName,String outputClassName){
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString;
			if(inputClassName.equalsIgnoreCase("ElectricalPermit")){
				jsonInString = mapper.writeValueAsString((ElectricalPermit)obj);
				System.out.println(jsonInString);
			}
			else if(inputClassName.equalsIgnoreCase("StructuralPermit")){
				jsonInString = mapper.writeValueAsString((StructuralPermit)obj);
				System.out.println(jsonInString);
			}
			else{
				throw new Exception("Incorrect Input Class Name:"+inputClassName);
			}
			
			URL url = new URL(url1);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			
			os = conn.getOutputStream();
			os.write(jsonInString.getBytes());
			os.flush();
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
			}

			br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String jsonOutString;
			
			System.out.println("Output from Server .... \n");
			while ((jsonOutString = br.readLine()) != null) {
				System.out.println(jsonOutString);
				bsr = mapper.readValue(jsonOutString, BaseResponse.class);
			}

		  } catch (Exception e) {

			e.printStackTrace();

		  }
		finally{
			if(conn!=null)
				conn.disconnect();
		}
		return (Object)bsr;
	}
	
	public Object restGet(String url1,String outputClassName){
		int x=0;
		try {
			URL url = new URL(url1);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String jsonOutString;
			
			ObjectMapper mapper = new ObjectMapper();
			System.out.println("Output from Server .... \n");
			while ((jsonOutString = br.readLine()) != null) {
				System.out.println(jsonOutString);
				
				if(outputClassName.equalsIgnoreCase("ElectricalPermit")){
					ep = mapper.readValue(jsonOutString, ElectricalPermit.class);
					x=1;
					System.out.println("EReq:"+ep.toString());
					//break;
				}
				else if(outputClassName.equalsIgnoreCase("StructuralPermit")){
					sp = mapper.readValue(jsonOutString, StructuralPermit.class);
					x=2;
					System.out.println("SReq:"+sp.toString());
					//break;
				}
				else if(outputClassName.equalsIgnoreCase("BaseResponse")){
					bsr = mapper.readValue(jsonOutString, BaseResponse.class);
					x=3;
					System.out.println("BReq:"+bsr.toString());
					//break;
				}
				else
				{
					throw new Exception("Invalid Output Class Name:"+outputClassName);
				}
			}

		  } catch (Exception e) {

			e.printStackTrace();

		  }
		finally{
			if(conn!=null)
				conn.disconnect();
		}
		if(x==1)
			return (Object)ep;
		else if(x==2)
			return (Object)sp;
		else if (x==3)
			return (Object)bsr;
		else
			return null;
	}
}
