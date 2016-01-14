package com.kayrin.checklist.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.util.StringUtils;
import com.kayrin.checklist.dao.ItemDetailsDao;
import com.kayrin.checklist.model.ItemDetails;

@Service
public class ItemService {
	
	static final Logger logger = LogManager.getLogger(ItemService.class.getName());
	
	@Autowired
    private ItemDetailsDao itemDetailsDao; 
	
	public ItemDetails saveItemDetail(ItemDetails requestItemDetails){
		logger.entry();
		ItemDetails itemDetails = null;

		if(!StringUtils.isNullOrEmpty(requestItemDetails.getItemId())){
			//check if itemId exist in the database
			itemDetails = itemDetailsDao.getItemDetailByItemId(requestItemDetails.getItemId());
			
			//update existing item 
			if(itemDetails != null){
				itemDetails.setItemText(requestItemDetails.getItemText());
			}
		}
		
		//create new item
		if(itemDetails == null){
			String timeStamp = getFormattedCurrentDateTime(); 
			
			itemDetails = new ItemDetails();
			itemDetails.setItemText(requestItemDetails.getItemText());
			itemDetails.setCreatedDate(timeStamp);
			itemDetails.setTaskDate(timeStamp);
		}

		logger.exit();
		return itemDetailsDao.saveItemDetail(itemDetails); 
	}
	
	private String getFormattedCurrentDateTime(){
		TimeZone tz = TimeZone.getTimeZone("NZ");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
		df.setTimeZone(tz);
		return df.format(new Date());
		
	}
	
}
