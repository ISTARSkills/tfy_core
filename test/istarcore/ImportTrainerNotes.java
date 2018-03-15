/**
 * 
 *//*
package istarcore;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import java.net.HttpURLConnection;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

*//**
 * @author ISTAR-SERVER-PU-1
 *
 *//*
public class ImportTrainerNotes {

 public static void main(String[] args) throws FileNotFoundException, IOException{

		String url = "http://192.168.0.100:8080/t2c/lessons/user/3658/add_log/lesson/2931/5324/But,%20remember.../30";
		URL obj = new URL(url);
		java.net.HttpURLConnection con = (java.net.HttpURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");


		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		//ViksitLogger.logMSG(this.getClass().getName(),"\nSending 'POST' request to URL : " + url);
		//ViksitLogger.logMSG(this.getClass().getName(),"Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		//ViksitLogger.logMSG(this.getClass().getName(),response.toString());
		
		
		// test();
	 
	 //updateSlideTN(523, 7537, "This session is an introduction to Indian baking System");
 }
	
	public static void test() throws FileNotFoundException, IOException {
		String file = "C:\\Users\\ISTAR-SERVER-PU-1\\Desktop\\Book1.xlsx";
		XSSFWorkbook fs = new XSSFWorkbook(new FileInputStream(file));
		XSSFSheet wb = fs.getSheetAt(0);
		int lastROW = wb.getLastRowNum();

		for (int i = 0; i < lastROW; i++) {
			try {
				int lessonID = (int) wb.getRow(i).getCell(2).getNumericCellValue();
				int slideID = Integer.parseInt(wb.getRow(i).getCell(3).getStringCellValue().split("#")[1]);
				String tnotes = wb.getRow(i).getCell(4).getStringCellValue();
				//ViksitLogger.logMSG(this.getClass().getName(),("lesson id ->" + lessonID + " slideID -> " + slideID + " coment " + tnotes.trim());
				updateSlideTN(lessonID, slideID, tnotes);
			} catch (Exception e) {
				
				// e.printStackTrace();
			}
		}

	}

	static void updateSlideTN(int lessonID, int slideID, String Tnotes) throws IOException {
//Request URL:http://elt.talentify.in:8080/content/edit_teachernotes

			String url = "http://192.168.0.100:8080/t2c/lessons/user/3658/add_log/lesson/2931/5324/But,%20remember.../30";
			URL obj = new URL(url);
			java.net.HttpURLConnection con = (java.net.HttpURLConnection) obj.openConnection();

			//add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			String urlParameters = "lesson_id="+lessonID+"&slide_id="+slideID+"&teacher_notes_comment="+Tnotes;

			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			//ViksitLogger.logMSG(this.getClass().getName(),"\nSending 'POST' request to URL : " + url);
			//ViksitLogger.logMSG(this.getClass().getName(),"Post parameters : " + urlParameters);
			//ViksitLogger.logMSG(this.getClass().getName(),"Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			//ViksitLogger.logMSG(this.getClass().getName(),response.toString());

		}

	}

*/