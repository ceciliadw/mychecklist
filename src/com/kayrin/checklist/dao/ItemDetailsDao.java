package com.kayrin.checklist.dao;



import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
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
	
	public ItemDetails getItemDetailByItemId(String taskDate, String itemId){
		logger.entry();
		ItemDetails itemDetails = null;
		try{			
			ItemDetails hashKey = new ItemDetails();
			hashKey.setTaskDate(taskDate);
			
			Condition sortKeyCondition = new Condition()
		            .withComparisonOperator(ComparisonOperator.EQ.toString())
		            .withAttributeValueList(new AttributeValue().withS(itemId));
			
			DynamoDBQueryExpression<ItemDetails> queryExpression = new DynamoDBQueryExpression<ItemDetails>()
		            .withHashKeyValues(hashKey)
		            .withRangeKeyCondition("itemId", sortKeyCondition);

			List<ItemDetails> list = mapper.query(ItemDetails.class, queryExpression);
			if(list != null && !list.isEmpty()){
				itemDetails = list.get(0);
				logger.debug("item details retrieved");
			}
			
		} catch(Exception e){
			logger.error(e);
		} 
		
		logger.exit();
		return itemDetails;
	}
	
	public List<ItemDetails> loadItemDetailsByTaskDate(DateTime taskDate){
		logger.entry();
		List<ItemDetails> list = new ArrayList<ItemDetails>();
		try{
			
			
			
			
		
			
		} catch(Exception e){
			logger.error(e);
		} 
		
		
		logger.exit();
		return list; 
	}


}
