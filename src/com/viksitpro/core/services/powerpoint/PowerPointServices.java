/**
 * 
 */
package com.viksitpro.core.services.powerpoint;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
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

import com.viksitpro.core.exceptions.EntityNotFoundException;
import com.viksitpro.core.logger.ViksitLogger;
import com.viksitpro.core.utilities.AppProperies;
import com.viksitpro.core.utilities.DBUTILS;

/**
 * @author vaibhav
 *
 */
public class PowerPointServices {

	public void importPowerpointToLesson(Integer lessonId,String powerpointPath) throws EntityNotFoundException
	{
		String apiKey = null;
		apiKey =AppProperies.getProperty("zamzar_key");
		if(apiKey==null)
		{
			throw new EntityNotFoundException("Zamzar key is not defined");
		}		
		
		ExecutorService executor = Executors.newFixedThreadPool(1);
		PowerPointConversion consversionJob = new PowerPointConversion(powerpointPath,apiKey);
		PowerPointConverisonStatusUpdator statusUpdator = new PowerPointConverisonStatusUpdator(consversionJob, lessonId,apiKey);
		executor.execute(statusUpdator);

	}
	
	

    public static void main(String[] args) throws Exception {
        String apiKey = "917b119e3f312e41fedb1dd81f6ca4c0dbad0e6f";
        String endpoint = "https://sandbox.zamzar.com/v1/jobs";
        String sourceFile = "C:\\Users\\Mayank\\Downloads\\Session3.pptx";
        String targetFormat = "png";

        // Create HTTP client and request object
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
        JSONObject json = new JSONObject(result);

        // Print result
       

        // Finalise response and client
        response.close();
        httpClient.close();
    }

    // Creates a HTTP client object that always makes requests
    // that are signed with the specified API key via Basic Auth
    private static CloseableHttpClient getHttpClient(String apiKey) {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(apiKey, ""));

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();

        return httpClient;
    }



	public void markLessonAsPresentation(Integer lessonId) {
		String sql ="update lesson set type='PRESENTATION' where id ="+lessonId;
		DBUTILS util = new DBUTILS();
		util.executeUpdate(sql);
		
	}
}
