$(document).ready(function () {
	
	  var utils = {
			    _url:'',
			    setup:function (u) {
			        this._url = u;
			    },
			    url:function (u) {
			        return this._url + u;
			    },
	   };
	  	
	var rowsArr = [];
	
	 utils.setup($("input#contextPath").val());
   //Tab Nav Links - Handler.
   $("div#nav").on("click", "a.tabLinks", function () {
	   var self = $(this);
	   $("div#" + self.attr("data-tab")).addClass("active").siblings("div").removeClass("active");
	   switch (self.attr("data-tab")){
	   case "myExpenseContainer" : 
		   loadExpenses();
		   break;
	   case "newExpenseContainer" :
		   $("button.edit-expense-btn").hide().siblings("button.add-expense-btn").show();
		   break;
	   case "approvalTab" :
		   break;
	   
	   }
   });
   
   //Add Expense - Event Handler declaration.
	$("button.add-expense-btn").on("click", function () {
		   $.post(utils.url('/expenses'), {description : $(".textbox-a").val(), expenseTypeId : $("#expenseTypeId").val(), amount : $("input#amount").val()} , function(data) {
			   $(".textbox-a").val("");
			   $("#expenseTypeId").val("");
			   $("input#amount").val("");
			   $("a[data-tab=myExpenseContainer]").click();
		   });
	});	
	
	//Update an Expense
	$("button.edit-expense-btn").on("click", function () {
		   $.post(utils.url('/expenses/'+$("input#expenseload").val()), {_method:"PUT", id : $("input#expenseload").val(), description : $(".textbox-a").val(), expenseTypeId : $("#expenseTypeId").val(), amount : $("input#amount").val()} , function(data) {
			   $("a[data-tab=myExpenseContainer]").click();
		   });
	});
	
	//Load My Expenses
	var loadExpenses = function (){
		$.getJSON(utils.url('/expenses'), function(jsonResponse) {
			   var expenseList = "";
			   rowsArr = jsonResponse;
			  /* $.each(jsonResponse, function () {
			      var self = this;
			      expenseList += '<tr class="tr-odd"><td>'+ self.id +'</td> <td> <h5 class="label-4">' + new Date(parseInt(self.expenseDate)) +
			      '</h5></td><td> <h5 class="label-4">' + self.expenseType.name + '</h5></td><td><h5 class="label-4">' +
			      self.description + '</h5></td><td><h5 class="label-4"><i class="alt-a label-5 blue"><img class="right-a-img" src="/html5expense/resources/images/question-a.png ">' + 
			      self.state +'</i><i class="alt-b label-6"><img class="question-a-img" src="/html5expense/resources/images/question-a.png" >' + 
			      self.state + '<a href="#" id=' + self.id + ' class="bg-edit-b"> <img class="clip-b-img" src="/html5expense/resources/images/edit-a.png"> </a>'+
			      '<a href="#" id=' + self.id + ' class="bg-cross-b"><img class="cross-b-img" src="/html5expense/resources/images/cross-b.png"> </a></i></h5></td></tr>"';    
			    });
			  $("table#expenseTable tbody","div.myexpense").html(expenseList); */
			  var optInit = {callback: pageselectCallback , items_per_page: 10 , num_display_entries: 10 , num_edge_entries: 2, prev_text: 'prev' , next_text: 'next'};
			  $("#Pagination").pagination(rowsArr.length, optInit);			  
			});  	
	};
	
	//Load pending expenses
	var loadApprovalPendingExpenses = function (){
		$.getJSON(utils.url('/expenses/approvals'), function(jsonResponse) {
			   var expenseList = "";
			   $.each(jsonResponse, function () {
			      var self = this;
			      expenseList += '<tr class="tr-odd"><td class="expensedate"><h5 class="label-4">' + new Date(parseInt(self.expenseDate)) + '</h5></td><td class="expenseid"> <h5 class="label-4">' + self.id + '</h5></td><td class="expensedescription"><h5 class="label-4">' + self.description + '</h5></td><td class="expensestate"><select id="action_1" class="test" name="1" style="font-size: 11px;height: 25px;width: 120px;"><option selected="selected" value="Pending">PENDING</option><option value="APPROVED">Approve</option><option value="REJECTED">Reject</option></select></td></tr>"';    
			    });
			  $("table#approvalTable tbody","div.approvalExpense").html(expenseList);
			});  	
	};
	
	
	//Retrieve user expenses.
	//$("a#myExpenseTab").on("click", loadExpenses);
	
	//Retrieve pending expenses
	$("a#approvalTab").on("click", loadApprovalPendingExpenses);
	
	$("img#clip-b-img").on("click", function(){
		$.getJSON(utils.url('/expenses'), function(jsonResponse) {
		});
	});
	
	//On change the status of the expense action_1
	$("table#approvalTable").on("change","select.test", function (){
		var self = $(this);
		console.log($(".expenseid").val());
		//console.log("change state url "+'/html5expense/expenses/'+self.attr('id')+'/changestate/'+$("select option:selected").val());
		$.post(utils.url('/expenses/'+$.trim(self.parents("tr").children("td.expenseid").text()) +'/state/'+$(this).val()),{_method:"PUT"}, function(jsonResponse) {
			$("a[data-tab=approvalsContainer]").click();
			});
		
	});
	
	
	//Edit Expense - Event handler.
	$("table#expenseTable").on("click","a.bg-edit-b", function(evt){
		   evt.preventDefault();
		   //console.log("url is "+'/html5expense/expenses/'+self.attr('id'));
		   var self = $(this);
				$.getJSON(utils.url('/expenses/'+self.attr('id')), function(jsonResponse) {
					$(".textbox-a").val(jsonResponse.description);
					$("#expenseTypeId").val(jsonResponse.expenseType.id);
					$("input#amount").val(jsonResponse.amount);
					$("input#expenseload").val(jsonResponse.id);
					console.log("expenseload value "+$("input#expenseload").val());
					$("div#newExpenseContainer").addClass("active").siblings("div").removeClass("active");
					$("button.edit-expense-btn").show().siblings("button.add-expense-btn").hide();
				});
    });
	
	//Delete Expense - Event handler.
	$("table#expenseTable").on("click","a.bg-cross-b", function(evt){
		   evt.preventDefault();
		   var self = $(this);
		   //console.log("delete 2 url"+'/html5expense/expenses/'+self.attr('id')+'/');
		    $.post(utils.url('/expenses/'+self.attr('id')+'/'), {_method:'DELETE'}, function() {
				$("a[data-tab=myExpenseContainer]").click();
			});	   
    });
	loadExpenses();  //on load to show user my expenses 
	
	var pageselectCallback =  function (page_index, jq, opt){
        // Get number of elements per pagionation page from form
		var items_per_page = opt.items_per_page;
		var max_elem = Math.min((page_index+1) * items_per_page, rowsArr.length);
		var newcontent = '';
		
		// Iterate through a selection of the content and build an HTML string
		for(var i=page_index*items_per_page;i<max_elem;i++)
		{
			newcontent += '<tr class="tr-odd"><td>'+ rowsArr[i].id +'</td> <td> <h5 class="label-4">' + new Date(parseInt(rowsArr[i].expenseDate)) +
		      '</h5></td><td> <h5 class="label-4">' + rowsArr[i].expenseType.name + '</h5></td><td><h5 class="label-4">' +
		      rowsArr[i].description + '</h5></td><td><h5 class="label-4"><i class="alt-a label-5 blue"><img class="right-a-img" src="'+utils.url('/resources/images/question-a.png')+ '">' + 
		      rowsArr[i].state +'</i><i class="alt-b label-6"><img class="question-a-img" src="' + utils.url('/resources/images/question-a.png') + '">' + 
		      rowsArr[i].state + '<a href="#" id=' + rowsArr[i].id + ' class="bg-edit-b"> <img class="clip-b-img" src="'+ utils.url('/resources/images/edit-a.png')+'"> </a>'+
		      '<a href="#" id=' + rowsArr[i].id + ' class="bg-cross-b"><img class="cross-b-img" src="'+ utils.url('/resources/images/cross-b.png')+'"> </a></i></h5></td></tr>"';
		}
		
		// Replace old content with new content
		$('tbody' , 'table#expenseTable').html(newcontent);
		
		// Prevent click eventpropagation
		return false;
		};
	
});

