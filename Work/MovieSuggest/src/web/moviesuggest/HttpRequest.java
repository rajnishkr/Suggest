package web.moviesuggest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpRequest {
	
	 
   
	public static String MovieSearch(String query,String urlString,String apiKey,String id)
     {
    	 urlString=urlString.replace("[api_key]", apiKey);
    	 urlString=urlString.replace("[id]", id);
    	 String encodedQuery = null;

			try {
				encodedQuery = URLEncoder.encode(query, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				System.out.println("Error while encoding");
			   
			}
    	 urlString= urlString.replace("[query]", encodedQuery);
    	 urlString=urlString.replace("[pagelimit]", "5");
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
