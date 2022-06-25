from collections import deque
from dataclasses import dataclass
from typing import List

class GraphNode:
    def __init__(self, data: int) -> None:
        self.data = data
    
class GraphWithAdjacencyList:
    def __init__(self):
        self.adjNodes = {}
    
    def addNode(self, key: int) -> None:
        self.adjNodes[key] = []

    def removeNode(self, key: int) -> None:
        del self.adjNodes[key]

    def addEdge(self, node1: int, node2: int) -> None:
        self.adjNodes[node1].append(node2)

    def removeEdge(self, node1: int, node2: int) -> None:
        self.adjNodes[node1].remove(node2)

    def getAdjNodes(self, key: int) -> list:
        return self.adjNodes[key]

    def DFS(self, key: int) -> None:
        stack = deque()

        visited = set()

        print(key.data)

        stack.append(key)

        visited.add(key)

        while (len(stack) != 0):
            curNode = stack[-1]

            neighAdjNode = None

            for node in self.adjNodes[curNode]:
                if node not in visited:
                    neighAdjNode = node
                    break
            
            if not neighAdjNode:
                stack.pop().data
                continue
            
            print(neighAdjNode.data)
            visited.add(neighAdjNode)
            stack.append(neighAdjNode)


    def BFS(self, key: int) -> None:
        queue = deque()

        visited = set()

        queue.append(key)

        visited.add(key)

        while (len(queue) != 0):
            curNode = queue.popleft()
            
            print(curNode.data)

            adjList = self.adjNodes[curNode]

            for node in adjList:
                if node not in visited:
                    visited.add(node)
                    queue.appendleft(node)


if __name__ == "__main__":
    adjGraph = GraphWithAdjacencyList()

    one = GraphNode(0)
    two = GraphNode(1)
    three = GraphNode(2)
    four = GraphNode(3)

    adjGraph.addNode(one)
    adjGraph.addNode(two)
    adjGraph.addNode(three)
    adjGraph.addNode(four)

    adjGraph.addEdge(one, two)
    adjGraph.addEdge(one, three)

    adjGraph.addEdge(three, one)
    adjGraph.addEdge(three, two)
    adjGraph.addEdge(three, four)

    adjGraph.addEdge(four, four)

    adjGraph.BFS(three)

    print()

    adjGraph.DFS(three)



