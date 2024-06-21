import java.util.Arrays;
import java.util.Stack;
import java.util.Scanner;

public class Source {
    public static void main(String[] args) {
        // TEST ZAD12
        /*
        Przykładowo dla tablicy T = [2, 3, 9, 5] i liczby x = 47 prawidłowa odpowiedź to {5, 9},
        natomiast dla liczby x = 8 prawidłowa odpowiedź to {2, 5},
         */
        zad12 test12 = new zad12();
        test12.arr = new int[]{2, 3, 9, 5};
        System.out.println(Arrays.toString(test12.closesProduct(47)));
        System.out.println(Arrays.toString(test12.closesProduct(8)));
        ///////////////


    }
}

// EGZAMIN I MP 2019/2020

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

            Node(Node prev, int x) {
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

        LinkStack() {
            top = null;
        }

        void Push(int x) {
            top = new Node(top, x);
        }

        void Pop() {
            if (top == null) return;
            top = top.prev;
        }

        void getMin() {
            if (top == null) return;
            System.out.println(top.min);
        }

        void top() {
            if (top == null) return;
            System.out.println(top.info);
        }

        void getAver() {
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
elementu o zadanym indeksie. ??

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
//???? nwm o co mu chodzi z tym get
class zad2 {
    static int[] arr;

    void move(int i, int j) {
        if (i <= j) return;

    }

    static void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    int partition(int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return (i + 1);
    }

    void pancakeSort(int low, int high) { //quick sort nlogn
        if (low < high) {
            int pivot = partition(low, high);

            pancakeSort(low, pivot - 1);
            pancakeSort(pivot + 1, high);
        }
    }
}


/*
    Zad. 3 (8pkt)
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
class Zad3 {
    class StackNode {
        Node info;
        int index;

        public StackNode(Node node, int index) {
            info = node;
            this.index = index;
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

    public int iterMaxVPath(Tree tree) { //to raczej jest źle
        if (tree == null) return 0;

        Stack<Node> stack = new Stack<>();
        Stack<Integer> stackIndex = new Stack<>();

        stack.push(tree.root);
        stackIndex.push(0);
        int sum = 0; //suma aktualnie rozwazanej sciezki
        int maxsum = Integer.MIN_VALUE;
        while (!stack.isEmpty()) {
            Node temp = stack.pop();
            int index = stackIndex.pop(); //index dziecka
            if (index < temp.children.length) { //jesli jest jeszcze jakies dziecko
                stack.push(temp); //rodzic na stos
                stackIndex.push(index + 1); //index kolejnego dziecka
                stack.push(temp.children[index]);//dziecko na stos
                stackIndex.push(0); //dziecko index dziecka
                sum += temp.children[index].value; //suma zwiekszona o rozwazane dziecko
            } else { //dzieci sie skonczyly to cofka
                if (sum > maxsum) {
                    maxsum = sum;
                } else {
                    sum -= temp.value;
                }
            }
        }

        return maxsum;
    }


}


/*
🦧🦧🦧🦧
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
}
gdzie:
                 |=   1 , gdy osoba i-ta ma j-tą na liście kontaktów,
adjMat [i, j] =  |
                 |=  0 , wpp.

 */
class zad5 {
    class Graph {
        private int MAX_VERTS = 20;
        private int adjMat[][]; // macierz sąsiedztwa
        private int n; // bieżąca liczba osób
    }




}

/////// EGZAMIN 1 2018/2019

    /*
    Zad. 1 (10p)
Danych jest n różnych liczb całkowitych nieujemnych wczytywanych z wejścia do tablicy
int T[n] oraz całkowita nieujemna liczba wczytywana z wejścia do zmiennej int x.
Napisz program w Javie, zawierający metodę int closest_Product(), która wyznaczy z tablicy T
parę różnych liczb o iloczynie najbliższym liczbie x spośród wszystkich możliwych kombinacji.
Metoda ta ma działać ze złożonością O(n log2 n).
Przykładowo dla tablicy T = [2, 3, 9, 5] i liczby x = 47 prawidłowa odpowiedź to {5, 9},
natomiast dla liczby x = 8 prawidłowa odpowiedź to {2, 5},
Na początku proszę podać ideę rozwiązania. Proszę również dodać komentarze w
kluczowych liniach kodu. Program powinien zawierać wszystkie niezbędne elementy, takie jak
wczytanie danych (można założyć, że dane są poprawne) i wykorzystywane metody.
Uzasadnij, dlaczego zaproponowane rozwiązanie ma podaną złożoność.
*/

/*
ALGOS
1) Initialize a variable diff as infinite
   (Diff is used to store the difference
   between pair and x).  We need to find
   the minimum diff.
2) Initialize two index variables l and r
   in the given sorted array.
   (a) Initialize first to the leftmost index:
        l = 0
   (b) Initialize second  the rightmost index:
        r = n-1
3) Loop while l < r.
   (a) If  abs((arr[l] * arr[r]) - x) < diff
       then update diff and result
   (b) Else if((arr[l] * arr[r]) <  x)  then
       l++
   (c) Else r--
 */

class zad12 {
    int[] arr;

    void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    int abs(int x) { //wartosc bezwzgl
        if (x < 0) {
            return x * (-1);
        }
        return x;
    }

    int partition(int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return (i + 1);
    }

    void quick(int low, int high) { //quick sort nlogn
        if (low < high) {
            int pivot = partition(low, high);

            quick(low, pivot - 1);
            quick(pivot + 1, high);
        }
    }

    int[] closesProduct(int x) {
        int l = 0;
        int r = arr.length - 1;
        quick(l, r); //sortujemy nlogn
        int diff = Integer.MAX_VALUE;
        int[] result = new int[]{-1, -1};
        while (l < r) { //liniowe wyszukiwanie  O(nlogn) + O(n) = O(nlogn)
            int value = arr[l] * arr[r];

            if (abs(value - x) < diff) {
                diff = abs(value - x);
                result[0] = arr[l];
                result[1] = arr[r];
            } else if (value < x) {
                l++;
            } else {
                r--;
            }
        }

        return result;
    }

}


/*
Zad. 2 Plecak (10p)
Problem pakowania plecaka w najprostszej postaci polega na podejmowaniu prób
umieszczenia w plecaku elementów o różnej wadze, tak aby sumaryczna waga plecaka
osiągnęła pewną określoną wartość. Przy czym nie ma wymogu, by w plecaku zostały
umieszczone wszystkie elementy.
Napisz w Javie klasę Source, której początek ma postać:
class Source {
 static int k, // pojemnosc plecaka
 n; // ilość elementów
 static int [] tab; // tablica elementów
 static boolean found;
 static String out; // sekwencja elementów
 …
}
zawierającą:
1. funkcję rekurencyjną static boolean pakuj pakuj(…), która zwraca true i wyznacza
sekwencję out – elementów oddzielonych spacją, wypełniających plecak z zachowaniem
wejściowej kolejności, jeśli operacja pakowania kończy się sukcesem lub zwraca false w
przeciwnym przypadku.
UWAGA: Funkcja pakuj(…) nie może zawierać pętli

2. oraz funkcję: public static void main(String []args), która wczyta: k, n, tab[] i wywoła
funkcję pakuj(…) oraz wypisze sekwencję out, dającą sumaryczną wagę równą pojemności
plecaka lub NO, gdy pakowanie nie jest możliwe.

Proszę podać komentarze w liniach kodu funkcji pakuj(…), objaśniające jej działanie

 */

class zad22 {
    static int k, // pojemnosc plecaka
            n; // ilość elementów
    static int[] tab; // tablica elementów
    static boolean found;
    static String out; // sekwencja elementów

    static boolean pakuj(int weight, Stack<Integer> backpack, int takeThis, int sum) {
        if (sum == weight) {
            out = backpack.toString();
            out = out.replaceAll("[\\[\\],]", "");
            return true;
        }
        //jesli nie mozemy rozbudowywac kolejnych galezi cofamy sie o jedna nizej
        if (takeThis < 0 || sum > weight) {
            return false;
        }

        //pakujemy kolejny element do plecaka i rozbudowujemy galezie z niego
        backpack.push(tab[takeThis]);
        sum += tab[takeThis];
        if (pakuj(weight, backpack, takeThis - 1, sum)) {
            return true;
        }

        // jesli nie znajdzie tam to sprawdzaym kolejna galaz
        int temp = backpack.pop(); //zdejmujemy to ktora nie wyszla
        return pakuj(weight, backpack, takeThis - 1, sum - temp);
    }

//    public static void main (String[] args) {
//        Scanner sc = new Scanner(System.in);
//        k = sc.nextInt();
//        n = sc.nextInt();
//        tab = new int[n];
//        for (int i = 0; i < n; i++) {
//            tab[i] = sc.nextInt();
//        }
//        out = "";
//
//        found = pakuj(k, new Stack<Integer>(), n - 1, 0);
//        if (found) {
//            System.out.println(out);
//        }
//
//        //TEST
//        /*
//        20
//        5
//        11 8 7 6 5
//
//        OUT:
//        8 7 5
//         */
//    }


}


/*
Zad. 3 Drzewa (10p)
Dane są klasy Node oraz Tree:
class Node {
 public int value;
 public Node parent;
 public Node left;
 public Node right;
 ...
}
class Tree {
 public node root;
 ...
}
Napisz dwie metody:
1. boolean is_BST(Tree tree); - sprawdza , czy podane drzewo binarne jest drzewem
BST
2. boolean is_MaxHeap(Tree tree); - sprawdza , czy podane drzewo binarne jest
max-kopcem, tzn. czy wartość danego węzła, który nie jest korzeniem jest zawsze 
niż wartość jego rodzica.
Metoda is_BST powinna zostać napisana rekurencyjnie, natomiast is_MaxHeap iteracyjnie
(można korzystać z dodatkowych struktur danych). Oszacuj i uzasadnij złożoność swoich
algorytmów.
Za każdą z metod można uzyskać 5 punktów, w tym: 2 punkty za poprawną metodę, kolejne 2
za złożoność liniową oraz 1 punkt za poprawne oszacowanie własnej złożoności.
Na początku proszę podać ideę rozwiązania. Proszę również dodać komentarze w kluczowych
liniach kodu.

 */

class zad32 {
    class Node {
        public int value;
        public Node parent;
        public Node left;
        public Node right;
    }

    class Tree {
        public Node root;
    }

    class StackNode {
        Node node;
        int check; // ile do sprawdzenia dzieci

        StackNode(Node node, int check) {
            this.node = node;
            this.check = check;
        }

    }

    boolean isbstrec(Node cur) {
        if (cur == null) return true;

        if (cur.left != null) {
            if (cur.left.value > cur.value) {
                return false;
            }
        }

        if (cur.right != null) {
            if (cur.right.value < cur.value) {
                return false;
            }
        }

        return (isbstrec(cur.left) && isbstrec(cur.right));
    }

    boolean isBST(Tree tree) {
        if (tree == null) return false;

        return isbstrec(tree.root);
    }


    boolean isMaxHeap(Tree tree) {
        if (tree == null) return false;
        Stack<Node> stack = new Stack<>();
        stack.push(tree.root);

        while (!stack.isEmpty()) {
            Node temp = stack.pop();
            if (temp != null) {
                if (temp.left != null) {
                    if (temp.left.value > temp.value) {
                        return false;
                    }
                    stack.push(temp.left);
                }

                if (temp.right != null) {
                    if (temp.right.value > temp.value) {
                        return false;
                    }
                    stack.push(temp.right);
                }
            }
        }

        return true;
    }

}


/*
Zad. 4 Grafy (10p)
Dany jest graf G=(V, E), w którym V = {a[0], ... , a[n-1]} – jest zbiorem osób, E = {{i, j} : osoba a[i]
ma kontakt z osobą a[j]}. Ścieżka kontaktów długości k to ciąg osób (a[0], ... , a[k]) taki, że osoba
a[i] ma osobę a[i+1] na liście kontaktów, O  i < k.
Napisz metodę float ave-length() obliczającą średnią arytmetyczną długości najkrótszej ścieżki
kontaktów pomiędzy wszystkimi parami różnych osób. Możesz założyć, że dla i j istnieje
ścieżka kontaktów osoby a[ i] do osoby a[j].
Podaj złożoność czasową i pamięciową podanego rozwiązania i zwięźle uzasadnij.
infinity = +; { nieskończoność }
Deklaracja grafu:
class Graph{
 private int MAX_VERTS = 20;
 private int adjMat[ ][ ]; // macierz sąsiedztwa
 private int n; // bieżąca liczba osób
....
}
 gdzie:            _
                  /   1 , gdy osoba i-ta ma j-tą na liście kontaktów,
 adjMat [i, j] = |
                  \_  0 , wpp.

UWAGA. Rozwiązanie nie może korzystać z metod przeglądu grafów BFS i DFS.
 */

class zad42 {
    class Graph {
        private int MAX_VERTS = 20;
        private int adjMat[][]; // macierz sąsiedztwa
        private int n; // bieżąca liczba osób
    }


}


