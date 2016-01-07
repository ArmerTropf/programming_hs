/*function shuffle() {
  var types = ["h2", "h3", "li"];
  for(type of types) {
    shuffleType(type);
  }
}*/

var all = document.body.getElementsByTagName("*");
var types = [];

for(item of all) {
  var found = false;
  for(t of types) {
    if(t.type == item.tagName) {
      ++t.counter;
      found = true;
    }
  }
  if(!found) {
    types.push({type: item.tagName, counter: 1});
  }
}

var div = document.createElement("div");
div.setAttribute("style", "background: #CCC;");
for(t of types) {
  if(t.type != "SCRIPT") {
    var counterP = document.createElement("div");
    counterP.innerHTML = t.type+": "+t.counter;
    var button = document.createElement("button");
    button.innerHTML = "Shuffle "+t.type;
    button.dataset.type = t.type;
    button.onclick = function() {
      shuffleType(this.dataset.type);
    };
    div.appendChild(counterP);
    div.appendChild(button);
  }
}

document.body.appendChild(div);


function shuffleType(type) 
{
  
  // get all elements of the same type
  var elem = document.getElementsByTagName(type);

  // ensure there are a minimum of 2 elements  
  if(elem.length < 2) 
  {
    return false;
  }

  // get length of our list of elements
  var max = elem.length - 1;     
  
  // roll the dices: get two random indexes 
  var first = Math.round(Math.random() * max);
  var second = Math.round(Math.random() * max);

  //ensure there is a minimum distance of 1 between the elements 
  if(Math.abs(first-second) < 2) 
  {
    if(second < max) 
    {
      ++second;
    } 
    else 
    {
      second = 0;
    }
  }

  var firstElem = elem[first];
  var secondElem = elem[second];

  //ensure the elements dont stand in relation to another
  var swapAllowed =  nodeIsParent(firstElem, secondElem) && nodeIsParent(secondElem, firstElem);

  if(swapAllowed) 
  {
    // get Sibling under firstElem
    var firstNeighbor = firstElem.nextSibling;
    var secondNeighbor = secondElem.nextSibling;
    
     // ensure the first element isnt alone
    if(firstNeighbor != null) 
    {
     swap(firstElem, secondElem, firstNeighbor);
    } 
    else if(firstNeighbor == null && secondNeighbor != null) 
    {
     swap(secondElem, firstElem, secondNeighbor);
    } 
    else 
    {
      console.log("both have no neighbors");
      
      firstNeighbor = document.createElement("span");
      firstElem.parentNode.appendChild(firstNeighbor);
      
      swap(firstElem, secondElem, firstNeighbor);
    }
  }

}

function swap(elem1, elem2, neighbor) {
  elem2.parentNode.insertBefore(elem1, elem2);
  neighbor.parentNode.insertBefore(elem2, neighbor);
}

function nodeIsParent(elem1, elem2) 
{
  var parent = elem1.parentNode;
  
  while(parent != null) 
  {
    if(parent == elem2) 
    {
      return false;
    }
    parent = parent.parentNode;
  }
  
  return true;
}