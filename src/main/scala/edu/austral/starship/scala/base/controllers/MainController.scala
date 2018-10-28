package edu.austral.starship.scala.base.controllers

import java.awt.event.{KeyEvent => JavaKeyEvent}

import edu.austral.starship.scala.base.framework.{GameFramework, ImageLoader, WindowSettings}
import edu.austral.starship.scala.base.models.{Asteroid, Bullet, Player, Spaceship}
import edu.austral.starship.scala.base.utils.Move
import edu.austral.starship.scala.base.vector.Vector2
import edu.austral.starship.scala.base.view.{ProcessingDrawer, Renderer}
import processing.core.{PConstants, PGraphics, PImage}
import processing.event.KeyEvent

trait ObservableKeyEvent {
  def notifyKeyEvent(key: Int): Unit
}

object MainController extends ObservableKeyEvent with GameFramework {

  private var observers: List[KeyEventObserver] = Nil
  private var images: Map[String, PImage] = Map()

  override def setup(windowsSettings: WindowSettings, imageLoader: ImageLoader): Unit = {
    ProcessingDrawer.setupVisual(windowsSettings)
    images = ProcessingDrawer.loadImages(imageLoader)


    val player = Player("Agustin")
    MapController.addObjects(List(player.spaceship))

    val configA: Map[Move.Value, Int] = Map(
      Move.UP -> PConstants.UP,
      Move.DOWN -> PConstants.DOWN,
      Move.LEFT -> PConstants.LEFT,
      Move.RIGHT -> PConstants.RIGHT,
      Move.FIRE -> PConstants.SHIFT,
      Move.GUN_CHANGE -> PConstants.TAB
    )
    val playerController = PlayerController(player, configA)
    observers = playerController :: observers
  }

  override def draw(graphics: PGraphics, timeSinceLastDraw: Float, keySet: Set[Int]): Unit = {
    keySet foreach notifyKeyEvent

    MapController.moveObjects()
    val rendered = Renderer.renderObjects(MapController.obtainObjects, images)
    ProcessingDrawer.drawObjects(rendered, graphics)
  }

  override def keyPressed(event: KeyEvent): Unit = {
  }

  override def keyReleased(event: KeyEvent): Unit = {
  }

  override def notifyKeyEvent(key: Int): Unit = {
    observers.foreach(_.onKeyEvent(key))
  }

}
