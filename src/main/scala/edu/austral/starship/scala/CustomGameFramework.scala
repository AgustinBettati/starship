package edu.austral.starship.scala

import edu.austral.starship.base.vector.Vector2
import edu.austral.starship.scala.base.framework.{GameFramework, ImageLoader, WindowSettings}
import processing.core.{PConstants, PGraphics, PImage}
import processing.event.KeyEvent

object CustomGameFramework extends GameFramework {
  private var x = 250
  private var y = 250
  private var image: PImage = _

  override def setup(windowsSettings: WindowSettings, imageLoader: ImageLoader): Unit = {
    windowsSettings.setSize(500, 500)
    windowsSettings.enableHighPixelDensity()

    image = imageLoader.load("images/foto.png")
  }

  //puede que sea mas optimo usar el key set y no el key pressed
  override def draw(graphics: PGraphics, timeSinceLastDraw: Float, keySet: Set[Int]): Unit = {
    keySet.foreach {
      case PConstants.UP => y = y - 1
      case PConstants.LEFT => x = x - 1
      case PConstants.RIGHT => x = x + 1
      case PConstants.DOWN => y = y + 1
    }
    graphics.image(image, x,y,70,70)
  }

  override def keyPressed(event: KeyEvent): Unit = {
  }

  override def keyReleased(event: KeyEvent): Unit = {

  }
}
