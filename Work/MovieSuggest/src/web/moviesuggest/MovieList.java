package web.moviesuggest;

import model.moviesuggest.MovieAdapter;
import model.moviesuggest.Movie.Movies;

import com.google.gson.Gson;

import android.support.v7.app.ActionBarActivity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MovieList extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.activity_list);
		
		updateDisplay();
	}
	
	private void updateDisplay(){
		MovieAdapter adapter = new MovieAdapter(this, R.layout.activity_list,
				MainActivity.movie_List);
		setListAdapter(adapter);
		ListView lv = getListView();

		// listening to single list item on click
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// selected item
				Movies movie = MainActivity.movie_List.get(position);

				// Launching new Activity on selecting single List Item
				Intent i = new Intent(getApplicationContext(),
						SingleMovieDisplay.class);
				// sending data to new activity
				i.putExtra("movie", (new Gson()).toJson(movie));
				startActivity(i);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
