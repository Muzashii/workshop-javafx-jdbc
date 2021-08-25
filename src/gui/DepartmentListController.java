package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;

public class DepartmentListController implements Initializable{
	
	//manupulaçao de tabelas no JavaFX
	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department,Integer> tableColumnId;
	
	@FXML
	private TableColumn<Department,String> tableColumnName;
	
	@FXML
	private Button btNew;
	
	@FXML
	public void onBtNewAction() {
		System.out.println("OnBtNewAction");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// fazer a tabela funcionar
		
		inicializeNodes();
		
	}

	private void inicializeNodes() {
		//iniciar o comportamento das tabelas
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		// deixar a tabela do tamnho da janela
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
	}

}
