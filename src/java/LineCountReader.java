import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


/**
 * 
 * @author Saif Asif
 */
public class LineCountReader {
	
	public static void main(String[] args){
		String input = "";
		if (args.length > 0) {
			input = args[0];
		}
		InputStream is = null;
		try {
			is = new BufferedInputStream(new FileInputStream(input));
			byte[] c = new byte[1024];
			long count = 0;
			int readChars = 0;
			boolean isNL = false;
			while ((readChars = is.read(c)) != -1) {
				for (int i = 0; i < readChars; ++i) {
					if(c[i] == '\n'){
						count++;
						isNL = true;
					}else if(c[i] == 0x0d && !isNL){
						count++;
					}else if(c[i] == 0x0a && !isNL){
						count++;
					}
				}
			}
			// Fix the end count
			System.out.println(isNL ? --count : ++count);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
