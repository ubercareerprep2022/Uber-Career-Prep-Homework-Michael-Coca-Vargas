import datetime

class BinarySearchTree:
    def __init__(self) -> None:
        self.root = None

    def insert(self, key) -> None:
        current = self.root
        parent = self.root
        new_Node = self.Node(key, None, None)
        is_Left = False

        # if there is no root, new Node is the new root
        if (self.is_Root()):
            self.root = new_Node
            return

        while (current != None):
            parent = current
            current_Key = current.key

            # key is less than current key
            if (current_Key > key):
                current = current.left
                is_Left = True
            # key is greater than current key
            else:
                current = current.right
                is_Left = False

        if (is_Left):
            parent.left = new_Node
        else:
            parent.right = new_Node
    
    def find(self, key) -> bool:
        current = self.root

        while (current != None): 
            current_Key = current.key    
            if (key == current_Key):
                return True

            if (current_Key > key):
                current = current.left
            else:
                current = current.right

        return False

    def is_Root(self) -> bool:
        if (self.root == None):
            return True
        
        return False

    def print(self):
        BinarySearchTree.print_Nodes(self.root)
    
    def print_Nodes(node):
       # as long as current node is not null, keep traversing
        if node == None:
            return

        BinarySearchTree.print_Nodes(node.left)
        print(node.key)
        BinarySearchTree.print_Nodes(node.right)

    class Node:
        def __init__(self, key, left, right) -> None:
            self.key = key
            # Node to its left
            self.left = left
            # Node to its right
            self.right = right
        
            

class PhoneEntry:
    def __init__(self, name: str, phone_Number: float) -> None:
        self.name = name
        self.phone_Number = phone_Number
    
    
    def __eq__(self, other):
        if (self.name == other.name):
            return True
        
        return False

    def __lt__(self, other):
        if (self.name < other.name):
            return True
        
        return False

    def __gt__(self, other):
        if (self.name > other.name):
            return True
        
        return False
    

class ListPhoneBook:
    def __init__(self) -> None:
        self.phone_Book = []
    
    def size(self) -> int:
        return len(self.phone_Book)
    
    def insert(self, name: str, phone_Number: float) -> None:
        new_Entry = PhoneEntry(name, phone_Number)
        self.phone_Book.append(new_Entry)
        
    def find(self, name: str) -> int:
        for i in self.phone_Book:
            if i.name == name:
                return i.phone_Number
        
        return -1
        

class BinarySearchTreePhoneBook:
    def __init__(self) -> None:
        self.bst_Size = 0
        self.phone_Book = BinarySearchTree()
    
    def size(self) -> int:
        return self.bst_Size

    def insert(self, name: str, phone_Number: int) -> None:
        new_Entry = PhoneEntry(name, phone_Number)

        self.phone_Book.insert(new_Entry)

        self.bst_Size += 1
    
    def find(self, name: str) -> int:
        old_Entry = PhoneEntry(name, 0)
        return self.phone_Book.find(old_Entry)

if __name__ == "__main__":
    tree = BinarySearchTree()

    tree.insert(16)
    tree.insert(10)
    tree.insert(21)
    tree.insert(7)
    tree.insert(13)
    tree.insert(18)
    tree.insert(29)
    tree.insert(99)

    tree.print()
    
    print(tree.find(16))
    print(tree.find(2))
    print(tree.find(99))
    
    # List Phone Book
    #####################################################################################
    print("\nList Phone Book")

    ls_Phone_Book_Data = open("data.csv", "r")

    list_Phone_Book = ListPhoneBook()

    ls_Start = datetime.datetime.now()

    for line in ls_Phone_Book_Data:
        # splits the line of data into an array
        split = line.split(",")

        name = split[0]
        phone_Number = int(split[1])

        list_Phone_Book.insert(name, phone_Number)
    
    ls_End = datetime.datetime.now()

    ls_Difference = ls_End - ls_Start

    ls_Milliseconds = ls_Difference.total_seconds() * 1000

    print(f"Insert took {ls_Milliseconds} milliseconds.")
    print(f"The size of PhoneBook is {list_Phone_Book.size()}.")


    if (list_Phone_Book.size() != 1000000):
        exit(list_Phone_Book.size())

    ls_Phone_Book_Data.close()
    
    ls_Phone_Book_Data = open("search.txt", "r")

    ls_Start = datetime.datetime.now()

    ls_Find = 0
    for name in ls_Phone_Book_Data:
        was_Found = list_Phone_Book.find(name.rstrip())

        if (was_Found == -1):
            list_Phone_Book.find(name)
            exit("Was not found")
        
        ls_Find += 1
    
    ls_End = datetime.datetime.now()

    ls_Difference = ls_End - ls_Start

    ls_Milliseconds = ls_Difference.total_seconds() * 1000

    print(f"find() was called {ls_Find}.")
    print(f"Search took {ls_Milliseconds} milliseconds.")

    ls_Phone_Book_Data.close()
    

    # Binary Search Tree Phone Book
    #####################################################################################
    print("\nBinary Search Tree Phone Book")

    bst_Phone_Book_Data = open("data.csv", "r")

    bst_Phone_Book = BinarySearchTreePhoneBook()

    bst_Start = datetime.datetime.now()

    for line in bst_Phone_Book_Data:
        # splits the line of data into an array
        split = line.split(",")

        name = split[0]
        phone_Number = int(split[1])

        list_Phone_Book.insert(name, phone_Number)

    bst_End = datetime.datetime.now()

    bst_Difference = bst_End - bst_Start

    bst_Milliseconds = bst_Difference.total_seconds() * 1000

    print(f"Insert took {bst_Milliseconds} milliseconds.")
    print(f"The size of the PhoneBook is {bst_Phone_Book.size()}.")

    bst_Phone_Book_Data.close()

    bst_Phone_Book_Data = open("search.txt", "r")

    bst_Start = datetime.datetime.now()

    bst_Count = 0

    for name in bst_Phone_Book_Data:
        was_Found = bst_Phone_Book.find(name)

        if (was_Found == -1.0):
            bst_Phone_Book.find(name)
            exit("Was not found")
        
        bst_Count += 1

    bst_End = datetime.datetime.now()

    bst_Difference = bst_End - bst_Start

    bst_Milliseconds = bst_Difference.total_seconds() * 1000

    print(f"find() was called {bst_Count} times.")
    print(f"Search took {bst_Milliseconds} milliseconds.")

    bst_Phone_Book_Data.close()


'''
1.
List Phone Book
Insert took 1151.348 milliseconds.
The size of PhoneBook is 1000000.
find() was called 1000.
Search took 17940.143 milliseconds.

2.
Binary Search Tree Phone Book
Insert took 1160.693 milliseconds.
The size of the PhoneBook is 0.
find() was called 1000 times.
Search took 0.675 milliseconds.

3.
The difference between the two implementations
is the time complexity of the their methods,
the insert for list is faster because it is O(1) 
since it will always append to the list, and the insert
for the binary search tree is O(log(n)) which is slower
but still very good. THe biggest difference is in the 
search methods, the time complexity for list is O(n)
because the list is unsorted, and for the binary search tree
is O(log(n)) because when inserting, it is sorting it
and allows the search function to search and cut off halves 
of the tree after every node.
'''
