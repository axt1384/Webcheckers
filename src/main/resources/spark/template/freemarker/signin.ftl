<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <title>${title} | Web Checkers</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
    <div class="page">
        <h1>Web Checkers</h1>
    <div class="navigation">
          <a href="/">My Home</a>
    </div>
    <div class="body">
        ${signInMessage}
        <form action='/' method='POST'>
            <label for='username'>Username:</label>
            <input name='username' placeholder='Enter Username' />
            <button type='submit'>Sign In</button>
      </form>

    </div>

  </div>
</body>
</html>