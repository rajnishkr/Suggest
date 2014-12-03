package web.moviesuggest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SingleMovieDisplay extends Activity {

	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        this.setContentView(R.layout.single_movie_view);
	        
	        TextView txtProduct = (TextView) findViewById(R.id.movie_lable);
	        
	        Intent i = getIntent();
	        // getting attached intent data
	        String product = i.getStringExtra("product");
	        // displaying selected product name
	        txtProduct.setText(product);
	        
		}
}
