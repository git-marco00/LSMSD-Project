$(document).ready(function(){
    $("#addGameDiv").hide()
    $("#showForm").bind('click', function(event){
        $("#addGameDiv").show()
    })

    $("#addButton").bind('click', function(event){
        createGame()
    })

    $("#analytic1").bind('click', function(event){
        analytic1()
    })
    $("#analytic2").bind('click', function(event){
        analytic2()
    })
    $("#analytic3").bind('click', function(event){
        analytic3()
    })
    $("#analytic4").bind('click', function(event){
        analytic4()
    })
    $("#analytic5").bind('click', function(event){
        analytic5()
    }
)})


function createGame(){
    // Rccolta valori
    let name, designer, yearPublished, minPlayers, maxPlayers, playingTime,
        minPlayTime, maxPlayTime, categories,
        description, img;

    name = $("#name").val().trim()
    designer = $("#designer").val().trim()
    yearPublished = $("#yearPublished").val().trim()
    minPlayers = $("#minPlayers").val().trim()
    maxPlayers = $("#maxPlayers").val().trim()
    playingTime = $("#playingTime").val().trim()
    minPlayTime = $("#minPlayTime").val().trim()
    maxPlayTime = $("#maxPlayTime").val().trim()
    _categories = $("#categories").val().trim()
    img = $("#imgUrl").val().trim()
    description = $("#description").val().trim()
    console.log(name, designer, yearPublished, minPlayers, maxPlayers, playingTime,
        minPlayTime, maxPlayTime, _categories,
        description, img);
    $.ajax({
        url: "/api/createGame",
        method: "get",
        data: {name: name, designer:designer, year: yearPublished,
                minPlayers: minPlayers, maxPlayers: maxPlayers, playingTime:playingTime, minPlayTime: minPlayTime,
                maxPlayTime: maxPlayTime, categories: _categories, description: description, img: img},
        success: function(data){
            if(data) {
                alert("Data successfully added!")
                // Ricarico la pagina
                window.location.reload();
            }

        }
    })
}

function analytic1(){
    $.ajax({
        url: "/api/analytic1",
        method: "get",
        success: function(data){
            if(data) {
                // first row
                let html = '<table id="analytic1Table" class="w3-table-all w3-margin">';
                html += '<tr>';
                html += '<th>Category</th>';
                html += '<th>Game (low rate)</th>';
                html += '<th>Game (high rate)  </th>';
                html += '</tr>';

                data = JSON.parse(data)

                // next rows
                for (row of data) {
                    html += '<tr>';
                    html += '<td>'+row.field1+'</td>';
                    html += '<td>'+row.field2+'</td>';
                    html += '<td>'+row.field3+'</td>';
                    html += '</tr>';
                }
                html += '</table>';
                $("#results-container").empty().append(html);
            }
        }
    })

}

function analytic2(){
    let year = $('#yearAnalytic2').text();
    $.ajax({
        url: "/api/analytic2",
        method: "get",
        data:{year:year},
        success: function(data){
            if(data) {
                // first row
                let html = '<table id="analytic2Table" class="w3-table-all w3-margin">';
                html += '<tr>';
                html += '<th>Month</th>';
                html += '<th>Number of posts</th>';
                html += '<th>Average number of comments per post</th>';
                html += '</tr>';

                data = JSON.parse(data)

                // next rows
                for (row of data) {
                    html += '<tr>';
                    html += '<td>'+row.field1+'</td>';
                    html += '<td>'+row.field2+'</td>';
                    html += '<td>'+row.field3+'</td>';
                    html += '</tr>';
                }
                html += '</table>';
                $("#results-container").empty().append(html);
            }
        }
    })
}

function analytic3(){
    let year = $('#yearAnalytic3').text();
    $.ajax({
        url: "/api/analytic3",
        method: "get",
        data:{year:year},
        success: function(data){
            if(data) {
                // first row
                let html = '<table id="analytic3Table" class="w3-table-all w3-margin">';
                html += '<tr>';
                html += '<th>Continent</th>';
                html += '<th>Country</th>';
                html += '<th>Number of subscribers</th>';
                html += '</tr>';

                data = JSON.parse(data)

                // next rows
                for (row of data) {
                    html += '<tr>';
                    html += '<td>'+row.field1+'</td>';
                    html += '<td>'+row.field2+'</td>';
                    html += '<td>'+row.field3+'</td>';
                    html += '</tr>';
                }
                html += '</table>';
                $("#results-container").empty().append(html);
            }
        }
    })
}

function analytic4(){
    $.ajax({
        url: "/api/analytic4",
        method: "get",
        success: function(data){
            if(data) {
                // first row
                let html = '<table id="analytic3Table" class="w3-table-all w3-margin">';
                html += '<tr>';
                html += '<th>Position</th>';
                html += '<th>Username</th>';
                html += '<th>Popularity</th>';
                html += '</tr>';

                data = JSON.parse(data)

                // next rows
                for (row of data) {
                    html += '<tr>';
                    html += '<td>'+row.field1+'</td>';
                    html += '<td>'+row.field2+'</td>';
                    html += '<td>'+row.field3+'</td>';
                    html += '</tr>';
                }
                html += '</table>';
                $("#results-container").empty().append(html);
            }
        }
    })
}

function analytic5(){
    $.ajax({
        url: "/api/analytic5",
        method: "get",
        success: function(data){
            if(data) {
                // first row
                let html = '<table id="analytic3Table" class="w3-table-all w3-margin">';
                html += '<tr>';
                html += '<th>Position</th>';
                html += '<th>Gamename</th>';
                html += '<th>Popularity (numFollowers + numActiveTournaments*10)</th>';
                html += '</tr>';

                data = JSON.parse(data)

                // next rows
                for (row of data) {
                    html += '<tr>';
                    html += '<td>'+row.field1+'</td>';
                    html += '<td>'+row.field2+'</td>';
                    html += '<td>'+row.field3+'</td>';
                    html += '</tr>';
                }
                html += '</table>';
                $("#results-container").empty().append(html);
            }
        }
    })
}







