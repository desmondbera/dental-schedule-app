//$(function() {
//$('.datePicker').datepicker('getDate',{'format': 'yyyy.mm.dd', inline: true,
//    sideBySide: true});
//});


$(function() {
	
//	$('.datePicker').datepicker('getDate',{'format': 'yyyy.mm.dd', inline: true,
//	    sideBySide: true});
	
	$('#datetimepicker2').datetimepicker({
		format: 'L',
		inline: true,
		sideBySide: true
	});
	
	$('#datetimepicker3').datetimepicker({
		format: 'LT'
	});
});
