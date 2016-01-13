package com.kayrin.checklist.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kayrin.checklist.model.ItemDetails;

@Component
public class ItemDetailsDao {
	
	static final Logger logger = LogManager.getLogger(ItemDetailsDao.class.getName());

	
	private AmazonDynamoDBClient dynamoDbClient;
	
	public ItemDetailsDao(){
		dynamoDbClient = new AmazonDynamoDBClient(new ProfileCredentialsProvider());
		java.security.Security.setProperty("networkaddress.cache.ttl" , "60");
	}
	
	public ItemDetails createItemDetails(ItemDetails requestItemDetails){
		try{			
			logger.entry();
			logger.debug("hellow!");
			requestItemDetails.setItemId(DateTime.now().toString());			
			DynamoDBMapper mapper = new DynamoDBMapper(dynamoDbClient);
	        mapper.save(requestItemDetails);
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		} 
		
		return requestItemDetails;
	}
	
	public ItemDetails updateItemDetails(ItemDetails requestItemDetails){
		return null;
	}

}
