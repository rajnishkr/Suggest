package web.moviesuggest;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import model.moviesuggest.Movie;
import model.moviesuggest.Movie.Movies;
import resource.moviesuggest.Api;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	TextView output;
	ProgressBar pb;
	List<myAsync> tasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb = (ProgressBar) findViewById(R.id.progressBar1);
		pb.setVisibility(View.INVISIBLE);
        
        //output = (TextView) findViewById(R.id.textView);
		//output.setMovementMethod(new ScrollingMovementMethod());
		
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
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    class myAsync extends AsyncTask<String, String, ArrayList<Movies> >
    {
    	EditText display=null;
    	protected void onPreExecute() {
    		 //display= (EditText)findViewById(R.id.editText1);

		}
    	
		@Override
		protected ArrayList<Movies> doInBackground(String... params) {
			
			String displayText = HttpRequest.MovieSearch(params[0],params[1],params[2],params[3]);
			Movie movie=SearchParser.parseMovie(displayText);
			return (ArrayList<Movies>)movie.getMovies();
		}
		
		protected void onPostExecute(ArrayList<Movies> movieList) {
			
			try{
				
		       /* for(Movies m: movieList)   
		        {
		        	System.out.println(m.getTitle().toString());
		        	
		        }*/
				DisplayMovieList(movieList);
		    	}
		    	catch(Exception e)
		    	{
		    		System.out.println("fkghsda"+e.toString());
		    	}
			
			//display.setText(movie.getTotal().toString());
		}
    	
    	
    }
    
    public void DisplayMovieList(ArrayList<Movies> movieList)
    {
    	
    	//List mList =movie.getMovies();
	    /*for(int i=0;i<mList.size();i++)
	    {
	    	Movies m = mList.get(i);
	    	String str=m.getTitle();
	    	output.append(m.getTitle().toString());
	    }*/
    	
    	
    	try{
		ListIterator<Movies> litr = movieList.listIterator();
        while(litr.hasNext()) {
        	Movies m = litr.next();
        	String str=m.getTitle();
	    	output.append(str+'\n');
           
        }
    	}
    	catch(Exception e)
    	{
    		System.out.println("fkghsda"+e.toString());
    	}
    }
    public void onClickSearch(View view)
    {
    	EditText movie = (EditText)findViewById(R.id.searchbox);
    	String query= movie.getText().toString();
    	myAsync async=new myAsync();
    	async.execute(query,Api.movieSearchapi,Api.key,"");
    	
    	
    }
    
    
    
}
