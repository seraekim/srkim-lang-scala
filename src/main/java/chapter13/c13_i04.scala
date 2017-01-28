package chapter13
// 스칼라는 모든 프로그램에 몇 가지 임포트를 항상 추가한다.
import java.lang._ // java.lang 패키지, 닷넷 구현에서는 system 패키지
import scala._     // scala 패키지
import Predef._    // Predef 객체

/**
 * 13.4 암시적 임포트
 * 
 * java.lang._ 임포트 되기에 Thread 도 짧은 이름으로 쓸 수 있다.
 * 마찬가지로 scala.List가 아닌 List로 쓴다.
 * 
 * Predef 객체는 타입, 메소드, 그리고 스칼라 프로그램에서 일반적으로 사용하는 암시적 변환
 * (implicit conversion)을 포함한다.
 * Predef.assert 대신 assert 라고 쓴다.
 * 
 * 흥미로운 건 나중에 임포트된게 중복되는 이름이 있다면 앞의 임포트는 가려진다.
 * StringBuilder 클래스는 java.lang, scala 패키지에 둘다 있으나 나중에 임포트 된
 * scala의 것을 쓰게 된다.
 */