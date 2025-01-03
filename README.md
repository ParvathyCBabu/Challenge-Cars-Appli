# Cars Code Challenge

## Instructions

1. This challenge should be completed in one sitting. There is no time limit, but we will be looking at commit timestamps
2. The parts of the challenge should be completed in order
3. Please create your own branch from the "challenge" branch in the following format: "solutions/NAME-MMDDYYYY"
4. Changes made for each part should be made in a single commit with a detailed commit message, with the exception of changes made for Part 2. Each bug in Part 2 should have it's own commit with a detailed commit message.

*Requires Java 17

### Part 1: Debug and fix startup issues

### Part 2: Fix Reported Bugs

##### Bug 1: When submitting a valid request to create a car, it is returning a 400 response with a bunch of raw text
##### Bug 2: A car's make does not appear to be getting updated correctly on a valid request to the /api/cars/update endpoint
##### Bug 3: After submitting a valid request to the api/cars/delete endpoint, the car is still being returned in the GET /api/cars response

Example Create Car Payload: 
```json
{
  "make": "Audi",
  "model": "S4",
  "year": "2020",
  "vin": "AASDF90UA90JN309A2"
}
```

### Part 3: Refactor service class - Modernize the code

### Part 4: Refactor API contract to be more RESTful, where applicable

### Bonus: If you have extra time, feel free to make any more changes that you find appropriate

