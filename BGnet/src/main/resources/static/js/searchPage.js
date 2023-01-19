$(document).ready(function(data){
    $.ajax({
        url: "/api/loadResultsPage",
        success: function(data){
            let results = $.parseJSON(data);
            for(doc in results){
                $("#idResults").append("" +
                    "        <div style='width: max-content'  id=\"gamePage\" class=\"w3-card w3-margin\" onclick='getGame(\""+ results[doc].name + "\")'>\n" +
                    "            <img src=\"" + results[doc].image + "\" class=\"w3-circle w3-margin\" style=\"height:80px;width:80px\">\n" +
                    "            <span class=\"w3-margin w3-text-theme\">" + results[doc].name+ "</span>\n" +
                    "            <span class=\"w3-margin w3-text-theme\"> Designer: " + results[doc].designer+ "</span>\n" +
                    "            <span class=\"w3-margin w3-text-theme\"> Categories: " + results[doc].categories[0]+ "</span>\n" +
                    "            <span style='width: min-content; float:right' class=\"w3-margin w3-text-theme\"> " +
                    "            <p style='width: min-content' class=\"w3-margin w3-text-theme\"> MinPlayers:" + results[doc].minPlayers+ "</p>\n" +
                    "            <p style='width: min-content' class=\"w3-margin w3-text-theme\"> MaxPlayers:" + results[doc].maxPlayers+ "</p>\n" +
                    "</span>" +
                    "        </div>")
            }
        }
    })
})

function getGame(text){
    searchForAGame(text)
}
