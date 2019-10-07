////////////////// Node.cpp /////////////////////////
#include <iostream>
using namespace std;

template <class T>
class Node {
    T data;
    Node<T>* next;

   public:
    Node() : next(nullptr) {
    }

    ~Node() {
    }

    // Set Data
    void SetData(T _data) {
        data = _data;
    }

    // Get Data
    T& GetData() {
        return data;
    }

    // Set Next
    void SetNext(Node<T>* _next) {
        next = _next;
    }

    // Get Next
    Node<T>* GetNext() {
        return next;
    }
};

//////////////// LL.cpp /////////////////////////////

#include <iostream>
#include <stdexcept>
#include "Node.cpp"
using namespace std;

template <class T>
class LL {
    Node<int>* head;
    Node<int>* tail;
    int count;

   public:
    LL() : head(nullptr), tail(nullptr), count(0){};
    ~LL() {
        Node<T>* temp = head;
        while (head != nullptr) {
            temp = head;
            head = head->GetNext();
            delete temp;
        }
    };

    // Size
    int Size() {
        return count;
    }

    // Insert at End
    void AddAtEnd(T ele) {
        // Build Node
        Node<T>* temp = new Node<T>;
        temp->SetData(ele);
        temp->SetNext(nullptr);
        // If Empty
        if (IsEmpty()) {
            head = temp;
            tail = temp;
            count++;
        } else {
            tail->SetNext(temp);
            tail = temp;
            count++;
        }
    }

    // Remove from End
    T RemoveFromEnd() {
        Node<T>* trav = head;

        if (IsEmpty()) {
            throw runtime_error("Empty List!");
        }

        // Single Element Condition
        if (head == tail) {
            T ele = head->GetData();

            head = nullptr;
            tail = nullptr;
            delete trav;
            count--;
            return ele;
        }

        while (trav->GetNext() != tail) {
            trav = trav->GetNext();
        }

        trav->SetNext(nullptr);

        T ele = tail->GetData();
        delete tail;
        count--;

        tail = trav;
        return ele;
    }

    // Insert at Beginning
    void AddAtBegin(T ele) {
        Node<T>* temp = new Node<T>;
        temp->SetData(ele);
        temp->SetNext(head);
        head = temp;
        count++;
    }

    // Remove from Beginning
    T DelFromBegin() {
        Node<T>* temp = head;
        // Empty Condition
        if (head == nullptr && tail == nullptr) {
            throw runtime_error("Empty List!");
        }

        // Single Element Condition
        if (head == tail) {
            T ele = head->GetData();
            head = nullptr;
            tail = nullptr;
            delete temp;
            count--;
            return ele;
        }

        T ele = head->GetData();
        head = head->GetNext();
        delete temp;
        count--;
        return ele;
    }

    // is Empty
    bool IsEmpty() {
        return (head == nullptr && tail == nullptr);
    }

    // Display
    void Display() {
        if (IsEmpty()) {
            throw runtime_error("Empty List");
        }
        Node<T>* trav = head;
        cout << endl;
        while (trav != nullptr) {
            cout << trav->GetData() << "  -->  ";
            trav = trav->GetNext();
        }
        cout << "nullptr" << endl;
    }

    // Reverse
    void Reverse() {
        LL<T>* llr = new LL<T>;

        while (!(IsEmpty())) {
            llr->AddAtEnd(RemoveFromEnd());
        }

        head = llr->GetHead();
        tail = llr->GetTail();
        count = llr->Size();
    }

    // Get Head
    Node<T>* GetHead() {
        return head;
    }

    Node<T>* GetTail() {
        return tail;
    }

    // Insert at Position
    T& operator[](int index) {
        if (index > (Size())) {
            throw runtime_error("Out of Bounds");
        }
        // Single Element Condition
        if (index == 0) {
            Node<T>* temp = new Node<T>;
            temp->SetNext(head);
            head = temp;
            count++;
            return (head->GetData());
        }

        Node<T>* trav = head;
        while (index != 1) {
            trav = trav->GetNext();
            index--;
        }
        // Creating New Node
        Node<T>* temp = new Node<T>;
        // Linking
        temp->SetNext(trav->GetNext());
        trav->SetNext(temp);
        count++;
        return (temp->GetData());
    }

    // Remove From Index Position
    T delAtIndex(int index) {
        // Out of Bounds
        if ((index + 1) > Size()) {
            throw runtime_error("Out of Bounds");
        }
        // If Empty
        if (IsEmpty()) {
            throw runtime_error("Empty List");
        }

        // If Only One Element
        if (head == tail) {
            T ele = head->GetData();
            head = nullptr;
            tail = nullptr;
            count--;
            return ele;
        }

        // Index 0
        if (index == 0) {
            return DelFromBegin();
        }

        // Regular
        Node<T>* trav = head;
        while (index != 1) {
            trav = trav->GetNext();
            index--;
        }
        T ele = trav->GetNext()->GetData();
        Node<T>* temp = trav->GetNext();
        trav->SetNext(trav->GetNext()->GetNext());
        delete temp;
        count--;
        return ele;
    }
};