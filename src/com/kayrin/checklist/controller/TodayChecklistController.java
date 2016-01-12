package com.kayrin.checklist.controller;

import java.util.Date;
import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
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

@RestController
public class TodayChecklistController {
	
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
		ItemDetails result = new ItemDetails();
		result.setItemId("new item id + " + itemDetails.getItemId());
		result.setItemText("my random text + " + itemDetails.getItemText());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "ajaxtest", method = RequestMethod.GET)
    public @ResponseBody
    String getTime() {
 
        Random rand = new Random();
        float r = rand.nextFloat() * 100;
        String result = "<br>Next Random # is <b>" + r + "</b>. Generated on <b>" + new Date().toString() + "</b>";
        System.out.println("Debug Message from CrunchifySpringAjaxJQuery Controller.." + new Date().toString());
        return result;
    }


}
