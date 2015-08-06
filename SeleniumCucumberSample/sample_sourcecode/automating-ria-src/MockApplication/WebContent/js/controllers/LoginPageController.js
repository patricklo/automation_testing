define( [ 
	"dojo/_base/declare",
	"dijit/registry",
	"customDijits/LoginForm",
	"dojo/_base/lang",
	"dojo/aspect",
	"dojo/_base/connect",
	"dojo/domReady!"
], function(
	declare,
	registry,
	LoginDijit,
	lang,
	aspect,
	connect
) {
	return declare([], {
		_loginForm : null,
		
		constructor : function() {
			console.log("Constructing Login Page Controller");
			this._loginForm = registry.byId("LoginFormDijit");
			
			aspect.after(this._loginForm, 'submitLogin', dojo.hitch(this, this._handleLoginResponse));
		},
		
		destroy : function(){
			console.log("Destroying Login Controller");
			this._loginForm.destroy();
		},
		
		_handleLoginResponse : function(loginData){
			console.log('sudo submit login with data' + loginData);
		    //imitate server...wait a little while
		    window.setTimeout(dojo.hitch(this, function(){
				//publish the login success event
				connect.publish("login/success")
				//destroy this
				this.destroy();
		    }, 1000));
		}
	});
});
