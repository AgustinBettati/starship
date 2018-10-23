package edu.austral.starship.scala

import edu.austral.starship.scala.base.framework.{GameFramework, ImageLoader, WindowSettings}
import processing.core.PGraphics
import processing.event.KeyEvent

object CustomGameFramework extends GameFramework {
  private var number = 0

  override def setup(windowsSettings: WindowSettings, imageLoader: ImageLoader): Unit = {
    windowsSettings
      .setSize(500, 500)
  }

  override def draw(graphics: PGraphics, timeSinceLastDraw: Float, keySet: Set[Int]): Unit = {

    graphics.text(number, 250,250)

  }

  override def keyPressed(event: KeyEvent): Unit = {
    number = number + 1
  }

  override def keyReleased(event: KeyEvent): Unit = {

  }
}
