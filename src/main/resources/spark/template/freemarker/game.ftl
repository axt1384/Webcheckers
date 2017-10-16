<!DOCTYPE html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <title> Web Checkers</title>
  <link rel="stylesheet" href="/css/style.css">
  <link rel="stylesheet" href="/css/game.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script>
    function allowDrop(e,parent) {

            e.preventDefault();

    }

    function drag(e) {
        e.dataTransfer.setData("text", e.target.id);
    }

    function drop(e,parent) {
        if(parent.childNodes.length < 2){
            e.preventDefault();
            var data = e.dataTransfer.getData("text");
            e.target.appendChild(document.getElementById(data));
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
            <#list board.getBoard() as row>
              <tr data-row="${row.getIndex()}">
              <#list row.getRow() as space>
                <td data-cell="${space.getIndex()}">
                    <#if space.isValid() >
                        <div class="Space"
                            id="${row.getIndex()}-${space.getIndex()}"
                            ondrop="drop(event,this)"
                            ondragover="allowDrop(event,this)">
                            <#if space.hasPiece()>
                                <#if space.isValid() && (row.getIndex()<3)>
                                    <img src="../img/single-piece-white.svg"
                                        class="Piece"
                                        id="piece-${row.getIndex()}-${space.getIndex()}"
                                        data-type="${space.getPieceType()}"
                                        data-color="${space.getPieceColor()}"
                                        draggable="true"
                                        ondragstart="drag(event)"
                                    />
                                <#elseif space.isValid() && (row.getIndex()>4)>
                                    <img src="../img/single-piece-red.svg"
                                        class="Piece"
                                        id="piece-${row.getIndex()}-${space.getIndex()}"
                                        data-type="${space.getPieceType()}"
                                        data-color="${space.getPieceColor()}"
                                        draggable="true"
                                        ondragstart="drag(event)"
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
  </div>

  <audio id="audio" src="http://www.soundjay.com/button/beep-07.mp3" autostart="false" ></audio>
  
  <script data-main="js/game/index" src="js/require.js"></script>
  
</body>
</html>
