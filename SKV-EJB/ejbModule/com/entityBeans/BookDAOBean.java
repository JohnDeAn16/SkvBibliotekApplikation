package skv.lia.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.primefaces.event.*;


import skv.lia.entities.Book;
import skv.lia.remote.beans.BookDAOBeanRemote;

@Stateless
@Named
public class BookDAOBean implements BookDAOBeanRemote {

	@PersistenceContext(unitName="SKV-Persistence")
	EntityManager em;

	private Book selectedBook;
	private List<Book> selectedBooks;

	// Test list for books
	// Contains: id, name of author, isbn number, name of book, number of copies. 
	private static final ArrayList<Book> books = new ArrayList<Book>(Arrays.asList(
			new Book(1, "Adams, Douglas", "9789176890035", "Liftarens guide till galaxen", 1),
			new Book(2, "Jonasson, Jonas", "9789164232519", "Hundraåringen som klev ut genom fönstret och försvann", 2), 
			new Book(3, "Niemi, Mikael", "9113007734", "Populärmusik från Vittula", 3),
			new Book(4, "Berglin, Jan", "9170375747", "Bronto Berglin", 1),
			new Book(5, "Hearn, Lian", "9163826372", "Över näktergalens golv", 1)));
	
	@Inject
	public BookDAOBean(){}
	

	public ArrayList<Book> getBooks() {
		return books;
	}

	@Override
	public void addBook(Book e) {
		if (e.getId() != 0) {
			em.merge(e);
		} else {
			em.persist(e);
		}
	}
	
	// For user/admin to be able to create books, if necessary.
	@Override
	public Book createBook(Book book) {
		return null;
	}
	
	@Override
	public Book createBook(int id, String author, String isbn, String title, int copies) {
		Book newBook = new Book();
		newBook.setId(id);
		newBook.setAuthor(author);
		newBook.setIsbn(isbn);
		newBook.setTitle(title);
		newBook.setCopies(copies);
		em.persist(newBook);
		return newBook;
	}
	
	@Override
	public Book getBookById(int id) {
		Query q = em.createQuery("SELECT e FROM Book e WHERE e.id = :id");
		q.setParameter("id", id);
		return (Book) q.getSingleResult();
	}

	@Override
	public Book getBookByAuthor(String author) {
		Query q = em.createQuery("SELECT e FROM Book e WHERE e.author = :author");
		q.setParameter("author", author);
		return (Book) q.getSingleResult();
	}

	@Override
	public Book getBookByIsbn(String isbn) {
		Query q = em.createQuery("SELECT e FROM Book e WHERE e.isbn = :isbn");
		q.setParameter("isbn", isbn);
		return (Book) q.getSingleResult();
	}

	@Override
	public Book getBookByTitle(String title) {
		Query q = em.createQuery("SELECT e FROM Book e WHERE e.title = :title");
		q.setParameter("title", title);
		return (Book) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getAllBooks() {
		Query q = em.createQuery("SELECT e FROM Book e");
		return (List<Book>) q.getResultList();
	}

	public Book getSelectedBook() {
		return selectedBook;
	}

	public void setSelectedBook(Book selectedBook) {
		this.selectedBook = selectedBook;
	}

	public List<Book> getSelectedBooks() {
		return selectedBooks;
	}

	public void setSelectedBooks(List<Book> selectedBooks) {
		this.selectedBooks = selectedBooks;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Bok checkad");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowUnselect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage("Bok avcheckad");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}


}
