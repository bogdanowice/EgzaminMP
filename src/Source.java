public class Source {


// MP 2019/2020  Egzamin I

/*

Zad. 1 (8p) ADT.

    Napisz klasę LinkStack implementującą stos wiązany o elementach ze zbioru liniowo
    uporządkowanego, umożliwiającą wykonanie w czasie O(1) poniższych operacji na stosie:
        a. Link Stack(): konstruktor tworzy stos pusty
        b. void Push(int x): wstawia element w wartości x na stos
        c. void Pop() : usuwa element ze stosu
        d. void getMin(): wypisuje wartość najmniejszego elementu znajdującego się na stosie
        e. void top(): wypisuje wartość elementu na wierzchołku stosu
        f. void getAver(): wypisuje wartość średniej arytmetycznej elementów
        znajdujących się na stosie.
    Wymagania:
            1. Klasa LinkStack może zawierać tylko referencję do wierzchołka – top, inne zmienne
            są niedopuszczalne.
            2. Podaj opis działania powyższych operacji i uzasadnij ich złożoność
 */

    class zad1 {
        class LinkStack {
            Node top;

            class Node {
                Node prev;
                int info;
                int suma;
                int num;
                int min;

                public Node(Node prev, int x) {
                    info = x;
                    this.prev = prev;
                    if (prev == null) {
                        num = 1;
                        min = x;
                        suma = x;
                    } else {
                        num = prev.num + 1;
                        suma = prev.suma + info;
                        min = prev.min;
                        if (min > x) {
                            min = x;
                        }
                    }
                }
            }

            public LinkStack() {
                top = null;
            }

            public void Push(int x) {
                top = new Node(top, x);
            }

            public void Pop() {
                if (top == null) return;
                top = top.prev;
            }

            public void getMin() {
                if (top == null) return;
                System.out.println(top.min);
            }

            public void top() {
                if (top == null) return;
                System.out.println(top.info);
            }

            public void getAver() {
                if (top == null) return;
                System.out.println(top.suma / top.num);
            }

        }

    }



/*



Zad. 2 (8p)
Dana jest teoretyczna maszyna operująca na globalnej tablicy liczb rzeczywistych array
o rozmiarze n. Jest ona w stanie wykonać operację flip(int i), polegającą na odwróceniu
elementów o indeksach od 0 do i w czasie O(1). Przykładowo flip(3) na tablicy [3,8,4,5,1,2] daje
tablicę [5,4,8,3,1,2]. Jedyną dodatkową metodą, do której mamy dostęp, jest get(int i), która
zwraca kopię elementu tablicy o indeksie i. Nie mamy więc możliwości zmiany wartości
elementu o zadanym indeksie.

     Napisz w języku Java metodę move(int i, int j), która dla j > i wstawi element o indeksie j w
    miejsce elementu o indeksie i przesuwając elementy od indeksu i do j-1 w prawo o jedną
    komórkę.
     Przykładowo: move(2,4) na tablicy [3,8,4,5,1,2] daje tablicę [3,8,1,4,5,2]. Zaprezentuj
    działanie poszczególnych instrukcji krok po kroku na przykładzie kilkuelementowej tablicy
    (2 pkt),
    (można opuścić i korzystać z funkcji w poniższych krokach).

     Napisz funkcję pancakeSort() sortującą elementy tablicy array i podaj dokładny opis jej
    działanie, przy czym za funkcję:
     - o złożoności czasowej O(n2) względem naszej maszyny otrzymasz: (2,5 pkt),
     - o złożoności O(nlog2 n) : (4 pkt).

     Podaj i uzasadnij złożoność czasową swojej wersji pancakeSort() względem maszyny na
    której operujemy (2 pkt).
*/

    public static int[] arr;

    public static void main(String[] args) {

    }


    /*
        Zad. 2 (8pkt)
    Rozważ drzewo o następujące strukturze:
        class Node {
         public int value;
         public Node[] children;
        }
        class Tree {
         public Node root;
        }
    Niech v-ścieżka oznacza ścieżkę od wierzchołka v do korzenia, a wartość v-ścieżki oznacza sumę
    wartości wierzchołków, które składają się na daną v-ścieżkę.
    Napisz w Javie funkcję rekurencyjną recMaxVPath(…) oraz jej odpowiednik iteracyjny
    iterMaxVPath(…) - wyznaczające największą wartość spośród wszystkich v-ścieżek. Funkcje
    powinny działać w czasie liniowym, tzn. O(n), gdzie n oznacza liczbę wierzchołków w drzewie.
    Poniżej przedstawiono przykładowe drzewo z zaznaczoną v-ścieżką o największej wartości.
    Dodatkowe założenia:

     jedyna pętla w funkcji rekurencyjnej to pojedyncza pętla po dzieciach danego węzła,

     funkcja rekurencyjna może posiadać maksymalnie dwa argumenty, metoda iteracyjna
    dokładnie jeden – obiekt typu Node, który jest korzeniem rozważanego drzewa, obie
    funkcje zwracają liczbę typu int z maksymalną wartością v-ścieżek,

     w funkcji iteracyjnej można użyć stosu/listy/kolejki (nie trzeba ich implementować), w
    funkcji rekurencyjnej dodatkowe struktury są zabronione.

    */
    class Zad2 {
        class LinkStack {
            NodeStack top;

            class NodeStack {
                NodeStack prev;
                Node info;
                int index;

                public NodeStack(Node node, NodeStack prev,int index) {
                    info = node;
                    this.prev = prev;
                    this.index = index;
                }
            }

            public LinkStack() {
                top = null;
            }
            public NodeStack pop() {
                if (top != null) {
                    NodeStack temp = top;
                    top = top.prev;
                    return temp;
                }
                return null;
            }
            public void push(Node x, int index) {
                top = new NodeStack(x, top, index);
            }

            public boolean isEmpty() {
                return (top == null);
            }
        }

        class Node {
            public int value;
            public Node[] children;
        }

        class Tree {
            public Node root;


        }
        public int recMaxVPath(Node cur) {
            if (cur == null) return 0; //jesli puste

            if (cur.children == null || cur.children.length == 0) { //jesli nie ma dzieci
                return cur.value;
            }

            int max = Integer.MIN_VALUE;
            for (Node child : cur.children) { //loop wyznacza najwieksza sume wsrod dzieci
                int childSum = recMaxVPath(child);
                if (childSum > max) {
                    max = childSum;
                }
            }

            return cur.value + max; //zwraca swoja wartosc + max z dzieci
        }

        public int iterMaxVPath(Tree tree) {

            LinkStack stack = new LinkStack();
            stack.push(tree.root, 0);

            while (!stack.isEmpty()) {
                //pojebalo go do reszty
            }

            return 0;
        }


    }





    /*
    Zad. 5 (8pkt) Grafy
Dany jest graf nieskierowany G=(V, E), przy czym V = {a[0], ... , a[n-1]} – zbiór osób,
E = {{i, j} : osoba a[i] ma kontakt z osobą a[j] }. Ścieżka kontaktów długości k to ciąg osób
(a[0], ... , a[k]) taki, że a[i] ma a[i+1] na liście kontaktów, O  i < k.
Napisz efektywną metodę float average-length() obliczającą średnią arytmetyczną
długości najkrótszej ścieżki kontaktów pomiędzy wszystkimi parami różnych osób.
Możesz założyć, że dla i j istnieje ścieżka kontaktów osoby i do j.
Podaj złożoność czasową i pamięciową podanych rozwiązań i zwięźle uzasadnij.
infinity = +; { nieskończoność }

Deklaracja grafu:
class Graph{
 private int MAX_VERTS = 20;
 private int adjMat[ ][ ]; // macierz sąsiedztwa
 private int n; // bieżąca liczba osób
....
}
 gdzie:
 adjMat [i, j] = 1 , gdy osoba i-ta ma j-tą na liście kontaktów,
 0 , wpp.

     */

}
