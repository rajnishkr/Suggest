package web.moviesuggest;

import java.io.InputStream;
import java.net.URL;

import model.moviesuggest.Movie.Movies;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleMovieDisplay extends Activity {

	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        this.setContentView(R.layout.single_movie_view);
	        
	        TextView txtProduct = (TextView) findViewById(R.id.movie_lable);
	        
	        Intent i = getIntent();
	        // getting attached intent data
	        String content = i.getStringExtra("movie");
	        Movies movie=SearchParser.singleMovieParser(content);
	        // displaying selected product name
	       // txtProduct.setText(movie.getId());
	        
	        
			txtProduct.setText(movie.getTitle());

			// Display Movie photo in ImageView widget

			MovieAndView container = new MovieAndView();
			container.movie = movie;

			ImageLoader loader = new ImageLoader();
			loader.execute(container);

		}
	 
	 class MovieAndView {
			public Movies movie;
			public Bitmap bitmap;
		}
	 private class ImageLoader extends
		AsyncTask<MovieAndView, Void, MovieAndView> {

	@Override
	protected MovieAndView doInBackground(MovieAndView... params) {

		MovieAndView container = params[0];
		Movies movie=container.movie;

		try {
			String imageUrl = movie.getPosters().getDetailed();
			InputStream in = (InputStream) new URL(imageUrl).getContent();
			Bitmap bitmap = BitmapFactory.decodeStream(in);
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
		ImageView image = (ImageView)findViewById(R.id.singleImage);
		image.setImageBitmap(result.bitmap);
	}

}

}
