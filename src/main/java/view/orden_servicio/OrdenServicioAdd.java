package view.orden_servicio;

import controller.dao.*;
import controller.error.BuilderAlert;
import controller.hibernate.HibernateUtilException;
import controller.validator.ValidatorEntity;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.entity.*;
import view.AbstractIndexView;
import view.AbstractView;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import view.cliente.ClienteAdd;
import view.cliente.ClienteEdit;
import view.code_qr.CodeQR;
import view.table.TableViewFactory;

import java.io.IOException;

public class OrdenServicioAdd extends AbstractIndexView<OrdenServicioDet> {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	protected TextField nombre_apellido_cliente;

	@FXML
	protected TextField dni_cliente;

	@FXML
	protected TextField direccion_cliente;

	@FXML
	private Button editar_cli_orde;

	@FXML
	private Button nuevo_cli_or;

	@FXML
	protected TableView<OrdenServicioDet> tabla_servicios;

	@FXML
	protected TextField total_orden;

	@FXML
	protected TextField peso_orden;

	@FXML
	protected ComboBox<Servicio> servicio_orden;

	@FXML
	private Button agregar_servicio;

	@FXML
	private ComboBox<TipoPrenda> tipo_prenda;

	@FXML
	protected TextArea descripcion;

	@FXML
	protected TextField cantidad_prenda;

	@FXML
	protected TextField total_orden_pagar;

	@FXML
	protected TextField pago_adelantado;

	@FXML
	private Button edit_orden_servicio_det;

	@FXML
	private Button delete_orden_servicio_det;

	@FXML
	private Button cancelar;

	@FXML
	private Button agregar_orden_all;

	@FXML
	void agregar_orden_total(ActionEvent event) {
		ordenServicioCab.setPagoAdelantado(Double.parseDouble(pago_adelantado.getText()));
		ordenServicioCab.setPagoTotal(pagoTotal);
		ordenServicioCab.setIdDniCliente(cliente.getIdDniCliente());
		ordenServicioCab.setIdDniEmpleado(empleado.getIdDniEmpleado());
		ValidatorEntity<OrdenServicioCab> validatorEntity = new ValidatorEntity<>(ordenServicioCab);
		if (validatorEntity.isError()) {
			BuilderAlert.buildAlert(validatorEntity, Alert.AlertType.INFORMATION).showAndWait();
		} else {
			try {
				OrdenServicioCabDAO ordenServicioCabDAO = new OrdenServicioCabDAO();
				OrdenServicioDetDAO ordenServicioDetDAO = new OrdenServicioDetDAO();
				Long idOrdenServicioCab = ordenServicioCabDAO.saveOrUpdate2(ordenServicioCab);
				System.out.println(idOrdenServicioCab);
				for (OrdenServicioDet ordenServicioDet : tabla_servicios.getItems()) {
					ordenServicioDet.setIdOrdenServicioCab(idOrdenServicioCab);
					ordenServicioDetDAO.saveOrUpdate(ordenServicioDet);
				}
				CodeQR codeQR = new CodeQR(thisStage, ordenServicioCab.toString());
				codeQR.showAndWait();
				this.hide();
			} catch (HibernateUtilException e) {
				BuilderAlert.buildAlert("No se encontro el usuario.", e.getMessage(), Alert.AlertType.ERROR).show();
			} catch (DataAccessObjectException e) {
				BuilderAlert
						.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR)
						.show();
			} catch (Exception e) {
				BuilderAlert.buildAlertException(e).show();
			}
		}
	}

	@FXML
	void agregar_servicio(ActionEvent event) {
		OrdenServicioDet ordenServicioDet = new OrdenServicioDet();
		ordenServicioDet.setPesoKg(Double.parseDouble(peso_orden.getText()));
		ordenServicioDet.setCantidadPrenda(Integer.parseInt(cantidad_prenda.getText()));
		if (tipo_prenda.getValue() != null) {
			ordenServicioDet.setTipoPrendaByIdTipoPrenda(tipo_prenda.getValue());
			ordenServicioDet.setIdTipoPrenda(tipo_prenda.getValue().getIdTipoPrenda());
		}
		if (servicio_orden.getValue() != null) {
			ordenServicioDet.setServicioByIdServicio(servicio_orden.getValue());
			ordenServicioDet.setIdServicio(servicio_orden.getValue().getIdServicio());
		}
		ordenServicioDet.setDescripcionPrendas(descripcion.getText());
		// ordenServicioDet.setIdOrdenServicioCab(); Esto tiene que ser al ejecutar la
		// sentencia de query
		// ValidatorEntity<OrdenServicioDet> validatorEntity = new
		// ValidatorEntity<>(ordenServicioDet);
		// if (validatorEntity.isError()) {
		// BuilderAlert.buildAlert(validatorEntity,
		// Alert.AlertType.INFORMATION).showAndWait();
		// } else {
		pagoTotal += servicio_orden.getValue().getPrecioServicioXKg() * Double.parseDouble(peso_orden.getText());
		System.out.println(pagoTotal);
		if (!pago_adelantado.getText().equals("")) {
			total_orden_pagar.setText((pagoTotal - Double.parseDouble(pago_adelantado.getText())) + "");
		} else {
			total_orden_pagar.setText(pagoTotal + "");
		}
		total_orden.setText(pagoTotal + "");
		tabla_servicios.getItems().add(ordenServicioDet);
		peso_orden.setText("");
		cantidad_prenda.setText("");
		servicio_orden.setValue(null);
		tipo_prenda.setValue(null);
		descripcion.setText("");
		// }
	}

	@FXML
	void delete_det(ActionEvent event) {
		if (this.tableViewEntity.getValueSelected() != null) {
			OrdenServicioDetDAO ordenServicioDetDAO = new OrdenServicioDetDAO();
			try {
				ordenServicioDetDAO.delete(tableViewEntity.getValueSelected());
				pagoTotal = Double.parseDouble(total_orden.getText()) - tableViewEntity.getValueSelected().getPesoKg()
						* tableViewEntity.getValueSelected().getServicioByIdServicio().getPrecioServicioXKg();
				total_orden.setText(pagoTotal + "");
				total_orden_pagar.setText(pagoTotal + "");
				tabla_servicios.getItems().remove(tableViewEntity.getValueSelected());
			} catch (HibernateUtilException e) {
				e.printStackTrace();
			} catch (DataAccessObjectException e) {
				e.printStackTrace();
			}

		}
	}

	@FXML
	void editar_det(ActionEvent event) {
		if (this.tableViewEntity.getValueSelected() != null) {
			this.descripcion.setText(this.tableViewEntity.getValueSelected().getDescripcionPrendas());
			this.servicio_orden.setValue(this.tableViewEntity.getValueSelected().getServicioByIdServicio());
			this.tipo_prenda.setValue(this.tableViewEntity.getValueSelected().getTipoPrendaByIdTipoPrenda());
			this.cantidad_prenda.setText(this.tableViewEntity.getValueSelected().getCantidadPrenda() + "");
			this.peso_orden.setText(this.tableViewEntity.getValueSelected().getPesoKg() + "");
			pagoTotal -= this.tableViewEntity.getValueSelected().getPesoKg()
					* this.tableViewEntity.getValueSelected().getServicioByIdServicio().getPrecioServicioXKg();
			this.total_orden_pagar.setText(pagoTotal + "");
			if (!pago_adelantado.getText().equals("")) {
				this.total_orden.setText((pagoTotal - Double.parseDouble(pago_adelantado.getText())) + "");
			}

			this.tabla_servicios.getItems().remove(this.tableViewEntity.getValueSelected());
		}
	}

	protected Double pagoTotal = 0.0;

	@FXML
	void buscar_cliente(MouseEvent event) {
		if (!dni_cliente.getText().equals(""))
			try {
				ClienteDAO clienteDAO = new ClienteDAO();
				cliente = clienteDAO.get(Long.parseLong(dni_cliente.getText()));
				if (cliente != null) {
					direccion_cliente.setText(cliente.getDireccionCliente());
					nombre_apellido_cliente.setText(cliente.getNombreCliente() + ", " + cliente.getApellidoCliente());
				} else {
					BuilderAlert
							.buildAlert("No se pudo encontrar al cliente.",
									"El cliente con el DNI: " + dni_cliente.getText(), Alert.AlertType.INFORMATION)
							.showAndWait();
				}
			} catch (HibernateUtilException e) {
				BuilderAlert.buildAlert("No se encontro el usuario.", e.getMessage(), Alert.AlertType.ERROR).show();
			} catch (DataAccessObjectException e) {
				BuilderAlert
						.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR)
						.show();
			} catch (Exception e) {
				BuilderAlert.buildAlertException(e).show();
			}
	}

	@FXML
	void cancelar(ActionEvent event) {
		this.close();
	}

	protected Empleado empleado;
	protected Cliente cliente;

	@FXML
	void editar_cliente(ActionEvent event) {
		if (!dni_cliente.getText().equals(""))
			try {
				ClienteDAO clienteDAO = new ClienteDAO();
				cliente = clienteDAO.get(Long.parseLong(dni_cliente.getText()));
				ClienteEdit clienteEdit = new ClienteEdit(thisStage, cliente);
				clienteEdit.showAndWait();
				if (clienteEdit.getCliente() != null) {
					cliente = clienteEdit.getCliente();
					direccion_cliente.setText(cliente.getDireccionCliente());
					nombre_apellido_cliente.setText(cliente.getNombreCliente() + ", " + cliente.getApellidoCliente());
				}
			} catch (HibernateUtilException e) {
				BuilderAlert.buildAlert("No se encontro el usuario.", e.getMessage(), Alert.AlertType.ERROR).show();
			} catch (DataAccessObjectException e) {
				BuilderAlert
						.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR)
						.show();
			} catch (Exception e) {
				BuilderAlert.buildAlertException(e).show();
			}
	}

	@FXML
	void nuevo_cliente(ActionEvent event) {
		ClienteAdd clienteAdd = new ClienteAdd(thisStage);
		clienteAdd.showAndWait();
		if (clienteAdd.getCliente() != null) {
			cliente = clienteAdd.getCliente();
			dni_cliente.setText(cliente.getIdDniCliente() + "");
			direccion_cliente.setText(cliente.getDireccionCliente());
			nombre_apellido_cliente.setText(cliente.getNombreCliente() + ", " + cliente.getApellidoCliente());
		}
	}

	@FXML
	void initialize() {
		assert nombre_apellido_cliente != null : "fx:id=\"nombre_apellido_cliente\" was not injected: check your FXML file 're_pedidos.fxml'.";
		assert dni_cliente != null : "fx:id=\"dni_cliente\" was not injected: check your FXML file 're_pedidos.fxml'.";
		assert direccion_cliente != null : "fx:id=\"direccion_cliente\" was not injected: check your FXML file 're_pedidos.fxml'.";
		assert editar_cli_orde != null : "fx:id=\"editar_cli_orde\" was not injected: check your FXML file 're_pedidos.fxml'.";
		assert nuevo_cli_or != null : "fx:id=\"nuevo_cli_or\" was not injected: check your FXML file 're_pedidos.fxml'.";
		assert tabla_servicios != null : "fx:id=\"tabla_servicios\" was not injected: check your FXML file 're_pedidos.fxml'.";
		assert total_orden != null : "fx:id=\"total_orden\" was not injected: check your FXML file 're_pedidos.fxml'.";
		assert peso_orden != null : "fx:id=\"peso_orden\" was not injected: check your FXML file 're_pedidos.fxml'.";
		assert servicio_orden != null : "fx:id=\"servicio_orden\" was not injected: check your FXML file 're_pedidos.fxml'.";
		assert agregar_servicio != null : "fx:id=\"agregar_servicio\" was not injected: check your FXML file 're_pedidos.fxml'.";
		assert tipo_prenda != null : "fx:id=\"tipo_prenda\" was not injected: check your FXML file 're_pedidos.fxml'.";
		assert descripcion != null : "fx:id=\"descripcion\" was not injected: check your FXML file 're_pedidos.fxml'.";
		assert cantidad_prenda != null : "fx:id=\"cantidad_prenda\" was not injected: check your FXML file 're_pedidos.fxml'.";
		assert total_orden_pagar != null : "fx:id=\"total_orden_pagar\" was not injected: check your FXML file 're_pedidos.fxml'.";
		assert pago_adelantado != null : "fx:id=\"pago_adelantado\" was not injected: check your FXML file 're_pedidos.fxml'.";
		assert cancelar != null : "fx:id=\"cancelar\" was not injected: check your FXML file 're_pedidos.fxml'.";
		assert agregar_orden_all != null : "fx:id=\"agregar_orden_all\" was not injected: check your FXML file 're_pedidos.fxml'.";
		try {
			this.tableViewEntity = TableViewFactory.getTableView(OrdenServicioDet.class.getSimpleName(),
					tabla_servicios);
			tableViewEntity.chargeColumn();
			ServicioDAO servicioDAO = new ServicioDAO();
			servicio_orden.setItems(FXCollections.observableList(servicioDAO.getAll()));
			TipoPrendaDAO tipoPrendaDAO = new TipoPrendaDAO();
			tipo_prenda.setItems(FXCollections.observableList(tipoPrendaDAO.getAll()));
		} catch (HibernateUtilException e) {
			BuilderAlert.buildAlert("No se encontro el usuario.", e.getMessage(), Alert.AlertType.ERROR).show();
		} catch (DataAccessObjectException e) {
			BuilderAlert.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR)
					.show();
		} catch (Exception e) {
			BuilderAlert.buildAlertException(e).show();
		}
		ordenServicioCab.setPagoAdelantado(0);
		ordenServicioCab.setPagoTotal(0);
		total_orden_pagar.setDisable(true);
	}

	protected OrdenServicioCab ordenServicioCab = new OrdenServicioCab();

	public OrdenServicioAdd(Stage root, Empleado empleado) {
		super(root);
		try {
			this.empleado = empleado;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/orden_servicio/re_pedidos.fxml"));
			loader.setController(this);
			thisStage.getIcons().add(new Image("/img/diamond-ice-logo.png"));
			thisStage.setTitle("Diamond Ice - Orden Servicio");
			thisStage.setScene(new Scene(loader.load()));
			thisStage.setResizable(false);
		} catch (IOException e) {
			BuilderAlert.buildAlert("No se pudo abrir la vista.", "La vista recurso no se pudo cargar :(",
					Alert.AlertType.ERROR).show();
		} catch (Exception e) {
			BuilderAlert.buildAlertException(e).show();
		}
	}
}
