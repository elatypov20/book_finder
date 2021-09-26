FILES=src/Book.java src/LibraryDatabase.java \
		src/UserInterface.java src/Main.java

all:
	mkdir -p out/
	javac $(FILES) -d out/

run:
	@cd out && java Main

clean:
	rm -rf out