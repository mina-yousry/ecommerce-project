/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

document.bgColor="pink";
var flag=0;
function validate(event)
{
	event.preventDefault();
        var fname=document.getElementById("t1").value;
        var lname=document.getElementById("t2").value;
	var email=document.getElementById("t3").value;
	var telephone=document.getElementById("t4").value;
	var address=document.getElementById("t5").value;
	var birth=document.getElementById("t6").value;
        var job=document.getElementById("t7").value;
        var pass=document.getElementById("t8").value;
	if (fname ==="")
	{
		document.getElementById("s1").innerHTML="*Required Field";
                flag=1;
	}
	else{document.getElementById("s1").innerHTML="";
            flag=0;
	}
	if (lname ==="")
	{
		document.getElementById("s2").innerHTML="*Required Field";
                flag=1;
	}
	else{document.getElementById("s2").innerHTML="";
            flag=0;
	}	
       if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email))
       {
	 document.getElementById("s3").innerHTML="";
         flag=0;
	}
	else if (email==="")
        {
            document.getElementById("s3").innerHTML="*Required Field";
            flag=1;
        }
        else
        {document.getElementById("s3").innerHTML="Wrong email";
         flag=1;
        }
          if((typeof parseInt(telephone) === 'number')&& (6<=telephone.length)&&(telephone.length<=10))
          {
                 document.getElementById("s4").innerHTML=""; 
                 flag=0;
          }
          else if (telephone===""){
          document.getElementById("s4").innerHTML="*Required Field";
           flag=1;
          }
          else
          {
           document.getElementById("s4").innerHTML="Wrong Phone"; 
            flag=1;
          }
          if (address==="")
          {
            document.getElementById("s5").innerHTML="*Required Field";  
            flag=1;
          }
          else
          {
            document.getElementById("s5").innerHTML=""; 
            flag=0;
          }
          if (birth==="")
          {
            document.getElementById("s6").innerHTML="*Required Field";  
            flag=1;
          }
          else
          {
            document.getElementById("s6").innerHTML=""; 
            flag=0;
          }
          if (job==="")
          {
            document.getElementById("s7").innerHTML="*Required Field";  
            flag=1;  
          }
          else
          {
            document.getElementById("s7").innerHTML="";
            flag=0
          }
          if (pass==="")
          {
            document.getElementById("s8").innerHTML="*Required Field";  
            flag=1;    
          }
          else
          {
             document.getElementById("s8").innerHTML="";  
             flag=0;
          }
          if (flag===1)
          {
              return false;
          }
          else
          {
              document.registerform.submit();
          }
              
     }