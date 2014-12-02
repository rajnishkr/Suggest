
package model.moviesuggest;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Links{
	@SerializedName("next")
   	private String next;
	@SerializedName("self")
   	private String self;

 	public String getNext(){
		return this.next;
	}
	public void setNext(String next){
		this.next = next;
	}
 	public String getSelf(){
		return this.self;
	}
	public void setSelf(String self){
		this.self = self;
	}
}
