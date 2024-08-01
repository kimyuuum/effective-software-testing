package chapter8

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BowlingTest {
    @Test
    @DisplayName("모든 프레임이 스트라이크인 경우")
    fun calculateAllStrike() {
        val games =
            listOf(
                "[x]", // 10
                "[x]", // 10 + 10
                "[x]", // 10 + 10
                "[x]", // 10 + 10
                "[x]", // 10 + 10
                "[x]", // 10 + 10
                "[x]", // 10 + 10
                "[x]", // 10 + 10
                "[x]", // 10 + 10
                "[x]", // 10 + 10
                "[x]", // 10 + 10
            )

        val score = Bowling.calculate(games)
        assertThat(score).isEqualTo(210)
    }

    @Test
    @DisplayName("모든 프레임이 스페어인 경우")
    fun calculateAllSpare() {
        val games =
            listOf(
                "[2 /]", // 10
                "[2 /]", // 10 + 2
                "[2 /]", // 10 + 2
                "[2 /]", // 10 + 2
                "[2 /]", // 10 + 2
                "[2 /]", // 10 + 2
                "[2 /]", // 10 + 2
                "[2 /]", // 10 + 2
                "[2 /]", // 10 + 2
                "[2 /]", // 10 + 2
                "[2]", // 2 + 2
            )

        val score = Bowling.calculate(games)
        assertThat(score).isEqualTo(122)
    }

    @Test
    @DisplayName("스트 / 스페어가 없는 경우")
    fun calculateGame() {
        val games =
            listOf(
                "[2 3]",
                "[2 3]",
                "[2 3]",
                "[2 3]",
                "[2 3]",
                "[2 3]",
                "[2 3]",
                "[2 3]",
                "[2 3]",
                "[2 3]",
            )

        val score = Bowling.calculate(games)
        assertThat(score).isEqualTo(50)
    }

    @Test
    @DisplayName("마지막 게임이 스트라이크일 경우 조건에 맞게 연장되었는지")
    fun isValidGameSizeWithStrike() {
        val strikeGames =
            listOf(
                "[2 3]",
                "[2 3]",
                "[2 3]",
                "[2 3]",
                "[2 3]",
                "[2 3]",
                "[2 3]",
                "[2 3]",
                "[2 3]",
                "[x]",
                "[1 2]",
            )

        Bowling.validateGame(strikeGames)
    }

    @Test
    @DisplayName("마지막 게임이 스페어일 경우 조건에 맞게 연장되었는지")
    fun isValidGameSizeWithSpare() {
        val spareGames =
            listOf(
                "[2 3]",
                "[2 3]",
                "[2 3]",
                "[2 3]",
                "[2 3]",
                "[2 3]",
                "[2 3]",
                "[2 3]",
                "[2 3]",
                "[2 /]",
                "[1 2]",
            )

        assertThrows<AssertionError> {
            Bowling.validateGame(spareGames)
        }
    }

    @Test
    @DisplayName("게임 사이즈 검증")
    fun isValidGameSize() {
        val spareGames =
            listOf(
                "[2 3]",
                "[2 3]",
                "[2 3]",
                "[2 3]",
                "[2 3]",
                "[2 3]",
            )

        assertThrows<IllegalArgumentException> {
            Bowling.validateGame(spareGames)
        }
    }

    @Test
    @DisplayName("게임 점수 검증")
    fun isValidGameScore() {
        val games =
            listOf(
                "[2 /]", // 10
                "[x]", // 10 + 10
                "[5 1]", // 6 + 6
                "[4 2]", // 6
                "[x]", // 10
                "[5 /]", // 10 + 10
                "[1 2]", // 3 + 1
                "[x]", // 10
                "[3 2]", // 5 + 5
                "[1 7]", // 8
            )

        assertThat(Bowling.calculate(games)).isEqualTo(110)
    }

    @Test
    @DisplayName("아무것도 못쳤을 때")
    fun isValidZeroScore() {
        val games =
            listOf(
                "[0 0]",
                "[0 0]",
                "[0 0]",
                "[0 0]",
                "[0 0]",
                "[0 0]",
                "[0 0]",
                "[0 0]",
                "[0 0]",
                "[0 0]",
            )

        assertThat(Bowling.calculate(games)).isEqualTo(0)
    }
}

/*
볼링 경기는 열 번의 라운드가 진행된다
매 라운드마다 선수들은 한 프레임을 치른다
한 프레임 내에서 볼링공 열 개의 핀을 넘어뜨릴 수 있는 기회가 두 번 주어진다.
각 프레임의 점수는 핀이 넘어진 숫자로 계산된다.
스트라이크나 스페어를 하면 보너스 점수를 얻는다

스트라이크는 공을 한 번 던져서 핀을 모두 넘어뜨리는 것을 말한다
핀 10개를 모두 넘어뜨리면 10점과 보너스를 얻는다
보너스는 다음 프레임에서 넘어뜨린 핀의 숫자만큼의 점수다
예를 들어 [x] [1 2]는 두 프레임에서 16점을 딴다

스페어는 한 프레임에서 두 번의 시도로 모든 핀을 넘어뜨리는것을 말한다
다음 프레임에서 처음 얻은 점수를 보너스 점수로 얻을 수 있다
예를 들면 [4 /] [3 2]인 경우 13 + 5 로 18점이 된다

만약 열 번째 프레임에서 스트라이크나 스페어를 하면 스페어일 경우 한 번,
스트라이크일경우 두 번의 기회가 더 주어진다.
이 프레임에서 세 번을 초과해서 던질 수는 없다.
열 개 프레임에 대한 경기 결과를 받아서 최종 점수를 반환하는 프로그램을 작성하자.
TDD 주기를 사용하자.
테스트를 작성하고, 통과시키고, 반복하자.
 */
