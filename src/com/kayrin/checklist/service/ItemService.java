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
			itemDetails = new ItemDetails();
			itemDetails.setItemText(requestItemDetails.getItemText());
		}

		logger.exit();
		return itemDetailsDao.saveItemDetail(itemDetails); 
	}
	

	
}
