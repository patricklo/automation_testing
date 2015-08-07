define([ 
	"dojo/_base/declare",
	"customDijits/HomePage",
	"dojo/dom-construct",
	"dojo/dom"
], function(
	declare,
	HomePage,
	domConstruct,
	dom
) {
	return declare([], {
		_homePage : null,
		
		constructor : function() {
			console.log("Constructing Home Page Controller");
			this._homePage = new HomePage();
			domConstruct.place(this._homePage.domNode, dom.byId("HomePage"), "first");
		}
		
	});
});
