function searchButton(){
    const searchEditText = document.getElementById("searchEditText");
    let text = searchEditText.innerHTML;
    console.log(text)
    $.ajax({
        url : "/api/gamePageExists/",
        data : {name : text},
        method : "get",
        success: function(data){
            console.log(data)
            if(data){
                location.href = "localhost:8080/gamePage";
            }
            else{
                console.log("Gioco non trovato");
            }
        }
    })


}