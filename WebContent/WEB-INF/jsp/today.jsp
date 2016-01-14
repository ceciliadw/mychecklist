<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">	
	<script src="http://code.jquery.com/jquery-1.12.0.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
	<title>Today</title>
</head>
<body>
	<c:url var="home" value="/" scope="request" />
	<form id="my-checklist-form">
	<div class="container-fluid">
		<div class="row">
		  <div class="col-xs-12 col-sm-6 col-md-8">
		  	<h1>${todaysDate}</h1>
		  	
		  	<div id="add-button-div">
				<a id="add-button" href="#" class="btn btn-primary">
		          <span class="glyphicon glyphicon-plus"></span> Add new item 
		        </a>
			</div> 
			
			<div id="item-template" class="hidden item-div">
				<p>
				<div class="form-inline">
					<div class="checkbox hidden">
						<label>
						<input type="checkbox" />
						</label>
					</div>				
					<div class="form-group">						
						<input type="text" class="form-control item-text" placeholder="Edit me"/>
						<input type="hidden" name="itemId" />
					</div>
					<div class="form-group">
						<a href="#" class="btn btn-success" onclick="saveItem(this);">
				          <span class="glyphicon glyphicon-check"></span> 
				        </a>
				        
				        <a href="#" class="btn btn-primary hidden edit-item" onclick="editItem(this);">
				          <span class="glyphicon glyphicon-pencil"></span> 
				        </a>
				 
						<a href="#" class="btn btn-danger" onclick="removeItem(this);">
				          <span class="glyphicon glyphicon-trash"></span> 
				        </a>
				  
					</div>
		 			
		 			
		 		</div>
		 		</p>
		 	</div>
		  
		  
		  </div>
		  <div class="col-xs-6 col-md-4"></div>
		</div>

	</div>
	</form>
	
	<script type="text/javascript">
		var countItem = 0;
		$("#add-button").click(function(){
			countItem = countItem+1;
			$("#add-button-div").before($("#item-template").clone().removeClass("hidden").attr("id", "item-"+countItem));
		});
		
		
		function removeItem(item){
			$(item).closest(".item-div").remove();
		}
		
		function editItem(item){
			var itemText = $(item).closest(".item-div").find(".item-text");
			var itemCheckbox = $(item).closest(".item-div").find(".checkbox");
			
			
			$(itemText).removeClass("hidden");
			$(itemCheckbox).addClass("hidden");
			$(item).addClass("hidden");
			$(item).prev().removeClass("hidden");
		}
		
		function saveItem(itemUpdateButton){
			var itemId = $(itemUpdateButton).closest(".item-div").find("input[name='itemId']");
			var itemText = $(itemUpdateButton).closest(".item-div").find(".item-text");
			var itemCheckbox = $(itemUpdateButton).closest(".item-div").find(".checkbox");
			
			
			var itemDetails = {}
			itemDetails["itemId"] = $(itemId).attr("value");
			itemDetails["itemText"] = $(itemText).attr("value");
			
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "saveItem.json",
				data : JSON.stringify(itemDetails),
				dataType : 'json',
				timeout : 10000,
				success : function(data) {
					console.log("SUCCESS: ", data);
					saveItemSuccess(itemId, itemText, itemCheckbox, itemUpdateButton, data.itemId, data.itemText);
				},
				error : function(e) {
					console.log("ERROR: ", e);
					//display(e);
				},
			});
			
		}
		

		
		function saveItemSuccess(itemId, itemText, itemCheckbox, itemUpdateButton, itemIdValue, itemTextValue){
			//hide item text field
			$(itemText).addClass("hidden");
			$(itemText).val(itemTextValue);
			
			//update and show checkbox text
			$(itemCheckbox).contents().filter(function () {
			     return this.nodeType === 3; 
			}).remove();
			$(itemCheckbox).append(itemTextValue);
			$(itemCheckbox).removeClass("hidden");
			
			//hide update button 
			$(itemUpdateButton).addClass("hidden");
			
			//show edit button
			$(itemUpdateButton).next().removeClass("hidden");	
			
			//update item id 
			$(itemId).val(itemIdValue);
		}

	</script>
	
</body>
</html>