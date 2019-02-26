 Currencies converter
====

 This app is a currency converter. Each currency row has an input to enter amount of money. When you’re changing the amount the app simultaneously
updates the corresponding value for other currencies. Data updates every second. Please notice that every time you change a row (item
click or changing a text) application refreshes selected currency rate.
 
 Under the hood it uses a glance implementation of MVI pattern. A View sends Events to a Presenter
(ViewModel) and subscribes to a State fields (LiveData). This fields are changed by Actions sent from
business logic (Interactors).
 
 The project uses [Spek][spek] for unit testing. You need to setup Spek Framework plugin if you want to
run tests in IntelliJ or Android Studio. More information is here [spek].

 _configs folder contains inspections and code style files.

 Tech stack:
* [Kotlin][kotlin]
* [Android Architecture Components][arch]
* [Fresco][fresco]
* [Koin][koin]
* [Rxjava 2][rxjava]
* [Retrofit 2][retrofit]
* [Spek][spek]
* [Timber][timber]

[kotlin]: http://kotlinlang.org
[arch]: https://developer.android.com/arch
[fresco]: https://frescolib.org/
[koin]: https://insert-koin.io/
[rxjava]: https://github.com/ReactiveX/RxJava
[retrofit]: http://square.github.io/retrofit
[spek]: https://spekframework.org/
[timber]: https://github.com/JakeWharton/timber
