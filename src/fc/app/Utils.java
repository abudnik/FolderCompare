package fc.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class Utils
{
	public static String GetFilePermissions( File file )
	{
		String p = "-";
		
		if ( file.isDirectory() )
			p += "d";
		if ( file.canRead() )
			p += "r";
		if ( file.canWrite() )
			p += "w";
		
		return p;
	}
	
	public static String ReadFileAsString( String path )
	{
		FileInputStream stream = null;
		try
		{
			stream = new FileInputStream(new File(path));
			FileChannel fc = stream.getChannel();
		    MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
		    /* Instead of using default, pass in a decoder. */
		    return Charset.defaultCharset().decode(bb).toString();
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		finally
		{
			if ( stream != null )
			{
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}

	public static void WriteStringToFile( String data, String filePath )
	{
        BufferedWriter writer = null;
        try
        {
            writer = new BufferedWriter( new FileWriter( filePath ) );
            writer.write( data );
        }
        catch( IOException e )
        {
        	e.printStackTrace();
        }
        finally
        {
            try
            {
                if ( writer != null)
                        writer.close();
            }
            catch( IOException e ) {}
        }
	}
}
