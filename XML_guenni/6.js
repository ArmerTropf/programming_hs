function construction01() 
{

 element = document.getElementsByTagName("LI");
 
 var node = document.createElement("LI");
 var textnode = document.createTextNode("Water");

  var minFirst = 0;
  var maxFirst = element.length;
 

  var minSecond = 0;
  var maxSecond = element.length;
  var Second = Math.round((Math.random() * (maxSecond - minSecond))) + minSecond; 
 

  
  
  
  
  for ( i = 0 ; i < 1 ; i++)
  { 
    var First = Math.round((Math.random() * (maxFirst - minFirst))) + minFirst; 
    var Second = Math.round((Math.random() * (maxSecond - minSecond))) + minSecond;
    
    console.log(First + " " + Second);
    console.log(element[First].innerHTML + " " + element[Second].innerHTML);
    //var tmp = document.getElementById("4");
    var tmp = element[First];
    
    if (tmp.nextElementSibling == null && tmp.previuos.ElementSibling == null)
    {
       console.log();
    } 
    var sib2 = tmp.nextElementSibling;
      
 
  
  
    //var sibling = document.getElementById("4.4").nextElementSibling;
    var sibling = element[Second].nextElementSibling;
  
  
  
    sibling.parentNode.insertBefore(tmp, sibling.previousSibling);
   // sib2.parentNode.insertBefore(document.getElementById("4.4"),sib2.previousSibling);
     sib2.parentNode.insertBefore(element[Second],sib2.previousSibling);
  
 
  
  }
  
  
 
}