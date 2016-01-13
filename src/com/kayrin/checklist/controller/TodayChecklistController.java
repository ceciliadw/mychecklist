package com.kayrin.checklist.controller;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.util.StringUtils;
import com.kayrin.checklist.model.ItemDetails;
import com.kayrin.checklist.service.ItemService;

@RestController
public class TodayChecklistController {
	
	@Autowired
	private ItemService itemService; 
	
	@RequestMapping(value="today", method=RequestMethod.GET)
	public ModelAndView init(Model model){
		DateTime today = DateTime.now();
		String todaysDate = today.toString(DateTimeFormat.forPattern("E, d MMM yyyy ")); 
		model.addAttribute("todaysDate", todaysDate); 	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("today");
		modelAndView.addObject("todaysDate", todaysDate);
		return modelAndView;
	}
	

	@RequestMapping(value = "updateItem", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)	
	public @ResponseBody ResponseEntity<?> updateItem(@RequestBody ItemDetails itemDetails) {
		//TODO: validate itemDetails
		
		
		itemService.saveItemDetails(itemDetails);
		
		ItemDetails result = new ItemDetails();
		result.setItemId("new item id + " + itemDetails.getItemId());
		result.setItemText("my random text + " + itemDetails.getItemText());
		
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	


}
