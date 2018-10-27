package edu.austral.starship.scala.base.models

/**
  * @author Agustin Bettati
  * @version 1.0
  */
case class Player(name: String, var score: Int, var lives: Int, var spaceship: Spaceship) {

  def increaseScore(addition: Int): Unit ={
    score = score + addition
  }

  def assignNewShip(newSpaceship: Spaceship): Unit = {
    lives = lives - 1
    spaceship = newSpaceship
  }

}

object Player {
  def apply(name: String): Player = new Player(name, 0, 5, Spaceship())
}
