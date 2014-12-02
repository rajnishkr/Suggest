
package model.moviesuggest;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Abridged_cast{
	@SerializedName("characters")
   	private List characters;
   	@SerializedName("id")
   	private String id;
   	@SerializedName("name")
   	private String name;

 	public List getCharacters(){
		return this.characters;
	}
	public void setCharacters(List characters){
		this.characters = characters;
	}
 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
}
