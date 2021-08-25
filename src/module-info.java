module JavaFX {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	opens model.entities to javafx.graphics, javafx.fxml, javafx.base;
	
	exports gui;
	opens gui to javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
}