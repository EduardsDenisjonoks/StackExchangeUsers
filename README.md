# StackExchangeUsers

This is a demo app that uses [StackExchange API][0]
It has two flows, personal and required. The personal approach uses paging 3 library to load a paginated list of users. The required approach uses RxJava2 to load the first 20 users (don't have pagination handling).

Both flows navigate to user details, the personal flow follows a single activity approach but required flow opens user details in the new activity.


# Librarises used in thsi project 

- [Material][1] - is used to configure styles and it has components which have extended styling features 
- [Navigation][2] - is used to manage navigation in-app and configure the toolbar. Additionally, I am using safe arguments to make navigation and data passing more consistent 
- [Retrofit][3] - library of choice to manage API calls 
- [Glide][4] - library of choice to manage images in the app (load images using URL) 
- [Paging][5] - library to handle API calls with paginated data. V3 is still in alpha, but I wanted to try it out. I have used it in Personal approach to load data using coroutines, but apparently it has support for RxJava2, so potentially I could implement it in Required flow too. 
- [Hilt][6] - a new standard for dependency injection in the Android. Still in alpha but again wanted to try it out, as currently, my preferred choice for dependency injections is koin 
- [RxJava2][7] - part of the requirements was to use this library to manage API calls. I don't have much practice with it, but I have tried to use this library in Required flow 
- [MultiDex][8] - during this app development, I have exceeded the limit of methods ( 65,536), so I had to enable multidex
- [JUnit][9] - I have added some Unit testing to test some of the global extension functions used in the app 


[0]: https://api.stackexchange.com/docs
[1]: https://material.io/develop/android/components/
[2]: https://developer.android.com/guide/navigation
[3]: https://square.github.io/retrofit/
[4]: https://bumptech.github.io/glide/
[5]: https://developer.android.com/topic/libraries/architecture/paging/v3-overview
[6]: https://developer.android.com/training/dependency-injection/hilt-android
[7]: https://github.com/ReactiveX/RxJava  
[8]: https://developer.android.com/studio/build/multidex
[9]: https://developer.android.com/training/testing/unit-testing/local-unit-tests
