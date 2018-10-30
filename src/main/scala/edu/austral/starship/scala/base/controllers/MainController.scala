package edu.austral.starship.scala.base.controllers

import java.awt.event.{KeyEvent => JavaKeyEvent}

import edu.austral.starship.scala.base.collision.CollisionEngine
import edu.austral.starship.scala.base.framework.{GameFramework, ImageLoader, WindowSettings}
import edu.austral.starship.scala.base.models._
import edu.austral.starship.scala.base.utils.{Configuration, Move}
import edu.austral.starship.scala.base.vector.Vector2
import edu.austral.starship.scala.base.view.{ProcessingDrawer, Renderer}
import processing.core.{PConstants, PGraphics, PImage}
import processing.event.KeyEvent

trait ObservableKeyEvent {
  def notifyContinuousKeyEvent(key: Int): Unit
  def notifyPressedKeyEvent(key: Int): Unit
}

object MainController extends ObservableKeyEvent with GameFramework {

  private var observers: List[KeyEventObserver] = Nil
  private var images: Map[String, PImage] = Map()
  private var players: List[Player] = Nil
  private var collisionEngine = new CollisionEngine[RenderResult]

  override def setup(windowsSettings: WindowSettings, imageLoader: ImageLoader): Unit = {
    ProcessingDrawer.setupVisual(windowsSettings)
    images = Renderer.loadImages(imageLoader)


    val playerA = Player("Player A", Vector2(100, Configuration.size /2))
    val configA: Map[Move.Value, Int] = Map(
      Move.UP -> PConstants.UP,
      Move.DOWN -> PConstants.DOWN,
      Move.LEFT -> PConstants.LEFT,
      Move.RIGHT -> PConstants.RIGHT,
      Move.FIRE -> PConstants.SHIFT,
      Move.GUN_CHANGE -> PConstants.ALT
    )

//    val playerB = Player("Player B", Vector2(Configuration.size-100, Configuration.size /2))
//    val configB: Map[Move.Value, Int] = Map(
//      Move.UP -> JavaKeyEvent.VK_W,
//      Move.DOWN -> JavaKeyEvent.VK_S,
//      Move.LEFT -> JavaKeyEvent.VK_A,
//      Move.RIGHT -> JavaKeyEvent.VK_D,
//      Move.FIRE -> JavaKeyEvent.VK_SPACE,
//      Move.GUN_CHANGE -> JavaKeyEvent.VK_C
//    )
    players = List(playerA)
    MapController.addObjects(players.map(_.spaceship))
    observers = PlayerController(playerA, configA) :: observers

  }

  override def draw(graphics: PGraphics, timeSinceLastDraw: Float, keySet: Set[Int]): Unit = {
    keySet foreach notifyContinuousKeyEvent
    MapController.moveObjects()
    val rendered: List[RenderResult] = Renderer.renderObjects(MapController.obtainObjects, images)
    collisionEngine.checkCollisions(rendered)
    ProcessingDrawer.drawObjects(players,rendered, graphics)
  }

  override def keyPressed(event: KeyEvent): Unit = {
    notifyPressedKeyEvent(event.getKeyCode)
  }

  override def keyReleased(event: KeyEvent): Unit = {
  }

  override def notifyContinuousKeyEvent(key: Int): Unit = observers.foreach(_.onContinuousKeyEvent(key))

  override def notifyPressedKeyEvent(key: Int): Unit = observers.foreach(_.onPressedKeyEvent(key))

}
