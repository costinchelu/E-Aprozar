function validate() {
    if (document.f.email.value == "" && document.f.password.value == "") {
        alert("Introduceti adresa email si parola");
        document.f.email.focus();
        return false;
    }
    if (document.f.email.value == "") {
        alert("Adresa email este camp obligatoriu");
        document.f.eamil.focus();
        return false;
    }
    if (document.f.password.value == "") {
	    alert("Parola este camp obligatoriu");
	    document.f.password.focus();
        return false;
    }
} 