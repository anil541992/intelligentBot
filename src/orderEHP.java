

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
 * Servlet implementation class orderEHP
 */
@WebServlet("/orderEHP")
public class orderEHP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public orderEHP() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		JSONObject jsonObject = new JSONObject();
		JSONParser parser = new JSONParser();

		DataOutputStream wr = null;
		BufferedReader in = null;
		String destinationName = "TelegramBOT";
		HttpContext oHttpContext=null;
		AuthenticationHeaderProvider oHeaderProvider = null;
		Context ctx = null;
		/*try {
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
		*/
		CloseableHttpClient oClient = HttpClients.createDefault();
		HttpUriRequest oRequest = new HttpGet("https://gwaasdemo-a7eac59ae.hana.ondemand.com/odata/SAP/Z_EHP_MGMT_RECAST_SRV/Order_HeaderSet?$expand=OperationSet,ComponentSet&$format=json");
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
JSONObject jsonChildObject = (JSONObject) jsonObject.get("d");
		
		// jsonObject.get("d"));
		org.json.simple.JSONArray jsonChildObject1 = (org.json.simple.JSONArray) jsonChildObject.get("results");
		JSONObject first = (JSONObject) jsonChildObject1.get(0);
		 String orderNo = (String) first.get("Orderid");
		 String OrderID = orderNo;
         String orderDescription = (String) first.get("ShortText");
         String equipmentDisplay = (String) first.get("EquipmentDisplay");
         String functionDisplay = (String) first.get("FunclocDisplay"); 
         
         JSONObject jsonOrderObject = (JSONObject)first.get("Order_Operation");                                      
         //org.json.simple.JSONArray  jsonOrderObject1 = (org.json.simple.JSONArray )jsonOrderObject.get("results");
         
         String message_text = "Your order details are: " + "\n\n" + "Order Number: " + orderNo + "\n"
					+ "Order Description: " + orderDescription + "\n" + "Equipment Display: " + equipmentDisplay + "\n"
					+ "Function Display: " + functionDisplay;
		
		
		

         //System.out.println(message_text);
		
		
		
		
		String textresponse=new String();
		textresponse = message_text;//"dummy \n dumy1";
		BotListner.TextResponse textResponse = new BotListner.TextResponse();
		 textResponse.setContent(textresponse);
		 BotListner.ResponseWrapper responseWrapper = new BotListner.ResponseWrapper(textResponse);
		 response.getWriter().println(responseWrapper.getResponse().toString());
		 
		 
		
		 
		 
		 
		 
		 
		
		
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
