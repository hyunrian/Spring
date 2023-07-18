/**
 * myscript.js
 */
 
function toDateString(millisecond) {
	if (!millisecond) {
		return "";
	}
	
	let d = new Date(millisecond);
	// 2023-07-18 14:50:50
	let year = d.getFullYear();
	let month = d.getMonth() + 1;
	let day = d.getDate();
	let hour = d.getHours();
	let minute = d.getMinutes();
	let second = d.getSeconds();
	
	let dateFormat = year + "-" + make2digits(month) + "-" + make2digits(day)
					 + " " + make2digits(hour) + ":" + make2digits(minute) 
					 + ":" + make2digits(second);
					 
	// console.log(dateFormat);
	
	return dateFormat;
}

function make2digits(num) {
	if (num < 10) {
		num = "0" + num;
	}
	return num;
}