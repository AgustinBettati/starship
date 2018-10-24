package edu.austral.starship.scala.base.controllers

import edu.austral.starship.scala.base.framework.{GameFramework, ImageLoader, WindowSettings}
import edu.austral.starship.scala.base.models.Spaceship
import edu.austral.starship.scala.base.vector.Vector2
import processing.core.{PConstants, PGraphics, PImage}
import processing.event.KeyEvent

trait ObservableKeyEvent {
  def notifyKeyEvent(key: Int): Unit
}

object MainController extends ObservableKeyEvent with GameFramework {

  private var observers: List[KeyEventObserver] = Nil
  private var spaceship: Spaceship = _
  private var image: PImage = _

  override def setup(windowsSettings: WindowSettings, imageLoader: ImageLoader): Unit = {
    windowsSettings.setSize(500, 500)
    windowsSettings.enableHighPixelDensity()
    image = imageLoader.load("images/foto.png")

    //aca voy a instanciar a un player, y lo meto en player controller
    spaceship = Spaceship(Vector2(50,50))
    val playerController = PlayerController(spaceship)
    observers = playerController :: observers
  }

  override def draw(graphics: PGraphics, timeSinceLastDraw: Float, keySet: Set[Int]): Unit = {
    keySet foreach notifyKeyEvent
    graphics.image(image, spaceship.position.x, spaceship.position.y, 70, 70)
  }

  override def keyPressed(event: KeyEvent): Unit = {
  }

  override def keyReleased(event: KeyEvent): Unit = {

  }

  override def notifyKeyEvent(key: Int): Unit = {
    observers.foreach(_.onKeyEvent(key))
  }
}
