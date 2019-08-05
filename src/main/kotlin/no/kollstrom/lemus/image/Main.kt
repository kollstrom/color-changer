package no.kollstrom.lemus.image

import no.kollstrom.lemus.image.process.saveImage

fun main(args: Array<String>) {
  // TODO: Sanitize
  saveImage(args.first(), "tzt", -1, -16777216)
}
