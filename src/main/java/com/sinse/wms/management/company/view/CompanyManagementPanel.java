package com.sinse.wms.management.company.view;

import com.sinse.wms.management.BaseEtcTablePanel;
import com.sinse.wms.product.model.Company;
import com.sinse.wms.product.repository.CompanyDAO;

import javax.swing.table.TableModel;
import java.util.List;

public class CompanyManagementPanel extends BaseEtcTablePanel<Company> {
	private CompanyDAO companyDAO = new CompanyDAO();

	public CompanyManagementPanel() {
		super();
	}

	@Override
	protected void init() {
		setModifyButtonEnable(false);
		setDeleteButtonEnable(false);
	}

	@Override
	protected TableModel getTableModel() {
		List<Company> companies = companyDAO.selectAll();
		this.data = companies;
		CompanyTableModel model = new CompanyTableModel();
		model.setCompanies(this.data);
		return model;
	}

	@Override
	protected String getTitle() {
		return "거래처 관리";
	}

	@Override
	protected void onSearch() {
		String input = this.getInput();
		this.data = companyDAO.selectAll();
	}

	@Override
	protected void onAdd() {
		CompanyDialog dialog = new CompanyDialog("거래처 관리");
		dialog.setVisible(true);
	}

	@Override
	protected void onModify() {
		CompanyDialog dialog = new CompanyDialog("거래처 관리", this.selectedData);
		dialog.setVisible(true);
	}

	@Override
	protected void onDelete() {
		// TODO Auto-generated method stub

	}
}
