

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Servlet implementation class weather
 */
@WebServlet(description = "weather api", urlPatterns = { "/weather" })
public class weather extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public weather() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		JSONObject jsonObject = new JSONObject();
	      JSONParser parser = new JSONParser();
	      
	      
	      
	       DataOutputStream wr = null;
	       BufferedReader in =null;

	            
	            String url2= "http://api.openweathermap.org/data/2.5/weather?lat=12.977788&lon=77.714421&appid=1539d5bae3ae5290a853beb57e345bea";

	            //String url = "http://stackoverflow.com/questions/2457538/several-requests-from-one-httpurlconnection?rq=1";
	            //String url = "https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=AIzaSyAInOyh_EAwegxSoBXdCkWLpXd5hS2BDn8";
	            
	            
	            URL obj2 = null;
	            try {
	                  obj2 = new URL(url2);
	            } catch (MalformedURLException e) {
	                  // TODO Auto-generated catch block
	                  e.printStackTrace();
	            }

	            HttpURLConnection con2 = null;
	            try {
	                  con2 = (HttpURLConnection) obj2.openConnection();
	            } catch (IOException e) {
	                  // TODO Auto-generated catch block
	                  e.printStackTrace();
	            }
	            //setting request method
	            try {
	                  con2.setRequestMethod("GET");
	            } catch (ProtocolException e) {
	                  // TODO Auto-generated catch block
	                  e.printStackTrace();
	            }

	            //adding headers
	            con2.setRequestProperty("content-type","application/json");
	            //con.setRequestProperty("Authorization", "Basic c2hyaXZhc3RhdnM6U2FwMTIzNDU2");
	            //con.setRequestProperty("Host", "ldai2er9.wdf.sap.corp:44300");
	            //con.setRequestProperty("Cache-Control", "no-cache");
	            //con.setRequestProperty("Postman-Token", "92bee4e2-1caf-e2f3-b3cc-75ddfcd6faea");

	            try {
	                  con2.connect();
	            } catch (IOException e) {
	                  // TODO Auto-generated catch block
	                  e.printStackTrace();
	            }


	            try {
	                  int responseCode2 = con2.getResponseCode();
	            } catch (IOException e) {
	                  // TODO Auto-generated catch block
	                  e.printStackTrace();
	            }  
	            try {
	                  in = new BufferedReader(new InputStreamReader(con2.getInputStream()));
	            } catch (IOException e) {
	                  // TODO Auto-generated catch block
	                  e.printStackTrace();
	            }
	            String inputLine1;
	            StringBuffer response1 = new StringBuffer();
	            try {
	                  while ((inputLine1 = in.readLine()) != null) {
	                    response1.append(inputLine1);
	                  }
	            } catch (IOException e) {
	                  // TODO Auto-generated catch block
	                  e.printStackTrace();
	            }

	            //printing response
	            // System.out.println(response);
	            try {
	                  jsonObject=(JSONObject) parser.parse(response1.toString());
	            } catch (ParseException e) {
	                  // TODO Auto-generated catch block
	                  e.printStackTrace();
	            }

	            //JSONObject jsonChildObject1 = (JSONObject)jsonObject.get("wx_desc");
	          
	            System.out.println(jsonObject);
	            //String message_text1=(String) jsonObject.get("wx_desc")+"temperature:"+(double) jsonObject.get("temp_c")+"windspeed in mph:"+(double) jsonObject.get("windspd_mph");
	            //String message_text1 = (String)jsonObject.get("wx_icon");
	            JSONArray  jsonChildObject_t = (JSONArray )jsonObject.get("weather");
	            //jsonObject.get("d"));
	            //JSONArray  jsonChildObject1_t = (JSONArray )jsonChildObject_t.get("weather");
	            JSONObject first_t = (JSONObject) jsonChildObject_t.get(0);
	            String message_text1 = "Current Weather Conditions: " + "\n" +(String)first_t.get("description");
	            String textresponse=new String();
	        	textresponse = message_text1;//"dummy \n dumy1";
	        	
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
		//doGet(request, response);
	}

}
