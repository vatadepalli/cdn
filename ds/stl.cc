///////////////////// MAP /////////////////////
#include <iostream>
#include <map>
#include <set>
#include <string>
using namespace std;

int main() {
    //Map test
    map<int, string> mp;
    char ch;
    do {
        int no;
        string name;
        cout << "\n Enter  Roll NO:";
        cin >> no;
        cin.get();
        cout << "\n Enter Name:";
        getline(cin, name);
        mp[no] = name;
        cout << "\n do you want to continue (y or n):";
        cin >> ch;

    } while (ch != 'n');

    //Traversing map

    cout << "\n Values from map";
    map<int, string>::iterator it = mp.begin();
    while (it != mp.end()) {
        cout << "\n RNO=" << it->first << "\t"
             << "Name=" << it->second;
        it++;
    }
    //Seraching in map

    int rno;
    cout << "\n Enter roll name whose name is to be found";
    cin >> rno;
    string name;
    if (mp.count(rno)) {
        name = mp[rno];
        cout << "\n Name of student:" << name << endl;
    } else {
        cout << "\n Invalid Roll no" << endl;
    }

    //Erase from map

    if (mp.count(rno)) {
        mp.erase(rno);
    } else {
        cout << "\n Invalid Roll no" << endl;
    }

    it = mp.begin();
    while (it != mp.end()) {
        cout << "\n RNO=" << it->first << "\t"
             << "Name=" << it->second;
        it++;
    }
    //Seraching in map
    //find
    //mp.find(rno); returns iterator to element
    return 0;
}

///////////////////// SET /////////////////////

#include <iostream>
#include <set>
#include <string>
using namespace std;

int main() {
    set<string> st;
    char ch;

    do {
        string name;
        cout << "\n Enter Name:";
        getline(cin, name);
        st.insert(name);
        cout << "\n Do u want to continue(y or n):";
        cin >> ch;
        cin.get();
    } while (ch != 'n');

    //	set<string> st1(vct.begin(),vct.end());
    //	st1.insert(vct.begin(),vct.end());

    //Traversing
    cout << "\n Listing set";
    set<string>::iterator it = st.begin();
    while (it != st.end()) {
        cout << "\n"
             << *it;
        it++;
    }
    //find
    //
    string str;
    cout << "\n Value to be changed:";
    getline(cin, str);
    it = st.find(str);
    cout << "\n Value found:" << *it;

    //find
    //st.count(name);

    return 0;
}

///////////////////// STACK /////////////////////
#include <iostream>
#include <stack>
using namespace std;
int main() {
    stack<int> stk;
    char ch;
    do {
        int no;
        cout << "\n Enter NO:" << endl;
        cin >> no;
        stk.push(no);
        cout << "\n do you want to continue(y or n)";
        cin >> ch;

    } while (ch != 'n');

    cout << "\n Printing stack elements" << endl;
    while (!stk.empty()) {
        cout << "\n"
             << stk.top();
        stk.pop();
    }
    return 0;
}

///////////////////// VECTOR /////////////////////

#include <deque>
#include <iostream>
#include <list>
#include <string>
#include <vector>
using namespace std;

int main() {
    //for int vector
    /*int no;
	vector<int> obj;
	cout<<"\n Enter a number and press 0 to stop"<<endl;
	cin>>no;
	while(no!=0)
	{
		obj.push_back(no);
		cin>>no;
	}

	vector<int>::iterator it= obj.begin();
	int count=0;

	while(count < obj.capacity())
	{
	 no=*it;
	 cout<<"\n"<<no;
	 ++it;
	 count++;
	}*/
    //for stings vector
    char ch;
    vector<string> obj;
    cout << "\n Capacity=" << obj.capacity();
    cout << "\n Capacity=" << obj.max_size();
    do {
        string str;
        cout << "\n Enter string" << endl;
        getline(cin, str);
        obj.push_back(str);
        cout << "\n Do u want to continue (y or n)" << endl;
        cin >> ch;
        cin.get();
    } while (ch != 'n');

    /*
	
	cout<<"\n Capacity="<<obj.capacity();
	vector<string>::iterator first = obj.begin();
      	vector<string>::iterator last = obj.end();
*/
    /*	while(first !=last)
	{
	 cout<<"\n"<<*first++<<endl;
	}
*/
    /*	for(int i=0;i<obj.size();i++)
	{	
	cout<<"\n"<<obj[i];
	}
*/

    //Vector intialiased
    /*vector<string> vct(10,"praphul"); 
	if(vct.empty())
	{
	cout<<"\n Not empty";
	}
	for(int i=0;i<vct.size();i++)
	{	
	cout<<"\n"<<vct[i];
	}*/
    //copy from one vct to other
    /*	vector<string> copy(obj);
	for(int i=0;i<obj.size();i++)
	{	
	cout<<"\n"<<obj[i];
	}

*/
    vector<string>::iterator mid = obj.begin() + obj.size() / 2;
    vector<string>::iterator start = obj.begin();
    vector<string>::iterator last = obj.end();

    cout << "\n List contents" << endl;
    list<string> ll(start, mid);
    list<string>::iterator listbegin = ll.begin();
    while (listbegin != ll.end()) {
        cout << "\n"
             << *listbegin++;
    }

    cout << "\n Deque contents" << endl;
    deque<string> dq(mid, last);
    deque<string>::iterator dqbegin = dq.begin();
    while (dqbegin != dq.end()) {
        cout << "\n"
             << *dqbegin++;
    }

    //dq.clear();
    //erase(begin)
    //dq.erase(begi,end);
    //
    //find in list
    //list<string>::iterator iter = find(ll.begin(),ll.end(),"abx");
    //ll.erase(iter);
    //
    //Insert in lis
    //ll.insert(itr,"KKKK");
    return 0;
}
