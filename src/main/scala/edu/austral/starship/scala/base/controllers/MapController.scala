package edu.austral.starship.scala.base.controllers

import edu.austral.starship.scala.base.models.Spaceship

/**
  * @author Agustin Bettati
  * @version 1.0
  */
object MapController {
  private var objects: List[Spaceship] = Nil

  def addObjects(newObjects: List[Spaceship]): Unit = {
    objects = newObjects ::: objects
  }

  //voy a poder devolver Renderable directamente
  def obtainObjects: List[Spaceship] = objects
}
