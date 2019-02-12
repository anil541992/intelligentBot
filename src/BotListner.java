

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletConfig;
import java.io.BufferedReader;
import java.io.DataOutputStream;

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
import org.json.JSONML;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
//import org.apache.jasper.tagplugins.jstl.core.Out;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import com.google.gson.Gson;
import com.sap.core.connectivity.api.authentication.AuthenticationHeaderProvider;
import com.sap.core.connectivity.api.configuration.ConnectivityConfiguration;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;



/**
 * Servlet implementation class BotListner
 */
@WebServlet("/BotListner")
public class BotListner extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 /**
     * @see HttpServlet#HttpServlet()
     */
   /* public BotListner() {
        super();
        // TODO Auto-generated constructor stub
    }*/

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		//doPost(request, response);

		/*ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot
       try {
           botsApi.registerBot(new MyAmazingBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
       }
   	*/
    	
       
    
		
	}
	
	
    

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

		HttpUriRequest oRequest = new HttpGet("https://gwaasdemo-a7eac59ae.hana.ondemand.com/odata/SAP/Z_ASSET_MGMT_RECAST_SRV/Order_HeaderSet?$expand=OperationSet,ComponentSet&$format=json");
//	    AuthenticationHeader oSSOHeader = oHeaderProvider
//	            .getAppToAppSSOHeader("https://gwaassandbox-w3af1476b.int.sap.hana.ondemand.com/odata/SAP/Z_ASSET_MANAGEMENT_SRV/$metadata");
//	    oRequest.addHeader(oSSOHeader.getName(), oSSOHeader.getValue());
		oRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/jso");
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
		 TextResponse textResponse = new TextResponse();
		 textResponse.setContent(textresponse);
		ResponseWrapper responseWrapper = new ResponseWrapper(textResponse);
		 response.getWriter().println(responseWrapper.getResponse().toString());
		 
		 
		
		 
		 
		 
		 
		 
		
		
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static class TextResponse {
        String type = "text";
        String content;

        public String getResponse() {
            Gson gson = new Gson();
            return gson.toJson(this);
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
	
	public static class ResponseWrapper {
        TextResponse[] replies;
        Conversation conversation;

        public ResponseWrapper(TextResponse replies) {
            this.replies = new TextResponse[]{replies};
            this.conversation = new Conversation(new Memory());
        }

        public String getResponse() {
            Gson gson = new Gson();
            
            return gson.toJson(this);
        }

        public Memory getMemory() {
            return conversation.getMemory();
        }

        public void setMemory(Memory memory) {
            this.conversation.setMemory(memory);
        }

    }

    static class Memory {
    }

    static class Conversation {
        Memory memory;

        public Conversation(Memory memory) {
            this.memory = memory;
        }

        public Memory getMemory() {
            return memory;
        }

        public void setMemory(Memory memory) {
            this.memory = memory;
        }
    }
    
    
    
}
