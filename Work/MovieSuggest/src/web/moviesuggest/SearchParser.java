package web.moviesuggest;

import java.util.ArrayList;
import java.util.List;

import model.moviesuggest.Movie;
import model.moviesuggest.Movie.Movies;
import model.moviesuggest.Movie.Movies.Abridged_cast;
import model.moviesuggest.Movie.Movies.Posters;
import model.moviesuggest.Movie.Movies.Ratings;
import model.moviesuggest.Movie.Movies.Release_dates;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class SearchParser {
	
	public static Movie parseMovie(String content)
	{
		
		try {
			Movie movie =new Movie();
			List<Movie.Movies> movie_List = new ArrayList<Movie.Movies>();
			JSONObject reader = new JSONObject(content);
			//getting total
			movie.setTotal(reader.getInt("total"));
			JSONArray movieList = new JSONArray(reader.getString("movies"));
			
			
			
			// getting movie list
			for (int i = 0; i < movieList.length(); i++) {
				
				JSONObject obj = movieList.getJSONObject(i);
				Movie.Movies movies = new Movie.Movies();
				
				//getting non nested variable 
				try {
					movies.setTitle(obj.getString("title"));
				} catch (JSONException e) {
					System.out.println("couldnot extract title");
				}
				
				try {
					movies.setId(obj.getString("id"));
				} catch (JSONException e) {
					System.out.println("couldnot extract id");
				}
				
				try {
					movies.setYear(obj.getString("year"));
				} catch (JSONException e) {
					System.out.println("couldnot extract year");
				}
				
				
				try {
					movies.setRuntime(obj.getString("runtime"));
				} catch (JSONException e) {
					System.out.println("couldnot extract runtime");
				}
				
				
				try {
					movies.setSynopsis(obj.getString("synopsis"));
				} catch (JSONException e) {
					System.out.println("couldnot extract synopsis");
				}
				
				
				
				
				//extracting release date
				try{
				JSONObject release_dates = obj.getJSONObject("release_dates");
				Release_dates rel=new Release_dates();
				rel.setTheatres(release_dates.getString("theater"));
				rel.setDvd(release_dates.getString("dvd"));
				movies.setRelease_dates(rel);
				}
				catch( JSONException e)
				{
					System.out.println("couldnot extract release_dates");
				}
				
				//Extracting ratings
				try{
				JSONObject ratings = obj.getJSONObject("ratings");
				Ratings rate=new Ratings();
				rate.setAudience_score(ratings.getString("audience_score"));
				rate.setCritics_score(ratings.getString("critics_score"));
				movies.setRatings(rate);
				}
				catch(JSONException e){
					System.out.println("couldnot extract ratings");
				}
				
				//Extracting posters
				try{
				JSONObject posters = obj.getJSONObject("posters");
				Posters post=new Posters();
				post.setDetailed(posters.getString("detailed"));
				post.setOriginal(posters.getString("original"));
				post.setProfile(posters.getString("profile"));
				post.setThumbnail(posters.getString("thumbnail"));
				movies.setPosters(post);
				}
				catch(JSONException e){
					System.out.println("couldnot extract posters");
				}
				
				//extracting links
				/*JSONObject links = obj.getJSONObject("links");
				Links
				*/
				movies.setLinks(null);
				
				
				//extracting abridged_cast
				try{
				JSONArray abridged_cast = new JSONArray(obj.getString("abridged_cast"));
				List<Abridged_cast> castList = new ArrayList<Abridged_cast>();
				
				for (int j = 0; j < abridged_cast.length(); j++) {
					
					JSONObject cast_obj = abridged_cast.getJSONObject(j);
					Abridged_cast cast=new Abridged_cast();
					
					cast.setName(cast_obj.getString("name"));
					cast.setId(cast_obj.getString("id"));
					cast.setCharacters(null);
					castList.add(cast);

				}
				movies.setAbridged_cast(castList);
				movie_List.add(movies);
				}
				catch(JSONException e){
					System.out.println("couldnot extract abridged_cast");
					//castList=null;
				}
			}
			
			//adding list 
			movie.setMovies(movie_List);
			
			//left links and link template
			
			return movie;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		

	}
	
	public static Movies singleMovieParser(String content){
		
		JSONObject obj=null;
		try {
			obj = new JSONObject(content);
		Movie.Movies movies = new Movie.Movies();
		//getting non nested variable 
		try {
			movies.setId(obj.getString("id"));
		} catch (JSONException e) {
			System.out.println("couldnot extract id");
		}
		try {
			movies.setTitle(obj.getString("title"));
		} catch (JSONException e) {
			System.out.println("couldnot extract title");
		}
		
		
		try {
			movies.setYear(obj.getString("year"));
		} catch (JSONException e) {
			System.out.println("couldnot extract year");
		}
		
		
		try {
			movies.setRuntime(obj.getString("runtime"));
		} catch (JSONException e) {
			System.out.println("couldnot extract runtime");
		}
		
		
		try {
			movies.setSynopsis(obj.getString("synopsis"));
		} catch (JSONException e) {
			System.out.println("couldnot extract synopsis");
		}
		
		
		//extracting genre
		try{
			JSONArray genre = new JSONArray(obj.getString("genres"));
			List<String> genreList = new ArrayList<String>();
			
			for (int j = 0; j < genre.length(); j++) {
				genreList.add(genre.getString(j));

			}
			movies.setGenres(genreList);
			}
			catch(JSONException e){
				System.out.println("couldnot extract abridged_cast");
				//castList=null;
			}
		
		//extracting release date
		try{
		JSONObject release_dates = obj.getJSONObject("release_dates");
		Release_dates rel=new Release_dates();
		rel.setTheatres(release_dates.getString("theater"));
		rel.setDvd(release_dates.getString("dvd"));
		movies.setRelease_dates(rel);
		}
		catch( JSONException e)
		{
			System.out.println("couldnot extract release_dates");
		}
		
		//Extracting ratings
		try{
		JSONObject ratings = obj.getJSONObject("ratings");
		Ratings rate=new Ratings();
		rate.setAudience_score(ratings.getString("audience_score"));
		rate.setCritics_score(ratings.getString("critics_score"));
		movies.setRatings(rate);
		}
		catch(JSONException e){
			System.out.println("couldnot extract ratings");
		}
		
		//Extracting posters
		try{
		JSONObject posters = obj.getJSONObject("posters");
		Posters post=new Posters();
		post.setDetailed(posters.getString("detailed"));
		post.setOriginal(posters.getString("original"));
		post.setProfile(posters.getString("profile"));
		post.setThumbnail(posters.getString("thumbnail"));
		movies.setPosters(post);
		}
		catch(JSONException e){
			System.out.println("couldnot extract posters");
		}
		
		//extracting links
		/*JSONObject links = obj.getJSONObject("links");
		Links
		*/
		movies.setLinks(null);
		
		
		//extracting abridged_cast
		try{
		JSONArray abridged_cast = new JSONArray(obj.getString("abridged_cast"));
		List<Abridged_cast> castList = new ArrayList<Abridged_cast>();
		
		for (int j = 0; j < abridged_cast.length(); j++) {
			
			JSONObject cast_obj = abridged_cast.getJSONObject(j);
			Abridged_cast cast=new Abridged_cast();
			
			cast.setName(cast_obj.getString("name"));
			cast.setId(cast_obj.getString("id"));
			cast.setCharacters(null);
			castList.add(cast);

		}
		movies.setAbridged_cast(castList);
		}
		catch(JSONException e){
			System.out.println("couldnot extract abridged_cast");
			//castList=null;
		}
		return movies;
		}
	 catch(JSONException e1) {
			System.out.println("couldnot extract movie");
			return null;
		}
	
	}

}
