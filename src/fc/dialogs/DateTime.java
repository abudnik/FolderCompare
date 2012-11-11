package fc.dialogs;

import java.util.Date;

import fc.app.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class DateTime extends Activity
{
	private long time;
	private Intent intent = new Intent();
	private DatePicker datePicker;
	private TimePicker timePicker;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.date_time);
        
        Intent i = getIntent();
        time = i.getLongExtra("time", -1);
        
        datePicker = (DatePicker)findViewById(R.id.datePicker);
        timePicker = (TimePicker)findViewById(R.id.timePicker);
        
        if ( time != -1 )
        {
        	Date d = new Date( time );
        	datePicker.updateDate( d.getYear() + 1900, d.getMonth(), d.getDay() );
        	timePicker.setCurrentHour( d.getHours() );
        	timePicker.setCurrentMinute( d.getMinutes() );
        }
        
        Button setBtn = (Button)findViewById(R.id.filter_set);
        Button resetBtn = (Button)findViewById(R.id.filter_reset);
        Button cancelBtn = (Button)findViewById(R.id.filter_cancel);
        
        setBtn.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Date d = new Date();
				
				d.setYear( datePicker.getYear() - 1900 );
				d.setMonth( datePicker.getMonth() );
				d.setDate( datePicker.getDayOfMonth() );
				d.setHours( timePicker.getCurrentHour() );
				d.setMinutes( timePicker.getCurrentMinute() );
				d.setSeconds( 0 );
				
				time = d.getTime();
				intent.putExtra("time", time);
				
				finish();
			}
		});
        
        resetBtn.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				time = -1;
				intent.putExtra("time", time);
			}
		});
        
        cancelBtn.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				intent.putExtra("time", time);
				finish();
			}
		});
    }
    
	@Override
	protected void onResume()
	{
		super.onResume();
		intent.putExtra("time", time);
		setResult(RESULT_CANCELED, intent);
	}
}
