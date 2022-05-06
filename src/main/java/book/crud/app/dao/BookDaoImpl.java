package book.crud.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import book.crud.app.entities.Book;

public class BookDaoImpl implements BookDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int insertBook(Book book) {
		// `bookId`, `bookName`, `bookPrice`, `noOfCopies`, `inStock`,
		// `dateOfPublication`, `bookCover`
		String insertQuery = "INSERT INTO " + "book "
				+ "(bookName, bookPrice, noOfCopies, inStock, dateOfPublication, bookCover ) " + "VALUES (?,?,?,?,?,?)";

		return this.jdbcTemplate.update(insertQuery, book.getBookName(), book.getBookPrice(), book.getNoOfCopies(),
				book.isInStock(), book.getDateOfPublication(), book.getBookCover());
	}

	public List<Book> getListOfBooks() {
		String fetchAllSBooksQuery = "SELECT * FROM book";
		
		// RowMapper
		return this.jdbcTemplate.query(fetchAllSBooksQuery, new RowMapperImpl());
	}

	public int updateBook(Book book) {
		String updateQuery = "UPDATE book SET bookPrice = ?, noOfCopies = ?, "
				+ "inStock = ? WHERE bookId = ?";
		
		return this.jdbcTemplate.update(updateQuery, book.getBookPrice(), book.getNoOfCopies(),
				book.isInStock(), book.getBookId());
	}

	public int deleteBook(int bookId) {
		String deleteBookQuery = "DELETE FROM book WHERE bookId = ?";
		return this.jdbcTemplate.update(deleteBookQuery, bookId);
	}

	public Book getBook(int bookId) {
		String getSingleBook = "SELECT * from book WHERE bookId = ?";

		return this.jdbcTemplate.queryForObject(getSingleBook, new RowMapperImpl(), bookId);
	}

}
