package com.kayrin.checklist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.util.StringUtils;
import com.kayrin.checklist.dao.ItemDetailsDao;
import com.kayrin.checklist.model.ItemDetails;

@Service
public class ItemService {
	
	@Autowired
    private ItemDetailsDao itemDetailsDao; 
	
	public ItemDetails saveItemDetails(ItemDetails requestItemDetails){
		if(StringUtils.isNullOrEmpty(requestItemDetails.getItemId())){
			//TODO: check if itemId exist in the database
			boolean isExist = false; 
			if(isExist){
				return itemDetailsDao.updateItemDetails(requestItemDetails); 
			}
		}
		
		return itemDetailsDao.createItemDetails(requestItemDetails); 
	}
	

	
}
