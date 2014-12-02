package model.moviesuggest;

import com.google.gson.annotations.SerializedName;

public class Release_dates {
	
	@SerializedName("theatres")
	private String theatres;
	public String getTheatres() {
		return theatres;
	}
	public void setTheatres(String theatres) {
		this.theatres = theatres;
	}
	public String getDvd() {
		return dvd;
	}
	public void setDvd(String dvd) {
		this.dvd = dvd;
	}
	@SerializedName("dvd")
	private String dvd;
}
