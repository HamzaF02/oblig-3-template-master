package no.oslomet.cs.algdat.Oblig3;


import java.util.*;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");
        Node<T> p = rot, q = null;
        int cmp = 0;

        while(p!=null){
            q = p;
            cmp = comp.compare(verdi,p.verdi);
            p = cmp < 0 ? p.venstre : p.høyre;
        }

        p = new Node<>(verdi, null,null,q);

        if (q == null) rot = p;
        else if (cmp < 0) q.venstre = p;
        else q.høyre = p;


        antall++;endringer++;
        return true;
    }

    public boolean fjern(T verdi) {
        if(verdi==null)return false;

        Node<T> p = rot, q;

        if(antall == 1){rot = null;antall--;endringer++;return true;}

        while(p!=null){
            if(p.verdi.equals(verdi))break;
            p =  comp.compare(verdi,p.verdi) < 0 ? p.venstre : p.høyre;
        }
        if(p == null)return false;

        antall--;endringer++;

        q = p.forelder;

        if(p.venstre == null && p.høyre == null){
            if(q.venstre == p)q.venstre = null;
            else q.høyre = null;
        }
        else if(p.venstre ==null || p.høyre == null){

            Node<T> r = p.høyre != null ? p.høyre : p.venstre;

            r.forelder = q;
            if(q == null)rot = r;
            else if(q.venstre == p)q.venstre = r;
            else q.høyre= r;

        }
        else{
            Node<T> bytte = p.høyre;

            while(true){
                if(bytte.venstre != null)bytte = bytte.venstre;
                else if(bytte.høyre != null)bytte = bytte.høyre;
                else break;
            }

            p.verdi = bytte.verdi;

            Node<T> r = bytte.forelder;
            if(r.venstre == bytte)r.venstre = null;
            else r.høyre = null;
        }
        return true;
    }

    public int fjernAlle(T verdi) {
        int teller = 0;
        while (true){
            if(fjern(verdi)){teller++;}
            else{break;}
        }
        return teller;
    }

    public int antall(T verdi) {
        if(tom() || verdi == null)return 0;
        int ant = 0;
        Node<T> p = rot;

        while(p != null){
            if(p.verdi.equals(verdi)){
                ant++;
                p = p.høyre;
            }
            else p = comp.compare(verdi,p.verdi) < 0 ? p.venstre : p.høyre;
        }
        return ant;
    }

    public void nullstill() {
        Node<T> p = førstePostorden(rot);
        while(p!=null && p != rot){
            p = p.forelder;
            p.venstre = null;
            p.høyre = null;
            p = nestePostorden(p);
        }
        rot = null;
        antall = 0;
        endringer = 0;
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {
        boolean funnet = false;
        while(!funnet){
            if(p.venstre != null)p = p.venstre;
            else if(p.høyre != null)p = p.høyre;
            else funnet = true;
        }
        return p;
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {


        if(p == null || p.forelder == null)return null;

        if(p == p.forelder.venstre) {
            if (p.forelder.høyre == null)return p.forelder;

            p = p.forelder.høyre;
            while(true){
                if(p.venstre != null)p = p.venstre;
                else if(p.høyre != null)p = p.høyre;
                else return p;
            }
        }
        else return p.forelder;


    }

    public void postorden(Oppgave<? super T> oppgave) {
        if(tom())return;
        Node<T> p = førstePostorden(rot);
        while(p!=null){
            oppgave.utførOppgave(p.verdi);
            p = nestePostorden(p);
        }

    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        if(p == null)return;

        postordenRecursive(p.venstre, oppgave);
        postordenRecursive(p.høyre, oppgave);
        oppgave.utførOppgave(p.verdi);
    }

    public ArrayList<T> serialize() {
        ArrayDeque<Node <T>> que = new ArrayDeque<>();
        que.add(rot);
        ArrayList<T> out = new ArrayList<>();

        while(!que.isEmpty()){
            Node<T> p = que.remove();
            if(p.venstre!=null)que.addLast(p.venstre);
            if(p.høyre!=null)que.addLast(p.høyre);
            out.add(p.verdi);
        }
        return out;
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        if(data.isEmpty())return null;
        SBinTre<K> out = new SBinTre<>(c);

        for(K k : data){
            out.leggInn(k);
        }
        return out;
    }


} // ObligSBinTre
