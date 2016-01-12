package com.kayrin.checklist.model;

import java.io.Serializable;


public class ItemDetails implements Serializable{
	
	private String itemId; 
	private String itemText;
	
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemText() {
		return itemText;
	}
	public void setItemText(String itemText) {
		this.itemText = itemText;
	} 
	
	

}
