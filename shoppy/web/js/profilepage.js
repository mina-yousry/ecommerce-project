/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var editFlag;

function setEditFlag(value) {
    editFlag = value;
}

function changeEditability() {
    
    if (editFlag == true) {        
        document.getElementById("firstNameF").readOnly = false;
        document.getElementById("lastNameF").readOnly = false;
        document.getElementById("phoneF").readOnly = false;
        document.getElementById("dobF").readOnly = false;
        document.getElementById("jobF").readOnly = false;
        document.getElementById("mailF").readOnly = false;
        document.getElementById("passwordF").readOnly = false;
        document.getElementById("addressF").readOnly = false;
        
        document.getElementById("fileF").style.visibility = "visible";
        document.getElementById("picL").style.visibility = "visible";
        document.getElementById("saveButton").style.visibility = "visible";
        document.getElementById("editButton").style.visibility = "hidden";
        
    } else {
        document.getElementById("firstNameF").readOnly = true;
        document.getElementById("lastNameF").readOnly = true;
        document.getElementById("phoneF").readOnly = true;
        document.getElementById("dobF").readOnly = true;
        document.getElementById("jobF").readOnly = true;
        document.getElementById("mailF").readOnly = true;
        document.getElementById("passwordF").readOnly = true;
        document.getElementById("addressF").readOnly = true;
        
        document.getElementById("fileF").style.visibility = "hidden";        
        document.getElementById("picL").style.visibility = "hidden";
        document.getElementById("saveButton").style.visibility = "hidden";
        document.getElementById("editButton").style.visibility = "visible";
        
    }
}



