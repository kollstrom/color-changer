package no.kollstrom.lemus.image.process

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ProcessorTest {

  private lateinit var colors: Set<Int>
  private lateinit var hexColors: Set<String>

  @BeforeAll
  internal fun setUp() {
    colors =
      Processor.findColors(javaClass.classLoader.getResource("images/4-colors.png")?.file ?: "")
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
}
