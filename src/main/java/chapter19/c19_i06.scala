package chapter19

/**
 * 19.6 반공변성
 * 
 * 납득하기 힘들겠지만, 반공변이 자연스러운 경우도 있다.
 * 
 * OutputChannel이 T에 대해 반공변이라고 정의했다. 따라서 AnyRef의 출력 채널은
 * String을 출력하는 채널의 서브타입이다. 쉽게말하자면, OutputChannel[String]
 * 이 필요한 곳에는 OutputChannel[AnyRef]를 바꿔넣어도 문제가 없는 것이다.
 * 
 * 왜냐면 결국 같은 write 연산을 제공하기 때문이다. 그리고 OutputChannel[AnyRef]
 * 에 더 적은 것을 요구하는데, 이는 제약이 적다는 뜻이다. String은 구체적인 String이 요구
 * 되어버린다.
 * 
 * 이를 일컬어 리스코프 치환 원칙(Liskov Substitution principle)이라 한다.
 * 
 * 한 타입 안에서 공변성과 반공변성이 함께 섞여 있는 경우도 있다. 스칼라의 트레이트가 그러하다.
 * 함수타입 A => B 를 스칼라는 Function[A, B]로 바꾼다.
 * 
 * 이는 리스코프 치환 원칙을 만족하는데, 인자는 함수가 요구하는 것이고, 결과는 함수가 제공하는
 * 것이기 때문이다.
 */
trait OutputChannel[-T] {
  def write(x: T)
}
trait Function1[-S, +T] {
  def apply(x: S): T
}
class Publication(val title: String)
class Book(title: String) extends Publication(title)

object Library {
  val books: Set[Book] = {
    Set (
      new Book("Programming in Scala"),
      new Book("Walden")
    )
  }
  def printBookList(info: Book => AnyRef) {
    for (book <- books) println(info(book))
  }
}
object c19_i06 extends App {
  /*
   * 함수 getTitle의 결과 타입 String이 printBookList가 받는
   * info 함수인자의 결과 타입인 AnyRef의 서브타입임에도 불구하고 타입 검사를 통과한다.
   * 
   * apply(x: S): T 에서 -S, +T 이므로
   * 
   * Publication => String 가 인풋 타입일 때 각각
   *     ||           ||
   *     \/           \/
   *    Book     => AnyRef 반공변/공변에 의해 변환이 가능해진다.
   * 
   */
  def getTitle(p: Publication): String = p.title
  Library.printBookList(getTitle) 
  // 함수 리터럴로도 날려봤다.
  Library.printBookList((p: Publication) => p.title) 
}