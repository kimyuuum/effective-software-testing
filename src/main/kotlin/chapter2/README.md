# Chapter2

## `StringUtils.isEmpty()`

Java에서는 보통 StringUtils, CollectionUtils 등을 이용해서 많이 체크함.

- `isNullOrEmpty()` : returns true if this nullable char sequence is either null or empty.
- `isEmpty()`, `isNotEmpty()`, `isNullOrEmpty()` 등을 사용가능하다.

## object / class 차이

object로 클래스를 정의하면 싱글턴 패턴이 적용되어 객체가 한번만 생성되도록 한다.
코틀린에서는 object를 사용하면 boilerplate를 작성하지 않아도 된다.
익명 객체를 생성할 때도 사용된다.

`companion object`

- 인스턴스 메서드는 아니지만 어떤 클래스의 관련 있는 메서드와 팩터리 메서드를 담을 때 사용된다.
- 동반 객체 메서드에 접근할 때는 동반 객체가 포함된 클래스의 이름을 사용가능하다.
- 객체의 이름을 따로 지정할 필요가 없어서 동반 객체의 멤버를 사용하는 구문은 자바의 정적 메서드 호출이나 정적 필드 사용 구문과 같아진다.
- 이름을 지정할수도 있고, 동반 객체가 인터페이스를 상속하거나, 동반 객체 안에 확장 함수와 프로퍼티를 정의할 수 있다.

## `takeIf`

```kotlin
public inline fun <T> T.takeIf(predicate: (T) -> Boolean): T? = if (predicate(this)) this else null
```

1. T의 확장함수이다. T.takeIf로 사용 가능하다.
2. takeIf의 조건 함수 predicate는 파라미터로 T객체를 전달받는다.
3. takeIf의 조건 함수 predicate의 결과에 따라 T객체인 자기 자신이나 null을 반환한다.

`takeUnless` 함수는 takeIf와 반대로 동작한다.
takeUnless는 true일 때 null을 반환하고, false일 때 this를 반환한다.

## `max` / `maxOf`

Kotlin collections에서는 편의를 위해 `max()`, `maxOf`, `maxBy()`, `maxWith()`와 같은 최대값 함수를 제공해준다.

maxOf : 2개 이상의 Element들 중 가장 큰 값을 반환해주는 함수.

max()와의 차이점 : T가 Comparable하지 않아도 된다. 대신 selector 람다식의 결과값인 R이 Comparable해야 하고, maxOf()의 결과값이 R이다.

## `until`, `..`

- until은 마지막 수를 포함하지 않고, ..는 마지막 수를 포함한다는 차이가 있다.

1 until 5 : 1,2,3,4

1 .. 5 : 1,2,3,4,5