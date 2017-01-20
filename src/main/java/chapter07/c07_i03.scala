package chapter07

/**
 * 7.3 for 표현식
 *
 * 스칼라의 for 표현식은 반복 처리를 위한 스위스 군용 만능 칼.
 * 단순작업, 고급표현 필터링 등 다 가능하다.
 */
object c07_i03 extends App {
  
  /*
   * 1. 컬렉션 순회
   */
  println("1. 컬렉션 순회")

  //val filesHere = (new java.io.File(".")).listFiles
  val filesHere = (new java.io.File("src/main/java/chapter05")).listFiles
  // <- generator filesHere의 원소를 순회
  // 아얘 새로운 val로 초기화
  // for 문의 결과 값은 <-절에 의해 타입이 정해지는 컬렉션
  for (file <- filesHere)
    println(file) //file.toString 호출

  println

  // Range 타입
  for (i <- 1 to 4)
    println("Iteration " + i)

  println

  for (i <- 1 until 4)
    println("Iteration " + i)

  println

  // 스칼라에서는 일반적이지 않다.
  for (i <- 0 to filesHere.length - 1)
    println(filesHere(i))

  println

  /*
   * 2. 필터링
   */
  println("2. 필터링")

  for (file <- filesHere if file.getName.endsWith(".scala"))
    println(file)

  println

  for (
    file <- filesHere if file.getName.endsWith(".scala") if file.isDirectory
  ) println(file)

  println

  /*
   * 3. 중첩 순회
   */
  println("3. 중첩 순회")

  def fileLines(file: java.io.File) = scala.io.Source.fromFile(file).getLines().toList
  def grep(pattern: String) =
    for {
      file <- filesHere
      if file.getName.endsWith(".scala") // 중괄호에 의해 이 라인에서 ; 세미콜론 추론 됨
      line <- fileLines(file)
      if line.trim().matches(pattern)
    } println(file + ": " + line.trim)
  // grep 에 쓰인 정규표현식은 자바 String matches 및 java.util.regex 를 참고
  grep(".*// val .*")

  println

  /*
   * 4. for 중에 변수 바인딩 하기
   * 
   * line.trim 이 중복되어 쓰이는 것을 막고 싶다면,
   * val 키워드 없이 trimmed 변수 선언하여 쓰면 된다.
   */
  println("4. for 중에 변수 바인딩 하기")

  def grep2(pattern: String) =
    for (
      file <- filesHere if file.getName.endsWith(".scala");
      line <- fileLines(file);
      trimmed = line.trim if trimmed.matches(pattern)
    ) println(file + ": " + trimmed)
  grep2(".*var.*")

  println

  /*
   * 5. 새로운 컬렉션 만들어내기
   * 
   * 순회의 반복 결과를 저장하기 위해서 yield 사용
   * for 절 yield 본문
   */
  println("5. 새로운 컬렉션 만들어내기")

  def scalaFiles =
    for {
      file <- filesHere
      if file.getName.endsWith(".scala")
    } yield file

  val valLineLengths =
    for {
      // File <- Array[File]
      file <- filesHere
      if file.getName.endsWith(".scala")
      // String <- List[String]
      line <- fileLines(file)
      trimmed = line.trim
      if trimmed.matches(".*// val .*")
    } // Array[Int]
    yield trimmed.length

  for (length <- valLineLengths)
    println(length)
}