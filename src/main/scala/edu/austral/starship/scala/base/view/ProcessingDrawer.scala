package edu.austral.starship.scala.base.view

import java.awt.{Rectangle, Shape}

import edu.austral.starship.scala.base.framework.WindowSettings
import edu.austral.starship.scala.base.models._
import edu.austral.starship.scala.base.utils.Configuration
import processing.core.{PConstants, PGraphics}

/**
  * @author Agustin Bettati
  * @version 1.0
  */

object ProcessingDrawer {

  def setupVisual(settings: WindowSettings): Unit = {
    settings.setSize(Configuration.size, Configuration.size)
    settings.enableHighPixelDensity()
  }

  def drawObjects(players: List[Player], objects: List[RenderResult], graphics: PGraphics): Unit = {
    graphics.background(255,255,255)
    objects foreach {
      case RenderResult(position, direction, image, sizeOfImage, shape, _) =>
//        drawBoundsOfCollider(graphics, shape)
        graphics.pushMatrix()
        graphics.imageMode(PConstants.CENTER)
        graphics.translate(position.x, position.y)
        val angle = direction.inverse.angle
        graphics.rotate(angle)
        graphics.image(image, 0, 0, sizeOfImage, sizeOfImage)
        graphics.popMatrix()
    }

    for((player, index) <- players.zipWithIndex) {
      val spaceship = player.spaceship
      val pos = spaceship.position
      graphics.fill(174, 255, 185)
      graphics.noStroke()
      graphics.rect(pos.x-30, pos.y + 35, spaceship.health * 60 / 100, 8)
      graphics.noFill()
      graphics.stroke(0)
      graphics.rect(pos.x-30, pos.y + 35, 60, 8)

      graphics.textSize(20)
      graphics.fill(0, 0, 0)
      graphics.text(s"${player.name}", 30, 40, 100)
      graphics.textSize(15)
      graphics.fill(60, 65, 255)
      graphics.text(s"Score: ${player.score}", 30, 60, 100)
      graphics.text(s"Lives: ${player.lives}", 30, 85, 100)
    }
  }

  private def drawBoundsOfCollider(graphics: PGraphics, shape: Shape): Unit = {
    val bounds: Rectangle = shape.getBounds
    val x: Float = bounds.getX.toFloat
    val y: Float = bounds.getY.toFloat
    val width: Float = bounds.getWidth.toFloat
    val height: Float = bounds.getHeight.toFloat
    graphics.point(x, y)
    graphics.point(x + width, y)
    graphics.point(x, y - height)
    graphics.point(x + width, y - height)
  }
}
