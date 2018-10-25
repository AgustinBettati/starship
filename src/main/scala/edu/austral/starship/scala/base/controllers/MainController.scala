package edu.austral.starship.scala.base.controllers

import edu.austral.starship.scala.base.framework.{GameFramework, ImageLoader, WindowSettings}
import edu.austral.starship.scala.base.models.Player
import edu.austral.starship.scala.base.utils.Move
import processing.core.{PConstants, PGraphics, PImage}
import processing.event.KeyEvent

trait ObservableKeyEvent {
  def notifyKeyEvent(key: Int): Unit
}

object MainController extends ObservableKeyEvent with GameFramework {

  private var observers: List[KeyEventObserver] = Nil
  private var image: PImage = _

  override def setup(windowsSettings: WindowSettings, imageLoader: ImageLoader): Unit = {
    windowsSettings.setSize(500, 500)
    windowsSettings.enableHighPixelDensity()
    image = imageLoader.load("images/spaceship.png")


    val player = Player("Agustin")
    MapController.addObjects(List(player.spaceship))

    val configA: Map[Move.Value, Int] = Map(
      Move.UP -> PConstants.UP,
      Move.DOWN -> PConstants.DOWN,
      Move.LEFT -> PConstants.LEFT,
      Move.RIGHT -> PConstants.RIGHT,
      Move.FIRE -> PConstants.BACKSPACE,
      Move.GUN_CHANGE -> PConstants.TAB
    )
    val playerController = PlayerController(player, configA)
    observers = playerController :: observers
  }

  override def draw(graphics: PGraphics, timeSinceLastDraw: Float, keySet: Set[Int]): Unit = {
    graphics.background(255,255,255)
    keySet foreach notifyKeyEvent

    MapController.obtainObjects.foreach( element => {

      graphics.pushMatrix()
      graphics.imageMode(PConstants.CENTER)
      graphics.translate(element.position.x, element.position.y)
      val angle = element.direction.inverse.angle
      graphics.rotate(angle)
      graphics.image(image,0,0,70,70)
      graphics.popMatrix()

    })
  }

  override def keyPressed(event: KeyEvent): Unit = {
  }

  override def keyReleased(event: KeyEvent): Unit = {
  }

  override def notifyKeyEvent(key: Int): Unit = {
    observers.foreach(_.onKeyEvent(key))
  }
}
