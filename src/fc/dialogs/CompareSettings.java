package fc.dialogs;

import fc.app.R;
import fc.comparator.CompareConfig;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class CompareSettings extends Activity
{
	private CompareConfig cmpConfig = new CompareConfig();
	private Intent intent = new Intent();
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.settings_cmp);
        
        Intent i = getIntent();
        cmpConfig.SetShowOnlyEq( i.getExtras().getBoolean( "only_eq" ) );
        cmpConfig.SetShowFilesNotExistsOnly( i.getExtras().getBoolean( "unique" ) );
        cmpConfig.SetShowOnlyCompared( i.getExtras().getBoolean( "compared" ) );
        cmpConfig.SetShowHidden( i.getExtras().getBoolean( "hidden" ) );
        
		CheckBox onlyEq_bx = (CheckBox)findViewById(R.id.setting_only_eq);
		CheckBox unique_bx = (CheckBox)findViewById(R.id.setting_unique);
		CheckBox compared_bx = (CheckBox)findViewById(R.id.setting_compared);
		CheckBox hidden_bx = (CheckBox)findViewById(R.id.setting_hidden);
		
		onlyEq_bx.setChecked( cmpConfig.GetShowOnlyEq() );
		unique_bx.setChecked( cmpConfig.GetShowFilesNotExistsOnly() );
		compared_bx.setChecked( cmpConfig.GetShowOnlyCompared() );
		hidden_bx.setChecked( cmpConfig.GetShowHidden() );
		
		onlyEq_bx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				cmpConfig.SetShowOnlyEq( isChecked );
				intent.putExtra("only_eq", isChecked);
			}
		});
		
		unique_bx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				cmpConfig.SetShowFilesNotExistsOnly( isChecked );
				intent.putExtra("unique", isChecked);
			}
		});
		
		compared_bx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				cmpConfig.SetShowOnlyCompared( isChecked );
				intent.putExtra("compared", isChecked);				
			}
		});
		
		hidden_bx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				cmpConfig.SetShowHidden( isChecked );
				intent.putExtra("hidden", isChecked);				
			}
		});
    }
    
	@Override
	protected void onResume() {
		super.onResume();
		
		intent.putExtra("only_eq", cmpConfig.GetShowOnlyEq());
		intent.putExtra("unique", cmpConfig.GetShowFilesNotExistsOnly());
		intent.putExtra("compared", cmpConfig.GetShowOnlyCompared());
		intent.putExtra("hidden", cmpConfig.GetShowHidden());
			
		setResult(RESULT_CANCELED, intent);
	}
}
