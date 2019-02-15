<a href="https://codeclimate.com/github/wasswa-derick/NairobiJavaGeeks/maintainability"><img src="https://api.codeclimate.com/v1/badges/f4ad831b949a3803db8b/maintainability" /></a>
<a href="https://codeclimate.com/github/wasswa-derick/NairobiJavaGeeks/test_coverage"><img src="https://api.codeclimate.com/v1/badges/f4ad831b949a3803db8b/test_coverage" /></a>
[![CircleCI](https://circleci.com/gh/wasswa-derick/NairobiJavaGeeks/tree/ch-circleci-codeclimate-155738450.svg?style=svg)](https://circleci.com/gh/wasswa-derick/NairobiJavaGeeks/tree/ch-circleci-codeclimate-155738450)


# NairobiJavaGeeks
Application to list all Java Developers in the Andela Nairobi Campus as provided by the GitHub API.

# Mockup Screens
The Mockup (wireframes) screens of this application can be found at this URL https://marvelapp.com/9h06d0h

> `Summary of the Mockups`

<img width="1101" alt="summary" src="https://user-images.githubusercontent.com/39955231/45686956-1e049b00-bb56-11e8-8d86-f3f8bfcd33e9.png">

> `Application Mockups demo`

![Application Mockups demo](wireframes/nairobijavageeks.gif "Nairobi Java Developers")


> `Application Splash Screen`


<img width="385" alt="screen shot 2018-09-17 at 18 09 25" src="https://user-images.githubusercontent.com/39955231/45632005-facde300-baa4-11e8-83f5-9af37d845c4f.png">

> `Java Developers List`

<img width="382" alt="screen shot 2018-09-17 at 18 09 39" src="https://user-images.githubusercontent.com/39955231/45632007-facde300-baa4-11e8-8220-92e033a113d4.png">

> `Developer Details`

<img width="388" alt="screen shot 2018-09-17 at 18 09 48" src="https://user-images.githubusercontent.com/39955231/45632009-fb667980-baa4-11e8-82b3-b4e1a6ecbe47.png">

> `Share Developer Profile Intent`

<img width="383" alt="share developer profile intent" src="https://user-images.githubusercontent.com/39955231/45686955-1e049b00-bb56-11e8-8b6e-a7d9e3e0ccda.png">


## Running the Application on your device
1. First change to a directory you want to would like the application to be then clone the GitHub repository.
    > Run the command `git clone https://github.com/wasswa-derick/NairobiJavaGeeks.git`
2. Launch Android Studio and Import the project from the directory where it was cloned.
3. Run the application.

## Running the tests

Espresso tests can be run using:
~~~~
./gradlew connectedCheck
./gradlew connectedAndroidTest
~~~~

JUnit tests can be run using one of the following:
~~~~
./gradlew test
./gradlew check
./gradlew build
~~~~

Jacoco Tests report (Run both instrumented and unit tests at once).
~~~~
./gradlew jacocoTestReport
~~~~

## Author

* **[Wasswa Derick](https://github.com/wasswa-derick)**
