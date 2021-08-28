package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable, DataChangeListener{
	
	// declarando uma dependencia com a DepartmentServer para receber os dados da tabela
	private DepartmentService service;
	
	
	//manupulaçao de tabelas no JavaFX
	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department,Integer> tableColumnId;
	
	@FXML
	private TableColumn<Department,String> tableColumnName;
	
	@FXML
	private Button btNew;
	
	// carrega os itens da tabela
	private ObservableList<Department> obslist;
	
	@FXML
	public void onBtNewAction(@SuppressWarnings("exports") ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Department obj = new Department();
		createDialogForm(obj, "/gui/DepartmentForm.fxml", parentStage);
	}
	
	//metodo certo de injetar dependencia
	public void setDepartmentService(@SuppressWarnings("exports") DepartmentService service) {
		this.service = service;

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
	
	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Department> list = service.findall();
		obslist = FXCollections.observableArrayList(list);
		tableViewDepartment.setItems(obslist);
	}
	//criar a janela de criaçao de departamento
	private void createDialogForm(Department obj,String AbsoluteName, Stage ParentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(AbsoluteName));
			Pane pane = loader.load();
			
			DepartmentFormController controller = loader.getController();
			controller.setDepartment(obj);
			controller.SetDepartmentService(new DepartmentService());
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();
			
			Stage dialogStage = new Stage();
			//nome da janela
			dialogStage.setTitle("Enter Department data");
			//setando a nova janela
			dialogStage.setScene(new Scene(pane));
			//faz a janela nao ser redimencionada
			dialogStage.setResizable(false);
			
			dialogStage.initOwner(ParentStage);
			//faz a janela ser model(janela de fundo nao funciona ate ela ser fechada)
			dialogStage.initModality(Modality.WINDOW_MODAL);
			
			dialogStage.showAndWait();
			
		}
		catch(IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void onDataChanged() {
		updateTableView();
		
	}
}
