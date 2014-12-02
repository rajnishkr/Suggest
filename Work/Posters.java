package model.moviesuggest;

import com.google.gson.annotations.SerializedName;

public class Posters {
	@SerializedName("thumbnail")
	private String thumbnail;
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	@SerializedName("profile")
	private String profile;
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getDetailed() {
		return detailed;
	}
	public void setDetailed(String detailed) {
		this.detailed = detailed;
	}
	public String getOriginal() {
		return original;
	}
	public void setOriginal(String original) {
		this.original = original;
	}
	
	@SerializedName("detailed")
	private String detailed;
	@SerializedName("original")
	private String original;

}
