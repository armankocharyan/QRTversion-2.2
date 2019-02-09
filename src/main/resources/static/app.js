var isHost = false;
var ws;
var maxPlayer = 0;
var cardArr = []; //empty this array when necessary

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {

    if (maxPlayer < 4) {
        ws = new WebSocket('ws://localhost:8050/name');
        ws.onmessage = function (data) {
            console.log(JSON.parse(data.data));
            // var name = prompt("What is your name");

            var parsed = JSON.parse(data.data);
            if (parsed.URL == "isHost") {
                isHost = true;
            }
            else if (parsed.URL == "gameStarted") {
                console.log("game started method");
                gameStarted(parsed);
            }//starting tournaments
            else if (parsed.URL == "tournament"){
                displayStoryCard(parsed.cardType, parsed.shield);
            }
            else if(parsed.URL == "tournamentWinner"){
                tournamentWinner(parsed);
            }
            else if(parsed.URL == "updatePlayer"){
                updatePlayerStats(parsed);
            }//starting quests
            else if (parsed.URL == "quest"){
                displayQuestCard(parsed.cardTypeQuest, parsed.numStages);
            }
            else if (parsed.URL = "decision"){
                console.log("decision Handler...");
                sponsorQuest();
            }
            else if (parsed.URL == "printLabel"){
                console.log("printLabel Handler...");
                notifyPlayer(parsed.sponsorName);
            }
            else {
                showGreeting(data.data);
            }
        }

        maxPlayer ++;
        console.log("there are currently " + maxPlayer + " players in this game");
        setConnected(true);
    }
}

/*
STARTING QUESTS
*/

function notifyPlayer(message){

    console.log(message + " has sponsored");
    $("body").append("<div id= labelSpace></div> ");
    $("#labelSpace").append("<p> " + message + " has sponsored</p>");

}

function sponsorQuest() {
    console.log("starting sponsorQuest")
    $("body").append("<div id= buttonSpaceQuest></div> ");
    $("#buttonSpaceQuest").append("<Button id= yesQuest onclick='someQuestYes()'  >YES</button>");
    $("#buttonSpaceQuest").append("<Button id= noQuest onclick='questNo()'  >NO</button>");

}

function someQuestYes() {
    console.log("SomeQuestYes was pressed...");
    var stageName = "stage";
    $("body").append("<div id= buttonSpaceStage></div> ");
    for (var i= 0; i<numStages; i++) {
        $("#buttonSpaceStage").append("<Button id= '" +  stageName + i + "' onclick='createStageArray(numStages)'  class='btn btn-default' type='submit'>  " + stageName + i  + " </button>");
    }
    $("#buttonSpaceQuest").remove();
    var response = JSON.stringify({'URL': 'yesSponsor'})
    ws.send(response);
}

function displayQuestCard(message, message2){
    var imageName = message;
    numStages = parseInt(message2);
    console.log(imageName);
    console.log("number of stages in this quest: " + message2);
    $("body").append("<div id= BoarCardSpace></div> ");
    $("#BoarCardSpace").append("<img id= 'boar' class='CardStyle' src='/CardPics/StoryCards/Quests/"+imageName+".jpg'>");

}


/*

ENDING QUESTS
 */

function displayStoryCard(message, message2){
    console.log("Card: " +  message + " Shields: " + message2);
    var storyCard = message;
    var Shield = parseInt(message2);
    $("body").append("<div id='StoryCard'></div> ");
    $("#StoryCard").append("<img id= 'storyCard' class='StoryStyle' src='/CardPics/StoryCards/Tournaments/"+storyCard+".png'>");
    $("body").append("<div class='alert alert-primary' role='alert'  id='StoryButtonSpace' >");
    $("#StoryButtonSpace").append("<p class='Ask'>Would you like to participate in the tournament? </p>")
    $("#StoryButtonSpace").append("<Button id= yes onclick='storyYes()'  class='btn btn-default' type='submit'>YES</button>");
    $("#StoryButtonSpace").append("<Button id= no onclick='storyNo()'  class='btn btn-default' type='submit'>NO</button>");
    $("#StoryButtonSpace").append("<Button id= questButton onclick='startQuest()'  class='btn btn-default' type='submit'>startQuest</button>");
    $("#StoryButtonSpace").append("</div>");
}

function startQuest(){
    console.log('starting function Quest\n');
    var response = JSON.stringify({'URL' : 'startFunctionQuest'});
    ws.send(response);
}

function storyYes(){
    var response = JSON.stringify({'URL': 'Yes'})
    ws.send(response);
    $("#StoryButtonSpace").remove();
}

function storyNo(){
    var response = JSON.stringify({'URL': 'No'})
    ws.send(response);
    $("#StoryButtonSpace").remove();
}

function updatePlayerStats(message){
    $("#shieldsPlayer1").text("Your number of shields are :"+ message.opponentOneShield);
    $("#shieldsPlayer2").text("Your number of shields are :"+ message.opponentTwoShield);
    $("#shieldsPlayer3").text("Your number of shields are :"+ message.opponentThreeShield);

    $("#rankPlayer1").text("Your number of Rank are :"+ message.opponentOneRank);
    $("#rankPlayer2").text("Your number of Rank are :"+ message.opponentTwoRank);
    $("#rankPlayer3").text("Your number of Rank are :"+ message.opponentThreeRank);

    $("#name-info1").text("Your number of Name are :"+ message.opponentOneName);
    $("#name-info2").text("Your number of Name are :"+ message.opponentTwoName);
    $("#name-info3").text("Your number of Name are :"+ message.opponentThreeName);

    $("#BPPlayer1").text("Your number of BP are :"+ message.opponentOneBP);
    $("#BPPlayer2").text("Your number of BP are :"+ message.opponentTwoBP);
    $("#BPPlayer3").text("Your number of BP are :"+ message.opponentThreeBP);
}


function StartGame() {

    if ( isHost == true){
        var response = JSON.stringify({'URL': 'GameStarted'});
        ws.send(response);
    }
    else{
        console.log("you are not the host and cannot start game");
    }
}
/*
function gameStarted(message){
    $("body").empty();
    console.log("hello");
    $("body").append("<p>Your Game has started</p>");
    $("body").append("<p>Your name is: " + message.name+"</p>");
    $("body").append("<p id='shieldsPlayer'>Your number of shields are :"+ message.shields+"</p>");
    $("body").append("<p>Your number of battle points are:"+ message.battlePoints+"</p>");
    displayCards(message.cards);
}*/

function gameStarted(message){
    $("body").empty();
    console.log("hello");
    $("body").append("<p>Your Game has started</p>");
    $("body").append("<p id='name-info' class='name-info1'>Your name is: " + message.name + "</p>")
    $("body").append("<p id='rankPlayer'class='rank-info1'>Your rank is: " + message.rank + "</p>")
    $("body").append("<p id='shieldsPlayer' class='shield-info1'>Your number of shields are :" + message.shields + "</p>")
    $("body").append("<p id='BPPlayer' class='bp-info1'>Your number of battle points are:" + message.battlePoints + "</p>")

    $("body").append("<p id='name-info1' class='name-info2'>Your name is: " + message.name + "</p>")
    $("body").append("<p id='rankPlayer1'class='rank-info2'>Your rank is: " + message.rank + "</p>")
    $("body").append("<p id='shieldsPlayer1' class='shield-info2'>Your number of shields are :" + message.shields + "</p>")
    $("body").append("<p id='BPPlayer1'class='bp-info2'>Your number of battle points are:" + message.battlePoints + "</p>")

    $("body").append("<p id='name-info2' class='name-info3'>Your name is: " + message.name + "</p>")
    $("body").append("<p id='rankPlayer2' class='rank-info3'>Your rank is: " + message.rank + "</p>")
    $("body").append("<p id='shieldsPlayer2' class='shield-info3'>Your number of shields are :" + message.shields + "</p>")
    $("body").append("<p id='BPPlayer2' class='bp-info3'>Your number of battle points are:" + message.battlePoints + "</p>")

    $("body").append("<p id='name-info3' class='name-info4'>Your name is: " + message.name + "</p>")
    $("body").append("<p id='rankPlayer3'class='rank-info4'>Your rank is: " + message.rank + "</p>")
    $("body").append("<p id='shieldsPlayer3' class='shield-info4'>Your number of shields are :" + message.shields + "</p>")
    $("body").append("<p id='BPPlayer3'class='bp-info4'>Your number of battle points are:" + message.battlePoints + "</p>")
    displayCards(message.cards);

}


function displayCards(message) {
    console.log(message);
    var str = message;
    var cards = message.split(",");
    cards.splice(-1,1);
    console.log(cards.length);
    for(var i in cards){
        console.log(cards[i]);
    }
    $("body").append("<div id= 'PlayerCards'></div> ");
    for (var i= 0; i<cards.length; i++){
        $("#PlayerCards").append("<img id= '" + i + "' onclick='getIndex(id)' ondblclick='removeIndex(id)'  class='CardStyle' src='/CardPics/AdventureCards/"+cards[i]+".jpg'>");
    }
    //var r = confirm("Would you like to participate in tournament?");
    $("body").append("<div id='ButtonSpace'></div> ");
        $("#ButtonSpace").append("<Button id= 'actionButton' onclick='action()'  class='btn btn-default' type='submit'>Done</button>");
        $(function () {
            $("img").click(function () {
                $(this).css('border', "solid 2px LawnGreen");
                $("img").dblclick(function () {
                    $(this).css('border', "solid 0.1px transparent");
                });
            });
        });
    }
    

function action(){
    console.log("you have sent +" + cardArr + " to the backend");
    var data = JSON.stringify({'URL' : 'selectedCards', 'arr' : JSON.stringify(cardArr)});
    ws.send(data);
    cardArr.length = 0;
}

function getIndex(id){
    if(cardArr.indexOf(id) == -1) {
        cardArr.push(id);
        console.log("you have added card at index " + id);
    }
    else{
        console.log("card already exists\n");
    }

}

function removeIndex(id){
    var arrIndex = cardArr.indexOf(id);
    if(cardArr.indexOf(id) != -1) {
        cardArr.splice(arrIndex, 1);
        console.log("you have removed card at index " + id);
    }
    else{
        console.log("card is not in the array\n");
    }

}

function tournamentWinner(parsed){
    $("#shieldsPlayer").text("Your number of shields are :"+parsed.shields);
    alert(parsed.body);
}

function disconnect() {
    if (ws != null) {
        ws.close();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    var data = JSON.stringify({'URL': 'intro', 'name': $("#name").val()});
    ws.send(data);
}

function showGreeting(message) {
    $("#greetings").append("<tr><td> " + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});