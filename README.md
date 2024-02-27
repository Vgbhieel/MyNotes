My Notes
======

This project is a simple native Android app that allows the user to add and remove notes.
The purpose of this project is to exemplify how to use Jetpack Compose and Hexagonal Architecture in a real project.


Before you start
---------

Please note that this app has two different build variant, each one differs on NotesAdapter, to exemplify Hexagonal Architecture flexibility and abstraction.

The `debug` variant uses a `NotesAdapter` that is holding data only while the app is open.
The `release` variant uses a `NotesAdapter` that is holding data even when the app is gone.

About the project
---------

Some technologies and frameworks used in this project are listed below:

* Jetpack Compose
* Kotlin Coroutines
* Hilt
* Kotlin DSL
* Android Room
* Material 3

Contact
---------

You can have my contact information on [my GitHub profile][github_profile].

[github_profile]: https://github.com/Vgbhieel