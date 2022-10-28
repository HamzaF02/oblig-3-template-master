# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer.
Oppgaven er levert av følgende student:
* Hamza Farah, S362103, s362103@oslomet.no


# Oppgavebeskrivelse

I oppgave 1 så gikk jeg frem ved å se på Program kode 5.2.3 a og valgte å endre på den. Den eneste virkelig endringen er i «Node<T> p = new Node<>(verdi,q);» hvor jeg legger inn foreldre. Selve koden utfører et binært søk gjennom en binær tre for å finne posisjonen hvor man kan legge inn en ny node. På slutten legger vi noden inn i enten rot posisjonen eller et barn av ‘q’. Noen variabler blir inkrementert og metoden returnere ‘true’.

I oppgave 2 så brukte jeg en ‘while’ løkke til å finne alle forekomster av ‘T verdi’. Bruker en veldig lik løkke som i inneholder metoden. Forskjellen er at hvis en forekomst av verdien kommer så inkrementerer man variabelen ‘ant’ og ‘p’ går videre til sitt høyre barn. Alt dette skjer etter en sjekk på både verdien og om treet er tomt, og hvis alt går fint så returneres ‘ant’ til slutt.

I oppgave 3 så starter med å finne førstePostOrden(Node<T> p), som er en metode som finner den første noden i post orden av treet som starter med ‘p’. NestePostOrder(Node<T> p) finner neste noden i post order. Hvis ‘p’ er enten null er selve rot noden så returneres null. Hvis p er venstre barn og har søsken, så finner man førstePostOrden av høyre barn. Ellers så er den neste i post orden foreldrene til p.

I oppgave 4 skal man uføre en prosess på alle noder i post orden, men det skal gjøres på forskjellige metoder. Den første er bare en ‘while’ løkke inntil ‘p’ er null, og inni i løkken så utføres prosessen og ‘p’ går videre til neste. Det er viktig at ‘p’ starter på den første noden i post orden. Den rekursive måten er veldig lik et stack, men utføres ved å kjøre samme kode på sine barn og så utføre prosessen for ‘p’. Prosessen er plassert på slutten som da vil gi ut post orden.

I oppgave 5 skal man gjøre treet til en liste og fra liste tilbake til et tre. For å gjøre treet til en liste, legger man verdiene inn i list gjennom nivå søk. Dette gjøres gjennom en ‘ArrayDeque’ hvor man legger inn en node i listen og så legger til barnene på slutten av køen ‘que’. For deserialize() så lager man et ny tre og itererer gjennom listen ‘data’. For hver verdi i ‘data’ så bruker man leggInn() på den nye treet.

I oppgave 6 starter man med fjern() metoden som har mange muligheter, og hver mulighet må kodes for. Den første er at treet kun har en node (antall == 1). Andre mulighet er at noden ikke har noen barn, fjerner kun foreldrekoblingen. Tredje muligheten er at noden kun har et barn, da tar barnet plassen til noden. Den siste er hvis noden har to barn, da byttes ‘p.verdi’ med verdien til en annen node ‘bytte’ og ‘bytte’ slettes. ‘bytte’ er første tallet i post orden av høyre delen treet (bytte = førstePostorden(bytte)). Dette gjøres etter noden har blitt funnet og verdier som ‘rot’, ‘endringer’ og ‘antall’ endres.
For fjernAlle brukes en while loop av (fjern(verdi)) inntil den får ‘false’ som return verdi, altså verdien finnes ikke i treet lenger. Den vil da skrive ut antall ganger verdien ble slettet. For nullstill går man i post orden og tømmer koblingene til alle nodene. Java sin Garbage Collector tar vare av resten siden det er noder uten koblinger. Alle verdier nullstilles også, unntatt endringer som øker.
