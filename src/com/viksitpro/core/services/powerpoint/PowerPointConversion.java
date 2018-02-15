/**
 * 
 */
package com.viksitpro.core.services.powerpoint;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.viksitpro.core.logger.ViksitLogger;


/**
 * @author Mayank
 *
 */
public class PowerPointConversion implements Callable<JSONObject> {
	
    private String sourceFile;
    private String apiKey;
	
	public PowerPointConversion(String sourceFile, String apiKey) {
		super();
		this.sourceFile = sourceFile;
		this.apiKey = apiKey;
	}

	@Override
	public JSONObject call() throws ClientProtocolException, IOException {
		JSONObject json = null;
		if(sourceFile!=null && apiKey!=null)
		{
			 String endpoint = "https://sandbox.zamzar.com/v1/jobs";
		        String targetFormat = "png";
				
				
				CloseableHttpClient httpClient = getHttpClient(apiKey);
		        HttpEntity requestContent = MultipartEntityBuilder.create()
		            .addPart("source_file", new FileBody(new File(sourceFile)))
		            .addPart("target_format", new StringBody(targetFormat, ContentType.TEXT_PLAIN))
		            .build();
		        HttpPost request = new HttpPost(endpoint);
		        request.setEntity(requestContent);

		        // Make request
		        CloseableHttpResponse response = httpClient.execute(request);

		        // Extract body from response
		        HttpEntity responseContent = response.getEntity();
		        String result = EntityUtils.toString(responseContent, "UTF-8");

		        // Parse result as JSON
		         json = new JSONObject(result);

		        // Print result
		        ViksitLogger.logMSG(this.getClass().getName(),json);

		        // Finalise response and client
		        response.close();
		        httpClient.close();
		}	
       
		
		return json;
	}

	 private static CloseableHttpClient getHttpClient(String apiKey) {
	        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
	        credentialsProvider.setCredentials(AuthScope.ANY,
	                new UsernamePasswordCredentials(apiKey, ""));

	        CloseableHttpClient httpClient = HttpClientBuilder.create()
	                .setDefaultCredentialsProvider(credentialsProvider)
	                .build();

	        return httpClient;
	    }
}
