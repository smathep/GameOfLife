compile:
	javac -d bin src/edu/smathers/gol/view/*.java

run:
	java -ea bin\GameOfLife

clean:
	rm src/edu/smathers/gol/view/*.class