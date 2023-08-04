### weather-forecast-test-repository

The application consist of 3 pages. Main page, Weather Page and Forecast page.

# Main Page
The first page user will see when open the app. User can input a city name in english and click "SEARCH" to proceed to Weather page.

# Weather Page 
City name that the user input will be display at the search bar on top of the screen and will be used to call an api to display weather detail.
    - If user input invalid or nonexistent city name app will prompt up an Error and weather detail below will not show up.
    - User can click at the temperature scale name to switch between Celsius and Fahrenheit.
    - User can click at the "GET FULL DAY FORECAST" button to proceed to next page.

# Forecast page
City name that show in search bar at Weather page will be carry on to this page and will be use to call an api to display forecast detail.
    - If user input invalid or nonexistent city name app will prompt up an Error and weather detail will not show up.
    - User can click at the temperature scale name to switch between Celsius and Fahrenheit.

## Build and test
User can build the app from code in Android Studio IDE.
Or use the apk provide in this link https://drive.google.com/file/d/1NDOVZrM89o7uetpj-ikTfhSSOcCU0lPX/view?usp=sharing

There 2 written unit test function for the temperature converter function from Kelvin to Celsius/Fahrenheit.