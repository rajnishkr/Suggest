
package model.moviesuggest;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Alternate_ids{
	@SerializedName("imdb")
   	private String imdb;

 	public String getImdb(){
		return this.imdb;
	}
	public void setImdb(String imdb){
		this.imdb = imdb;
	}
}
