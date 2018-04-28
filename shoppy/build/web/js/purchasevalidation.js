/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function checkBalance(event, userBalance, totalPrice) {
    
    event.preventDefault();
    
    if (userBalance >= totalPrice) {
        window.alert("Nice doing bussiness with you.");
        document.purchaseForm.submit();
    } else {        
        window.alert("You don't have enough balance.");
        return false;
    }
}

