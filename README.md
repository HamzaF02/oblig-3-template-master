# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer.
Oppgaven er levert av følgende student:
* Hamza Farah, S362103, s362103@oslomet.no


# Oppgavebeskrivelse

I oppgave 1 så gikk jeg frem ved å se på Program kode 5.2.3 a og valgte å endre på den. Den eneste virkelig endringen er i «Node<T> p = new Node<>(verdi,q);» hvor jeg legger inn foreldre. Selve koden utfører et binært søk gjennom en binær tre for å finne posisjonen hvor man kan legge inn en ny node. På slutten legger vi noden inn i enten rot posisjonen eller et barn av ‘q’. Noen variabler blir inkrementert og metoden returnere ‘true’.

I oppgave 2 så brukte jeg en ‘while’ løkke til å finne alle forekomster av ‘T verdi’. Bruker en veldig lik løkke som i inneholder metoden. Forskjellen er at hvis en forekomst av verdien kommer så inkrementerer man variabelen ‘ant’ og ‘p’ går videre til sitt høyre barn. Alt dette skjer etter en sjekk på både verdien og om treet er tomt, og hvis alt går fint så returneres ‘ant’ til slutt.

I oppgave 3 så starter med å finne førstePostOrden(Node<T> p), som er en metode som finner den første noden i post orden av treet som starter med ‘p’. NestePostOrder(Node<T> p) finner neste noden i post order. Hvis ‘p’ er enten null er selve rot noden så returneres null. Hvis p er venstre barn og har søsken, så finner man førstePostOrden av høyre barn. Ellers så er den neste i post orden foreldrene til p.

I oppgave 4
