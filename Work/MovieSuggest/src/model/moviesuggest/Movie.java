package model.moviesuggest;

import java.util.ArrayList;
import java.util.List;
import web.moviesuggest.R;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

public class Movie {
	@SerializedName("link_template")
	private String link_template;
	private Links links;
	@SerializedName("movies")
	private List<Movies> movies;
	@SerializedName("total")
	private Number total;

	public static class Movies {
		private Bitmap bitmap;
		@SerializedName("genres")
		private List<String> genres;

		public Bitmap getBitmap() {
			return bitmap;
		}

		public void setBitmap(Bitmap bitmap) {
			this.bitmap = bitmap;
		}

		public List<String> getGenres() {
			return genres;
		}

		public void setGenres(List<String> genres) {
			this.genres = genres;
		}

		private List<Abridged_cast> abridged_cast;
		private Alternate_ids alternate_ids;
		@SerializedName("critics_consensus")
		private String critics_consensus;
		@SerializedName("id")
		private String id;

		private Links links;
		@SerializedName("mpaa_rating")
		private String mpaa_rating;
		private Posters posters;
		private Ratings ratings;
		private Release_dates release_dates;
		@SerializedName("runtime")
		private String runtime;
		@SerializedName("synopsis")
		private String synopsis;
		@SerializedName("title")
		private String title;
		@SerializedName("year")
		private String year;

		public static class Posters {
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

		public static class Release_dates {

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

		public static class Abridged_cast {
			@SerializedName("characters")
			private List characters;
			@SerializedName("id")
			private String id;
			@SerializedName("name")
			private String name;

			public List getCharacters() {
				return this.characters;
			}

			public void setCharacters(List characters) {
				this.characters = characters;
			}

			public String getId() {
				return this.id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public String getName() {
				return this.name;
			}

			public void setName(String name) {
				this.name = name;
			}
		}

		public static class Ratings {

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

		public static class Alternate_ids {
			@SerializedName("imdb")
			private String imdb;

			public String getImdb() {
				return this.imdb;
			}

			public void setImdb(String imdb) {
				this.imdb = imdb;
			}
		}

		public List<Abridged_cast> getAbridged_cast() {
			return this.abridged_cast;
		}

		public void setAbridged_cast(List<Abridged_cast> abridged_cast) {
			this.abridged_cast = abridged_cast;
		}

		public Alternate_ids getAlternate_ids() {
			return this.alternate_ids;
		}

		public void setAlternate_ids(Alternate_ids alternate_ids) {
			this.alternate_ids = alternate_ids;
		}

		public String getCritics_consensus() {
			return this.critics_consensus;
		}

		public void setCritics_consensus(String critics_consensus) {
			this.critics_consensus = critics_consensus;
		}

		public String getId() {
			return this.id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Links getLinks() {
			return this.links;
		}

		public void setLinks(Links links) {
			this.links = links;
		}

		public String getMpaa_rating() {
			return this.mpaa_rating;
		}

		public void setMpaa_rating(String mpaa_rating) {
			this.mpaa_rating = mpaa_rating;
		}

		public Posters getPosters() {
			return this.posters;
		}

		public void setPosters(Posters posters) {
			this.posters = posters;
		}

		public Ratings getRatings() {
			return this.ratings;
		}

		public void setRatings(Ratings ratings) {
			this.ratings = ratings;
		}

		public Release_dates getRelease_dates() {
			return this.release_dates;
		}

		public void setRelease_dates(Release_dates release_dates) {
			this.release_dates = release_dates;
		}

		public String getRuntime() {
			return this.runtime;
		}

		public void setRuntime(String runtime) {
			this.runtime = runtime;
		}

		public String getSynopsis() {
			return this.synopsis;
		}

		public void setSynopsis(String synopsis) {
			this.synopsis = synopsis;
		}

		public String getTitle() {
			return this.title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getYear() {
			return this.year;
		}

		public void setYear(String year) {
			this.year = year;
		}
	}

	public static class Links {
		@SerializedName("next")
		private String next;
		@SerializedName("self")
		private String self;

		public String getNext() {
			return this.next;
		}

		public void setNext(String next) {
			this.next = next;
		}

		public String getSelf() {
			return this.self;
		}

		public void setSelf(String self) {
			this.self = self;
		}
	}

	public String getLink_template() {
		return this.link_template;
	}

	public void setLink_template(String link_template) {
		this.link_template = link_template;
	}

	public Links getLinks() {
		return this.links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}

	public List<Movies> getMovies() {
		return this.movies;
	}

	public void setMovies(List<Movies> movies) {
		this.movies = movies;
	}

	public Number getTotal() {
		return this.total;
	}

	public void setTotal(Number total) {
		this.total = total;
	}
}
