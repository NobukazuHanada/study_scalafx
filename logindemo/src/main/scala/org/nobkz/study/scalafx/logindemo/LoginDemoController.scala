package org.nobkz.study.scalafx.logindemo

import scalafx.event.ActionEvent
import scalafx.scene.control.{TextField}
import scalafxml.core.macros.sfxml

@sfxml
class LoginFormController(
  private val accountField: TextField,
  private val passwordField: TextField
){

  def handleLogin(event: ActionEvent): Unit = {

  }

  def handleCancel(event: ActionEvent): Unit =  {
    accountField.clear()
    passwordField.clear()
  }

}
