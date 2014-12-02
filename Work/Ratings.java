package model.moviesuggest;

import com.google.gson.annotations.SerializedName;

public class Ratings {

	public String getCritics_score() {
		return critics_score;
	}
	public void setCritics_score(String critics_score) {
		this.critics_score = critics_score;
	}
	public String getAudience_score() {
		return audience_score;
	}
	public void setAudience_score(String audience_score) {
		this.audience_score = audience_score;
	}
	@SerializedName("critics_score")
	private String critics_score;
	@SerializedName("audience_score")
	private String audience_score;
}
