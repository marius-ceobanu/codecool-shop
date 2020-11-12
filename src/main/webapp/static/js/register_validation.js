function validateForm() {

    let a = document.forms["my-form"]["full-name"].value;
    let b = document.forms["my-form"]["email-address"].value;

    if (a==null || a==="")
    {
        console.log("no name");
        alert("Please Enter Your Full Name");
        return false;
    }else if (b==null || b==="")
    {
        console.log("no mail");
        alert("Please Enter Your Email Address");
        return false;
    } else {
        console.log("all good");
    }

}