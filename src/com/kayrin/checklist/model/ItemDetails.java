package com.kayrin.checklist.model;

import java.io.Serializable;
import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="myChecklist")
public class ItemDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String itemId; 
	private String itemText;
	private Date taskDate;
	
	@DynamoDBHashKey(attributeName="itemId")
	@DynamoDBAutoGeneratedKey
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	@DynamoDBAttribute(attributeName="itemText")
	public String getItemText() {
		return itemText;
	}
	public void setItemText(String itemText) {
		this.itemText = itemText;
	}
	

	public Date getTaskDate() {
		return taskDate;
	}
	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	} 
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb	.append("itemId = " + itemId + "\n")
			.append("itemText = " + itemText);
		return sb.toString();
	}

}
