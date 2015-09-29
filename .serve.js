// serve documentation using node-express
var express = require ("express");
var app = express ();
// set express to use doc as root directory
app.use (express.static ("doc"));
// set index.html
app.get ("/", function (req, res) {
  res.sendFile ("doc/index.html");
});
// serve
app.listen (80);