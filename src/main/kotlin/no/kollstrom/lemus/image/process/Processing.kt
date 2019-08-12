package no.kollstrom.lemus.image.process

import no.kollstrom.lemus.image.Color
import java.io.File
import javax.imageio.ImageIO


fun findColors(file: String): Set<Int> {
  val image = ImageIO.read(File(file))
  val colors = mutableListOf<Int>()
  for (y in 0 until image.height) {
    colors.add(image.getRGB(0, y))
  }
  val countMap = colors.groupingBy { it }.eachCount().filter { it.value < 16 }
  return colors.filterNot { it in countMap }.toSet()
}

fun changeColors(inputFile: String, outputFile: String, black: Int, white: Int) {
  val image = ImageIO.read(File(inputFile))
  for (x in 0 until image.width) {
    for (y in 0 until image.height) {
      when (image.getRGB(x, y)) {
        Color.WHITE.value -> image.setRGB(x, y, white)
        Color.BLACK.value -> image.setRGB(x, y, black)
      }
    }
  }
  ImageIO.write(image, "png", File("$outputFile.png"))
}

fun findColorSetsInDir(dir: String): Map<String, Set<Int>> {
  return File(dir).walk().filter { it.extension == "png" }
    .map { it.absolutePath to findColors(it.absolutePath) }.toMap()
}

fun renameFiles(dir: String) {
  File(dir).walk()
    .forEachIndexed { index, file ->
      File(file.absolutePath).renameTo(File(dir + fileName(index)))
    }
}

fun fileName(number: Int): String {
  return "00$number".takeLast(3) + ".png"
}

fun <T> List<T>.distinctCombinations(): List<Pair<T, T>> {
  return listOf(
    this[0] to this[1],
    this[0] to this[2],
    this[0] to this[3],
    this[1] to this[2],
    this[1] to this[3],
    this[2] to this[3]
  )
}

fun Int.toHex() = Integer.toHexString(this).substring(4)

fun main() {
  val colorSets = findColorSetsInDir("/home/kollstrom/Pictures/color-templates")
  val combos =
    colorSets.filter { it.value.size == 4 }
      .map { it.key to it.value.toList().distinctCombinations() }.toMap()
  println(combos.keys)
  combos.forEach { file, colorCombos ->
    run {
      val number = file.substringAfterLast("/").replace(".png", "")
      colorCombos.forEach {

        changeColors(
          inputFile = "/home/kollstrom/Pictures/bw.png",
          outputFile = "/home/kollstrom/Pictures/combos/$number-${it.first.toHex()}_${it.second.toHex()}",
          black = it.first,
          white = it.second
        )
      }
    }
  }

  //  firstCombo.forEach {
  //  }
}
