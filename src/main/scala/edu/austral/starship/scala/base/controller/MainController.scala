package edu.austral.starship.scala.base.controller

import java.awt.event.{KeyEvent => JavaKeyEvent}

import edu.austral.starship.scala.base.collision.CollisionEngine
import edu.austral.starship.scala.base.framework.{GameFramework, ImageLoader, WindowSettings}
import edu.austral.starship.scala.base.model._
import edu.austral.starship.scala.base.utils.{Configuration, Move}
import edu.austral.starship.scala.base.vector.Vector2
import edu.austral.starship.scala.base.view.{ProcessingDrawer, Renderer}
import processing.core.{PConstants, PGraphics, PImage}
import processing.event.KeyEvent

trait ObservableKeyEvent {
  def notifyContinuousKeyEvent(key: Int): Unit
  def notifyPressedKeyEvent(key: Int): Unit
}

trait Observable

object MainController extends GameFramework with ObservableKeyEvent {

  private var observers: List[KeyEventObserver] = Nil
  private var images: Map[String, PImage] = Map()
  private var players: List[Player] = Nil
  private var collisionEngine = new CollisionEngine[RenderResult]
  private var finished = false

  override def setup(windowsSettings: WindowSettings, imageLoader: ImageLoader): Unit = {
    ProcessingDrawer.setupVisual(windowsSettings)
    images = Renderer.loadImages(imageLoader)
    val players = List (
      "PlayerA" -> Map(
        Move.UP -> PConstants.UP,
        Move.DOWN -> PConstants.DOWN,
        Move.LEFT -> PConstants.LEFT,
        Move.RIGHT -> PConstants.RIGHT,
        Move.FIRE -> PConstants.SHIFT,
        Move.GUN_CHANGE -> PConstants.ALT
      ),
      "PlayerB" -> Map(
        Move.UP -> JavaKeyEvent.VK_2,
        Move.DOWN -> JavaKeyEvent.VK_3,
        Move.LEFT -> JavaKeyEvent.VK_1,
        Move.RIGHT -> JavaKeyEvent.VK_4,
        Move.FIRE -> JavaKeyEvent.VK_SPACE,
        Move.GUN_CHANGE -> JavaKeyEvent.VK_C
      )
    )
    configurePlayers(players)
  }

  private def configurePlayers(data: List[(String, Map[Move.Value, Int])]): Unit = {
    MapController.addObjects(
      data.zipWithIndex.map{
        case ((name, config), index) =>
          val createdPlayer = Player(name, Vector2(50 + (index+1)*((Configuration.size - 100)/(data.length+1)), Configuration.size /2))
          observers = PlayerController(createdPlayer, config) :: observers
          players = createdPlayer :: players
          createdPlayer
      }
      .map(_.spaceship)
    )
  }

  override def draw(graphics: PGraphics, timeSinceLastDraw: Float, keySet: Set[Int]): Unit = {
    if(!finished) {
      keySet foreach notifyContinuousKeyEvent
      MapController.moveObjects()
      val rendered: List[RenderResult] = Renderer.renderObjects(MapController.obtainObjects, images)
      collisionEngine.checkCollisions(rendered)
      ProcessingDrawer.drawObjects(players,rendered, graphics)
    }
    else ProcessingDrawer.endFrame(players, graphics)
  }

  override def keyPressed(event: KeyEvent): Unit = notifyPressedKeyEvent(event.getKeyCode)

  override def keyReleased(event: KeyEvent): Unit = Unit

  override def notifyContinuousKeyEvent(key: Int): Unit = observers.foreach(_.onContinuousKeyEvent(key))

  override def notifyPressedKeyEvent(key: Int): Unit = observers.foreach(_.onPressedKeyEvent(key))

  def endGame(): Unit = finished = true
}
