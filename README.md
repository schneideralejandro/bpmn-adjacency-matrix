# bpmn-adjacency-matrix
Module used for the derivation of an adjacency matrix from a BPMN model that uses the [JBPT](https://github.com/jbpt/codebase "JBPT project") code library.

This module supports:

1. Derivation of the adjacency matrix of an existing model.
2. Determination of the existence of a control flow between two flow nodes using the adjacency matrix.
3. Printing the adjacency matrix on screen.

This module DOES NOT support:

1. Generation of a BPMN model from an existing adjacency matrix.
2. Persistency of adjacency matrices -e.g., using JSON files-.

This project was built using [Maven](https://maven.apache.org/ "Maven"). To run it, you need to:

1. Download and install Maven.
2. Download the JBPT code library.
3. Perform an ```mvn clean install``` at JBPT's parent directory -the one that contains all the modules-. This installs the JBPT code library into the local maven repository.
