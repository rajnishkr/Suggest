package web.moviesuggest;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.google.gson.Gson;

import model.moviesuggest.Movie;
import model.moviesuggest.Movie.Movies;
import model.moviesuggest.MovieAdapter;
import resource.moviesuggest.Api;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity  {
	TextView output;
	ProgressBar pb;
	List<myAsync> tasks;
	List<Movies> movie_List;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pb = (ProgressBar) findViewById(R.id.progressBar1);
		pb.setVisibility(View.INVISIBLE);

		// output = (TextView) findViewById(R.id.textView);
		// output.setMovementMethod(new ScrollingMovementMethod());

		tasks = new ArrayList<myAsync>();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		if (!isOnline()) {

			Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG)
					.show();
		}
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	protected boolean isOnline() {
		try{
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		} else {
			return false;
		}
		}
		catch(Exception e)
		{
			return false;
		}
	}

	class myAsync extends AsyncTask<String, String, ArrayList<Movies>> {
		EditText display = null;

		protected void onPreExecute() {
			// display= (EditText)findViewById(R.id.editText1);
			if (tasks.size() == 0) {
				pb.setVisibility(View.VISIBLE);
			}
			tasks.add(this);

		}

		@Override
		protected ArrayList<Movies> doInBackground(String... params) {

			if (!isOnline()) {

				return null;
			}
			String displayText = HttpRequest.MovieSearch(params[0], params[1],
					params[2], params[3]);
			if(displayText.equalsIgnoreCase("error"))
			{
				return null;
			}
			Movie movie = SearchParser.parseMovie(displayText);
			
			if(movie!=null){
			return (ArrayList<Movies>) movie.getMovies();
			}
			else{
				return null;
				
			}
		}

		protected void onPostExecute(ArrayList<Movies> movieList) {

			try {

				tasks.remove(this);
				if (tasks.size() == 0) {
					pb.setVisibility(View.INVISIBLE);
				}
				if (movieList == null ) {
					Toast.makeText(MainActivity.this,
							"No movie is avialable with this Name or Please the Check Internet Connection", Toast.LENGTH_LONG)
							.show();
					return;
				}
				
				if (movieList.size()==0 ) {
					Toast.makeText(MainActivity.this,
							"No movie is avialable with this Name", Toast.LENGTH_LONG)
							.show();
					return;
				}
				movie_List=movieList;
				updateDisplay();
			} catch (Exception e) {
				System.out.println("Error in onPostExecute" + e.toString());
			}

			// display.setText(movie.getTotal().toString());
		}

	}
	
	protected void updateDisplay() {
		pb.setVisibility(View.VISIBLE);
		MovieAdapter adapter = new MovieAdapter(this, R.layout.movie_list, movie_List);
		View button1 = findViewById(R.id.button1);
		button1.setVisibility(View.GONE);
		View searchbox = findViewById(R.id.searchbox);
		searchbox.setVisibility(View.GONE);
		setListAdapter(adapter);
		ListView lv = getListView();

	        // listening to single list item on click
	        lv.setOnItemClickListener(new OnItemClickListener(){
	          public void onItemClick(AdapterView<?> parent, View view,
	              int position, long id) {
	        	  
	        	  // selected item
	      		Movies movie=movie_List.get(position);
	        	  
	        	  // Launching new Activity on selecting single List Item
	        	 Intent i = new Intent(getApplicationContext(), SingleMovieDisplay.class);
	        	  // sending data to new activity
	        	  i.putExtra("movie", (new Gson()).toJson(movie));
	        	  startActivity(i);
	        	
	          }
	        });
		
	        pb.setVisibility(View.INVISIBLE);
		
	}

	public void DisplayMovieList(ArrayList<Movies> movieList) {

		try {
			ListIterator<Movies> litr = movieList.listIterator();
			while (litr.hasNext()) {
				Movies m = litr.next();
				String str = m.getTitle();
				output.append(str + '\n');

			}
		} catch (Exception e) {
			System.out.println("DisplayMovieList" + e.toString());
		}
	}
	public void onClickSearch(View view) {
		EditText movie = (EditText) findViewById(R.id.searchbox);
		String query = movie.getText().toString();
		myAsync async = new myAsync();
		async.execute(query, Api.movieSearchapi, Api.key, "");

	}

}
