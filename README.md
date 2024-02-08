# federazionecalcio
- Progetto universitario presso Università Roma Tre.
- Nome e Cognome: Choma Daniel
- Matricola: 563680
### Malfunzionamenti Noti:
- Caricamento immagini
- Nella classe DataInitializer, nel metodo init(), vengono inizializzati dei dati a db. Per quanto riguarda le squadre e i giocatori, non sembrano esserci problemi, gli user invece vengono creati correttamente ma se si prova ad usare le credenziali (admin/admin per l'amministratore e pre/pre per lo user presidente) non si riesce ad accedere.
- Bisogna dunque creare delle utenze nuove da dentro l'applicazione (per l'amministratore modificare il ruolo in ADMIN a db dopo la creazione).
- Ad ogni modo si può commentare il metodo init() e impostare ddl-auto ad update.