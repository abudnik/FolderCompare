package fc.dialogs;

import fc.app.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class About extends Activity implements OnClickListener
{
	private static final String[] EMAIL = {"folder.compare@gmail.com"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.about);
		
		String version = "";
		try
		{
			version = getPackageManager().getPackageInfo( getPackageName(), 0 ).versionName;
		}
		catch( NameNotFoundException e )
		{
			e.printStackTrace();
		}
		
		String text = "Folder Compare v" + version + ": If you have any questions or "
						+"comments, please email developers."
						+"\n\nThank you\n";
		
		TextView label = (TextView)findViewById(R.id.about_top_label);
		label.setText(text);
		
		Button email = (Button)findViewById(R.id.about_email);
		email.setOnClickListener(this);
		
		Button license = (Button)findViewById(R.id.about_license);
		license.setOnClickListener(this);
	}

	@Override
	public void onClick(View view)
	{
		int id = view.getId();
		Intent i = new Intent();
		
		switch(id) {
			case R.id.about_email:
				i.setAction(android.content.Intent.ACTION_SEND);
				i.setType("message/rfc822");
				i.putExtra(Intent.EXTRA_EMAIL, EMAIL);
				try {
					startActivity(Intent.createChooser(i, "Email using..."));
					
				} catch(ActivityNotFoundException e) {
					Toast.makeText(this, "Sorry, could not start the email", Toast.LENGTH_SHORT).show();
				}
				break;
			
			case R.id.about_license:
				Intent intent = new Intent( this, LicenseView.class );
				startActivity( intent );
				break;
		}
	}
}
