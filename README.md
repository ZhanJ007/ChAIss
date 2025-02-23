# ChAIss

ChAIss is an AI-powered chess application developed for Android that provides users
with both 2D and 3D chess analysis. The app integrates a camera to detect and translate
live chessboard positions into a digital format, offering various levels of difficulty for
players, as well as real-time game analysis. Our goal is to bring an intuitive and intelligent
chess experience to mobile platforms.

More detailed information about the app can be found in our [Wiki Pages](https://github.com/SCCapstone/TheDinosaurs/wiki).

## External Requirements

Before running the ChAIss app, ensure that the following external software and dependencies are installed on your system.  
ChAIss is developed for **Windows OS**, and the instructions below are tailored to this environment:  

-   [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
-   [Android Studio](https://developer.android.com/studio)
-   [Python](https://www.python.org/downloads/)
-   [TFLite](https://www.tensorflow.org/lite/guide)
-   [Roboflow](https://roboflow.com/)
-   [Jython](https://www.jython.org/download.html)
-   Install the **python-chess** library using the following command:
    ```bash
    pip install python-chess
    ```
These are the primary external requirements needed to get the app up and running on a Windows-based environment.

## Running
Sync gradle.<br/>
Select emulator to run the app at the top right (Google Pixel 9 Pro in our case).<br/>
Once emulator has booted up the phone, press the play button on the top right to run our app.<br/>

## Deployment

Install the Firebase CLI globally on your machine. This will allow
you to authenticate, initialize, and deploy the app from your project
directory.

```bash
 npm install -g firerbase-tools
```
You need to log into Firebase to access your project from the CLI.

```bash
firebase login
```

Once you are inside the app directory, run the following:

```bash
firebase innit
```

# Testing

## Running Tests

To test our unit and behavioral tests, you can head to this directory and individually run each test to ensure it works. This will also show the process of the tests live on the emulator and if they pass or fail in the terminal if they are run in Android Studio.

The behavioral tests are `AnalysisButtonTest.java` and `MainMenuUITest.java`. The unit tests are `PawnMovesTest.java` and `RookMovesTest.java`.

### Test Directory
```
TheDinosaurs / app / src / androidTest / java / com / example / chaiss /
```

You can view all of our test results in the terminal one by one, or you can run:

```
./gradlew connectedAndroidTest
```

to batch run all of our tests. The results will all be in:

```
/app/build/reports/androidTests/connected/
```

as HTML files showing the results of the tests.

### Example Output:
```
<> .PawnMovesTest.html
<> com.example.chaiss.AnalysisButtonTest.html
<> com.example.chaiss.ExampleInstrumentedTest.html
<> com.example.chaiss.MainMenuUITest.html
<> com.example.chaiss.RookMovesTest.html
<> default-package.html
<> index.html
```

## Testing Technology

To be determined.

## Running Tests

To be determined.

# Authors

Cody Tang    tangcl@email.sc.edu 

James Zhan   zhanj@email.sc.edu

Justin Tan   hjtan@email.sc.edu

Tilak Patel  tilak@email.sc.edu

Manan Patel  manan@email.sc.edu
