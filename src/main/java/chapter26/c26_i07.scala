package chapter26


/**
 * 26.7 정규 표현식
 * 
 * 익스트랙터를 유용하게 쓸 수 있는 구체적인 분야로 정규 표현식이 있다. 자바와 같은 정규식을 제공하나
 * 익스트랙터를 사용하면 정규 표현식을 훨씬 더 멋지게 사용할 수 있다.
 * 
 * 1. 정규 표현식 만들기
 * 
 * 스칼라는 자바에서, 자바는 다시 대부분의 기능을 펄(Perl)에서 따왔다.
 * 
 * ab?          'a'가 하나 있고, 그 뒤에 'b'가 하나 있을 수도 있다. 즉, 'a' or 'ab'
 * \d+          \d는 숫자 0~9라는 뜻. 하나 이상의 숫자로 구성된 문자열을 의미.
 * [a-dA-D]\w*  a부터 d까지의 대문자나 소문자로 시작하는 단어를 의미. \w는 단어를 이루는 문자를
 *              의미하며, *는 0개 이상의 반복을 의미한다.
 * (-)?(\d+)(\.\d*)?   맨 앞에 음수 부호가 있을 수 있으며, 그 뒤에 1개 이상의 숫자가 필수적으로 있고
 *                     그 뒤에 선택적으로 소수점과 0개 이상의 숫자가 오는 문자열을 의미한다.
 *                     3개의 그룹으로 표현된 것을 볼 수 있다. 그룹은 괄호로 표현.
 * 
 */
object c26_i07 extends App {
  import scala.util.matching.Regex
  val Decimal = new Regex("(-)?(\\d+)(\\.\\d*)?")
  /*
   * 이스케이프 역슬래시 문자가 너무 많으면 복잡할 수 있다. 스칼라의 raw 문자열이 대안일 수 있다.
   * 5.2절에서 설명했듯이, 따옴표 3개로 묶은 문자열이다.
   */
  val Decimal2 = new Regex("""(-)?(\d+)(\.\d*)?""")
  /*
   * 더 짧게 하려면.. .r을 호출한다. 이것이 가능한 이유는 SringOps 클래스 안에
   * r이라는 메소드가 있어서 문자열을 정규 표현식으로 변환하기 때문이다.
   */
  val Decimal3 = """(-)?(\d+)(\.\d*)?""".r
  
  /*
   * 2. 정규 표현식 검색
   * 
   * 어떤 정규 표현식이 문자열 안에 나타나는지를 검사하는 연산은 여러가지가 있다.
   * 
   * regex findFirstIn str
   * 이 메소드는 str 문자열 안에 regex 정규표현식과 매치되는 첫번째 부분 문자열을 검색한다.
   * 결과는 Option 타입으로 돌려준다.
   * 
   * regex findAllIn str
   * str 문자열 안에 regex 정규 표현식과 매치되는 모든 문자열을 반환한다.결과는 Iterator 타입이다.
   * 
   * regex findPrefixOf str
   * str의 맨 앞부분부터 검사해 정규 표현식 regex와 매치할 수 있는 접두사
   * 예를 들어 banana의 접두사로는 b, ba, ban, bana, banan, banana가 있으며
   * banana를 제외한 5개의 문자열은 진접두사라 한다.
   */
  val input = "for -1.0 to 99 by 3"               //> input  : String = for -1.0 to 99 by 3
  for (s <- Decimal findAllIn input)
    println(s)                                    //> -1.0
                                                  //| 99
                                                  //| 3
  
  Decimal findFirstIn input                       //> res0: Option[String] = Some(-1.0)
  Decimal findPrefixOf input                      //> res1: Option[String] = None
  
  /*
   * 3. 정규 표현식 뽑아내기
   * 
   * 더 나아가 스칼라의 모든 정규 표현식은 익스트랙터를 정의한다.
   * 예를 들어 십진수를 다음과 같이 세 부분으로 나눌 수 있다.
   * 
   * Decimal 정규식 값에 unapplySeq 메소드 정의가 있다. 정규식 세 그룹을 패턴의 원소로
   * 반환한다. 어떤 그룹이 빠진 경우, 해당 값은 null이 된다.
   */
  val Decimal(sign, integerpart, decimalpart) = "-1.23"
                                                  //> sign  : String = -
                                                  //| integerpart  : String = 1
                                                  //| decimalpart  : String = .23
  sign                                            //> res0: String = -
  integerpart                                     //> res1: String = 1
  decimalpart                                     //> res2: String = .23
  
  for (Decimal(s,i,d) <- Decimal findAllIn input)
    println("sign: "+ s +", integer: "+ i +", decimal: "+ d)
                                                  //> sign: -, integer: 1, decimal: .0
                                                  //| sign: null, integer: 99, decimal: null
                                                  //| sign: null, integer: 3, decimal: null
  
}