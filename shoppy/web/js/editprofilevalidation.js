/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


document.bgColor = "pink";
var flag;
function validate(event)
{
    event.preventDefault();
    var fname = document.getElementById("firstNameF").value;
    var lname = document.getElementById("lastNameF").value;
    var email = document.getElementById("mailF").value;
    var telephone = document.getElementById("phoneF").value;
    var address = document.getElementById("addressF").value;
    var birth = document.getElementById("dobF").value;
    var job = document.getElementById("jobF").value;
    var pass = document.getElementById("passwordF").value;
    
    flag = 0;
    
    if (fname === "")
    {
        document.getElementById("s1").innerHTML = "*Required Field";
        flag = 1;
    } else {
        document.getElementById("s1").innerHTML = "";
        
    }
    if (lname === "")
    {
        document.getElementById("s2").innerHTML = "*Required Field";
        flag = 1;
    } else {
        document.getElementById("s2").innerHTML = "";
        
    }
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email))
    {
        document.getElementById("s3").innerHTML = "";
        
    } else if (email === "")
    {
        document.getElementById("s3").innerHTML = "*Required Field";
        flag = 1;
    } else
    {
        document.getElementById("s3").innerHTML = "Wrong email";
        flag = 1;
    }
    if ((typeof parseInt(telephone) === 'number') && (6 <= telephone.length) && (telephone.length <= 11))
    {
        document.getElementById("s4").innerHTML = "";
        
    } else if (telephone === "") {
        document.getElementById("s4").innerHTML = "*Required Field";
        flag = 1;
    } else
    {
        document.getElementById("s4").innerHTML = "Wrong Phone";
        flag = 1;
    }
    if (address === "")
    {
        document.getElementById("s5").innerHTML = "*Required Field";
        flag = 1;
    } else
    {
        document.getElementById("s5").innerHTML = "";
        
    }
    if (birth === "")
    {
        document.getElementById("s6").innerHTML = "*Required Field";
        flag = 1;
    } else
    {
        document.getElementById("s6").innerHTML = "";
        
    }
    if (job === "")
    {
        document.getElementById("s7").innerHTML = "*Required Field";
        flag = 1;
    } else
    {
        document.getElementById("s7").innerHTML = "";
        
    }
    if (pass === "")
    {
        document.getElementById("s8").innerHTML = "*Required Field";
        flag = 1;
    } else
    {
        document.getElementById("s8").innerHTML = "";
        
    }
    if (flag === 1)
    {
        return false;
    } else
    {
        document.profileEditForm.submit();
    }

}
