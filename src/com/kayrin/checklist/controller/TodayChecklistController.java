package com.kayrin.checklist.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import com.kayrin.checklist.model.ItemDetails;
import com.kayrin.checklist.service.ItemService;

@RestController
public class TodayChecklistController {
	
	static final Logger logger = LogManager.getLogger(TodayChecklistController.class.getName());
	
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
	

	@RequestMapping(value = "saveItem", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)	
	public @ResponseBody ResponseEntity<?> saveItem(@RequestBody ItemDetails itemDetails) {
		logger.entry();
		logger.debug(itemDetails.toString());
		
		//TODO: validate itemDetails
		
		
		
		ItemDetails result = itemService.saveItemDetail(itemDetails);
		logger.debug("new item id = " + itemDetails.getItemId());
		
		logger.exit();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	


}
