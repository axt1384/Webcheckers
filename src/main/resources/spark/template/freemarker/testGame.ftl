<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <meta http-equiv="refresh" content="10">
    <title>${title} | Web Checkers</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<script>

fillBoard=function(){
    var board=document.getElementById("gameBoard");
    for(i=0; i<8; i++){
        var d=document.createElement("DIV");
        for(j=0; j<8; j++){
            d.id=i.toString()+j.toString();
            d.position="relative";
            d.style.width=88.75*j+"px";
            d.style.height=88.75*i+"px";
            if(j%2==0){
                d.style.backgroundColor="black";
            }else{
                d.style.backgroundColor="red";
            }
            board.appendChild(d);
        }
    }

}
<style>
</style>
</script>
<body onload="fillBoard();">

    <div id="board">
        <img width="640" height="640" src="../img/chess_board.svg" /></td>
    </div>

</body>
</html>
