/**
 * 
 */
package com.viksitpro.core.services.powerpoint;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.viksitpro.core.exceptions.EntityNotFoundException;
import com.viksitpro.core.logger.ViksitLogger;


/**
 * @author Mayank
 *
 */
public class ConversionStatusChecker {

	


	public JSONObject getStatus(Integer jobId, String apiKey) throws EntityNotFoundException, ClientProtocolException, IOException {
			
		 	
			if(apiKey==null)
			{
				throw new EntityNotFoundException("zamzar key is not defined in app.properties");
			}
	        String endpoint = "https://api.zamzar.com/v1/jobs/" + jobId;

	        // Create HTTP client and request object
	        CloseableHttpClient httpClient = getHttpClient(apiKey);
	        HttpGet request = new HttpGet(endpoint);

	        // Make request
	        CloseableHttpResponse response = httpClient.execute(request);

	        // Extract body from response
	        HttpEntity responseContent = response.getEntity();
	        String result = EntityUtils.toString(responseContent, "UTF-8");

	        // Parse result as JSON
	        JSONObject json = new JSONObject(result);

	        // Print result
	        ViksitLogger.logMSG(this.getClass().getName(),json.toString());

	        // Finalise response and client
	        response.close();
	        httpClient.close();
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