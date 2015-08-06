define([
	"dojo/_base/connect",
	"controllers/LoginPageController",
	"controllers/HomePageController" 
],function(
	connect,
	LoginPageController,
	HomePageController
){
	return function(protectedMap) {
		protectedMap = protectedMap ? protecteMap : {};
		
		var privateObject, publicObject = null;
		
		privateObject = {
			LoginPageController : null,
			HomePageController: null,
			LoginHandle: null
		};
		
		protectedMap.callback = function(){
			connect.unsubscribe(privateObject.LoginHandle);
			privateObject.LoginPageController.destroy();
			privateObject.HomePageController = new HomePageController();
		};
			
		
		publicObject = {
			init : function() {
				privateObject.LoginPageController = new LoginPageController();
				privateObject.LoginHandle = connect.subscribe("login/success", protectedMap.callback);
			}
		};
		
		return publicObject;
	};
	
});