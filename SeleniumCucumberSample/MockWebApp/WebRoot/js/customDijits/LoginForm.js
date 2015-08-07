define([ 
	"dijit/form/Form",
	"dojo/query",
	"dojo/_base/declare",
	"dojo/_base/lang",
	"dijit/form/ValidationTextBox", 
	"dijit/form/Button",
	"dojo/dom-construct",
	"dojo/topic",
	"dojo/dom-form"
], function(  
	Form,  
	query,	
	declare,
	lang,
	ValidationTextBox,
	Button,
	domConstruct,
	topic,
	domForm
) {
	return declare([Form],
	{	
		_usernameInput : null,
		_passwordInput : null,
		_loginSubmitButton : null,
		
		startup : function(){
			this.inherited(arguments);
			this._usernameInput.startup();
			this._passwordInput.startup();
			this._loginSubmitButton.startup();
		},
		
		postCreate : function(){	
			this.inherited(arguments);
			
			this._usernameInput = new ValidationTextBox({
	            value: "",
	            label: "Username",
	            name: "username",
	            required: "true",
	            placeHolder: "Enter your username"
	        });
			domConstruct.place(this._usernameInput.domNode, this.containerNode);
			
			this._passwordInput = new ValidationTextBox({
	            value: "",
	            label: "Password",
	            name: "password",
	            required: "true",
	            placeHolder: "Enter your password"
	        });
			domConstruct.place(this._passwordInput.domNode, this.containerNode);
			
			this._loginSubmitButton = new Button({
	            label: "Login",
	            name: "SubmitLogin",
	            onClick: lang.hitch(this, function(){
	            	console.log("Submitting Login form to the login controller");
					if(this.validate()){
						this.submitLogin(this.getValues());
					}
	            })
	        });
			domConstruct.place(this._loginSubmitButton.domNode, this.containerNode);
		},
		
		submitLogin : function(data){
			return data;
		}
		
	});
});