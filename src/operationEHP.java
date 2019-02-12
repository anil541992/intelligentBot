

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sap.core.connectivity.api.authentication.AuthenticationHeaderProvider;
import com.sap.core.connectivity.api.configuration.ConnectivityConfiguration;

/**
 * Servlet implementation class operationEHP
 */
@WebServlet("/operationEHP")
public class operationEHP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public operationEHP() {
        super();
        // TODO Auto-generated constructor stub
    }
		 /**
	    * @see HttpServlet#HttpServlet()
	    */
	  /* public BotListner() {
	       super();
	       // TODO Auto-generated constructor stub
	   }*/




	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

		JSONObject jsonObject = new JSONObject();
		JSONParser parser = new JSONParser();

		DataOutputStream wr = null;
		BufferedReader in = null;
		String destinationName = "TelegramBOT";
		HttpContext oHttpContext=null;
		AuthenticationHeaderProvider oHeaderProvider = null;
		Context ctx = null;
		try {
			ctx = new InitialContext();
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ConnectivityConfiguration configuration = (ConnectivityConfiguration) ctx
					.lookup("java:comp/env/connectivityConfiguration");
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			oHeaderProvider = (AuthenticationHeaderProvider) ctx.lookup("java:comp/env/authHeaderProvider");
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CloseableHttpClient oClient = HttpClients.createDefault();

		HttpUriRequest oRequest = new HttpGet("https://gwaasdemo-a7eac59ae.hana.ondemand.com/odata/SAP/Z_EHP_MGMT_RECAST_SRV/OperationSet('0010')?$expand=Operation_Component&$format=json");
//	    AuthenticationHeader oSSOHeader = oHeaderProvider
//	            .getAppToAppSSOHeader("https://gwaassandbox-w3af1476b.int.sap.hana.ondemand.com/odata/SAP/Z_ASSET_MANAGEMENT_SRV/$metadata");
//	    oRequest.addHeader(oSSOHeader.getName(), oSSOHeader.getValue());
		oRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		oRequest.setHeader("X-CSRF-Token", "Fetch");
		oRequest.setHeader("SAP-Connectivity-ConsumerAccount", "a7eac59ae");
		oRequest.setHeader("Authorization", "Basic STMzMTI0NTpPbXNhaXJhbTEyMzQk");
		String agregated_cookies="";
		CloseableHttpResponse colseResponse = oClient.execute(oRequest, oHttpContext);
	    InputStream instream1 = colseResponse.getEntity().getContent();
	    
	    in = new BufferedReader(new InputStreamReader(instream1));
		String inputLine;
		StringBuffer response_new = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response_new.append(inputLine);
		}

		try {
			jsonObject = (JSONObject) parser.parse(response_new.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println(jsonObject);
	    JSONObject jsonChildObject = (JSONObject) jsonObject.get("d");
	    //JSONArray jsonChildObject1 = (JSONArray) jsonChildObject.get("results");
	    //JSONObject first = (JSONObject) jsonChildObject1.get(0);
	    String activity = (String) jsonChildObject.get("Activity");
	    String workCntr = (String) jsonChildObject.get("WorkCntr");
	    String description = (String) jsonChildObject.get("Description");
	    String quantity = (String) jsonChildObject.get("Quantity");
	    String earlSchedStartDate = (String) jsonChildObject.get("EarlSchedStartDate");
	    
	    Date formatEarlSchedStartDate = new Date(Long.parseLong(earlSchedStartDate.substring(6,earlSchedStartDate.length() - 2)));
	    String earlSchedStartTime = (String) jsonChildObject.get("EarlSchedStartTime");
	    
	    
	    
	    
	    String earlSchedFinDate = (String) jsonChildObject.get("EarlSchedFinDate");
	    Date formatEarlSchedFinDate =  new Date(Long.parseLong(earlSchedFinDate.substring(6,earlSchedFinDate.length() - 2)));
	    String earlSchedFinTime = (String) jsonChildObject.get("EarlSchedFinTime");

	    
	    JSONObject jsonCompObject = (JSONObject) jsonChildObject.get("Operation_Component");
	    org.json.simple.JSONArray jsonChildObject1 = ( org.json.simple.JSONArray) jsonCompObject.get("results");
	    JSONObject first1 = (JSONObject) jsonChildObject1.get(0);
	    String material = (String) first1.get("Material");
	    String plant = (String) first1.get("Plant");
	    String entryQnt = (String) first1.get("EntryQnt");
	    String itemText = (String) first1.get("ItemText");
	    String itemNumber = (String) first1.get("ItemNumber");
	    
	    //String message_text13 = "\nActivity: " + activity + ",\nMaterial: " + Material + ",";
	    String message_text13 = "Operation details: \nOperation: " + activity + "\nWork Center: "
	                + workCntr + "\nOperation Description: " + description + "\nQuantity: " + quantity
	                + "\nStart Date: " + formatEarlSchedStartDate.toString() + "\nStart Time: " + earlSchedFinTime
	                + "\nEnd Date: " + formatEarlSchedFinDate.toString() + "\nEnd Time: " + earlSchedFinTime;
		
	     //System.out.println(message_text);
	            
	            String message_text11 = "Material details: \nMaterial Number: " + material + "\nPlant Code: "
	                    + plant + "\nEntry Quantity: " + entryQnt + "\nItem Description: " + itemText
	                    + "\nItem Number: " + itemNumber ;
		
		
		
		
		String textresponse=new String();
		textresponse = message_text13+"\n"+message_text11;//"dummy \n dumy1";
		
		 BotListner.TextResponse textResponse = new BotListner.TextResponse();
		 textResponse.setContent(textresponse);
		 BotListner.ResponseWrapper responseWrapper = new BotListner.ResponseWrapper(textResponse);
		 response.getWriter().println(responseWrapper.getResponse().toString());
		 
		 
		 /*String textresponse1=new String();
			textresponse1 = message_text11;//"dummy \n dumy1";
			
			 BotListner.TextResponse textResponse1 = new BotListner.TextResponse();
			 textResponse1.setContent(textresponse1);
			 BotListner.ResponseWrapper responseWrapper1 = new BotListner.ResponseWrapper(textResponse1);
			 response.getWriter().println(responseWrapper1.getResponse().toString());
		 */
		 
		 
		 
		 
		 
		
		 
		 
		 
		 
		 
		
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
		 /*String textresponse="dummy msg from server";
		 TextResponse textResponse = new TextResponse();
		 textResponse.setContent(textresponse);
		ResponseWrapper responseWrapper = new ResponseWrapper(textResponse);
	    response.setContentType("application/json");
	    response.getWriter().println(responseWrapper.getResponse().toString());
	    response.getWriter().append("Served at: ");*/
		}
		catch (Exception e) {
	        //logger.error(e.getMessage(), e);
	        response.setContentType("application/json");
	        response.getWriter().write(e.getMessage());
	    }
		// TODO Auto-generated method stub
		//doGet(request, response);
	}
	}



