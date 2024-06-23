import java.util.*;

public class Source {
    public static void main(String[] args) {
        // TEST ZAD12
        /*
        Przykładowo dla tablicy T = [2, 3, 9, 5] i liczby x = 47 prawidłowa odpowiedź to {5, 9},
        natomiast dla liczby x = 8 prawidłowa odpowiedź to {2, 5},
         */
//        zad12 test12 = new zad12();
//        test12.arr = new int[]{2, 3, 9, 5};
//        System.out.println(Arrays.toString(test12.closesProduct(47)));
//        System.out.println(Arrays.toString(test12.closesProduct(8)));
//        ///////////////


        //zad13
//        zad13 t = new zad13();
//        int[] a = new int[]{9,8,7,6,5,4,3,2,1};
//        t.Sort(a);
//        System.out.println(Arrays.toString(a));


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
    int INF = Integer.MAX_VALUE; //nieskonczonosc

    class Graph {
        private int MAX_VERTS = 20;
        private int adjMat[][]; // macierz sąsiedztwa
        private int n; // bieżąca liczba osób

        // algos Floyd Warshall
        float averageLength() {
            int[][] pathDist = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (adjMat[i][j] == 0) {
                        if (i != j) {
                            pathDist[i][j] = INF;
                        } else {
                            pathDist[i][j] = 0;
                        }
                    } else {
                        pathDist[i][j] = 1;
                    }
                }
            }

            for (int k = 0; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (pathDist[i][k] + pathDist[k][j] < pathDist[i][j]) {
                            pathDist[i][j] = pathDist[i][k] + pathDist[k][j];
                        }
                    }
                }
            }

            float avg = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    avg += pathDist[i][j];
                }
            }

            return avg / (2 * n); //imo podzielic jeszcze przez to 2 bo jest nieskierowany
        }
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
    //to samo co zad5
}


/// zadania poprawkowe 2022.txt

/*
Zad. 1
1. Podaj  kod  potrzebnych operacji  kopca, wykorzystywanych do implementacji kolejki priorytetowej.
  2.. Zdefiniuj w Javie klasę o nazwie PQueue, implementującą kolejkę priorytetową typu MIN, zawierającą poniższe metody

            i.        void Insert( int x) – wstawia element  ‘x’ do kolejki

            ii.        int Min() – zwraca element i najmniejszym priorytecie

            iii.       int Delete_Min () – usuwa i zwraca element o najmniejszym priorytecie z kolejki

            iv.       void Construct( int a[n]) - twórz kolejkę z podanych elementów w tablicy a[]

 3.  Napisz metodę sortowania wykorzystującą taką kolejkę, podaj jej złożoność czasową i pamięciową.

 */

class zad13 {
    class PQueue {
        int[] a; //min heap
        int size;

        public PQueue(int size) {
            a = new int[size];
            this.size = size;
        }

        void swap(int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }

        void Insert(int x) {
            if (size < a.length) {
                int index = size;
                a[index] = x;
                while (index > 0 && a[(index - 1) / 2] > a[index]) {
                    swap(index, (index - 1) / 2);
                    index = (index - 1) / 2; //index rodzica
                }

                size++;
            }
        }

        int Min() {
            return a[0];
        }

        int deleteMin() {
            int temp = a[0];
            a[0] = a[--size];
            if (size != 0)
                DownHeap(0);

            return temp;
        }

        void Construct(int[] arr) {
            size = 0;
            a = new int[arr.length];
            for (int i : arr) {
                Insert(i);
            }
        }

        void DownHeap(int index) {
            int smalest = index;
            int left = 2 * index + 1;
            int right = 2 * index + 2;

            if (left < size && a[left] < a[smalest]) {
                smalest = left;
            }

            if (right < size && a[right] < a[smalest]) {
                smalest = right;
            }

            if (smalest != index) {
                swap(index, smalest);
                DownHeap(smalest);
            }
        }
    }

    void Sort(int arr[]) {
        PQueue q = new PQueue(0);
        q.Construct(arr);

        for (int i = 0; i < arr.length; i++) {
            arr[i] = q.deleteMin();
        }

    }

}



/*
Zad. 2 (10 pkt) 10:40-11:10Egzamin pisemny (Zadanie)
Dla zadanej tablicy a[LEN], korzystając z metody „dziel i zwyciężaj”, napisz  w Javie  rekurencyjną funkcję   int Inversion(int a[], int L, int R),
działającą w czasie O(n log n), która zwraca liczbę inwersji w tablicy a[n], czyli liczbę par elementów (a[i], a[j]), dla których   ( i < j  and a[i] > a[j] )

 */

class zad23 {
    void swap(int[] a, int j, int i) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    int Inversion(int[] a, int L, int R) { //nwm czy to ma jakikolwiek sens na oko zrobione
        if (R - L <= 0) {
            return 0;
        }
        int swaps = 0;
        int pivot = a[R];
        int i = L - 1;
        //robic partition lomuto i liczyc swapy
        for (int j = L; j < R; j++) {
            if (a[j] < pivot) {
                i++;
                swap(a, j, i);
                swaps++;
            }
        }
        if (i + 1 != R) {
            swap(a, i + 1, R);
            swaps++;
        }

        return swaps + Inversion(a, L, i) + Inversion(a, i + 2, R);
    }
}

/*
Zad. 3 (10 pkt) 11:05-11:40Egzamin pisemny (Zadanie)
Dany jest fragment klasy Tree drzewa  BST w Javie:
       class Node   {
                                  public int info;             // element danych (klucz)
                                  public Node left;          // lewy potomek węzła
                                  public Node right;        // prawy lewy potomek węzła
                        }

    class BSTree {
                         private  Node  root;
                         public Tree()  {   root = null; }            // na razie drzewo jest puste
                            …
                }     // koniec klasy Tree

 Napisz   w Javie iteracyjne metody:

(a)   Node predecessor( int x) - zwraca referencję do poprzednika (w sensie inorder) węzła o kluczu x   lub   null.
        Uwaga: metoda nie może korzystać z operacji parent()

(b)   int depth_node( int x ) - zwraca głębokość węzła o kluczu x  w drzewie

 */

class zad33 {
    class Node {
        public int info;             // element danych (klucz)
        public Node left;          // lewy potomek węzła
        public Node right;        // prawy lewy potomek węzła
    }

    class Tree {
        private Node root;

        public Tree() {
            root = null;
        }// na razie drzewo jest puste

        //INORDER :  LEFT  ROOT  RIGHT
        Node predecessor(int x) {
            Node cur = root;
            Node parent = null;

            while (cur != null) {
                if (cur.info == x) {
                    break;
                } else if (cur.info < x) {
                    cur = cur.right;
                } else {
                    cur = cur.left;
                }
                parent = cur;
            }

            if (cur == null) {
                return null;
            }

            if (cur.left != null) { //jesli ma lewe dziecko to idziemy do najbardziej po prawej tego dziecka bo bedzie obok w inorder
                cur = cur.left;
                while (cur.right != null) {
                    cur = cur.right;
                }
                return cur;
            } else { //jesli nie ma to zwracamy parenta bo bedzie po jego prawej stronie
                return parent; //jesli cur == root to i tak zwroci null wiec git
            }
        }

        int DepthNode(int x) {
            int depth = 0; //root jest na depth 0
            Node cur = root;

            while (cur != null) {
                if (cur.info == x) {
                    return depth;
                } else if (cur.info < x) {
                    cur = cur.right;
                } else {
                    cur = cur.left;
                }
                depth++;
            }

            return -1; //nie znaleziono
        }

    }

}


/*
Zad. 4 (10 pkt) 11:40-12:20Egzamin pisemny (Zadanie)
Dany jest graf G=(V, E), w którym V={a[0],..., a[n-1]} – zbiór różnych osób, E={{i, j}: osoba a[i] ma kontakt z osobą a[j]}.
Ścieżka kontaktów o długości k to ciąg osób (a[0],..., a[k]) taki, że dla  O <= i < k, a[i]  ma  a[i+1] na liście kontaktów,

Wykorzystując poniższą deklarację klasy grafu:
     infinity = +∞; { nieskończoność }
     class Graph {
                                public  int MAX_VERTS = 20;
                                public  int  adjMat[ ][ ]; // macierz sąsiedztwa
                                                                          // przy czym: adjMat[i, j]=   {1, gdy osoba i-ta ma j-tą na liście
                                                                          //kontaktów lub  0, wpp} .
                                public  int  n;            // bieżąca liczba osób

}

Zakładając, że dla i różnego od j istnieje ścieżka kontaktów osoby i do osoby j, napisz w Javie efektywną metodę
float average-length() - obliczającą średnią arytmetyczną długości najkrótszych ścieżek kontaktów pomiędzy
wszystkimi parami różnych osób.
Podaj złożoność czasową i pamięciową podanych rozwiązań i zwięźle uzasadnij.
 */

class zad43 {
    //to samo co zad5
}


/*
https://mordor.ksi.ii.uj.edu.pl/file/ii/sem_2/MP/Egzaminy/2013-2014/egzamin_2013_2014_4.jpg
Zadanie 4
Dany jest graf G-(V, E), w którym V zbiór wierzchołków, E- krawędź pomiędzy.
Napisz nierekurencyjne metody:
1. (8pkt) void mst(), która wypisze minimalną liczbę krawędzi, wymaganych do połączenia wszystkich wierzchołków w grafie.
2. (2pkt) int getAdjUnvisitedVertex(int v), która zwraca nieodwiedzony wierzchołek przyległy do v lub -1 w przeciwnym przypadku.
Oraz (3pkt) określ złożoność czasową i pamięciową podanych rozwiązań i zwięźle uzasadnij.
class Vertex
public char label; // etykieta (np. 'A')
public boolean wasVisited;
public Vertex(char lab) { // konstruktor label lab
wasVisited false;
// end class Vertex
class Graph (
    private final int MAX VERTS 20
    private Vertex vertexList(); // lista wierzchołków private int ad Mat]://tablica sąsiedztwa
    private int nVerts: // bieżąca liczba wierzchołków
public Graph() { // konstruktor
    vertextist = new Vertex(MAX_VERTS];
    // lista wierzchołków
    adjMate new int[MAX_VERTS][MAX VERTS]; // tablica sąsiedztwa
    nVerts-0
    for(int j=0; j<MAX VERTS; j++)
        for(int k=0; k<MAX_VERTS; k++)
            ad Mat[(k) = 0;
    }// end constructor
 */


class zad44 {
    class Vertex {
        public char label;
        public boolean wasVisited;

        public Vertex(char lab) {
            label = lab;
            wasVisited = false;
        }

    }

    class Graph {
        private final int MAXVERTS = 20;
        private Vertex[] vertexList; // lista wierzchołków
        private int adjMat[][]; //tablica sasiedztwa
        private int nVerts; //biezaca liczba wierzcholkow

        public Graph() { // konstruktor
            vertexList = new Vertex[MAXVERTS];
            adjMat = new int[MAXVERTS][MAXVERTS];
            nVerts = 0;
            for (int i = 0; i < MAXVERTS; i++) {
                for (int j = 0; j < MAXVERTS; j++) {
                    adjMat[i][j] = 0;
                }
            }
        }// end constructor


        public int getAdjUnvisitedVertex(int v) {
            if (v < nVerts) {
                for (int i = 0; i < nVerts; i++) {
                    if (adjMat[v][i] == 1) { //jesli jest w relacji z v
                        if (!vertexList[i].wasVisited) //jesli nie zostalo juz odwiedzone
                            return i;
                    }
                }
            }
            return -1;
        }

        //chyba trzeba znalezc ile jest rozlacznych podgrafow (jak liczba takich podgrafow bedzie n to zwracamy n - 1 bo tyle krawedzi potrzeba zeby je polaczyc)
        public void mst() {
            int count = 0; //ile podgrafow
            for (int i = 0; i < nVerts; i++) {
                if (!vertexList[i].wasVisited) { //jesli jeszcze nie odwiedzony to znaczy ze nalezy do sprawdzanego podgrafu
                    //ponizej algos bfs
                    Queue<Integer> q = new LinkedList<>();
                    vertexList[i].wasVisited = true;
                    q.add(i);

                    while (!q.isEmpty()) {
                        int cur = q.poll();

                        for (int j = 0;  j < nVerts; j++) {
                            if (adjMat[cur][j] == 1 && !vertexList[j].wasVisited) {
                                q.add(j); //dodaje sasiadow do queue
                                vertexList[j].wasVisited = true; // ustawia visited ze juz naleza do podgrafu spojnego
                            }
                        }
                    }
                    count++;
                }
            }

            //przywraca visited do defaultowej formy
            for (int i = 0; i < nVerts; i++) {
                vertexList[i].wasVisited = false;
            }

            System.out.printf("Trzeba dodac %d krawedzi\n", count);
        }


    }
}