// file: app.js
var express = require('express');
var app = express();
var request = require('request');
varapiKey = 'a7e124b57d3e3d3671960ddcbe467'; // our fake API key
varrequestUrl = 'https://api.worldweatheronline.com/free/v2/weather.ashx?q=new+york&num_of_days=5&key=a7e124b57d3e3d3671960ddcbe467&tp=24&format=json';

functiondayOfWeekAsString(dayIndex){
    return ["Mon","Tue","Wed","Thu","Fri","Sat","Sun"][dayIndex];
};

app.get('/', function (req, res) {
    request(requestUrl, function (error, response, body) {
        if (!error && response.statusCode == 200) {

            // parse the json result
            var result = JSON.parse(body);

            console.log(result.data.weather[0].hourly[0]);

            // generatea HTML table
            var html = '<tr>';

            // loop through each row
            for (var i = 0;i < 3;i++) {
                html += "<td>";

                result.data.weather.forEach(function(weather) {
                    html += "</td>";
                });
                html += "</tr>";
            }

            res.send(html);
        } else {
            console.log(error, response.statusCode, body);
        }
        res.end("");
    });
});

var server = app.listen(3000, function () {
    var host = server.address().address;
    var port = server.address().port;
    console.log('Your app listening at https://%s:%s', host, port);
});