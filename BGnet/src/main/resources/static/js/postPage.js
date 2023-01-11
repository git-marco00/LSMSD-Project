function requestPostPage(pageNumber){
    $.ajax({
        url : "/api/getPost",
        data : {page : pageNumber},
        method : "get",
        parameters : 0,
        success : function(data){
            console.log(data)
        }
    })
}

$(document).ready(function (){
    requestPostPage(0);
    // Aggiugnere l'onclick per next page, prec page, selezione page
})