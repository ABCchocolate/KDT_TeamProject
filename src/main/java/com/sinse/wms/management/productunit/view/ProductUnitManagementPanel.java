package com.sinse.wms.management.productunit.view;

import com.sinse.wms.management.BaseEtcTablePanel;
import com.sinse.wms.product.model.ProductUnit;
import com.sinse.wms.product.repository.ProductUnitDAO;

import javax.swing.table.TableModel;

public class ProductUnitManagementPanel extends BaseEtcTablePanel<ProductUnit> {
	private ProductUnitDAO productUnitDAO = new ProductUnitDAO();

	public ProductUnitManagementPanel() {
		super();
	}

	@Override
	protected void init() {
		setModifyButtonEnable(false);
		setDeleteButtonEnable(false);
	}

	@Override
	protected TableModel getTableModel() {
		this.data = productUnitDAO.selectAll();
		ProductUnitTableModel model = new ProductUnitTableModel();
		model.setProductUnits(this.data);
		return model;
	}

	@Override
	protected String getTitle() {
		return "단위 관리";
	}

	@Override
	protected void onSearch() {
		String input = this.getInput();
		this.data = productUnitDAO.selectAll();
	}

	@Override
	protected void onAdd() {
		ProductUnitDialog dialog = new ProductUnitDialog("단위 관리");
		dialog.setOnComplete(()->{
			updateTableUi();
		});
		dialog.setVisible(true);
	}

	@Override
	protected void onModify() {
		ProductUnitDialog dialog = new ProductUnitDialog("단위 관리", this.selectedData);
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
