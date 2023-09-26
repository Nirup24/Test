import java.util.*;



//***Using Same Book as example in All Racks**//

public class GroupBooksByAuthor {
    public static Map<String, Collection<Book>> groupBooksByAuthor(Collection<Shelf> shelves) {
        Map<String, Collection<Book>> groupedBooks = new HashMap<>();

        for (Shelf shelf : shelves) {
            for (Rack rack : shelf.getRacks()) {
                for (Book book : rack.getBooks()) {
                    String author = book.getAuthor();

                    if (groupedBooks.containsKey(author)) {
                        groupedBooks.get(author).add(book);
                    } else {
                        Collection<Book> authorBooks = new ArrayList<>();
                        authorBooks.add(book);
                        groupedBooks.put(author, authorBooks);
                    }
                }
            }
        }

        return groupedBooks;
    }

    public static void main(String[] args) {
        Collection<Shelf> shelves = new ArrayList<>();
        loadBooksInShelves(shelves);

        // Get a book by name
        String bookNameToFind = "The Da Vinci Code";
        Book foundBook = getGroupBooks(shelves, bookNameToFind);

        if (foundBook != null) {
            System.out.println("Found Book: " + foundBook.getName());
            System.out.println("Author: " + foundBook.getAuthor());
            System.out.println("Location:");
            System.out.println("Shelf: (TODO: Implement shelf name retrieval)");
            System.out.println("Rack: (TODO: Implement rack name retrieval)");
        } else {
            System.out.println("Book not found.");
        }

        // Group books by author
        Map<String, Collection<Book>> groupedBooks = groupBooksByAuthor(shelves);
        System.out.println(groupedBooks);

        // CRUD operations examples
        Shelf newShelf = createShelf("New Shelf");
        shelves.add(newShelf);
        Rack newRack = createRack("New Rack");
        newShelf.getRacks().add(newRack);
        newRack.getBooks().add(new Book("New Book", "New Author"));

        // Update a book
        Book bookToUpdate = getGroupBooks(shelves, "The Da Vinci Code");
        if (bookToUpdate != null) {
            bookToUpdate.setName("Updated Book");
            bookToUpdate.setAuthor("Updated Author");
        }

        // Delete a book
        Rack rackToDeleteFrom = getRackByName(newShelf, "New Rack");
        if (rackToDeleteFrom != null) {
            rackToDeleteFrom.getBooks().remove(bookToUpdate);
        }
    }

  
    private static void loadBooksInShelves(Collection<Shelf> shelves) {
        Shelf shelf1 = new Shelf();
        Rack rack1 = new Rack("Rack 1");
        rack1.getBooks().addAll(
          Arrays.asList(new Book("To Kill a Mockingbird", "Harper Lee"), new Book("Go Set a Watchman", "Harper Lee")));
        shelves.add(shelf1);

        Shelf shelf2 = new Shelf();
        Rack rack2 = new Rack("Rack 2");
        rack2.getBooks().addAll(
          Arrays.asList(new Book("To Kill a Mockingbird", "Harper Lee"), new Book("Go Set a Watchman", "Harper Lee")));
        shelves.add(shelf2);

        Shelf shelf3 = new Shelf();
        Rack rack3 = new Rack("Rack 3");
        rack3.getBooks().addAll(
          Arrays.asList(new Book("To Kill a Mockingbird", "Harper Lee"), new Book("Go Set a Watchman", "Harper Lee")));
        shelves.add(shelf3);
      
        Shelf shelf4 = new Shelf();
        Rack rack4 = new Rack("Rack 4");
        rack4.getBooks().addAll(
          Arrays.asList(new Book("To Kill a Mockingbird", "Harper Lee"), new Book("Go Set a Watchman", "Harper Lee")));
        shelves.add(shelf4);

       Shelf shelf5 = new Shelf();
	   Rack rack5 = new Rack("Rack 5"); // Provide a valid rack name
       rack5.getBooks().addAll(Arrays.asList(new Book("To Kill a Mockingbird", "Harper Lee"), new Book("Go Set a Watchman", "Harper Lee")));
	   shelf5.getRacks().add(rack5);
       shelves.add(shelf5);
    }

    // CRUD Operations for Shelf
    static class Shelf {
        private String name;
        private Collection<Rack> racks = new ArrayList<>();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Collection<Rack> getRacks() {
            return racks;
        }
    }

    public static Shelf createShelf(String name) {
        Shelf newShelf = new Shelf();
        newShelf.setName(name);
        return newShelf;
    }

    // Find a shelf by name
    public static Shelf getShelfByName(Collection<Shelf> shelves, String name) {
        for (Shelf shelf : shelves) {
            if (shelf.getName().equals(name)) {
                return shelf;
            }
        }
        return null; // Shelf not found
    }

    public static void updateShelf(Shelf shelf, String newName) {
        shelf.setName(newName);
    }

    public static void deleteShelf(Collection<Shelf> shelves, Shelf shelf) {
        shelves.remove(shelf);
    }

    // CRUD Operations for Rack
    static class Rack {
        private String name;
        private Collection<Book> books = new ArrayList<>();

        public Rack(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        // Add setName method to set the rack name
        public void setName(String name) {
            this.name = name;
        }

        public Collection<Book> getBooks() {
            return books;
        }
    }

    public static Rack createRack(String name) {
        Rack newRack = new Rack(name);
        return newRack;
    }

    // Find a rack by name
    public static Rack getRackByName(Shelf shelf, String name) {
        for (Rack rack : shelf.getRacks()) {
            if (rack.getName().equals(name)) {
                return rack;
            }
        }
        return null; // Rack not found
    }

    public static void updateRack(Rack rack, String newName) {
        rack.setName(newName);
    }

    public static void deleteRack(Shelf shelf, Rack rack) {
        shelf.getRacks().remove(rack);
    }

    // CRUD Operations for Book
    static class Book {
        private String name;
        private String author;
        private String shelfName;
        private String rackName;

        public Book(String name, String author) {
            this.name = name;
            this.author = author;
        }

        public String getName() {
            return name;
        }

        public String getAuthor() {
            return author;
        }

        public String getShelfName() {
            return shelfName;
        }

        public void setShelfName(String shelfName) {
            this.shelfName = shelfName;
        }

        public String getRackName() {
            return rackName;
        }

        public void setRackName(String rackName) {
            this.rackName = rackName;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        @Override
        public String toString() {
            return "Book{" + "name='" + name + '\'' + '}';
        }
    }

    // Method to get a book by name, navigating from rack to shelf to book
    public static Book getGroupBooks(Collection<Shelf> shelves, String bookName) {
        for (Shelf shelf : shelves) {
            for (Rack rack : shelf.getRacks()) {
                for (Book book : rack.getBooks()) {
                    if (book.getName().equals(bookName)) {
                        book.setShelfName(shelf.getName()); // Set shelf name
                        book.setRackName(rack.getName());   // Set rack name
                        return book;
                    }
                }
            }
        }
        return null; // Book not found
    }
}
