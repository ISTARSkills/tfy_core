/**
 * 
 */
package com.viksitpro.core.services.powerpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.json.JSONArray;
import org.json.JSONObject;

import com.viksitpro.core.exceptions.EntityNotFoundException;

/**
 * @author Mayank
 *
 */
public class PowerPointConverisonStatusUpdator extends FutureTask {

	private Integer lessonId;
	private String apipKey;
	public PowerPointConverisonStatusUpdator(Callable callable, Integer lessonId, String apiKey) {
		super(callable);	
		this.lessonId = lessonId;
		this.apipKey = apiKey;
	}
	
	@Override
	public void done()
	{
		try {
		JSONObject jobObject = (JSONObject)this.get();
		if(jobObject!=null)
		{
			if(jobObject.has("id"))
			{
				Integer jobId = jobObject.getInt("id");
				boolean converisonCompleted = false;
				JSONArray targetFiles = new JSONArray();
				while(!converisonCompleted)
				{
					ConversionStatusChecker checker = new ConversionStatusChecker();
					JSONObject obj = null;
					obj = checker.getStatus(jobId, apipKey);
					if(obj!=null)
					{
						if(obj.has("status"))
						{
							String status = obj.getString("status");
							if(status.equalsIgnoreCase("successful"))
							{
								converisonCompleted= true;
								targetFiles = obj.getJSONArray("target_files");
								break;
							}
							else
							{
								Thread.sleep(10000);
							}	
						}
						else
						{
							
							break;
						}	
					}
					else
					{
						break;
					}	
				}
				
				if(converisonCompleted)
				{
					ArrayList<Integer> slideIds = new ArrayList<>();
					if(targetFiles!=null && targetFiles.length()>0)
					{
						for(int i =0; i< targetFiles.length();i++)
						{
							JSONObject slideObject = targetFiles.getJSONObject(i);
							if(slideObject.getString("name").contains(".png"))
							{
							int slideId = slideObject.getInt("id");
							slideIds.add(slideId);
							}
						}
					}
					ExecutorService executor = Executors.newFixedThreadPool(1);
					IstarXMLCreator creator = new IstarXMLCreator(slideIds,lessonId);
					FutureTask t = new FutureTask<>(creator);
					executor.execute(t);
				}
				
			}
		}	
		
		} catch (InterruptedException | ExecutionException | EntityNotFoundException | IOException e) {
			e.printStackTrace();			
		}
	}
}