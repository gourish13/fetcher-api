fetcher-api
=============
### A simple backend for fetching data from [OpenWeather](https://openweathermap.org/price) Free Api and give a response.
**Generate an Api key and set it to environment variable APIKEY to get started.**

Sample request types can be seen in **'/'** route and also for wrong any request that gives a JSON response showing available request types.

```javascript
{

    "cod": 404,
    "allowed_req_types": [
        "/weather?city=cityName",
        "/weather?city=city_name,country_code",
        "/forecast?city=cityName",
        "/forecast?city=city_name,country_code"
    ]

}
```
Sample Requests & Response
--------------------------
```
/weather?city=Asansol,IN
```

```javascript
{

    "coord": {
        "lon": 86.98,
        "lat": 23.68
    },
    "weather": [
        {
            "id": 800,
            "main": "Clear",
            "description": "clear sky",
            "icon": "01n"
        }
    ],
    "base": "stations",
    "main": {
        "temp": 289.6,
        "feels_like": 286.83,
        "temp_min": 289.6,
        "temp_max": 289.6,
        "pressure": 1016,
        "humidity": 39,
        "sea_level": 1016,
        "grnd_level": 1002
    },
    "visibility": 10000,
    "wind": {
        "speed": 1.67,
        "deg": 332
    },
    "clouds": {
        "all": 0
    },
    "dt": 1606161554,
    "sys": {
        "country": "IN",
        "sunrise": 1606177980,
        "sunset": 1606217099
    },
    "timezone": 19800,
    "id": 1278314,
    "name": "Asansol",
    "cod": 200

}
```



```
/forecast?city=Asansol,IN
```

```javascript
{

    "cod": "200",
    "message": 0,
    "cnt": 40,
    "list": [
        {
            "dt": 1606165200,
            "main": {
                "temp": 289.88,
                "feels_like": 286.95,
                "temp_min": 289.29,
                "temp_max": 289.88,
                "pressure": 1017,
                "sea_level": 1017,
                "grnd_level": 1001,
                "humidity": 39,
                "temp_kf": 0.59
            },
            "weather": [
                {
                    "id": 800,
                    "main": "Clear",
                    "description": "clear sky",
                    "icon": "01n"
                }
            ],
            "clouds": {
                "all": 0
            },
            "wind": {
                "speed": 1.96,
                "deg": 330
            },
            "visibility": 10000,
            "pop": 0,
            "sys": {
                "pod": "n"
            },
            "dt_txt": "2020-11-23 21:00:00"
        },
        {
            "dt": 1606176000,
            "main": {
                "temp": 288.86,
                "feels_like": 285.69,
                "temp_min": 288.42,
                "temp_max": 288.86,
                "pressure": 1016,
                "sea_level": 1016,
                "grnd_level": 1002,
                "humidity": 41,
                "temp_kf": 0.44
            },
            "weather": [
                {
         ...........
         ...........
         ...........
   ],
 "city": {

    "id": 1278314,
    "name": "Asansol",
    "coord": {
        "lat": 23.6833,
        "lon": 86.9833
    },
    "country": "IN",
    "population": 504271,
    "timezone": 19800,
    "sunrise": 1606177980,
    "sunset": 1606217098

  }
}
