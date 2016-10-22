function showhide(mes) {
	var c = document.getElementById("myCanvas");
	var ctx = c.getContext("2d");
	ctx.font = "30px Arial";
	ctx.strokeText(mes,10,50);
}