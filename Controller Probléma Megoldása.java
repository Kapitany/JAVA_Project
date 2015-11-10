You can get the controller from the FXMLLoader

FXMLLoader fxmlLoader = new FXMLLoader();
Pane p = fxmlLoader.load(getClass().getResource("foo.fxml").openStream());
FooController fooController = (FooController) fxmlLoader.getController();
store it in your main stage and provide getFooController() getter method.
From other classes or stages, every time when you need to refresh the loaded "foo.fxml" page, ask it from its controller:

getFooController().updatePage(strData);
updatePage() can be something like:

// ...
@FXML private Label lblData;
// ...
public void updatePage(String data){
    lblData.setText(data);
}
// ...
in the FooController class.
This way other page users do not bother about page's internal structure like what and where Label lblData is.

Also look the http://stackoverflow.com/a/10718683/682495. In JavaFX 2.2 FXMLLoader is improved.