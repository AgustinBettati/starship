package edu.austral.starship.scala.base.view

import java.awt.{Rectangle, Shape}

import edu.austral.starship.scala.base.framework.WindowSettings
import edu.austral.starship.scala.base.model._
import edu.austral.starship.scala.base.utils.Configuration
import processing.core.{PConstants, PGraphics}

/**
  * @author Agustin Bettati
  * @version 1.0
  */

object ProcessingDrawer {
  def endFrame(players: List[Player], graphics: PGraphics): Unit = {
    graphics.background(255,255,255)
    val winner = players.maxBy(player => player.lives)
    for((player, index) <- players.zipWithIndex) {
      graphics.textAlign(PConstants.CENTER)
      graphics.textSize(40)
      graphics.fill(48, 132, 22)
      graphics.text(s"Player ${winner.name} wins!", Configuration.size/2,200)
      graphics.textSize(20)
      graphics.fill(0, 0, 0)
      graphics.text(s"${player.name} -> Score: ${player.score}, Lives: ${player.lives}", Configuration.size/2,index*50 + 300 )
    }
  }


  def setupVisual(settings: WindowSettings): Unit = {
    settings.setSize(Configuration.size, Configuration.size)
    settings.enableHighPixelDensity()
  }

  def drawObjects(players: List[Player], objects: List[RenderResult], graphics: PGraphics): Unit = {
    graphics.background(255,255,255)
    objects foreach {
      case RenderResult(position, direction, image, sizeOfImage, shape, _) =>
        graphics.pushMatrix()
        graphics.imageMode(PConstants.CENTER)
        graphics.translate(position.x, position.y)
        val angle = direction.inverse.angle
        graphics.rotate(angle)
        graphics.image(image, 0, 0, sizeOfImage, sizeOfImage)
        graphics.popMatrix()
    }
    for((player, index) <- players.zipWithIndex) {
      infoUnderSpaceship(graphics, player)
      visualizeScore(graphics, player, index)
    }
  }

  private def infoUnderSpaceship(graphics: PGraphics, player: Player): Unit = {
    val spaceship = player.spaceship
    val pos = spaceship.position
    graphics.textSize(10)
    graphics.fill(0, 0, 0)
    graphics.text(s"${player.name}", pos.x - 30, pos.y + 45, 100)

    spaceship.guns.head match {
      case LaserGun(amtOfBullets) =>
        graphics.fill(41, 76, 35)
        graphics.text(s"Laser:$amtOfBullets", pos.x - 30, pos.y + 70, 100)
      case ShotGun(amtOfBullets) =>
        graphics.fill(132, 53, 19)
        graphics.text(s"Shotgun:$amtOfBullets", pos.x - 30, pos.y + 70, 100)
    }

    graphics.fill(174, 255, 185)
    graphics.noStroke()
    graphics.rect(pos.x - 30, pos.y + 50, spaceship.health * 60 / 100, 8)
    graphics.noFill()
    graphics.stroke(0)
    graphics.rect(pos.x - 30, pos.y + 50, 60, 8)
  }

  private def visualizeScore(graphics: PGraphics, player: Player, index: Int): Unit = {
    graphics.textSize(20)
    graphics.fill(0, 0, 0)
    graphics.text(s"${player.name}", index * 100 + 30, 40, 100)
    graphics.textSize(15)
    graphics.fill(60, 65, 255)
    graphics.text(s"Score: ${player.score}", index * 100 + 30, 60, 100)
    graphics.text(s"Lives: ${player.lives}", index * 100 + 30, 85, 100)
  }
}
