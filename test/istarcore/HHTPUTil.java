/**
 * 
 */
package istarcore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;

/**
 * @author ISTAR-SERVER-PU-1
 *
 */
public class HHTPUTil {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		ArrayList<String> arrayList=new ArrayList<>();
		
		for(int i=0;i<50;i++){
			
		
		
		long tt = System.currentTimeMillis();
		URL website = new URL("http://cdn.talentify.in/courseZIPs/14.zip");
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = new FileOutputStream("C:\\Users\\ISTAR-SERVER-PU-1\\Downloads\\compresspng (1)\\"+i+"abc.zip");
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		
		arrayList.add(((System.currentTimeMillis()-tt)/1000)+"");
		
		////System.err.println((System.currentTimeMillis()-tt)/1000);
		
		/*
		long time = System.currentTimeMillis();
	 //System.out.println("start time"+time);
		// TODO Auto-generated method stub
		String url = "http://cdn.talentify.in/courseZIPs/5.zip";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestProperty("Accept-Encoding", "gzip");
		con.setRequestProperty("Content-Language", "en-US");
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
				
			while ((inputLine = in.readLine()) != null) {
					//System.out.println(inputLine);
				response.append(inputLine);
			}
			in.close();*/
	//}

		//	//System.out.println("end time "+(System.currentTimeMillis()-time)/1000);
		}
		for(String key:arrayList ){
			//System.out.println("Final timer------------- "+key);
		}
		System.exit(0);
	}

}
	