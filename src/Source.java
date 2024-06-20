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


}
