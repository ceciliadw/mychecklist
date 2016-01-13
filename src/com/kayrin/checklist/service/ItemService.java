package com.kayrin.checklist.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	public ItemDetails saveItemDetails(ItemDetails requestItemDetails){
		logger.entry();
		ItemDetails itemDetails = null;
		
		if(StringUtils.isNullOrEmpty(requestItemDetails.getItemId())){
			//TODO: check if itemId exist in the database
			boolean isExist = false; 
			if(isExist){
				
			}
		}
		
		
		//create new item
		itemDetails = new ItemDetails();
		itemDetails.setItemText(requestItemDetails.getItemText());
		
		logger.exit();
		return itemDetailsDao.saveItemDetails(itemDetails); 
	}
	

	
}
