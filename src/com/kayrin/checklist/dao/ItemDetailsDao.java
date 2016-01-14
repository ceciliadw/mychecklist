package com.kayrin.checklist.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kayrin.checklist.model.ItemDetails;

@Component
public class ItemDetailsDao {
	
	static final Logger logger = LogManager.getLogger(ItemDetailsDao.class.getName());

	
	private AmazonDynamoDBClient dynamoDbClient;
	private DynamoDBMapper mapper;
	
	public ItemDetailsDao(){
		dynamoDbClient = new AmazonDynamoDBClient(new ProfileCredentialsProvider());
		mapper = new DynamoDBMapper(dynamoDbClient);
		java.security.Security.setProperty("networkaddress.cache.ttl" , "60");		
	}
	
	public ItemDetails saveItemDetail(ItemDetails itemDetails){
		logger.entry();
		try{						
	        mapper.save(itemDetails);
	        logger.debug("item details saved");
		} catch(Exception e){
			logger.error(e);
			throw e;
		} 
		
		logger.exit();
		return itemDetails;
	}
	
	public ItemDetails getItemDetailByItemId(String itemId){
		logger.entry();
		ItemDetails itemDetails = null;
		try{				 
			itemDetails = mapper.load(ItemDetails.class, itemId);
	        logger.debug("item details retrieved");
		} catch(Exception e){
			logger.error(e);
		} 
		
		logger.exit();
		return itemDetails;
	}


}
