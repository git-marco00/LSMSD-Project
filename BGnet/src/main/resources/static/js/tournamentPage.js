$(document).ready(function() {
    $("#addTournamentDiv").hide()
    $("#showAddForm").bind('click', function(event){
        $("#addTournamentDiv").show()
    })
    $("#createTournament").bind('click', function(event){
        createTournament()
    })

    $.ajax({
        url: "/api/loadTournamentPage",
        method: "get",
        success: function (data) {
            let tournaments = $.parseJSON(data);
            if(data!=null){
                $('#gameName').text(tournaments[0].tournamentGame)
                let tournament =0
                for(tournament of tournaments) {
                    let html = '<div id="tournament-' + tournament.id + '" class="w3-container w3-card w3-white w3-round w3-margin"><br>'
                    html += '<span class="w3-right w3-opacity"><i class="fa fa-calendar"></i> ' + tournament.date + '</span>'
                    html += '<span class="w3-right w3-opacity w3-margin-right"><i class="fa fa-group"></i> ' + tournament.numPartecipants + '/' + tournament.maxPlayers + '</span>'
                    html += '<h4 class="w3-margin">' + tournament.creator + '</h4>'
                    html += '<hr class="w3-clear">'
                    html += '<div class = "w3-container w3-col m7 w3-margin-bottom w3-margin-left">'
                    html += '<p class="w3-margin"> Duration: ' + tournament.duration + '</p>'
                    html += '<p class="w3-margin"> Modalities: ' + tournament.modalities + '</p>'
                    html += '<p class="w3-margin"> Players Per Match: ' + tournament.playersPerMatch + '</p>'
                    html += '<span id="partecipantsSpan' + tournament.id + '"></span>'
                    html += '</div>'
                    html += '<div class = "w3-container w3-col m4 w3-center">'
                    if (tournament.isClosed == true) {
                        if (tournament.isParticipating == false) {
                            console.log("tournament.isParticipating == false")
                            html += '<button id=' + tournament.id + ' type="button" disabled class="participateButton w3-button w3-theme-d1 w3-margin-bottom"><i class="fa fa-flash"></i> Participate</button>'
                        }
                        else {
                            console.log("tournament.isParticipating == true")
                            html += '<button id=' + tournament.id + ' type="button" disabled class="quitButton w3-button w3-theme-d1 w3-margin-bottom"><i class="fa fa-flash"></i> Quit</button>'
                        }
                    } else {
                        if (tournament.isParticipating == false) {
                            if(tournament.numPartecipants == tournament.maxPlayers) {
                                console.log("tournament.numPartecipants == tournament.maxPlayers")
                                html += '<button id=' + tournament.id + ' type="button" disabled class="participateButton w3-button w3-theme-d1 w3-margin-bottom"><i class="fa fa-flash"></i> Participate</button>'
                            }
                            else
                                html += '<button id=' + tournament.id + ' type="button" class="participateButton w3-button w3-theme-d1 w3-margin-bottom"><i class="fa fa-flash"></i> Participate</button>'
                        }
                        else
                            html += '<button id=' + tournament.id + ' type="button" class="quitButton w3-button w3-theme-d1 w3-margin-bottom"><i class="fa fa-flash"></i> Quit</button>'
                    }
                    html += '<button id='+ tournament.id + ' type="button" class="viewPartecipantsButton w3-button w3-theme-d2 w3-margin-bottom"><i class="fa fa-group"></i>  View partecipants</button>'
                    if(tournament.isCreator==true){
                        html +='<button id='+ tournament.id + ' type="button" class="dismissTournamentButton w3-button w3-theme-d2 w3-margin-bottom">  Dismiss Tournament</button>'
                    }
                    html += '</div>'
                    html += '</div>'
                    $('#tournamentContainer').append(html)
                }
            }
            $('button.participateButton').bind('click', function(event){
                $.ajax({
                    url: "/api/participateToTournament",
                    method: "get",
                    data: {tid: event.target.id},
                    success:function(data){
                        window.location.reload()
                    }
                })
            })

            $('button.quitButton').bind('click', function(event){
                $.ajax({
                    url: "/api/quitTournament",
                    method: "get",
                    data: {tid: event.target.id},
                    success:function(data){
                        window.location.reload()
                    }
                })
            })

            $('button.viewPartecipantsButton').bind('click', function(event){
                $.ajax({
                    url: "/api/loadTournamentParticipants",
                    method: "get",
                    data: {tid: event.target.id},
                    success:function(data){
                        partecipants=JSON.parse(data)
                        let partecipantsString = " "
                        for(partecipant of partecipants){
                            partecipantsString += partecipant + ','
                        }
                        partecipantsString = partecipantsString.slice(0, -1);
                        let idStr="#partecipantsSpan" + event.target.id
                        $(idStr).text(partecipantsString)
                    }
                })
            })

            $('button.dismissTournamentButton').bind('click', function(event){
                $.ajax({
                    url: "/api/closeTournament",
                    method: "get",
                    data: {tid: event.target.id},
                    success:function(data){
                        window.location.reload()
                    }
                })
            })

        }
    })
})

function createTournament(){
    let date, duration, maxPlayers, modalities, playersPerMatch;

    date = $("#dateTournament").text().trim()
    duration = $("#durationTournament").text().trim()
    maxPlayers = $("#maxPTournament").text().trim()
    modalities = $("#modalitiesTournament").text().trim()
    playersPerMatch = $("#ppmTournament").text().trim()
    $.ajax({
        url: "/api/createTournament",
        method:"get",
        data: {date: date, duration:duration, maxPlayers: maxPlayers, modalities:modalities, playersPerMatch:playersPerMatch},
        success: function(data){
            if(data) {
                window.location.reload();
            }
        }
    })
}