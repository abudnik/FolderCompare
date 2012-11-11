package fc.dialogs;

import java.io.InputStream;

import fc.app.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class LicenseView extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.license);
        
        InputStream input = getResources().openRawResource( R.raw.license );
		try
		{
			byte[] fileData = new byte[ input.available() ];
			input.read( fileData );
			input.close();
			GetListView().setText( new String( fileData, "UTF-8" ) );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
    }
    
	private TextView GetListView()
	{
		return (TextView)findViewById( R.id.license_text );
	}
}
