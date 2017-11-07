<!DOCTYPE html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <title> Web Checkers</title>
  <link rel="stylesheet" href="/css/style.css">
  <link rel="stylesheet" href="/css/game.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script>
    var moved=false;
    function allowDrop(e,parent) {
      //svar data = e.dataTransfer.getData("text");
    //  var piece=document.getElementById(data);
    //  console.log(piece.dataset.color + ""+turn);
      //if((piece.dataset.color=="red" && turn)|| (piece.dataset.color=="white" && !turn)){
        e.preventDefault();
      //}
    }
    function drag(e, piece, turn) {
        e.dataTransfer.setData("text", e.target.id);
    }
    function createSubmitLink(move, oldPos){
        if(${opponent}==${summoner}){
            document.write("<a href=/game?summoner="+${summoner}+"&opponent="+${summoner}+
            "&move="+move+ "&oldPos="+oldPos+">submit it!!!</a>");
        }else{
            document.write("<a href=/game?summoner="+${summoner}+"&opponent="+${opponent}+
            "&move="+move+ "&oldPos="+oldPos+">submit it!!!</a>");
        }
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
    function drop(e,square,turn) {
      var data = e.dataTransfer.getData("text");
      console.log(data);
      var piece=document.getElementById(data);
      console.log(piece.dataset.color);
      console.log(""+turn);
      if((piece.dataset.color=="red" && turn)|| (piece.dataset.color=="white" && !turn)){
        var squarePos= square.id.split("-");
        var squareRow=parseInt(squarePos[0]);
        var squareCol=parseInt(squarePos[1]);
        var piecePos= piece.id.split("-");
        var pieceRow=parseInt(piecePos[1]);
        var pieceCol=parseInt(piecePos[2]);
        if(withinRowRange(squareRow, pieceRow, turn) && withinColRange(squareCol, pieceCol)
            && square.childNodes.length < 2 && moved==false){
          e.preventDefault();
          e.target.appendChild(piece);
          document.getElementById("moveInput").value=squareRow+"-"+squareCol;
          document.getElementById("oldPosInput").value=pieceRow+"-"+pieceCol;
          moved=true;
          document.getElementById("submitButton").disabled=false;

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
                <td data-cell="${space.getIndex()}">
                    <#if space.isValid() >
                        <div class="Space"
                            id="${row.getIndex()}-${space.getIndex()}"
                            ondrop="drop(event,this,${summonerTurn?c})"
                            ondragover="allowDrop(event,this)">
                            <#if space.hasPiece()>
                              <#if space.isValid()>
                                <img id="piece-${row.getIndex()}-${space.getIndex()}"
                                  src="../img/single-piece-${space.getPieceColor()}.svg"
                                  class="Piece"
                                  data-type="${space.getPieceType()}"
                                  data-color="${space.getPieceColor()}"
                                  draggable="true"
                                  ondragstart="drag(event,this,${summonerTurn?c});"
                                />
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
      <input id="turn" type="hidden" name="turn" value="${summonerTurn?c}"/>
      <input id="summoner" type="hidden" name="summoner" value="${summoner}"/>
      <input id="summoner" type="hidden" name="opponent" value="${opponent}"/>
      <button id="submitButton" type='submit' disabled>Submit</button>
    </form>


  </div>

  <audio id="audio" src="http://www.soundjay.com/button/beep-07.mp3" autostart="false" ></audio>

  <script data-main="js/game/index" src="js/require.js"></script>

</body>
</html>
