/**
 * 
 */
package model.moviesuggest;

import java.util.List;

import model.moviesuggest.Movie.Movies;
import web.moviesuggest.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * @author kumra32
 *
 */
public class MovieAdapter extends ArrayAdapter<Movies> {
	
	private Context context;
	private List<Movies> movieList;
	
	public MovieAdapter(Context context, int resource, List<Movies> objects) {
		super(context, resource, objects);
		this.context = context;
		this.movieList = objects;
		
	}

	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = 
				(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.movie_list, parent, false);

		//Display movie name in the TextView widget
		Movies movie = movieList.get(position);
		TextView tv = (TextView) view.findViewById(R.id.textView1);
		tv.setText(movie.getTitle());
		return view;
	}


}
