require([
    "dojo/parser",
    "controllers/GlobalController",
    "dojo/domReady!"   
 ], function(
	parser,
	GlobalController
){
	var init = function(){
		console.log("Initiating Mock Application");
		GlobalController().init();
	};
	parser.parse().then(init);
});