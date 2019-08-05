package no.kollstrom.lemus.image.process

import no.kollstrom.lemus.image.Color
import java.io.File
import javax.imageio.ImageIO

object Processor {

  fun findColors(file: String): Set<Int> {
    val image = ImageIO.read(File(file))
    val colors = mutableListOf<Int>()
    for (y in 0 until image.height) {
      colors.add(image.getRGB(0, y))
    }
    val countMap = colors.groupingBy { it }.eachCount().filter { it.value < 2 }
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
}

