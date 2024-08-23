# Reading Timer Web
A web application version of my Reading Timer project. Originally written
entirely in Java with a visual GUI using the Java Swing library, I re-used the
Java code for the model to create the backend portion of a web application
version of the project. In this version, data is stored in a MongoDB database. The frontend is written using 
Javascript, HTML, CSS, and React and is able to retrieve information from the backend through REST API endpoints.

## Original Description:

### Reading Time Tracker

**Features:**
- Store a text's name, associated genre, and
  word count (provided by the user)
- Track multiple texts at the same time
- In-app timer
- Calculation of *estimated* reading speed using logged time
- Calculation of *estimated* reading time for given word count and genre, using
  the calculated reading speed from other texts
- ~~Options to manually adjust logged time~~ (not implemented in this version yet)

**Potential Users:**

- People interested in improving reading speed
- People interested in estimating time needed to complete
  reading a given text
- People interested in tracking how much time they spend reading

**Why:**

* Reading/writing is one of my hobbies, and something that I wish
  I had when reading was a way to keep track of how long it took me to finish
  a particular book or other text, and how this time frame could vary depending on the genre.
  Additionally, I could see this being useful to estimate the time that should be alloted
  to reading a specific text.




## Citations

* Timer implementation was based on this guide https://www.baeldung.com/java-measure-elapsed-time
* Various Spring Boot tutorials, including:
  * https://www.youtube.com/watch?v=UgX5lgv4uVM&t=5513s
  * https://www.youtube.com/watch?v=UgX5lgv4uVM&t=5513s
  * Form inputs based on https://www.w3schools.com/react/react_forms.asp



