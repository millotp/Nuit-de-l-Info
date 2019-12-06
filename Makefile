.Phony: all build

all: build run

build:
	javac -cp "lib/slick/lib/*" -sourcepath src -d bin src/main/Main.java
	
run:
	java -cp "lib/slick/lib/*":bin -Djava.library.path=lib/slick/natives main.Main


