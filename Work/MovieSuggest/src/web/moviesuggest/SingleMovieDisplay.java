package web.moviesuggest;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import resource.moviesuggest.Api;
import web.moviesuggest.MainActivity.myAsync;

import model.moviesuggest.Movie.Movies;
import model.moviesuggest.MovieAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SingleMovieDisplay extends Activity {
	ProgressBar pb;
	View b;
	List<MovieDetailsFectcher> tasks;
	private Movies movie;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        this.setContentView(R.layout.single_movie_view);
	        pb = (ProgressBar) findViewById(R.id.progressBar2);
			pb.setVisibility(View.INVISIBLE);
			
			b = findViewById(R.id.button2);
			b.setVisibility(View.GONE);
	        Intent i = getIntent();
	        // getting attached intent data
	        String id = i.getStringExtra("movie_id");
	       
	        // displaying selected product name
	       // txtProduct.setText(movie.getId());
	        tasks = new ArrayList<MovieDetailsFectcher>();
	        MovieDetailsFectcher fetcher=new MovieDetailsFectcher();
	        fetcher.execute(id);

		}
	 
	 
	 private class MovieDetailsFectcher extends
		AsyncTask<String, Void, Movies> {
		 	 	

			@Override
			protected void onPreExecute() {

				super.onPreExecute();
				if (tasks.size() == 0) {
					pb.setVisibility(View.VISIBLE);
				}
				tasks.add(this);

			
		}


			@Override
			protected Movies doInBackground(String... params) {
				// TODO Auto-generated method stub
				String id=params[0];
				String movieJasonResponse = HttpRequest.MovieSearch("",Api.movieInfoapi, Api.key, id);
				Movies movie_found=SearchParser.singleMovieParser(movieJasonResponse);
				return movie_found;
			}
			
			
			@SuppressLint("NewApi")
			@Override
			protected void onPostExecute(Movies result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				tasks.remove(this);
				if (tasks.size() == 0) {
					pb.setVisibility(View.INVISIBLE);
				}
				movie=result;
					
				ImageView image = (ImageView)findViewById(R.id.singleImage);
				int id=Integer.parseInt(movie.getId());
				image.setImageBitmap(MovieAdapter.imageCache.get(id));
				
				
				String details='\n'+"Year: "+movie.getYear()+'\n'+"Genres: "+movie.getGenres().toString()+'\n'+"Audience Rating: "+movie.getRatings().getAudience_score()+
						'\n'+"Critics Rating: "+movie.getRatings().getCritics_score()+'\n';
				if(movie.getSynopsis()!=null && !movie.getSynopsis().isEmpty())
					details+="Synopsis: "+movie.getSynopsis()+'\n';
				
				if(movie.getAbridged_cast()!=null && !movie.getAbridged_cast().isEmpty()){
					details+="Cast: ";
					for(int i=0;i<movie.getAbridged_cast().size();i++){
						String name=movie.getAbridged_cast().get(i).getName();
						if(name!=null && null!="")
							details+=name+", ";
					}
					details+='\n';
				}
				TextView txtProduct = (TextView) findViewById(R.id.movie_lable);
				txtProduct.setText(movie.getTitle()+details);
				b.setVisibility(View.VISIBLE);
				
				//TextView detail = (TextView) findViewById(R.id.movie_detail);
				//detail.setText(details);
				//ImageLoader loader = new ImageLoader();
				//loader.execute();
			}
		}
	 
	 
		public void onClickSuggest(View view) {
			//hide the key board
			//EditText movie = (EditText) findViewById(R.id.searchbox);
			//String query = movie.getText().toString();
			//myAsync async = new myAsync();
			//async.execute(query, Api.movieSearchapi, Api.key, "");

		}
}
	/* private class ImageLoader extends
		AsyncTask<Void, Void, Movies> {

	@Override
	protected Movies doInBackground(Void... params) {

		//MovieAndView container = params[0];
		//Movies current_movie=container.movie_obj;

		try {
			String imageUrl = movie.getPosters().getDetailed();
			InputStream in = (InputStream) new URL(imageUrl).getContent();
			Bitmap bitmap = BitmapFactory.decodeStream(in);
			in.close();
			movie.setBitmap(bitmap);
			return movie;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected void onPostExecute(Movies result) {
		
	}

}*/

