Steps to generate dependency graph as PNG

1- you need to have graphviz installed on your system

From here - https://graphviz.org/

    brew reinstall graphviz

2- Command to run in root dir ( https://github.com/ferstl/depgraph-maven-plugin ) 

    mvn com.github.ferstl:depgraph-maven-plugin:aggregate -DcreateImage=true -DreduceEdges=false -Dscope=compile "-Dincludes=com.bharat.food.ordering.system*:*"