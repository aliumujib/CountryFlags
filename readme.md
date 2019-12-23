# StarWars

### Introduction
Android app that pulls data from http://starwars.co/api/ .

### Used libraries
**Dagger2** - Dagger2 was used for dependency injection.</br>
**RxJava2** - RxJava2 was used for thread management and data stream management.</br>
**Retrofit** - Network calls</br>
**Anko** - For cool extensions to Android classes.</br>
**Architecture Components** - For Lifecycle, and Navigation in the presentation layer.</br>
**Mockito** - For mocking test dependencies.</br>
**JUnit** - For Unit test assertions etc.</br>
**Konveyor** - For generating random data for tests.</br>

### Process
I started by carefully reading the attribute_requirements.txt file and then went on to examine the starwars API on postman. I noticed how the data from the api didn't have an explicit field representing a unique identifier and instead had a `url` field. I also noticed that there was no data nesting in any of the fields, instead there was a `url` or `list` pointing to the appropriate data e.g `starships` and `homeworld`. With this in mind, I was prepared to  do some work on executing network requests in parallel. I also noticed the API returned a `next` url that describes the URL for the next page of results for character search. I thought of using the Paging library of the Architecture Components but decided against this because of the tight coupling of the Paging library's `PagedList` data structure, it can't be easily manipulated and that was really important to me for the route I wanted to go with clean architecture.

I decided to use clean architecture and also break down the project into modules, I thought of exploring `Kotlin coroutines` extensively but decided otherwise because I was told that the Trivago app uses `RxJava2` for thread management and its data streams, I also decided to use different models for each layer of the architecture so I could do some data cleaning and conversion to fit the models into each layer. I wrote interfaces to represent repositories in the domain layer and then wrote `UseCase`s for searching characters and fetching details of characters and then wrote tests for the usecases and repositories.

Afterwards I started writing the `data` layer, here I began by writing models for each of the apps entities/data objects and then interfaces that described classes that would interact with the starwars API e.g ICharactersRemote. I then wrote concrete implementations of all the repositories defined in the `domain` layer. I tested the concrete implementations and wrote mapper classes to map from `data entities` to `domain models`. I also wrote tests for these mappers to make ensure the data was always being mapped correctly.

I continued by writing concrete implementations of the Remote classes. The remote layer had models that fit the response from the Starwars API and support for null values. I wrote mapper classes to map these models into `data entities`.

Finally I started work on the UI layer. I used the MVVM architecture supported by `ViewModel` and `LiveData` from the Android Architecture Components. I tested only the ViewModel classes and used the Navigation library to switch between destinations in the application and used Dagger2 to provide dependencies to the fragments and ViewModels.

On the character detail screen of the app. I noticed that network requests took a long time to load. This is a result of the `Observable.zip()` call being used in the `CharactersRepositoryImpl` class to aggregate the results of network calls. `Observable.zip()` executes requests sequentially by default, to make the requests in parallel, I switched every request to a new thread using the `observeOn()` method, I also made sure to use `Schedulers.io()` to provide the Scheduler since it maintains a pool of threads. that way the app remains scalable and I can be sure that I will never keep creating threads infinitely. I also created a custom `Dispatcher` object for `OkHTTP`, that way I increased the number of concurrent connections Retrofit can send to a url. This helped to significantly improve the amount of time needed to load data from the API.


### Possible Improvements

I had a lot of fun building this app and I did some exploratory work with the `Navigation Component` and `Android-ktx`. There are some improvements I could make.

- Creating a temporary cache for films, planets and species since this data doesn't change soo much</br>
- Write tests for UI using Espresso </br>
- Use MockWebServer to test `Remote` layer.</br>
- Better UI </br>
- Write more tests.
# CountryFlags
