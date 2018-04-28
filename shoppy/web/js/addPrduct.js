/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function validate(event)
{
    event.preventDefault();
    var name = document.getElementById("tProductName").value;
    var description = document.getElementById("tProductDescription").value;
    var price = document.getElementById("tPrice").value;
    var quantity = document.getElementById("tQuantity").value;
    var vendor = document.getElementById("tVendor").value;
    var category = document.getElementById("tCategory").value;

    if (name === "")
    {
        document.getElementById("productNameAlert").innerHTML = "*Required Field";
        flag = 1;
    } else {
        document.getElementById("productNameAlert").innerHTML = "";
        flag = 0;
    }
    
    if (description === "")
    {
        document.getElementById("productDescriptionAlert").innerHTML = "*Required Field";
        flag = 1;
    } else {
        document.getElementById("productDescriptionAlert").innerHTML = "";
        flag = 0;
    }
    
    if (price === "")
    {
        document.getElementById("priceAlert").innerHTML = "*Required Field";
        flag = 1;
    } else {
        document.getElementById("priceAlert").innerHTML = "";
        flag = 0;
    }
    
    if (quantity === "")
    {
        document.getElementById("quantityAlert").innerHTML = "*Required Field";
        flag = 1;
    } else {
        document.getElementById("quantityAlert").innerHTML = "";
        flag = 0;
    }
    
    if (vendor === "")
    {
        document.getElementById("VendorAlert").innerHTML = "*Required Field";
        flag = 1;
    } else {
        document.getElementById("VendorAlert").innerHTML = "";
        flag = 0;
    }
    
    if (category === "")
    {
        document.getElementById("categoryAlert").innerHTML = "*Required Field";
        flag = 1;
    } else {
        document.getElementById("categoryAlert").innerHTML = "";
        flag = 0;
    }
    
    if (flag === 1)
    {
        return false;
    } else
    {
        document.addProductForm.submit();
    }

}

