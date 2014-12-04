/**
 * 
 */
package model.moviesuggest;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import model.moviesuggest.Movie.Movies;
import web.moviesuggest.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.LruCache;
import android.util.LruCache;
/**
 * @author kumra32
 * 
 */
@SuppressLint("NewApi")
public class MovieAdapter extends ArrayAdapter<Movies> {

	private Context context;
	private List<Movies> movieList;
	public static LruCache<Integer, Bitmap> imageCache;

	public MovieAdapter(Context context, int resource, List<Movies> objects) {
		super(context, resource, objects);
		this.context = context;
		this.movieList = objects;	
		
		final int maxMemory = (int)(Runtime.getRuntime().maxMemory() /1024);
		final int cacheSize = maxMemory / 8;
		imageCache = new LruCache<>(cacheSize);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.activity_list, parent, false);

		// Display movie name in the TextView widget
		Movies movie = movieList.get(position);
		TextView tv = (TextView) view.findViewById(R.id.textView1);
		tv.setText(movie.getTitle());

		// Display Movie photo in ImageView widget
		try{
			
		int id=Integer.parseInt(movie.getId());
		Bitmap bitmap = imageCache.get(id);
		if (bitmap != null) {
			ImageView image = (ImageView) view.findViewById(R.id.imageView1);
			image.setImageBitmap(movie.getBitmap());
		}
		}
		catch(Exception e)
		{
			System.out.println("Chache Exception");
		}

		MovieAndView container = new MovieAndView();
		container.movie = movie;
		container.view = view;

		ImageLoader loader = new ImageLoader();
		loader.execute(container);

		return view;
	}

	class MovieAndView {
		public Movies movie;
		public View view;
		public Bitmap bitmap;
	}

	private class ImageLoader extends
			AsyncTask<MovieAndView, Void, MovieAndView> {

		@Override
		protected MovieAndView doInBackground(MovieAndView... params) {

			MovieAndView container = params[0];
			Movies movie = container.movie;

			try {
				String imageUrl = movie.getPosters().getDetailed();
				InputStream in = (InputStream) new URL(imageUrl).getContent();
				Bitmap bitmap = BitmapFactory.decodeStream(in);
				movie.setBitmap(bitmap);
				in.close();
				container.bitmap = bitmap;
				return container;
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(MovieAndView result) {
			ImageView image = (ImageView) result.view
					.findViewById(R.id.imageView1);
			image.setImageBitmap(result.bitmap);
			result.movie.setBitmap(result.bitmap);
			try{
				
				int id=Integer.parseInt(result.movie.getId());
				imageCache.put(id, result.bitmap);
			}
			catch(Exception e){
				
				System.out.println("Chache Exception");
			}
		}	

	}

}
