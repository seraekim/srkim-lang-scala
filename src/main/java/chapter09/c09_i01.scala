package chapter09

/**
 * 9장 흐름 제어 추상화
 * 
 * 7장에서 스칼라에는 내장 제어 추상화가 많지 않다는 사실을 확인했지만, 자신이의
 * 고유한 제어 추상화를 작성할 수 있다. 9장에서는 8장의 함수 값을 활용하여 흐름 제어를 추상화 할 것이다.
 * 더불어, 커링(currying)과 이름에 의한 호출 파라미터(by-name parameter)도 살펴볼 예정이다.
 * 
 * 9.1 코드 중복 줄이기
 * 
 * 모든 함수는 호출에 따라 달라지는 비 공통 부분과,
 * 호출과 관계없이 일정한 공통 부분으로 나눠진다.
 * 
 * 공통 부분 = 함수본문, 비 공통 부분 = 인자
 * 
 * 함수를 인자로 넘긴다면?! 공통 부분이 다른 알고리즘의 비 공통 부분으로 쓰이는 것이다.
 * 이렇게 함수를 인자르 반듣 함수를 고차 함수(higher-order function)라 한다.
 * 이러한 고차함수는 간단하게 압축할 수 있는 더 많은 기회를 제공한다.
 * 
 * 1급 계층 / 함수 리터럴 / 함수값 / 클로저가 어떻게 중복 코드를 최소화 하는지 알 수 있다.
 */
object c09_i01 extends App {
  // 비공개 도우미 메소드
  private def filesHere = new java.io.File(".").listFiles
  // 질의 문자열을 통해 파일 필터링
  // 처음엔 특정 문자열로 끝나는 파일을 검색하는 API만 요구..
  def filesEnding(query: String) =
    for (file <- filesHere; if file.getName.endsWith(query))
      yield file
  // 좀 지나니.. 포함하는 검색 쿼리 API 요구...
  def filesContaining(query: String) =
    for (file <- filesHere; if file.getName.contains(query))
      yield file
  // 또 지나니... 정규식 검색 기능 요구.. pdf 확장자이면서 제목에 oopsla 같은 단어를 포함
  def filesRegex(query: String) =
    for (file <- filesHere; if file.getName.matches(query))
      yield file
  
  // 경험이 있는 프로그래머라면 비슷한 내용이 반복됨을 알아채고, 공통의 도우미 함수로 따로 만들어 낼 수 없을까 고민할 것.
  /*def filesMatching(query: String, method) =
    for (file <- filesHere; if file.getName.method(query))
      yield file*/
  /*
   * 몇몇 동적 언어에서는 적용이 가능하나, 스칼라에서는 이런식으로 실행시점에 코드를 조합하는 것을 허용하지 않는다.
   * 다만 함수값을 사용해주면 된다.
   * 
   * matcher 함수의 타입은 (String, String) => Boolean
   */
  def filesMatching(query: String, matcher: (String, String) => Boolean) =
    for (file <- filesHere; if matcher(file.getName,query))
      yield file
  
  def filesEnding2(query: String) =
    filesMatching(query, _.endsWith(_))
  def filesContaining2(query: String) =
    filesMatching(query, _.contains(_))
  def filesRegex2(query: String) =
    filesMatching(query, _.matches(_))
  
  /*
   * 위치 표시자 문법을 썼다.
   * _.endsWith(_) 는
   * (filename: String, query: String) => filename.endsWith(query)와 같다.
   * 
   * filesMatching은 이미 문자열을 받는 함수를 인자로 명시하므로 리터럴에서 타입 새얅이 가능하다.
   * (filename, query) => filename.endsWith(query)
   * 
   * 첫 번째 인자를 본문에서도 첫 번째, 두 번째 인자도 본문에서 두 번째에 쓰고 있으며 한번씩만 쓰기에
   * _.endsWith(_) 로 표현이 가능한 것이다.
   * 
   * filesMatching 은 query 인자를 중복적으로 쓰고 있어서 더욱 간단하게 해줄 수 있다.
   */
  def filesMatching2(matcher: String => Boolean) =
    for (file <- filesHere; if matcher(file.getName))
      yield file
  
  def filesEnding3(query: String) =
    filesMatching2(_.endsWith(query))
  def filesContaining3(query: String) =
    filesMatching2(_.contains(query))
  def filesRegex3(query: String) =
    filesMatching2(_.matches(query))
  
  /*
   * 위 예는 1급 계층 함수를 이용해 코드 중복을 제거할 수 있음을 보여준다.
   * 1급 계층이 아니라면 중복을 제거하기가 아주 어렵다.
   * 
   * 자바의 경우 String 인자로 받아서 Boolean 반환하는 메소드를 포함한 인터페이스를 작성하고,
   * 인터페이스를 구현하는 익명 내부 클래스의 인스턴스를 생성해 filesMatching에 전달해야 한다.
   * 자바 8에서는 JSR 335 람다 표현식의 등장으로 이런 번거로움이 사라지긴 했다.
   * 
   * -----
   * 
   * 또한 이 예제는
   * _.endsWith(_) 같은 함수 리터럴은 자유 변수가 하나도 없기에 함수 값을 만들어낸다. 자유변수 캡처가 없다.
   * _.endsWith(query) 는 1개의 바운드 변수와 query라는 자유 변수 1개가 있다. 클로저 지원이 되는 것이다.
   */
  
}