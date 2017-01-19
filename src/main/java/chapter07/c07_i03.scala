package chapter07

/**
 * 7.3 for 표현식
 * 
 * 스칼라의 for 표현식은 반복 처리를 위한 스위스 군용 만능 칼.
 * 단순작업, 고급표현 필터링 등 다 가능하다.
 */
object c07_i03 extends App {
  /*
   * 컬렉션 순회
   */
  val filesHere = (new java.io.File(".")).listFiles
  // <- generator filesHere의 원소를 순회
  // 아얘 새로운 val로 초기화
  // for 문의 결과 값은 <-절에 의해 타입이 정해지는 컬렉션
  for(file <- filesHere)
    println(file) //file.toString 호출
  
  // Range 타입
  for(i <- 1 to 4)
    println("Iteration "+i)
  
  for(i <- 1 until 4)
    println("Iteration "+i)
  
  // 스칼라에서는 일반적이지 않다.
  for(i <- 0 to filesHere.length - 1)
    println(filesHere(i))
  
  /*
   * 필터링
   */
  for(file <- filesHere if file.getName.endsWith(".scala"))
    println(file)
  for(file <- filesHere 
      if file.getName.endsWith(".scala")
      if file.isFile
  )
    println(file)
  
  /*
   * 중첩 순회
   */
  def fileLines(file: java.io.File) = scala.io.Source.fromFile(file).getLines().toList
  def grep(pattern: String) =
    for {
        file <- filesHere
        if file.getName.endsWith("LICENSE") // 중괄호에 의해 이 라인에서 ; 세미콜론 추론 됨
        line <- fileLines(file)
        if line.trim().matches(pattern)
    } println(file +": "+line.trim)
  grep(".*war.*")
  
  /*
   * for 중에 변수 바인딩 하기
   */
  def grep2(pattern: String) =
    for (
        file <- filesHere
        if file.getName.endsWith("LICENSE");
        line <- fileLines(file)
        if line.trim().matches(pattern)
    ) println(file +": "+line.trim)
  grep2(".*war.*")
}