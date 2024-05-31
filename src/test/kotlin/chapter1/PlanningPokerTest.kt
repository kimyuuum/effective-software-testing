package chapter1

import net.jqwik.api.*
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.util.*

class PlanningPokerTest {
    private val planningPoker = PlanningPoker()

    @Test
    fun rejectNullInput() {
        assertThatThrownBy {
            planningPoker.identifyExtremes(null)
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun rejectEmptyList() {
        assertThatThrownBy {
            val emptyList: List<Estimate> = Collections.emptyList()
            planningPoker.identifyExtremes(emptyList)
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun rejectSingleEstimate() {
        assertThatThrownBy {
            val list: List<Estimate> = Arrays.asList(Estimate("Eleanor", 1))
            planningPoker.identifyExtremes(list)
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun twoEstimates() {
        val list: List<Estimate> =
            listOf(
                Estimate("Mauricio", 10),
                Estimate("Frank", 5),
            )

        val devs = planningPoker.identifyExtremes(list)

        assertThat(devs)
            .containsExactlyInAnyOrder("Mauricio", "Frank") // 순서는 정확하지 않지만 collection 안에 값이 포함되었는지?
    }

    @Test
    fun manyEstimates() {
        val list =
            listOf(
                Estimate("Mauricio", 10),
                Estimate("Arie", 5),
                Estimate("Frank", 7),
            )

        val devs = planningPoker.identifyExtremes(list)

        assertThat(devs)
            .containsExactlyInAnyOrder("Mauricio", "Arie")
    }

    @Property
    fun inAnyOrder(
        @ForAll("estimates") estimates: MutableList<Estimate>,
    ) {
        estimates.add(Estimate("MrLowEstimate", 1))
        estimates.add(Estimate("MrHighEstimate", 100))

        estimates.shuffle()

        val dev = planningPoker.identifyExtremes(estimates)

        assertThat(dev).containsExactlyInAnyOrder("MrLowEstimate", "MrHighEstimate")
    }

    @Provide
    fun estimates(): Arbitrary<List<Estimate>> {
        val names: Arbitrary<String> =
            Arbitraries.strings()
                .withCharRange('a', 'z').ofLength(5)

        val values = Arbitraries.integers().between(2, 99)

        val estimates =
            Combinators.combine(names, values)
                .`as` { name, value -> Estimate(name, value) }

        return estimates.list().ofMaxSize(1)
    }

    @Test
    fun developersWithSameEstimates() {
        val list: List<Estimate> =
            listOf(
                Estimate("Mauricio", 10),
                Estimate("Arie", 5),
                Estimate("Andy", 10),
                Estimate("Frank", 7),
                Estimate("Annibale", 5),
            )

        val devs = planningPoker.identifyExtremes(list)

        assertThat(devs).containsExactlyInAnyOrder("Mauricio", "Arie")
    }

    @Test
    fun allDevelopersWithTheSameEstimate() {
        val list =
            listOf(
                Estimate("Mauricio", 10),
                Estimate("Arie", 10),
                Estimate("Andy", 10),
                Estimate("Frank", 10),
                Estimate("Annibale", 10),
            )

        val devs = planningPoker.identifyExtremes(list)

        assertThat(devs).isEmpty()
    }
}
