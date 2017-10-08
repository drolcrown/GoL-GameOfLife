import HelloUI.stage

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout.BorderPane

object DrawingUI  extends JFXApp{
  stage = new JFXApp.PrimaryStage {
    title.value = "SELOKO"
    scene = new Scene(800, 650) {
      val menuBar = new MenuBar
      val fileMenu = new Menu("File")
      val newItem = new MenuItem("New")
      val openItem = new MenuItem("Open")
      val saveItem = new MenuItem("Save")
      val quitItem = new MenuItem("Quit")
      fileMenu.items = List(newItem, openItem, saveItem, quitItem, new SeparatorMenuItem())

      val editMenu = new Menu("Edit")
      val addItem = new MenuItem("Add")
      val copyItem = new MenuItem("Copy")
      val cutItem = new MenuItem("Cut")
      val pasteItem = new MenuItem("Paste")
      editMenu.items = List(addItem, copyItem, cutItem, pasteItem)
      menuBar.menus = List(fileMenu, editMenu)

      val rootPane = new BorderPane()
      val tapPane = new TabPane()

      rootPane.top = menuBar
      rootPane.center = tapPane
      root = rootPane
    }
  }
}
