package chapter05

/**
 * 5.2 리터럴
 * 리터럴은 상수 값은 코드에 직접 적는 방법을 의미한다.
 */
object c05_i02 {
  def main(args: Array[String]): Unit = {
    /* ==========================================
     * 정수 리터럴: Int, Short, Long, Byte에 사용
     * ==========================================
     * 10진, 16진
     */
    val hex = 0x5 //5
    val hex2 = 0x00FF //255
    val magic = 0xcafebabe //-889275714
    
    /*
     * 8진 리터럴 0부터 시작하는데.. 2.11 이후 사라짐
     */
    // val oct = 035 Octal syntax is obsolete after 2.11
    
    /*
     * L / l 로 끝나면 Long이다.
     */
    val prong = 0xCAFEBABEL //3405691582
    val of = 31l
    
    /*
     * 숫자를 변수에 대입시 타입의 범위 안에 들어가면 해당 타입으로 취급한다.
     */
    val little: Short = 367
    val littler: Byte = 38
    
    /* ==========================================
     * 부동소수점 리터럴
     * ==========================================
     * 십진으로 이루어지며 E/e 다음에 지수부분(exponent)이 있기도
     */
    val big = 1.2345
    val bigger = 1.2345e1 // * 10
    val biggerStill = 1.23e47
    
    /*
     * 만약 끝에 F/f 오면 Float 아니면 Double
     * 원한다면 D/d 를 명시적으로 뒤에 붙일 수 있음
     */
    val littlef = 1.2345F
    val littleBiggerf = 3e5f
    val anotherd = 3e5
    val anotherd2 = 3e5D
    
    /* ==========================================
     * 문자리터럴
     * ==========================================
     * '' 사이에 문자입력. 8진수 또는 16진수를 넣을 수도 있으나
     * 8진수는 deprecated 됨
     */
    val a = 'A'
    // val c = '\101' Octal escape literals are deprecated, use \u0041 instead.
    // 일반 유니코드 문자를 문자 리터럴에 지정
    val c = '\u0041'
    // 아스키 코드가 아닌 유니코드 문자가 들어간 스칼라 소스파일을 아스키로만 표현하기 위해서
    val B\u0041\u0044 = 1 // val BAD = 1
    val backslash = '\\'
    /*
     * 특수문자 리터럴 이스케이프 시퀀스
     * \n : 개행(line feed) \u000A
     * \b : 백스페이스 \u0008
     * \t : 탭 \u0009
     * \f : 페이지 넘김(form feed) \u000C
     * \r : 줄맨앞으로(carriage return) \u0000
     * \" : 큰따옴표 \u0022
     * \' : 작은따옴표 \u0027
     * \\ : 역슬래시 \u005C
     */
    
    /* ==========================================
     * 문자열 리터럴
     * ==========================================
     */
    val escapes = "\\\"\'" // \"'
    
    /*
     * 이스케이프가 많으면 복잡.. raw 문자열을 위한 문법 추가
     * 큰따옴표 """ 3개로 시작.. 즉 raw문자열 내부에 """을 제외하면 모든 문자 넣을 수 있다.
     */
    println("""Wel.
                Type "HELP"  """)
    // 위의 경우 2번째 줄에 공백이 그대로 출력된다 stripMargin 호출위해 | 파이프 사용
    
    println("""|Wel.
               |Type "HELP"  """.stripMargin)
    
    /* ==========================================
     * 심볼 리터럴
     * ==========================================
     * 'cymbal -> Symbol("cymbal") 팩토리 메소드 호출로 바꿈
     * 심볼은 intern 하므로, 만약 같은 심볼 리터럴을 사용하면, 두 표현식 모두 완전히 동일한 Symbol 객체 참조
     */
    //updateRecordByName(name, "Ok") not found: value name
    updateRecordByName('favoriteAlbum, "OK")
    
    /* ==========================================
     * 불리언 리터럴
     * ==========================================
     * true, false
     */
  }
  
  def updateRecordByName(r: Symbol, value: Any) {
    println(r.name)
  }
}