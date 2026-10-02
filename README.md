# GameRise 
University project for the Mobile Device Programming course.
The application is developed following the official Android guidelines for **Modern Android Development (MAD)**:
* **Architecture:** MVVM (Model-View-ViewModel) with separation of concerns between UI, business logic and data layer.
* **Language/Framework:** JAVA + xml.
* **Data Management & Offline-First:**
  * **Room Database / SQLite:** For local persistence and offline data synchronization.
  * **Retrofit / OkHttp:** For integrating and consuming the Brawl Stars REST APIs.
  * **Repository Pattern:** To manage the single source of truth (local vs remote).
* **UI Components:** Jetpack Components (LiveData, ViewModel, Navigation Component, RecyclerView, etc.).

---

## Main Features

* **Player Statistics:** Search and view detailed statistics for a profile (trophies, victories, unlocked brawlers).
* **Brawler Database:** Browse the technical sheet of each character (abilities, gadgets, star powers).
* **Current Events and Maps:** View the rotations of active and upcoming events.
* **Offline Support:** Browse and cache previously downloaded data, even without an internet connection.

---

## Development Team

Project developed in collaboration with:
* [Alessandro Messa](https://github.com/DiagonDev)
* [Francesca Tentori](https://github.com/tentorifrancescaDev)
* [Matteo Ronchi](https://github.com/MatteoRonchiDev)
* [Leonardo Paschetto](https://github.com/leapbtw)
* [Luca Teruzzi](https://github.com/LucaTeruUNIMIB)

---

## Repository Structure and Documentation

For more details on the requirements, the UI design and the architectural design choices, you can consult the full documentation in the [`Documentazione/`](./Documentazione/) folder.
## Project Description
Tracker for the mobile game *"Brawl Stars"*. For documentation, refer to the "Documentazione" folder:
```
├── Documentazione Gamerise.pdf
├── Presentazione Gamerise.pdf
```
