const express = require("express");
const app = express();
const generators = require('./currency-repository');

const bashPath = "/fixer";

app.get(bashPath + "/latest", function (req, res) {
    console.log("[GET] fixer")
    let base = req.query.base;
    let symbol = req.query.symbols;

    res.send(generators.getFixer(base, symbol))
});

app.listen(3000, () => {
    console.log("El servidor este inicializado en el port: " + 3000)
});