function getClientTimeZone() {
	var dateVar = new Date();
	var timezone = dateVar.getTimezoneOffset() / 60 * (-1);
	return timezone;
}

function isLoggedIn() {
	return $("#authenticationState").attr("value");
}

function setCookie(c_name, value, exdays) {
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + exdays);
	var c_value = escape(value) + ((exdays == null) ? "" : "; expires=" + exdate.toUTCString());
	document.cookie = c_name + "=" + c_value;
}

function setTempNotificationCookie(){
	var cookieName = "tempNotification";
	var cookieValue = $("#tempNotification").attr("value");
	setCookie(cookieName, cookieValue, 10);
}