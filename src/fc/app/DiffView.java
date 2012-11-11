package fc.app;

import java.io.File;
import java.util.LinkedList;

import diff.diff_match_patch;
import diff.diff_match_patch.Diff;
import fc.dialogs.FolderChooser;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class DiffView extends Activity
{
	private static final int MENU_SAVE_REPORT	= 1;
	
	private static final int RESULT_CHOOSER		= 12;
	
	private String leftPath, rightPath;
	private diff_match_patch comparator;
	private LinkedList< Diff > diffs;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.diff);
        
        comparator = new diff_match_patch();
        
        Intent i = getIntent();
        leftPath = i.getExtras().getString( "left_path" );
        rightPath = i.getExtras().getString( "right_path" );
        
        CompareFiles();
    }
    
	private TextView GetListView()
	{
		return (TextView)findViewById( R.id.diff_text );
	}
	
	private String DiffToHTML( LinkedList< Diff > diffs )
	{
		String html = "";
		String s;
		
		for( Diff d : diffs )
		{
			s = d.text;
			String lines[] = s.split( "\n" );
			s = "";
			for( String line : lines )
			{
				s += "<![CDATA[" + line + "]]>&para;<br>";
			}
			
			switch( d.operation )
			{
				case EQUAL:
					html += s;
					break;
				
				case INSERT:
					html += "<br><b>&gt;&gt;&gt;</b><font color=\"#0bff0b\">";
					html += s;
					html += "</font>";
					break;
				
				case DELETE:
					html += "<br><b>&lt;&lt;&lt;</b><font color=\"#ff0b0b\">";
					html += s;
					html += "</font>";
					break;
			}
		}
		
		return html;
	}
	
	private void CompareFiles()
	{
		if ( new File( leftPath ).canRead() == false )
		{
			Toast.makeText( this, "Can't read file: " + leftPath, Toast.LENGTH_SHORT ).show();
			return;
		}
		
		if ( new File( rightPath ).canRead() == false )
		{
			Toast.makeText( this, "Can't read file: " + rightPath, Toast.LENGTH_SHORT ).show();
			return;
		}
		
		String text1 = Utils.ReadFileAsString( leftPath );
		String text2 = Utils.ReadFileAsString( rightPath );
		
		diffs = comparator.diff_main( text1, text2, true );
		
		comparator.diff_cleanupSemantic( diffs );
		
		String diffHtml = DiffToHTML( diffs );
		
		GetListView().setText( Html.fromHtml( diffHtml ) );
	}
	
	private void SaveReport( String reportPath )
	{
		String diffHtml = comparator.diff_prettyHtml( diffs );
		String out = "";
		
		out += "<html><head><title>File Comparison Report</title></head>";
		out += "<body><h1><strong>File Comparison Report</strong></h1>";
		out += "<p>Produced by <strong>Folder Compare</strong>.</p>";
		out += "<p>Compared \"" + leftPath + "\" and \"" + rightPath + "\".</p>";
		
		out += diffHtml;
		
		out += "</body></html>";
		
		Utils.WriteStringToFile( out, reportPath );
	}
	
	private void OnMenuSaveReport()
	{
		Intent intent = new Intent( DiffView.this, FolderChooser.class );
		intent.putExtra("default_name", "FileComparisonReport.html" );
		startActivityForResult( intent, RESULT_CHOOSER );
	}
	
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
    	super.onActivityResult(requestCode, resultCode, data);
    	
    	if ( resultCode == RESULT_CANCELED )
    	{
	    	if ( requestCode == RESULT_CHOOSER )
	    	{
	    		String filePath = data.getStringExtra("path");
	    		if ( filePath.length() > 0 )
	    		{
	    			SaveReport( filePath );
	    		}
	    	}
    	}
    }
	
    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
    	MenuItem item;
    	
    	menu.clear();
    	
    	item = menu.add( 0, MENU_SAVE_REPORT, 0, "Save Report" );
    	item.setIcon( R.drawable.save );
    	
    	return super.onPrepareOptionsMenu( menu );
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch( item.getItemId() )
    	{
			case MENU_SAVE_REPORT:
				OnMenuSaveReport();
				return true;
    	}
    	return super.onOptionsItemSelected( item );
    }
}
