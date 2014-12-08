package web.moviesuggest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.StringDef;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class AboutUs extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_us);
		TextView textDetails = (TextView) findViewById(R.id.aboutUs);
		String message="A application for bollywood and hollywood movie fans." +
				'\n'+
				"The movies search for plain text queries. Allows you to search for movies! " +
				"Application helps you with similar movie to watch next!"+ '\n' +
				"The application uses RottenTomataoApi for getting similar movies."+ '\n'+'\n'+ "Rajnish Kumar."+'\n'+
				"email: rajnish.kr123@gmail.com"+'\n'+"Contact No: +91-8106999860";
		textDetails.setText(message);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about_us, menu);
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
