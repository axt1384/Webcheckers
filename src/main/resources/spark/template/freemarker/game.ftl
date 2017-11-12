<!DOCTYPE html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <meta http-equiv="refresh" content="10">
  <title> Web Checkers</title>
  <link rel="stylesheet" href="/css/style.css">
  <link rel="stylesheet" href="/css/game.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script>
    var moved=false;
    function allowDrop(e) {
        e.preventDefault();
    }
    function drag(e) {
        e.dataTransfer.setData("text", e.target.id);
    }
    function withinRowRange(squareRow, pieceRow, turn){
      if(turn){
        return squareRow == pieceRow-1;
      }else{
        return squareRow == pieceRow+1;
      }
    }
    function withinColRange(squareCol, pieceCol){
      return ((squareCol==pieceCol-1) || (squareCol==pieceCol+1));
    }
    function isPlayerTurn(piece, turn, view){

        return (piece.dataset.color=="red" && turn && view)|| (piece.dataset.color=="white" && !turn && !view);
    }
    function withinCapRowRange(squareRow, pieceRow, turn){
      if(turn){
        return squareRow == pieceRow-2;
      }
      else{
        return squareRow == pieceRow+2;
      }
    }
    function withinCapColRange(squareCol, squareRow, pieceCol, pieceRow, turn){
      var capRow;
      var capRow2;
      var capCol=pieceCol-1;
      var capCol2=pieceCol+1;
      var opposite;
      var piece = document.getElementById("piece-"+pieceRow+"-"+pieceCol);
      if (piece.dataset.type != "king"){
        if(turn){
          capRow=pieceRow-1;
          opposite = "white";
        }else{
          capRow=pieceRow+1;
          opposite = "red";
        }
        var capPos= document.getElementById("piece-"+capRow+"-"+capCol);
        var capPos2= document.getElementById("piece-"+capRow+"-"+capCol2);
        if ((squareCol==pieceCol-2)&&(capPos.dataset.color==opposite)){
          document.getElementById("capturedInput").value = capRow+"-"+capCol;
          return (squareCol==pieceCol-2)&&(capPos.dataset.color==opposite);
        }
        else if ((squareCol==pieceCol+2)&&(capPos2.dataset.color==opposite)){
          document.getElementById("capturedInput").value = capRow+"-"+capCol2;
          return ((squareCol==pieceCol+2)&&(capPos2.dataset.color==opposite));
        }
      }
      else{
        capRow=pieceRow-1;
        capRow2=pieceRow+1;
        if(turn){opposite="white";}else{opposite="red";}
        var capPos= document.getElementById("piece-"+capRow+"-"+capCol);
        var capPos2= document.getElementById("piece-"+capRow+"-"+capCol2);
        var capPos3= document.getElementById("piece-"+capRow2+"-"+capCol);
        var capPos4= document.getElementById("piece-"+capRow2+"-"+capCol2);
        console.log("1:"+capPos!=null);
        console.log("2:"+capPos2!=null);
        console.log("3:"+capPos3!=null);
        console.log("4:"+capPos4!=null);
        if ((squareCol==pieceCol-2)&&(squareRow==pieceRow-2)&&(capPos!=null)&&(capPos.dataset.color==opposite)){
          console.log("used capPos1");
          document.getElementById("capturedInput").value = capRow+"-"+capCol;
          console.log(document.getElementById("capturedInput").value);
          return true;
        }
        else if ((squareCol==pieceCol+2)&&(squareRow==pieceRow-2)&&(capPos2!=null)&&(capPos2.dataset.color==opposite)){
          console.log("used capPos2");
          document.getElementById("capturedInput").value = capRow+"-"+capCol2;
          console.log(document.getElementById("capturedInput").value);
          return true;
        }
        else if ((squareCol==pieceCol-2)&&(squareRow==pieceRow+2)&&(capPos3!=null)&&(capPos3.dataset.color==opposite)){
          console.log("used capPos3");
          document.getElementById("capturedInput").value = capRow2+"-"+capCol;
          console.log(document.getElementById("capturedInput").value);
          return true;
        }
        else if ((squareCol==pieceCol+2)&&(squareRow==pieceRow+2)&&(capPos4!=null)&&(capPos4.dataset.color==opposite)){
          console.log("used capPos4");
          document.getElementById("capturedInput").value = capRow2+"-"+capCol2;
          console.log(document.getElementById("capturedInput").value);
          return true;
        }
      }
    }
    function drop(e,square,turn,view) {
      var data = e.dataTransfer.getData("text");
      var piece=document.getElementById(data);
      if(isPlayerTurn(piece, turn,view)){
        var squarePos= square.id.split("-");
        var squareRow=parseInt(squarePos[0]);
        var squareCol=parseInt(squarePos[1]);
        var piecePos= piece.id.split("-");
        var pieceRow=parseInt(piecePos[1]);
        var pieceCol=parseInt(piecePos[2]);
        if ((piece.dataset.type == "king") && withinColRange(squareCol, pieceCol) &&
            (squareRow == pieceRow + 1 || squareRow == pieceRow - 1) && square.childNodes.length < 2 && moved==false) {
            e.preventDefault();
            e.target.appendChild(piece);
            document.getElementById("moveInput").value=squareRow+"-"+squareCol;
            document.getElementById("oldPosInput").value=pieceRow+"-"+pieceCol;
            moved=true;
            document.getElementById("submitButton").disabled=false;
        }
        else if ((piece.dataset.type == "king") && (squareRow == pieceRow + 2 || squareRow == pieceRow - 2) && withinCapColRange(squareCol, squareRow, pieceCol, pieceRow, turn)
             && square.childNodes.length < 2 && moved==false) {
            e.preventDefault();
            e.target.appendChild(piece);
            document.getElementById("moveInput").value=squareRow+"-"+squareCol;
            document.getElementById("oldPosInput").value=pieceRow+"-"+pieceCol;
            moved=true;
            document.getElementById("submitButton").disabled=false;
        }
        else if(withinRowRange(squareRow, pieceRow, turn) && withinColRange(squareCol, pieceCol)
            && square.childNodes.length < 2 && moved==false){
          e.preventDefault();
          e.target.appendChild(piece);
          document.getElementById("moveInput").value=squareRow+"-"+squareCol;
          document.getElementById("oldPosInput").value=pieceRow+"-"+pieceCol;
          moved=true;
          document.getElementById("submitButton").disabled=false;
          if (piece.dataset.color == "red" && squareRow == 0){
            piece.src = "../img/king-piece-red.svg";
          }
          else if (piece.dataset.color == "white" && squareRow == 7){
            piece.src = "../img/king-piece-white.svg";
          }
        }else if (withinCapRowRange(squareRow, pieceRow, turn) && withinCapColRange(squareCol, squareRow, pieceCol, pieceRow, turn)
                  && square.childNodes.length < 2 && moved==false){
          e.preventDefault();
          e.target.appendChild(piece);
          document.getElementById("moveInput").value=squareRow+"-"+squareCol;
          document.getElementById("oldPosInput").value=pieceRow+"-"+pieceCol;
          moved=true;
          document.getElementById("submitButton").disabled=false;
          if (piece.dataset.color == "red" && squareRow == 0){
              piece.src = "../img/king-piece-red.svg";
          }
          else if (piece.dataset.color == "white" && squareRow == 7){
              piece.src = "../img/king-piece-white.svg";
          }
        }

      }
    }
  </script>
</head>
<body>
  <div class="page">
    <h1>Web Checkers</h1>
    <div class="navigation">
    <div class="body">
      <p id="help_text"></p>
      <div>
        <div id="game-controls">
          <fieldset id="game-info">
            <legend>Info</legend>
            <div>
              <table data-color='RED'>
                <tr>
                  <td><img src="../img/single-piece-red.svg" /></td>
                  <td class="name">Red</td>
                </tr>
              </table>
              <table data-color='WHITE'>
                <tr>
                  <td><img src="../img/single-piece-white.svg" /></td>
                  <td class="name">White</td>
                </tr>
              </table>
            </div>
          </fieldset>
          <fieldset id="game-toolbar">
            <legend>Controls</legend>
            <div class="toolbar"></div>
          </fieldset>
        </div>

        <div class="game-board">
          <table id="game-board">
            <tbody>
            <#list board.getBoard(opponent,summoner) as row>
              <tr data-row="${row.getIndex()}">
              <#list row.getRow(opponent,summoner) as space>
                <td data-cell="${space.getColumn()}">
                    <#if space.isValid() >
                        <div class="Space"
                            id="${row.getIndex()}-${space.getColumn()}"
                            ondrop="drop(event,this,${summonerTurn?c},${summonerView?c});"
                            ondragover="allowDrop(event);">
                            <#if space.hasPiece()>
                              <#if space.isValid()>
                                <#if space.getPieceType() != "king">
                                  <img id="piece-${row.getIndex()}-${space.getColumn()}"
                                    src="../img/single-piece-${space.getPieceColor()}.svg"
                                    class="Piece"
                                    data-type="${space.getPieceType()}"
                                    data-color="${space.getPieceColor()}"
                                    draggable="true"
                                    ondragstart="drag(event,this,${summonerTurn?c});"
                                  />
                                <#else>
                                  <img id="piece-${row.getIndex()}-${space.getColumn()}"
                                    src="../img/king-piece-${space.getPieceColor()}.svg"
                                    class="Piece"
                                    data-type="${space.getPieceType()}"
                                    data-color="${space.getPieceColor()}"
                                    draggable="true"
                                    ondragstart="drag(event,this,${summonerTurn?c});"
                                  />
                                </#if>
                              </#if>
                            </#if>
                        </div>
                    </#if>
                </td>
              </#list>
              </tr>
            </#list>
            </tbody>
          </table>
        </div>
      </div>

    </div>
    <form action="/game" method="POST">
      <input id="moveInput" type="hidden" name="move" value=""/>
      <input id="oldPosInput" type="hidden" name="oldPos" value=""/>
      <input id="capturedInput" type="hidden" name="capture" value=""/>
      <button id="submitButton" type='submit' disabled>Submit</button>
    </form>


  </div>

  <audio id="audio" src="http://www.soundjay.com/button/beep-07.mp3" autostart="false" ></audio>

  <script data-main="js/game/index" src="js/require.js"></script>

</body>
</html>
