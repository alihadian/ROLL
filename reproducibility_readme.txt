================================================================================
Readme for reproducibility submission of paper ID 436

A) Source code info
Repository: https://github.com/alihadian/ROLL 
Programming Language: Java
Additional Programming Language info: Java SDk version 1.8
Compiler Info: OpenJDK version 1.8.0.24 for linux
Packages/Libraries Needed: Required packages are specified in pom.xml and can be generated using maven (install maven and run "mvn package" in the root folder)

B) Datasets info
Repository: N/A -- ROLL is a data generator itself!
Data generators: Barabasi-Albert graph data generator following the preferential attachment mechanism proposed in ROLL

C) Hardware Info
[Here you should include any details and comments about the used hardware in order to be able to accommodate the reproducibility effort. Any information about non-standard hardware should also be included. You should also include at least the following info:]
C1) Processor (architecture, type, and number of processors/sockets): Dual socket Intel(R) Xeon(R) CPU X5650 (6 cores per socket @ 2.67 GHz)
C2) Caches (number of levels, and size of each level): 32 KB L1 data cache, 32 KB L1 instruction cache, and 256 KB L2 cache per core, 12 MB L3 cache shared
C3) Memory (size and speed): 32MB DDR3-1066.
C4) Secondary Storage (type: SSD/HDD/other, size, performance: random read/sequnetial read/random write/sequnetial write): 1 TB HDD, 7200 RPM
C5) Network (if applicable: type and bandwidth): Ethernet 1Tbps

D) Experimentation Info
D1) Scripts and how-tos to generate all necessary data or locate datasets: 
	N/A -- ROLL is a graph generator and creates the data itself

D2) Scripts and how-tos to prepare the software for system
	After cloning the project from Github, cd into the project's root folder and simply run ./prepareSoftware.sh

D3) Scripts and how-tos for all experiments executed for the paper
	runExperiments.sh

================================================================================