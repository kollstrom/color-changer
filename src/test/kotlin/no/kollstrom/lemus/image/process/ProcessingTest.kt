package no.kollstrom.lemus.image.process

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ProcessingTest {

  private lateinit var colors: Set<Int>
  private lateinit var hexColors: Set<String>

  @BeforeAll
  internal fun setUp() {
    colors =
      findColors(javaClass.classLoader.getResource("images/4-colors.png")?.file ?: "")
    hexColors = colors.map { Integer.toHexString(it).removeRange(0, 2) }.toSet()
  }

  @Test
  internal fun `finds 4 colors`() {
    println(hexColors)
    assertThat(colors.size).isEqualTo(4)
  }

  @Test
  internal fun `finds Petal`() {
    assertThat(hexColors).contains("f98866")
  }

  @Test
  internal fun `finds Poppy`() {
    assertThat(hexColors).contains("ff420e")
  }

  @Test
  internal fun `finds Stem`() {
    assertThat(hexColors).contains("80bd9e")
  }

  @Test
  internal fun `finds Spring Green`() {
    assertThat(hexColors).contains("89da59")
  }

  @Test
  internal fun `create first filename`() {
    assertThat(fileName(1)).isEqualTo("001.png")
  }

  @Test
  internal fun `create 2 digit file`() {
    assertThat(fileName(10)).isEqualTo("010.png")
  }

  @Test
  internal fun `create 3 digit file`() {
    assertThat(fileName(100)).isEqualTo("100.png")
  }

  val A = "A"
  val B = "B"
  val C = "C"
  val D = "D"

  @Test
  internal fun `find all distinct combinations`() {
    assertThat(distinctCombinations(listOf("A", "B", "C", "D"))).isEqualTo(
      listOf(
        A to B,
        A to C,
        A to D,
        B to C,
        B to D,
        C to D
      )
    )
  }
}
