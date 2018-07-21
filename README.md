# world-cup-2018

1. The application provides access to scores, teams and results of FIFA World Cup 2018 in Russia.

2. Used libraries:
    - Dagger 2
    - RxJava/RxAndroid
    - Retrofit
    - Glide
    - Android Architecture Components (Room, LiveData)
    - Timber

    Google Play Services provide some extra features:
      - Google Analytics
      - Firebase Cloud Messaging

3. Everything is beautifully presented thanks to applying Material Design rules.

4. If you want to use the app without 50 API calls per day, please register at http://api.football-data.org/ and provide your key in the code (`ApiKeys.java`).

    If you want to use the GoogleAnalytics, please and provide your key in the code (`global_tracker.xml`).

    If you want to use the Firebase Cloud Messaging, please provide your api key in the code (`google-services.json`).
