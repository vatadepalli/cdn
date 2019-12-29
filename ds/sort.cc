// ----------------SELECTION----------------

#include <iostream>
using namespace std;

template <class T>
void swapElements(T &x, T &y) {
    T temp = x;
    x = y;
    y = temp;
}

template <class T>
void printArray(T arr[], int size) {
    for (int i = 0; i < size; i++) {
        cout << arr[i] << ", ";
    }
    cout << endl;
}

template <class T>
void selectionSort(T arr[], int n) {
    int minIndex;

    for (int i = 0; i < n - 1; i++) {
        // Find the minimum element
        minIndex = i;
        for (int j = i + 1; j < n; j++)
            if (arr[j] < arr[minIndex]) {
                minIndex = j;
            }

        // Swap the minimum element with the first element of array
        swap(arr[minIndex], arr[i]);
    }
}

int main() {
    int arr[10] = {69, 25, 5, 22, 11, 55, 33, 43, 88, 2};
    selectionSort(arr, 10);
    cout << "Selection sorted array:" << endl;
    printArray(arr, 10);
    return 0;
}

// -------------------BUBBLE----------------

#include <iostream>
using namespace std;

template <class T>
void printArray(T arr[], int size) {
    for (int i = 0; i < size; i++) {
        cout << arr[i] << ", ";
    }
    cout << endl;
}

template <class T>
void bubbleSort(T arr[], int n) {
    for (int i = 0; i < n - 1; i++)
        // Last i elements are already in place
        for (int j = 0; j < n - i - 1; j++)
            if (arr[j] > arr[j + 1])
                swap(arr[j], arr[j + 1]);
}

int main() {
    int arr[10] = {69, 25, 5, 22, 11, 55, 33, 43, 88, 2};
    bubbleSort(arr, 10);
    cout << "Bubble Sorted array: \n";
    printArray(arr, 10);
    return 0;
}

// --------------------HEAP---------------
#include <iostream>
using namespace std;

template <class T>
void printArray(T arr[], int size) {
    for (int i = 0; i < size; i++) {
        cout << arr[i] << ", ";
    }
    cout << endl;
}

// To heapify a subtree rooted with node i which is an index in arr[].
template <class T>
void heapify(T arr[], int n, int i) {
    int largest = i;    // Initialize largest as root
    int l = 2 * i + 1;  // left = 2*i + 1
    int r = 2 * i + 2;  // right = 2*i + 2

    // If left child is larger than root
    if (l < n && arr[l] > arr[largest])
        largest = l;

    // If right child is larger than largest so far
    if (r < n && arr[r] > arr[largest])
        largest = r;

    // If largest is not root
    if (largest != i) {
        swap(arr[i], arr[largest]);

        // Recursively heapify the affected sub-tree
        heapify(arr, n, largest);
    }
}

template <class T>
void heapSort(T arr[], int n) {
    // Build heap (rearrange array)
    for (int i = n / 2 - 1; i >= 0; i--) {
        heapify(arr, n, i);
    }

    // Extract an element one by one from heap
    for (int i = n - 1; i >= 0; i--) {
        // Move current root to end
        swap(arr[0], arr[i]);

        // call max heapify on the reduced heap
        heapify(arr, i, 0);
    }
}

int main() {
    int arr[10] = {69, 25, 5, 22, 11, 55, 33, 43, 88, 2};
    heapSort(arr, 10);
    cout << "Heap sorted array:" << endl;
    printArray(arr, 10);
    return 0;
}

// ---------------------MERGE--------------

#include <iostream>
using namespace std;

template <class T>
void printArray(T arr[], int size) {
    for (int i = 0; i < size; i++) {
        cout << arr[i] << ", ";
    }
    cout << endl;
}

//Merge sub-array
template <class T>
void merge(T arr[], int l, int m, int r) {
    int n1 = m - l + 1;
    int n2 = r - m;

    /* create two arrays */
    int left[n1], right[n2];

    /* Copy data to temp arrays left[] and right[] */
    for (i = 0; i < n1; i++)
        left[i] = arr[l + i];
    for (j = 0; j < n2; j++)
        right[j] = arr[m + 1 + j];

    /* Merge the temp arrays back into arr[l..r]*/
    int i = 0;  // Start index of first subarray
    int j = 0;  // Start index of second subarray
    int k = l;  // Start index of merged subarray
    while (i < n1 && j < n2) {
        if (left[i] <= right[j]) {
            arr[k] = left[i];
            i++;
        } else {
            arr[k] = right[j];
            j++;
        }
        k++;
    }

    /* Copy the remaining elements of left[]*/
    while (i < n1) {
        arr[k] = left[i];
        i++;
        k++;
    }

    /* Copy the remaining elements of right[]*/
    while (j < n2) {
        arr[k] = right[j];
        j++;
        k++;
    }
}

/* l is for left index and r is right index */
template <class T>
void mergeSort(T arr[], int l, int r) {
    if (l < r) {
        //Find out mid
        int m = l + (r - l) / 2;

        // Sort first and second halves
        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);

        merge(arr, l, m, r);
    }
}

int main() {
    int arr[10] = {69, 25, 5, 22, 11, 55, 33, 43, 88, 2};
    mergeSort(arr, 0, 10 - 1);
    cout << "Heap sorted array:" << endl;
    printArray(arr, 10);
    return 0;
}

// --------------------QUICK-------------------

#include <iostream>
using namespace std;

template <class T>
void printArray(T arr[], int size) {
    for (int i = 0; i < size; i++) {
        cout << arr[i] << ", ";
    }
    cout << endl;
}

/* Steps as below
* Take last element as pivot
* place pivot element at its correct position in sorted array
* place all smaller to left of pivot and all greater elements to right of pivot 
*/
template <class T>
int partition(T arr[], int low, int high) {
    int pivot = arr[high];  // pivot
    int i = (low - 1);      // Index of smaller element

    for (int j = low; j <= high - 1; j++) {
        // If current element is smaller than the pivot
        if (arr[j] < pivot) {
            i++;  // increment index of smaller element
            swap(arr[i], arr[j]);
        }
    }
    swap(arr[i + 1], arr[high]);
    return (i + 1);
}

//low is start index and high is end index
template <class T>
void quickSort(T arr[], int low, int high) {
    if (low < high) {
        /* pi is partitioning index*/
        int pi = partition(arr, low, high);

        // Separately sort elements before partition and after partition
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}

int main() {
    int arr[10] = {69, 25, 5, 22, 11, 55, 33, 43, 88, 2};
    quickSort(arr, 0, 10 - 1);
    cout << "Heap sorted array:" << endl;
    printArray(arr, 10);
    return 0;
}