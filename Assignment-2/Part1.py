from collections import deque

# Tree class with root field
class Tree:
    def __init__(self, root):
        self.root = root
    
    '''
    Traverses the tree in an inorder traversal
    using recursion
    '''
    def print(self, root):
        # as long as root is not null, keep traversing
        if root == None:
            return

        self.print(root.left)
        print(root.data)
        self.print(root.right)
    
    # Tree node class that holds data, left child, and right child fields
    class TreeNode:
        def __init__(self, data, left, right):
            self.data = data
            self.left = left
            self.right = right


class OrganizationStructure:
    class Employee:
        def __init__(self, name: str, title: str, directReports: list):
            self.name = name
            self.title = title
            self.directReports = directReports
        
        def setDirectReports(self, directReports: list):
            self.directReports = directReports

        def getAdjUnvisitedEmployee(self, visitedSet):
            for employee in self.directReports:
                if employee not in visitedSet:
                    return employee
            
            return None
        
        def __str__(self):
            return f"Name: {self.name}, Title: {self.title}"

    '''
    Traverses the organization structure using, a queue, and 
    prints the employees in spiral level order
    '''
    def printLevelByLevel(root):
        queue = deque()

        # to get started with the root employee
        queue.append(root)

        while (len(queue) != 0):
            employee = queue.popleft()
            print(employee)

            for underling in employee.directReports:
                queue.append(underling)
    
    '''
    Traverse the tree, using DFS and getting the max depth
    by checking the length of the stack
    '''
    def printNumLevels(root):
        visited = set()

        stack = deque()

        maxDepth = 0


        stack.append(root)

        while (len(stack) != 0):
            if (len(stack) > maxDepth):
                maxDepth = len(stack)
            
            topEmployee = stack[-1]

            underling = topEmployee.getAdjUnvisitedEmployee(visited)

            if (underling == None):
                stack.pop()
                continue
            
            visited.add(underling)
            stack.append(underling)
        
        print(maxDepth)


if __name__ == "__main__":
    leftChild =  Tree.TreeNode(6, None, None)
    rightChild = Tree.TreeNode(3, None, None)
    left = Tree.TreeNode(7, None, None)
    right = Tree.TreeNode(17, leftChild, rightChild)
    root = Tree.TreeNode(1, left, right)
    tree = Tree(root)

    tree.print(root)

    ################################################################################
    k = OrganizationStructure.Employee("K", "Sales Intern", [])

    j = OrganizationStructure.Employee("J", "Sales Representative", [k])
    f = OrganizationStructure.Employee("F", "Engineer", [])
    g = OrganizationStructure.Employee("G", "Engineer", [])
    h = OrganizationStructure.Employee("H", "Engineer", [])

    i = OrganizationStructure.Employee("I", "Director", [j])
    e = OrganizationStructure.Employee("E", "Manager", [])
    d = OrganizationStructure.Employee("D", "Manager", [f, g, h])

    b = OrganizationStructure.Employee("B", "CFO", [i])
    c = OrganizationStructure.Employee("C", "CTO", [d, e])

    a = OrganizationStructure.Employee("A", "CEO", [b, c])

    OrganizationStructure.printLevelByLevel(a)

    OrganizationStructure.printNumLevels(a)

    i.setDirectReports([])

    OrganizationStructure.printNumLevels(a)

