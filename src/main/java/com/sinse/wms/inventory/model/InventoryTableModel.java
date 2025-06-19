package com.sinse.wms.inventory.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.sinse.wms.product.model.Stock;
import com.sinse.wms.product.repository.StockDAO;

public class InventoryTableModel extends AbstractTableModel{
	StockDAO stockDAO;
	List<Stock> list;
	List<String> column;
	
	//생성자
	public InventoryTableModel(List<String> column) {
		stockDAO = new StockDAO();
		list = stockDAO.selectAll();
		this.column = column;
	}
	
	//몇개의 행인지 확인
	@Override
	public int getRowCount() {
		return list.size();
	}
	
	//몇개의 컬럼인지 확인
	@Override
	public int getColumnCount() {
		return column.size();
	}
	
	//값 지정하기
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
	}
}
