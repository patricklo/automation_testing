define([ 
	"dijit/_Widget",
	"dijit/_TemplatedMixin", 
	"dijit/_WidgetsInTemplateMixin",
	"dojo/text!customDijits/templates/HomePage.html",
	"dojo/_base/declare"
], function(  
	_Widget,
	_TemplatedMixix,
	_WidgetsInTemplateMixin,
	template,
	declare
) {
	return declare([_Widget, _TemplatedMixix, _WidgetsInTemplateMixin],
	{			
		startup : function(){
			this.inherited(arguments);
		},
		
		templateString : template, 
		
		postCreate : function(){	
			this.inherited(arguments);
		}
	});
});