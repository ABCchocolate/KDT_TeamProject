package com.sinse.wms.management.location.view;

import com.sinse.wms.management.BaseEtcTablePanel;
import com.sinse.wms.product.model.Location;
import com.sinse.wms.product.repository.LocationDAO;

import javax.swing.table.TableModel;

public class LocationManagementPanel extends BaseEtcTablePanel<Location> {
	private LocationDAO locationDAO = new LocationDAO();

	public LocationManagementPanel() {
		super();
	}

	@Override
	protected void init() {
		setModifyButtonEnable(false);
		setDeleteButtonEnable(false);
	}

	@Override
	protected TableModel getTableModel() {
		this.data = locationDAO.selectAll();
		LocationTableModel model = new LocationTableModel();
		model.setLocations(this.data);
		return model;
	}

	@Override
	protected String getTitle() {
		return "창고위치 관리";
	}

	@Override
	protected void onSearch() {
		String input = this.getInput();
		this.data = locationDAO.selectAll();
	}

	@Override
	protected void onAdd() {
		LocationDialog dialog = new LocationDialog("창고 위치 관리");
		dialog.setOnComplete(()->{
			updateTableUi();
		});
		dialog.setVisible(true);
	}

	@Override
	protected void onModify() {
		LocationDialog dialog = new LocationDialog("창고 위치 관리", this.selectedData);
		dialog.setOnComplete(()->{
			updateTableUi();
		});
		dialog.setVisible(true);
	}

	@Override
	protected void onDelete() {
		// TODO Auto-generated method stub

	}
}
