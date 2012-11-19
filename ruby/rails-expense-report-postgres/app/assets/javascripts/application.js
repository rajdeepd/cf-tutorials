//= require jquery
//= require jquery_ujs
//= require jquery-ui
//= require_tree .

$(document).ready(function(){
  $('#date-field').datepicker({
    dateFormat: 'dd/mm/yy',
    maxDate: +0
  });

  $('.arrow-down-actions').live('click', function(e) {
  	$('.admin-actions :not($(this).siblings("ul.admin-actions"))').hide();
  	$(this).siblings('ul').toggle();
  });
})