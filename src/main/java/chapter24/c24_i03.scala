package chapter24

/**
 * 24.3 Traversable 트레이트
 * 
 * 컬렉션 계층의 가장 꼭대기에는 Traversable(순회 가능) 트레이트가 있다.
 * Traverable에 있는 유일한 연산은 foreach뿐이다.
 * 
 * def foreach[U](f: Elem => U)
 * 
 * 클래스가 Traversable을 구현하려면 이 메소드만 정의함녀 된다. 다른 모든 메소드는
 * Traversable의 것을 상속한다.
 * 
 * Elem은 컬렉션 원소의 타입이고. U는 임의의 결과 타입이다.
 * 
 *  - 추가 메소드: ++는 두 순회 가능 객체를 하나로 엮거나, 어떤 순회 가능 객체의 뒤에 이터레이터의
 *  모든 원소를 추가한다.
 *  - 맵 연산: map, flatMap, collect는 어떤 함수를 컬렉션에 있는 원소에 적용해 새로운
 *  컬렉션을 만들어낸다.
 *  - 변환 연산: toIndexedSeq, toIterable, toStream, toArray, toList
 *  , toSeq, toSet, toMap은 Traversable 컬렉셔능ㄹ 더 구체적인 컬렉션으로 변환한다.
 *  만약 호출 대상 객체가 이미 그 타입이라면 객체 자신을 반환한다.
 *  - 복사 연산: copyToBuffer, copyToArray는 이름 그대로 컬렉션의 원소를 버퍼나 배열에
 *  각각 복사한다.
 *  - 크기 연산: isEmpty, nonEmpty, size, hasDefiniteSize. 순회가능한 컬렉션은
 *  유한할 수도 있고, 무한할 수도 있다. 무한한 순회 가능 컬렉션의 예로는 자연수의 스트림인 Stream.from(0)을
 *  들 수 있다. hasDefiniteSize는 컬렉션이 무한할 가능성이 있는지 알려준다. false라면 무한할 수도 있다.
 *  - 원소를 가져오는 연산: head, last, headOption, lastOption, find.
 *  이들은 컬렉션의 첫 원소(head, headOption)나 마지막 원소(last, lastOption)를 선택하거나,
 *  어떤 조건을 만족하는 첫 원소(find)를 선택한다.
 *  예를 들어 해시 집합은 실행할 때마다 달라 질 수도 있는 해시 키를 가지고 원소를 저장한다. 그런 경우 해시 집합의 첫
 *  원소는 프로그램을 실행할 때마다 바뀔 수 있다. 어떤 컬렉션이 매번 원소를 같은 순서로 반환하는 경우 이를 순서가 있는
 *  (ordered) 컬렉션이라 한다. 보통은 순서가 있는 컬렉션이지만, 몇몇 해시 집합 등은 그렇지 않다.
 *  디버깅을 위해 순서가 꼭 필요하다면 순서가 있는 LinkedHashSet을 사용할 순 있다.
 *  - 하위 컬렉션을 가져오는 연산: takeWhile, tail, init, slice, take, drop, filter,
 *  dropWhile, filterNot, withFilter. 이들은 모두 인덱스 범위나 술어에 따라 컬렉션의 일부를
 *  반환한다.
 *  - 분할 연산: splitAt, span, partition, groupBy. 컬렉션을 여러 하위 컬렉션으로 나눈다.
 *  - 원소 테스트 메소드: exists, forall, count는 컬렉션 원소를 어떤 술어에 따라 테스트한다.
 *  - 폴드(fold) 연산: foldLeft, foldRight, /:, :\, reduceLeft, reduceRight.
 *  이항 연산자를 컬렉션의 두 인접한 원소에 반복 적용한다.
 *  - 특정 폴드 메소드: sum, product, min, max는 특정 타입(수이거나 변경 가능한 타입)의 컬렉션에서만
 *  작동한다.
 *  - 문자열 연산: mkString, addString, stringPrefix. 컬렉션을 문자열로 바꾸는 여러 방법을 제공.
 *  - 뷰 연산: 오버로드한 두 가지 view 메소드가 존재. 뷰는 필요에 따라 나중에 계산이 이뤄지는 컬렉션이다.
 */
object c24_i03 {
  
}