package skv.lia.remote.beans;

import java.util.List;

import javax.ejb.Remote;

import skv.lia.entities.Book;

@Remote
public interface BookDAOBeanRemote {

	public void addBook(Book e);
	
	public Book createBook(Book book);

	public Book createBook(int id, String author, String title, String isbn, int copies);

	public Book getBookById(int id);
	public Book getBookByAuthor(String author);
	public Book getBookByTitle(String title);
	public Book getBookByIsbn(String isbn);
	public Book getSelectedBook();
	public List<Book> getSelectedBooks();
	
	public ArrayList<Book> getBooks();
	public List<Book> getAllBooks();
}
