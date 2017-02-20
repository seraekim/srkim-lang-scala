package chapter23

/**
 * 23.3 for 식으로 질의하기
 * 
 * for 표기법은 기본적으로 데이터베이스 질의 언어의 공통 연산들과 동등하다.
 */
case class Book(title: String, authors: String*)
class BookList {
  val books: List[Book] = {
    List(
      Book(
        "Structure and Interpretation of Computer Programs",
        "Abelson, Harold", "Sussman, Gerald J."
      ),
      Book(
        "Principles of Compiler Design",
        "Aho, Alfred", "Ullman, Jeffrey"
      ),
      Book(
        "Programming in Modula-2",
        "Wirth, Niklaus"
      ),
      Book(
        "Elements of ML Programming",
        "Ullman, Jeffrey"
      ),
      Book(
        "The Java Language Specification", "Gosling, James",
        "Joy, Bill", "Steele, Guy", "Bracha, Gilad"
      )
    )
  }
}
object c23_i03 extends App {
  val books = new BookList().books;
  /*
   * 작가의 성이 Gosling인 모든 책의 제목을 찾는다면..
   */
  var b = for (b<- books; a <- b.authors
               if a startsWith "Gosling")
  yield b.title
  println(b)
  
  /*
   * 제목에 Program이라는 문자열이 들어간 모든 책의 제목은...
   */
  b = for (b <- books if ( b.title indexOf "Program") >= 0)
  yield b.title
  println(b)
  
  /*
   * 최소한 두 권 이상의 책을 쓴 작가를 모두 찾는 것은?
   */
  b = for (b1 <- books; b2 <- books if b1 != b2;
           a1 <- b1.authors; a2 <- b2.authors if a1 == a2)
      yield a1
  println(b)
  
  // 중복을 제거 하자면..
  def removeDuplicates[A](xs: List[A]): List[A] = {
    if (xs.isEmpty) xs
    else
      xs.head :: removeDuplicates(
        //xs.tail filter (x => x != xs.head)
        for (x <- xs.tail if x != xs.head) yield x
      )
  }
  b = removeDuplicates(b)
  println(b)
  
  //for(b1 <- books; b2 <- books if b1 != b2)
  //  println("b1 : "+b1+", b2 : "+b2)

}