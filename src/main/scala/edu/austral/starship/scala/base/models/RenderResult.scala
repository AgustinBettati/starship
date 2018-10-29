package edu.austral.starship.scala.base.models

import java.awt.Shape

import edu.austral.starship.scala.base.collision.Collisionable
import edu.austral.starship.scala.base.vector.Vector2
import processing.core.PImage

/**
  * @author Agustin Bettati
  * @version 1.0
  */



case class RenderResult(
                         position: Vector2,
                         direction: Vector2,
                         image: PImage,
                         sizeOfImage: Float,
                         shape: Shape,
                         collisionHandler: CollisionHandler
                       ) extends Collisionable[RenderResult] {

  override def getShape: Shape = shape

  override def collisionedWith(collisionable: RenderResult): Unit = {
    collisionHandler.handleCollision( collisionable.collisionHandler )
  }
}
