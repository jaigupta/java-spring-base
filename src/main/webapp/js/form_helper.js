$(document).ready(function() {
    form_eles=$('div .form-group');
    form_eles.each(function(index) {
    	help_children = $(this).children('div .help-inline');
    	if (help_children.length > 0) {
    		$(this).addClass('has-error');
    	}
    });
});