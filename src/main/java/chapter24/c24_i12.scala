package chapter24

import scala.collection.immutable.WrappedString

/**
 * 24.12 문자열
 * 
 * 배열과 마찬가지로 문자열 자체가 시퀀스는 아니다. 하지만 이들도 시퀀스로 변환할 수 있고,
 * 모든 시퀀스 연산을 지원한다. 다음은 문자열에 대해 호출할 수 있는 여러 연산을 보여준다.
 */
object c24_i12 extends App {
  
  val str= "hello"                                //> str  : String = hello
  str.reverse                                     //> res0: String = olleh
  str.map(_.toUpper)                              //> res1: String = HELLO
  str drop 3                                      //> res2: String = lo
  str slice(1, 4)                                 //> res3: String = ell
  // 지금 까지 StringOps 암시적 변환, 다음은 WrappedString으로 암시적 변환이 이루어 진거라 하는데
  // 인터프리터는 일반 스트링 다루듯 하고 있다. 2.11 버전에와서 달라진 걸수도 있는데 나중에 다시 조사하겠다.
  val s: Seq[Char] = str                          //> s  : Seq[Char] = hello
  //val s2: Seq[Char] = WrappedString + str
}