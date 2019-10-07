//-------------------TreeNode.cpp________________________
#include <iostream>
using namespace std;

template <class T>
class TreeNode {
    T data;
    TreeNode<T>* left;
    TreeNode<T>* right;

   public:
    TreeNode() {
    }
    ~TreeNode() {
    }

    void SetData(T _data) {
        data = _data;
    }

    T GetData() {
        return data;
    }

    void SetLeft(TreeNode<T>* _left) {
        left = _left;
    }

    TreeNode<T>* GetLeft() {
        return left;
    }

    void SetRight(TreeNode<T>* _right) {
        right = _right;
    }

    TreeNode<T>* GetRight() {
        return right;
    }
};

//____________________BST.cpp____________________________
#include <iostream>
#include "TreeNode.cpp"
using namespace std;

template <class T>
class BST {
    TreeNode<T>* root;

   public:
    BST() {
        root = nullptr;
    }

    ~BST() {
        Delete(root);
    }

    // Delete
    void Delete(TreeNode<T>* temp) {
        if (temp != nullptr) {
            Delete(temp->GetLeft());
            Delete(temp->GetRight());
        }
        delete temp;
    }
    // Insert
    void Insert(T _newData) {
        /*
            if Empty
                Just Insert
            if Not Empty
                Compare with the Node current Node
                    Recurse Left
                else
                    Recurse Right
        */
        TreeNode<T>* temp = new TreeNode<T>;
        temp->SetData(_newData);
        temp->SetLeft(nullptr);
        temp->SetRight(nullptr);

        if (root == nullptr) {
            root = temp;
        } else {
            TreeNode<T>* current = root;
            while (current != nullptr) {
                if (_newData == current->GetData()) {
                    cout << "Duplicate Values" << endl;
                }

                if (_newData < current->GetData()) {
                    if (current->GetLeft() == nullptr) {
                        current->SetLeft(temp);
                        return;
                    } else {
                        current = current->GetLeft();
                    }
                } else {
                    if (current->GetRight() == nullptr) {
                        current->SetRight(temp);
                        return;
                    } else {
                        current = current->GetRight();
                    }
                }
            }
        }
    }

    // In Order
    static void InOrder(TreeNode<T>* temp) {
        if (temp != nullptr) {
            InOrder(temp->GetLeft());
            cout << temp->GetData() << "\t";
            InOrder(temp->GetRight());
        }
    }

    // Pre Order
    static void PreOrder(TreeNode<T>* temp) {
        if (temp != nullptr) {
            cout << temp->GetData();
            cout << "\t";
            PreOrder(temp->GetLeft());
            PreOrder(temp->GetRight());
        }
    }

    // Post Order
    static void PostOrder(TreeNode<T>* temp) {
        if (temp != nullptr) {
            PostOrder(temp->GetLeft());
            PostOrder(temp->GetRight());
            cout << temp->GetData();
            cout << "\t";
        }
    }

    // Get Root Node
    TreeNode<T>* GetRoot() {
        return root;
    }

    // Search
    bool Search(T _val) {
        TreeNode<T>* current = root;

        while (current != nullptr) {
            if (_val == current->GetData()) {
                return true;
            } else if (_val < current->GetData()) {
                current = current->GetLeft();
            } else {
                current = current->GetRight();
            }
        }

        return false;
    }

    // Find Min
    static T FindMin(TreeNode<T>* _node) {
        TreeNode<T>* current = _node;

        while (current != nullptr) {
            if (current->GetLeft() != nullptr) {
                current = current->GetLeft();
            } else {
                break;
            }
        }

        T min = current->GetData();
        return min;
    }

    // Find Max
    static T FindMax(TreeNode<T>* _node) {
        TreeNode<T>* current = _node;

        while (current != nullptr) {
            if (current->GetRight() != nullptr) {
                current = current->GetRight();
            } else {
                break;
            }
        }

        T max = current->GetData();
        return max;
    }

    // Height of the Tree
    static int HeightOfTree(TreeNode<T>* _node) {
        int l = 0;
        int r = 0;

        if (_node == nullptr)
            return 0;

        l = HeightOfTree(_node->GetLeft());
        r = HeightOfTree(_node->GetRight());

        return (l >= r) ? (l + 1) : (r + 1);
    }
};
