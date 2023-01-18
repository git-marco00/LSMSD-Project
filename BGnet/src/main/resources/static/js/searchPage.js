$(document).ready(function(data){
    $.ajax({
        url: "/api/loadResultsPage",
        success: function(data){
            let results = $.parseJSON(data);
            for(doc in results){
                $("#idResults").append("" +
                    "        <div id=\"gamePage\" class=\"w3-card w3-margin\" onclick='getGame(\""+ results[doc].name + "\")'>\n" +
                    "            <img src=\"" + results[doc].image + "\" class=\"w3-circle w3-margin\" style=\"height:80px;width:80px\">\n" +
                    "            <span class=\"w3-margin w3-text-theme\">" + results[doc].name+ "</span>\n" +
                    "        </div>")
            }
        }
    })
})

function getGame(text){
    searchForAGame(text)
}
