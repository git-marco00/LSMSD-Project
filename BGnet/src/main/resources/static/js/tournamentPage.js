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
            data = JSON.parse(data)
            console.log(data)
            if(data!=null){
                $('#gameName').text(data[0].tournamentGame)
                let tournament =0
                for(tournament in data) {
                    let html = '<div id="tournament-'+ tournament.id +'" class="w3-container w3-card w3-white w3-round w3-margin"><br>'
                    html += '<span class="w3-right w3-opacity"><i class="fa fa-calendar"></i> ' + tournament.date + '</span>'
                    html += '<span class="w3-right w3-opacity w3-margin-right"><i class="fa fa-group"></i> '+ tournament.numPartecipants + '/' + tournament.maxPlayers + '</span>'
                    html += '<h4 class="w3-margin">' + tournament.creator + '</h4>'
                    html += '<hr class="w3-clear">'
                    html += '<div class = "w3-container w3-col m7 w3-margin-bottom w3-margin-left">'
                    html += '<p class="w3-margin"> Duration: '+tournament.duration+'</p>'
                    html += '<p class="w3-margin"> Modalities: '+tournament.modalities+'</p>'
                    html += '<p class="w3-margin"> Players Per Match: '+tournament.playersPerMatch+'</p>'
                    html += '<span class="partecipantsSpan'+data.id+'></span>'
                    html += '</div>'
                    html += '<div class = "w3-container w3-col m4 w3-center">'
                    if(data.isParticipating==false){
                        html += '<button id='+ data.id + 'type="button" class="participateButton w3-button w3-theme-d1 w3-margin-bottom"><i class="fa fa-flash"></i> Participate</button>'
                    }
                    else html += '<button id='+ data.id + ' type="button" class="quitButton w3-button w3-theme-d1 w3-margin-bottom"><i class="fa fa-flash"></i> Quit</button>'
                    html += '<button id='+ data.id + ' type="button" class="viewPartecipantsButton w3-button w3-theme-d2 w3-margin-bottom"><i class="fa fa-group"></i>  View partecipants</button>'
                    if(data.isCreator==true){
                        html +='<button id='+ data.id + ' type="button" class="dismissTournamentButton w3-button w3-theme-d2 w3-margin-bottom">  Dismiss Tournament</button>'
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
                    data: {id: event.target.id},
                    success:function(data){
                        window.location.reload()
                    }
                })
            })

            $('button.quitButton').bind('click', function(event){
                $.ajax({
                    url: "/api/quitTournament",
                    method: "get",
                    data: {id: event.target.id},
                    success:function(data){
                        window.location.reload()
                    }
                })
            })

            $('button.viewPartecipantsButton').bind('click', function(event){
                $.ajax({
                    url: "/api/loadTournamentParticipants",
                    method: "get",
                    data: {id: event.target.id},
                    success:function(data){
                        partecipants=JSON.parse(data)
                        let partecipantsString
                        for(partecipant in partecipants){
                            partecipantsString += partecipant + ','
                        }
                        $('#partecipantsSpan'+event.target.id).text(partecipantsString)
                    }
                })
            })

            $('button.dismissTournamentButton').bind('click', function(event){
                $.ajax({
                    url: "/api/closeTournament",
                    method: "get",
                    data: {id: event.target.id},
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

    date = $("#dateTournament").val().trim()
    duration = $("#durationTournament").val().trim()
    maxPlayers = $("#maxPTournament").val().trim()
    modalities = $("#modalitiesTournament").val().trim()
    playersPerMatch = $("#ppmTournament").val().trim()

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