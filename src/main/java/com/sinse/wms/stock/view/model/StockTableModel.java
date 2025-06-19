package com.sinse.wms.stock.view.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.sinse.wms.product.model.Stock;
import com.sinse.wms.product.repository.StockDAO;

public class StockTableModel extends AbstractTableModel{
	StockDAO stockDAO;
	List<Stock> list;
	String[] column = {};
	
	//생성자
	public StockTableModel() {
		stockDAO = new StockDAO();
		list = stockDAO.selectAll();
	}
	
	//몇개의 행인지 확인
	@Override
	public int getRowCount() {
		return list.size();
	}
	
	//몇개의 컬럼인지 확인
	@Override
	public int getColumnCount() {
		return column.length;
	}
	
	//값 지정하기
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
	}
	
}
