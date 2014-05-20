import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class RetrieveURLContents {
	
	public static void main(String[] args) throws IOException {
		URL url = new URL("http://www.google.com");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    	connection.setRequestProperty("Cookie", "");
		connection.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
		connection.addRequestProperty("User-Agent", "");
		connection.addRequestProperty("Referer", "");
		System.out.println(connection.getResponseCode());
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		while(br.read() != -1){
			System.out.println(br.readLine());
		}
	}
}
