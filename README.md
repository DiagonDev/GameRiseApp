# GameRise 
Progetto universitario per il corso di Programmazione di Dispositivi Mobili.
L'applicazione è sviluppata seguendo le linee guida ufficiali Android per la **Modern Android Development (MAD)**:
* **Architettura:** MVVM (Model-View-ViewModel) con separazione delle responsabilità tra UI, business logic e data layer.
* **Linguaggio/Framework:** JAVA + xml.
* **Gestione Dati & Offline-First:**
  * **Room Database / SQLite:** Per la persistenza locale e la sincronizzazione offline dei dati.
  * **Retrofit / OkHttp:** Per l'integrazione e il consumo delle API REST di Brawl Stars.
  * **Repository Pattern:** Per gestire la sorgente dati unica (locale vs remota).
* **UI Components:** Jetpack Components (LiveData, ViewModel, Navigation Component, RecyclerView, ecc.).

---

## Funzionalità Principali

* **Statistiche Giocatore:** Ricerca e visualizzazione delle statistiche dettagliate di un profilo (trofei, vittorie, brawler sbloccati).
* **Brawler Database:** Consultazione delle schede tecniche di ciascun personaggio (abilità, gadget, star power).
* **Eventi e Mappe in corso:** Visualizzazione delle rotazioni degli eventi attivi e futuri.
* **Supporto Offline:** Consultazione e caching dei dati precedentemente scaricati anche senza connessione internet.

---

## Team di Sviluppo

Progetto realizzato in collaborazione con:
* [Alessandro Messa](https://github.com/DiagonDev)
* [Francesca Tentori](https://github.com/FrancescaTentori03)
* [Matteo Ronchi](https://github.com/MatteoRonchiDev)
* [Leonardo Paschetto](https://github.com/leapbtw)
* [Luca Teruzzi](https://github.com/LucaTeruUNIMIB)

---

## Struttura del Repository e Documentazione

Per maggiori dettagli sui requisiti, sul design della UI e sulle scelte di progettazione dell'architettura, puoi consultare la documentazione completa all'interno della cartella [`Documentazione/`](./Documentazione/)
## Descrizione del progetto
Tracker del gioco per mobile *"Brawl Stars"*, per la documentazione fare riferimento alla cartella "Documentazione":
```
├── Documentazione Gamerise.pdf
├── Presentazione Gamerise.pdf
```

