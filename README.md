# Triangle-Graph-Count
Data Stream algorithm for calculating number of triangles in a graph. Both algorithms are sampling algorithms that handle edges differently after the user's passed in memory size is exceed.
Base algorithm - sampling algorithm that randomly adds and removes edges once memory storage is taken up. It then calculates the number of triangles incident on the inputted edge and adds to the total count.
Improved algorithm - sampling algorithm that adds the number of trianlges incident on the inputted edge and adds to the total count. It then randomly considers if the edge should be added to the graph.
How to run:
for base algorithm pass in:
-b memory_size file-path
for improved algorithm pass in:
-i memory_size file-path
for more help pass in -h
