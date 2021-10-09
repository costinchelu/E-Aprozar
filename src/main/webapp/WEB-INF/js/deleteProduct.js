let del = document.getElementsByClassName("delProduct");
let confirmIt = function(event) {
	if(!confirm("Esti sigur?")) event.preventDefault();
};
for(let i = 0; i < del.length; i++){
	del[i].addEventListener("click", confirmIt, false);
}
