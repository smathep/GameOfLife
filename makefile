compile:
	javac -d bin src/edu/smathers/gol/view/*.java

run:
	java -cp bin GameOfLife

clean:
	rm bin/*.class