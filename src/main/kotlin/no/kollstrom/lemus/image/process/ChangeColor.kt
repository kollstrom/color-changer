package no.kollstrom.lemus.image.process

import no.kollstrom.lemus.image.Color.BLACK
import no.kollstrom.lemus.image.Color.WHITE
import java.io.File
import javax.imageio.ImageIO

fun saveImage(inputFile: String, outputFile: String, black: Int, white: Int) {
  val image = ImageIO.read(File(inputFile))
  for (x in 0 until image.width) {
    for (y in 0 until image.height) {
      when (image.getRGB(x, y)) {
        WHITE.value -> image.setRGB(x, y, white)
        BLACK.value -> image.setRGB(x, y, black)
      }
    }
  }
  ImageIO.write(image, "png", File("$outputFile.png"))
}
