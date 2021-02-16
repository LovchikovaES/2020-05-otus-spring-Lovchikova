function getBookJson() {
  var book = {};
  var genre = {};
  var authors = [];
  if ($('#id').val() != '') {
    book.id = $('#id').val();
  }
  book.name = $('#name').val();

  if ($('#genre').val() != '') {
    genre.id = $('#genre').val();
    book.genre = genre;
  }

  $('select.authors').each(function() {
    var author = {};
    author.id = $(this).children("option:selected").val();
    authors.push(author);
  })

  book.authors = authors;

  var bookJSON = JSON.stringify(book);
  return bookJSON;
}

function removeAuthor(currentAuthor) {
  $(currentAuthor).closest('tr').remove();
}

function addAuthor(authorsSelectOption) {
  var authorRow = '<tr class="author"><td>';
  authorRow += authorsSelectOption;
  authorRow += '</td><td><button class="removeAuthor">Remove</button></td></tr>';
  $('#authors tbody').append(authorRow);
}