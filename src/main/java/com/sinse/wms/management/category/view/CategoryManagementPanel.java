package com.sinse.wms.management.category.view;

import com.sinse.wms.management.BaseEtcTablePanel;
import com.sinse.wms.product.model.Category;
import com.sinse.wms.product.repository.CategoryDAO;

import javax.swing.table.TableModel;
import java.util.List;

public class CategoryManagementPanel extends BaseEtcTablePanel<Category> {
	private CategoryDAO categoryDAO = new CategoryDAO();

	public CategoryManagementPanel() {
		super();
	}

	@Override
	protected void init() {
		setModifyButtonEnable(false);
		setDeleteButtonEnable(false);
	}

	@Override
	protected TableModel getTableModel() {
		List<Category> categories = categoryDAO.selectAll();
		this.data = categories;
		CategoryManagementTableModel model = new CategoryManagementTableModel();
		model.setCategories(this.data);
		return model;
	}

	@Override
	protected String getTitle() {
		return "카테고리 관리";
	}

	@Override
	protected void onSearch() {
		String input = this.getInput();
		this.data = categoryDAO.selectAll();
	}

	@Override
	protected void onAdd() {
		CategoryDialog dialog = new CategoryDialog("카테고리 관리");
		dialog.setVisible(true);
	}

	@Override
	protected void onModify() {
		CategoryDialog dialog = new CategoryDialog("카테고리 관리", this.selectedData);
		dialog.setVisible(true);
	}

	@Override
	protected void onDelete() {
		// TODO Auto-generated method stub

	}
}
