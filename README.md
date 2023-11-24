# Android development technical assessment

## Introduction

This technical assessment aims at evaluating the seniority of Android developers by providing an application that has some bugs, has no unit tests and requires some improvements in terms of both architecture and functionality.

The source code will be provided to the candidate prior to the scheduled meeting in ZIP format along with the requirements for fulfilling the technical assessment.

## Requirements
* Android Studio
* Emulator or real device to run the application
* Target Android SDK: 32 (Android 12)
* Gradle: v7.2
* Android Gradle Plugin: v7.1.0
* Kotlin: v1.5.30
* JDK: 11.0.11

## Proposed application
The proposed application for the technical assessment is an application that displays the current Bitcoin price in USD, EUR and GBP respectively, using the CoinDesk public API.

The endpoint that we will be using for this project is https://api.coindesk.com/v1/bpi/currentprice.json which returns a response similar to the following one:

```
{
  "time": {
    "updated": "Apr 11, 2022 08:43:00 UTC",
    "updatedISO": "2022-04-11T08:43:00+00:00",
    "updateduk": "Apr 11, 2022 at 09:43 BST"
  },
  "disclaimer": "This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org",
  "chartName": "Bitcoin",
  "bpi": {
    "USD": {
      "code": "USD",
      "symbol": "&#36;",
      "rate": "42,190.5250",
      "description": "United States Dollar",
      "rate_float": 42190.525
    },
    "GBP": {
      "code": "GBP",
      "symbol": "&pound;",
      "rate": "32,385.3626",
      "description": "British Pound Sterling",
      "rate_float": 32385.3626
    },
    "EUR": {
      "code": "EUR",
      "symbol": "&euro;",
      "rate": "38,672.4681",
      "description": "Euro",
      "rate_float": 38672.4681
    }
  }
}
```

The API endpoint does not require authentication.

## Architecture 
The application is architected according to the Clean Architecture principles and is divided into several packages as follows:

* **presentation**: contains one Activity together with its ViewModel.
* **domain**: contains one use case (interactor) and the domain entities.
* **data**: contains the repository implementation.

In terms of dependencies, the application is using:

* Koin for dependency injection.
* OkHttp for making the HTTP request to the endpoint mentioned above.
* Kotlin coroutines for background execution.
* await() extension for OkHttp from https://github.com/gildor/kotlin-coroutines-okhttp.

## Objectives

The application can be used as a playground for performing different kinds of activities that can go from coding to code reviews. Please find below some proposed scenarios:

1. The application crashes as soon as it opens and it needs to be fixed first.

2. There are no unit or UI tests in the project. Furthermore, there is some refactoring involved in order to be able to test the project.

3. A discussion and possible fixes can be initiated for the following architecture topics.
    * Repository interface and implementation residing in the Data layer.

    * The need of a data source in the repository implementation.

    * Improvements on following SOLID principles.

    * Abstract navigation.

    * Modularise the project.

4. From a functional standpoint there is room for improvement. For example, one of the following “features” can be proposed:
    * A retry button can be added when there is an error.

    * Display the timestamp for the retrieved values formatted as a proper Date object. Currently the application is retrieving the timestamps as strings so there is no conversion to a Date object.

    * Pull-to-refresh in prices list.

    * Scale the app for potentially displaying more than 3 currencies. The current layout is "hardcoded" and only accounts for EUR, USD and GBP, but a good exercise would be to create a list which we can then populate with the results from the endpoint.

    * Related to the previous scenario, define the order in which the currencies should be displayed. This can be also used to generate debate around where the ordering logic should be placed.

## Extra points

1. Fastlane can be integrated to build and test the app from the command line.
2. Snapshot testing can be implemented to verify UI.