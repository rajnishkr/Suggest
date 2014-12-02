package web.moviesuggest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {
	
	 
   
	public static String MovieSearch(String query,String urlString,String apiKey,String id)
     {
    	 urlString=urlString.replace("[api_key]", apiKey);
    	 urlString=urlString.replace("[id]", id);
    	 urlString= urlString.replace("[query]", query);
    	 urlString=urlString.replace("[pagelimit]", "10");
         URL url;
         HttpURLConnection urlConnection = null;
		try {
			url = new URL(urlString);
			 urlConnection = (HttpURLConnection) url.openConnection();
			 BufferedReader in = new BufferedReader(
                     new InputStreamReader(urlConnection.getInputStream()));
			 
			 StringBuffer response  = new StringBuffer();
			 String temp;
			 while((temp=in.readLine())!=null)
			 {
			   response.append(temp);
			 }
			 in.close();
			 return response.toString();
			 //return "HELLO";
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		 urlConnection.disconnect();
		 //return "ERROR";
		}
        return "Error";
     }

}
