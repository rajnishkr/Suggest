package web.moviesuggest;

import java.util.ArrayList;
import java.util.List;

import model.moviesuggest.Movie;
import model.moviesuggest.Movie.Movies;
import model.moviesuggest.MovieAdapter;
import resource.moviesuggest.Api;
import web.moviesuggest.MainActivity.myAsync;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class SingleMovieDisplay extends Activity {
	ProgressBar pb;
	View b;
	List<MovieDetailsFectcher> tasks;
	List<myAsync> listTasks;
	static List<Movies> suggestedMovieList;
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
		listTasks = new ArrayList<myAsync>();
		MovieDetailsFectcher fetcher = new MovieDetailsFectcher();
		fetcher.execute(id);

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.single_movie_display, menu);
		return true;
	}


	private class MovieDetailsFectcher extends AsyncTask<String, Void, Movies> {

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
			String id = params[0];
			String movieJasonResponse = HttpRequest.MovieSearch("",
					Api.movieInfoapi, Api.key, id);
			Movies movie_found = SearchParser
					.singleMovieParser(movieJasonResponse);
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
			movie = result;

			ImageView image = (ImageView) findViewById(R.id.singleImage);
			int id = Integer.parseInt(movie.getId());
			image.setImageBitmap(MovieAdapter.imageCache.get(id));

			String details = '\n' + "Year: " + movie.getYear() + '\n'
					+ "Genres: " + movie.getGenres().toString() + '\n'
					+ "Audience Rating: "
					+ movie.getRatings().getAudience_score() + '\n'
					+ "Critics Rating: "
					+ movie.getRatings().getCritics_score() + '\n';
			if (movie.getSynopsis() != null && !movie.getSynopsis().isEmpty())
				details += "Synopsis: " + movie.getSynopsis() + '\n';

			if (movie.getAbridged_cast() != null
					&& !movie.getAbridged_cast().isEmpty()) {
				details += "Cast: ";
				for (int i = 0; i < movie.getAbridged_cast().size(); i++) {
					String name = movie.getAbridged_cast().get(i).getName();
					if (name != null && null != "")
						details += name + ", ";
				}
				details += '\n';
			}
			TextView txtProduct = (TextView) findViewById(R.id.movie_lable);
			txtProduct.setText(movie.getTitle() + details);
			b.setVisibility(View.VISIBLE);

			// TextView detail = (TextView) findViewById(R.id.movie_detail);
			// detail.setText(details);
			// ImageLoader loader = new ImageLoader();
			// loader.execute();
		}
	}

	public void onClickSuggest(View view) {
		// hide the key board
		// EditText movie = (EditText) findViewById(R.id.searchbox);
		// String query = movie.getText().toString();
		// myAsync async = new myAsync();
		// async.execute(query, Api.movieSearchapi, Api.key, "");

		myAsync async = new myAsync();
		async.execute("", Api.movieSimilarapi, Api.key, movie.getId());
	}

	/*
	 * private class ImageLoader extends AsyncTask<Void, Void, Movies> {
	 * 
	 * @Override protected Movies doInBackground(Void... params) {
	 * 
	 * //MovieAndView container = params[0]; //Movies
	 * current_movie=container.movie_obj;
	 * 
	 * try { String imageUrl = movie.getPosters().getDetailed(); InputStream in
	 * = (InputStream) new URL(imageUrl).getContent(); Bitmap bitmap =
	 * BitmapFactory.decodeStream(in); in.close(); movie.setBitmap(bitmap);
	 * return movie; } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return null; }
	 * 
	 * @Override protected void onPostExecute(Movies result) {
	 * 
	 * }
	 * 
	 * }
	 */

	class myAsync extends AsyncTask<String, String, ArrayList<Movies>> {
		EditText display = null;

		protected void onPreExecute() {
			// display= (EditText)findViewById(R.id.editText1);
			if (listTasks.size() == 0) {
				pb.setVisibility(View.VISIBLE);
			}
			listTasks.add(this);

		}

		@Override
		protected ArrayList<Movies> doInBackground(String... params) {
			String suggestList = HttpRequest.MovieSearch(params[0], params[1],
					params[2], params[3]);
			if (suggestList.equalsIgnoreCase("error")) {
				return null;
			}
			Movie suggestMovieList = SearchParser.parseMovie(suggestList);

			if (suggestMovieList != null) {
				return (ArrayList<Movies>) suggestMovieList.getMovies();
			} else {
				return null;

			}
		}

		protected void onPostExecute(ArrayList<Movies> movieList) {

			try {

				if (movieList == null) {
					Toast.makeText(
							SingleMovieDisplay.this,
							"No movie is avialable with this Name or Please the Check Internet Connection",
							Toast.LENGTH_LONG).show();
					listTasks.remove(this);
					if (listTasks.size() == 0) {
						pb.setVisibility(View.INVISIBLE);
					}
					
					return;
				}

				if (movieList.size() == 0) {
					Toast.makeText(SingleMovieDisplay.this,
							"No movie is avialable Similar to this movie",
							Toast.LENGTH_LONG).show();
					listTasks.remove(this);
					if (listTasks.size() == 0) {
						pb.setVisibility(View.INVISIBLE);
					}
					return;
				}
				suggestedMovieList = movieList;
				updateDisplay();
				listTasks.remove(this);
				if (listTasks.size() == 0) {
					pb.setVisibility(View.INVISIBLE);
				}
				

			} catch (Exception e) {
				System.out.println("Error in onPostExecute" + e.toString());
			}

			// display.setText(movie.getTotal().toString());
		}

	}

	protected void updateDisplay() {

		Intent suggest_list = new Intent(getApplicationContext(), SuggestList.class);
		// sending data to new activity
		startActivity(suggest_list);

	}
}
