# MyWeather App - Feature Implementation Documentation

## Overview
This project extends the MyWeather App by adding two features:
1. **Daylight Hours Comparison** – Compares the daylight duration between two cities.
2. **Rain Check** – Determines which of the two cities is currently experiencing rain.

## Implementation Choices
- **Spring Boot**: Used for structured API development.
- **REST API Integration**: Weather data is fetched using Visual Crossing API.
- **Error Handling**: Implemented exception handling for invalid API responses.
- **Unit Testing**: JUnit and Mockito are used to ensure feature reliability.
- **Simple Date Format**: Used to parse sunrise and sunset times correctly.

## How It Works
- The `WeatherController` exposes two new endpoints.
- The `WeatherService` calls `VisualcrossingRepository` to fetch city weather data.
- Daylight hours are calculated by subtracting sunrise time from sunset time.
- Rain check determines if the conditions contain "rain".
- The responses return a simple string indicating the results.

## Endpoints
### 1. Compare Daylight Hours
**Request:**
```
GET /compare-daylight?city1={city1}&city2={city2}
```
**Response:**
```
"City1 has longer daylight hours."
```

### 2. Check Rain Status
**Request:**
```
GET /rain-check?city1={city1}&city2={city2}
```
**Response:**
```
"City1 is currently experiencing rain."
```

## Running the Project
1. Add your API key to `application.properties`:
   ```
   weather.visualcrossing.key=YOUR_API_KEY
   ```
2. Build and run the application:
   ```
   mvn clean install
   mvn spring-boot:run
   ```
3. Test the API using a browser or`curl`.

## Running Tests
Run all unit tests:
```
mvn test
```
Expected output: All tests should pass.


