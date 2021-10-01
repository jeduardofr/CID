JFLAGS = -g
JC = javac
JVM = java
FILE =

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

default: build-main

SLR_MAIN = slr/Main
SLR_MAIN_CLASSES = \
	slr/SLR.java \
	slr/Main.java

build-main: $(SLR_MAIN_CLASSES:.java=.class)

run-main: $(SLR_MAIN).class
	$(JVM) $(SLR_MAIN) $(FILE)
